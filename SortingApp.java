import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class SortingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Application");
        frame.setSize(600, 500); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 182, 193)); 
        frame.add(panel);

        JLabel labelJumlah = new JLabel("Jumlah Data:");
        labelJumlah.setBounds(20, 20, 100, 25);
        labelJumlah.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(labelJumlah);

        JTextField inputJumlah = new JTextField();
        inputJumlah.setBounds(120, 20, 80, 25);
        panel.add(inputJumlah);

        JButton btnGenerate = new JButton("Generate");
        btnGenerate.setBounds(210, 20, 100, 25);
        panel.add(btnGenerate);

        JTextArea textAreaData = new JTextArea();
        textAreaData.setEditable(false);
        JScrollPane scrollPaneData = new JScrollPane(textAreaData);
        scrollPaneData.setBounds(20, 60, 540, 50); 
        panel.add(scrollPaneData);

        JLabel labelSorting = new JLabel("Metode Sorting:");
        labelSorting.setBounds(20, 130, 150, 25);
        labelSorting.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(labelSorting);

        JRadioButton bubbleSortBtn = new JRadioButton("Bubble Sort");
        bubbleSortBtn.setBounds(140, 130, 120, 25);
        bubbleSortBtn.setSelected(true);
        bubbleSortBtn.setBackground(new Color(255, 182, 193));
        panel.add(bubbleSortBtn);

        JRadioButton insertionSortBtn = new JRadioButton("Insertion Sort");
        insertionSortBtn.setBounds(260, 130, 120, 25);
        insertionSortBtn.setBackground(new Color(255, 182, 193));
        panel.add(insertionSortBtn);

        ButtonGroup sortingGroup = new ButtonGroup();
        sortingGroup.add(bubbleSortBtn);
        sortingGroup.add(insertionSortBtn);

        JButton btnSort = new JButton("Sort");
        btnSort.setBounds(400, 130, 80, 25);
        panel.add(btnSort);

        JLabel labelHasil = new JLabel("Hasil Sorting:");
        labelHasil.setBounds(20, 170, 150, 25);
        labelHasil.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(labelHasil);

        JTextArea textAreaHasil = new JTextArea();
        textAreaHasil.setEditable(false);
        JScrollPane scrollPaneHasil = new JScrollPane(textAreaHasil);
        scrollPaneHasil.setBounds(20, 200, 540, 50); 
        panel.add(scrollPaneHasil);

        JLabel labelSimulasi = new JLabel("Simulasi Proses:");
        labelSimulasi.setBounds(20, 260, 150, 25);
        labelSimulasi.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(labelSimulasi);

        JTextArea textAreaSimulasi = new JTextArea();
        textAreaSimulasi.setEditable(false);
        JScrollPane scrollPaneSimulasi = new JScrollPane(textAreaSimulasi);
        scrollPaneSimulasi.setBounds(20, 290, 540, 80);
        panel.add(scrollPaneSimulasi);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(250, 400, 100, 25);
        panel.add(btnReset);

        btnGenerate.addActionListener(e -> {
            try {
                int jumlah = Integer.parseInt(inputJumlah.getText());
                if (jumlah <= 0) throw new NumberFormatException();

                Random rand = new Random();
                int[] data = new int[jumlah];
                for (int i = 0; i < jumlah; i++) {
                    data[i] = rand.nextInt(100); // Angka antara 0-99
                }

                textAreaData.setText(Arrays.toString(data));
                textAreaHasil.setText("");
                textAreaSimulasi.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan jumlah data yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSort.addActionListener(e -> {
            String dataText = textAreaData.getText();
            if (dataText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Silakan generate data terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] stringData = dataText.replace("[", "").replace("]", "").split(", ");
            int[] data = Arrays.stream(stringData).mapToInt(Integer::parseInt).toArray();

            textAreaSimulasi.setText(""); // Reset simulasi

            if (bubbleSortBtn.isSelected()) {
                bubbleSort(data, textAreaSimulasi);
            } else if (insertionSortBtn.isSelected()) {
                insertionSort(data, textAreaSimulasi);
            }

            textAreaHasil.setText(Arrays.toString(data));
        });

        btnReset.addActionListener(e -> {
            inputJumlah.setText("");
            textAreaData.setText("");
            textAreaHasil.setText("");
            textAreaSimulasi.setText("");
            bubbleSortBtn.setSelected(true);
        });

        frame.setVisible(true);
    }

    // Implementasi Bubble Sort dengan simulasi
    private static void bubbleSort(int[] arr, JTextArea textAreaSimulasi) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                textAreaSimulasi.append(Arrays.toString(arr) + "\n");
            }
        }
    }

    // Implementasi Insertion Sort dengan simulasi
    private static void insertionSort(int[] arr, JTextArea textAreaSimulasi) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            textAreaSimulasi.append(Arrays.toString(arr) + "\n");
        }
    }
}
