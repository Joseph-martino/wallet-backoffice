package com.prestigeWallet.backoffice.entities;

import javax.persistence.*;

@Entity
public class Coulour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coulour_id")
    private Integer id;

    private String name;

    public Coulour(){

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
