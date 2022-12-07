// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Feeder extends SubsystemBase {
  public DigitalInput beamBreaker1 = new DigitalInput(1);
  public DigitalInput beamBreaker2 = new DigitalInput(2);
  public DigitalInput beamBreaker3 = new DigitalInput(0);

  static ColorSensorV3 colorSensor = new ColorSensorV3(Port.kMXP);
  static ColorMatch colorMatch = new ColorMatch();
  static Color kBlueTarget = new Color(0,0,255);
  static Color kRedTarget = new Color(255,0,0);
  static Color kGreenTarget = new Color(0,255,0);

  public static boolean isBlue;

  public static TalonFX frontFeeder = new TalonFX(8);
  public static TalonFX backFeeder = new TalonFX(5);
  
  /** Creates a new Feeder. */
  public Feeder() {
    colorMatch.addColorMatch(kBlueTarget);
    colorMatch.addColorMatch(kRedTarget);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public boolean detectColorIsBlue(){
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatch.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      isBlue = true;
    } 
    else if (match.color == kRedTarget)  {
      isBlue = false;
    } 
    return isBlue;
  }

  public boolean tripBreaker(DigitalInput breaker){
    return breaker.get();
  }

  public void onBreakerTrip(){
    if (tripBreaker(beamBreaker1)){
      
      if (detectColorIsBlue()){
          backFeeder.set(TalonFXControlMode.PercentOutput, 0.3);
      }
      else{
        backFeeder.set(TalonFXControlMode.PercentOutput, -0.3);
      }
      frontFeeder.set(TalonFXControlMode.PercentOutput, 0.3);
    }
    if ((tripBreaker(beamBreaker2) && tripBreaker(beamBreaker3)) || (!tripBreaker(beamBreaker2) && tripBreaker(beamBreaker3)) || !tripBreaker(beamBreaker1) && !tripBreaker(beamBreaker2))
    {
      frontFeeder.set(TalonFXControlMode.PercentOutput, 0);
      backFeeder.set(TalonFXControlMode.PercentOutput, 0);
      
    }

  }
}
