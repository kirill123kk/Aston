public class HashMapMe<K, V> {

        private static class Node<K, V> {
            int hash;
            K key;
            V value;
            Node<K, V> nodeNext;

            Node(int hash, K key, V value, Node<K, V> nodeNext) {
                this.hash = hash;
                this.key = key;
                this.value = value;
                this.nodeNext = nodeNext;
            }
        }

        private final Node<K, V>[] map;
        private int capacity = 16;


        public HashMapMe() {
            map = (Node<K, V>[]) new Node[capacity];
        }

        private int hash(K key) {
            return key == null ? 0 : Math.abs(key.hashCode()) % capacity;
        }

        public void put(K key, V value) {
            int h = hash(key);
            Node<K, V> current = map[h];
            if (current == null) {
                map[h] = new Node<>(h, key, value, null);
                return;
            }

            while (true) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                if (current.nodeNext == null) break;
                current = current.nodeNext;
            }

            current.nodeNext = new Node<>(h, key, value, null);
        }

        public V get(K key) {
            int h = hash(key);
            Node<K, V> current = map[h];
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.nodeNext;
            }
            return null;
        }

        public void remove(K key) {
            int h = hash(key);
            Node<K, V> current = map[h];
            Node<K, V> prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    if (prev == null) {
                        map[h] = current.nodeNext;
                    } else {
                        prev.nodeNext = current.nodeNext;
                    }
                    return;
                }
                prev = current;
                current = current.nodeNext;
            }
        }

}

