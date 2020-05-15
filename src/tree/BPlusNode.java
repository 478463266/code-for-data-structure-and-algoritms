package tree;

//ref: https://www.cnblogs.com/jing99/p/11741685.html
//未完全实现，详细信息参见该参考
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BPlusNode<K extends Comparable<K>, V> {
    //是否为叶子节点
    protected  boolean isLeaf;

    //是否为根节点
    protected  boolean isRoot;

    //父节点
    protected BPlusNode<K, V> parent;

    //叶节点的前节点
    protected BPlusNode<K, V> previous;

    //叶节点的后节点
    protected BPlusNode<K, V> next;

    //节点的关键字列表
    protected List<Map.Entry<K, V>> entries;

    //子节点列表
    protected List<BPlusNode<K, V>> children;

    public BPlusNode(boolean isLeaf){
        this.isLeaf = isLeaf;
        entries = new ArrayList<>();

        if (!isLeaf)
            children = new ArrayList<>();
    }

    public BPlusNode(boolean isLeaf, boolean isRoot){
        this(isLeaf);
        this.isRoot = isRoot;
    }

    public V get(K key){
        //如果是叶子节点
        if (isLeaf){
            int low = 0, high = entries.size() - 1, mid;
            int cmp;
            while (low <= high){
                mid = (low + high) / 2;
                cmp = entries.get(mid).getKey().compareTo(key);
                if (cmp == 0)
                    return entries.get(mid).getValue();
                else if (cmp < 0)
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            return null;
        }
        //如果不是叶子节点
        //如果key小于节点最左边的key，沿第一个子节点继续搜索
        if (key.compareTo(entries.get(0).getKey()) < 0)
            return children.get(0).get(key);
        //如果key大于等于节点最右边的key，沿最后一个子节点继续搜索
        else if (key.compareTo(entries.get(entries.size() - 1).getKey()) >= 0 )
            return children.get(children.size() - 1).get(key);
        //否则沿比key大的前一个前一个子节点继续搜索
        else {
            int low = 0, high = entries.size() - 1, mid;
            int cmp;
            while (low <= high){
                mid = (low + high) / 2;
                cmp = entries.get(mid).getKey().compareTo(key);
                if (cmp == 0)
                    return children.get(mid + 1).get(key);
                else if (cmp < 0)
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            return children.get(low).get(key);
        }
    }

    public void insertOrUpdate(K key, V value, BPlusTree<K, V> tree){

        if (isLeaf)
        {
            //不需要分裂，直接插入或者更新
            if (contains(key) != -1 || entries.size() < tree.getOrder()){
                insertOrUpdate(key, value);
                if (tree.getHeight() == 0)
                    tree.setHeight(1);
                return;
            }

            //需要分裂成左右两个节点
            BPlusNode<K, V> left = new BPlusNode<>(true);
            BPlusNode<K, V> right = new BPlusNode<>(true);
            //设置链接
            if (previous != null){
                previous.next = left;
                left.previous = previous;
            }
            if (next != null){
                next.previous = right;
                right.next = next;
            }
            if (previous == null)
                tree.setHead(left);

            left.next = right;
            right.previous = left;
            previous = null;
            next = null;

            //复制原节点关键字到分裂出来的新节点
            copy2Nodes(key, value, left, right, tree);

            //如果不是根节点
            if (parent != null){
                //调整父子关系
                int index = parent.children.indexOf(this);
                parent.children.remove(this);
            }

        }

    }

    //复制原节点关键字到分裂出来的新节点
    private void copy2Nodes(K key, V value, BPlusNode<K, V> left,
                            BPlusNode<K, V> right, BPlusTree<K, V> tree) {
        //左右两个节点关键字长度
        int leftSize = (tree.getOrder() + 1) / 2 + (tree.getOrder() + 1) % 2;
        boolean b = false; //用于记录新元素是否已经被插入
        for (int i = 0; i < entries.size(); i++) {
            if (leftSize != 0){
                leftSize--;
                if (!b && entries.get(i).getKey().compareTo(key) > 0){
                    left.entries.add(new AbstractMap.SimpleEntry<>(key, value));
                    b = true;
                    i--;
                }else {
                    left.entries.add(entries.get(i));
                }
            }else {
                if (!b && entries.get(i).getKey().compareTo(key) > 0){
                    right.entries.add(new AbstractMap.SimpleEntry<>(key, value));
                    b = true;
                    i--;
                }else {
                    right.entries.add(entries.get(i));
                }
            }
        }
        if (!b){
            right.entries.add(new AbstractMap.SimpleEntry<>(key, value));
        }
    }

    /**
     * 插入节点后中间节点的更新
     */
    protected void updateInsert(BPlusTree<K, V> tree) {
        //如果子节点数超出阶数，则需要分裂该节点
        if (children.size() > tree.getOrder()) {
            //分裂成左右两个节点
            BPlusNode<K, V> left = new BPlusNode<K, V>(false);
            BPlusNode<K, V> right = new BPlusNode<K, V>(false);
            //左右两个节点子节点的长度
            int leftSize = (tree.getOrder() + 1) / 2 + (tree.getOrder() + 1) % 2;
            int rightSize = (tree.getOrder() + 1) / 2;
            //复制子节点到分裂出来的新节点，并更新关键字
            for (int i = 0; i < leftSize; i++) {
                left.children.add(children.get(i));
                children.get(i).parent = left;
            }
            for (int i = 0; i < rightSize; i++) {
                right.children.add(children.get(leftSize + i));
                children.get(leftSize + i).parent = right;
            }
            for (int i = 0; i < leftSize - 1; i++) {
                left.entries.add(entries.get(i));
            }
            for (int i = 0; i < rightSize - 1; i++) {
                right.entries.add(entries.get(leftSize + i));
            }

            //如果不是根节点
            if (parent != null) {
                //调整父子节点关系
                int index = parent.children.indexOf(this);
                parent.children.remove(this);
                left.parent = parent;
                right.parent = parent;
                parent.children.add(index, left);
                parent.children.add(index + 1, right);
                parent.entries.add(index, entries.get(leftSize - 1));
                entries = null;
                children = null;

                //父节点更新关键字
                parent.updateInsert(tree);
                parent = null;
                //如果是根节点
            } else {
                isRoot = false;
                BPlusNode<K, V> parent = new BPlusNode<K, V>(false, true);
                tree.setRoot(parent);
                tree.setHeight(tree.getHeight() + 1);
                left.parent = parent;
                right.parent = parent;
                parent.children.add(left);
                parent.children.add(right);
                parent.entries.add(entries.get(leftSize - 1));
                entries = null;
                children = null;
            }
        }
    }

    /**
     * 删除节点后中间节点的更新
     */
    protected void updateRemove(BPlusTree<K, V> tree) {


    }

    public V remove(K key, BPlusTree<K, V> tree) {
        return null;
    }

    // 判断当前节点是否包含该关键字
    protected int contains(K key) {
        int low = 0, high = entries.size() - 1, mid;
        int cmp;
        while (low <= high){
            mid = low + (high - low) / 2;
            cmp = entries.get(mid).getKey().compareTo(key);
            if (cmp == 0){
                return mid;
            }
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    // 插入到当前节点的关键字中
    protected void insertOrUpdate(K key, V value) {

        int low = 0, high = entries.size() - 1, mid;
        int cmp;
        while (low <= high){
            mid = low + (high - low) / 2;
            cmp = entries.get(mid).getKey().compareTo(key);
            if (cmp == 0){
                //进行更新
                entries.get(mid).setValue(value);
                break;
            }
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        //进行更新
        if (low > high)
            entries.add(low, new AbstractMap.SimpleEntry<K, V>(key, value));
    }

    // 删除节点
    protected V remove(K key) {
        int low = 0, high = entries.size() - 1, mid;
        int cmp;
        while (low <= high){
            mid = low + (high - low) / 2;
            cmp = entries.get(mid).getKey().compareTo(key);
            if (cmp == 0){
                return entries.remove(mid).getValue();
            }
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return null;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("isRoot: ");
        sb.append(isRoot);
        sb.append(", ");
        sb.append("isLeaf: ");
        sb.append(isLeaf);
        sb.append(", ");
        sb.append("keys: ");
        for (Map.Entry<K, V> entry : entries){
            sb.append(entry.getKey());
            sb.append(", ");
        }
        sb.append(", ");
        return sb.toString();
    }

    public void printBPlusTree(int index) {

        if (this.isLeaf){
            System.out.println("层级：" + index + ",叶子节点， key为：");
            for (int i = 0; i < entries.size(); i++) {
                System.out.print(entries.get(i) + " ");
            }
            System.out.println();
        }else {
            System.out.println("层级： " + index + ", 非叶子节点，key为：");
            for (int i = 0; i < entries.size(); i++) {
                System.out.print(entries.get(i) + " ");
            }
            System.out.println();
            for (int i = 0; i < children.size(); i++) {
                children.get(i).printBPlusTree(index + 1);
            }
        }
    }
}
