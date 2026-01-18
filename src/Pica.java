public class Pica {
    private String vards;
    private String adrese;
    private String talrunis;
    private String izmers;
    private String piedevas;
    private String merce;
    private String dzeriens;
    private double cena;

   
    public Pica(String vards, String adrese, String talrunis, String izmers, String piedevas, String merce, String dzeriens) {
        this.vards = vards;
        this.adrese = adrese;
        this.talrunis = talrunis;
        this.izmers = izmers;
        this.piedevas = piedevas;
        this.merce = merce;
        this.dzeriens = dzeriens;
        this.cena = 0.0;
    }

   
    public Pica(String vards, String adrese, String talrunis, String izmers, String piedevas, String merce, String dzeriens, double cena) {
        this(vards, adrese, talrunis, izmers, piedevas, merce, dzeriens);
        this.cena = cena;
    }

    public void aprekinatCenu() {
        double summa = 0.0;

        if (izmers.contains("Mazs")) summa += 6.0;
        else if (izmers.contains("Videjs")) summa += 8.0;
        else if (izmers.contains("Liels")) summa += 10.0;

        summa += 2.0;

        if (piedevas != null && !piedevas.isEmpty() && !piedevas.equalsIgnoreCase("Nav")) {
            String[] piedevuSaraksts = piedevas.split(",");
            summa += piedevuSaraksts.length * 1.0;
        }

        
        if (dzeriens != null && !dzeriens.equalsIgnoreCase("Nav")) {
            summa += 1.50;
        }

        this.cena = summa;
    }

    public String getVards() { return vards; }
    public String getAdrese() { return adrese; }
    public String getTalrunis() { return talrunis; }
    public String getIzmers() { return izmers; }
    public String getPiedevas() { return piedevas; }
    public String getMerce() { return merce; }
    public String getDzeriens() { return dzeriens; }
    public double getCena() { return cena; }

    public String sanemtInfo() {
        return "Klients: " + vards + " | " + izmers + " | " + dzeriens + " | " + String.format("%.2f", cena) + " EUR";
    }

    public String failaFormata() {
        return vards + ";" + adrese + ";" + talrunis + ";" + izmers + ";" + piedevas + ";" + merce + ";" + dzeriens + ";" + cena;
    }
}