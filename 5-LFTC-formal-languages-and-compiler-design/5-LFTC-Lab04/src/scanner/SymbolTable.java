package scanner;

import scanner.auxiliary.Node;

import java.util.ArrayList;

/**
 * Keeps track of all the variables & constants used in the program.
 * Holds multiple Node<K, V> elements where:
 * @param <K> is the identifier name or constant value
 * @param <V> it's position in the symbol table
 */
public class SymbolTable<K, V> {
    private ArrayList<Node<K, V>> array;
    private int numBuckets;
    private int size;

    public SymbolTable()
    {
        array = new ArrayList<>();
        numBuckets = 100;
        size = 0;

        for (int i = 0; i < numBuckets; i++)
            array.add(null);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    private int getBucketIndex(K key)
    {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index;
    }

    public V remove(K key)
    {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = array.get(bucketIndex);
        Node<K, V> prev = null;
        while (head != null)
        {
            if (head.getKey().equals(key))
                break;
            prev = head;
            head = head.getNext();
        }

        if (head == null)
            return null;

        size--;


        if (prev != null)
            prev.setNext(head.getNext());
        else
            array.set(bucketIndex, head.getNext());

        return head.getValue();
    }

    public V get(K key)
    {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = array.get(bucketIndex);

        while (head != null)
        {
            if (head.getKey().equals(key))
                return head.getValue();
            head = head.getNext();
        }

        return null;
    }

    public void add(K key, V value)
    {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = array.get(bucketIndex);

        while (head != null)
        {
            if (head.getKey().equals(key))
            {
                head.setValue(value);
                return;
            }
            head.setNext(head.getNext());
        }

        size++;
        head = array.get(bucketIndex);
        Node<K, V> newNode = new Node<K, V>(key, value);
        newNode.setNext(head);
        array.set(bucketIndex, newNode);

        if ((1.0*size)/numBuckets >= 0.7)
        {
            ArrayList<Node<K, V>> temp = array;
            array = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                array.add(null);

            for (Node<K, V> headNode : temp)
            {
                while (headNode != null)
                {
                    add(headNode.getKey(), headNode.getValue());
                    headNode = headNode.getNext();
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "ST:\nKey  Value\n";
        for(Node<K, V> node : this.array) {
            if (node != null) {
                output += node.toString();
                while (node.getNext() != null) {
                    node = node.getNext();
                    output += node.toString();
                }
            }
        }
        return output;
    }
}
