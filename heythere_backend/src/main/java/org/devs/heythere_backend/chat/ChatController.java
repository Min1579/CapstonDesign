package org.devs.heythere_backend.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {
    private final Sender sender;
    private final Receiver receiver;

    private static final String BOOT_TOPIC = "kafka-chatting";

    @MessageMapping("/message")
    public void sendMessage(final ChatMessage message){
      log.info("message : {} ", message.getMessage());
        sender.send(BOOT_TOPIC, message);
    }
}
