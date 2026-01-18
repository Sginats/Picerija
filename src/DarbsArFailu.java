/*/
 * ## ✅ TODO
- [ ]Izveidot galveno **Picerija** klasi (nodrošina Picas izveidi, saglabāšanu un darbību izsaukšanu)
- [ ]Izveidot Pica klasi ar atribūtiem (lielums, piedevas, mērce, cena, adrese, tālrunis, vārds)
- [ ]Realizēt picas izveidi (izmērs, piedevas, mērce)
- [ ]Ieviest piegādes informācijas ievadi (klienta vārds, adrese, tālrunis)
- [ ]Nodrošināt ievades datu pārbaudi (teksts, skaitļi, tukšas vērtības)
- [ ]Ieviest pasūtījumu labošanas funkcionalitāti
- [ ]Aprēķināt pasūtījuma beigu summu
- [ ]Izveidot DarbsArFailu klasi pasūtījumu saglabāšanai un nolasīšanai
- [ ]Nodrošināt aktīvo un nodoto pasūtījumu apskati
- [ ]Izveidot lietotāja saskarni ar JFrame
- [ ]Apstrādāt kļūdas un nepareizas lietotāja darbības
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            JOptionPane.showMessageDialog(null, "Kļūda saglabājot failu: " + e.getMessage());
        }
    }
	public static ArrayList<Pica> nolasit() {
        ArrayList<Pica> pasutijumi = new ArrayList<>();
        File file = new File(FAILA_NOSAUKUMS);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pasutijumi;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FAILA_NOSAUKUMS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 7) {
                    try {
                        double cena = Double.parseDouble(parts[6]);
                        pasutijumi.add(new Pica(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], cena));
                    } catch (NumberFormatException nfe) {
                        System.out.println("Kļūdaini dati failā (cena): " + line);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kļūda nolasot failu: " + e.getMessage());
        }
        return pasutijumi;
    }
}