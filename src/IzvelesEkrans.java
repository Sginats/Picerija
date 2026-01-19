import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class IzvelesEkrans extends JFrame {

    /**
	 * Ģenerēts serialVersionUID
	 */
	private static final long serialVersionUID = 3377661696091457087L;
	private final Color ACCENT_COLOR = new Color(13, 110, 253);
    private final Color GREEN_COLOR = new Color(25, 135, 84);
    private final Color TEXT_WHITE = new Color(255, 255, 255);

    public IzvelesEkrans() {
        setTitle("Izvelies Pasutijuma Veidu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        setContentPane(new RainingPanel());

        URL iconURL = getClass().getResource("/bildes/logo.png");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

       
        JLabel lblTitle = new JLabel("IZVELIES PASUTIJUMA VEIDU");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(TEXT_WHITE);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(lblTitle, gbc);

        
        JButton btnUzVietas = new JButton("UZ VIETAS");
        styleButton(btnUzVietas, ACCENT_COLOR);
        btnUzVietas.setPreferredSize(new Dimension(250, 50));
        btnUzVietas.addActionListener(e -> {
            new Picerija(false).setVisible(true);
            dispose();
        });
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 0);
        add(btnUzVietas, gbc);

        
        JButton btnPasutit = new JButton("PASUTIT (PIEGADE)");
        styleButton(btnPasutit, GREEN_COLOR);
        btnPasutit.setPreferredSize(new Dimension(250, 50));
        btnPasutit.addActionListener(e -> {
            new Picerija(true).setVisible(true);
            dispose();
        });
        gbc.gridy++;
        add(btnPasutit, gbc);

       
        JButton btnIziet = new JButton("ATPAKAL / IZIET");
        styleButton(btnIziet, new Color(108, 117, 125));
        btnIziet.setPreferredSize(new Dimension(250, 50));
        btnIziet.addActionListener(e -> {
            new SakumaEkrans().setVisible(true);
            dispose();
        });
        gbc.gridy++;
        gbc.insets = new Insets(40, 0, 0, 0);
        add(btnIziet, gbc);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
       
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}