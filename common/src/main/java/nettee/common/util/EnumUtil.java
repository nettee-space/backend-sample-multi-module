package nettee.common.util;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumUtil {

    private EnumUtil() {}

    public static <E extends Enum<E>, T> boolean isUniqueAllOf(Class<E> enumClass, Function<E, T> getter) {
        return EnumSet.allOf(enumClass).stream()
                .map(getter)
                .collect(Collectors.toSet())
                .size() == enumClass.getEnumConstants().length;
    }
}