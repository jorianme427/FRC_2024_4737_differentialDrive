// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import static frc.robot.Constants.ClimberConstants.*;


//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;




public class CANClimber extends SubsystemBase {
  
   // DoubleSolenoid corresponds to a double solenoid.
  // In this case, it's connected to channels 1 and 2 of a PH with the default CAN ID.
  
  private final DoubleSolenoid m_rightDoubleSolenoid =
      new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
   // Compressor connected to a PCM with a default CAN ID (0)
  //private final Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  

  /** Creates a new climber. */
  public CANClimber() {
   
 /* 
    m_leftDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
    m_leftDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    m_rightDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
    m_rightDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    */
  
    //m_doubleSolenoid.setSmartCurrentLimit(kClimberCurrentLimit); 
    //m_compressor.setSmartCurrentLimit(kClimberCurrentLimit);
  }

  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */ 
  
  public Command getClimberUpCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          m_rightDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
         
          
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        }); 
  }
public Command getClimberDownCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
         
          m_rightDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
        
         
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        }); 
  }
  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setClimber(double speed) {
    
  }

  // An accessor method to set the speed (technically the output percentage) of the feed wheel
  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    m_rightDoubleSolenoid.set(DoubleSolenoid.Value.kOff);
   
  }
  
} 
