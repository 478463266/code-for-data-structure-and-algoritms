package alg_DP;

//ref: https://www.cnblogs.com/liuzhen1995/archive/2017/02/26/6452519.html
//考虑虚拟节点：https://blog.csdn.net/xiajun07061225/article/details/8088784?utm_medium=distribute.pc_relevant.none-task-blog-OPENSEARCH-1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-OPENSEARCH-1


//不考虑虚拟节点
public class BestBST {
    /*
     * 参数P：表示1~n个节点的查找概率。其中P[0] = 0，无意义
     * 函数功能：返回在最优BST中查找的平均比较次数主表C[][]，以及最优BST中子树的根表R
     */

    public static void getBestBST(double[] P){
        int lenP = P.length;

        //保存最优BST的成功查找的平均比较次数
        double[][] C = new double[lenP + 1][lenP];
        //保存最优BST中子树的根表R
        int[][] R = new int[lenP + 1][lenP];
        //初始化
        for (int i = 1; i < lenP; i++) {
            C[i][i] = P[i];
            R[i][i] = i;
        }

        for (int d = 1; d < lenP - 1; d++){//间隔
            for (int i = 1; i < lenP - d; i++) {//第i个的开头
                int j = i + d;//第i个的结尾
                double minval = Double.MAX_VALUE;
                int kmin = 0;
                for (int k = i; k <= j ; k++) {//第i个的中间遍历
                    if (C[i][k - 1] + C[k + 1][j] < minval){
                        minval = C[i][k - 1] + C[k + 1][j];
                        kmin = k;
                    }
                }
                R[i][j] = kmin;
                double sum = P[i];
                for (int s = i + 1; s <= j ; s++) {
                    sum += P[s];
                }
                C[i][j] = minval + sum;

            }
        }

        System.out.println("在最优BST中查找的平均比较次数依次为：");
        for(int i = 1;i < C.length;i++) {
            for(int j = 0;j < C[0].length;j++)
                System.out.printf("%.2f\t", C[i][j]);
            System.out.println();
        }

        System.out.println("在最优BST中子树的根表R为：");
        for(int i = 1;i < R.length;i++) {
            for(int j = 0;j < R[0].length;j++)
                System.out.print(R[i][j]+"\t");
            System.out.println();
        }

    }

    public static void main(String[] args) {
        BestBST test = new BestBST();
        //double[] P = {0,0.1,0.2,0.4,0.3};
        double[] P2 = {0, 0.22, 0.18, 0.20, 0.05, 0.25, 0.02,
        0.08};
        //test.getBestBST(P);
        test.getBestBST(P2);
    }


}
