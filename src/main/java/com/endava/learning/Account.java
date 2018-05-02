package com.endava.learning;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ACCOUNT")
public final class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATED")
    private String createdOn;

    @OneToOne
    @JoinColumn
    private Client client;

    public Account() { //for JPA
    }

    public Account(String login, String password, String createdOn, Client client) {
        this.login = login;
        this.password = password;
        this.createdOn = createdOn;
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
