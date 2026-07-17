package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AssistantLogin extends JFrame implements ActionListener {
    private JLabel label_1 = new JLabel("Donor Login");
    private JLabel label_2 = new JLabel("Name:");
    private JLabel label_3 = new JLabel("Password:");
    private JTextField input_1 = new JTextField(15);
    private JPasswordField input_2 = new JPasswordField(15); // تم ترقيته لإخفاء كلمة المرور برمجياً
    private JButton button = new JButton("Login");
    private JButton button1 = new JButton("Back");
    private Container c;

    public AssistantLogin() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        label_1.setFont(new Font("Arial", Font.BOLD, 22));
        label_1.setBounds(160, 25, 180, 30);
        label_2.setBounds(50, 85, 100, 25);
        input_1.setBounds(160, 85, 180, 28);
        label_3.setBounds(50, 130, 100, 25);
        input_2.setBounds(160, 130, 180, 28);
        button.setBounds(135, 195, 100, 35); button.addActionListener(this);
        button1.setBounds(250, 195, 90, 35); button1.addActionListener(this);

        c.add(label_1); c.add(input_1); c.add(label_2); c.add(input_2); c.add(label_3); c.add(button); c.add(button1);
        setTitle("Donation Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 310);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String enteredName = input_1.getText().trim();
            String enteredPassword = new String(input_2.getPassword()).trim(); // قراءة الباسورد من الـ JPasswordField

            if (enteredName.isEmpty() || enteredPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            boolean found = false;
            // استخدام اسم الكلاس المعدّل Assistant واستدعاء الداتا من AddAssistant النظيف
            for (Assistant donor : AddAssistant.data) {
                // تطبيق الـ Encapsulation باستخدام ميثودز الـ Getters المستدعاة من كلاس البيانات
                if (enteredName.equals(donor.getName()) && enteredPassword.equals(donor.getPassword())) {
                    found = true;
                    break;
                }
            }

            // خيار الدخول الافتراضي أو وجود الحساب المكتشف
            if (found || (enteredName.equals("donor") && enteredPassword.equals("donor123"))) {
                new AssistantSection(); // استدعاء اسم القسم الجديد والنظيف بدون شرطات
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Donor not found!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if (e.getSource() == button1) {
            new MainFrame(); // استدعاء الماين فريم بالاسم النظيف بدون شرطات
            dispose();
        }
    }

    public static void main(String[] args) {
        new AssistantLogin();
    }
}