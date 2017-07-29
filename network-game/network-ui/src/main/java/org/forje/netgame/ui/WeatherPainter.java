package org.forje.netgame.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Brian on 8/30/15.
 */
class WeatherPainter extends JComponent {

    int _temperature = 65;

    String[] conditions = {"Snow", "Rain", "Cloud", "Sun"};
    BufferedImage snow = null;
    BufferedImage rain = null;
    BufferedImage cloud = null;
    BufferedImage sun = null;
    Color textColor = Color.yellow;
    String condStr = "";
    String feels = "";

    Composite alpha0 = null, alpha1 = null;
    BufferedImage img0 = null, img1 = null;

    public WeatherPainter() {

        setPreferredSize(new Dimension(450, 125));

        addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                Object oldValue = evt.getOldValue();
                Object newValue = evt.getNewValue();

//                System.out.println(propertyName + " : " + oldValue + " -> " + newValue);

            }
        });

    }

    void setTemperature(int temp) {
//        System.out.println("temp = " + temp);
        _temperature = temp;
        repaint();
    }

    void setupWeatherReport() {
        if (_temperature <= 32) {
            setupImages(snow);
            setupText("Snow", null);
        } else if (_temperature <= 40) {
            setupImages(32, 40, snow, rain);
            setupText("Snow", "Rain");
        } else if (_temperature <= 50) {
            setupImages(rain);
            setupText("Rain", null);
        } else if (_temperature <= 58) {
            setupImages(50, 58, rain, cloud);
            setupText("Rain", "Cloud");
        } else if (_temperature <= 65) {
            setupImages(cloud);
            setupText("Cloud", null);
        } else if (_temperature <= 75) {
            setupImages(65, 75, cloud, sun);
            setupText("Cloud", "Sun");
        } else {
            setupImages(sun);
            setupText("Sun", null);
        }
    }

    void setupText(String s1, String s2) {
        if (_temperature <= 32) {
            textColor = Color.blue;
            feels = "Freezing";
        } else if (_temperature <= 50) {
            textColor = Color.green;
            feels = "Cold";
        } else if (_temperature <= 65) {
            textColor = Color.yellow;
            feels = "Cool";
        } else if (_temperature <= 75) {
            textColor = Color.orange;
            feels = "Warm";
        } else {
            textColor = Color.red;
            feels = "Hot";
        }
        condStr = s1;
        if (s2 != null) {
            condStr += "/" + s2;
        }
    }

    void setupImages(BufferedImage i0) {
        alpha0 = null;
        alpha1 = null;
        img0 = i0;
        img1 = null;
    }

    void setupImages(int min, int max, BufferedImage i0, BufferedImage i1) {
        float alpha = (max - _temperature) / (float) (max - min);
        alpha0 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        alpha1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - alpha);
        img0 = i0;
        img1 = i1;

    }

    public void paint(Graphics g) {
        System.out.println("WeatherPainter.paint");
        Graphics2D g2 = (Graphics2D) g;
        Dimension size = getSize();
        System.out.println("size = " + size);
        Composite origComposite;

        setupWeatherReport();

        origComposite = g2.getComposite();
        if (alpha0 != null) g2.setComposite(alpha0);
        g2.drawImage(img0,
                0, 0, size.width, size.height,
                0, 0, img0.getWidth(null), img0.getHeight(null),
                null);
        if (img1 != null) {
            if (alpha1 != null) g2.setComposite(alpha1);
            g2.drawImage(img1,
                    0, 0, size.width, size.height,
                    0, 0, img1.getWidth(null), img1.getHeight(null),
                    null);
        }
        g2.setComposite(origComposite);

        // Freezing, Cold, Cool, Warm, Hot,
        // Blue, Green, Yellow, Orange, Red
        Font font = new Font("Serif", Font.PLAIN, 36);
        g.setFont(font);

        String tempString = feels + " " + _temperature + "F";
        FontRenderContext frc = ((Graphics2D) g).getFontRenderContext();
        Rectangle2D boundsTemp = font.getStringBounds(tempString, frc);
        Rectangle2D boundsCond = font.getStringBounds(condStr, frc);
        int wText = Math.max((int) boundsTemp.getWidth(), (int) boundsCond.getWidth());
        int hText = (int) boundsTemp.getHeight() + (int) boundsCond.getHeight();
        int rX = (size.width - wText) / 2;
        int rY = (size.height - hText) / 2;

        g.setColor(Color.LIGHT_GRAY);
        g2.fillRect(rX, rY, wText, hText);

        g.setColor(textColor);
        int xTextTemp = rX - (int) boundsTemp.getX();
        int yTextTemp = rY - (int) boundsTemp.getY();
        g.drawString(tempString, xTextTemp, yTextTemp);

        int xTextCond = rX - (int) boundsCond.getX();
        int yTextCond = rY - (int) boundsCond.getY() + (int) boundsTemp.getHeight();
        g.drawString(condStr, xTextCond, yTextCond);

    }


} // end WeatherPainterClass
