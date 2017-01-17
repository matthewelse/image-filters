package uk.ac.cam.cl.gfx.tick4.me390;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MedianFilter implements Filter {
    int window_w;
    int window_h;

    public MedianFilter() {
        window_w = 3;
        window_h = 3;
    }

    public MedianFilter(int w, int h) {
        window_w = w;
        window_h = h;
    }

    private int limit(int x, int low, int high) {
        return Math.max(Math.min(x, high), low);
    }

    public int median(BufferedImage in, int cx, int cy) {
        int mid_y = window_h / 2;
        int mid_x = window_w / 2;

        int[] r_buffer = new int[window_w * window_h];
        int[] g_buffer = new int[window_w * window_h];
        int[] b_buffer = new int[window_w * window_h];

        int i = 0;

        for (int x = 0; x < window_w; x++) {
            for (int y = 0; y < window_h; y++) {
                int px = limit(x - mid_x + cx, 0, in.getWidth() - 1);
                int py = limit(y - mid_y + cy, 0, in.getHeight() - 1);

                int colour = in.getRGB(px, py);

                int r = colour & 0xff;
                int g = (colour >> 8) & 0xff;
                int b = (colour >> 16) & 0xff;

                r_buffer[i] = r;
                g_buffer[i] = g;
                b_buffer[i] = b;
                i++;
            }
        }

        Arrays.sort(r_buffer);
        Arrays.sort(g_buffer);
        Arrays.sort(b_buffer);

        int r = r_buffer[r_buffer.length / 2];
        int g = g_buffer[g_buffer.length / 2];
        int b = b_buffer[b_buffer.length / 2];

        return r | g << 8 | b << 16;
    }

    @Override
    public BufferedImage apply(BufferedImage in) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());

        for (int x = 0; x < in.getWidth(); x++) {
            for (int y = 0; y < in.getHeight(); y++) {
                out.setRGB(x, y, median(in, x, y));
            }
        }

        return out;
    }

}
