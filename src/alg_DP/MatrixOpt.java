package alg_DP;

//ref:https://blog.csdn.net/weixin_43896318/article/details/104506577
public class MatrixOpt {

    private static final long INFINITY = Long.MAX_VALUE;

    /**
     * Compute optimal ordering of matrix multiplication.
     * c contains the number of columns for each of the n matrices.
     * c[0] is the number of rows in matrix 1.
     * The minimum number of multiplications is left in m[1][n].
     * Actual ordering is computed via another procedure using lastChange.
     * m and lastChange are indexed starting at 1, instead of 0.
     * Note: Entries below main diagonals of m and lastChange are meaningless and uninitialized.
     */

    public static void matrixOpt(int[] c, long[][]m ,int[][] lastChagge){
        int n = c.length - 1;
        for (int i = 1; i <=  n; i++){
            m[i][i] = 0;
        }

        for (int k = 1; k < n; k++){ //逐渐间距变大,// k is (right - left)
            for (int left = 1; left <= n - k; left++) {
                //for each position
                int right = left + k;
                m[left][right] = Integer.MAX_VALUE;

                for (int i = left; i < right; i++) {
                    long thiscost = m[left][i] + m[i + 1][right] + c[left - 1] * c[i] * c[right];
                    if (thiscost < m[left][right]){
                        m[left][right] = thiscost;
                        lastChagge[left][right] = i;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] c = {50, 10, 40, 30, 5};
        int[] c2 = {20, 2, 30, 12, 8};
        long[][] m = new long[5][5];
        int[][] lastChange = new int[5][5];
        matrixOpt(c2, m, lastChange);
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(lastChange[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
