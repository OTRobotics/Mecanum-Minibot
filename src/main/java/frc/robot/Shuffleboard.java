package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shuffleboard {
    PowerDistribution PDP; 
    
    public void command(Joystick m_stick) {
        PDP = new PowerDistribution(0, ModuleType.kCTRE);
        double voltage = PDP.getVoltage();
        double power = PDP.getTotalPower();
        double temperature = PDP.getTemperature();
        double energy = PDP.getTotalEnergy();
        double current = PDP.getTotalCurrent();

        SmartDashboard.putNumber("PDP Voltage", voltage);
        SmartDashboard.putNumber("PDP Power", power);
        SmartDashboard.putNumber("PDP Temperature", temperature);
        SmartDashboard.putNumber("PDP Energy", energy);
        SmartDashboard.putNumber("PDP Current", current);

        SmartDashboard.putNumber("Joystick X Val", m_stick.getX() * 100);
        SmartDashboard.putNumber("Joystick Y Val", m_stick.getY() * 100);
        SmartDashboard.putNumber("Joystick Z Val", m_stick.getZ() * 100);

        

    
    }
}
