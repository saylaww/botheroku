package com.example.botheroku.controller;

import com.example.botheroku.payload.ApiResponse;
import com.example.botheroku.payload.ChatDto;
import com.example.botheroku.payload.TimeDto;
import com.example.botheroku.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

//@PreAuthorize(value = "hasRole('ADMIN')")
//@PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','ADMIN')")
@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    BotService botService;

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    @GetMapping("/getAllSupervisor")
    public HttpEntity<?> getSupervisors() {
        ApiResponse apiResponse = botService.getAllSupervisors();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    @GetMapping("/getRule")
    public HttpEntity<?> getRule() {
        ApiResponse apiResponse = botService.getRule();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    @GetMapping("/getAllReport")
    public HttpEntity<?> getAllReport() {
        ApiResponse apiResponse = botService.getAllReport();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    @GetMapping("/byDate")
    public HttpEntity<?> getByDate(@RequestParam String start, @RequestParam String end) throws ParseException {

        ApiResponse apiResponse = botService.byDate(start, end);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    @GetMapping("/byDateSupervisor/{id}")
    public HttpEntity<?> getByDateSupervisor(@RequestParam String start,@RequestParam String end, @PathVariable Long id) throws ParseException {
        ApiResponse apiResponse = botService.byDateSupervisor(id, start, end);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    @PutMapping("/editTime")
    public HttpEntity<?> editTime(@RequestBody TimeDto timeDto) {
        ApiResponse apiResponse = botService.editTime(timeDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('SUPER_ADMIN')")
    @PutMapping("/editChatId")
    public HttpEntity<?> editChatId(@RequestBody ChatDto chatDto) {
        ApiResponse apiResponse = botService.editChatId(chatDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
