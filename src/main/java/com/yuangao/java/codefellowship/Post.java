package com.yuangao.java.codefellowship;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    ApplicationUser applicationUser;
    String body;
    Timestamp createdAt;

    public Post(){}
    //constructor
    public Post(ApplicationUser applicationUser, String body,Timestamp createdAt){

        this.applicationUser = applicationUser;
        this.body = body;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }






}
