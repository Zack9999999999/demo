package com.example.details.controller;

import com.example.act.model.ActVO;
import com.example.details.service.IRetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetailsController {
    @Autowired
    private IRetailsService retailsService;

    //活動詳情
    @GetMapping("/activity/{actId}")
    public ResponseEntity<ActVO> getRetail(@PathVariable Integer actId){
        ActVO act = retailsService.getRetail(actId);
        return ResponseEntity.status(HttpStatus.OK).body(act);
    }
}
