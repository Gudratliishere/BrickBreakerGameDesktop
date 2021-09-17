package com.company.brickbreakergame.properties;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dunay Gudratli
 */
@Getter
@Setter
public class Level
{

    private static final Logger LOG = Logger.getLogger(Level.class.getName());

    private int level;

    private final Path path = Paths.get(System.getProperty("user.home"), "level.dat");

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Level: " + level, 50, 30);
    }

    public void increase()
    {
        level++;
    }

    public void readLevel()
    {
        if (isThereLevelCache())
            try
            {
                byte[] data = Files.readAllBytes(path);
                level = Integer.parseInt(new String(data));
            } catch (IOException | NumberFormatException e)
            {
                LOG.log(java.util.logging.Level.SEVERE, "Error occured when read level from file! \n"
                        + "File path: " + path.toString(), e);
            }
    }

    public void writeLevel()
    {
        try
        {
            Files.write(path, String.valueOf(level).getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e)
        {
            LOG.log(java.util.logging.Level.SEVERE, "Error occured when write level to file! \n"
                    + "File path: " + path.toString(), e);
        }
    }

    private boolean isThereLevelCache()
    {
        File file = new File(path.toString());
        if (!file.exists())
        {
            level = 0;
            return false;
        }

        return true;
    }
}
