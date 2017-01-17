package uk.ac.cam.me390;

import java.awt.image.BufferedImage;

/**
 * Created by matt on 14/01/2017.
 */
public abstract class BWConvolutionFilter implements Filter {

    private int w;
    private int h;

    protected double[][] kernel;

    public BWConvolutionFilter(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public BufferedImage apply(BufferedImage in) {
        BufferedImage im = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

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

    /**
     * The sobel filter only really makes sense in B&W, so modify the convolution method slightly
     */
    protected int convolve(BufferedImage in, int x, int y) {
        int r = 0;

        for (int kx = 0; kx < w; kx++) {
            for (int ky = 0; ky < h; ky++) {
                int ix = x + kx - (w / 2);
                int iy = y + ky - (h / 2);

                ix = limit(ix, 0, in.getWidth() - 1);
                iy = limit(iy, 0, in.getHeight() - 1);

                int praw = in.getRGB(ix, iy);
                int p = praw & 0xff;
                p += (praw >> 8) & 0xff;
                p += (praw >> 16) & 0xff;

                r += (p / 3) * kernel[ky][kx];
            }
        }

        return r;
    }
}
