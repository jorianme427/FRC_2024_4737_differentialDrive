// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import frc.robot.Constants.LauncherConstants;
//import frc.robot.Constants.DrivetrainConstants;
//import frc.robot.Constants.IntakeConstants;
//import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
//import frc.robot.commands.ElevatorUp;
//import frc.robot.commands.LaunchNote;
//import frc.robot.commands.PrepareLaunch;
//import frc.robot.commands.IntakeNote;
//import frc.robot.commands.IntakeLauncher;
import frc.robot.commands.FeedShoot;
import frc.robot.subsystems.CANClimber;
//import frc.robot.subsystems.PWMDrivetrain;
//import frc.robot.subsystems.PWMLauncher;
//import frc.robot.subsystems.CANClimber;
//Climber is closed for now due to problems//
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANElevator;
import frc.robot.subsystems.CANLauncher;
import frc.robot.subsystems.CANIntake;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here.
   private final CANDrivetrain m_drivetrain = new CANDrivetrain();
   private final CANLauncher m_launcher = new CANLauncher();
   private final CANIntake m_Intake = new CANIntake();
   private final CANClimber m_Climber = new CANClimber(); 
   private final CANElevator m_Elevator = new CANElevator();

  /*The gamepad provided in the KOP shows up like an XBox controller if the mode switch is set to X mode using the
   * switch on the top.*/
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }
// Eesh Garg was here
// Rodent Turtle Tina waz hear
  /**
   * Use this method to define your trigger->command mappings. Triggers can be accessed via the
   * named factory methods in the Command* classes in edu.wpi.first.wpilibj2.command.button (shown
   * below) or via the Trigger constructor for arbitary conditions
   */
  private void configureBindings() {
    // Set the default command for the drivetrain to drive using the joysticks
    /*m_drivetrain.setDefaultCommand(
        new RunCommand(
            () ->
                m_drivetrain.arcadeDrive(
                    -m_driverController.getLeftY(), -m_driverController.getRightX()),
            m_drivetrain));*/

    m_drivetrain.setDefaultCommand(
        new RunCommand(
            () ->
                m_drivetrain.arcadeDrive(
                    -(m_driverController.getRightTriggerAxis() - m_driverController.getLeftTriggerAxis()), -m_driverController.getRightX()),
            m_drivetrain));
   
    /*Create an inline sequence to run when the operator presses and holds the A (green) button. Run the PrepareLaunch
     * command for 1 seconds and then run the LaunchNote command */
   // m_operatorController
     //   .y()
       // .whileTrue(
         //   new LaunchNote(m_launcher)
           // .withTimeout(LauncherConstants.kLauncherDelay)
            //.andThen(new FeedShoot(m_Intake))
            //.withTimeout(LauncherConstants.kLauncherDelay)
            //.handleInterrupt(() -> m_Intake.stop()));
    m_operatorController
        .b()
        .whileTrue(
          new FeedShoot(m_Intake)
                .handleInterrupt(() -> m_Intake.stop()));
    

        
                
   // m_operatorController
    //    .rightTrigger()
      //  .whileTrue(
        //    new IntakeLauncher(m_launcher)
            
          //      .handleInterrupt(() -> m_launcher.stop()));
                
                

        //new RunCommand(
           // () ->
           //     m_Elevator.setElevator(
             //       -m_operatorController.getLeftY()),
            //m_Elevator);
        
    // Set up a binding to run the launcher command while the operator is pressing and holding the
    // left trigger
    m_driverController.y().whileTrue(m_launcher.getAmpShooterCommand());

    m_operatorController.y().whileTrue(m_launcher.getLauncherCommand());
    m_operatorController.a().whileTrue(m_Climber.getClimberDownCommand());
    m_operatorController.x().whileTrue(m_Climber.getClimberUpCommand());
    //m_operatorController.y().whileTrue(m_launcher.getAmpShooterCommand());
    // Set up a binding to run the intake command while the operator is pressing and holding the
    // right trigger
    m_operatorController.rightTrigger().whileTrue(m_Intake.getIntakeNoteCommand());
    m_operatorController.rightTrigger().whileTrue(m_launcher.getShooterIntakeCommand());
    // Set up a binding to run the climber UP command while the operator is pressing and holding the
    // a button
    m_operatorController.leftBumper().whileTrue(m_Elevator.getElevatorCommand()); 
    m_operatorController.leftTrigger().whileTrue(m_Elevator.getElevatorDownCommand());
    // changed from getElevatorIntakecommand to getElevatorcommand will chin case of any errors 

    // Set up a binding to run the climber DOWN command while the operator is pressing and holding the
    // b button
    //m_operatorController.b().whileTrue(m_Intake.getFeedShootCommand());
    // Set up a binding to run the intake command while the operator is pressing and holding the
    // left Bumper
  
  //right bumper    trty deleting this /* 
    m_operatorController.rightBumper().whileTrue(m_Intake.getIntakeNoteReverseCommand());
    m_operatorController.rightBumper().whileTrue(m_launcher.getReverseShooterIntakeCommand());
     
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.elevatorAuto(m_Elevator);
  }
}
