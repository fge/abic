package com.github.fge.abic.core;

public abstract class RegularAbicCollection<E>
    extends AbicCollection<E>
{
    protected RegularAbicCollection(final Class<E> elementClass,
        final E... array)
    {
        super(elementClass, array);
    }

    @Override
    protected final int doIndexOf(final Object o)
    {
        if (o == null)
            return -1;
        for (int i = 0; i < size; i++)
            if (array[i].equals(o))
                return i;
        return -1;
    }
}
