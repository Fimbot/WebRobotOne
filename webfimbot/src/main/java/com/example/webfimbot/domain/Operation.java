package com.example.webfimbot.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int phase;
    private int motor1;
    private int motor2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getMotor1() {
        return motor1;
    }

    public void setMotor1(int motor1) {
        this.motor1 = motor1;
    }

    public int getMotor2() {
        return motor2;
    }

    public void setMotor2(int motor2) {
        this.motor2 = motor2;
    }
}
