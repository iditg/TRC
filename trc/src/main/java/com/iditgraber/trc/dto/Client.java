package com.iditgraber.trc.dto;

import javax.persistence.Entity;

@Entity
public class Client {

    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client( int id) {
        this.id = id;
    }

}