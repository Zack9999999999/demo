package com.example.actreg.controller;

import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.ActRegStatus;
import com.example.actreg.dto.MemNameAndPicDTO;
import com.example.actreg.model.ActRegVO;
import com.example.actreg.service.IActRegService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ActRegVO>> getActRegs() {

        List<ActRegVO> actRegVOList = actRegService.getActRegs();

        return ResponseEntity.status(HttpStatus.OK).body(actRegVOList);
    }

    @GetMapping("/actreg/{actRegId}")
    public ResponseEntity<ActRegVO> getActReg(@PathVariable Integer actRegId) {
        ActRegVO actReg = actRegService.getActReg(actRegId);
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

    @PutMapping("/actreg/{actRegId}")
    public ResponseEntity<ActRegVO> updateActReg(@PathVariable Integer actRegId,
                                                 @RequestBody @Valid ActRegStatus actRegStatus) {
        ActRegVO actReg = actRegService.updateActReg(actRegId, actRegStatus);

        return ResponseEntity.status(HttpStatus.OK).body(actReg);
    }

    @GetMapping("/actreg/members")
    public ResponseEntity<List<MemNameAndPicDTO>> findMemIdAndPic(@RequestParam Integer actId,
                                                                  @RequestParam Integer isActPart) {

        List<MemNameAndPicDTO> memIdAndPic = actRegService.findMemIdAndPic(actId, isActPart);

        return ResponseEntity.status(HttpStatus.OK).body(memIdAndPic);
    }
}
