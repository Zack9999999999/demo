package com.example.details.controller;

import com.example.act.model.ActVO;
import com.example.details.service.IRetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping("/activity")
@Slf4j
public class RetailsController {
    @Autowired
    private IRetailsService retailsService;

    //活動詳情
//    @GetMapping("/{actId}")
//    public ResponseEntity<ActVO> getDetail(@PathVariable Integer actId){
//        ActVO act = retailsService.getDetail(actId);
//        return ResponseEntity.status(HttpStatus.OK).body(act);
//    }

    @GetMapping("/{actId}")
    public String actDetail(@PathVariable Integer actId, Model model) {
        ActVO act = retailsService.getDetail(actId);
        model.addAttribute("act", act);
        return "actdetails";
    }

    @GetMapping("/images/{actId}")
    public ResponseEntity<byte[]> getActPic(@PathVariable Integer actId) {

        ActVO act = retailsService.getDetail(actId);
        byte[] actPic = act.getActPic();

        if (actPic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(actPic);
        }
    }
}
