package org.forje.netgame.ui;

import org.forje.netgame.engine.Orientation;
import org.forje.netgame.engine.PieceType;
import org.forje.netgame.engine.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class TilePainter extends JComponent
        implements PropertyChangeListener {

    private static final Dimension PREFERRED_SIZE = new Dimension(50,50);

    private final Tile _tile;
    private BufferedImage _image;
    private final double  _xScale;
    private final double _yScale;

    public TilePainter(final Tile tile) {
        _tile = tile;
        _tile.addPropertyChangeListener(this);
        setPreferredSize(PREFERRED_SIZE);
        ClickToRotateMouseListener listener = new ClickToRotateMouseListener(_tile);
        this.addMouseListener(listener);
        PieceType type = _tile.getType();
        try {
            _image = loadImageFile(type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = _image.getWidth();
        int height = _image.getHeight();

        _xScale =  (50 - width) / 50;
        _yScale = (50 - height) / 50;


        System.out.println("width = " + width);
        System.out.println("height = " + height);
        System.out.println("_xScale = " + _xScale);
        System.out.println("_yScale = " + _yScale);

    }

    public void propertyChange(final PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals(Tile.TILE_ORIENTATION_PROPERTY_NAME)) {
            repaint();
        }

    }

    @Override
    public void paint(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Orientation orientation = _tile.getOrientation();
        int ordinal = orientation.ordinal();
        int degrees = (ordinal * 90);
        double radians = Math.toRadians(degrees);

        // this rotation technique sourced from this page
        // http://stackoverflow.com/questions/3405799/how-to-rotate-an-image-gradually-in-swing/3420651#3420651

        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
        g2d.rotate(radians);
        g2d.translate(-_image.getWidth(this) / 2, -_image.getHeight(this) / 2);
        g2d.drawImage(_image, 0, 0, null);

    }
    
    private static class ClickToRotateMouseListener extends MouseAdapter {

        private final Tile _tile;

        public ClickToRotateMouseListener(final Tile tile) {
            _tile = tile;
        }

        @Override
        public void mouseClicked(final MouseEvent e) {

            int button = e.getButton();
            if (MouseEvent.BUTTON1 == button) {
                _tile.rotate();
            }

        }

    }

    private BufferedImage loadImageFile(final PieceType type) throws IOException {
        String name = type.name().toLowerCase();
        String path = "img/" + name + ".png";
        System.out.println("path = " + path);
        return ImageIO.read(ClassLoader.getSystemResource(path));
    }

}
