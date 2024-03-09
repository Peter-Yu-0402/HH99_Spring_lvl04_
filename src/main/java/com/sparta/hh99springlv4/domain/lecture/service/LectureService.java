package com.sparta.hh99springlv4.domain.lecture.service;


import com.sparta.hh99springlv4.domain.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.FindLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.ReadLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.SelectLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.lecture.entity.OrderByEnum;
import com.sparta.hh99springlv4.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.domain.likes.entity.Likes;
import com.sparta.hh99springlv4.domain.likes.repository.LikesRepository;
import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import com.sparta.hh99springlv4.domain.teacher.repository.TeacherRepository;
import com.sparta.hh99springlv4.global.handler.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

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
    public ReadLectureResponseDto readLecture(Long lectureId) {
        // lectureId로 특정한 Lecture
        Lecture lecture = lectureRepository.findById(lectureId)
                    .orElseThrow(() -> new NotFoundException("Not found lecture id" + lectureId));


        // 연관관계 설정
        List<Comment> commentList = lecture.getCommentList();

        // 댓글 외 나머지 강의 및 강사 정보 ResponseDTO에 추가
        SelectLectureResponseDto responseDto = new SelectLectureResponseDto(lecture);

        // 댓글 목록을 ResponseDTO에 추가
        List<CommentResponseDto> commentResponseDtoList = commentList.stream().map(CommentResponseDto::new).toList();

        // 좋아요 집계
        List<Likes> likesList = likesRepository.findAllByLectureId(lectureId);
        int likesCount = 0;
        for (Likes likes : likesList) {
            if (likes.isLikes()) {
                likesCount++;
            }
        }
        return new ReadLectureResponseDto(responseDto, commentResponseDtoList, likesCount);
    }

    @Transactional
    public List<FindLectureResponseDto> findLecturesByCategory(String categoryBy, String orderBy, String lectureBy) {
        // Lecture 데이터타입 List 객체 초기화
        List<Lecture> lectureList;
        // switch문 categroyBy, lectureBy
        switch (lectureBy) {
            // 강의명 기준 정렬
            case "LECTURE_NAME" :
                lectureList = lectureRepository
                        .findByLectureCategoryOrderByLectureNameDesc(CategoryEnum.valueOf(categoryBy));
                break;
            // 강의 가격 기준 정렬
            case "LECTURE_PRICE" :
                lectureList = lectureRepository
                        .findByLectureCategoryOrderByLecturePriceDesc(CategoryEnum.valueOf(categoryBy));
                break;
            // default : 강의 등록일자 기준 정렬
            default:
                lectureList = lectureRepository
                        .findByLectureCategoryOrderByLectureRegistrationDateDesc(CategoryEnum.valueOf(categoryBy));
        }
        // 오름차순일 경우 리스트 정렬 역순으로 배열
        if (OrderByEnum.ASC.equals(OrderByEnum.valueOf(orderBy))) {
            Collections.reverse(lectureList);
        }

        return lectureList.stream().map(FindLectureResponseDto::new).toList();
    }
}