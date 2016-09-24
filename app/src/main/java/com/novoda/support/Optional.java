// All rights reserved by Novoda
package com.novoda.support;

public final class Optional<T> {

    @SuppressWarnings("unchecked")
    private static final Optional ABSENT = new Optional(null);

    private final T data;

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> absent() {
        return ABSENT;
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> fromNullable(T data) {
        if (data == null) {
            return ABSENT;
        }
        return new Optional<>(data);
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> of(T data) {
        if (data == null) {
            throw new IllegalStateException("Data cannot be null. Use Optional.fromNullable(maybeNullData).");
        }
        return new Optional<>(data);
    }

    private Optional(T data) {
        this.data = data;
    }

    public boolean isPresent() {
        return data != null;
    }

    public boolean isAbsent() {
        return !isPresent();
    }

    public T get() {
        if (!isPresent()) {
            throw new IllegalStateException("You must check if data is present before using get()");
        }
        return data;
    }

    public T or(T elseCase) {
        return isPresent() ? get() : elseCase;
    }

    public Optional<T> or(Optional<T> elseCase) {
        return isPresent() ? this : elseCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Optional<?> optional = (Optional<?>) o;

        return data != null ? data.equals(optional.data) : optional.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("Optional<%s>", isAbsent() ? "Absent" : data.toString());
    }
}
