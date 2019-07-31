package com.indusfo.spc.sdk.utils;

import com.indusfo.spc.sdk.common.exception.SpcRuntimeException;
import com.indusfo.spc.sdk.common.exception.impl.SysException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;

/**
 * @Descirption The same as org.springframework.util.Assert, but throw SpcRuntimeException
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/22 16:13
 *
 * @see org.springframework.util.Assert
 */
public abstract class SpcAssert {

    /**
     * Assert a boolean expression, throwing {@code SpcRuntimeException}
     * if the assertion result is {@code false}.
     * @param expression a boolean expression
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @throws SpcRuntimeException if expression is {@code false}
     */

    public static void isTrue(boolean expression, int code, String message){
        if(!expression){
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void isTrue(boolean expression, int code) {
        isTrue(expression, code, "[Assertion failed] - this expression must be true");
    }

    public static void isTrue(boolean expression, Class<? extends SpcRuntimeException> spcRuntimeExceptionClazz, Object... args) {
        if (!expression) {
            passCodeException(spcRuntimeExceptionClazz, args);
        }
    }

    /**
     * Assert that an object is {@code null}
     * @param object the object to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @throws SpcRuntimeException if the object is not {@code null}
     */
    public static void isNull(Object object, int code, String message){
        if(object != null){
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void isNull(Object object, int code) {
        isNull(object, code, "[Assertion failed] - the object argument must be null");
    }

    public static void isNull(Object object,  Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (object != null) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that an object is not {@code null}
     * @param object the object to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @throws SpcRuntimeException if the object is {@code null}
     */
    public static void notNull(Object object, int code, String message) {
        if (object == null) {
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void notNull(Object object, int code) {
        notNull(object, code, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void notNull(Object object,  Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (object == null) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that the given String is not empty;
     * that is, it must not be {@code null} and not the empty String.
     * @param text the String to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @see StringUtils#hasLength(String)
     */
    public static void hasLength(String text, int code, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void hasLength(String text, int code) {
        hasLength(text, code,
                "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static void hasLength(String text,  Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (!StringUtils.hasLength(text)) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that the given String has valid text content;
     * that is, it must not be {@code null} and must contain at least one non-whitespace character.
     * @param text the String to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @see StringUtils#hasText(String)
     */
    public static void hasText(String text, int code, String message) {
        if (!StringUtils.hasText(text)) {
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void hasText(String text, int code) {
        hasText(text, code,
                "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    public static void hasText(String text,  Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (!StringUtils.hasText(text)) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * @param textToSearch the text to search
     * @param substring the substring to find within the text
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     */
    public static void doesNotContain(String textToSearch, String substring, int code, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, int code) {
        doesNotContain(textToSearch, substring, code,
                "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
    }

    public static void doesNotContain(String textToSearch, String substring, Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that an array has elements; that is, it must not be
     * {@code null} and must have at least one element.
     * @param array the array to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @throws SpcRuntimeException if the object array is {@code null} or has no elements
     */
    public static void notEmpty(Object[] array, int code, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void notEmpty(Object[] array, int code) {
        notEmpty(array, code, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Object[] array,  Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (ObjectUtils.isEmpty(array)) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that an array has no null elements.
     * Note: Does not complain if the array is empty!
     * @param array the array to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @throws SpcRuntimeException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array, int code, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new SpcRuntimeException(code, message);
                }
            }
        }
    }

    public static void noNullElements(Object[] array, int code) {
        noNullElements(array, code, "[Assertion failed] - this array must not contain any null elements");
    }

    public static void noNullElements(Object[] array,  Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    passCodeException(clazz, args);
                }
            }
        }
    }

    /**
     * Assert that a collection has elements; that is, it must not be
     * {@code null} and must have at least one element.
     * @param collection the collection to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @throws SpcRuntimeException if the collection is {@code null} or has no elements
     */
    public static void notEmpty(Collection<?> collection, int code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void notEmpty(Collection<?> collection, int code) {
        notEmpty(collection, code,
                "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Collection<?> collection,  Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (CollectionUtils.isEmpty(collection)) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that a Map has entries;
     * that is, it must not be {@code null} and must have at least one entry.
     * @param map the map to check
     * @param code the errorCode
     * @param message the exception message to use if the assertion fails
     * @throws SpcRuntimeException if the map is {@code null} or has no entries
     */
    public static void notEmpty(Map<?, ?> map, int code, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new SpcRuntimeException(code, message);
        }
    }

    public static void notEmpty(Map<?, ?> map, int code) {
        notEmpty(map, code, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static void notEmpty(Map<?, ?> map, Class<? extends SpcRuntimeException> clazz, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            passCodeException(clazz, args);;
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * @param type the type to check against
     * @param obj the object to check
     * @param code the errorCode
     * @param message a message which will be prepended to the message produced by
     * the function itself, and which may be used to provide context. It should
     * normally end in a ": " or ". " so that the function generate message looks
     * ok when prepended to it.
     * @throws SpcRuntimeException if the object is not an instance of clazz
     * @see Class#isInstance(Object)
     */
    public static void isInstanceOf(Class<?> type, Object obj, int code, String message) {

        notNull(type, code, "Type to check against must not be null");

        if (!type.isInstance(obj)) {
            throw new SpcRuntimeException(
                    code, (StringUtils.hasLength(message) ? message + " " : "") +
                    "Object of class [" + (obj != null ? obj.getClass().getName() : "null") +
                    "] must be an instance of " + type);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, int code) {
        isInstanceOf(type, obj, code, "");
    }

    public static void isInstanceOf(Class<?> type, Object obj, Class<? extends SpcRuntimeException> clazz, Object... args) {
        notNull(type, clazz, args);
        if (!type.isInstance(obj)) {
            passCodeException(clazz, args);
        }
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * @param superType the super type to check against
     * @param subType the sub type to check
     * @param code the errorCode
     * @param message a message which will be prepended to the message produced by
     * the function itself, and which may be used to provide context. It should
     * normally end in a ": " or ". " so that the function generate message looks
     * ok when prepended to it.
     * @throws SpcRuntimeException if the classes are not assignable
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, int code, String message) {
        notNull(superType, code, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new SpcRuntimeException(code, message + subType + " is not assignable to " + superType);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, int code) {
        isAssignable(superType, subType, code, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, Class<? extends SpcRuntimeException> clazz, Object... args) {
        notNull(superType, clazz, args);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            passCodeException(clazz, args);
        }
    }


    private static void passCodeException(Class<? extends SpcRuntimeException> clazz, Object... args) {
        try {
            if (args != null && args.length > 0) {
                @SuppressWarnings(value = "rawtypes")
                Class[] parameterTypes = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    parameterTypes[i] = args[i].getClass();
                }
                Constructor<? extends SpcRuntimeException> constructor = clazz.getConstructor(parameterTypes);
                throw constructor.newInstance(args);
            } else {
                throw clazz.newInstance();
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            throw new SysException(e.getLocalizedMessage());
        }
    }

}
