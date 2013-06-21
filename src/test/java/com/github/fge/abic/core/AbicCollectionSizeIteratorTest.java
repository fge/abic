package com.github.fge.abic.core;

import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public final class AbicCollectionSizeIteratorTest
{
    @Test
    public void emptyCollectionHasCorrectSizeAndIterator()
    {
        final Collection<Integer> c
            = new TestCollection<Integer>(Integer.class);

        assertEquals(c.size(), 0);
        assertTrue(c.isEmpty());
        assertFalse(c.iterator().hasNext());
        try {
            c.iterator().next();
            fail("No exception thrown!");
        } catch (NoSuchElementException ignored) {
        }
    }

    @Test
    public void nonEmptyCollectionHasCorrectSizeAndIterator()
    {
        final String s1 = "hello";
        final String s2 = "world";
        final String s3 = "goodbye";

        final Collection<String> c
            = new TestCollection<String>(String.class, s1, s2, s3);

        assertEquals(c.size(), 3);
        assertFalse(c.isEmpty());

        final Iterator<String> iterator = c.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), s1);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), s2);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), s3);
        assertFalse(iterator.hasNext());
        try {
            iterator.next();
            fail("No exception thrown!");
        } catch (NoSuchElementException ignored) {
        }
    }
}
