package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MultiSelectCombo extends JComboBox<String> {
    private JCheckBox[] checkBoxes;
    private boolean keepOpen = false;

    public MultiSelectCombo(String[] items) {
        super(items);
        checkBoxes = new JCheckBox[items.length];
        for (int i = 0; i < items.length; i++) {
            checkBoxes[i] = new JCheckBox(items[i]);
        }
        setRenderer(new ComboBoxRenderer());
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = getSelectedIndex();
                if (index >= 0 && index < checkBoxes.length) {
                    checkBoxes[index].setSelected(!checkBoxes[index].isSelected());
                    keepOpen = true;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            showPopup();
                            keepOpen = false;
                        }
                    });
                    repaint();
                }
            }
        });
    }

    public void setSelectedValuesFromText(String text) {
        for (JCheckBox box : checkBoxes) {
            box.setSelected(false);
            if (text != null) {
                String[] parts = text.split(",");
                for (String p : parts) {
                    if (box.getText().trim().equalsIgnoreCase(p.trim())) {
                        box.setSelected(true);
                    }
                }
            }
        }
        repaint();
    }

    public String getSelectedValues() {
        StringBuilder sb = new StringBuilder();
        for (JCheckBox box : checkBoxes) {
            if (box.isSelected()) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(box.getText());
            }
        }
        return sb.toString();
    }

    class ComboBoxRenderer implements ListCellRenderer<String> {
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            if (index >= 0) {
                checkBoxes[index].setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                checkBoxes[index].setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                return checkBoxes[index];
            }
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            String selected = getSelectedValues();
            label.setText(selected.isEmpty() ? "Select one or more..." : selected);
            return label;
        }
    }
}
