package com.example.botheroku.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String url;

    @CreationTimestamp
    private Timestamp date;

    @ManyToOne
    private Supervisor supervisor;

    @NotNull
    @Column(nullable = false)
    private String messageId;

    public Report(@NotNull String url, Supervisor supervisor, @NotNull String messageId) {
        this.url = url;
        this.supervisor = supervisor;
        this.messageId = messageId;
    }

    public Report(@NotNull String url, Timestamp date, Supervisor supervisor, @NotNull String messageId) {
        this.url = url;
        this.date = date;
        this.supervisor = supervisor;
        this.messageId = messageId;
    }
}
