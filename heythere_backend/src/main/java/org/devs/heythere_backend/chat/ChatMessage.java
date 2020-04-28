package org.devs.heythere_backend.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessage {
    private String message;
    private String writer;
    private Long timeStamp;

    private String fileName;
    private String rawData;

    public ChatMessage(String message, String user) {
        this.writer = user;
        this.message = message;
    }

    public ChatMessage(String fileName, String rawData, String user) {
        this.fileName = fileName;
        this.rawData = rawData;
        this.writer = user;
    }

    public ChatMessage(String message) {
        this.message = message;
    }
}
