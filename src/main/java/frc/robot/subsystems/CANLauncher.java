// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.LauncherConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANLauncher extends SubsystemBase {
  CANSparkMax m_leftLaunchWheel;
  CANSparkMax m_rightLaunchWheel;
  RelativeEncoder m_rightEncoder;
  RelativeEncoder m_leftEncoder;

  /** Creates a new Launcher. */
  public CANLauncher() {
    m_leftLaunchWheel = new CANSparkMax(kLeftLauncherID, MotorType.kBrushless);
    m_rightLaunchWheel = new CANSparkMax(kRightLauncherID, MotorType.kBrushless);

    m_leftLaunchWheel.setSmartCurrentLimit(kLeftLauncherCurrentLimit);
    m_rightLaunchWheel.setSmartCurrentLimit(kRightLauncherCurrentLimit);

    m_rightEncoder = m_rightLaunchWheel.getEncoder();
    m_leftEncoder = m_leftLaunchWheel.getEncoder();
    m_rightEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);
  }

  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */
  public Command getLauncherCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setLeftLaunchWheel(kLeftLauncherSpeed);
          setRightLaunchWheel(kRightLauncherSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

   public Command getShooterIntakeCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setLeftLaunchWheel(kReverseLeftLauncherSpeed);
          setRightLaunchWheel(kReverseRightLauncherSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  } 

     public Command getReverseShooterIntakeCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setLeftLaunchWheel(kLeftLauncherSpeed);
          setRightLaunchWheel(kRightLauncherSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  } 
     public Command getAmpShooterCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setLeftLaunchWheel(kAmpLeftLauncherSpeed);
          setRightLaunchWheel(kAmpRightLauncherSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  } 



  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setLeftLaunchWheel(double speed) {
    m_leftLaunchWheel.set(speed);
  }

  // An accessor method to set the speed (technically the output percentage) of the feed wheel
  public void setRightLaunchWheel(double speed) {
    m_rightLaunchWheel.set(-speed);
  }

  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    m_leftLaunchWheel.set(0);
    m_rightLaunchWheel.set(0);
  }
}
