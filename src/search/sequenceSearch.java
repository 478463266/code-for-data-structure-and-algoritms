package search;

public class sequenceSearch {

    public static int seqsearch(int[] array, int value){

        if (array == null || array.length == 0)
            return -1;

        int len = array.length;

        for (int i = 0; i < len; i++) {
            if (array[i] == value)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] test = {1, 4, 5, 55, 7, 0, 21, 3};
        System.out.println(seqsearch(test, 0));
        System.out.println(seqsearch(test, 9));

    }
}
