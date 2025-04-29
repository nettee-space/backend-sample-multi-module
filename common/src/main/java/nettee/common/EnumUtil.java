package nettee.common;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumUtil {

    private EnumUtil() {}

    /**
     * 모든 열거형(enum)의 필드 값이 고유한지 검사합니다.
     * @param enumClass
     * @param getter
     * @return
     * @param <E>
     * @param <T>
     */
    public static <E extends Enum<E>, T> boolean isUniqueAllOf(Class<E> enumClass, Function<E, T> getter) {
        return EnumSet.allOf(enumClass).stream()
                .map(getter)
                .collect(Collectors.toSet())
                .size() == enumClass.getEnumConstants().length;
    }

}