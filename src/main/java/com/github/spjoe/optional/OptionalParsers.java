
package com.github.spjoe.optional;

import com.github.spjoe.functional.ThrowableSupplier;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public final class OptionalParsers {
    private OptionalParsers() {
    }

    public static Optional<Long> parseLong(final String longString) {
        return parseAnyNumber(() -> Long.parseLong(longString));
    }

    public static Optional<Long> parseLong(final String longString, int radix) {
        return parseAnyNumber(() -> Long.parseLong(longString, radix));
    }

    public static Optional<Integer> parseInt(final String integerString) {
        return parseAnyNumber(() -> Integer.parseInt(integerString));
    }

    public static Optional<Integer> parseInt(final String integerString, int radix) {
        return parseAnyNumber(() -> Integer.parseInt(integerString, radix));
    }

    public static Optional<Float> parseFloat(final String floatString) {
        return parseAnyNumber(() -> Float.parseFloat(floatString));
    }

    public static Optional<Double> parseDouble(final String doubleString) {
        return parseAnyNumber(() -> Double.parseDouble(doubleString));
    }

    public static Optional<BigDecimal> parseBigDecimal(final String decimalString) {
        return parseAnyNumber(() -> new BigDecimal(decimalString));
    }

    public static Optional<BigInteger> parseBigInteger(final String bigIntegerString) {
        return parseAnyNumber(() -> new BigInteger(bigIntegerString));
    }

    public static Optional<BigInteger> parseBigInteger(final String bigIntegerString, final int radix) {
        return parseAnyNumber(() -> new BigInteger(bigIntegerString, radix));
    }

    public static Optional<Boolean> parseBoolean(final String booleanString) {
        if (booleanString == null) {
            return Optional.empty();
        } else if (booleanString.equalsIgnoreCase("false")) {
            return Optional.of(false);
        } else if (booleanString.equalsIgnoreCase("true")) {
            return Optional.of(true);
        }

        return Optional.empty();
    }

    private static <T> Optional<T> parseAnyNumber(final ThrowableSupplier<T, NumberFormatException> parser) {
        try {
            return Optional.of(parser.get());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
