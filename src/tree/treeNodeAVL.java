package tree;

public class treeNodeAVL<T extends Comparable> {
    T value;
    treeNodeAVL<T> leftChild;
    treeNodeAVL<T> rightChild;
    int height;

    //另外一种方法：first child；nextSibling

    treeNodeAVL(T value)
    {
        this.value = value;
        this.height = 0;
        this.leftChild = null;
        this.rightChild = null;
    }

    treeNodeAVL(T value, treeNodeAVL<T> left, treeNodeAVL<T> right)
    {
        this.value = value;
        this.leftChild = left;
        this.rightChild = right;
        this.height = 0;
    }

    public void addLeft(T value)
    {
        treeNodeAVL<T> l = new treeNodeAVL<>(value);
        this.leftChild = l;
    }

    public void addRight(T value)
    {
        treeNodeAVL<T> r = new treeNodeAVL<>(value);
        this.rightChild = r;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof treeNodeAVL))
            return false;
        return this.value.equals(((treeNodeAVL<T>)obj).value);
    }

    public int hashCode(){
        return this.value.hashCode();
    }

    public String toString(){
        return "value: " + this.value == null ? "" : this.value.toString()
                + "height: " + this.height
                + "Balanced: " + isBalance();
    }

    public boolean isBalance(){
        int leftHeight = leftChild == null ? -1 : leftChild.height;
        int rightHeight = rightChild == null ? -1 : rightChild.height;
        return Math.abs(leftHeight - rightHeight) <= 1 ? true : false;
    }
}
