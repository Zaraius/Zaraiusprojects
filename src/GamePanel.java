import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
/*
stuff i still needa do:
add banner at the top where you can put things including new gamemodes and ability to change colors AND 2 player mode
fix glitch of eating banana = death (i think its fixed)
be able to pause
 */

public class GamePanel extends JPanel implements ActionListener {
    static final int m_screenX = 600;
    static final int m_screenY = 600;
    static final int m_unitSize = 60;
    static final int m_gameUnits = (m_screenX * m_screenY) / m_unitSize;
    static final int totalSquares = (m_screenX / m_unitSize) * (m_screenY / m_unitSize);
    static final int m_delay = 100;
    final int[] x = new int[m_gameUnits];
    final int[] y = new int[m_gameUnits];
    Color[] rainbow = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
    int bodyParts = 3;
    int bananasEaten;
    int bananaX;
    int bananaY;
    String currentDirection;
    LinkedList<String> direction = new LinkedList<String>();
    Timer timer;
    boolean isPaused = false;
    Random random;
    JButton button;
    JLabel label = new JLabel("Game Over");
    JLabel label2 = new JLabel("You Win");
    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

    private final ImageIcon bananaPic = new ImageIcon("banana.png");

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
        direction.add("R");
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
                    g.drawLine(0, i * m_unitSize, m_screenX, i * m_unitSize);
                }
                g.drawImage(bananaPic.getImage(), bananaX, bananaY, m_unitSize + 5, m_unitSize + 5, null);
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

    // creates a new Banana when one is eaten
    public void newBanana() {
        // creates random x and y positions for bananas
        bananaX = random.nextInt((int) m_screenX / m_unitSize) * m_unitSize;
        bananaY = random.nextInt((int) m_screenY / m_unitSize) * m_unitSize;
        // if banana x and y position is the same as a current snake body part, new one is created
        for (int i = 0; i < bodyParts; i++) {
            if ((bananaX == x[i]) && bananaY == y[i]) {
                System.out.println("bad banana");
                if (bodyParts >= totalSquares) {
                    currentState = State.GAME_OVER;
                } else {
                    newBanana();
                }
            }
        }

    }

    //allows the snake to move up down left and right
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        // these are essential to ensure the linkedList for direction has the correct value and isn't empty
        //if (!direction.isEmpty()) {
        currentDirection = direction.pop();
        //}
        if (direction.isEmpty()) {
            direction.add(currentDirection);
        }
        // assigns each case x and y directions to move towards
        switch (currentDirection) {
            case "U":
                y[0] = y[0] - m_unitSize;
                break;
            case "D":
                y[0] = y[0] + m_unitSize;
                break;
            case "L":
                x[0] = x[0] - m_unitSize;
                break;
            case "R":
                x[0] = x[0] + m_unitSize;
                break;
        }

    }

    // This count when the banana is eaten by the head of the snake
    public void checkBanana() {
        if ((x[0] == bananaX) && y[0] == bananaY) {
            bodyParts++;
            bananasEaten++;
            newBanana();
        }
    }

    public void checkCollision() {
        //checks if the snake hits itself
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && y[0] == y[i]) {
                currentState = State.GAME_OVER;
                System.out.println("hit snake");
                //break;
            }
        }

        // Mexico check
        if (x[0] < 0) {
            currentState = State.GAME_OVER;
            System.out.println("hit border");
        } else if (y[0] < 0) {
            currentState = State.GAME_OVER;
            System.out.println("hit border");
        } else if (x[0] >= m_screenX) {
            currentState = State.GAME_OVER;
            System.out.println("hit border");
        } else if (y[0] >= m_screenY) {
            currentState = State.GAME_OVER;
            System.out.println("hit border");
        } else if (currentState == State.GAME_OVER) {
            timer.stop();

        }
    }

    // button setting when game is over
    public void gameOver(Graphics g) {
        label.setForeground(Color.red);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        label.setBounds((m_screenX - metrics.stringWidth("Game Over")) / 3, m_screenY / 3, 300, 200);
        label.setVisible(true);
        add(label);
        restartButton(g);
        // button setting if you win
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

    /*
        public void topBannerSeparator(){
            separator.setForeground(Color.BLUE);
            separator.setBackground(Color.BLACK);
            separator.setAlignmentX(300);
            separator.setAlignmentY(200);
            separator.setBounds(0, 0, m_screenX/3, m_screenY/3);
            separator.setName("TOP BANNER");
            separator.setVisible(true);
        }

     */
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
        direction.clear();
        direction.add("R");
        button.setVisible(false);
        move();
        currentState = State.RUNNING;
        repaint();
        remove(label);
        remove(label2);
        startGame();
    }

    // allows you to pause the game... still in development
    public void pauseGame() {

        if (isPaused) {
            timer.stop();
            currentState = State.WAITING;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // allows everything to move when the game is running
        if (currentState == State.RUNNING) {
            move();
            checkBanana();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        // detects when a key is pressed
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (!(direction.getLast().equals("R")))
                        direction.add("L");
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if (!(direction.getLast().equals("L")))
                        direction.add("R");

                    break;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    if (!(direction.getLast().equals("D")))
                        direction.add("U");

                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if (!(direction.getLast().equals("U")))
                        direction.add("D");

                    break;

            }
        }
    }
}
