package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "answers")
public class answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "answer_text")
    private String answer_text;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @PrePersist
    protected void onCreate() {
        created_at = new Date();
    }

    @OneToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private comments f_comment_id;
}