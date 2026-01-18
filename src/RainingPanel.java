import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RainingPanel extends JPanel implements ActionListener {

    /**
	 * Ģenerēts serialVersionUID
	 */
	private static final long serialVersionUID = -2975799232629468620L;
	private final Color BG_COLOR = new Color(33, 37, 41);
    private final String[] ICONS = {"🍕", "🍟", "🥤", "🌭", "🧂"};
    private ArrayList<Drop> drops;
    private Timer timer;
    private Random random;

    
    private class Drop {
        int x, y;
        int speed;
        String icon;
        int size;

        public Drop(int width) {
            randomize(width, true);
        }

        public void update(int height, int width) {
            y += speed;
            if (y > height) {
                randomize(width, false);
            }
        }

        private void randomize(int width, boolean startRandomY) {
            x = random.nextInt(width);
            y = startRandomY ? random.nextInt(800) - 800 : -50; 
            speed = 2 + random.nextInt(4); 
            icon = ICONS[random.nextInt(ICONS.length)];
            size = 20 + random.nextInt(30); 
        }
    }

    public RainingPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(BG_COLOR);
        random = new Random();
        drops = new ArrayList<>();

        
        for (int i = 0; i < 60; i++) {
            drops.add(new Drop(1280));
        }

       
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

       
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        
        for (Drop drop : drops) {
            g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, drop.size));
            
        
            g2d.setColor(new Color(0, 0, 0, 50));
            g2d.drawString(drop.icon, drop.x + 2, drop.y + 2);

            
            g2d.setColor(Color.WHITE);
            g2d.drawString(drop.icon, drop.x, drop.y);
        }
        
       
        g2d.setColor(new Color(0, 0, 0, 140));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Drop drop : drops) {
            drop.update(getHeight(), getWidth());
        }
        repaint(); // Pārzīmēt ekrānu
    }
}