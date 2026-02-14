package com.ai.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final AiClientService aiClient;

    public String ask(Long fileId, String question) {
        return aiClient.askQuestion(fileId, question);
    }

    public String summarize(Long fileId) {
        return aiClient.summarize(fileId);
    }
}