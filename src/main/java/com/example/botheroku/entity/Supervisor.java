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
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName = "fake firstname";

    private String lastName = "fake lastname";

    private String username = "fake username";

    private String chatId;

    private String groupChatId;

    public Supervisor(String firstName, String lastName, String username, String chatId, String groupChatId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.chatId = chatId;
        this.groupChatId = groupChatId;
    }

}
