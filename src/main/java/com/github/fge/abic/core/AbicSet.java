package com.github.fge.abic.core;

import java.util.Set;

public final class AbicSet<E>
    extends RegularAbicCollection<E>
    implements Set<E>
{
    private final int hashCode;

    public AbicSet(final Class<E> elementClass, final E... elements)
    {
        super(elementClass,
            ArrayHelpers.uniqueArrayElementsOf(elementClass, elements));
        int hash = 0;
        for (final E element: array)
            hash += element.hashCode();
        hashCode = hash;
    }

    @Override
    public int hashCode()
    {
        return hashCode;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof Set))
            return false;
        if (this == obj)
            return true;
        final Set<?> other = (Set<?>) obj;
        return size == other.size() && containsAll(other);
    }
}
