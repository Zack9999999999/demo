package com.example.actreg.controller;

import com.example.actreg.dto.ActRegQueryParams;
import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.ActRegStatus;
import com.example.actreg.dto.MemNameAndPicDTO;
import com.example.actreg.model.ActRegVO;
import com.example.actreg.service.IActRegService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ActRegController {

    @Autowired
    private IActRegService actRegService;

    @GetMapping("/actreg")
    public ResponseEntity<Page<ActRegVO>> getActRegs(
            HttpSession session,
            @RequestParam(required = false) Byte regStatus,
            @RequestParam(required = false) String sortDirection,
            @PageableDefault(size = 5, sort = "act.actStartTime", direction = Sort.Direction.ASC) Pageable pageable
    ) {

        //模擬從session取出會員id
        Integer testMemId = 1;
        session.setAttribute("memId", testMemId);
        Integer memId = (Integer) session.getAttribute("memId");

        //傳進來是DESC的話替換掉pageable內的Sort
        if ("DESC".equalsIgnoreCase(sortDirection)) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "act.actStartTime")
            );
        }

        ActRegQueryParams actRegQueryParams = new ActRegQueryParams();
        actRegQueryParams.setRegStatus(regStatus);

        Page<ActRegVO> actRegVOList = actRegService.getActRegs(memId, actRegQueryParams, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(actRegVOList);
    }

    //此方法用來判斷此會員有沒有報名過這個活動
    @GetMapping("/actreg/{actId}")
    public ResponseEntity<ActRegVO> getActReg(@PathVariable Integer actId,
//                                              @RequestParam Integer memId,
                                              HttpSession session) {

        //模擬從session取出會員id
        Integer testMemId = 1;
        session.setAttribute("memId", testMemId);
        Integer memId = (Integer) session.getAttribute("memId");

        ActRegVO actReg = actRegService.getActReg(actId, memId);
        if (actReg != null) {
            return ResponseEntity.status(HttpStatus.OK).body(actReg);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/actreg")
    public ResponseEntity<ActRegVO> createActReg(@RequestBody @Valid ActRegRequest actRegRequest, HttpSession session) {

        //模擬從session取會員id
        Integer testMemId = 1;
        session.setAttribute("memId", testMemId);

        Integer memId = (Integer) session.getAttribute("memId");

//        if (memId != null) {
        actRegRequest.setMemId(memId);
//        }

        ActRegVO actReg = actRegService.createActReg(actRegRequest);

        if (actReg != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(actReg);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/actreg")
    public ResponseEntity<ActRegVO> updateActReg(@RequestBody @Valid ActRegStatus actRegStatus,
                                                 HttpSession session) {

        //模擬從session取出會員id
        Integer testMemId = 1;
        session.setAttribute("memId", testMemId);
        Integer memId = (Integer) session.getAttribute("memId");
        actRegStatus.setMemId(memId);

        ActRegVO actReg = actRegService.updateActReg(actRegStatus);

        return ResponseEntity.status(HttpStatus.OK).body(actReg);
    }

    @GetMapping("/actreg/members")
    public ResponseEntity<List<MemNameAndPicDTO>> findMemNameAndPic(@RequestParam Integer actId,
                                                                    @RequestParam Integer isActPart) {

        List<MemNameAndPicDTO> memNameAndPic = actRegService.findMemNameAndPic(actId, isActPart);

        return ResponseEntity.status(HttpStatus.OK).body(memNameAndPic);
    }
}
