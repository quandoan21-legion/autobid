// File: `src/main/java/com/autobid/autobid/DTO/AnswerDTO.java`
package com.autobid.autobid.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class AnswerDTO {
    private int id;
    private int userId;
    private int commentId;
    private String answerText;
    private Date createdAt;
}