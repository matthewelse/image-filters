package uk.ac.cam.me390;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage apply(BufferedImage in);
}
