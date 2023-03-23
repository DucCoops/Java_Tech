package com.nguyenduc.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="usertable")
public class Usertable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String username;
    private String email;
    private String pwd;

    public Usertable() {
    }

    public Usertable(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public Usertable(String username, String email, String pwd) {
        this.username = username;
        this.email = email;
        this.pwd = pwd;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
