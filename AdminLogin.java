package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame implements ActionListener {
    private JLabel title = new JLabel("Admin Login");
    private JLabel userLabel = new JLabel("Username:");
    private JLabel passLabel = new JLabel("Password:");
    private JTextField userField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15); // استخدام حقل آمن لكلمات المرور
    private JButton loginBtn = new JButton("Login");
    private JButton backBtn = new JButton("Back");
    private Container c;

    public AdminLogin() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(160, 25, 180, 30);
        
        userLabel.setBounds(50, 85, 100, 25);
        userField.setBounds(160, 85, 180, 28);
        
        passLabel.setBounds(50, 130, 100, 25);
        passField.setBounds(160, 130, 180, 28);
        
        loginBtn.setBounds(135, 195, 100, 35); 
        loginBtn.addActionListener(this);
        
        backBtn.setBounds(250, 195, 90, 35); 
        backBtn.addActionListener(this);

        c.add(title); 
        c.add(userLabel); 
        c.add(userField); 
        c.add(passLabel); 
        c.add(passField); 
        c.add(loginBtn); 
        c.add(backBtn);
        
        setTitle("Donation Management System - Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 310);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String username = userField.getText().trim();
            // جلب كلمة المرور وتحويلها من مصفوفة رموز إلى نص String
            String password = new String(passField.getPassword()).trim(); 
            
            if (username.equals("admin") && password.equals("admin123")) {
                new AdminSection(); // استدعاء الاسم النظيف للوحة تحكم الآدمن
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password mismatch");
            }
        }
        if (e.getSource() == backBtn) {
            new MainFrame(); // استدعاء واجهة البوابة الرئيسية النظيفة
            dispose();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}