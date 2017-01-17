package uk.ac.cam.cl.gfx.tick4.me390;

public class VerticalSobelFilter extends BWConvolutionFilter {
    public VerticalSobelFilter() {
        super(3, 3);

        kernel = new double[][] {
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };
        //norm_kernel(kernel);
    }
}
