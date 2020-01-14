/*--------------------------------------------------------------------
  Author    : Jiri Suryn
  Date      : 12/2019
  Version   : 0.0 
  Robot gyroskope controler
  --------------------------------------------------------------------*/
#include <SparkFunMPU9250-DMP.h>
#include <SerialCommand.h>
#include <PID_v1.h>

MPU9250_DMP imu;
SerialCommand SCmd;
double Setpoint, Input, Output;
int motor=0;
PID myPID(&Input, &Output, &Setpoint,2,5,1, DIRECT);

void setup() 
{

  Serial1.begin(115200);
  Serial2.begin(115200);
  
  SCmd.addCommand("m", sMotor)
  myPID.SetMode(AUTOMATIC);
  
  if (imu.begin() != INV_SUCCESS)
  {
    while (1)
    {
      Serial1.println("ERROR MPU-9250");
      delay(5000);
    }
  }

  imu.setSensors(INV_XYZ_GYRO | INV_XYZ_ACCEL | INV_XYZ_COMPASS);

  imu.setGyroFSR(2000); // Set gyro to 2000 dps
  
  imu.setAccelFSR(2); // Set accel to +/-2g
  
  imu.setLPF(5); // Set LPF corner frequency to 5Hz

  imu.setSampleRate(10); // Set sample rate to 10Hz

  imu.setCompassSampleRate(10); // Set mag rate to 10Hz
}


void loop() 
{
  
  if ( imu.dataReady() )
  {
    
    imu.update(UPDATE_ACCEL | UPDATE_GYRO | UPDATE_COMPASS);
    control();
  }
}

void Control()
{
float gyroX = imu.calcGyro(imu.gx);
float gyroY = imu.calcGyro(imu.gy);
float gyroZ = imu.calcGyro(imu.gz);

myPID.Compute();

}

void sMotor(){
   char *arg;
  arg = SCmd.next(); // predani hodnoty limitu alarmu do promene
  if (arg != NULL)
  {
   motor = constrain(atoi(arg), 0, 255);  
  }

  Serial2.println(motor);
}
