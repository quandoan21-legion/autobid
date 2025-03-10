package com.autobid.autobid.Controller;

import lombok.Data;

@Data
public class AnswerRequest {
    private Integer carId;
    private Integer userId;
    private Integer commentId;
    private String answerText;
}