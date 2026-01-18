import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Picerija extends JFrame implements ActionListener {
	/**
	 * Ģenerētais serialVersionUID
	 */
	private static final long serialVersionUID = 3143085933575258568L;

	static ArrayList<Pica> pasutijumi = new ArrayList<>();

    private JComboBox<String> cbIzmers, cbMerce;
    private JTextField tfPiedevas, tfVards, tfAdrese, tfTalrunis;
    private JTextArea taIzvade;
    private JButton btnPievienot, btnSaglabat, btnSkatit;

    public Picerija() {
        setTitle("Picerija");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelInput = new JPanel(new GridLayout(7, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Jauns Pasūtījums"));

        panelInput.add(new JLabel("Izmērs:"));
        String[] izmeri = {"Mazs-15 cm", "Vidējs-30 cm", "Liels-45 cm"};
        cbIzmers = new JComboBox<>(izmeri);
        panelInput.add(cbIzmers);

        panelInput.add(new JLabel("Mērce:"));
        String[] merces = {"Tomātu", "BBQ", "Ķiploku", "Majonēzes"};
        cbMerce = new JComboBox<>(merces);
        panelInput.add(cbMerce);

        panelInput.add(new JLabel("Piedevas (atdalīt ar komatu):"));
        tfPiedevas = new JTextField();
        panelInput.add(tfPiedevas);

        panelInput.add(new JLabel("Klienta Vārds:"));
        tfVards = new JTextField();
        panelInput.add(tfVards);

        panelInput.add(new JLabel("Adrese:"));
        tfAdrese = new JTextField();
        panelInput.add(tfAdrese);

        panelInput.add(new JLabel("Tālrunis (tikai cipari):"));
        tfTalrunis = new JTextField();
        panelInput.add(tfTalrunis);

        panelInput.add(new JLabel(""));
        btnPievienot = new JButton("Aprēķināt un Pievienot");
        btnPievienot.addActionListener(this);
        panelInput.add(btnPievienot);

        taIzvade = new JTextArea();
        taIzvade.setEditable(false);
        taIzvade.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(taIzvade);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pasūtījumu Vēsture"));

        JPanel panelBottom = new JPanel(new FlowLayout());
        btnSkatit = new JButton("Ielādēt no faila");
        btnSaglabat = new JButton("Saglabāt failā");
        
        btnSkatit.addActionListener(this);
        btnSaglabat.addActionListener(this);
        
        panelBottom.add(btnSkatit);
        panelBottom.add(btnSaglabat);

        add(panelInput, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        pasutijumi = DarbsArFailu.nolasit();
        atjaunotSarakstu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPievienot) {
            registretPasutijumu();
        } else if (e.getSource() == btnSaglabat) {
            DarbsArFailu.saglabat(pasutijumi);
        } else if (e.getSource() == btnSkatit) {
            pasutijumi = DarbsArFailu.nolasit();
            atjaunotSarakstu();
            JOptionPane.showMessageDialog(this, "Dati ielādēti!");
        }
    }

    private void registretPasutijumu() {
        String izmers = (String) cbIzmers.getSelectedItem();
        String merce = (String) cbMerce.getSelectedItem();
        String piedevas = tfPiedevas.getText();
        String vards = tfVards.getText();
        String adrese = tfAdrese.getText();
        String talrunis = tfTalrunis.getText();

        if (vards.isEmpty() || adrese.isEmpty() || talrunis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lūdzu aizpildiet visus laukus!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!talrunis.matches("\\d+") || talrunis.length() < 8) {
            JOptionPane.showMessageDialog(this, "Tālrunim jāsastāv tikai no cipariem (min 8)!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (piedevas.isEmpty()) piedevas = "Nav";

        Pica jaunaPica = new Pica(vards, adrese, talrunis, izmers, piedevas, merce, getOpacity());
        jaunaPica.aprekinatCenu();

        pasutijumi.add(jaunaPica);
        atjaunotSarakstu();
        
        tfPiedevas.setText("");
        tfVards.setText("");
        tfAdrese.setText("");
        tfTalrunis.setText("");
        
        JOptionPane.showMessageDialog(this, "Pasūtījums pievienots! Summa: " + jaunaPica.getCena() + " EUR");
    }

    private void atjaunotSarakstu() {
        taIzvade.setText("");
        for (Pica p : pasutijumi) {
            taIzvade.append(p.sanemtInfo() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Picerija().setVisible(true);
        });
    }
}