package com.epam.adok.core.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_registration")
    private Timestamp registrationDate;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}
