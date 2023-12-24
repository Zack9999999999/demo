package com.example.comment.controller;

import com.example.comment.dto.CommentRequest;
import com.example.comment.service.CommentService;
import com.example.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{comId}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer comId) {

        Comment comment = commentService.getCommentById(comId);

        if (comment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments() {

        List<Comment> comments = commentService.getComments();

        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> insertCommnet(@RequestBody @Valid CommentRequest commentRequest) {

        Integer comId = commentService.insertComment(commentRequest);

        Comment comment = commentService.getCommentById(comId);

        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @PutMapping("/comments/{comId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer comId,
                                                 @RequestBody @Valid CommentRequest commentRequest) {
        //檢查 comment 是否存在
        Comment comment = commentService.getCommentById(comId);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改留言的數據
        commentService.updateComment(comId, commentRequest);
        Comment updatecomment = commentService.getCommentById(comId);

        return ResponseEntity.status(HttpStatus.OK).body(updatecomment);
    }

    @DeleteMapping("/comments/{comId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer comId) {

        commentService.deleteComment(comId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
