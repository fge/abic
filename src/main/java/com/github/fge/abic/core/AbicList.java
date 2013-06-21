package com.github.fge.abic.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public final class AbicList<E>
    extends AbicCollection<E>
    implements List<E>
{
    private final int hashCode;

    public AbicList(final Class<E> elementClass, final E... array)
    {
        super(elementClass, array);
        hashCode = Arrays.hashCode(this.array);
    }

    @Override
    public boolean contains(final Object o)
    {
        return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(final Collection<?> c)
    {
        BUNDLE.checkNotNull(c, "core.nullArgument");
        for (final Object o: c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(final int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return array[index];
    }

    @Override
    public E set(final int index, final E element)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final int index, final E element)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(final int index)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object o)
    {
        if (o == null)
            return -1;
        for (int i = 0; i < size; i++)
            if (array[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(final Object o)
    {
        if (o == null)
            return -1;
        for (int i = size - 1; i > 0; i--)
            if (array[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return getIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return getIterator(index);
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex)
    {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException();
        if (toIndex > size)
            throw new IndexOutOfBoundsException();
        if (fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        return new AbicList<E>(elementClass,
            Arrays.copyOfRange(array, fromIndex, toIndex));
    }

    @Override
    public int hashCode()
    {
        return hashCode;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof List))
            return false;
        if (this == obj)
            return true;
        final List<?> other = (List<?>) obj;
        if (size != other.size())
            return false;
        for (int i = 0; i < size; i++)
            if (!array[i].equals(other.get(i)))
                return false;
        return true;
    }
}
