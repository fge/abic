package com.github.fge.abic.core;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.testng.Assert.assertEquals;

public final class AbicCollectionToStringTest
{
    @Test
    public void toStringShowsExpectedOutput()
    {
        final Collection<Object> c1 = Arrays.<Object>asList(1, "foo", 32.0);
        final Collection<Object> c2 = new TestCollection<Object>(Object.class,
            1, "foo", 32.0);

        assertEquals(c1.toString(), c2.toString());
    }
}
