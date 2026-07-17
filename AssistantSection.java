package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AssistantSection extends JFrame implements ActionListener {
    private JLabel label = new JLabel("Donor Section");
    private JButton button_1 = new JButton("Add Donation");
    private JButton button_2 = new JButton("View Donations");
    private JButton button_3 = new JButton("Edit Donation");
    private JButton button_4 = new JButton("Delete Donation");
    private JButton button_5 = new JButton("Logout");
    private Container c;

    public AssistantSection() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setBounds(145, 20, 200, 30);
        button_1.setBounds(50, 80, 150, 35); button_1.addActionListener(this);
        button_2.setBounds(230, 80, 150, 35); button_2.addActionListener(this);
        button_3.setBounds(50, 140, 150, 35); button_3.addActionListener(this);
        button_4.setBounds(230, 140, 150, 35); button_4.addActionListener(this);
        button_5.setBounds(140, 205, 150, 35); button_5.addActionListener(this);

        c.add(label); c.add(button_1); c.add(button_2); c.add(button_3); c.add(button_4); c.add(button_5);
        setTitle("Donation Management System - Donor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 330);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // استدعاء كلاس الإضافة الجديد بدون شرطات
        if (e.getSource() == button_1) { new AddDonation(); dispose(); }
        
        // بقية الواجهات استدعيها بحسب أسمائها الحالية، وعند تعديلها لاحقاً سنقوم بإزالة الشرطات منها هنا أيضاً
        if (e.getSource() == button_2) { new ViewDonation(); dispose(); }
        if (e.getSource() == button_3) { new EditDonation(); dispose(); }
        if (e.getSource() == button_4) { new DeleteDonation(); dispose(); }
        if (e.getSource() == button_5) { new MainFrame(); dispose(); }
    }

    public static void main(String[] args) {
        new AssistantSection();
    }
}