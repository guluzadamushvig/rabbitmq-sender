package com.example.rabbitsender.queue;

import com.example.rabbitsender.model.EmailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class QueueSender {
    private final ObjectMapper objectMapper;
    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.queue.mail}")
    private String mailQueue;

    public void sendMessgeToMailQueue(EmailDto emailDto){
        try{
            amqpTemplate.convertAndSend(mailQueue,objectMapper.writeValueAsString(emailDto));
        }
        catch (IOException exception){
            log.error("Error when deserialize object {} to json" + emailDto.getMail());
        }

        catch (MessagingException ex){
            //apply retry mechanism
            log.error("can't send message to queue");
        }

    }
}
