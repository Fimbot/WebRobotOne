package cz.prorobot.webapp.entity;

public class RizeniForm {
    private int motorL;
    private int motorR;

    public RizeniForm() {
    }

    public int getMotorL() {
        return motorL;
    }

    public void setMotorL(int motorL) {
        this.motorL = motorL;
    }

    public int getMotorR() {
        return motorR;
    }

    public void setMotorR(int motorR) {
        this.motorR = motorR;
    }

    @Override
    public String toString() {
        return "RizeniForm{" +
                "motorL=" + motorL +
                ", motorR=" + motorR +
                '}';
    }
}
