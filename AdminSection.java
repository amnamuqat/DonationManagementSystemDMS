package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminSection extends JFrame implements ActionListener {
    private JLabel titleLabel = new JLabel("Admin Section");
    private JButton addDonorBtn = new JButton("Add Donor");
    private JButton viewDonorsBtn = new JButton("View Donors");
    private JButton logoutBtn = new JButton("Logout");
    private Container c;

    public AdminSection() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(145, 25, 180, 30);

        addDonorBtn.setBounds(145, 90, 150, 38); 
        addDonorBtn.addActionListener(this);
        
        viewDonorsBtn.setBounds(145, 145, 150, 38); 
        viewDonorsBtn.addActionListener(this);
        
        logoutBtn.setBounds(145, 200, 150, 38); 
        logoutBtn.addActionListener(this);

        c.add(titleLabel); 
        c.add(addDonorBtn); 
        c.add(viewDonorsBtn); 
        c.add(logoutBtn);

        setTitle("Donation Management System - Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 330);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDonorBtn) {
            new AddAssistant(); // استدعاء واجهة إضافة مساعد بالاسم النظيف
            dispose();
        }
        if (e.getSource() == viewDonorsBtn) {
            new ViewAssistant(); // استدعاء واجهة استعراض المساعدين بالاسم النظيف
            dispose();
        }
        if (e.getSource() == logoutBtn) {
            new MainFrame(); // تسجيل الخروج والعودة للبوابة الرئيسية النظيفة
            dispose();
        }
    }

    public static void main(String[] args) {
        new AdminSection();
    }
}