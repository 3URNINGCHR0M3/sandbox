package org.forje.netgame.ui;

import org.forje.netgame.engine.Tile;
import org.forje.netgame.solver.strategies.PerimeterStrategy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Brian on 8/6/15.
 */
public class Frame extends JFrame{

    private final RandomTable _table;

    public Frame() throws HeadlessException {
        super("Network Game");

        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _table = new RandomTable(10);

        GridLayout layout = new GridLayout(10, 10);
        setLayout(layout);

        for (int i=0; i < 10; i++) {
            for (int j=0; j < 10; j++) {
                Tile tile = _table.getTile(i, j);
                TilePainter painter = new TilePainter(tile);
                add(painter);
            }

        }



        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("Waiting to sort.");

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Done waiting");

                PerimeterStrategy perimeterStrategy = new PerimeterStrategy();
                perimeterStrategy.apply(_table);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

}
