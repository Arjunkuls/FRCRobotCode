// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class TheDDrive extends SubsystemBase {
  /** Creates a new TheDDrive. */

  CANSparkMax LeftF, LeftB;
  CANSparkMax RightF, RightB;
  MotorControllerGroup leftGroup;
  MotorControllerGroup rightGroup;
  DifferentialDrive driveTrain;


  public TheDDrive() {

    LeftF  = new CANSparkMax(Constants.FrontLeft, MotorType.kBrushless);
    RightF = new CANSparkMax(Constants.FrontRight, MotorType.kBrushless);
    LeftB  = new CANSparkMax(Constants.BackLeft, MotorType.kBrushless);
    RightB = new CANSparkMax(Constants.BackRight, MotorType.kBrushless);
    leftGroup = new MotorControllerGroup(LeftF, LeftB);
    rightGroup = new MotorControllerGroup(RightF, RightB);
    rightGroup.setInverted(true);
    driveTrain = new DifferentialDrive(leftGroup, rightGroup);


    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive (double lPow, double rPow){
 
    driveTrain.arcadeDrive(lPow, rPow);
  }


}
