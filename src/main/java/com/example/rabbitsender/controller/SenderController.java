package com.example.rabbitsender.controller;

import com.example.rabbitsender.model.EmailDto;
import com.example.rabbitsender.queue.QueueSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/send-mail/")
@RequiredArgsConstructor
public class SenderController {

    private final QueueSender queueSender;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMail(@RequestBody EmailDto emailDto) {
      queueSender.sendMessgeToMailQueue(emailDto);
    }
}
