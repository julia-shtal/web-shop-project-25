package com.fulda.iuliiashtal.notification.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EMailService {

    private static final Logger log = LoggerFactory.getLogger(EMailService.class);

    public void sendEmail(UUID userId) {
        // Simulating email sending by logging to console
        log.info("Sending order confirmation email to user: " + userId);
    }
}
