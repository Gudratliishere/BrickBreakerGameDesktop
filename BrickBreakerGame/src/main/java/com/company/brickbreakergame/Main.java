package com.company.brickbreakergame;

import com.company.brickbreakergame.game.GamePlay;
import javax.swing.JFrame;

/**
 *
 * @author Dunay Gudratli
 */
public class Main
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePlay gamePlay = new GamePlay();
        frame.add(gamePlay);
        frame.setVisible(true);
    }
}
