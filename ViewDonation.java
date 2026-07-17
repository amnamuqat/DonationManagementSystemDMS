package finalproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewDonation extends JFrame {
    private JTable table;
    private JButton backBtn;

    public ViewDonation() {
        setTitle("Donation Management System - View Donations");
        setLayout(new BorderLayout());

        
        String[] columns = {"ID", "Donation Name", "Type", "Amount", "Quantity", "Total", "Target Group", "Description", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        
        for (int i = 0; i < AddDonation.data.size(); i++) {
            Donation s = AddDonation.data.get(i);
            model.addRow(new Object[]{
                i + 1, 
                s.getDonationName(), 
                s.getDonationType(), 
                s.getAmount(), 
                s.getQuantity(), 
                s.getTotal(), 
                s.getTargetGroup(), 
                s.getDescription(), 
                s.getStatus()
            });
        }

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        add(new JScrollPane(table), BorderLayout.CENTER);

        backBtn = new JButton("Back");
        
        backBtn.addActionListener(e -> { 
            new AssistantSection(); 
            dispose(); 
        });
        add(backBtn, BorderLayout.SOUTH);

        setSize(950, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) { 
        new ViewDonation(); 
    }
}