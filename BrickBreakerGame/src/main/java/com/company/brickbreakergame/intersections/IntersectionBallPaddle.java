package com.company.brickbreakergame.intersections;

import com.company.brickbreakergame.components.Ball;
import com.company.brickbreakergame.components.Paddle;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dunay Gudratli
 */
@Getter
@Setter
public class IntersectionBallPaddle
{

    private Ball ball;
    private Paddle paddle;

    public boolean intersectsBallPaddle()
    {
        if (ball.getBallRectangle().intersects(paddle.getPaddleRectangle()))
        {
            changeBallDirection();
            return true;
        }

        return false;
    }

    private void changeBallDirection()
    {
        if ((ball.getBallPositionX() >= paddle.getPaddleX() - 20 && ball.getBallPositionX() <= paddle.getPaddleX() + 12)
                || (ball.getBallPositionX() >= paddle.getPaddleX() + 64 && ball.getBallPositionX() <= paddle.getPaddleX() + 100))
            bendDirectionSide();
        else
            bendDirectionStraight();

    }

    private void bendDirectionStraight()
    {
        ball.setBallYDirection(-2);
        switch (ball.getBallXDirection())
        {
            case -2:
                ball.setBallXDirection(-1);
                break;
            case 2:
                ball.setBallXDirection(1);
                break;
            default:
                break;
        }
    }

    private void bendDirectionSide()
    {
        ball.setBallYDirection(-1);
        switch (ball.getBallXDirection())
        {
            case -1:
                ball.setBallXDirection(-2);
                break;
            case 1:
                ball.setBallXDirection(2);
                break;
            default:
                break;
        }
    }
}
