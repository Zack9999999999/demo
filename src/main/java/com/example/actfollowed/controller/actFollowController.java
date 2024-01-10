package com.example.actfollowed.controller;

import com.example.actfollowed.dto.ActFollowRequest;
import com.example.actfollowed.model.ActFollowedVO;
import com.example.actfollowed.service.IActFollowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class actFollowController {

    @Autowired
    private IActFollowedService actFollowedService;

    @PostMapping("/activity/actfollow")
    public ResponseEntity<ActFollowedVO> createActFollow(@RequestBody ActFollowRequest actFollowRequest) {
        ActFollowedVO actFollowed = actFollowedService.createActFollow(actFollowRequest);

        return ResponseEntity.status(HttpStatus.OK).body(actFollowed);
    }

    @PostMapping("/activity/unactfollow")
    public ResponseEntity<ActFollowedVO> updateActFollow(@RequestBody ActFollowRequest actFollowRequest) {
        ActFollowedVO actFollowed = actFollowedService.updateActFollow(actFollowRequest);

        return ResponseEntity.status(HttpStatus.OK).body(actFollowed);
    }
}
