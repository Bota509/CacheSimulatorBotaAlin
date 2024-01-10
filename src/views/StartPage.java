package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPage extends JFrame {

    private JComboBox<Integer> comboBoxAddressWidth;
    private JComboBox<Integer> comboBoxCacheSize;
    private JComboBox<Integer> comboBoxBlockSize;
    private JComboBox<Integer> comboBoxAssociativity;
    private JComboBox<String> comboBoxWriteHit;
    private JComboBox<String> comboBoxReplacement;
    private  JButton buttonStartSimulation;

   // Integer[] comboBoxAddressWidthOption = {4,6,8,10,12};
    Integer[] comboBoxAddressWidthOption = {12};
   /// Integer[] comboBoxCacheSizeOption = {8, 16,32, 64, 128, 256};
    Integer[] comboBoxCacheSizeOption = {128};
   /// Integer[] comboBoxBlockSizeOption = {2, 4, 8};
    Integer[] comboBoxBlockSizeOption = {64};
    //Integer[] comboBoxAssociativityOption = {1, 2, 4};
    Integer[] comboBoxAssociativityOption = {1, 2, 4};
    String[] comboBoxWriteHitOption = {"Write-through", "Write-back"};
    String[] comboBoxReplacementOption = {"LRU", "FIFO"};

    public StartPage() {
        this.setBounds(400, 200, 600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);



         comboBoxAddressWidth = new JComboBox<>(comboBoxAddressWidthOption);
        comboBoxAddressWidth.setBounds(416, 166, 99, 21);
        add(comboBoxAddressWidth);

        comboBoxCacheSize = new JComboBox<>(comboBoxCacheSizeOption);
        comboBoxCacheSize.setBounds(416, 197, 99, 21);
        add(comboBoxCacheSize);

        comboBoxBlockSize = new JComboBox<>(comboBoxBlockSizeOption);
        comboBoxBlockSize.setBounds(416, 228, 99, 21);
        add(comboBoxBlockSize);

        comboBoxAssociativity = new JComboBox<>(comboBoxAssociativityOption);
        comboBoxAssociativity.setBounds(416, 259, 99, 21);
        add(comboBoxAssociativity);

         comboBoxWriteHit = new JComboBox<>(comboBoxWriteHitOption);
        comboBoxWriteHit.setBounds(416, 290, 99, 21);
        add(comboBoxWriteHit);

        comboBoxReplacement = new JComboBox<>(comboBoxReplacementOption);
        comboBoxReplacement.setBounds(416, 329, 99, 21);
        add(comboBoxReplacement);

        JLabel addressWidthLabel = new JLabel("Address Width");
        addressWidthLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addressWidthLabel.setBounds(261, 170, 145, 17);
        add(addressWidthLabel);

        JLabel cacheSizeLabel = new JLabel("Cache Size");
        cacheSizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cacheSizeLabel.setBounds(271, 201, 104, 17);
        add(cacheSizeLabel);

        JLabel lblBlockSize = new JLabel("Block Size");
        lblBlockSize.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblBlockSize.setBounds(271, 232, 104, 17);
        add(lblBlockSize);

        JLabel lblAssociativity = new JLabel("Associativity");
        lblAssociativity.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAssociativity.setBounds(271, 263, 120, 17);
        add(lblAssociativity);

        JLabel lblWriteHit = new JLabel("Write Hit");
        lblWriteHit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblWriteHit.setBounds(269, 294, 104, 17);
        add(lblWriteHit);

        JLabel lblReplacement = new JLabel("Replacement");
        lblReplacement.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblReplacement.setBounds(271, 333, 120, 17);
        add(lblReplacement);

        JLabel lblBits = new JLabel("Bits");
        lblBits.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblBits.setBounds(538, 170, 145, 17);
        add(lblBits);

        JLabel lblBytes_1 = new JLabel("Bytes");
        lblBytes_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblBytes_1.setBounds(538, 201, 145, 17);
        add(lblBytes_1);

        JLabel lblBytes = new JLabel("Bytes");
        lblBytes.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblBytes.setBounds(538, 232, 145, 17);
        add(lblBytes);

        JLabel lblWay = new JLabel("Ways");
        lblWay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblWay.setBounds(538, 263, 145, 17);
        add(lblWay);

        buttonStartSimulation = new JButton("Start Simulation!");
        buttonStartSimulation.setFont(new Font("Tahoma", Font.PLAIN, 20));
        buttonStartSimulation.setBounds(340, 400, 230, 55);
        add(buttonStartSimulation);

        JLabel lblNewLabel = new JLabel("Cache Simulator");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
        lblNewLabel.setBounds(328, 23, 302, 30);
        add(lblNewLabel);

        this.setVisible(true);

    }




    public int getComboBoxAddressWidth() {
        return Integer.parseInt(comboBoxAddressWidth.getSelectedItem().toString());
    }

    public int getComboBoxCacheSize() {
        return Integer.parseInt(comboBoxCacheSize.getSelectedItem().toString());
    }

    public int getComboBoxBlockSize() {
        return Integer.parseInt(comboBoxBlockSize.getSelectedItem().toString());
    }

    public int getComboBoxAssociativity() {
        return Integer.parseInt(comboBoxAssociativity.getSelectedItem().toString());
    }


    public String getComboBoxWriteHit() {
        return comboBoxWriteHit.getSelectedItem().toString();
    }
    public String getComboBoxReplacement() {
        return comboBoxReplacement.getSelectedItem().toString();
    }

    public void addStartListener(ActionListener action) {
        buttonStartSimulation.addActionListener(action);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ALERT", JOptionPane.ERROR_MESSAGE);
    }
}
