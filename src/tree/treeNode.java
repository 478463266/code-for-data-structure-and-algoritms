package tree;

//树的基本知识介绍：https://blog.csdn.net/u014203449/article/details/80231848
//普通树节点，非二叉树
public class treeNode<T> {

    T value;
    treeNode<T> leftChild;
    treeNode<T> rightChild;

    //另外一种方法：first child；nextSibling

    treeNode( T value)
    {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    treeNode (T v, treeNode<T> l, treeNode<T> r){
        this.value = v;
        this.leftChild = l;
        this.rightChild = r;
    }

    public void addLeft(T value)
    {
        treeNode<T> l = new treeNode<>(value);
        this.leftChild = l;
    }

    public void addRight(T value)
    {
        treeNode<T> r = new treeNode<>(value);
        this.rightChild = r;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof treeNode))
            return false;
        return this.value.equals(((treeNode<T>)obj).value);
    }

    public int hashCode(){
        return this.value.hashCode();
    }

    public String toString(){
        return this.value == null ? "" : this.value.toString();
    }
}
