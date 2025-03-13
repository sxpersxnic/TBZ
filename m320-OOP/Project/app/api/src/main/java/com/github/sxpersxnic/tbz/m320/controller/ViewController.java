package com.github.sxpersxnic.tbz.m320.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sxpersxnic
 */
@Controller
@RequestMapping(path = "/")
public class ViewController {

    @GetMapping
    public String index() {
        return "index";
    }
}
