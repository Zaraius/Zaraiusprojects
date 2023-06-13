import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {
    Random random;
    static int m_screenX = 800;
    static int m_screenY = 800;
    int 

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(m_screenX, m_screenY));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        //this.addKeyListener(new MyKeyAdapter());

        startGame();     
    }

    public void startGame() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // allows everything to move when the game is running
    }
}