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
public class Pica {
    private String vards;
    private String adrese;
    private String talrunis;
    private String izmers;   
    private String piedevas;
    private String merce;
    private double cena;
   
    public Pica(String vards, String adrese, String talrunis, String izmers, String piedevas, String merce, double cena) {
        this.vards = vards;
        this.adrese = adrese;
        this.talrunis = talrunis;
        this.izmers = izmers;
        this.piedevas = piedevas;
        this.merce = merce;
        this.cena = cena;
    }
    
    public void setKlientInfo(String vards, String adrese, String talrunis) {
        this.vards = vards;
        this.adrese = adrese;
        this.talrunis = talrunis;
    }
    public void aprekinatCenu() {
        double summa = 0.0;

        if (izmers.startsWith("Mazs")) summa += 6.0;
        else if (izmers.startsWith("Vidējs")) summa += 8.0;
        else if (izmers.startsWith("Liels")) summa += 10.0;

        summa += 2.0;

        if (piedevas != null && !piedevas.isEmpty() && !piedevas.equalsIgnoreCase("nav")) {
            String[] piedevuSaraksts = piedevas.split(",");
            summa += piedevuSaraksts.length * 1.0;
        }

        this.cena = summa;
    }
    public double getCena() {
        return cena;
    }

    public String getVards() {
        return vards;
    }
public String sanemtInfo() {
    return "------------------------------\n" +
           "Pica: " + izmers + " | Mērce: " + merce + "\n" +
           "Piedevas: " + piedevas + "\n" +
           "Klients: " + (vards.isEmpty() ? "Nav norādīts" : vards) + "\n" +
           "Tālr: " + (talrunis.isEmpty() ? "Nav norādīts" : talrunis) + "\n" +
           "Adrese: " + (adrese.isEmpty() ? "Nav norādīts" : adrese) + "\n" +
           "Summa: " + String.format("%.2f", cena) + " EUR";
}
public String failaFormata() {
    return vards + ";" + adrese + ";" + talrunis + ";" + izmers + ";" + piedevas + ";" + merce + ";" + cena;
}
}