package pobj.multiset;

import java.util.Collection;
import java.util.HashMap;


import java.util.logging.Logger;

public class HashMultiSet<T> implements MultiSet<T> {
    private HashMap<T, Integer> map;
    private int size;

    private final static Logger LOGGER = Logger.getLogger(HashMultiSet.class.getName());

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
        if(count <= 0){
            return false;
        }else{
            Integer value = map.get(e);
            if(value == null){
                map.put(e, count);
            }else{
                map.put(e, value + count);
            }
            size+= count;
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
        if(value == null) return 0;
        return value;
    }

    @Override
    public boolean remove(Object e) {
        Integer value = map.get(e);
        if(value == null){
            return true;
        }
        size--;
        if(value == 1){
            map.remove(e);
            return true;
        }else{
            map.put((T) e, --value);
            return false;
        }
    }

    @Override
    public boolean remove(Object e, int count) {
        Integer value = map.get(e);
        if(value == null){
            return true;
        }
        if(value - count <= 0){
            map.remove(e);
            size--;
            return true;
        }else{
            map.put((T) e, value - count);
            size -= count;
            return false;
        }
    }

    @Override
    public int size() {
       return size;
    }

    
}