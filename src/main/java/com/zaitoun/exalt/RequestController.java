package com.zaitoun.exalt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @GetMapping("/storage")
    public String greeting(@RequestParam(value = "size", defaultValue = "0") String size_string) {
        int size = Integer.parseInt(size_string);
        return Handler.getInstance().handleRequest(size);
    }
}
