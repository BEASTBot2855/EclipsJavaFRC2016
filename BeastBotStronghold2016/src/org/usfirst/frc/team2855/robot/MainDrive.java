package org.usfirst.frc.team2855.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;

public class MainDrive {
	private CANTalon talon1, talon2, talon3, talon4;
	
	
	public MainDrive(CANTalon one, CANTalon two, CANTalon three, CANTalon four){
		talon1=one;
		talon2=two;
		talon3=three;
		talon4=four;
		
		
		talon1.setInverted(true);
		talon3.setInverted(false);
		
		talon2.changeControlMode(TalonControlMode.Follower);
		talon4.changeControlMode(TalonControlMode.Follower);
	}
	
	public void driveRobot (double speedL, double speedR) {
		talon1.set(speedR);
		talon2.set(talon1.getDeviceID());
		talon3.set(speedL);
		talon4.set(talon3.getDeviceID());
	}
    public void autoDriveStraight() {
    	driveRobot(-1, -1);
		
		Timer.delay(1.0);
		
    	driveRobot(0, 0);
    }
    public void autoDriveBackwards() {
    	driveRobot(1, 1);
		
		Timer.delay(1.0);
		
    	driveRobot(0, 0);
    }
    public void autoSpyDrive() {
    	
    }
    public void autoChevalDeFrise() {
    	
    }
	
}


// rightJ leftJ