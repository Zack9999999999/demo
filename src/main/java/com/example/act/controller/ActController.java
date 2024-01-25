package com.example.act.controller;

import com.example.act.dto.ActQueryParams;
import com.example.act.model.ActVO;
import com.example.act.service.IActService;
import com.example.actreg.service.IActRegService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class ActController {

    @Autowired
    private IActService actService;

    @Autowired
    private IActRegService actRegService;

    @GetMapping("/acts")
    public ResponseEntity<Page<ActVO>> reviewActs(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(required = false) Byte actStatus,
            HttpSession session
    ) {
        //模擬從session取出會員id
        Integer testMemId = 1;
        session.setAttribute("memId", testMemId);
        Integer memId = (Integer) session.getAttribute("memId");

        ActQueryParams actQueryParams = new ActQueryParams();
        actQueryParams.setMemId(memId);
        actQueryParams.setActStatus(actStatus);

        Page<ActVO> actRegList = actService.reviewActs(actQueryParams, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(actRegList);
    }

    @PutMapping("/acts/{actId}")
    public ResponseEntity<ActVO> deleteActReg(@PathVariable Integer actId) {
        ActVO actVO = actService.deleteActReg(actId);

        if (actVO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(actVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
