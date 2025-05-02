package nettee.post.entity.type.builder;

import nettee.post.entity.type.builder.TypeSafeMarkers.Present;
import nettee.post.entity.type.builder.TypeSafeMarkers.Missing;

public sealed interface TypeSafeMarkers permits Present, Missing {
    non-sealed interface Present extends TypeSafeMarkers {}
    non-sealed interface Missing extends TypeSafeMarkers {}
}

