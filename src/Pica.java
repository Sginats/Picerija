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

import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;

public class Pica {
	String vards;
    String adrese;
    String talrunis;
    String izmers;     // "Mazs-15 cm", "Vidējs-30 cm", "Liels-45 cm"
    String piedevas;
    String merce;
    double cena;
    public Pica(String vards, String adrese, String talrunis, String izmers, String piedevas, String merce, double cena) {
        this.vards = vards;
        this.adrese = adrese;
        this.talrunis = talrunis;
        this.izmers = izmers;
        this.piedevas = piedevas;
        this.merce = merce;
        this.cena = cena;
    }
    public String sanemtInfo() {
        return "Klients: " + vards + " | Tālr: " + talrunis + "\n" +
               "Adrese: " + adrese + "\n" +
               "Pica: " + izmers + " | Mērce: " + merce + "\n" +
               "Piedevas: " + piedevas + "\n" +
               "Summa: " + String.format("%.2f", cena) + " EUR\n" +
               "------------------------------";
    }
    public String failaFormata() {
        return vards + ";" + adrese + ";" + talrunis + ";" + izmers + ";" + piedevas + ";" + merce + ";" + cena;
    }
}
