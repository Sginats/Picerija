import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DarbsArFailu {
    private static final String FAILA_NOSAUKUMS = "pasutijumi.txt";

    public static void saglabat(ArrayList<Pica> pasutijumi) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FAILA_NOSAUKUMS))) {
            for (Pica p : pasutijumi) {
                writer.write(p.failaFormata());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Dati veiksmigi saglabati faila!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kluda saglabajot failu: " + e.getMessage());
        }
    }

    public static ArrayList<Pica> nolasit() {
        ArrayList<Pica> pasutijumi = new ArrayList<>();
        File file = new File(FAILA_NOSAUKUMS);
        if (!file.exists()) return pasutijumi;

        try (BufferedReader reader = new BufferedReader(new FileReader(FAILA_NOSAUKUMS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                
                if (parts.length == 8) {
                    try {
                        double cena = Double.parseDouble(parts[7]);
                        pasutijumi.add(new Pica(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], cena));
                    } catch (NumberFormatException e) {
                        System.out.println("Kluda lasot cenu: " + line);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kluda nolasot failu: " + e.getMessage());
        }
        return pasutijumi;
    }
}