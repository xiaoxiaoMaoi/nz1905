package com.qf.back.v2.service.controller;

import com.qf.back.v2.service.service.IBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("back")
public class BackController {

    @Autowired
    private IBackService service;

}
