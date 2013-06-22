package com.github.fge.abic.core;

import com.github.fge.abic.AbicMessageBundle;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.serviceloader.MessageBundleFactory;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public final class ArrayHelpersTest
{
    private static final MessageBundle BUNDLE
        = MessageBundleFactory.getBundle(AbicMessageBundle.class);

    @Test
    public void helperRefusesNullElementClass()
    {
        try {
            ArrayHelpers.elementsOf(null, null);
            fail("No exception thrown");
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(),
                BUNDLE.getMessage("core.nullElementClass"));
        }
    }

    @Test
    public void helperRefusesNullCollection()
    {
        try {
            ArrayHelpers.elementsOf(Double.class, null);
            fail("No exception thrown!");
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(),
                BUNDLE.getMessage("core.nullCollection"));
        }
    }

    @Test
    public void helperRefusesNullArray()
    {
        try {
            ArrayHelpers.uniqueArrayElementsOf(Integer.class, null);
            fail("No exception thrown!");
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), BUNDLE.getMessage("core.nullArray"));
        }
    }

    @Test
    public void helperReallyRetainsUniqueElements()
    {
        final String[] expected = { "hello", "world", "" };
        final String[] actual = ArrayHelpers.uniqueArrayElementsOf(String.class,
            "hello", "world", "world", "hello", "hello", "", "");
        assertTrue(Arrays.equals(actual, expected));
    }
}
