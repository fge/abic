package com.github.fge.abic.core;

import java.util.Collection;

final class TestCollection<E>
    extends AbicCollection<E>
{

    TestCollection(final Class<E> elementClass, final E... array)
    {
        super(elementClass, array);
    }

    @Override
    public boolean contains(final Object o)
    {
        // We don't care, this is not what is tested
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c)
    {
        // We don't care, this is not what is tested
        return false;
    }
}
