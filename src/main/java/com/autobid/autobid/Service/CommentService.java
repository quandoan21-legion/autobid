package com.autobid.autobid.Service;

import com.autobid.autobid.DTO.CommentDTO;
import com.autobid.autobid.DTO.AnswerDTO;
import com.autobid.autobid.Entity.answers;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Entity.comments;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.AnswerRepo;
import com.autobid.autobid.Repository.CarInformationRepo;
import com.autobid.autobid.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private CarInformationService carInformationService;
    @Autowired
    private CarInformationRepo carInformationRepo;

    public boolean isCarOwner(Integer carId, Integer userId) {
        car_information car = carInformationRepo.findById(carId).orElse(null);
        return car != null && car.getF_user_id().getId() == userId;
    }


    private final MessageFactory message = new MessageFactory();

    @Transactional
    public MessageFactory createComment(Integer carId, Integer userId, String commentText) {
        // Fetch the car information without persisting it again
        car_information carInformation = carInformationRepo.findById(carId).orElse(null);
        if (carInformation == null) {
            return message.MessageResponse("Invalid car ID", false, List.of());
        }

        comments comment = new comments();
        comment.setUser_id(userId);
        comment.setCarId(carId);
        comment.setComment_text(commentText);

        comments savedComment = commentRepo.save(comment);
        return message.MessageResponse("Comment created successfully", true, List.of(convertToDTO(savedComment)));
    }

    @Transactional
    public MessageFactory createAnswer(Integer carId, Integer userId, Integer commentId, String answerText) {
        // Check if user is car owner
        if (!carInformationService.isCarOwner(carId, userId)) {
            return message.MessageResponse("Only the car owner can answer comments", false, List.of());
        }

        // Check if comment exists and belongs to the car
        comments comment = commentRepo.findById(commentId).orElse(null);
        if (comment == null || comment.getCarId() != carId) {
            return message.MessageResponse("Invalid comment", false, List.of());
        }

        // Check if comment already has an answer
        if (answerRepo.existsByCommentId(commentId)) {
            return message.MessageResponse("This comment already has an answer", false, List.of());
        }

        answers answer = new answers();
        answer.setUser_id(userId);
        answer.setCommentId(commentId);
        answer.setAnswer_text(answerText);

        answers savedAnswer = answerRepo.save(answer);
        return message.MessageResponse("Answer created successfully", true, List.of(convertToAnswerDTO(savedAnswer)));
    }

    public MessageFactory getCommentsByCarId(Integer carId) {
        List<comments> commentsList = commentRepo.findByCarId(carId);
        List<CommentDTO> commentDTOs = commentsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return message.MessageResponse("Comments retrieved successfully", true, commentDTOs);
    }


    private CommentDTO convertToDTO(comments comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setUserId(comment.getUser_id());
        dto.setCarId(comment.getCarId());
        dto.setCommentText(comment.getComment_text());
        dto.setCreatedAt(comment.getCreated_at());

        answers answer = answerRepo.findByCommentId(comment.getId()).orElse(null);
        if (answer != null) {
            dto.setAnswer(convertToAnswerDTO(answer));
        }

        return dto;
    }

    private AnswerDTO convertToAnswerDTO(answers answer) {
        AnswerDTO dto = new AnswerDTO();
        dto.setId(answer.getId());
        dto.setUserId(answer.getUser_id());
        dto.setCommentId(answer.getCommentId());
        dto.setAnswerText(answer.getAnswer_text());
        dto.setCreatedAt(answer.getCreated_at());
        return dto;
    }
}