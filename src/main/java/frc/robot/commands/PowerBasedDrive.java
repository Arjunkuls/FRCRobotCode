// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TheDDrive;

public class PowerBasedDrive extends CommandBase {
  /** Creates a new PowerBasedDrive. */
  TheDDrive TheDDrive;
  double lPow;
  double rPow;
  public PowerBasedDrive(TheDDrive TheDDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.TheDDrive = TheDDrive;

    addRequirements(TheDDrive);
  }


// Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    TheDDrive.drive((-RobotContainer.MeBoss.getRawAxis(1))*0.5, (RobotContainer.MeBoss.getRawAxis(4))*0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
