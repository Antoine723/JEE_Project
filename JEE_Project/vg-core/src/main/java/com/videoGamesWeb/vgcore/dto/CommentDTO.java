package com.videoGamesWeb.vgcore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {

    private String comment;
    private int rating;
    private Long productId;
    private Long userId;
}
