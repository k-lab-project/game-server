package klab.sugangstar.service;

import klab.sugangstar.config.LogDataProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LogDataProducer logDataProducer;
    private static final String LOG_FILE_PATH = "sugang-star/log/app.log";
    private static final String KAFKA_TOPIC = "spark";

    @Transactional
    @Scheduled(fixedRate = 30000)
    public void sendDataForKafka(){
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                logDataProducer.send(KAFKA_TOPIC, line);
//                System.out.println("Sent message to Kafka: " + line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
