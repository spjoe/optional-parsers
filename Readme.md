Optional Parsers
================

A simple numbers and boolean parser which returns a Optional and do not
raise any Exception.

This library has no third party compile time dependencies, 
the only dependency is Java8.

Motivation
==========
Use this parser functions to easily integrate in your optional call 
chain.

Usage
=====

Example usage:
```java
Optional.ofNullable(System.getProperty("some.property.key"))
        .flatMap(OptionalParsers::parseLong)
        .orElse(DEFAULT_VALUE_OF_SOME_PROPERTY);
```

API
===

```java
    public static Optional<Long> parseLong(final String longString);
    public static Optional<Long> parseLong(final String longString, int radix);
    
    public static Optional<Integer> parseInt(final String integerString);
    public static Optional<Integer> parseInt(final String integerString, int radix);
    
    public static Optional<BigInteger> parseBigInteger(final String bigIntegerString);
    public static Optional<BigInteger> parseBigInteger(final String bigIntegerString, final int radix);
    
    public static Optional<BigDecimal> parseBigDecimal(final String decimalString);
    
    public static Optional<Float> parseFloat(final String floatString);
    
    public static Optional<Double> parseDouble(final String doubleString);

    public static Optional<Boolean> parseBoolean(final String booleanString);
```


Changelog
=========
v0.0.1
------
### Released at 2016-xx-xx
- Initial release of this library