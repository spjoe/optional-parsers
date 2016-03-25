package com.github.spjoe.optional.matcher;

import org.hamcrest.Matcher;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;

public final class OptionalMatchers {
    private OptionalMatchers() { }

    public static <T> Matcher<Optional<T>> hasValue(final Matcher<? super T> matcher) {
        return new OptionalHasValue<>(matcher);
    }

    public static <T> Matcher<Optional<T>> hasValue(final T value) {
        return new OptionalHasValue<>(equalTo(value));
    }

    public static <T> Matcher<Optional<T>> isEmpty() {
        return new OptionalHasValue<>(nullValue());
    }

    public static <T> Matcher<Optional<T>> isPresent() {
        return new OptionalHasValue<>(notNullValue());
    }
}
