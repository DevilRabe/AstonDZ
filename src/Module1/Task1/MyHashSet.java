package Module1.Task1;

public class MyHashSet<T>
{
    private static final int INITIAL_CAPACITY = 16;
    private MyLinkedList<T>[] buckets;
    private int size;

    public MyHashSet() {
        buckets = new MyLinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new MyLinkedList<>();
        }
        size = 0;
    }

    public void add(T element) {
        int index = hash(element);
        if (!buckets[index].contains(element)) {
            buckets[index].add(element);
            size++;
        }
    }

    public boolean remove(T element) {
        int index = hash(element);
        if (buckets[index].remove(element)) {
            size--;
            return true;
        }
        return false;
    }

    public boolean contains(T element) {
        int index = hash(element);
        return buckets[index].contains(element);
    }

    public int size() {
        return size;
    }

    private int hash(T element) {
        int h = (element == null) ? 0 : element.hashCode();
        return Math.abs(h) % buckets.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (MyLinkedList<T> bucket : buckets) {
            for (T item : bucket.getAll()) {
                if (!first) sb.append(", ");
                sb.append(item);
                first = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
