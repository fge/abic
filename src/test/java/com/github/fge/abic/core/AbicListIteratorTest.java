package com.github.fge.abic.core;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public final class AbicListIteratorTest
{
    @Test
    public void listIteratorReportsNoNextOrPreviousIfEmptyList()
    {
        final List<Object> list = new AbicList<Object>(Object.class);

        final ListIterator<Object> iterator = list.listIterator();

        assertFalse(iterator.hasNext());
        try {
            iterator.next();
            fail("No exception thrown!");
        } catch (NoSuchElementException ignored) {
        }

        assertFalse(iterator.hasPrevious());
        try {
            iterator.previous();
            fail("No exception thrown!");
        } catch (NoSuchElementException ignored) {
        }

        assertEquals(iterator.nextIndex(), 0);
        assertEquals(iterator.previousIndex(), -1);
    }

    @Test
    public void nextAndPreviousFullyWalkTheList()
    {
        final List<String> list = new AbicList<String>(String.class, "foo",
            "bar", "baz", "meh", "ehm");

        final List<String> forward = new ArrayList<String>(list);
        final List<String> backwards = new ArrayList<String>(list);
        Collections.reverse(backwards);

        final List<String> tmp = new ArrayList<String>();

        final ListIterator<String> iterator = list.listIterator();

        while (iterator.hasNext())
            tmp.add(iterator.next());

        assertEquals(forward, tmp);
        assertEquals(iterator.nextIndex(), list.size());

        tmp.clear();

        while (iterator.hasPrevious())
            tmp.add(iterator.previous());

        assertEquals(backwards, tmp);
        assertEquals(iterator.previousIndex(), -1);
    }

    @Test
    public void preindexedListIteratorWithAnInvalidIndexBarfs()
    {
        final List<String> list = new AbicList<String>(String.class,
            "one", "two", "three");

        try {
            list.listIterator(-1);
            fail("No exception thrown!");
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            list.listIterator(3);
            fail("No exception thrown!");
        } catch (IndexOutOfBoundsException ignored) {
        }

        assertTrue(true);
    }

    @Test
    public void preindexedListIteratorWithAValidIndexWorks()
    {
        final List<Integer> list = new AbicList<Integer>(Integer.class,
            1, 2, 3, 4, 5, 6);

        ListIterator<Integer> iterator;

        iterator = list.listIterator(2);
        assertEquals(iterator.nextIndex(), 2);
        assertEquals(iterator.previousIndex(), 1);

        final List<Integer> tmp = new ArrayList<Integer>();
        while (iterator.hasNext())
            tmp.add(iterator.next());

        assertEquals(tmp, Arrays.asList(3, 4, 5, 6));

        iterator = list.listIterator(2);
        tmp.clear();

        while (iterator.hasPrevious())
            tmp.add(iterator.previous());

        assertEquals(tmp, Arrays.asList(2, 1));
    }
}
