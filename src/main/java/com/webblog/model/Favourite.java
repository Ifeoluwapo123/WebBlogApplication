package com.webblog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private Long currentUserId;

    @Column(nullable = false)
    private Long postId;
}
