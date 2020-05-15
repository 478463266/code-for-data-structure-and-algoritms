package alg_greedy;

//https://www.cnblogs.com/lvbcy/p/5958620.html
//任务调度-最小惩罚算法
//https://blog.csdn.net/hxpjava1/article/details/21815959/

/**
 * 题解：根据罚时的长短进行排序，将罚时时间长的放在前面。
 * 开一个数组作为时间槽，记录每个单位时间是否有任务安排。
 * 若截止日期相同，根据时间长短判断哪个优先，
 * 尽量将任务安排在截至时间完成，否则放在放在前一天，
 *以此类推。若在截至时间前都有任务安排，则舍去，增加到罚时中。
 */

public class TaskSchedule {

    public static int taskSchedule(int[] deadline, int[] penalty){
        if (deadline.length != penalty.length)
            return -1;
        int n = deadline.length;
        int result = 0;

        /*按照时间进行排序，截止时间也随罚时排序*/
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (penalty[j] < penalty[j + 1]){
                    int t = penalty[j];
                    penalty[j] = penalty[j + 1];
                    penalty[j + 1] = t;

                    t = deadline[j];
                    deadline[j] = deadline[j + 1];
                    deadline[j + 1] = t;
                }
            }
        }
        /*done[]为时间槽，表示已有任务安排，j表示第几个单位时间*/
        boolean[] done = new boolean[n];
        for (int i = 0; i < n; i++) {
            int j;
            for (j = deadline[i]; ; j--){
                if (done[j] == false){
                    //没有占用
                    done[j] = true;
                    break;
                }
            }
            /*若都有安排，则将其加入总罚时时间sum*/
            if (j == 0){
                result += penalty[i]; 
            }
        }
        return result;
    }
}
