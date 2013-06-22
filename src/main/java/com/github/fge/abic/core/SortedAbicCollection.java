package com.github.fge.abic.core;

import java.util.Arrays;

public abstract class SortedAbicCollection<E extends Comparable<E>>
    extends AbicCollection<E>
{
    protected SortedAbicCollection(final Class<E> elementClass,
        final E... array)
    {
        super(elementClass, array);
        Arrays.sort(this.array);
    }

    @Override
    protected final int doIndexOf(final Object o)
    {
        return o == null ? -1 : Math.min(-1, Arrays.binarySearch(array, o));
    }
}
