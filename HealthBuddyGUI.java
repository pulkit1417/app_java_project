import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthBuddyGUI extends JFrame {

    private JTextField ageField, weightField, heightField;
    private JLabel bmiLabel, adviceLabel, waterLabel;
    private int waterIntake = 0;

    public HealthBuddyGUI() {
        // Set up the window
        setTitle("Health Buddy");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Main panel for input fields and buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel
        mainPanel.setBackground(new Color(245, 245, 245)); // Light background color

        // Heading
        JLabel heading = new JLabel("Welcome to Health Buddy");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(33, 150, 243)); // Blue text color
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(heading);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between components

        // Age input
        addLabelAndField(mainPanel, "Enter your age:", ageField = new JTextField());

        // Weight input
        addLabelAndField(mainPanel, "Enter your weight (kg):", weightField = new JTextField());

        // Height input
        addLabelAndField(mainPanel, "Enter your height (m):", heightField = new JTextField());

        // Button to calculate BMI
        JButton calcBMIButton = createStyledButton("Calculate BMI");
        calcBMIButton.addActionListener(new CalculateBMIListener());
        mainPanel.add(calcBMIButton);

        // Label to display the calculated BMI
        bmiLabel = createStyledLabel("Your BMI: ", 18);
        mainPanel.add(bmiLabel);

        // Label to display health advice
        adviceLabel = createStyledLabel("Health Advice: ", 18);
        mainPanel.add(adviceLabel);

        // Button to track water intake
        JButton addWaterButton = createStyledButton("Add a glass of water");
        addWaterButton.addActionListener(new WaterTrackerListener());
        mainPanel.add(addWaterButton);

        // Label to show water intake
        waterLabel = createStyledLabel("Water Intake: 0 glasses", 18);
        mainPanel.add(waterLabel);

        // Add padding between components
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add the main panel to the center of the frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the window visible
        setVisible(true);
    }

    // Method to add a label and text field to the main panel
    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(label);
        textField.setPreferredSize(new Dimension(200, 30));
        panel.add(textField);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between input fields
    }

    // Method to create a styled label
    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        label.setForeground(new Color(66, 66, 66)); // Dark gray text color
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    // Method to create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(33, 150, 243)); // Blue background color
        button.setForeground(Color.WHITE); // White text color
        button.setPreferredSize(new Dimension(200, 40));
        button.setFocusPainted(false); // Remove focus border
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    // Inner class to handle BMI calculation
    private class CalculateBMIListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                double bmi = weight / (height * height);

                bmiLabel.setText(String.format("Your BMI: %.2f", bmi));

                // Provide health advice based on BMI
                if (bmi < 18.5) {
                    adviceLabel.setText("Health Advice: You are underweight.");
                } else if (bmi >= 18.5 && bmi < 24.9) {
                    adviceLabel.setText("Health Advice: You have a normal weight.");
                } else if (bmi >= 25 && bmi < 29.9) {
                    adviceLabel.setText("Health Advice: You are overweight.");
                } else {
                    adviceLabel.setText("Health Advice: You are obese.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for weight and height.");
            }
        }
    }

    // Inner class to handle water intake tracking
    private class WaterTrackerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            waterIntake++;
            waterLabel.setText("Water Intake: " + waterIntake + " glasses");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HealthBuddyGUI());
    }
}
