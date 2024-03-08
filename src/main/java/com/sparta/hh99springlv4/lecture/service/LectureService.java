package com.sparta.hh99springlv4.lecture.service;


import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto.*;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto.*;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.entity.OrderByEnum;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.likes.entity.Likes;
import com.sparta.hh99springlv4.likes.repository.LikesRepository;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.teacher.repository.TeacherRepository;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j(topic = "LectureService")
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final LikesRepository likesRepository;


    // 강의 등록 기능
    @Transactional
    public CreateLectureResponseDto createLecture(CreateLectureRequestDto lectureRequestDto) {
        // RequestDto -> Entity
        Lecture lecture = new Lecture(lectureRequestDto);

        // teacherName으로 찾은 Teacher 객체
        Teacher teacher = teacherRepository
                .findByTeacherName(lecture.getTeacherName())
                .orElseThrow(() ->
                        new NotFoundException("해당 이름의 강사를 찾을 수 없습니다." + lectureRequestDto.getTeacherName()));

        // 연관관계 설정
        lecture.setTeacher(teacher);

        // DB에 저장
        Lecture saveLecture = lectureRepository.save(lecture);

        // Entity -> ResponseDto
        CreateLectureResponseDto lectureResponseDto = new CreateLectureResponseDto(saveLecture);

        return lectureResponseDto;
    }

    @Transactional
    // 선택한 강의 조회 기능
    public ReadLectureResponseDto readLecture(ReadLectureRequestDto lectureRequestDto) {
        log.info("readLecture commencing");
        Lecture lecture;
        if (lectureRequestDto.getId() != null) {
            lecture = lectureRepository.findById(lectureRequestDto.getId())
                    .orElseThrow(() -> new NotFoundException("Not found lecture id" + lectureRequestDto.getId()));
            log.info("readLecture findById checked");

        } else {
            lecture = lectureRepository.findByLectureName(lectureRequestDto.getLectureName())
                    .orElseThrow(() -> new NotFoundException("Not found lecture title" + lectureRequestDto.getLectureName()));
        }
        // 명세가 달라지는 것, 하나만 하는 것이 오히려 좋은 것이다. 쿼리를 두 번 날리게 되었다.
        // 두 개로 만드는 경우는 일반적이지 않다. 어색하다. 보통은 PK로 찾는 것이 인덱스로 인해서 검색 성능이 더 우월하다.
        // DB의 인덱스란? DB의 인덱스란, 레코드를 쉽게 찾기 위해서! 두꺼운 책을 볼 때, 그 색인과 같은 역할이다. 색인이 없다면 처음부터 끝까지 조회해야 하는 대참사.
        // 배열의 인덱스는 밸류 찾기 위함이고,
        // DB 인덱스 검색 효율을 높이는 검색 알고리즘이 별도로 있다! 사용하는 자료구조도 다르다!
        // PK는 인덱스가 걸려있고 검색 효율이 좋다.

        // teacherName으로 찾은 Teacher 객체 // 티쳐 객체는 이미 렉쳐에 있으니 따로 찾을 필요가 없어다!
//        Teacher teacher = teacherRepository
//                .findByTeacherName(lecture.getTeacherName())
//                .orElseThrow(() ->
//                        new NotFoundException("Not found teacher : " + lecture.getTeacherName()));

        // 리포지토리에서 검색하는 행위 자체가 조인을 하는 역할을 했다? ManyToOne, 리포지토리에서 findBY하면서 같이 찾아준다. Lazy loading, 내부적으로 프록시를 써서 PK만 갖고 있다가, getTeacher
        // 위에서 lecture.setTeacher(teacher) // save.()
        // lecture.getTeacher.get속성();

        // 연관관계 설정
//        lecture.setTeacher(teacher); // 불필요한 코드. 이미 위에서 그 역할을 함
        List<Comment> commentList = lecture.getCommentList();

        // 댓글 외 나머지 강의 및 강사 정보 ResponseDTO에 추가
        SelectLectureResponseDto responseDto = new SelectLectureResponseDto(lecture);

        // 댓글 목록을 ResponseDTO에 추가
        List<CommentResponseDto> commentResponseDtoList = commentList.stream().map(CommentResponseDto::new).toList();

        // 좋아요 집계
        List<Likes> likesList = likesRepository.findAllByLectureId(lecture.getId());
        int likesCount = 0;
        for (Likes likes : likesList) {
            if (likes.isLikes()) {
                likesCount++;
            }
        }

        return new ReadLectureResponseDto(responseDto, commentResponseDtoList, likesCount);
//        return responseDto;


    }

    @Transactional
    public List<FindLectureResponseDto> findLecturesByCategory(FindLectureRequestDto lectureRequestDto) {
        // CategoryEnum으로 변환된 카테고리로 강의를 조회
        Optional<List<Lecture>> optionalLectures = lectureRepository.findAllByLectureCategory(CategoryEnum.valueOf(lectureRequestDto.getLectureCategory()));

        if (optionalLectures.isPresent()) {
            List<Lecture> lectures = optionalLectures.get();

            // 정렬 기준 설정
            Comparator<Lecture> comparator = null;
            // OrderByEnum이 null이 아닌 경우에만 정렬 기준을 설정
            if (lectureRequestDto.getOrderByEnum() != null) {
                if (lectureRequestDto.getOrderByEnum().equals(OrderByEnum.LECTURE_NAME)) {
                    comparator = Comparator.comparing(Lecture::getLectureName);
                } else if (lectureRequestDto.getOrderByEnum().equals(OrderByEnum.LECTURE_PRICE)) {
                    comparator = Comparator.comparing(Lecture::getLecturePrice);
                } else if (lectureRequestDto.getOrderByEnum().equals(OrderByEnum.LECTURE_REGISTRATION_DATE)) {
                    comparator = Comparator.comparing(Lecture::getLectureRegistrationDate);
                }
                // OrderBy 값이 true일 때 내림차순 정렬
                if (lectureRequestDto.isOrderBy()) {
                    comparator = comparator.reversed();
                }
            }

            // 정렬된 강의 목록을 FindLectureResponseDto로 변환하여 반환
            if (comparator != null) {
                lectures.sort(comparator);
            }
            return lectures.stream().map(FindLectureResponseDto::new).toList();
        } else {
            // 강의 목록이 존재하지 않을 경우 빈 목록 반환
            log.info("강의 목록이 존재하지 않습니다.");
            return Collections.emptyList();
        }
    }
}


