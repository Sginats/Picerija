import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.sound.sampled.*;
import java.io.*;
public class SakumaEkrans extends JFrame {
	static void play() throws Exception {
	  
	    		AudioInputStream ais = AudioSystem.getAudioInputStream
	    		(SakumaEkrans.class.getResourceAsStream("/sound/start.wav"));

	        start(ais);
	}

    private static void start(AudioInputStream ais) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Ģenerēts serialVersionUID
	 */
	private static final long serialVersionUID = 689807117209968234L;
	private final Color ACCENT_COLOR = new Color(13, 110, 253);
    private final Color TEXT_WHITE = new Color(255, 255, 255);


 URL iconURL = getClass().getResource("/bildes/logo.png"); {
 if (iconURL != null) {
     setIconImage(new ImageIcon(iconURL).getImage());
 }

 GridBagConstraints gbc = new GridBagConstraints();
 gbc.gridx = 0;
 gbc.gridy = 0;
 gbc.insets = new Insets(10, 10, 10, 10);
 gbc.anchor = GridBagConstraints.CENTER;

 
 if (iconURL != null) {
     ImageIcon logoIcon = new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
     JLabel lblLogo = new JLabel(logoIcon);
     gbc.gridy++;
     gbc.insets = new Insets(0, 0, 20, 0);
     add(lblLogo, gbc);
 }
 }
    public SakumaEkrans() {
        setTitle("Picerija - Sakums");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setContentPane(new RainingPanel());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
 
        JLabel lblTitle = new JLabel("MANA PICERIJA");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_WHITE);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(lblTitle, gbc);

        JLabel lblSubtitle = new JLabel("Labākā pica pilsētā!");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(200, 200, 200));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(lblSubtitle, gbc);

        
        JButton btnStart = new JButton("SAKT DARBU");
        styleButton(btnStart, ACCENT_COLOR);
        btnStart.setPreferredSize(new Dimension(200, 50));
        btnStart.addActionListener(e -> {
            new IzvelesEkrans().setVisible(true);
            dispose(); 
        });
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 0);
        add(btnStart, gbc);

        
        JButton btnExit = new JButton("IZIET");
        styleButton(btnExit, new Color(220, 53, 69));
        btnExit.setPreferredSize(new Dimension(200, 50));
        btnExit.addActionListener(e -> System.exit(0));

        gbc.gridy++;
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
        });
    }
}