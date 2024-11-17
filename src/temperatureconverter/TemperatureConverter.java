/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package temperatureconverter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Nur Amelia Abdah 2210010146
 */
public class TemperatureConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Membuat Frame
        JFrame frame = new JFrame("Aplikasi Konversi Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Panel untuk Input dan Output
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel inputLabel = new JLabel("Masukkan Suhu:");
        JTextField inputField = new JTextField();
        JLabel scaleLabel = new JLabel("Pilih Skala Awal:");
        JComboBox<String> scaleComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin", "Reamur"});
        JLabel targetLabel = new JLabel("Pilih Skala Target:");
        JComboBox<String> targetComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin", "Reamur"});
        JButton convertButton = new JButton("Konversi");
        JLabel resultLabel = new JLabel("Hasil Konversi: ");
        JTextField resultField = new JTextField();
        resultField.setEditable(false);

        // Menambahkan Komponen ke Panel
        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(scaleLabel);
        panel.add(scaleComboBox);
        panel.add(targetLabel);
        panel.add(targetComboBox);
        panel.add(new JLabel()); // Placeholder
        panel.add(convertButton);
        panel.add(resultLabel);
        panel.add(resultField);

        frame.add(panel, BorderLayout.CENTER);

        // Validasi Input Hanya Angka
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != '-') {
                    e.consume(); // Hanya angka, titik, dan minus
                }
            }
        });

        // Logika Konversi
        convertButton.addActionListener(e -> {
            try {
                double input = Double.parseDouble(inputField.getText());
                String fromScale = (String) scaleComboBox.getSelectedItem();
                String toScale = (String) targetComboBox.getSelectedItem();

                double result = convertTemperature(input, fromScale, toScale);
                resultField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Menampilkan Frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Metode Konversi Suhu
    public static double convertTemperature(double value, String fromScale, String toScale) {
        if (fromScale.equals(toScale)) return value;

        double celsiusValue = switch (fromScale) {
            case "Fahrenheit" -> (value - 32) * 5 / 9;
            case "Kelvin" -> value - 273.15;
            case "Reamur" -> value * 5 / 4;
            default -> value; // Jika dari Celsius
        };

        return switch (toScale) {
            case "Fahrenheit" -> (celsiusValue * 9 / 5) + 32;
            case "Kelvin" -> celsiusValue + 273.15;
            case "Reamur" -> celsiusValue * 4 / 5;
            default -> celsiusValue; // Jika ke Celsius
        };
    }
    
}
