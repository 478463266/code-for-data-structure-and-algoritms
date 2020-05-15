package tree;

public class testAVL {

    public static void main(String[] args) {
        AVLtree<Integer> ope = new AVLtree<>();
        treeNodeAVL<Integer> t = new treeNodeAVL<>(3);
        t = ope.insert(t, 2);
        t = ope.insert(t, 1);
        t=  ope.insert(t, 4);
        t=  ope.insert(t, 5);
        t=  ope.insert(t, 6);
        t=  ope.insert(t, 7);
        t=  ope.insert(t, 16);
        t=  ope.insert(t, 15);
        t=  ope.insert(t, 14);
        t=  ope.insert(t, 13);
        t=  ope.insert(t, 12);
        t=  ope.insert(t, 11);
        t=  ope.insert(t, 10);
        t=  ope.insert(t, 8);
        t=  ope.insert(t, 9);

        ope.preOrderTravel(t);
        System.out.println();

        //t = ope.insert(t,9);


        ope.remove(t, 8);
        ope.preOrderTravel(t);
    }

}
