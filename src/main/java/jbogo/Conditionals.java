package jbogo.util;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 *
 */
public class Conditionals
{
    static public String notEmptyOr(String lhs, String elseObj)
    {
        return (lhs != null && !lhs.trim().isEmpty()) ? lhs : elseObj;
    }

    static public <T> T notNullOr(T lhs, T elseObj)
    {
        return (lhs != null) ? lhs : elseObj;
    }

    static public <T> T notNullOrThrow(T lhs, Supplier<? extends RuntimeException> elseThrowSupplier)
    {
        if (lhs == null) {
            throw elseThrowSupplier.get();
        }

        return lhs;
    }

    static public <T> T elvis(T lhs, T rhs, Supplier<T> elseObjSupplier)
    {
        if (lhs == null) {
            return (rhs == null) ? null : elseObjSupplier.get();
        } else {
            return (lhs.equals(rhs)) ? lhs : elseObjSupplier.get();
        }
    }

    static public <T> T elvis(T lhs, T rhs, UnaryOperator<T> elseObjUnary)
    {
        if (lhs == null) {
            return (rhs == null) ? null : elseObjUnary.apply(null);
        } else {
            return (lhs.equals(rhs)) ? lhs : elseObjUnary.apply(lhs);
        }
    }

    static public <T> T elvisThrow(T lhs, T rhs, Supplier<? extends RuntimeException> elseThrowSupplier)
    {
        if (lhs == null) {
            if (rhs == null) {
                return null;
            }
        } else {
            if (lhs.equals(rhs)) {
                return lhs;
            }
        }

        throw elseThrowSupplier.get();
    }

    static public <T> T elvisThrow(T lhs, T rhs, Class<? extends RuntimeException> elseThrowClass)
    {
        if (lhs == null) {
            if (rhs == null) {
                return null;
            }
        } else {
            if (lhs.equals(rhs)) {
                return lhs;
            }
        }

        try {
            throw elseThrowClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
