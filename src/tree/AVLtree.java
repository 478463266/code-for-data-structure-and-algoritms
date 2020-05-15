package tree;

//ref:https://www.cnblogs.com/skywang12345/p/3577479.html
public class AVLtree<T extends Comparable<T>> {

    private int callHeight(treeNodeAVL<T> node){

        if (node == null)
            return 0;
        else return node.height;
    }


    //K2为原根节点，k1位其左长
    public treeNodeAVL<T> leftLeftRotation(treeNodeAVL<T> k2){
        treeNodeAVL<T> k1;
        k1 = k2.leftChild;
        k2.leftChild = k1.rightChild;
        k1.rightChild = k2;

        k2.height = Math.max(callHeight(k2.leftChild), callHeight(k2.rightChild)) + 1;
        k1.height = Math.max(callHeight(k1.leftChild), k2.height) + 1;

        return k1;
    }

    //k1为原根节点，k2位其右长
    public treeNodeAVL<T> rightRightRotation(treeNodeAVL<T> k1){
        treeNodeAVL<T> k2;
        k2 = k1.rightChild;
        k1.rightChild = k2.leftChild;
        k2.leftChild = k1;

        k1.height = Math.max(callHeight(k1.leftChild), callHeight(k1.rightChild)) + 1;
        k2.height = Math.max(k1.height, callHeight(k2.rightChild)) + 1;

        return k2;
    }

    //原树由上到下的顺序为k3, k1, k2
    public treeNodeAVL<T> leftRightRotation(treeNodeAVL<T> k3)
    {
        //第一次围绕k1右右；第二次围绕k3进行左左
        k3.leftChild = rightRightRotation(k3.leftChild);
        return leftLeftRotation(k3);
    }

    //原树由上到下的顺序为k1, k3, k2
    public treeNodeAVL<T> rightLeftRotation(treeNodeAVL<T> k1){
        //先围绕k3继续左左，然后围绕k1右右
        k1.rightChild = leftLeftRotation(k1.rightChild);
        return rightRightRotation(k1);
    }

    public treeNodeAVL<T> insert(treeNodeAVL<T> root, T value){
        if (root == null){
            root = new treeNodeAVL<T> (value, null, null);
        }else {
            if (value.compareTo(root.value) < 0){ //插入到root的左子树
                root.leftChild = insert(root.leftChild, value);
                if (callHeight(root.leftChild) - callHeight(root.rightChild) == 2){
                    if (value.compareTo(root.leftChild.value) < 0)
                        root = leftLeftRotation(root);
                    else
                        root = leftRightRotation(root);
                }
            }else if (value.compareTo(root.value) > 0){
                root.rightChild = insert(root.rightChild, value);
                if (callHeight(root.rightChild) - callHeight(root.leftChild) == 2){
                    if (value.compareTo(root.rightChild.value) > 0)
                        root = rightRightRotation(root);
                    else
                        root = rightLeftRotation(root);
                }
            }
            else //不允许添加相同大小的节点
                System.out.println("添加失败，不能添加相同的节点");
        }
        root.height = Math.max(callHeight(root.leftChild), callHeight(root.rightChild)) + 1;

        return root;
    }

    public void remove(treeNodeAVL<T> root, T value)
    {
        if (root == null || value == null)
            return;

        treeNodeAVL<T> temp = root;

        while (temp != null && temp.value.compareTo(value) != 0){
            if (value.compareTo(temp.value) < 0)
                temp = temp.leftChild;
            else
                temp = temp.rightChild;
        }

        if (temp == null)
            System.out.println("删除失败，值为" + value + "的节点不存在");
        else
            remove(root, temp);
    }

    public treeNodeAVL<T> remove(treeNodeAVL<T> root, treeNodeAVL<T> node){
        if (root == null || node == null)
            return null;
        if (node.value.compareTo(root.value) < 0){// 待删除的节点在"tree的左子树"中
            root.leftChild = remove(root.leftChild, node);
            if (callHeight(root.rightChild) - callHeight(root.leftChild) == 2){
                treeNodeAVL<T> temp = root.rightChild;
                if (callHeight(temp.leftChild) > callHeight(temp.rightChild))
                    root = rightLeftRotation(root);
                else
                    root = rightRightRotation(root);
            }
        }else if (node.value.compareTo(root.value) > 0){// 待删除的节点在"tree的右子树"中
            //删除节点后，若AVL树失去平衡，则进行相应的调节
            root.rightChild = remove(root.rightChild, node);
            if (callHeight(root.leftChild) - callHeight(root.rightChild) == 2){
                treeNodeAVL<T> l = root.leftChild;
                if (callHeight(l.rightChild) - callHeight(l.leftChild) > 0)
                    root = leftRightRotation(root);
                else
                    root = leftLeftRotation(root);
            }
        }else //root是对应要删除的节点
        {
            //如果root的左右孩子都非空
            if (root.rightChild != null && root.leftChild != null){
                // 如果tree的左子树比右子树高；
                // 则(01)找出tree的左子树中的最大节点
                //   (02)将该最大节点的值赋值给tree。
                //   (03)删除该最大节点。
                // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                if (callHeight(root.leftChild) > callHeight(root.rightChild)) {
                    treeNodeAVL<T> max = maximumNode(root.leftChild);
                    root.value = max.value;
                    root.leftChild = remove(root.leftChild, max);
                }
                else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    treeNodeAVL<T> min =minimumNode(root.rightChild);
                    root.value = min.value;
                    root.rightChild = remove(root.rightChild, min);
                }
            }else { //有一个孩子为空
                treeNodeAVL<T> temp = root;
                root = (root.leftChild != null) ? root.leftChild : root.rightChild;
                temp = null;
            }
        }
        return root;
    }

    public treeNodeAVL<T> maximumNode(treeNodeAVL<T> root){
        if (root == null)
            return null;
        while (root.rightChild != null)
        {
            root = root.rightChild;
        }
        return root;
    }

    public treeNodeAVL<T> minimumNode(treeNodeAVL<T> root){
        if (root == null)
            return null;
        while (root.leftChild != null)
        {
            root = root.leftChild;
        }
        return root;
    }

    public void preOrderTravel(treeNodeAVL<T> root)
    {
        if (root == null)
            return;
        System.out.print(root.value + "\t");
        preOrderTravel(root.leftChild);
        preOrderTravel(root.rightChild);
    }
}

