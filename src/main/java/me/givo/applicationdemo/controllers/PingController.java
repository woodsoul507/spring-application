package me.givo.applicationdemo.controllers;


import me.givo.applicationdemo.utils.success.Success;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
public class PingController {
    @GetMapping
    public Object getSuccess() {
        return ResponseEntity.status(HttpStatus.OK).body(new Success());
    }

}
