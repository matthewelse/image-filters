package uk.ac.cam.me390;

/**
 * Created by matt on 15/01/2017.
 */
public class LaplaceFilter extends BWConvolutionFilter {
    public LaplaceFilter() {
        super(3, 3);

        kernel = new double[][] {
                {0, 1, 0},
                {1, -4, 1},
                {0, 1, 0}
        };
        //norm_kernel(kernel);
    }
}
