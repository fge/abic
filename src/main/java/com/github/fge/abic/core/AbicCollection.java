package com.github.fge.abic.core;

import com.github.fge.abic.AbicMessageBundle;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.serviceloader.MessageBundleFactory;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class AbicCollection<E>
    implements Collection<E>
{
    protected static final MessageBundle BUNDLE
        = MessageBundleFactory.getBundle(AbicMessageBundle.class);

    protected final E[] array;
    protected final int size;
    protected final Class<E> elementClass;

    protected AbicCollection(final Class<E> elementClass,
        final E... array)
    {
        BUNDLE.checkNotNull(elementClass, "core.nullElementClass");
        BUNDLE.checkNotNull(array, "core.nullArray");
        this.elementClass = elementClass;
        size = array.length;
        this.array = (E[]) Array.newInstance(elementClass, size);

        for (int i = 0; i < size; i++)
            this.array[i] = BUNDLE.checkNotNull(array[i], "core.nullElement");
    }

    @Override
    public final int size()
    {
        return size;
    }

    @Override
    public final boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public final Iterator<E> iterator()
    {
        return getIterator();
    }

    @Override
    public final Object[] toArray()
    {
        return Arrays.copyOf(array, size);
    }

    @Override
    public final <T> T[] toArray(final T[] a)
    {
        BUNDLE.checkNotNull(a, "core.nullArray");
        final Class<?> targetClass = a.getClass().getComponentType();
        if (!targetClass.isAssignableFrom(elementClass))
            throw new ArrayStoreException(BUNDLE.printf("core.arrayStore",
                targetClass.getCanonicalName(),
                elementClass.getCanonicalName()));

        final int targetSize = a.length;

        if (targetSize < size)
            return (T[]) Arrays.copyOf(array, size, a.getClass());

        int index = 0;
        while (index < size) {
            a[index] = (T) array[index];
            index++;
        }
        while (index < targetSize)
            a[index++] = null;

        return a;
    }

    @Override
    public final boolean add(final E e)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean remove(final Object o)
    {
        throw new UnsupportedOperationException();
    }

    protected abstract int doIndexOf(final Object o);

    @Override
    public final boolean contains(final Object o)
    {
        return doIndexOf(o) != -1;
    }

    @Override
    public final boolean containsAll(final Collection<?> c)
    {
        BUNDLE.checkNotNull(c, "core.nullArgument");
        for (final Object o: c)
            if (!contains(o))
                return false;
        return true;
    }


    @Override
    public final boolean addAll(final Collection<? extends E> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean removeAll(final Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean retainAll(final Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void clear()
    {
        throw new UnsupportedOperationException();
    }

    protected final ListIterator<E> getIterator()
    {
        return new AbicIterator<E>(this);
    }

    protected final ListIterator<E> getIterator(final int index)
    {
        return new AbicIterator<E>(this, index);
    }

    @Override
    public final String toString()
    {
        if (size == 0)
            return "[]";
        final StringBuilder sb = new StringBuilder("[").append(array[0]);
        for (int i = 1; i < size; i++)
            sb.append(", ").append(array[i]);
        return sb.append(']').toString();
    }

    private static final class AbicIterator<V>
        implements ListIterator<V>
    {
        private final V[] array;
        private final int size;
        private int index;

        private AbicIterator(final AbicCollection<V> collection,
            final int index)
        {
            array = collection.array;
            size = collection.size;
            this.index = index;
        }

        private AbicIterator(final AbicCollection<V> collection)
        {
            this(collection, 0);
        }

        @Override
        public boolean hasNext()
        {
            return index < size;
        }

        @Override
        public V next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            return array[index++];
        }

        @Override
        public boolean hasPrevious()
        {
            return index > 0;
        }

        @Override
        public V previous()
        {
            if (!hasPrevious())
                throw new NoSuchElementException();
            return array[--index];
        }

        @Override
        public int nextIndex()
        {
            return index;
        }

        @Override
        public int previousIndex()
        {
            return index - 1;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final V e)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(final V e)
        {
            throw new UnsupportedOperationException();
        }
    }
}
