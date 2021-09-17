package com.company.brickbreakergame.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dunay Gudratli
 */
@Getter
@Setter
public class Brick
{

    private int[][] map;
    private int brickWidth;
    private int brickHeight;
    private int totalBricks;
    private int row;
    private int column;
    private int minValueOfBrick;
    private int maxValueOfBrick;

    public void initializeMap()
    {
        map = new int[row][column];

        brickWidth = 540 / column;
        brickHeight = 150 / row;
    }

    public void initializeRowAndColumn(int level)
    {
        row = 3 + level / 100;
        if (level % 100 != 0 || level == 0)
            column = 7 + level / 50;
    }

    public void initializeTotalBricks()
    {
        totalBricks = row * column;
    }

    public void initializeMinAndMaxValueOfBricks(int level)
    {
        maxValueOfBrick = 10 + (level / 10) * 5;
        minValueOfBrick = maxValueOfBrick / 5;
    }

    public void fillMap()
    {
        for (int rowBrick = 0; rowBrick < map.length; rowBrick++)
            for (int columnBrick = 0; columnBrick < map[rowBrick].length; columnBrick++)
                map[rowBrick][columnBrick] = ThreadLocalRandom.current().nextInt(minValueOfBrick, maxValueOfBrick);
    }

    public void draw(Graphics2D g)
    {
        for (int rowBrick = 0; rowBrick < map.length; rowBrick++)
            for (int columnBrick = 0; columnBrick < map[rowBrick].length; columnBrick++)
                if (map[rowBrick][columnBrick] > 0)
                {
                    drawBrick(g, rowBrick, columnBrick);
                    drawValueOfBrick(g, rowBrick, columnBrick);
                    drawBorderOfBrick(g, rowBrick, columnBrick);
                }

    }

    private void drawBrick(Graphics2D g, int row, int column)
    {
        g.setColor(Color.WHITE);
        g.fillRect(column * brickWidth + 80, row * brickHeight + 50, brickWidth, brickHeight);
    }

    private void drawValueOfBrick(Graphics2D g, int row, int column)
    {
        g.setColor(Color.BLUE);
        g.setFont(new Font("serif", Font.BOLD, brickWidth / 3));
        g.drawString(String.valueOf(map[row][column]), column * brickWidth + 80 + brickWidth / 3,
                (int) (row * brickHeight + 50 + brickHeight / 1.5));
    }

    private void drawBorderOfBrick(Graphics2D g, int row, int column)
    {
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawRect(column * brickWidth + 80, row * brickHeight + 50, brickWidth, brickHeight);
    }

    public int decreaseBrickValue(int value, int row, int column)
    {
        int difference = map[row][column] - value;
        int point = map[row][column];
        map[row][column] -= value;
        return (difference >= 0) ? value : point;
    }

    public void decreaseTotalBricks()
    {
        totalBricks--;
    }

    public boolean isGameFinished()
    {
        return totalBricks == 0;
    }

    public Rectangle getBrickRectangle(int row, int column)
    {
        int brickX = column * brickWidth + 80;
        int brickY = row * brickHeight + 50;

        return new Rectangle(brickX, brickY, brickWidth, brickHeight);
    }
}
