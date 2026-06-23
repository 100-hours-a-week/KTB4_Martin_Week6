package com.homework4.workapi.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "attaches")
public class Attach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String attachUrl;
    private LocalDateTime attachTime;
    protected Attach(){}

    public Attach(Post post, String attachUrl) {
        this.post = post;
        this.attachUrl = attachUrl;
        this.attachTime = LocalDateTime.now();
    }
}
