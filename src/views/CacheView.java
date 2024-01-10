package views;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class CacheView extends JFrame {
    private DefaultTableModel cacheTableModel;
    private JTable cacheTable;

    private String[] requestColumns = {"Type", "Address", "Tag", "body.Set", "Offset"};
    private String[] cacheColumns;
    private JButton continueButton;
    private JLabel nextLabel;
    private JTextArea queueArea;

    public CacheView() {
        this.setBounds(25, 25, 1500, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel cacheLabel = new JLabel("The cache:");
        cacheLabel.setFont(new Font("Tahoma",Font.PLAIN, 18));
        cacheLabel.setBounds(650, 80, 300, 20);
        this.getContentPane().add(cacheLabel);

        nextLabel = new JLabel();
        nextLabel.setFont(new Font("Tahoma",Font.PLAIN, 25));
        nextLabel.setBounds(100, 100, 300, 30);
        this.getContentPane().add(nextLabel);

        queueArea = new JTextArea("");
        queueArea.setFont(new Font("Tahoma",Font.PLAIN, 20));
        queueArea.setBounds(100, 150, 300, 400);
        queueArea.setOpaque(false);
        this.getContentPane().add(queueArea);

        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        continueButton.setBounds(700, 35, 100, 21);
        this.getContentPane().add(continueButton);

        this.setVisible(false);
    }


    public void setCacheTable(Integer size, String[] cacheColumns, Integer numberOfSets, Integer numberOfBlocks, Integer cacheSize) {
        this.cacheColumns = cacheColumns;
        cacheTableModel = new DefaultTableModel(null, cacheColumns);
        boolean color = false;
        int count = 0;
        int[] myArray = new int[size / 2];
        for (int i = 0; i < size/2; i++) {
            myArray[i] = -1;
        }
        int iterator = 0;
        for (int i = 0; i < size; i++) {
            if (count == cacheSize / (numberOfBlocks * numberOfSets)) {
                System.out.println(count);
                if (!color) {
                    color = true;
                } else {
                    color = false;
                }
                count = 0;
            }
            if (color) {
                myArray[iterator++] = i;
            }
            count++;

            Object[] data = {"", "", "", "", "", ""};
            cacheTableModel.addRow(data);
        }

        cacheTable = new JTable(cacheTableModel) {
            private static final Color EVEN_ROW_COLOR = new Color(240, 240, 240);
            private static final Color TARGET_ROW_COLOR = new Color(173, 216, 230); // Light blue

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);

                boolean present = false;
                for (int i = 0; i < size / 2; i++) {
                    if (row == myArray[i]) {
                        present = true;
                        break;
                    }
                }
                if (present) {
                    component.setBackground(TARGET_ROW_COLOR);
                } else {
                    component.setBackground(getBackground());
                }

                return component;
            }
        };
        JScrollPane cacheScrollPane = new JScrollPane(cacheTable);
        cacheScrollPane.setBounds(400, 100, 700, 400);
        cacheScrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        cacheScrollPane.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
        this.getContentPane().add(cacheScrollPane);
    }


    /*public void updateCacheTable( int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cacheTableModel.setValueAt(i, j);
            }
        }
    }*/

    public void addContinueButton(ActionListener actionListener) {
        continueButton.addActionListener(actionListener);
    }

    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message,"Program Finished", JOptionPane.ERROR_MESSAGE);
    }
}