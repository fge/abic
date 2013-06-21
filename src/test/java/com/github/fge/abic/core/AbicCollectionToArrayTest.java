package com.github.fge.abic.core;

import com.github.fge.msgsimple.bundle.MessageBundle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.testng.Assert.*;

public final class AbicCollectionToArrayTest
{
    private static final MessageBundle BUNDLE = AbicCollection.BUNDLE;
    private static final Collection<String> MODEL
        = Arrays.asList("string1", "hello", "world", "whatever", "meh");

    private TestCollection<String> c;

    @BeforeMethod
    public void initCollection()
    {
        c = new TestCollection<String>(String.class, "string1", "hello",
            "world", "whatever", "meh");
    }

    @Test
    public void baseToArrayWorksCorrectly()
    {
        final Object[] expected = MODEL.toArray();
        final Object[] actual = c.toArray();

        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void typedToArrayWithUndersizedArgumentWorks()
    {
        final String[] expected = MODEL.toArray(new String[0]);
        final String[] actual = c.toArray(new String[0]);

        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void typedToArrayWithExactSizedArgumentWorks()
    {
        final String[] expected = MODEL.toArray(new String[MODEL.size()]);
        final String[] actual = c.toArray(new String[c.size()]);

        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void typedToArrayWithOversizedArgumentWorks()
    {
        final String[] array = new String[10];
        array[8] = "meh";

        final String[] expected = MODEL.toArray(new String[10]);
        final String[] actual = c.toArray(array);

        assertNull(array[8]);
        assertSame(actual, array);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void typedToArrayThrowsNPEOnNullArray()
    {
        try {
            c.toArray(null);
            fail("No exception thrown!");
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), BUNDLE.getMessage("core.nullArray"));
        }
    }

    @Test
    public void typedToArrayThrowsArrayStoreExceptionWhenAppropriate()
    {
        try {
            c.toArray(new Integer[0]);
            fail("No exception thrown!");
        } catch (ArrayStoreException e) {
            assertEquals(e.getMessage(), BUNDLE.printf("core.arrayStore",
                Integer.class.getCanonicalName(),
                String.class.getCanonicalName()));
        }
    }

    @Test
    public void typedToArrayWithSubtypeIsOK()
    {
        final Collection<Integer> collection
            = new TestCollection<Integer>(Integer.class, 1, 2, 3);
        final Number[] array = collection.toArray(new Number[0]);

        assertEquals(array[0], new Integer(1));
        assertEquals(array[1], new Integer(2));
        assertEquals(array[2], new Integer(3));
    }
}
