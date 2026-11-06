public class TreeBucket {
    private static class Node {
        Entry entry;
        Node left;
        Node right;

        Node(Entry entry) {
            this.entry = entry;
        }
    }

    private Node root;
    private int size;

    public boolean insert(Entry entry) {
        Entry existing = find(entry.getSnils());
        if (existing != null) {
            existing.setFullName(entry.getFullName());
            return false;
        }
        root = insert(root, entry);
        size++;
        return true;
    }

    private Node insert(Node node, Entry entry) {
        if (node == null) {
            return new Node(entry);
        }
        if (entry.getSnils().compareTo(node.entry.getSnils()) < 0) {
            node.left = insert(node.left, entry);
        } else {
            node.right = insert(node.right, entry);
        }
        return node;
    }

    public Entry find(String key) {
        return find(root, key);
    }

    private Entry find(Node node, String key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.entry.getSnils());
        if (cmp == 0) {
            return node.entry;
        }
        return cmp < 0 ? find(node.left, key) : find(node.right, key);
    }

    public boolean remove(String key) {
        if (find(key) == null) {
            return false;
        }
        root = remove(root, key);
        size--;
        return true;
    }

    private Node remove(Node node, String key) {
        int cmp = key.compareTo(node.entry.getSnils());
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node min = node.right;
            while (min.left != null) {
                min = min.left;
            }
            node.entry = min.entry;
            node.right = remove(node.right, min.entry.getSnils());
        }
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void collectPreOrder(MyList<Entry> result) {
        collectPreOrder(root, result);
    }

    private void collectPreOrder(Node node, MyList<Entry> result) {
        if (node == null) {
            return;
        }
        result.add(node.entry);
        collectPreOrder(node.left, result);
        collectPreOrder(node.right, result);
    }

    public void print() {
        print(root, 0);
    }

    private void print(Node node, int depth) {
        if (node == null) {
            return;
        }
        System.out.println("      " + "  ".repeat(depth) + "- " + node.entry);
        print(node.left, depth + 1);
        print(node.right, depth + 1);
    }
}
