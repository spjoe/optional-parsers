/*
    Copyright 2016 Camillo Dell'mour

    Licensed under the Apache License,Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.github.spjoe.optional;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static com.github.spjoe.optional.matcher.OptionalMatchers.hasValue;
import static com.github.spjoe.optional.matcher.OptionalMatchers.isEmpty;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

// TODO 2016-03-25: Use https://github.com/npathai/hamcrest-optional when it has a good license.

@RunWith(DataProviderRunner.class)
public class OptionalParsersTest {
    private static Integer TEST_RADIX = 8;

    @DataProvider
    public static Object[][] longExampleValues() {
        return new Object[][] {
                { 0L }, { 1L }, { -1L },
                { TEST_RADIX.longValue() }, { -TEST_RADIX.longValue() },
                { Long.MAX_VALUE }, { Long.MIN_VALUE }
        };
    }

    @DataProvider
    public static Object[][] intExampleValues() {
        return new Object[][] {
                { 0 }, { 1 }, { -1 },
                { TEST_RADIX }, { -TEST_RADIX },
                { Integer.MAX_VALUE }, { Integer.MIN_VALUE }
        };
    }

    @DataProvider
    public static Object[][] floatExampleValues() {
        return new Object[][] {
                { 0f }, { 1f }, { -1f },
                { TEST_RADIX.floatValue() }, { -TEST_RADIX.floatValue() },
                { Float.MAX_VALUE }, { Float.MIN_VALUE }
        };
    }

    @DataProvider
    public static Object[][] doubleExampleValues() {
        return new Object[][] {
                { 0d }, { 1d }, { -1d },
                { TEST_RADIX.doubleValue() }, { -TEST_RADIX.doubleValue() },
                { Double.MAX_VALUE }, { Double.MIN_VALUE }
        };
    }

    @DataProvider
    public static Object[][] booleanExampleValues() {
        return new Object[][] {
                { "invalidBooleanString", isEmpty() }, { null, isEmpty() },
                { "false", hasValue(false) }, { "true", hasValue(true) },
                { "FaLSe", hasValue(false) }, { "tRuE", hasValue(true) },
        };
    }

    @Test
    @UseDataProvider("longExampleValues")
    public void parseLong_aValidLongString_shallReturnPresentOptional(final Long aLong) throws Exception {
        Optional<Long> actual = OptionalParsers.parseLong(aLong.toString());

        assertThat(actual, hasValue(aLong));
    }

    @Test
    @UseDataProvider("longExampleValues")
    public void parseLongWithRadix_aValidLongString_shallReturnPresentOptional(final Long aLong) throws Exception {
        Optional<Long> actual = OptionalParsers.parseLong(Long.toString(aLong, TEST_RADIX), TEST_RADIX);

        assertThat(actual, hasValue(aLong));
    }

    @Test
    public void parseLong_aInvalidLongString_shallReturnEmpty() throws Exception {
        Optional<Long> actual = OptionalParsers.parseLong("aInvalidLongString");

        assertThat(actual, isEmpty());
    }

    @Test
    @UseDataProvider("intExampleValues")
    public void parseInt_aValidIntegerString_shallReturnPresentOptional(final Integer anInteger) throws Exception {
        Optional<Integer> actual = OptionalParsers.parseInt(anInteger.toString());

        assertThat(actual, hasValue(anInteger));
    }

    @Test
    @UseDataProvider("intExampleValues")
    public void parseIntWithRadix_aValidLongString_shallReturnPresentOptional(final Integer anInteger) throws Exception {
        Optional<Integer> actual = OptionalParsers.parseInt(Integer.toString(anInteger, TEST_RADIX), TEST_RADIX);

        assertThat(actual, hasValue(anInteger));
    }

    @Test
    public void parseInt_aInvalidIntegerString_shallReturnEmpty() throws Exception {
        Optional<Integer> actual = OptionalParsers.parseInt("aInvalidIntegerString");

        assertThat(actual, isEmpty());
    }

    @Test
    @UseDataProvider("floatExampleValues")
    public void parseFloat_aValidFloatString_shallReturnPresentOptional(final Float aFloat) throws Exception {
        Optional<Float> actual = OptionalParsers.parseFloat(aFloat.toString());

        assertThat(actual, hasValue(aFloat));
    }

    @Test
    public void parseFloat_aInvalidFloatString_shallReturnEmpty() throws Exception {
        Optional<Float> actual = OptionalParsers.parseFloat("aInvalidFloatString");

        assertThat(actual, isEmpty());
    }

    @Test
    @UseDataProvider("doubleExampleValues")
    public void parseDouble_aValidDoubleString_shallReturnPresentOptional(final Double aDouble) throws Exception {
        Optional<Double> actual = OptionalParsers.parseDouble(aDouble.toString());

        assertThat(actual, hasValue(aDouble));
    }

    @Test
    public void parseDouble_aInvalidDoubleString_shallReturnEmpty() throws Exception {
        Optional<Double> actual = OptionalParsers.parseDouble("aInvalidDoubleString");

        assertThat(actual, isEmpty());
    }

    @Test
    public void parseBigDecimal_aValidBigDecimalString_shallReturnPresentOptional() throws Exception {
        Optional<BigDecimal> actual =
                OptionalParsers.parseBigDecimal("12342349450760456546756765756756756495687.548670498567054867094");

        assertThat(actual, hasValue(new BigDecimal("12342349450760456546756765756756756495687.548670498567054867094")));
    }

    @Test
    public void parseBigDecimal_aInvalidBigDecimalString_shallReturnEmpty() throws Exception {
        Optional<BigDecimal> actual = OptionalParsers.parseBigDecimal("aInvalidBigDecimalString");

        assertThat(actual, isEmpty());
    }

    @Test
    public void parseBigInteger_aValidBigIntegerString_shallReturnPresentOptional() throws Exception {
        Optional<BigInteger> actual =
                OptionalParsers.parseBigInteger("12342349450760456546756765756756756495687");

        assertThat(actual, hasValue(new BigInteger("12342349450760456546756765756756756495687")));
    }

    @Test
    public void parseBigIntegerWithRadix_aValidBigIntegerString_shallReturnPresentOptional() throws Exception {
        Optional<BigInteger> actual =
                OptionalParsers.parseBigInteger("2313256465465431321321654654", TEST_RADIX);

        assertThat(actual, hasValue(new BigInteger("2313256465465431321321654654", TEST_RADIX)));
    }

    @Test
    public void parseBigInteger_aInvalidBigIntegerString_shallReturnEmpty() throws Exception {
        Optional<BigInteger> actual = OptionalParsers.parseBigInteger("aInvalidBigIntegerString");

        assertThat(actual, isEmpty());
    }

    @Test
    @UseDataProvider("booleanExampleValues")
    public void parseBoolean(final String booleanString, final Matcher<Optional<Boolean>> matcher) throws Exception {
        Optional<Boolean> actual = OptionalParsers.parseBoolean(booleanString);

        assertThat(actual, matcher);
    }

    @Test
    public void ctor_none_shallBePrivateUtilityCtor() throws Exception {
        Constructor[] ctors = OptionalParsers.class.getDeclaredConstructors();
        assertThat("Utility class should only have one constructor", ctors.length, is(1));

        Constructor ctor = ctors[0];
        assertThat("Utility class constructor should be inaccessible", ctor.isAccessible(), is(false));

        ctor.setAccessible(true); // obviously we'd never do this in production
        assertThat("You'd expect the construct to return the expected type",
                ctor.newInstance().getClass(),
                equalTo(OptionalParsers.class));
    }
}