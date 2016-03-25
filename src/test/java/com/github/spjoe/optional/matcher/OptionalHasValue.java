package com.github.spjoe.optional.matcher;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.Optional;

class OptionalHasValue<U> extends FeatureMatcher<Optional<U>, U> {
    public OptionalHasValue(Matcher<? super U> subMatcher) {
        super(subMatcher, "Optional has value", "value");
    }

    @Override
    protected U featureValueOf(final Optional<U> optional) {
        return optional.orElse(null);
    }
}
