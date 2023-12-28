package com.example.comment.controller;

import com.example.comment.dto.CommentRequest;
import com.example.comment.service.ICommentService;
import com.example.comment.model.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/comments/{comId}")
    public ResponseEntity<CommentVO> getComment(@PathVariable Integer comId) {

        CommentVO comment = commentService.getCommentById(comId);

        if (comment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentVO>> getComments() {

        List<CommentVO> comments = commentService.getComments();

        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping("/comments")
    public ResponseEntity<List<CommentVO>> insertCommnet(@RequestBody @Valid CommentRequest commentRequest) {

        List<CommentVO> commentVO = commentService.insertComment(commentRequest);

        return ResponseEntity.status(HttpStatus.OK).body(commentVO);
    }

    @PutMapping("/comments/{comId}")
    public ResponseEntity<CommentVO> updateComment(@PathVariable Integer comId,
                                                   @RequestBody @Valid CommentRequest commentRequest) {
        //檢查 comment 是否存在
        CommentVO comment = commentService.getCommentById(comId);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改留言的數據
        commentService.updateComment(comId, commentRequest);
        CommentVO updateComment = commentService.getCommentById(comId);

        return ResponseEntity.status(HttpStatus.OK).body(updateComment);
    }

    @DeleteMapping("/comments/{comId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer comId) {

        commentService.deleteComment(comId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
