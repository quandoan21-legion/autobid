// File: `src/main/java/com/autobid/autobid/DTO/CommentDTO.java`
package com.autobid.autobid.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private int id;
    private int userId;
    private int carId;
    private String commentText;
    private Date createdAt;
    private AnswerDTO answer;
    private String commenterName;
    private String commenterImage;
}