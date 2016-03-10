package org.usfirst.frc.team2855.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

// this is the arm thing that picks up the ball
public class Hopper {
	private Spark spark5;
	private Talon talon6;
	private AnalogPotentiometer armPot;
	private double autoArm; 
	private boolean autoArmBupTF;
	private boolean autoArmDownTF;
	private boolean autoArmUpTF;
	private double speedSpark5;
	private double counter;
	
	public Hopper (Spark five, Talon six, AnalogPotentiometer seven) {
		spark5 = five;
		talon6 = six;
		armPot = seven;
	}
	
	public void autoArmUp () {
		autoArmUpTF = true;
		if (counter > 60) {
			 counter = 0;
			 autoArmUpTF = false;
		} else {
			counter++;
		}
	}
	public void autoArmDown () {
		autoArmDownTF = true;
		if (counter > 60) {
			 counter = 0;
			 autoArmDownTF = false;
		} else {
			counter++;
		}
	}
	public void autoArmBup() {
		autoArmBupTF = true;
		if (counter > 60) {
			 counter = 0;
			 autoArmBupTF = false;
		} else {
			counter++;
		}
	}
	
	public void autoBup () {
		double anglePot = armPot.get();
		double armPot180;
		armPot180 = 180 - anglePot;
		if(autoArmBupTF) {
			talon6.set(1);
		} else {
			if (armPot180 > 0) {
				spark5.set(speedSpark5);
			} else if(armPot180 == 0) {
				spark5.set(0);
				counter = 0;
			}
		}
	}
	
	public void moveArm (boolean button1, boolean button2, boolean button3, boolean button4) {
		double anglePot = armPot.get();
		if ((anglePot < 30) || (anglePot > 100)) {	// pot reads 130 when fully extended, 0 when retracted
			speedSpark5 = .4;
		} else {
			speedSpark5 = .6;
		}
		
		if(autoArmDownTF) {
			spark5.set(speedSpark5);
		} else {
			if(autoArmUpTF){
				spark5.set(-1*(speedSpark5));
			} else {
				 if(button1 && button2) {
					spark5.set(0);
				} else if(button1 && (!button2)) {
					spark5.set(speedSpark5);
				} else if(button2 && (!button1)){
					spark5.set(-1*(speedSpark5));
				} else if(button3 && (!button1) && (!button2)){
					autoArmDown();
				} else if(button4 && (!button1) && (!button2) && (!button4)){
					autoArmUp();
				} else {
					spark5.set(0);
				}
			}
		}
	}
	public void moveWheel (boolean button3, boolean button4) {
		if(button3 && button4) {
			talon6.set(0);
		} else if(button3 && (!button4)) {
			talon6.set(1);
		} else if(button4 && (!button3)){
			talon6.set(-1);
		} else {
			talon6.set(0);
		}
	}
}
