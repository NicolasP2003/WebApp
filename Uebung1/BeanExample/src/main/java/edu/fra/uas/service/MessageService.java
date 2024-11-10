package edu.fra.uas.service;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private String message;

    public String getMessage() {
        log.info("getMessage called. Returning message: {}", message);
        return message;
    }

    public void setMessage(String message) {
        log.info("setMessage called. Updating message from '{}' to '{}'", this.message, message);
        this.message = message;
    }

}