import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.logging.Handler;
/*
git init
git remote add origin [master or main]
git add .
git commit -m "Snake is born"
git push origin [master or main]
comment
 */

public class GamePanel extends JPanel implements ActionListener {
    static final int m_screenX = 600;
    static final int m_screenY = 600;
    static final int m_unitSize = 40;
    static final int m_gameUnits = (m_screenX * m_screenY) / m_unitSize;
    static final int totalSquares = (m_screenX / m_unitSize) * (m_screenY / m_unitSize);
    static final int m_delay = 150;
    final int[] x = new int[m_gameUnits];
    final int[] y = new int[m_gameUnits];
    Color[] rainbow = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
    int j = 0;
    int bodyParts = 3;
    int bananasEaten;
    int bananasX;
    int bananasY;
    char direction = 'R';
    Timer timer;
    Random random;
    JButton button;
    JLabel label = new JLabel("Game Over");
    JLabel label2 = new JLabel("You Win");

    private ImageIcon bananaPic = new ImageIcon("please_work.png");

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(m_screenX, m_screenY));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        label.setVisible(false);
        newBanana();
        currentState = State.RUNNING;
        timer = new Timer(m_delay, this);
        timer.start();
        bananasEaten = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    enum State {RUNNING, GAME_OVER, WAITING}

    private State currentState = State.RUNNING;

    public void draw(Graphics g) {
        switch (currentState) {
            case RUNNING:
                for (int i = 0; i < m_screenY / m_unitSize; i++) {
                    g.drawLine(i * m_unitSize, 0, i * m_unitSize, m_screenY);
                    g.drawLine(0, i * m_unitSize, m_screenY, i * m_unitSize);
                }
                g.drawImage(bananaPic.getImage(), bananasX, bananasY, m_unitSize + 5, m_unitSize + 5, null);
                for (int i = 0; i < bodyParts; i++) {
                    g.setColor(rainbow[i % rainbow.length]);
                    g.fillRect(x[i], y[i], m_unitSize, m_unitSize);
                }
                break;
            case GAME_OVER:
                gameOver(g);
                bodyParts = 3;
                currentState = State.WAITING;
                timer.stop();
                break;
            case WAITING:
                break;
        }
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + bananasEaten, (m_screenX - metrics.stringWidth("Score: " + bananasEaten)) / 2, g.getFont().getSize());
// def needa put stuff here
    }

    public void newBanana() {
        bananasX = random.nextInt((int) m_screenX / m_unitSize) * m_unitSize;
        bananasY = random.nextInt((int) m_screenY / m_unitSize) * m_unitSize;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - m_unitSize;
                break;
            case 'D':
                y[0] = y[0] + m_unitSize;
                break;
            case 'L':
                x[0] = x[0] - m_unitSize;
                break;
            case 'R':
                x[0] = x[0] + m_unitSize;
                break;
        }

    }

    public void checkBanana() {
        if ((x[0] == bananasX) && y[0] == bananasY) {
            bodyParts++;
            bananasEaten++;
            newBanana();
        }
    }

    public void checkCollision() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && y[0] == y[i]) {
                currentState = State.GAME_OVER;
                break;
            }
        }

        // Mexico check
        if (x[0] < 0) {
            currentState = State.GAME_OVER;
        } else if (y[0] < 0) {
            currentState = State.GAME_OVER;
        } else if (x[0] >= m_screenX) {
            currentState = State.GAME_OVER;
        } else if (y[0] >= m_screenY) {
            currentState = State.GAME_OVER;
        } else if (currentState == State.GAME_OVER) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        label.setForeground(Color.red);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        label.setBounds((m_screenX - metrics.stringWidth("Game Over")) / 3, m_screenY / 3, 300, 200);
        label.setVisible(true);
        add(label);
        restartButton(g);

        if (bodyParts >= totalSquares) {
            remove(label);
            label2.setForeground(Color.red);
            label2.setFont(new Font("Monospaced", Font.BOLD, 80));
            label2.setBounds((m_screenX - metrics.stringWidth("You Win!")) / 4, m_screenY / 3, 350, 100);
            label2.setVisible(true);
            add(label2);
            System.out.println("You Win");
        }
    }


    // make the button APPEAR when gameOver
    public void restartButton(Graphics g) {
//        g.setColor(Color.RED);
//        g.setFont(new Font("Serif", Font.BOLD, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
//        g.drawString("Restart", (m_screenX - metrics.stringWidth("Restart"))/2, (int) (m_screenY/ 1.5));
        button = new JButton("Restart");
        button.addActionListener(this);
        button.setBounds((m_screenX - metrics.stringWidth("Restart")) / 3, (int) (m_screenY / 1.5), 200, 200);
        button.setBackground(Color.BLACK);
        button.setFont(new Font("Serif", Font.BOLD, 45));
        button.setForeground(Color.RED);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(e -> restartGame());
        add(button);
    }

    // When restartButton is pressed start the game over again
    public void restartGame() {


//        button.setEnabled(true);
        for (int i = bodyParts; i >= 0; i--) {
            x[i] = i;
            y[i] = 0;
        }
        direction = 'R';
        button.setVisible(false);
        move();
        currentState = State.RUNNING;
        repaint();
        remove(label);
        remove(label2);
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentState == State.RUNNING) {
            move();
            checkBanana();
            checkCollision();
        } else {
            // what do you do when the button is pressed?
//            button.setEnabled(true);
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
