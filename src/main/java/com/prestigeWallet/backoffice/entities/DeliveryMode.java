package com.prestigeWallet.backoffice.entities;

import javax.persistence.*;

@Entity
@Table(name="delivery_mode")
public class DeliveryMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_mode_id")
    private Integer id;

    private String name;

    public DeliveryMode(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
