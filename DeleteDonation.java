package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteDonation extends JFrame implements ActionListener {
    private JTextField t1;
    private JLabel title, l2;
    private JButton b1, b2;
    private Container c;

    public DeleteDonation() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        title = new JLabel("Delete Donation");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(95, 20, 180, 30);

        l2 = new JLabel("Donation ID:");
        l2.setBounds(35, 80, 100, 25);
        t1 = new JTextField(12);
        t1.setBounds(140, 80, 150, 28);

        b1 = new JButton("Delete");
        b1.setBounds(75, 140, 100, 35);
        b1.addActionListener(this);
        b2 = new JButton("Back");
        b2.setBounds(195, 140, 90, 35);
        b2.addActionListener(this);

        c.add(title); c.add(l2); c.add(t1); c.add(b1); c.add(b2);
        setTitle("Donation Management System - Delete Donation");
        setSize(360, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                int i = Integer.parseInt(t1.getText().trim()) - 1;
                
                
                if (i >= 0 && i < AddDonation.data.size()) {
                   
                    AddDonation.data.remove(i);
                    
                    
                    AddDonation.rewriteDonationFile();
                    
                    JOptionPane.showMessageDialog(null, "Donation deleted successfully!");
                    t1.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Donation ID not found!");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric Donation ID!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        
        if (e.getSource() == b2) { 
            new AssistantSection(); 
            dispose(); 
        }
    }

    public static void main(String[] args) { 
        new DeleteDonation(); 
    }
}