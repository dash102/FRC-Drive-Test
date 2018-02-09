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
		rightMotorControllerFollow = new TalonSRX(5);
        rightMotorControllerLead = new TalonSRX(4);
        
		leftMotorControllerFollow = new TalonSRX(2);
        leftMotorControllerLead = new TalonSRX(3);
        
		leftMotorControllerLead.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightMotorControllerLead.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		leftMotorControllerLead.setSensorPhase(true);
	}
	int i;
	public void autonomousInit() {
	    // Resets encoders
	    i = 0;
        leftMotorControllerLead.setSelectedSensorPosition(0, 0, 0);
        rightMotorControllerLead.setSelectedSensorPosition(0, 0, 0);
        

        /* set the peak, nominal outputs, and deadband */
        leftMotorControllerLead.configNominalOutputForward(0, 0);
        leftMotorControllerLead.configNominalOutputReverse(0, 0);
        leftMotorControllerLead.configPeakOutputForward(1, 0);
        leftMotorControllerLead.configPeakOutputReverse(-1, 0);
       
        /* set closed loop gains in slot0 */
        leftMotorControllerLead.config_kF(0, 0.1097, 0);
        leftMotorControllerLead.config_kP(0, 0, 0);
        leftMotorControllerLead.config_kI(0, 0, 0);
        leftMotorControllerLead.config_kD(0, 0, 0);
        leftMotorControllerLead.config_IntegralZone(0, 100, 0);
        
        /* set the peak, nominal outputs, and deadband */
        rightMotorControllerLead.configNominalOutputForward(0, 0);
        rightMotorControllerLead.configNominalOutputReverse(0, 0);
        rightMotorControllerLead.configPeakOutputForward(1, 0);
        rightMotorControllerLead.configPeakOutputReverse(-1, 0);
        rightMotorControllerLead.config_IntegralZone(0, 100, 0);
       
        /* set closed loop gains in slot0 */
        rightMotorControllerLead.config_kF(0, 0.1097, 0);
        rightMotorControllerLead.config_kP(0, 0, 0);
        rightMotorControllerLead.config_kI(0, 0, 0);
        rightMotorControllerLead.config_kD(0, 0, 0);
	}

	public void autonomousPeriodic() {
		//System.out.println("Left Encoder: " + leftMotorControllerLead.getSelectedSensorPosition(0));
		//System.out.println("Right Encoder: " + rightMotorControllerLead.getSelectedSensorPosition(0));
		

		//System.out.println("Left Motor Output: " + leftMotorController.getMotorOutputPercent());
		//System.out.println("Right Motor Output: :" + rightMotorController.getMotorOutputPercent());
		
		double targetVelocity = -0.1 * 2048 * 500.0 / 600;
	    //double targetVelocity = 0.1;
		//leftMotorController.set(ControlMode.Velocity, targetVelocity);
		//rightMotorController.set(ControlMode.Velocity, targetVelocity);
		
		leftMotorControllerLead.selectProfileSlot(0, 0);
		rightMotorControllerLead.selectProfileSlot(0, 0);
		
		leftMotorControllerFollow.follow(leftMotorControllerLead);
        rightMotorControllerFollow.follow(rightMotorControllerLead);
        
        // leftMotorControllerFollow.set(ControlMode.Follower, 3);
        // rightMotorControllerFollow.set(ControlMode.Follower, 4);
        
        // Drive straight forward slowly
        leftMotorControllerLead.set(ControlMode.Velocity, targetVelocity * -1);
		rightMotorControllerLead.set(ControlMode.Velocity, targetVelocity);

		if(i % 10 == 0) {
		    System.out.println("Left Error: " + leftMotorControllerLead.getClosedLoopError(0));
            System.out.println("Right Error: " + rightMotorControllerLead.getClosedLoopError(0));
	        System.out.println("Target Velocity: " + targetVelocity);
            System.out.println("Left Encoder Velocity: " + leftMotorControllerLead.getSelectedSensorVelocity(0));
            System.out.println("Right Encoder Velocity: " + rightMotorControllerLead.getSelectedSensorVelocity(0));
            //System.out.println(_sb.toString());
        }
		
        i++;
        /*
        
        double leftYstick = .5;
        double motorOutput = leftMotorControllerLead.getMotorOutputPercent();

        _sb.append("\tout:");
        _sb.append(motorOutput);
        _sb.append("\tspd:");
        _sb.append(leftMotorControllerLead.getSelectedSensorVelocity(0));
        
        double targetVelocity_UnitsPer100ms = leftYstick * 2048 * 500.0 / 600;

        leftMotorControllerLead.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
        rightMotorControllerLead.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

        _sb.append("\terr:");
        _sb.append(leftMotorControllerLead.getClosedLoopError(0));
        _sb.append("\ttrg:");
        _sb.append(targetVelocity_UnitsPer100ms);
        
        if(i % 10 == 0) {
            //System.out.println("Left Encoder Velocity: " + leftMotorControllerLead.getSelectedSensorVelocity(0));
            //System.out.println("Right Encoder Velocity: " + rightMotorControllerLead.getSelectedSensorVelocity(0));
            System.out.println(_sb.toString());
        }
        _sb.setLength(0);*/
	}

	public void teleopPeriodic() {
	}

	public void testPeriodic() {
	}


}

