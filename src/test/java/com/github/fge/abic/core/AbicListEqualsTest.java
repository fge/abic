package com.github.fge.abic.core;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public final class AbicListEqualsTest
{
    @Test
    public void equalsBasicsWork()
    {
        final List<Object> list = new AbicList<Object>(Object.class);

        assertFalse(list.equals(null));
        assertFalse(list.equals(new Object()));
        assertTrue(list.equals(list));

        final List<Object> list2 = new ArrayList<Object>();
        assertTrue(list.equals(list2));
        assertTrue(list2.equals(list));
        assertEquals(list.hashCode(), list2.hashCode());

        final List<String> list3 = new ArrayList<String>();
        assertTrue(list.equals(list3));
        assertTrue(list3.equals(list));
        assertEquals(list.hashCode(), list3.hashCode());
    }

    @Test
    public void equalsHashCodeOnNonEmptyListsWorks()
    {
        final List<String> list = new AbicList<String>(String.class,
            "hello", "world");
        final List<String> list2 = Arrays.asList("hello", "world");

        assertTrue(list.equals(list));

        assertEquals(list.hashCode(), list2.hashCode());
        assertTrue(list.equals(list2));
        assertTrue(list2.equals(list));

        final List<Object> list3 = Arrays.<Object>asList("hello", "world");

        assertEquals(list.hashCode(), list3.hashCode());
        assertTrue(list.equals(list3));
        assertTrue(list3.equals(list));
    }

    @Test
    public void nonEqualListsAreIndeedUnequal()
    {
        final List<String> list = new AbicList<String>(String.class,
            "hello", "world", "of", "goo");
        final List<String> list2 = Arrays.asList("hello", "world", "of", "");

        assertFalse(list.equals(list2));
        assertFalse(list2.equals(list));
    }
}
