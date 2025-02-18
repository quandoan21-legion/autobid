package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "answers")
public class answers {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "user_id", insertable = false, updatable = false)
    private int user_id;
    @Column(name = "comment_id", insertable = false, updatable = false)
    private int comment_id;
    @Column(name = "answer_text")
    private String answer_text;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private users f_user_id;

    @OneToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private comments f_comment_id;
}
