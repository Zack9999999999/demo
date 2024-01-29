package com.example.indexcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
//@RequestMapping("/CHA104G3")
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/membership/commentreport") //要改
    public String commentReport() {
        return "commentreport";
    }

    @RequestMapping("/membership/report") //要改
    public String report() {
        return "report";
    }

    @RequestMapping("/membership/actregistration")
    public String actreg() {
        return "actregistration";
    }

    @RequestMapping("/membership/actfollow")
    public String actfollow() {
        return "actfollow";
    }

    @RequestMapping("/membership/actreview")
    public String actreview() {
        return "actReview";
    }
}
