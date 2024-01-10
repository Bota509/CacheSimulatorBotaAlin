package Controller;

import Enums.CacheType;
import Enums.Replacement;
import Enums.Type;
import Enums.WritePolicy;
import body.Cache;
import views.StartPage;
import views.CacheView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class CacheController {
    static double totalRequests = 0;

    private StartPage startPage;
    private CacheView cacheView;
    private int addressWidth;
    private int cacheSize;
    private int blockSize;
    private int associativity;
    private WritePolicy writePolicy;
    private Replacement replacement;
    private Cache cache;
    private Type type;
    List<String> inputList = new ArrayList<>();

    public CacheController(StartPage startPage, CacheView cacheView) {
        this.startPage = startPage;
        this.cacheView = cacheView;
        this.startPage.addStartListener(new AddStartListener());
        this.cacheView.addContinueButton(new AddContinueListener());
    }

    class AddStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println("Started");
            addressWidth = startPage.getComboBoxAddressWidth();
            cacheSize = startPage.getComboBoxCacheSize();
            blockSize = startPage.getComboBoxBlockSize();
            associativity = startPage.getComboBoxAssociativity();


            if (startPage.getComboBoxWriteHit().equals("Write-through"))
                writePolicy = WritePolicy.WRITE_THROUGH;
            else
                writePolicy = WritePolicy.WRITE_BACK;

            if (startPage.getComboBoxReplacement().equals("LRU"))
                replacement = Replacement.LRU;
            else
                replacement = Replacement.FIFO;


            int setsNumber = cacheSize / blockSize;  //number of sets
            cache = new Cache(associativity, cacheSize, writePolicy, replacement,blockSize);

            readFromFile();
            buildTables();
        }
    }

    public void readFromFile() {
        String filePath = "file1.txt"; // Replace with your file path

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Process each line here
                inputList.add(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void buildTables() {
        List<String> columns = new ArrayList<>(List.of("Dirty", "V", "Tag"));
        if (writePolicy == WritePolicy.WRITE_THROUGH) {
            columns.remove(0);
        }

        // for (int i = 0; i < blockSize; i++) {
        columns.add("Data");
        // }
        cacheView.setCacheTable(cacheSize / blockSize, columns.toArray(new String[0]), associativity, blockSize, cacheSize);
        cacheView.setVisible(true);

    }


    class AddContinueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // System.out.println(inputList.get(0));

                totalRequests++;
                System.out.println("# " + totalRequests + "iteration");
                String[] words = inputList.get(0).split("\\s+");

                //System.out.println(words[1]);
                System.out.println("Full Address in hexa : " + words[1].substring(2));
                BigInteger address = new BigInteger(words[1].substring(2), 16);

                System.out.println("Full Address in decimal : " + address);

                BigInteger tag = address.divide(BigInteger.valueOf(blockSize));

                System.out.println("Tag Number: " + tag);

                int setNumber = (tag.mod(BigInteger.valueOf(cache.getNumOfSets()))).intValue();
                System.out.println("Set Number: " + setNumber);



                inputList.remove(0);

                if (words[0].equals("read")) {
                    type = Type.READ;
                } else if (words[0].equals(("write"))) {
                    type = Type.WRITE;
                }

                switch (type) {
                    case READ:
                        System.out.println("Read Operation");
                        cache.read(tag, setNumber);
                        break;

                    case WRITE:
                        System.out.println("Write Operation");
                        cache.write(tag, setNumber);
                        break;

                    default:
                        System.out.println("nothing");
                        cacheView.errorMessage("The program has no more inputs");
                        break;
                }

                System.out.println("Ratio: " + cache.getNumMisses() / totalRequests);
                System.out.println("Misses: " + cache.getNumMisses());
                System.out.println("Hits" + cache.getNumHits());
                System.out.println("Reads: " + cache.getNumReads());

                System.out.println();
                System.out.println();

            } catch (Exception exception) {
                System.out.println("Finished");
                cacheView.errorMessage("The program has no more inputs");
            }
        }
    }
}
