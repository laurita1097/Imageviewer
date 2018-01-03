package ui;

import model.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image currentImage;

    @Override
    public void paint(Graphics g) {
        if (currentImage == null) return;
        g.drawImage(imageOf(currentImage), 0, 0, null);
    }

    @Override
    public Image current() {
        return currentImage;
    }

    @Override
    public void show(Image image) {
        this.currentImage = image;
        this.repaint();
    }

    private BufferedImage imageOf(Image i) {
        try {
            return ImageIO.read(i.stream());
        }catch(java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
