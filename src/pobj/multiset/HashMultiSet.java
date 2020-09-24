package pobj.multiset;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
    private HashMap<T, Integer> map;
    private int size;

    public HashMultiSet() {
        map = new HashMap<>();
        size = 0;
    }

    public HashMultiSet(Collection<T> list) {
        for (T t : list) {
            Integer value = map.get(t);
            if (value == null) {
                map.put(t, 1);
            } else {
                map.put(t, ++value);
            }
            size++;
        }
    }

    @Override
    public boolean add(T e, int count) {
        if (count <= 0) {
            return false;
        } else {
            Integer value = map.get(e);
            if (value == null) {
                map.put(e, count);
            } else {
                map.put(e, value + count);
            }
            size += count;
            return true;
        }
    }

    @Override
    public boolean add(T e) {
        Integer value = map.get(e);
        if (value == null) {
            map.put(e, 1);
        } else {
            map.put(e, ++value);
        }
        size++;
        return true;
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }

    @Override
    public int count(T o) {
        Integer value = map.get(o);
        if (value == null)
            return 0;
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object e) {
        Integer value = map.get(e);
        if (value == null) {
            return true;
        }
        size--;
        if (value == 1) {
            map.remove(e);
            return true;
        } else {
            map.put((T) e, --value);
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object e, int count) {
        Integer value = map.get(e);
        if (value == null) {
            return true;
        }
        if (value - count <= 0) {
            map.remove(e);
            size--;
            return true;
        } else {
            map.put((T) e, value - count);
            size -= count;
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int totalCompteur = 0;
            private int currentCompteur = 0;

            Iterator<Map.Entry<T, Integer>> itr = map.entrySet().iterator();
            private Map.Entry<T, Integer> currentEntry = itr.next();

            public boolean hasNext() {
                return totalCompteur < size;
            }

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    if (currentEntry.getValue() > currentCompteur++) {
                        totalCompteur++;
                        return currentEntry.getKey();
                    } else {
                        currentEntry = itr.next();
                        currentCompteur = 1;
                        totalCompteur++;
                        return currentEntry.getKey();
                    }
                }
            }
        };
    }
}