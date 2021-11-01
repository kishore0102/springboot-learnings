package com.fresco.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MyController {

    List<Key> keys = new ArrayList<>();

    @GetMapping("/")
    public String loadIndexJsp(Model model) {
        List<String> keysString = new ArrayList<>();
        for(Key key: keys){
            keysString.add(key.getKey());
        }
        model.addAttribute("keysString", keysString);
        model.addAttribute("keys", keys);
        return "index";
    }

    @PostMapping("/createkey")
    public RedirectView createKey(@ModelAttribute("createkeytext") String createkeytext) {
        System.out.println("inside createkey controller");
        System.out.println(createkeytext);
        final RedirectView redirectView = new RedirectView("/", true);
        keys.add(new Key(createkeytext));
        return redirectView;
    }

    @PostMapping("/updatekey/{id}")
    public RedirectView updateKey(@PathVariable(value = "id") int id,@ModelAttribute("updatekeytext") String updatekeytext) {
        System.out.println("inside updatekey controller");
        System.out.println(updatekeytext);
        keys.get(id).setKey(updatekeytext);
        final RedirectView redirectView = new RedirectView("/", true);
        return redirectView;
    }

    @PostMapping("/deletekey")
    public RedirectView deletekey(@ModelAttribute("deletekeytext") int deletekeytext) {
        System.out.println("inside deletekey controller");
        System.out.println(deletekeytext);
        final RedirectView redirectView = new RedirectView("/", true);
        keys.remove(deletekeytext);
        return redirectView;
    }

}