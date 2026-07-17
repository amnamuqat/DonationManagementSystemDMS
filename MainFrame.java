package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {
    private JLabel label = new JLabel("Donation Management System");
    private JButton adminLoginBtn = new JButton("Admin Login");
    private JButton donorLoginBtn = new JButton("Donor Login");
    private Container c;

    public MainFrame() {
        AddAssistant.loadFromFile();
        AddDonation.loadFromFile();

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setBounds(55, 30, 340, 35);
        
        adminLoginBtn.setBounds(145, 110, 150, 40); 
        adminLoginBtn.addActionListener(this);
        
        donorLoginBtn.setBounds(145, 175, 150, 40); 
        donorLoginBtn.addActionListener(this);

        c.add(label); 
        c.add(adminLoginBtn); 
        c.add(donorLoginBtn);
        
        setTitle("Donation Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 330);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginBtn) {
            new AdminLogin(); 
            dispose();
        }
        if (e.getSource() == donorLoginBtn) {
            new AssistantLogin(); 
            dispose();
        }
    }

    public static void main(String[] args) { 
        new MainFrame(); 
    }
}