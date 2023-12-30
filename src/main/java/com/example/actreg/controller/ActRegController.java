package com.example.actreg.controller;

import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.model.ActRegVO;
import com.example.actreg.service.IActRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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
    public ResponseEntity<ActRegVO> createActReg(@RequestBody @Valid ActRegRequest actRegRequest) {

        ActRegVO actReg = actRegService.createActReg(actRegRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(actReg);
    }

    @PutMapping("/actreg/{actRegId}")
    public ResponseEntity<ActRegVO> updateActReg(@PathVariable Integer actRegId,
                                                 @RequestBody @Valid ActRegRequest actRegRequest) {
        ActRegVO actReg = actRegService.updateActReg(actRegId, actRegRequest);

        return ResponseEntity.status(HttpStatus.OK).body(actReg);
    }
}
