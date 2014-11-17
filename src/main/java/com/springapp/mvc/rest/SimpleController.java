package com.springapp.mvc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @RequestMapping("/get")
    public String get() {
        System.out.println("SimpleController.get");
        return "hello";
    }

    @ResponseBody
    @RequestMapping("/get2")
    public String get2() {
        return "hello";
    }

}
