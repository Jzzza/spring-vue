package ru.dmartyanov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dmartyanov.domain.User;
import ru.dmartyanov.domain.Views;
import ru.dmartyanov.repo.MessageRepo;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepo messageRepo;
    private final ObjectWriter writer;
    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(MessageRepo messageRepo, ObjectMapper mapper) {
        this.messageRepo = messageRepo;
        this.writer = mapper.setConfig(mapper.getSerializationConfig()).writerWithView(Views.FullMessage.class);
    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);
            data.put("messages", messageRepo.findAll());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
