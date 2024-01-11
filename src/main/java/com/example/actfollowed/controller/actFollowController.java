package com.example.actfollowed.controller;

import com.example.actfollowed.dto.ActFollowRequest;
import com.example.actfollowed.model.ActFollowedVO;
import com.example.actfollowed.service.IActFollowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class actFollowController {

    @Autowired
    private IActFollowedService actFollowedService;

    @GetMapping("/activity/actfollow")
    public ResponseEntity<Byte> getActFollows(@RequestParam Integer actId,
//                                              @RequestParam Integer memId,
                                              HttpSession session) {

        //第1種方式.actId從原先詳情那邊的controller的session存到model 存給前端了

        //第2種方式.模擬從session取會員id
        Integer testMemId = 2;
        session.setAttribute("memId", testMemId);

        Integer memId = (Integer) session.getAttribute("memId");

        Byte actFollows = actFollowedService.getActFollows(actId, memId);
        return ResponseEntity.status(HttpStatus.OK).body(actFollows);
    }

    //應該不需要回傳 沒意義 可以為void
    @PostMapping("/activity/actfollow")
    public ResponseEntity<ActFollowedVO> createActFollow(@RequestBody ActFollowRequest actFollowRequest,
                                                         HttpSession session) {

        //模擬從session取會員id
        Integer testMemId = 2;
        session.setAttribute("memId", testMemId);

        Integer memId = (Integer) session.getAttribute("memId");
        actFollowRequest.setMemId(memId);

        ActFollowedVO actFollowed = actFollowedService.createActFollow(actFollowRequest);

        return ResponseEntity.status(HttpStatus.OK).body(actFollowed);
    }

    //應該不需要回傳 沒意義 可以為void
    @PostMapping("/activity/unactfollow")
    public ResponseEntity<ActFollowedVO> updateActFollow(@RequestBody ActFollowRequest actFollowRequest,
                                                         HttpSession session) {
        //模擬從session取會員id
        Integer testMemId = 2;
        session.setAttribute("memId", testMemId);

        Integer memId = (Integer) session.getAttribute("memId");
        actFollowRequest.setMemId(memId);

        ActFollowedVO actFollowed = actFollowedService.updateActFollow(actFollowRequest);

        return ResponseEntity.status(HttpStatus.OK).body(actFollowed);
    }
}
