import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
	private static final long serialVersionUID = -8572302226688599272L;

	static ArrayList<Pica> pasutijumi = new ArrayList<>();

    private JComboBox<String> cbIzmers, cbMerce, cbDzeriens;
    private ArrayList<JCheckBox> piedevuCheckboxes;
    private JTextField tfVards, tfAdrese, tfTalrunis;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnPievienot, btnSaglabat, btnSkatit, btnIziet, btnDzest;

    private final Color SIDEBAR_BG = new Color(33, 37, 41);
    private final Color MAIN_BG = new Color(240, 242, 245);
    private final Color ACCENT_COLOR = new Color(13, 110, 253);
    private final Color TEXT_WHITE = new Color(255, 255, 255);
    private final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 12);
    private final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 13);

    public Picerija() {
        setTitle("Picerija");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel(new GridBagLayout());
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(320, 0));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        
        int row = 0;

        JLabel lblTitle = new JLabel("JAUNS PASUTIJUMS");
        lblTitle.setForeground(ACCENT_COLOR);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridy = row++;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.weighty = 0;
        sidebar.add(lblTitle, gbc);

        gbc.insets = new Insets(0, 0, 15, 0);

        addSidebarLabel(sidebar, gbc, row++, "Izmers:");
        cbIzmers = new JComboBox<>(new String[]{"Mazs-15 cm", "Videjs-30 cm", "Liels-45 cm"});
        addSidebarComponent(sidebar, gbc, row++, cbIzmers);

        addSidebarLabel(sidebar, gbc, row++, "Merce:");
        cbMerce = new JComboBox<>(new String[]{"Tomatu", "BBQ", "Kiploku", "Majonezes"});
        addSidebarComponent(sidebar, gbc, row++, cbMerce);

        addSidebarLabel(sidebar, gbc, row++, "Dzeriens (+1.50 EUR):");
        cbDzeriens = new JComboBox<>(new String[]{"Nav", "Coca-Cola", "Fanta", "Sprite", "Udens"});
        addSidebarComponent(sidebar, gbc, row++, cbDzeriens);

        addSidebarLabel(sidebar, gbc, row++, "Piedevas:");
        
        JPanel pnlPiedevas = new JPanel(new GridLayout(3, 3, 5, 5)); 
        pnlPiedevas.setBackground(SIDEBAR_BG);
        String[] piedevuVarianti = {"Siers", "Senes", "Bekons", "Salami", "Paprika", "Sipoli", "Ananāss"};
        piedevuCheckboxes = new ArrayList<>();
        for (String variants : piedevuVarianti) {
            JCheckBox cb = new JCheckBox(variants);
            cb.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            cb.setForeground(TEXT_WHITE);
            cb.setOpaque(false);
            cb.setFocusPainted(false);
            piedevuCheckboxes.add(cb);
            pnlPiedevas.add(cb);
        }
        gbc.gridy = row++;
        sidebar.add(pnlPiedevas, gbc);

        addSidebarLabel(sidebar, gbc, row++, "Klienta Vards:");
        tfVards = new JTextField();
        addSidebarComponent(sidebar, gbc, row++, tfVards);

        addSidebarLabel(sidebar, gbc, row++, "Adrese:");
        tfAdrese = new JTextField();
        addSidebarComponent(sidebar, gbc, row++, tfAdrese);

        addSidebarLabel(sidebar, gbc, row++, "Talrunis:");
        tfTalrunis = new JTextField();
        addSidebarComponent(sidebar, gbc, row++, tfTalrunis);

        btnPievienot = new JButton("APSTIPRINAT PASUTIJUMU");
        styleButton(btnPievienot, ACCENT_COLOR);
        gbc.gridy = row++;
        gbc.insets = new Insets(10, 0, 0, 0);
        sidebar.add(btnPievienot, gbc);

        gbc.gridy = row++;
        gbc.weighty = 1.0; 
        sidebar.add(new JLabel(), gbc);

        btnIziet = new JButton("Iziet");
        styleButton(btnIziet, new Color(220, 53, 69));
        gbc.gridy = row++;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        sidebar.add(btnIziet, gbc);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBackground(MAIN_BG);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        JLabel lblHeader = new JLabel("Pasutijumu Vesture");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblHeader.setForeground(new Color(33, 37, 41));
        headerPanel.add(lblHeader, BorderLayout.WEST);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setOpaque(false);
        
        btnDzest = new JButton("Dzest");
        styleButton(btnDzest, new Color(220, 53, 69));
        
        btnSaglabat = new JButton("Saglabat");
        styleButton(btnSaglabat, new Color(25, 135, 84));
        
        btnSkatit = new JButton("Ieladet");
        styleButton(btnSkatit, new Color(13, 202, 240));

        actionPanel.add(btnDzest);
        actionPanel.add(btnSkatit);
        actionPanel.add(btnSaglabat);
        headerPanel.add(actionPanel, BorderLayout.EAST);

        contentPanel.add(headerPanel, BorderLayout.NORTH);

        String[] columns = {"Klients", "Adrese", "Tel", "Pica", "Merce", "Dzeriens", "Piedevas", "Cena"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.GRAY);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, MAIN_BG));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBackground(Color.WHITE);
        
        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(Color.WHITE);
        tableCard.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        tableCard.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tableCard, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        pasutijumi = DarbsArFailu.nolasit();
        atjaunotTabulu();
    }

    private void addSidebarLabel(JPanel panel, GridBagConstraints gbc, int row, String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(173, 181, 189));
        label.setFont(FONT_LABEL);
        gbc.gridy = row;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(label, gbc);
    }

    private void addSidebarComponent(JPanel panel, GridBagConstraints gbc, int row, JComponent comp) {
        comp.setFont(FONT_INPUT);
        comp.setBackground(Color.WHITE);
        if(comp instanceof JComponent) {
            ((JComponent)comp).setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        }
        gbc.gridy = row;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(comp, gbc);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPievienot) {
            registretPasutijumu();
        } else if (e.getSource() == btnSaglabat) {
            DarbsArFailu.saglabat(pasutijumi);
        } else if (e.getSource() == btnSkatit) {
            pasutijumi = DarbsArFailu.nolasit();
            atjaunotTabulu();
            JOptionPane.showMessageDialog(this, "Dati atjaunoti no faila!");
        } else if (e.getSource() == btnIziet) {
            System.exit(0);
        } else if (e.getSource() == btnDzest) {
            dzestPasutijumu();
        }
    }

    private void registretPasutijumu() {
        String izmers = (String) cbIzmers.getSelectedItem();
        String merce = (String) cbMerce.getSelectedItem();
        String dzeriens = (String) cbDzeriens.getSelectedItem();
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

        Pica jaunaPica = new Pica(vards, adrese, talrunis, izmers, piedevas, merce, dzeriens);
        jaunaPica.aprekinatCenu();

        pasutijumi.add(jaunaPica);
        atjaunotTabulu();
        
        for (JCheckBox cb : piedevuCheckboxes) cb.setSelected(false);
        tfVards.setText("");
        tfAdrese.setText("");
        tfTalrunis.setText("");
        cbDzeriens.setSelectedIndex(0);
        
        JOptionPane.showMessageDialog(this, "Pasutijums pievienots! Summa: " + jaunaPica.getCena() + " EUR");
    }

    private void dzestPasutijumu() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Izvelies pasutijumu, ko dzest!", "Kluda", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(this, "Vai tiesam dzest so pasutijumu?", "Apstiprinat", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            pasutijumi.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        }
    }

    private void atjaunotTabulu() {
        tableModel.setRowCount(0);
        for (Pica p : pasutijumi) {
            tableModel.addRow(new Object[]{
                p.getVards(),
                p.getAdrese(),
                p.getTalrunis(),
                p.getIzmers(),
                p.getMerce(),
                p.getDzeriens(),
                p.getPiedevas(),
                String.format("%.2f", p.getCena()) + " EUR"
            });
        }
    }

    private void triggerEasterEgg() {
        new Thread(() -> {
            try {
                InputStream audioSrc = getClass().getResourceAsStream("/skana/stinky.mp3");
                if (audioSrc != null) {
                    InputStream bufferedIn = new BufferedInputStream(audioSrc);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            } catch (Exception ex) {
                System.err.println("Audio Error: " + ex.getMessage());
            }
        }).start();

        URL imgURL = getClass().getResource("/bildes/shower.gif");
        ImageIcon icon = (imgURL != null) ? new ImageIcon(imgURL) : null;
        
        if (icon != null && icon.getImageLoadStatus() != MediaTracker.ERRORED) {
             JOptionPane.showMessageDialog(this, "", "Crime against Pizza", JOptionPane.INFORMATION_MESSAGE, icon);
        } else {
             JOptionPane.showMessageDialog(this, "WHY PINEAPPLE??", "Crime against Pizza", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            new Picerija().setVisible(true);
        });
    }
}