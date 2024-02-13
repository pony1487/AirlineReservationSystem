package com.example.airlinereservationsystem.Reporting;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookingMessageConsumer {

    Logger logger = LoggerFactory.getLogger(BookingMessageConsumer.class);

    private int failedBookings = 0;
    private int successfulBookings = 0;

    // TODO move to shared class as this is used in BookingService
    private final String failedMessage = "[FAILED]";
    private final String successMessage = "[SUCCESS]";


    @KafkaListener(topics = "#{@topicName}", groupId = "airline-reservation-activity")
    public void listen(String message) {
        handleMessage(message);
    }

    public void handleMessage(String message) {
        if (message.startsWith(failedMessage)) {
            failedBookings++;
            logger.info("Failed booking count: " + failedBookings);
        } else if (message.startsWith(successMessage)) {
            successfulBookings++;
            logger.info("Successful booking count: " + successfulBookings);
        } else {
            logger.warn("Unknown message type");
        }
    }
}
