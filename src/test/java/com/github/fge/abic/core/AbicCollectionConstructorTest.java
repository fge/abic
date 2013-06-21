package com.github.fge.abic.core;

import com.github.fge.abic.AbicMessageBundle;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.serviceloader.MessageBundleFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public final class AbicCollectionConstructorTest
{
    private static final MessageBundle BUNDLE
        = MessageBundleFactory.getBundle(AbicMessageBundle.class);

    @Test
    public void nullElementClassIsNotAccepted()
    {
        try {
            new TestCollection<Integer>(null, null);
            fail("No exception thrown!");
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(),
                BUNDLE.getMessage("core.nullElementClass"));
        }
    }

    @Test
    public void nullElementIsNotAccepted()
    {
        try {
            new TestCollection<Object>(Object.class, new Object(), null);
            fail("No exception thrown!");
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(),
                BUNDLE.getMessage("core.nullElement"));
        }
    }

    @Test
    public void whenFedWithCorrectElementsConstructionSucceeds()
    {
        new TestCollection<Integer>(Integer.class, 1, 2, 3);
        assertTrue(true);
    }
}
