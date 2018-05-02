package com.endava.learning;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Embeddable
public final class Transaction {

    @Column(name = "NUMBER")
    private Long number;

    private Transaction() {} //for JPA

    public Transaction(Long number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", number=" + number +
                '}';
    }
}
