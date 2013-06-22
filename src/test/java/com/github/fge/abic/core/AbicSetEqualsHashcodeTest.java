package com.github.fge.abic.core;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public final class AbicSetEqualsHashcodeTest
{
    @Test
    public void emptySetEqualsHashcodeObeysTheContract()
    {
        final Set<Object> set = new AbicSet<Object>(Object.class);
        final Set<Object> set2 = new HashSet<Object>();

        assertEquals(set.hashCode(), 0);
        assertEquals(set.hashCode(), set2.hashCode());
        assertTrue(set.equals(set2));
        assertTrue(set2.equals(set));

        assertTrue(set.equals(set));
        assertFalse(set.equals(null));
        assertFalse(set.equals(new Object()));
    }
}
