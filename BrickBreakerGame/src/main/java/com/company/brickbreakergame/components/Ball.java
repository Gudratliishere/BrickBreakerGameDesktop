package com.company.brickbreakergame.components;

import com.company.brickbreakergame.game.Sound;
import com.company.brickbreakergame.properties.Score;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dunay Gudratli
 */
@Getter
@Setter
public class Ball
{

    private int ballPositionX;
    private int ballPositionY;
    private int ballXDirection;
    private int ballYDirection;
    private int powerOfBall;

    public void draw(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillOval(ballPositionX, ballPositionY, 20, 20);
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, 13 - (String.valueOf(powerOfBall).length()) * 2));
        g.drawString(String.valueOf(powerOfBall), ballPositionX + 7, ballPositionY + 14);
    }

    public void moveBall()
    {
        ballPositionX += ballXDirection;
        ballPositionY += ballYDirection;

        if (ballPositionX < 0 || ballPositionX > 670)
            ballXDirection = -ballXDirection;

        if (ballPositionY < 0)
            ballYDirection = -ballYDirection;
    }

    public void initializePosition()
    {
        ballPositionX = ThreadLocalRandom.current().nextInt(50, 650);
        ballPositionY = ThreadLocalRandom.current().nextInt(250, 450);

        int[] ballXDirs = new int[]
        {
            -2, -1, 1, 2
        };
        Map<Integer, Integer> ballYDirs = new HashMap<>();
        ballYDirs.put(-2, -1);
        ballYDirs.put(-1, -2);
        ballYDirs.put(1, -2);
        ballYDirs.put(2, -1);

        ballXDirection = ballXDirs[ThreadLocalRandom.current().nextInt(0, 4)];
        ballYDirection = ballYDirs.get(ballXDirection);
    }

    public void initializePower()
    {
        powerOfBall = 1;
    }

    public boolean isGameOver()
    {
        return ballPositionY > 570;
    }

    public Rectangle getBallRectangle()
    {
        return new Rectangle(ballPositionX, ballPositionY, 20, 20);
    }

    public void increasePowerOfBall()
    {
        powerOfBall++;
    }

    public void turnBallXDirection()
    {
        ballXDirection = -ballXDirection;
    }

    public void turnBallYDirection()
    {
        ballYDirection = -ballYDirection;
    }
}
