package org.forje.netgame.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class WeatherApp extends JFrame implements ChangeListener {

    WeatherPainter painter;

    public static void main(String[] args) {
        WeatherApp app = new WeatherApp();
        app.initComponents();
        app.setVisible(true);
    }

    public WeatherApp() throws HeadlessException {
        super("Weather App");
        setMinimumSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {


        setLayout(new BorderLayout());


        JPanel p = new JPanel();
        p.add(new JLabel("Temperature:"));
        JSlider tempSlider = new JSlider(20, 100, 65);
        tempSlider.setMinorTickSpacing(5);
        tempSlider.setMajorTickSpacing(20);
        tempSlider.setPaintTicks(true);
        tempSlider.setPaintLabels(true);
        tempSlider.addChangeListener(this);
        p.add(tempSlider);
        add(p, BorderLayout.NORTH);

        painter = new WeatherPainter();
        painter.setBorder(BorderFactory.createEtchedBorder());
        painter.sun = loadImage("sun");
        painter.cloud = loadImage("cloud");
        painter.rain = loadImage("rain");
        painter.snow = loadImage("snow");
        painter.setTemperature(65);
        p.add(painter, BorderLayout.CENTER);

    }

    private BufferedImage loadImage(String name) {
        String imgFileName = "images/weather-"+name+".png";

        URL url = ClassLoader.getSystemResource(imgFileName);
        BufferedImage img = null;
        try {
            img =  ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider)e.getSource();
        int temperature = slider.getValue();
//        System.out.println("temperature = " + temperature);
        painter.setTemperature(temperature);

    }


}
