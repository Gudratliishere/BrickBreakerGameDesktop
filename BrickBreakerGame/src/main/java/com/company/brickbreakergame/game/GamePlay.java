package com.company.brickbreakergame.game;

import com.company.brickbreakergame.components.Ball;
import com.company.brickbreakergame.components.Brick;
import com.company.brickbreakergame.components.Paddle;
import com.company.brickbreakergame.intersections.IntersectionBallBrick;
import com.company.brickbreakergame.intersections.IntersectionBallPaddle;
import com.company.brickbreakergame.properties.Level;
import com.company.brickbreakergame.properties.Score;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Dunay Gudratli
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener
{

    private static final Logger LOG = Logger.getLogger(GamePlay.class.getName());

    private boolean play = false;

    public static Timer timer;
    private final int delay = 8;

    public static final Brick brick = new Brick();
    public static final Ball ball = new Ball();
    private static final Paddle paddle = new Paddle();
    private static final Score score = new Score();
    private static final Level level = new Level();
    private static final IntersectionBallPaddle intersectionBallPaddle = new IntersectionBallPaddle();
    private static final IntersectionBallBrick intersectionBallBrick = new IntersectionBallBrick();

    public GamePlay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        intersectionBallPaddle.setBall(ball);
        intersectionBallPaddle.setPaddle(paddle);
        intersectionBallBrick.setBall(ball);
        intersectionBallBrick.setBrick(brick);
        intersectionBallBrick.setScore(score);

        timer = new Timer(delay, this);
        timer.start();

        startGame();
    }

    @Override
    public void paint(Graphics g)
    {
        //Bakcground
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);

        brick.draw((Graphics2D) g);
        score.draw(g);
        level.draw(g);

        //Borders
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(682, 0, 3, 592);

        paddle.draw(g);
        ball.draw(g);

        if (brick.isGameFinished())
        {
            level.increase();
            level.writeLevel();

            play = false;

            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You won!", 250, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press enter to restart.", 230, 350);

            timer.stop();
        }

        if (ball.isGameOver())
        {
            play = false;

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over. Scores: " + score.getScore(), 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press enter to restart.", 230, 350);
        }

        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            play = true;
            paddle.moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            play = true;
            paddle.moveLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            if (!play)
            {
                timer.start();
                restartGame();
            }

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            play = !play;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        timer.start();

        if (play)
        {
            if (intersectionBallPaddle.intersectsBallPaddle())
                ball.increasePowerOfBall();

            intersectionBallBrick.intersectsBallBrick();

            ball.moveBall();
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    private void restartGame()
    {
        play = true;
        startGame();
    }

    private void startGame()
    {
        level.readLevel();

        ball.initializePosition();
        ball.initializePower();

        paddle.initializePosition();
        score.initializeScore();

        brick.initializeRowAndColumn(level.getLevel());
        brick.initializeTotalBricks();
        brick.initializeMinAndMaxValueOfBricks(level.getLevel());
        brick.initializeMap();
        brick.fillMap();

        repaint();
    }

}