//        -------------------------- LectureResponseDto에 같이 넣어서 가져오는 방법 (두개를 따로 반환도 되나?) ------------------------------
//        // 받아온 강의 이름으로 repository에서 다른 정보 찾아오기
//        // lectureRequestDto -> Entity
//        Lecture lecture = lectureRepository.findByLectureName(lectureRequestDto.getLectureName());
//
//        // Entity -> Repository 저장 (등록 할때만?)
////        Lecture lectureInfo = lectureRepository.save(lecture);
//
//        // 받아온 정보를 반환값으로 주기 & 선생님 정보도 같이 주기 (전화번호는 제외)
//        // Entity -> Response
////        LectureResponseDto lectureResponseDto = new LectureResponseDto(lectureInfo);
//
//        // lectureRequestDto로 들어왔으니까 여기서 가져온다
//        Teacher teacher = lecture.getTeacher();
//
//        TeacherResponseDto teacherResponseDto = new TeacherResponseDto(
//                teacher.getTeacherName(),
//                teacher.getTeacherCareer(),
//                teacher.getTeacherCompany(),
//                teacher.getTeacherIntro()
//        );
//
////        Lecture lecture = lectureRepository.findByLectureName(lectureRequestDto.getLectureName());
//
//        LectureResponseDto lectureResponseDto = new LectureResponseDto(lecture);
//
//        return new LectureResponseDto(lecture).setTeacher(teacherResponseDto);
//
////        return lectureResponseDto;




//    public LectureCommentDto findLectureComment(Long lectureId) {
//        // 강의 정보 조회
//        Lecture lecture = lectureRepository.findById(lectureId)
//                .orElseThrow(() -> new NotFoundException("해당 강의는 존재하지 않습니다."));
//
//        // 해당 강의에 등록된 댓글 목록 조회
//        List<CommentDto> commentDtos = lecture.getComments().stream()
//                .map(comment -> new CommentDto(comment.getContent(), comment.getAuthor()))
//                .collect(Collectors.toList());
//
//        // LectureWithCommentsDto 객체 생성 및 설정
//        LectureDto lectureDto = new LectureDto(lecture.getTitle(), lecture.getInstructor());
//        LectureWithCommentsDto lectureWithCommentsDto = new LectureWithCommentsDto();
//        lectureWithCommentsDto.setLecture(lectureDto);
//        lectureWithCommentsDto.setComments(commentDtos);
//
//        return lectureWithCommentsDto;
//    }



    // 선택한 강의 정보 수정
//    public LectureResponseDto infoLecture(Long lectureId, LectureRequestDto lectureRequestDto) {
//
//        // 선택한 강의 정보 조회
//        Lecture lecture = findLecture(lectureId);
//
//        // 강의 정보 수정
////        lecture.setLectureName(lectureRequestDto.getLectureName());
////        lecture.setPrice(lectureRequestDto.getPrice());
////        lecture.setIntroL(lectureRequestDto.getIntroL());
////        lecture.setCategory(CategoryEnum.valueOf(lectureRequestDto.getCategory())); // 카테고리 수정
//
//
//        // 변경된 강의 정보 DB에 저장
//        lecture = lectureRepository.save(lecture);
//
//        return new LectureResponseDto(lecture);
//    }
//
//
//    // 강의 조회 메서드 + 선택한 강의를 조회할 때 해당 강의에 등록된 댓글들도 함께 조회할 수 있습니다.
//    private Lecture findLecture(Long lectureId) {
//        return lectureRepository.findById(lectureId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 강의는 존재하지 않습니다."));
//
//    }
//

////
//
//
//    // 선택한 강의 조회
//    public LectureResponseDto updateLecture(Long lectureId) {
//
//        // 선택 강의가 lecture DB에 존재하는지 확인
//        Lecture lecture = findLecture(lectureId);
//
//        return new LectureResponseDto(lecture);
//    }






