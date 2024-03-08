package com.sparta.hh99springlv4.domain.likes.dto;

import com.sparta.hh99springlv4.domain.likes.entity.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikesResponseDto {
    private boolean likes;

    public LikesResponseDto(Likes saveLikes) {
        this.likes = saveLikes.isLikes();
    }
}
