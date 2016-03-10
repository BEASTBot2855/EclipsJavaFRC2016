package org.usfirst.frc.team2855.robot;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends SampleRobot {
    final String autoStraight = "Drive Straight Ahead";
    final String autoSpyBot = "Spy Bot";
    final String autoBackwards = "Drive Straight Backwards";
    final String autoPortcullis = "Defence Portcullis";
    final String autoChevalDeFrise = "Defence autoChevalDeFrise";
    SendableChooser chooser;

    
    private CANTalon talon1, talon2, talon3, talon4;
    private MainDrive drive;
    private Hopper hopper;
    private Joystick leftJ, rightJ;
	private Spark spark5;
	private Talon talon6;
	private AnalogPotentiometer armPot;
	private CameraServer server;
    
    public Robot() {
    	talon1 = new CANTalon(1);
    	talon2 = new CANTalon(2);
    	talon3 = new CANTalon(3);
    	talon4 = new CANTalon(4);
    	spark5 = new Spark(9);
    	talon6 = new Talon(7);
    	

    	armPot = new AnalogPotentiometer(0, 180, 0);
    	
    	drive=new MainDrive(talon1, talon2, talon3, talon4);
    	hopper = new Hopper(spark5, talon6, armPot);
    	
    	leftJ=new Joystick(0);
    	rightJ=new Joystick(1);
    	
    }
    
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Drive Straight Ahead", autoStraight);
        chooser.addDefault("Drive Straight Backwards", autoBackwards);
        chooser.addObject("Spy Bot", autoSpyBot);
        chooser.addObject("Defence Portcullis", autoPortcullis);
        chooser.addObject("Defence autoChevalDeFrise", autoChevalDeFrise);
        SmartDashboard.putData("Auto modes", chooser);
        SmartDashboard.putNumber("Pot: ", armPot.get());
        //server=CameraServer.getInstance();
    	//server.setQuality(50);
    	//server.startAutomaticCapture("cam0");
    	//server = CameraServer.getInstance();
    }

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the if-else structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomous() {
     
     
     String activeButton=(String)chooser.getSelected();
     switch (activeButton) {
     case autoSpyBot: drive.autoSpyDrive();
    	    break;
     default: drive.autoDriveStraight();
    	    break;
     }
     
    }
    public void autoPortcullis() {
    	hopper.moveArm(true, false, false, false);
    	
    	Timer.delay(2.0);
    	
    	drive.driveRobot(-0.3, -0.3);
		
		Timer.delay(4.0);
		
		drive.driveRobot(0, 0);
		hopper.moveArm(false, true, false, false);
		
		Timer.delay(1.0);
		
		drive.driveRobot(-0.3, -0.3);
		
		Timer.delay(2.0);
		
		drive.driveRobot(0, 0);
		hopper.moveArm(false, false, false, false);
		
    }
    
    /*runs during teleop mode
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	drive.driveRobot(leftJ.getY(), rightJ.getY());
        	hopper.moveArm(rightJ.getRawButton(1), rightJ.getRawButton(2), rightJ.getRawButton(3), rightJ.getRawButton(4));
        	hopper.moveWheel(leftJ.getRawButton(1), leftJ.getRawButton(2));
        	
        	SmartDashboard.putNumber("Pot: ", armPot.get());

        	
        	Timer.delay(0.05);
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    	
    }
}
