public class HashTable {
    private static final double LOAD_FACTOR = 0.75;

    private TreeBucket[] buckets;
    private int size;

    public HashTable(int capacity) {
        buckets = new TreeBucket[capacity];
    }

    public int getCapacity() {
        return buckets.length;
    }

    public int getSize() {
        return size;
    }

    private int indexOf(String key) {
        return HashFunction.fold(key, buckets.length);
    }

    public boolean insert(Entry entry) {
        int index = indexOf(entry.getSnils());
        if (buckets[index] == null) {
            buckets[index] = new TreeBucket();
        }
        boolean added = buckets[index].insert(entry);
        if (added) {
            size++;
            if ((double) size / buckets.length > LOAD_FACTOR) {
                rehash();
            }
        }
        return added;
    }

    public Entry search(String key) {
        TreeBucket bucket = buckets[indexOf(key)];
        return bucket == null ? null : bucket.find(key);
    }

    public boolean delete(String key) {
        int index = indexOf(key);
        TreeBucket bucket = buckets[index];
        if (bucket == null || !bucket.remove(key)) {
            return false;
        }
        size--;
        if (bucket.isEmpty()) {
            buckets[index] = null;
        }
        return true;
    }

    private void rehash() {
        TreeBucket[] old = buckets;
        buckets = new TreeBucket[old.length * 2 + 1];
        for (TreeBucket bucket : old) {
            if (bucket == null) {
                continue;
            }
            MyList<Entry> entries = new MyList<>();
            bucket.collectPreOrder(entries);
            for (int i = 0; i < entries.size(); i++) {
                Entry entry = entries.get(i);
                int index = indexOf(entry.getSnils());
                if (buckets[index] == null) {
                    buckets[index] = new TreeBucket();
                }
                buckets[index].insert(entry);
            }
        }
    }

    public void print() {
        System.out.printf("Размер таблицы: %d, элементов: %d, коэффициент заполнения: %.2f%n",
                buckets.length, size, (double) size / buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) {
                System.out.println("[" + i + "] пусто");
            } else {
                System.out.println("[" + i + "] элементов: " + buckets[i].size());
                buckets[i].print();
            }
        }
    }
}
