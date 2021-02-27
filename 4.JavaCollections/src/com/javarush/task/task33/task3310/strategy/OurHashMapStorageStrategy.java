package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return (length - 1) & hash;
    }

    public Entry getEntry(Long key) {
        int hash = hash(key);

        for (Entry e = this.table[indexFor(hash, this.table.length)]; e != null ; e = e.next) {
            Long k;

            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return e;
            }
        }
        return null;
    }

    public void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    public void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;

        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];

            if (e != null) {
                src[j] = null;

                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }

    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);

        if (size++ == threshold) {
            resize(table.length * 2);
        }
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        for (Entry e = table[index]; e != null; e = e.next) {
            Long k;

            if (e.hash == hash && ((k = e.key).equals(key) || key.equals(k))) {
                e.value = value;
                return;
            }
        }

        addEntry(hash, key, value, index);

    }

    @Override
    public Long getKey(String value) {
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (e.getValue().equals(value)) {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return getEntry(key).value;
    }
}
