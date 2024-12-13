package scanner.auxiliary;

/**
 * A key, value pair referencing Node or null
 * @param <K>
 * @param <V>
 */
public class Node<K, V> {
    private K key;
    private V value;
    private Node next;

    public Node() {
        this.key = null;
        this.value = null;
        this.next = null;
    }

    public Node(K key, V value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }


    public Node(K key, V value)
    {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public Node(Node<K, V> other)
    {
        this.key = other.getKey();
        this.value = other.getValue();
        this.next = other.getNext();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Node<K ,V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return key.toString() + " " +value.toString() + "\n";
    }
}
