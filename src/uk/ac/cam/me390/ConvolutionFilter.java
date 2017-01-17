package uk.ac.cam.me390;

import java.awt.image.BufferedImage;

public abstract class ConvolutionFilter implements Filter {

    protected double[][] kernel;
    protected int w;
    protected int h;

    protected ConvolutionFilter(int h, int w) {
        this.w = w;
        this.h = h;
    }

    @Override
    public BufferedImage apply(BufferedImage in) {
        BufferedImage im = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < in.getHeight(); y++) {
            for (int x = 0; x < in.getWidth(); x++) {
                im.setRGB(x, y, convolve(in, x, y));
            }
        }

        return im;
    }

    private int limit(int x, int low, int high) {
        return Math.max(Math.min(x, high), low);
    }

    protected int convolve(BufferedImage in, int x, int y) {
        int r = 0, g = 0, b = 0;

        for (int kx = 0; kx < w; kx++) {
            for (int ky = 0; ky < h; ky++) {
                int ix = x + kx - (w / 2);
                int iy = y + ky - (h / 2);

                ix = limit(ix, 0, in.getWidth() - 1);
                iy = limit(iy, 0, in.getHeight() - 1);

                int praw = in.getRGB(ix, iy);
                int pr = praw & 0xff;
                int pg = (praw >> 8) & 0xff;
                int pb = (praw >> 16) & 0xff;

                r += pr * kernel[ky][kx];
                g += pg * kernel[ky][kx];
                b += pb * kernel[ky][kx];
            }
        }

        return 0xff000000 | b << 16 | g << 8 | r;
    }

    @Override
    public String toString() {
        String out = "";

        for (int y = 0; y < h; y++) {
            String row = "";

            for (int x = 0; x < w; x++) {
                row += Double.toString(kernel[y][x]);

                if (x != w - 1) {
                    row += " ";
                }
            }

            if (y != h - 1) {
                row += "\r\n";
            }

            out += row;
        }

        return out;
    }

    protected void norm_kernel(double[][] k) {
        double s = 0;

        for (int y = 0; y < k.length; y++) {
            for (int x = 0; x < k[y].length; x++) {
                s += k[y][x];
            }
        }

        for (int y = 0; y < k.length; y++) {
            for (int x = 0; x < k[y].length; x++) {
                k[y][x] /= s;
            }
        }
    }

}
