package uk.ac.cam.cl.gfx.tick4.me390;

public class GaussianFilter extends ConvolutionFilter {
    private double sd;

    public GaussianFilter(int size, double stddev) {
        super(size, size);

        sd = stddev;
        kernel = gen_kernel(h, w);
    }

    protected double[][] gen_kernel(int h, int w) {
        assert h == w;

        int size = h;

        if (size % 2 != 1) {
            throw new UnsupportedOperationException("convolution kernels must have odd sizes");
        }

        int middle = size / 2;
        double[][] grid = new double[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[y][x] = gen_kernel_cell(x - middle, y - middle);
            }
        }

        norm_kernel(grid);

        return grid;
    }

    private double gen_kernel_cell(int x, int y) {
        double result = 1 / (2 * Math.PI * sd * sd);
        result *= Math.exp(-(x*x + y*y)/(2 * sd * sd));

        return result;
    }
}
