/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDonation extends JFrame implements ActionListener, KeyListener {
    private JLabel title, l1, l2, l3, l4, l5, l6, l7, l8;
    private JTextField nameField, amountField, quantityField, totalField;
    private JTextArea descriptionArea;
    private MultiSelectCombo typeBox, targetBox;
    private JComboBox<String> statusBox;
    private JButton addBtn, backBtn;
    private Container c;

    public static ArrayList<Donation> data = new ArrayList<Donation>();
    private static boolean loadedFromFile = false;
    private static final Pattern LEGACY_LINE = Pattern.compile(
            "^(.+?),(.+?),(\\d+(?:\\.\\d+)?),(\\d+),(\\d+(?:\\.\\d+)?),(.+),([^,]*),([^,]*)$");

    /** تحميل التبرعات من الملف عند تشغيل البرنامج حتى تبقى البيانات بعد الإغلاق */
    public static void loadFromFile() {
        if (loadedFromFile) {
            return;
        }
        data.clear();
        File file = new File("donations.txt");
        if (!file.exists()) {
            loadedFromFile = true;
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Donation donation = parseDonationLine(line);
                if (donation != null) {
                    data.add(donation);
                }
            }
            loadedFromFile = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading donations from file: " + e.getMessage());
        }
    }

    private static Donation parseDonationLine(String line) {
        try {
            if (line.contains("|")) {
                String[] p = line.split("\\|", -1);
                if (p.length >= 8) {
                    return new Donation(
                            p[0].trim(),
                            p[1].trim(),
                            Double.parseDouble(p[2].trim()),
                            Integer.parseInt(p[3].trim()),
                            p[5].trim(),
                            p[6].trim(),
                            p[7].trim()
                    );
                }
                return null;
            }
            Matcher m = LEGACY_LINE.matcher(line);
            if (m.matches()) {
                return new Donation(
                        m.group(1).trim(),
                        m.group(2).trim(),
                        Double.parseDouble(m.group(3).trim()),
                        Integer.parseInt(m.group(4).trim()),
                        m.group(6).trim(),
                        m.group(7).trim(),
                        m.group(8).trim()
                );
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /** كتابة كل التبرعات للملف (بعد تعديل أو حذف) */
    public static void rewriteDonationFile() {
        try (FileWriter fw = new FileWriter("donations.txt", false);
             PrintWriter pw = new PrintWriter(fw)) {
            for (Donation donation : data) {
                pw.println(formatDonationLine(donation));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating file: " + e.getMessage());
        }
    }

    public static String formatDonationLine(Donation donation) {
        return donation.getDonationName() + "|" + donation.getDonationType() + "|" +
                donation.getAmount() + "|" + donation.getQuantity() + "|" +
                donation.getTotal() + "|" + donation.getTargetGroup() + "|" +
                donation.getDescription() + "|" + donation.getStatus();
    }

    public AddDonation() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        title = new JLabel("Add Donation");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(185, 15, 180, 35);

        l1 = new JLabel("Donation Name:");
        l1.setBounds(35, 65, 140, 25);
        nameField = new JTextField();
        nameField.setBounds(190, 65, 260, 28);

        l2 = new JLabel("Donation Type:");
        l2.setBounds(35, 105, 140, 25);
        typeBox = new MultiSelectCombo(new String[]{"Food", "Clothes", "Medicine", "Money"});
        typeBox.setBounds(190, 105, 260, 30);

        l3 = new JLabel("Unit Value:");
        l3.setBounds(35, 150, 140, 25);
        amountField = new JTextField("0");
        amountField.setBounds(190, 150, 260, 28);
        amountField.addKeyListener(this);

        l4 = new JLabel("Quantity:");
        l4.setBounds(35, 190, 140, 25);
        quantityField = new JTextField("1");
        quantityField.setBounds(190, 190, 260, 28);
        quantityField.addKeyListener(this);

        l5 = new JLabel("Total:");
        l5.setBounds(35, 230, 140, 25);
        totalField = new JTextField("0.0");
        totalField.setBounds(190, 230, 260, 28);
        totalField.setEditable(false);

        l6 = new JLabel("Target Group:");
        l6.setBounds(35, 270, 140, 25);
        targetBox = new MultiSelectCombo(new String[]{"Children", "Adults", "Elderly", "Families", "Patients"});
        targetBox.setBounds(190, 270, 260, 30);

        l7 = new JLabel("Description:");
        l7.setBounds(35, 315, 140, 25);
        descriptionArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(descriptionArea);
        scroll.setBounds(190, 315, 260, 70);

        l8 = new JLabel("Status:");
        l8.setBounds(35, 400, 140, 25);
        statusBox = new JComboBox<String>(new String[]{"Available", "Requested", "Approved", "Delivered"});
        statusBox.setBounds(190, 400, 260, 30);

        addBtn = new JButton("Add Donation");
        addBtn.setBounds(150, 460, 140, 35);
        addBtn.addActionListener(this);

        backBtn = new JButton("Back");
        backBtn.setBounds(315, 463, 90, 30);
        backBtn.addActionListener(this);

        c.add(title);
        c.add(l1); c.add(nameField);
        c.add(l2); c.add(typeBox);
        c.add(l3); c.add(amountField);
        c.add(l4); c.add(quantityField);
        c.add(l5); c.add(totalField);
        c.add(l6); c.add(targetBox);
        c.add(l7); c.add(scroll);
        c.add(l8); c.add(statusBox);
        c.add(addBtn); c.add(backBtn);

        setTitle("Donation Management System - Add Donation");
        setSize(520, 560);
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

    
    private void saveDonationToFile(Donation donation) {
        try (FileWriter fw = new FileWriter("donations.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(formatDonationLine(donation));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving donation to file: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            calculateTotal();
            if (nameField.getText().trim().isEmpty() || typeBox.getSelectedValues().isEmpty() ||
                amountField.getText().trim().isEmpty() || quantityField.getText().trim().isEmpty() ||
                targetBox.getSelectedValues().isEmpty() || descriptionArea.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all donation information!");
            } else {
                try {
                    
                    double parsedAmount = Double.parseDouble(amountField.getText().trim());
                    int parsedQuantity = Integer.parseInt(quantityField.getText().trim());

                    if (parsedAmount < 0 || parsedQuantity <= 0) {
                        throw new Exception("Amount cannot be negative and Quantity must be greater than 0!");
                    }

                    Donation newDonation = new Donation(
                        nameField.getText().trim(),
                        typeBox.getSelectedValues(),
                        parsedAmount,
                        parsedQuantity,
                        targetBox.getSelectedValues(),
                        descriptionArea.getText().trim(),
                        statusBox.getSelectedItem().toString()
                    );

                    
                    data.add(newDonation);
                    saveDonationToFile(newDonation);

                    JOptionPane.showMessageDialog(null, "Donation added successfully! Total = " + newDonation.getTotal());
                    
                   
                    nameField.setText(""); amountField.setText("0"); quantityField.setText("1"); 
                    totalField.setText("0.0"); descriptionArea.setText("");
                    
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Amount and Quantity!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
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

    public static void main(String[] args) {
        new AddDonation();
    }
}
