package simulation;

import simulation.goublets.Goublet;
import simulation.glorzo.Glorzo;

import javax.swing.*;
import java.awt.*;

public class Simulation extends JPanel {
    private final java.util.List<Goublet> GOUBLETS = new java.util.ArrayList<>();

    public Simulation() {
        Glorzo WORLD = new Glorzo();

        // Create and add goublets to the world
        for (int i = 0; i < 100; i++) {
            Goublet goublet = new Goublet();
            WORLD.addGoublet(goublet);
            this.GOUBLETS.add(goublet);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the world, and update the position
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());  // Draw background

        for (Goublet goublet : this.GOUBLETS) {
            goublet.draw(g);
        }
    }

    public void nextDay() {
        for (Goublet goublet : this.GOUBLETS) {
            goublet.move();
        }
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Goublet World");
        Simulation simulation = new Simulation();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Glorzo.WIDTH, Glorzo.HEIGHT);
        frame.add(simulation);
        frame.setVisible(true);

        // Simulate movement by updating the position
        for (int i = 0; i < 100000; i++) {
            simulation.nextDay();  // Move goublets
            try {
                Thread.sleep(100);  // Delay to simulate movement speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}