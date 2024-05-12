package com.jyotish.api.core.models.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectUtils {

    /**
     * concatenate list items from all list passed into a single list
     * @param list1 any number of lists with same generic type
     * @return single list containing all items from all list
     * @param <T> any generic type
     */
    @SafeVarargs
    public static <T> List<T> joinList(List<T>... list1) {
        List<T> newList = new ArrayList<>();
        for (List<T> lists : list1) {
            newList.addAll(lists);
        }
        return newList;
    }

    @SafeVarargs
    public static <T> boolean allNonEmpty(List<T>... list1) {
        for (List<T> lists : list1) {
            if (lists == null || lists.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <T> List<T> toList(T... ts) {
        return new ArrayList<>(Arrays.asList(ts));
    }

    public static String[] toStringArray(List<String> strings) {
        return strings.toArray(new String[]{});
    }

    public static <T> boolean hasAny(List<T> list) {
        return !list.isEmpty();
    }

    public static <T> boolean hasNotAny(List<T> list) {
        return !hasAny(list);
    }

    public static <T> boolean has(T[] array, T value) {
        for (T valueFromArray : array) {
            if (valueFromArray.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean has(List<T> array, T value) {
        for (T valueFromArray : array) {
            if (valueFromArray.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
