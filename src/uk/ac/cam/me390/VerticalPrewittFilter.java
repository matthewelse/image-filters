package uk.ac.cam.me390;

public class VerticalPrewittFilter extends BWConvolutionFilter {
    public VerticalPrewittFilter() {
        super(3, 3);

        kernel = new double[][] {
                {-1, 0, 1},
                {-1, 0, 1},
                {-1, 0, 1}
        };
        //norm_kernel(kernel);
    }
}
