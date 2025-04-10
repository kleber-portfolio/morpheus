package io.github.mrspock182.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mrspock182.entity.Person;
import io.github.mrspock182.messaging.adapter.ChosenPersonMessagingAdapter;
import io.github.mrspock182.messaging.dto.ChosenPersonMessagingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
public class ChosenPersonProducerWithSqs implements ChosenPersonProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenPersonProducerWithSqs.class);

    private final String topic;
    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    public ChosenPersonProducerWithSqs(
            SqsClient sqsClient,
            ObjectMapper objectMapper,
            @Value("${app.queues.chosen-person}") String topic) {
        this.topic = topic;
        this.sqsClient = sqsClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void produce(final Person person) {
        try {
            final ChosenPersonMessagingDto producer = ChosenPersonMessagingAdapter.cast(person);
            final String json = objectMapper.writeValueAsString(producer);
            final SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(topic)
                    .messageBody(json)
                    .build();
            sqsClient.sendMessage(request);
        } catch (RuntimeException | JsonProcessingException ex) {
            LOGGER.error("Error to send message to SQS", ex);
            throw new RuntimeException(ex);
        }
    }
}
