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
    /**
     * Gets a result.
     * @return a result
     * @throws E a generic throwable.
     */
    T get() throws E;
}