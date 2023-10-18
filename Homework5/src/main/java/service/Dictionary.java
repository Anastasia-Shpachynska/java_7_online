package service;

import java.util.*;

public class Dictionary<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Map<K, V>[] table;
    private int size;

    public Dictionary() {
        this.table = new Map[INITIAL_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        int hash = hash(key);
        Map<K, V> map = table[hash];
        while (map != null) {
            if (map.key.equals(key)) {
                return true;
            }
            map = map.next;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (Map<K, V> map : table) {
            while (map != null) {
                if (map.value.equals(value)) {
                    return true;
                }
                map = map.next;
            }
        }
        return false;
    }

    public V get (K key) {
        int hash = hash(key);
        Map<K, V> map = table[hash];
        while (map != null) {
            if(map.key.equals(key)) {
                return map.value;
            }
            map = map.next;
        }
        return null;
    }

    public boolean put(K key, V value) {
        int hash = hash(key);
        Map<K, V> newMap = new Map<>(key, value);
        if (table[hash] == null) {
            table[hash] = newMap;
            size++;
            if (size >= table.length * LOAD_FACTOR) {
                resize();
            }
            return true;
        }else {
            Map<K, V> current = table[hash];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return false;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
                return false;
            }
                current.next = newMap;
                size++;
                if (size >= table.length * LOAD_FACTOR) {
                    resize();
                }
                return true;
        }
    }

    public boolean remove(K key) {
        int hash = hash(key);
        Map<K, V> map = table[hash];
        Map<K, V> prev = null;
        while (map != null) {
            if (map.key.equals(key)) {
                if (prev == null) {
                    table[hash] = map.next;
                }else {
                    prev.next = map.next;
                }
                size--;
                return true;
            }
            prev = map;
            map = map.next;
        }
        return false;
    }

    public boolean putAll(Dictionary<K, V> dictionary) {
        for (Map<K, V> map : dictionary.table) {
            while (map != null) {
                put(map.key, map.value);
                map = map.next;
            }
        }
        return true;
    }


    public boolean clear() {
        table = new Map[INITIAL_CAPACITY];
        size = 0;
        return true;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map<K, V> map : table) {
            while (map != null) {
                keys.add(map.key);
                map = map.next;
            }
        }
        return keys;
    }

    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map<K, V> map : table) {
            while (map != null) {
                values.add(map.value);
                map = map.next;
            }
        }
        return values;
    }

    private void resize() {
        Map<K, V>[] newTable = new Map[table.length * 2];
        for (Map<K, V> map : table) {
            while (map != null) {
                int hash = map.key.hashCode() % newTable.length;
                if (newTable[hash] == null) {
                    newTable[hash] = new Map<>(map.key, map.value);
                } else {
                    Map<K, V> current = newTable[hash];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = new Map<>(map.key, map.value);
                }
                map = map.next;
            }
        }
        table = newTable;
    }

    private int hash(K key) {
        return key.hashCode() % table.length;
    }

    private static class Map<K, V> {
        K key;
        V value;
        Map<K, V> next;

        public Map(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
