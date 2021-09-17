package com.company.brickbreakergame.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dunay Gudratli
 */
@Setter
@Getter
public class Paddle
{

    private int paddleX = 310;

    public void draw(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect(paddleX, 550, 100, 8);
    }

    public void moveRight()
    {
        if (paddleX >= 570)
            paddleX = 570;

        paddleX += 20;
    }

    public void moveLeft()
    {
        if (paddleX < 10)
            paddleX = 10;

        paddleX -= 20;
    }

    public void initializePosition()
    {
        paddleX = 310;
    }

    public Rectangle getPaddleRectangle()
    {
        return new Rectangle(paddleX, 550, 100, 1);
    }
}
