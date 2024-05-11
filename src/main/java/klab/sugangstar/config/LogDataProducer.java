package klab.sugangstar.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogDataProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String logData){
        kafkaTemplate.send(topic,logData);
//        log.info("good kafka" + logData);
    }

}
