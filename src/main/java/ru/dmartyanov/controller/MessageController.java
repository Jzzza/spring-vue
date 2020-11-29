package ru.dmartyanov.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dmartyanov.domain.Message;
import ru.dmartyanov.domain.Views;
import ru.dmartyanov.dto.EventType;
import ru.dmartyanov.dto.ObjectType;
import ru.dmartyanov.repo.MessageRepo;
import ru.dmartyanov.util.WsSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(
            @PathVariable("id") Message message
    ) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        Message createdMessage = messageRepo.save(message);
        wsSender.accept(EventType.CREATE, createdMessage);
        return createdMessage;
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updatedMessage = messageRepo.save(messageFromDb);
        wsSender.accept(EventType.UPDATE, updatedMessage);
        return updatedMessage;
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable("id") Message message
    ) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }
}
