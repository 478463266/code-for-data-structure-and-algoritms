package hash;

public class hashLinkedNode<K, V> {
    K key;
    V value;
    hashLinkedNode<K, V> next;

    public hashLinkedNode(K key, V value, hashLinkedNode<K, V> node){
        this.key = key;
        this.value = value;
        this.next = node;
    }

}
