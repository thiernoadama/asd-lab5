public class MyList<T> {
    private Object[] data = new Object[4];
    private int size;

    public void add(T item) {
        if (size == data.length) {
            grow();
        }
        data[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) data[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void removeLast() {
        data[--size] = null;
    }

    public void clear() {
        data = new Object[4];
        size = 0;
    }

    public MyList<T> copy() {
        MyList<T> result = new MyList<>();
        for (int i = 0; i < size; i++) {
            result.add(get(i));
        }
        return result;
    }

    private void grow() {
        Object[] bigger = new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            bigger[i] = data[i];
        }
        data = bigger;
    }
}
