package org.atanasov.bookshop.utils;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class EnumUtils {

  public static <T extends Enum<T>> LinkedHashMap<T, String> ENUM_MAP(Class<T> enumClass) {
    LinkedHashMap<T, String> map = new LinkedHashMap<>();
    Stream.of(enumClass.getEnumConstants())
        .sorted((a, b) -> a.toString().compareToIgnoreCase(b.toString()))
        .forEach(t -> map.put(t, t.toString()));
    return map;
  }

  public static <T extends Enum<T>> Optional<T> fromString(String string, Class<T> enumClass) {
    return Stream.of(enumClass.getEnumConstants())
        .filter(t -> t.name().equalsIgnoreCase(string))
        .findAny();
  }

  public static <A extends Enum<A>, B extends Enum<B>> B convertNullableByEnumName(
      final A value, final Class<B> clazz) {
    return value == null ? null : Enum.valueOf(clazz, value.name());
  }

  public static <T extends Enum<T>> boolean has(T enumType, Class<T> enumClass) {
    return Stream.of(enumClass.getEnumConstants())
        .anyMatch(t -> t.name().equalsIgnoreCase(enumType.name()));
  }

  public static <T extends Enum<T>> boolean has(String enumName, Class<T> enumClass) {
    return Stream.of(enumClass.getEnumConstants())
        .anyMatch(t -> t.name().equalsIgnoreCase(enumName));
  }

  private EnumUtils() {}
}
