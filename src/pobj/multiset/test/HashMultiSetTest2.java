package pobj.multiset.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pobj.multiset.HashMultiSet;
import pobj.multiset.MultiSet;

public class HashMultiSetTest2 {
    
    private MultiSet<String> m;

    @Before
    public void before() {
		m = new HashMultiSet<>();
		m.add("a");
		m.add("a",5);
		m.add("b",3);
    }
    
	@Test(expected = IllegalArgumentException.class)
	public void testAdd2(){
		m.add("a",-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
	public void testRemove(){
		m.remove("a",-1);
    }
    
    @Test
    public void testSize(){
        assertEquals(9, m.size());
    }

    @Test
    public void testToString(){
        assertEquals("[a : 6, b : 3]", m.toString());
    }

    @Test
    public void testClear(){
        m.clear();
        assertEquals(0, m.count("a"));
        assertEquals(0, m.count("b"));
        assertEquals(0, m.size());
    }

    @Test
    public void testCountOnElementNeverCounted(){
        assertEquals(0, m.count("o"));
    }

    @Test
    public void testAddWithZeroCount(){
        assertEquals(false, m.add("e", 0));
    }
    
    @Test
    public void testRemoveWithZeroCount(){
        assertEquals(false, m.remove("a", 0));
    }

    @Test
    public void testAddWithCountBeforeRemoveWithCount(){
        m.add("e", 42);
        m.remove("e", 42);
        assertEquals(0, m.count("e"));
        assertEquals(9, m.size());
    }
}
