package org.devs.heythere_backend.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class Receiver {
    private final SimpMessagingTemplate template;

    @KafkaListener(id = "main-listener", topics = "kafka-chatting")
    public void receive(ChatMessage message) throws Exception {
        log.info("writer: {},  message :{}",
                message.getWriter(), message.getMessage() );
        Map<String, String> msg = new HashMap<>();
        msg.put("timestamp", Long.toString(message.getTimeStamp()));
        msg.put("message", message.getMessage());
        msg.put("author", message.getWriter());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);

        template.convertAndSend("/topic/public", json);
    }
}
