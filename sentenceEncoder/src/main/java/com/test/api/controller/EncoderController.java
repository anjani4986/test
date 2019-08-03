package com.test.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.model.ContentWrapper;
import com.test.api.model.EncodedContent;

@RestController
public class EncoderController {
    @PostMapping(value="encodedcontent",consumes="application/json",produces="application/json")
    public EncodedContent getContent(@RequestBody ContentWrapper wrapper) {
        return new EncodedContent("");
    }
}
