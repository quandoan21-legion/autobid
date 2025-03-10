package com.autobid.autobid.Controller;

import lombok.Data;

@Data
public class CommentRequest {
    private Integer carId;
    private Integer userId;
    private String commentText;
}