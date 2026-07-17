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


public class AddAssistant extends JFrame implements ActionListener {
    private JLabel title, l1, l2, l3, l4;
    private JTextField t1, t2, t3, t4; 
    private JButton addBtn, backBtn;
    private Container c;
    
    
    public static ArrayList<Assistant> data = new ArrayList<Assistant>();
    private static boolean loadedFromFile = false;

    
    public static void loadFromFile() {
        if (loadedFromFile) {
            return;
        }
        data.clear();
        File file = new File("assistants.txt");
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
                String[] parts = line.split(",", -1);
                if (parts.length >= 4) {
                    data.add(new Assistant(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                }
            }
            loadedFromFile = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading donors from file: " + e.getMessage());
        }
    }

    public AddAssistant() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(245, 247, 250));

        title = new JLabel("Add Donor");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(175, 15, 180, 35);

        l1 = new JLabel("Donor Name:");
        l1.setBounds(35, 75, 120, 25);
        t1 = new JTextField(15);
        t1.setBounds(165, 75, 230, 28);

        l2 = new JLabel("Password:");
        l2.setBounds(35, 120, 120, 25);
        t2 = new JTextField(15);
        t2.setBounds(165, 120, 230, 28);

        l3 = new JLabel("Email:");
        l3.setBounds(35, 165, 120, 25);
        t3 = new JTextField(15);
        t3.setBounds(165, 165, 230, 28);

        l4 = new JLabel("Contact No:");
        l4.setBounds(35, 210, 120, 25);
        t4 = new JTextField(15);
        t4.setBounds(165, 210, 230, 28);

        addBtn = new JButton("Add Donor");
        addBtn.setBounds(145, 270, 130, 35);
        addBtn.addActionListener(this);

        backBtn = new JButton("Back");
        backBtn.setBounds(295, 273, 90, 30);
        backBtn.addActionListener(this);

        c.add(title); c.add(l1); c.add(t1); c.add(l2); c.add(t2);
        c.add(l3); c.add(t3); c.add(l4); c.add(t4); c.add(addBtn); c.add(backBtn);

        setTitle("Donation Management System - Add Donor");
        setSize(460, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

   
    private void saveAssistantToFile(Assistant assistant) {
        try (FileWriter fw = new FileWriter("assistants.txt", true); 
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println(assistant.getName() + "," + assistant.getPassword() + "," + 
                       assistant.getEmail() + "," + assistant.getContactNo());
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to file: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            if (t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty() ||
                t3.getText().trim().isEmpty() || t4.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all donor information!");
            } else {
                try {
                    String name = t1.getText().trim();
                    String password = t2.getText().trim();
                    String email = t3.getText().trim();
                    String phone = t4.getText().trim();

                    if (!name.matches("[a-zA-Z ]+"))
                        throw new Exception("Name must contain letters only!");

                    if (password.length() < 6)
                        throw new Exception("Password must be at least 6 characters!");

                    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
                        throw new Exception("Invalid email address!");

                    if (!phone.matches("\\d{10}"))
                        throw new Exception("Phone number must contain exactly 10 digits!");

                   
                    Assistant newAssistant = new Assistant(name, password, email, phone);
                    
                   
                    data.add(newAssistant);

                   
                    saveAssistantToFile(newAssistant);

                    JOptionPane.showMessageDialog(null, "Donor added successfully and saved to file!");

                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } 
            }
        }
        if (e.getSource() == backBtn) {
            new AdminSection(); 
            dispose();
        }
    }

    public static void main(String[] args) {
        new AddAssistant();
    }
}
