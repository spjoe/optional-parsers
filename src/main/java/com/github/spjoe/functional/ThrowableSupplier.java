package com.github.spjoe.functional;

// TODO 2016-03-25: extract this to a small functional support library.

/**
 * Same as {@link java.util.function.Supplier}, but it can throw an exception.
 *
 * @param <T> the type of result supplied by this supplier
 * @param <E> the type of exception to be handled
 */
@FunctionalInterface
public interface ThrowableSupplier<T, E extends Throwable> {
    T get() throws E;
}