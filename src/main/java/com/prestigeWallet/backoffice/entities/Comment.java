package com.prestigeWallet.backoffice.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer id;

    @Column(name="created_at")
    private Date createdAt;
    private String content;

}
