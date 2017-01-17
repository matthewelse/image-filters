package uk.ac.cam.cl.gfx.tick4.me390;

import java.awt.image.BufferedImage;

public class PrewittFilter implements Filter {
    private VerticalPrewittFilter v;
    private HorizontalPrewittFilter h;

    public PrewittFilter() {
        v = new VerticalPrewittFilter();
        h = new HorizontalPrewittFilter();
    }

    @Override
    public BufferedImage apply(BufferedImage im) {
        BufferedImage a = v.apply(im);
        BufferedImage b = h.apply(im);

        return average(a, b);
    }

    private BufferedImage average(BufferedImage ima, BufferedImage imb) {
        assert ima.getHeight() == imb.getHeight();
        assert ima.getWidth() == imb.getWidth();

        BufferedImage out = new BufferedImage(ima.getWidth(), ima.getHeight(), ima.getType());

        for (int y = 0; y < ima.getHeight(); y++) {
            for (int x = 0; x < ima.getWidth(); x++) {
                int ar = ima.getRGB(x, y) & 0xff;
                int br = imb.getRGB(x, y) & 0xff;

                int r = (int)Math.sqrt((double)ar*ar + (double)br*br);

                out.setRGB(x, y, 0xff000000 | r << 16 | r << 8 | r);
            }
        }

        return out;
    }
}
