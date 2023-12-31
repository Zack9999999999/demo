package com.example.comment.controller;

import com.example.comment.dto.CommentQueryParams;
import com.example.comment.dto.CommentRequest;
import com.example.comment.service.ICommentService;
import com.example.comment.model.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentVO>> getComments(@RequestParam(defaultValue = "5") Integer limit) {

        CommentQueryParams commentQueryParams = new CommentQueryParams();
        commentQueryParams.setLimit(limit);

        List<CommentVO> comments = commentService.getComments(commentQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @GetMapping("/comments/{comId}")
    public ResponseEntity<CommentVO> getComment(@PathVariable Integer comId) {

        CommentVO comment = commentService.getCommentById(comId);

        if (comment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentVO> insertCommnet(@RequestBody @Valid CommentRequest commentRequest) {

        Integer commentId = commentService.insertComment(commentRequest);

        CommentVO comment = commentService.getCommentById(commentId);

        return ResponseEntity.status(HttpStatus.OK).body(comment);
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
