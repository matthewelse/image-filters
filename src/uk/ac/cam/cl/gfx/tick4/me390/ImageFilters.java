package uk.ac.cam.cl.gfx.tick4.me390;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFilters {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: ImageFilters <filter> <in file> <out file>");
            System.exit(0);
        }

        // TODO: allow in to be -, representing stdin and if out doesn't exist, output to stdout so that we can create
        // a pipeline

        String filter = args[0];
        String in = args[1];
        String out = args[2];

        Filter f;

        switch (filter) {
            case "gaussian3x3":
                f = new GaussianFilter(3, 1);
                break;
            case "gaussian5x5":
                f = new GaussianFilter(5, 1);
                break;
            case "sobelv":
                f = new VerticalSobelFilter();
                break;
            case "sobelh":
                f = new HorizontalSobelFilter();
                break;
            case "sobel":
                f = new SobelFilter();
                break;
            case "prewitt":
                f = new PrewittFilter();
                break;
            case "prewitth":
                f = new HorizontalPrewittFilter();
                break;
            case "prewittv":
                f = new VerticalPrewittFilter();
                break;
            case "laplace":
                f = new LaplaceFilter();
                break;
            case "median":
                f = new MedianFilter();
                break;
            case "dither":
                f = new OrderedDither();
                break;
            default:
                System.err.println("Invalid choice of filter. Choose from: gaussian3x3, gaussian5x5, sobel[h|v], prewitt[h|v], laplace, median, dither");
                System.exit(1);
                return;
        }

        try {
            BufferedImage in_im = ImageIO.read(new File(in));
            BufferedImage out_im = f.apply(in_im);

            if (out.endsWith("png") || out.endsWith("PNG")) {
                ImageIO.write(out_im, "PNG", new File(out));
            } else {
                ImageIO.write(out_im, "JPEG", new File(out));
            }
        } catch (IOException ioe) {
            System.err.println(String.format("Unable to load file %s from disk.", in));
            ioe.printStackTrace();
        }

    }
}
