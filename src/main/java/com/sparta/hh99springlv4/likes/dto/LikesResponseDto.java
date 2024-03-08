package com.sparta.hh99springlv4.likes.dto;

import com.sparta.hh99springlv4.likes.entity.Likes;
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
