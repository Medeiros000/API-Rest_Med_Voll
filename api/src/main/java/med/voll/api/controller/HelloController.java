package med.voll.api.controller;

import med.voll.api.view.HelloView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String ola() {
        return HelloView.generatePage("Hello There, Spring!!");
    }

}
