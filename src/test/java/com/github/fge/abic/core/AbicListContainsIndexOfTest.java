package com.github.fge.abic.core;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public final class AbicListContainsIndexOfTest
{
    @Test
    public void containsWorksCorrectly()
    {
        final List<String> list = new AbicList<String>(String.class,
            "hello", "world", "of", "goo");

        assertFalse(list.contains(null));
        assertFalse(list.contains(new Object()));
        assertTrue(list.contains("hello"));
        assertFalse(list.contains("worlds"));

        final Object o = "goo";
        assertTrue(list.contains(o));
    }

    @Test
    public void containsAllWorksCorrectly()
    {
        final List<String> list = new AbicList<String>(String.class,
            "hello", "world", "of", "goo");

        final List<String> list2 = Arrays.asList("goo", "world", "hello", "of");
        assertTrue(list.containsAll(list2));
        assertTrue(list2.containsAll(list));

        final List<String> list3 = Arrays.asList("goo", "hello", "of");
        assertTrue(list.containsAll(list3));
        assertFalse(list3.containsAll(list));

        final List<String> list4 = Arrays.asList("hello", "world", "goo", "of",
            "bonus");
        assertFalse(list.containsAll(list4));
        assertTrue(list4.containsAll(list));

        final List<String> list5 = Arrays.asList("hello", "world", "goo", "of",
            "goo");
        assertTrue(list.containsAll(list5));
        assertTrue(list5.containsAll(list));
    }

    @Test
    public void indexOfWorksCorrectly()
    {
        final List<String> list = new AbicList<String>(String.class,
            "hello", "world", "of", "goo", "of", "hello", "goo", "world");

        assertEquals(list.indexOf("hello"), 0);
        assertEquals(list.indexOf("goo"), 3);
        assertEquals(list.indexOf("goose"), -1);
    }

    @Test
    public void lastIndexOfWorksCorrectly()
    {
        final List<String> list = new AbicList<String>(String.class,
            "hello", "world", "of", "goo", "meh", "of", "hello", "goo",
            "world");

        assertEquals(list.lastIndexOf("goo"), 7);
        assertEquals(list.lastIndexOf("meh"), 4);
        assertEquals(list.lastIndexOf("of"), 5);
        assertEquals(list.lastIndexOf("worlds"), -1);
    }
}
