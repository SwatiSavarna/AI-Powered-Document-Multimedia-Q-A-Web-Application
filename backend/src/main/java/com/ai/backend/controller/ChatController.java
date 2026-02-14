package com.ai.backend.controller;

import com.ai.backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;

    @PostMapping("/{fileId}")
    public String ask(@PathVariable Long fileId,
                      @RequestBody Map<String, String> body) {

        return service.ask(fileId, body.get("question"));
    }

    @GetMapping("/summary/{fileId}")
    public String summary(@PathVariable Long fileId) {
        return service.summarize(fileId);
    }
}