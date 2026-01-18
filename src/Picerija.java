import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;

public class Picerija extends JFrame implements ActionListener {
    
    /**
	 * Ģenerētais serialVersionUID
	 */
	private static final long serialVersionUID = -6479829953355725530L;

	static ArrayList<Pica> pasutijumi = new ArrayList<>();

    private JComboBox<String> cbIzmers, cbMerce;
    private ArrayList<JCheckBox> piedevuCheckboxes;
    private JTextField tfVards, tfAdrese, tfTalrunis;
    private JTextArea taIzvade;
    private JButton btnPievienot, btnSaglabat, btnSkatit;

    public Picerija() {
        setTitle("Picerija - Pasutijumu Sistema");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelInput = new JPanel(new GridLayout(7, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Jauns Pasutijums"));

        panelInput.add(new JLabel("Izmers:"));
        String[] izmeri = {"Mazs-15 cm", "Videjs-30 cm", "Liels-45 cm"};
        cbIzmers = new JComboBox<>(izmeri);
        panelInput.add(cbIzmers);

        panelInput.add(new JLabel("Merce:"));
        String[] merces = {"Tomatu", "BBQ", "Kiploku", "Majonezes"};
        cbMerce = new JComboBox<>(merces);
        panelInput.add(cbMerce);

        panelInput.add(new JLabel("Piedevas:"));
        
        JPanel pnlPiedevas = new JPanel(new GridLayout(0, 2)); 
        String[] piedevuVarianti = {"Siers", "Senes", "Bekons", "Salami", "Paprika", "Sipoli", "Ananāss"};
        piedevuCheckboxes = new ArrayList<>();
        
        for (String variants : piedevuVarianti) {
            JCheckBox cb = new JCheckBox(variants);
            piedevuCheckboxes.add(cb);
            pnlPiedevas.add(cb);
        }
        panelInput.add(pnlPiedevas);

        panelInput.add(new JLabel("Klienta Vards:"));
        tfVards = new JTextField();
        panelInput.add(tfVards);

        panelInput.add(new JLabel("Adrese:"));
        tfAdrese = new JTextField();
        panelInput.add(tfAdrese);

        panelInput.add(new JLabel("Talrunis (tikai cipari):"));
        tfTalrunis = new JTextField();
        panelInput.add(tfTalrunis);

        panelInput.add(new JLabel("")); 
        btnPievienot = new JButton("Aprekinat un Pievienot");
        btnPievienot.addActionListener(this);
        panelInput.add(btnPievienot);

        taIzvade = new JTextArea();
        taIzvade.setEditable(false);
        taIzvade.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(taIzvade);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pasutijumu Vesture"));

        JPanel panelBottom = new JPanel(new FlowLayout());
        btnSkatit = new JButton("Ieladet no faila");
        btnSaglabat = new JButton("Saglabat faila");
        
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
            JOptionPane.showMessageDialog(this, "Dati ieladeti!");
        }
    }

    private void registretPasutijumu() {
        String izmers = (String) cbIzmers.getSelectedItem();
        String merce = (String) cbMerce.getSelectedItem();
        String vards = tfVards.getText();
        String adrese = tfAdrese.getText();
        String talrunis = tfTalrunis.getText();

        List<String> selectedValues = new ArrayList<>();
        boolean hasPineapple = false;

        for (JCheckBox cb : piedevuCheckboxes) {
            if (cb.isSelected()) {
                selectedValues.add(cb.getText());
                if (cb.getText().equals("Ananāss")) {
                    hasPineapple = true;
                }
            }
        }
        
        String piedevas = String.join(",", selectedValues);

        if (vards.isEmpty() || adrese.isEmpty() || talrunis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ludzu aizpildiet visus laukus!", "Kluda", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!talrunis.matches("\\d+") || talrunis.length() < 8) {
            JOptionPane.showMessageDialog(this, "Talrunim jasastav tikai no cipariem (min 8)!", "Kluda", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (piedevas.isEmpty()) piedevas = "Nav";

        if (hasPineapple) {
            triggerEasterEgg();
        }

        Pica jaunaPica = new Pica(vards, adrese, talrunis, izmers, piedevas, merce, 0.0);
        jaunaPica.aprekinatCenu();

        pasutijumi.add(jaunaPica);
        atjaunotSarakstu();
        
        for (JCheckBox cb : piedevuCheckboxes) {
            cb.setSelected(false);
        }
        tfVards.setText("");
        tfAdrese.setText("");
        tfTalrunis.setText("");
        
        JOptionPane.showMessageDialog(this, "Pasutijums pievienots! Summa: " + jaunaPica.getCena() + " EUR");
    }

    private void triggerEasterEgg() {
        new Thread(() -> {
            try {
                InputStream audioSrc = getClass().getResourceAsStream("/skana/stinky.mp3");
                
                if (audioSrc == null) {
                    System.err.println("ERROR: Could not find /skana/stinky.mp3.");
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }

                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception ex) {
                System.err.println("Audio Error: " + ex.getMessage());
            }
        }).start();

        URL imgURL = getClass().getResource("/bildes/shower.gif");
        ImageIcon icon = null;
        
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("ERROR: Could not find /bildes/shower.gif.");
        }

        if (icon == null || icon.getImageLoadStatus() == MediaTracker.ERRORED) {
             JOptionPane.showMessageDialog(this, "WHY PINEAPPLE??", "Crime against Pizza", JOptionPane.WARNING_MESSAGE);
        } else {
             JOptionPane.showMessageDialog(this, "", "Crime against Pizza", JOptionPane.INFORMATION_MESSAGE, icon);
        }
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