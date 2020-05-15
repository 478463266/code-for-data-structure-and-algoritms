package hash;

public class hashNode<K, V> {
    public K key;
    public V value;
    public boolean isActive;

    public hashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
        this.isActive = true;
    }

    public hashNode(K key, V value, boolean state)
    {
        this.key = key;
        this.value = value;
        isActive = state;
    }
}
