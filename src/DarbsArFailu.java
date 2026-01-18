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
                
                if (parts.length == 10) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        double cena = Double.parseDouble(parts[9]);
                        pasutijumi.add(new Pica(id, parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], cena));
                    } catch (NumberFormatException e) {
                        System.out.println("Kluda lasot datus: " + line);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kluda nolasot failu: " + e.getMessage());
        }
        return pasutijumi;
    }
}