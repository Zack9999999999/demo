package com.example.indexcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CHA104G3")
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "index";
    }

    @RequestMapping("/member/commentreport")
    public String commentReport(){
        return "commentreport";
    }

    @RequestMapping("/member/report")
    public String report(){
        return "report";
    }
}
