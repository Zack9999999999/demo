package com.example.details.controller;

import com.example.act.model.ActVO;
import com.example.details.dto.ActDTO;
import com.example.details.dto.ActRandomDTO;
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

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/CHA104G3")
@Slf4j
public class RetailsController {

    @Autowired
    private IRetailsService retailsService;

    @GetMapping("/activity/{actId}")
    public String actDetail(@PathVariable Integer actId, Model model, HttpSession session) {
        ActDTO act = retailsService.getDetail(actId);

        //格式化活動開始跟結束日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String actStartTime = dateFormat.format(act.getActStartTime());
        String actEndTime = dateFormat.format(act.getActEndTime());
        model.addAttribute("actStartTime", actStartTime);
        model.addAttribute("actEndTime", actEndTime);

        model.addAttribute("act", act);

        String base64Pic = Base64.getEncoder().encodeToString(act.getMemPic());
        model.addAttribute("memPic", base64Pic);

        //模擬從session取會員id
        Integer testMemId = 2;
        session.setAttribute("memId", testMemId);
        Integer memId = (Integer) session.getAttribute("memId");
        model.addAttribute("memId", memId);

        return "actdetails";
    }

    @GetMapping("/activity/images/{actId}")
    public ResponseEntity<byte[]> getActPic(@PathVariable Integer actId) {

        ActDTO act = retailsService.getDetail(actId);
        byte[] actPic = act.getActPic();

        if (actPic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(actPic);
        }
    }

    @GetMapping("/activity/random")
    public ResponseEntity<List<ActRandomDTO>> randomFourAct() {
        //參數放類別 這樣就只會抓出同類別的活動
        List<ActRandomDTO> actList = retailsService.randomFourAct();

        return ResponseEntity.status(HttpStatus.OK).body(actList);
    }
}
