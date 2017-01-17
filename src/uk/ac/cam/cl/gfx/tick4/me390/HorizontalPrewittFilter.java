package uk.ac.cam.cl.gfx.tick4.me390;

public class HorizontalPrewittFilter extends BWConvolutionFilter {
    public HorizontalPrewittFilter() {
        super(3, 3);

        kernel = new double[][]{
                {-1, -1, -1},
                {0, 0, 0},
                {1, 1, 1}
        };
        //norm_kernel(kernel);
    }
}
