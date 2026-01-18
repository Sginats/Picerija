public class Pica {
    private int id;
    private String vards;
    private String adrese;
    private String talrunis;
    private String izmers;
    private String piedevas;
    private String merce;
    private String dzeriens;
    private String uzkodas;
    private double cena;

    public Pica(int id, String vards, String adrese, String talrunis, String izmers, String piedevas, String merce, String dzeriens, String uzkodas) {
        this.id = id;
        this.vards = vards;
        this.adrese = adrese;
        this.talrunis = talrunis;
        this.izmers = izmers;
        this.piedevas = piedevas;
        this.merce = merce;
        this.dzeriens = dzeriens;
        this.uzkodas = uzkodas;
        this.cena = 0.0;
    }

    public Pica(int id, String vards, String adrese, String talrunis, String izmers, String piedevas, String merce, String dzeriens, String uzkodas, double cena) {
        this(id, vards, adrese, talrunis, izmers, piedevas, merce, dzeriens, uzkodas);
        this.cena = cena;
    }

    public void aprekinatCenu() {
        double summa = 0.0;

        if (izmers.contains("Mazs")) summa += 6.0;
        else if (izmers.contains("Videjs")) summa += 8.0;
        else if (izmers.contains("Liels")) summa += 10.0;

        if (!adrese.equals("-") && !adrese.isEmpty()) {
            summa += 2.0;
        }

        if (piedevas != null && !piedevas.isEmpty() && !piedevas.equalsIgnoreCase("Nav")) {
            String[] piedevuSaraksts = piedevas.split(",");
            summa += piedevuSaraksts.length * 1.0;
        }

        if (dzeriens != null && !dzeriens.equalsIgnoreCase("Nav")) summa += 1.50;
        if (uzkodas != null && !uzkodas.equalsIgnoreCase("Nav")) summa += 3.00;

        this.cena = summa;
    }

    public int getId() { return id; }
    public String getVards() { return vards; }
    public String getAdrese() { return adrese; }
    public String getTalrunis() { return talrunis; }
    public String getIzmers() { return izmers; }
    public String getPiedevas() { return piedevas; }
    public String getMerce() { return merce; }
    public String getDzeriens() { return dzeriens; }
    public String getUzkodas() { return uzkodas; }
    public double getCena() { return cena; }

    public String sanemtInfo() {
        return "ID: " + id + " | " + vards + " | " + String.format("%.2f", cena) + " EUR";
    }

    public String failaFormata() {
        return id + ";" + vards + ";" + adrese + ";" + talrunis + ";" + izmers + ";" + piedevas + ";" + merce + ";" + dzeriens + ";" + uzkodas + ";" + cena;
    }
}