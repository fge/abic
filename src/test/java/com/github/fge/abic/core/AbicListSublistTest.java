package com.github.fge.abic.core;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public final class AbicListSublistTest
{
    private List<String> list;

    @BeforeMethod
    public void initList()
    {
        list = new AbicList<String>(String.class, "hello", "world", "of",
            "misery", "and", "lust");
    }

    @Test
    public void subListWithWrongArgumentsThrowsIOOB()
    {
        try {
            list.subList(-1, 3);
            fail("No exception thrown!");
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            list.subList(0, 7);
            fail("No exception thrown!");
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            list.subList(5, 3);
            fail("No exception thrown!");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test
    public void subListsAreCorrectlyComputed()
    {
        assertEquals(list, list.subList(0, list.size()));

        final List<String> list2 = new ArrayList<String>(list);
        final List<String> sub1 = list.subList(2, 5);
        final List<String> sub2 = list2.subList(2, 5);

        assertEquals(sub1, sub2);

        assertTrue(list.subList(4, 4).isEmpty());
    }

    private static final class MrBean
    {
        private int val = 0;

        private int getVal()
        {
            return val;
        }

        private void setVal(final int val)
        {
            this.val = val;
        }
    }
}
