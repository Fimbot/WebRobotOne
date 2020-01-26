package com.example.webfimbot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    private Float batery_voltage;
    private Float batery_current;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getBatery_voltage() {
        return batery_voltage;
    }

    public void setBatery_voltage(Float batery_voltage) {
        this.batery_voltage = batery_voltage;
    }

    public Float getBatery_current() {
        return batery_current;
    }

    public void setBatery_current(Float batery_current) {
        this.batery_current = batery_current;
    }
}
