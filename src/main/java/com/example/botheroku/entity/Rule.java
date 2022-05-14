package com.example.botheroku.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String startHour;
    private String endHour;

    private String startMinute;
    private String endMinute;

    private String chatId;

    private String sendHour;
    private String sendMinute;

    public Rule(String startHour, String endHour, String startMinute, String endMinute) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
    }

    public Rule(String startHour, String endHour, String startMinute, String endMinute, String chatId, String sendHour, String sendMinute) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        this.chatId = chatId;
        this.sendHour = sendHour;
        this.sendMinute = sendMinute;
    }

    public Rule(String startHour, String endHour, String startMinute, String endMinute, String chatId) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        this.chatId = chatId;
    }
}
