package utilRW;

public class ArrayRW {

    public void printArray(int[] array){
        if (null == array || array.length == 0) {
            System.out.println("数组为空");
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
