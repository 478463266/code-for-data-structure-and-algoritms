package tree;

public class treeNodeCom <T extends Comparable> {
    T value;
    treeNodeCom<T> leftChild;
    treeNodeCom<T> rightChild;
    //另外一种方法：first child；nextSibling

    treeNodeCom( T value)
    {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    treeNodeCom (T v, treeNodeCom<T> l, treeNodeCom<T> r){
        this.value = v;
        this.leftChild = l;
        this.rightChild = r;
    }

    public treeNodeCom() {
        this.leftChild = null;
        this.rightChild = null;
    }


    public void addLeft(T value)
    {
        treeNodeCom<T> l = new treeNodeCom<>(value);
        this.leftChild = l;
    }

    public void addRight(T value)
    {
        treeNodeCom<T> r = new treeNodeCom<>(value);
        this.rightChild = r;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof treeNodeCom))
            return false;
        return this.value.equals(((treeNodeCom<T>)obj).value);
    }

    public int hashCode(){
        return this.value.hashCode();
    }

    public String toString(){
        return "value: " + this.value == null ? "" : this.value.toString();

    }

}
