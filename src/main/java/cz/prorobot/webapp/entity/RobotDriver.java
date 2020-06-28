package cz.prorobot.webapp.entity;

public class RobotDriver {

   JSerialComm01 jSerialComm01=new JSerialComm01();

    public RobotDriver() {
        //jSerialComm01.openPort();
    }

    public void openPort(){

    }

    public void send(String message){
        System.out.println(message);
    }

    public String  recive(){
        String massage="";
        return massage;
    }
}
