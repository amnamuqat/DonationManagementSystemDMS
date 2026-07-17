package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditDonation extends JFrame implements ActionListener, KeyListener {
    private JLabel title, l0, l1, l2, l3, l4, l5, l6, l7, l8;
    private JTextField indexField, nameField, amountField, quantityField, totalField;
    private JTextArea descriptionArea;
    private MultiSelectCombo typeBox, targetBox;
    private JComboBox<String> statusBox;
    private JButton loadBtn, updateBtn, backBtn;
    private Container c;

    public EditDonation() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        title = new JLabel("Edit Donation");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(190, 10, 180, 35);

        l0 = new JLabel("Donation ID:");
        l0.setBounds(35, 55, 140, 25);
        indexField = new JTextField();
        indexField.setBounds(190, 55, 150, 28);
        loadBtn = new JButton("Load");
        loadBtn.setBounds(360, 55, 90, 28);
        loadBtn.addActionListener(this);

        l1 = new JLabel("Donation Name:");
        l1.setBounds(35, 95, 140, 25);
        nameField = new JTextField();
        nameField.setBounds(190, 95, 260, 28);

        l2 = new JLabel("Donation Type:");
        l2.setBounds(35, 135, 140, 25);
        typeBox = new MultiSelectCombo(new String[]{"Food", "Clothes", "Medicine", "Money"});
        typeBox.setBounds(190, 135, 260, 30);

        l3 = new JLabel("Amount:");
        l3.setBounds(35, 180, 140, 25);
        amountField = new JTextField();
        amountField.setBounds(190, 180, 260, 28);
        amountField.addKeyListener(this);

        l4 = new JLabel("Quantity:");
        l4.setBounds(35, 220, 140, 25);
        quantityField = new JTextField();
        quantityField.setBounds(190, 220, 260, 28);
        quantityField.addKeyListener(this);

        l5 = new JLabel("Total:");
        l5.setBounds(35, 260, 140, 25);
        totalField = new JTextField();
        totalField.setBounds(190, 260, 260, 28);
        totalField.setEditable(false);//رجوع

        l6 = new JLabel("Target Group:");
        l6.setBounds(35, 300, 140, 25);
        targetBox = new MultiSelectCombo(new String[]{"Children", "Adults", "Elderly", "Families", "Patients"});
        targetBox.setBounds(190, 300, 260, 30);

        l7 = new JLabel("Description:");
        l7.setBounds(35, 345, 140, 25);
        descriptionArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(descriptionArea);
        scroll.setBounds(190, 345, 260, 70);

        l8 = new JLabel("Status:");
        l8.setBounds(35, 430, 140, 25);
        statusBox = new JComboBox<String>(new String[]{"Available", "Requested", "Approved", "Delivered"});
        statusBox.setBounds(190, 430, 260, 30);

        updateBtn = new JButton("Update Donation");
        updateBtn.setBounds(135, 485, 150, 35);
        updateBtn.addActionListener(this);
        backBtn = new JButton("Back");
        backBtn.setBounds(315, 488, 90, 30);
        backBtn.addActionListener(this);

        c.add(title); c.add(l0); c.add(indexField); c.add(loadBtn);
        c.add(l1); c.add(nameField); c.add(l2); c.add(typeBox); c.add(l3); c.add(amountField);
        c.add(l4); c.add(quantityField); c.add(l5); c.add(totalField); c.add(l6); c.add(targetBox);
        c.add(l7); c.add(scroll); c.add(l8); c.add(statusBox); c.add(updateBtn); c.add(backBtn);

        setTitle("Donation Management System - Edit Donation");
        setSize(520, 590);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void calculateTotal() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            totalField.setText(String.valueOf(amount * quantity));
        } catch (Exception ex) {
            totalField.setText("0.0");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadBtn) {
            try {
                int i = Integer.parseInt(indexField.getText().trim()) - 1;
                if (i >= 0 && i < AddDonation.data.size()) {
                    Donation s = AddDonation.data.get(i);
                    
                    nameField.setText(s.getDonationName());
                    typeBox.setSelectedValuesFromText(s.getDonationType());
                    
                    
                    amountField.setText(String.valueOf(s.getAmount()));
                    quantityField.setText(String.valueOf(s.getQuantity()));
                    totalField.setText(String.valueOf(s.getTotal()));
                    
                    targetBox.setSelectedValuesFromText(s.getTargetGroup());
                    descriptionArea.setText(s.getDescription());
                    statusBox.setSelectedItem(s.getStatus());
                } else {
                    JOptionPane.showMessageDialog(null, "Donation ID not found!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Donation ID!");
            }
        }

        if (e.getSource() == updateBtn) {
            try {
                int i = Integer.parseInt(indexField.getText().trim()) - 1;
                if (i >= 0 && i < AddDonation.data.size()) {
                    
                    
                    double inputAmount = Double.parseDouble(amountField.getText().trim());
                    int inputQuantity = Integer.parseInt(quantityField.getText().trim());
                    
                    Donation s = AddDonation.data.get(i);
                    
                    
                    s.setDonationName(nameField.getText().trim());
                    s.setDonationType(typeBox.getSelectedValues());
                    s.setAmount(inputAmount);       
                    s.setQuantity(inputQuantity);   
                   
                    s.setTargetGroup(targetBox.getSelectedValues());
                    s.setDescription(descriptionArea.getText().trim());
                    s.setStatus(statusBox.getSelectedItem().toString());
                    
                    
                    totalField.setText(String.valueOf(s.getTotal()));
                    AddDonation.rewriteDonationFile();
                    
                    JOptionPane.showMessageDialog(null, "Donation updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Donation ID not found!");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for Amount and Quantity!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Please load a valid donation first!");
            }
        }

        if (e.getSource() == backBtn) { 
            new AssistantSection(); 
            dispose(); 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { calculateTotal(); }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}

    public static void main(String[] args) { new EditDonation(); }
}
