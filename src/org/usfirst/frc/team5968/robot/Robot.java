package org.usfirst.frc.team5968.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotBase;

public class Robot extends IterativeRobot {
	
	private TalonSRX leftMotorControllerLead;
	private TalonSRX rightMotorControllerFollow;
	

    private TalonSRX leftMotorControllerFollow;
    private TalonSRX rightMotorControllerLead;
	
	
	
	public void robotPeriodic(){
	}


    /**
     * Called the first time the robot is disabled
     */
    public void disabledInit(){
    	
    }
    
    /**
     * Called periodically while disabled
     */
    public void disabledPeriodic(){
    }
    
    public void teleopInit() {
    	
    }
    
	public void robotInit() {
		rightMotorControllerFollow = new TalonSRX(4);
        rightMotorControllerLead = new TalonSRX(1);
        
		leftMotorControllerFollow = new TalonSRX(3);
        leftMotorControllerLead = new TalonSRX(2);
        
		leftMotorControllerLead.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightMotorControllerLead.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		
	}

	public void autonomousInit() {
		// Resets encoders
		leftMotorControllerLead.setSelectedSensorPosition(0, 0, 0);
		rightMotorControllerLead.setSelectedSensorPosition(0, 0, 0);
	}

	public void autonomousPeriodic() {
		System.out.println("Left Encoder: " + leftMotorControllerLead.getSelectedSensorPosition(0));
		System.out.println("Right Encoder: " + rightMotorControllerLead.getSelectedSensorPosition(0));
		

		//System.out.println("Left Encoder Velocity: " + leftMotorController.getSelectedSensorVelocity(0));
		//System.out.println("Right Encoder Velocity: " + rightMotorController.getSelectedSensorVelocity(0));

		//System.out.println("Left Motor Output: " + leftMotorController.getMotorOutputPercent());
		//System.out.println("Right Motor Output: :" + rightMotorController.getMotorOutputPercent());
		
		double targetVelocity = 0.2;
		//leftMotorController.set(ControlMode.Velocity, targetVelocity);
		//rightMotorController.set(ControlMode.Velocity, targetVelocity);
		
		leftMotorControllerLead.selectProfileSlot(0, 0);
		rightMotorControllerLead.selectProfileSlot(0, 0);
		
		// Drive straight forward slowly
		leftMotorControllerFollow.set(ControlMode.Follower, 2);
        rightMotorControllerFollow.set(ControlMode.Follower, 4);
        
		leftMotorControllerLead.set(ControlMode.PercentOutput, targetVelocity);
		rightMotorControllerLead.set(ControlMode.PercentOutput, targetVelocity * -1);

	}

	public void teleopPeriodic() {
	}

	public void testPeriodic() {
	}


}

