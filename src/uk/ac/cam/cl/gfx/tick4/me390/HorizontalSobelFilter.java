package uk.ac.cam.cl.gfx.tick4.me390;

public class HorizontalSobelFilter extends BWConvolutionFilter {
    public HorizontalSobelFilter() {
        super(3, 3);

        kernel = new double[][]{
                {-1, -2, -1},
                {0, 0, 0},
                {1, 2, 1}
        };
        //norm_kernel(kernel);
    }
}
