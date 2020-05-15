package alg_greedy;

import org.jetbrains.annotations.NotNull;

import java.util.*;

//https://blog.csdn.net/lican19911221/article/details/25817971
public class JobMachine {

    public  static class JobNode implements Comparable{
        int id; //作业编号
        int time; //作业时间

        public JobNode(int id, int time){
            this.id = id;
            this.time = time;
        }

        @Override
        public int compareTo(Object o) {//从大到小排序
            int times = ((JobNode)o).time;
            if (time > times) return -1;
            if (times == time) return 0;
            return 1;
        }
    }

    public static class MachineNode implements Comparable{

        int id;//机器编号
        int avail;////机器空闲的时间（即机器做完某一项工作的时间）
        public MachineNode(int id, int avail){
            this.id = id;
            this.avail = avail;
        }

        @Override
        public int compareTo(@NotNull Object o) {//升序排序，LinkedList的first为最小的
            int xs = ((MachineNode)o).avail;
            if (avail < xs) return -1;
            if (avail == xs) return 0;
            return 1;
        }
    }

    public static int greedy(int[] a, int m){
        //a的下标从1开始，所以n（作业的数目）=a.length-1
        int n = a.length - 1;
        int sum = 0;
        //机器数量足够
        if (n <= m){
            System.out.println("为每个作业分配一台机器");
            for (int i : a) {
                if (i > sum)
                    sum = i;
            }
            return sum;
        }

        List<JobNode> job = new ArrayList<>();//保存所有作业
        for (int i = 0; i < n; i++) {
            JobNode jb = new JobNode(i + 1, a[i + 1]);
            job.add(jb);
        }
        //对作业进行排序
        Collections.sort(job);

        LinkedList<MachineNode> machine = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            //初始时，每台机器的空闲时间（完成上一个作业的时间）都为0
            MachineNode mn = new MachineNode(i, 0);
            machine.add(mn);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(machine);

            MachineNode cur = machine.peek();
            System.out.println("将机器" + cur.id + "从" +
                    cur.avail + "到" + (cur.avail + job.get(i).time) +
            "的时间段分配给作业" + job.get(i).id);
            cur.avail += job.get(i).time;
            sum = cur.avail;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a={0,2,14,4,16,6,5,3};
        int m=3;
        int sum=greedy(a,m);
        System.out.println("总时间为："+sum);
    }
}
