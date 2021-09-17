package com.company.brickbreakergame.properties;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dunay Gudratli
 */
@Getter
@Setter
public class Score
{

    private int score = 0;

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 500, 30);
    }

    public void increaseScore(int point)
    {
        score += point * 5;
    }

    public void initializeScore()
    {
        score = 0;
    }
}
