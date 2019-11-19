package com.cloud.galaxy.demo.message.controller;

import com.cloud.galaxy.common.core.message.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageSender messageSender;

    public
}
