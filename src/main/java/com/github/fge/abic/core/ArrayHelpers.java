package com.github.fge.abic.core;

import com.github.fge.abic.AbicMessageBundle;
import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.serviceloader.MessageBundleFactory;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

final class ArrayHelpers
{
    private static final MessageBundle BUNDLE
        = MessageBundleFactory.getBundle(AbicMessageBundle.class);

    private ArrayHelpers()
    {
    }

    @SuppressWarnings("unchecked")
    static <T> T[] elementsOf(final Class<T> elementClass,
        final Collection<T> collection)
    {
        BUNDLE.checkNotNull(elementClass, "core.nullElementClass");
        BUNDLE.checkNotNull(collection, "core.nullCollection");
        final int size = collection.size();
        final T[] ret = (T[]) Array.newInstance(elementClass, size);
        int i = 0;
        for (final T element: collection)
            ret[i++] = BUNDLE.checkNotNull(element, "core.nullElement");
        return ret;
    }

    static <T> T[] uniqueElementsOf(final Class<T> elementClass,
        final Collection<T> collection)
    {
        return elementsOf(elementClass, new HashSet<T>(collection));
    }

    static <T> T[] uniqueArrayElementsOf(final Class<T> elementClass,
        final T... elements)
    {
        BUNDLE.checkNotNull(elements, "core.nullArray");
        final Set<T> set = new LinkedHashSet<T>(elements.length);
        Collections.addAll(set, elements);
        return elementsOf(elementClass, set);
    }
}
