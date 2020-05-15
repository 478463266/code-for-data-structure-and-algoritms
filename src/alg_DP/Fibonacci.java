package alg_DP;

public class Fibonacci {

    public static int fib(int n){
        int last, nextLast, resutl = 0;

        if (n <= 1)
            return 1;
        //start from 2
        last = nextLast = 1;
        for (int i = 2; i <= n; i++) {
            resutl = last + nextLast;
            nextLast = last;
            last = resutl;
        }
        return resutl;
    }

    public static void main(String[] args) {
        System.out.println(fib(5));
    }
}
