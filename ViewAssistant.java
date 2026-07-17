package finalproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewAssistant extends JFrame {
    private JTable table;
    private JButton backBtn;

    public ViewAssistant() {
        setTitle("Donation Management System - View Donors");
        setLayout(new BorderLayout());

        // تصميم الأعمدة للجدول
        String[] columns = {"ID", "Donor Name", "Password", "Email", "Contact No"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

      
        for (int i = 0; i < AddAssistant.data.size(); i++) {
            Assistant d = AddAssistant.data.get(i);
            model.addRow(new Object[]{
                i + 1, 
                d.getName(), 
                d.getPassword(), 
                d.getEmail(), 
                d.getContactNo()
            });
        }

        table = new JTable(model);
        
        add(new JScrollPane(table), BorderLayout.CENTER);

        backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            new AdminSection(); 
            dispose();
        });
        add(backBtn, BorderLayout.SOUTH);

        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewAssistant();
    }
}