// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  private static final int FrontLeft = 0;
  private static final int RearLeft = 1;
  private static final int FrontRight = 2;
  private static final int RearRight = 3;

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick;

  private AddressableLED m_led = new AddressableLED(7);;
  private AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(27);;
  private Shuffleboard test;
 
  private PowerDistribution PDP = new PowerDistribution(0, ModuleType.kCTRE);

  public static SendableChooser<Integer[]> m_Chooser = new SendableChooser<>();

  private Integer[] currentColour;
  private Integer[] red = {255, 0, 0};
  private Integer[] purple = {255, 0, 200};


  @Override
  public void robotInit() {
  

    Talon frontLeft = new Talon(FrontLeft);
    Talon rearLeft = new Talon(RearLeft);
    Talon frontRight = new Talon(FrontRight);
    Talon rearRight = new Talon(RearRight);

    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new Joystick(kJoystickChannel);


  }

  @Override
  public void robotPeriodic() {
    m_Chooser.setDefaultOption("Red", red);
    m_Chooser.addOption("Purple", purple);
    
    SmartDashboard.putData(m_Chooser);
     
    m_led.setLength(m_ledBuffer.getLength());
    
    currentColour = m_Chooser.getSelected();

    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      m_ledBuffer.setRGB(i, currentColour[0], currentColour[1], currentColour[2]);
    }

    m_led.setData(m_ledBuffer);
    m_led.start();

    double voltage = PDP.getVoltage();
    SmartDashboard.putNumber("Voltage", voltage);

    double temperature = PDP.getTemperature();
    SmartDashboard.putNumber("Temperature", Math.round(temperature));

    

  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.driveCartesian(-m_stick.getY() * 0.5, m_stick.getX() *0.5, -m_stick.getZ() *0.5);
    test.command(m_stick);
    
  }
}
