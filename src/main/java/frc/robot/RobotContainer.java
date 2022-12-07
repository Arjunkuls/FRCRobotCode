// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FeederCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PowerBasedDrive;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TheDDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final TheDDrive drive = new TheDDrive();
  private final Intake intake = new Intake();
  private final Feeder feeder = new Feeder();


  

  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final PowerBasedDrive pwbDrive = new PowerBasedDrive(drive);
  private final IntakeCommand intakeCommand = new IntakeCommand(intake, false);
  private final FeederCommand feederCommand = new FeederCommand(feeder);

  public static Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */

  AHRS navx = new AHRS(SPI.Port.kMXP);
  TheDDrive TheDDrive;
  public static Joystick MeBoss = new Joystick(0);
  public static JoystickButton intakeButton = new JoystickButton(MeBoss, 4);

   

  public RobotContainer() {
    // Call actual shit
    drive.setDefaultCommand(pwbDrive);
    intake.setDefaultCommand(intakeCommand);
    feeder.setDefaultCommand(feederCommand);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
   intakeButton.toggleWhenPressed(new IntakeCommand(intake, true));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
