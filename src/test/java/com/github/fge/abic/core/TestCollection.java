package com.github.fge.abic.core;

final class TestCollection<E>
    extends AbicCollection<E>
{

    TestCollection(final Class<E> elementClass, final E... array)
    {
        super(elementClass, array);
    }

    @Override
    protected int doIndexOf(final Object o)
    {
        // We don't care, this is not what is tested
        return 0;
    }
}
