package com.autobid.autobid.Controller;

import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public MessageFactory createComment(@RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentRequest.getCarId(), commentRequest.getUserId(), commentRequest.getCommentText());
    }

    @PostMapping("/answer")
    public MessageFactory createAnswer(@RequestBody AnswerRequest answerRequest) {
        return commentService.createAnswer(answerRequest.getCarId(), answerRequest.getUserId(), answerRequest.getCommentId(), answerRequest.getAnswerText());
    }

    @GetMapping("/car/{carId}")
    public MessageFactory getCommentsByCarId(@PathVariable Integer carId) {
        return commentService.getCommentsByCarId(carId);
    }
}