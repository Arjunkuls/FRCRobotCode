// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
  
  public static Solenoid Solenoid;
  public static TalonFX intake;
  public static TalonFX feeder;

  public static double power = 0.4;

  /** Creates a new Intake. */
  public static int pwr;
  public Intake() {
    Solenoid = new Solenoid(PneumaticsModuleType.CTREPCM,7);
    intake = new TalonFX(Constants.Intake);
    feeder = new TalonFX(Constants.Feeder);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

    // 1 - forward, 2 - reverse, 3 - off

  public static void moveIntake(Boolean raise){
    Solenoid.set(raise);
    if (raise) {
      intake.set(TalonFXControlMode.PercentOutput, power);
      feeder.set(TalonFXControlMode.PercentOutput, power);
    }
    else 
    {
      intake.set(TalonFXControlMode.PercentOutput, 0);
      feeder.set(TalonFXControlMode.PercentOutput, 0);

    }
  }


}
