package com.sparta.hh99springlv4.lecture.service;


import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j(topic = "LectureService")
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    // 강의 등록 기능
    @Transactional
    public LectureResponseDto createLecture(LectureRequestDto lectureRequestDto) {


        // RequestDto -> Entity
        Lecture lecture = new Lecture(lectureRequestDto);

        // DB에 저장
        Lecture saveLecture = lectureRepository.save(lecture);

        // Entity -> ResponseDto
        LectureResponseDto lectureResponseDto = new LectureResponseDto(saveLecture);

        return lectureResponseDto;
    }


    // 선택한 강의 정보 수정
    public LectureResponseDto infoLecture(Long lectureId, LectureRequestDto lectureRequestDto) {

        // 선택한 강의 정보 조회
        Lecture lecture = findLecture(lectureId);

        // 강의 정보 수정
        lecture.setLectureName(lectureRequestDto.getLectureName());
        lecture.setPrice(lectureRequestDto.getPrice());
        lecture.setIntroL(lectureRequestDto.getIntroL());
        lecture.setCategory(CategoryEnum.valueOf(lectureRequestDto.getCategory())); // 카테고리 수정


        // 변경된 강의 정보 DB에 저장
        lecture = lectureRepository.save(lecture);

        return new LectureResponseDto(lecture);
    }


    // 강의 조회 메서드 + 선택한 강의를 조회할 때 해당 강의에 등록된 댓글들도 함께 조회할 수 있습니다.
    private Lecture findLecture(Long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의는 존재하지 않습니다."));

    }

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




    // 선택한 강의 조회
    public LectureResponseDto updateLecture(Long lectureId) {

        // 선택 강의가 lecture DB에 존재하는지 확인
        Lecture lecture = findLecture(lectureId);

        return new LectureResponseDto(lecture);
    }


    // 카테고리별 강의 목록 조회 기능
    public List<LectureResponseDto> findLecturesByCategory(CategoryEnum category) {
        List<Lecture> lectures = lectureRepository.findByCategory(category);
        List<LectureResponseDto> lectureResponseDtos = new ArrayList<>();
        for (Lecture lecture : lectures) {
            lectureResponseDtos.add(new LectureResponseDto(lecture));
        }
        return lectureResponseDtos;
    }

}

