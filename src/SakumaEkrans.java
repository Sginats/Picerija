import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SakumaEkrans extends JFrame {

    private static final long serialVersionUID = 689807117209968234L;
    private final Color ACCENT_COLOR = new Color(13, 110, 253);
    private final Color TEXT_WHITE = new Color(255, 255, 255);

    public static void Sound() {
        new Thread(() -> {
            try {
                URL url = SakumaEkrans.class.getResource("/bildes/main.wav");
                if (url != null) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public SakumaEkrans() {
        setTitle("Picerija - Sakums");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        RainingPanel panel = new RainingPanel();
        panel.setLayout(new GridBagLayout());
        setContentPane(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        URL iconURL = getClass().getResource("/bildes/logo.png");
        if (iconURL != null) {
            ImageIcon originalIcon = new ImageIcon(iconURL);
            setIconImage(originalIcon.getImage());
            
            ImageIcon logoIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JLabel lblLogo = new JLabel(logoIcon);
            gbc.insets = new Insets(0, 0, 20, 0);
            add(lblLogo, gbc);
            gbc.gridy++;
        }

        JLabel lblTitle = new JLabel("MANA PICERIJA");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_WHITE);
        gbc.insets = new Insets(0, 0, 10, 0);
        add(lblTitle, gbc);
        gbc.gridy++;

        JLabel lblSubtitle = new JLabel("Labākā pica pilsētā!");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(200, 200, 200));
        gbc.insets = new Insets(0, 0, 40, 0);
        add(lblSubtitle, gbc);
        gbc.gridy++;

        JButton btnStart = new JButton("SAKT DARBU");
        styleButton(btnStart, ACCENT_COLOR);
        btnStart.setPreferredSize(new Dimension(200, 50));
        btnStart.addActionListener(e -> {
            new IzvelesEkrans().setVisible(true);
            dispose();
        });
        gbc.insets = new Insets(0, 0, 15, 0);
        add(btnStart, gbc);
        gbc.gridy++;

        JButton btnExit = new JButton("IZIET");
        styleButton(btnExit, new Color(220, 53, 69));
        btnExit.setPreferredSize(new Dimension(200, 50));
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit, gbc);
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
            new SakumaEkrans().setVisible(true);
            Sound();
        });
    }
}