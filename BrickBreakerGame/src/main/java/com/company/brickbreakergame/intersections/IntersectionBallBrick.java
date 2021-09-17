package com.company.brickbreakergame.intersections;

import com.company.brickbreakergame.components.Ball;
import com.company.brickbreakergame.components.Brick;
import com.company.brickbreakergame.game.Sound;
import com.company.brickbreakergame.properties.Score;
import java.awt.Rectangle;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dunay Gudratli
 */
public class IntersectionBallBrick
{

    @Getter
    @Setter
    private Ball ball;
    @Getter
    @Setter
    private Brick brick;
    @Getter
    @Setter
    private Score score;

    private Rectangle ballRectangle;
    @Getter
    private Rectangle brickRectangle;

    private int[][] map;
    private int row;
    private int column;

    public boolean intersectsBallBrick()
    {
        map = brick.getMap();

        if (!brickExistsAndIntersectsBall())
            return false;

        Sound.playBrickBreaking();
        int point = decreaseBrickValue();
        score.increaseScore(point);
        breakBrick();
        changeBallDirection();

        return true;
    }

    private int decreaseBrickValue()
    {
        return brick.decreaseBrickValue(ball.getPowerOfBall(), row, column);
    }

    private void breakBrick()
    {
        if (isBrickBreaked())
            brick.decreaseTotalBricks();
    }

    private boolean isBrickBreaked()
    {
        return map[row][column] <= 0;
    }

    private boolean brickExistsAndIntersectsBall()
    {
        for (row = 0; row < map.length; row++)
            for (column = 0; column < map[0].length; column++)
                if (map[row][column] > 0)
                    if (intersects())
                        return true;

        return false;
    }

    private boolean intersects()
    {
        ballRectangle = ball.getBallRectangle();
        brickRectangle = brick.getBrickRectangle(row, column);

        return ballRectangle.intersects(brickRectangle);
    }

    private void changeBallDirection()
    {
        if ((ball.getBallPositionX() + 18 <= brickRectangle.x)
                || (ball.getBallPositionX() + 1 >= brickRectangle.x + brickRectangle.width))
            ball.turnBallXDirection();
        else
            ball.turnBallYDirection();
    }
}
