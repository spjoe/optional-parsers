
package com.github.spjoe.optional;

import com.github.spjoe.functional.ThrowableSupplier;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.function.Function;

/**
 * Utility class which provides parse functions. This functions are designed to be used in the
 * {@link java.util.Optional#flatMap(Function)}} method as parameter.
 *<pre>
 *     {@code
 *     Optional.ofNullable(System.getProperty("some.property.key"))
 *          .flatMap(OptionalParsers::parseLong)
 *          .orElse(DEFAULT_VALUE_OF_SOME_PROPERTY);
 *     }
 *</pre>
 *
 */
public final class OptionalParsers {
    private OptionalParsers() {
    }

    /**
     * Parses the string argument as a signed decimal long.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link Long}, otherwise {@link Optional#empty()}.
     * @param longString which could representing a valid long of radix 10.
     * @return an {@link Optional}.
     */
    public static Optional<Long> parseLong(final String longString) {
        return parseAnyNumber(() -> Long.parseLong(longString));
    }

    /**
     * Parses the string argument as a signed long in the radix specified by the second argument.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link Long}, otherwise {@link Optional#empty()}.
     * @param longString which could represent a valid long.
     * @param radix of the number.
     * @return an {@link Optional}.
     */
    public static Optional<Long> parseLong(final String longString, int radix) {
        return parseAnyNumber(() -> Long.parseLong(longString, radix));
    }

    /**
     * Parses the string argument as a signed decimal integer.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link Integer}, otherwise {@link Optional#empty()}.
     * @param integerString which could represent a valid integer.
     * @return an {@link Optional}.
     */
    public static Optional<Integer> parseInt(final String integerString) {
        return parseAnyNumber(() -> Integer.parseInt(integerString));
    }

    /**
     * Parses the string argument as a signed integer in the radix specified by the second argument.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link Integer}, otherwise {@link Optional#empty()}.
     * @param integerString which could represent a valid integer.
     * @param radix of the number.
     * @return an {@link Optional}.
     */
    public static Optional<Integer> parseInt(final String integerString, int radix) {
        return parseAnyNumber(() -> Integer.parseInt(integerString, radix));
    }

    /**
     * Parses the string argument as a signed floating-point number.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link Float}, otherwise {@link Optional#empty()}.
     * @param floatString which could represent a valid float.
     * @return an {@link Optional}.
     */
    public static Optional<Float> parseFloat(final String floatString) {
        return parseAnyNumber(() -> Float.parseFloat(floatString));
    }

    /**
     * Parses the string argument as a signed floating-point number.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link Double}, otherwise {@link Optional#empty()}.
     * @param doubleString which could represent a valid double.
     * @return an {@link Optional}.
     */
    public static Optional<Double> parseDouble(final String doubleString) {
        return parseAnyNumber(() -> Double.parseDouble(doubleString));
    }

    /**
     * Parses the string argument as a signed floating-point number.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link BigDecimal}, otherwise {@link Optional#empty()}.
     * @param decimalString which could represent a valid decimal.
     * @return an {@link Optional}.
     */
    public static Optional<BigDecimal> parseBigDecimal(final String decimalString) {
        return parseAnyNumber(() -> new BigDecimal(decimalString));
    }

    /**
     * Parses the string argument as a signed integer.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link BigInteger}, otherwise {@link Optional#empty()}.
     * @param bigIntegerString which could represent a valid integer.
     * @return an {@link Optional}.
     */
    public static Optional<BigInteger> parseBigInteger(final String bigIntegerString) {
        return parseAnyNumber(() -> new BigInteger(bigIntegerString));
    }

    /**
     * Parses the string argument as a signed integer in the radix specified by the second argument.
     * If parsing is successful it returns an {@link Optional} containing the resulting {@link BigInteger}, otherwise {@link Optional#empty()}.
     * @param bigIntegerString which could represent a valid integer.
     * @param radix  of the number.
     * @return an {@link Optional}.
     */
    public static Optional<BigInteger> parseBigInteger(final String bigIntegerString, final int radix) {
        return parseAnyNumber(() -> new BigInteger(bigIntegerString, radix));
    }

    /**
     * Parses the string argument as a boolean.
     * If the given string reads case insensitive "false" then an {@link Optional} containing {@link Boolean#FALSE} returns.
     * If the given string reads case insensitive "true" then an {@link Optional} containing {@link Boolean#TRUE} returns.
     * Otherwise {@link Optional#empty()} returns.
     * @param booleanString which could represent a valid boolean.
     * @return an {@link Optional}.
     */
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
