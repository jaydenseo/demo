package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Trader {

    @Id
    private String name;
    private String city;

    public Trader(String n, String c) {
        name = n;
        city = c;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + (name == null ? 0 : name.hashCode());
        hash = hash * 31 + (city == null ? 0 : city.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Trader)) {
            return false;
        }
        Trader o = (Trader) other;
        boolean eq = Objects.equals(name, o.getName());
        eq = eq && Objects.equals(city, o.getCity());
        return eq;
    }
}
