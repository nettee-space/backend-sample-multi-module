package nettee.common.marker;

public sealed interface TypeSafeMarker permits TypeSafeMarker.Present, TypeSafeMarker.Missing {
    final class Present implements TypeSafeMarker { private Present() {} }
    final class Missing implements TypeSafeMarker { private Missing() {} }
}
