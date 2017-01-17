package uk.ac.cam.cl.gfx.tick4.me390;

import java.awt.image.BufferedImage;

public class OrderedDither implements Filter {

    int[][] THRESHOLD_MATRIX = {{0, 8, 2, 10}, {12 , 4 , 14 , 6 }, {3 , 11 , 1 , 9 }, {15 , 7 , 13 , 5 }};

    private int dither(BufferedImage in, int x, int y) {
        int rgb = in.getRGB(x, y);

        int r = (rgb & 0xff) / 16;
        int g = ((rgb >> 8) & 0xff) / 16;
        int b = ((rgb >> 16) & 0xff) / 16;

        r = (r < THRESHOLD_MATRIX[x % 4][y % 4]) ? 0 : 255;
        g = (g < THRESHOLD_MATRIX[x % 4][y % 4]) ? 0 : 255;
        b = (b < THRESHOLD_MATRIX[x % 4][y % 4]) ? 0 : 255;

        return (r | (g << 8) | (b << 16));
    }

    @Override
    public BufferedImage apply(BufferedImage in) {
        // restrict the output image to 3 colours (full red/full green/full blue)
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());

        for (int x = 0; x < in.getWidth(); x++) {
            for (int y = 0; y < in.getHeight(); y++) {
                int rgb = dither(in, x, y);
                out.setRGB(x, y, rgb);
            }
        }

        return out;
    }

}
