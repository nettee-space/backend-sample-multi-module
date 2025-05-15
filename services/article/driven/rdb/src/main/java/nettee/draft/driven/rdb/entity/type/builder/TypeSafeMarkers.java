package nettee.draft.driven.rdb.entity.type.builder;

import nettee.draft.driven.rdb.entity.type.builder.TypeSafeMarkers.Missing;
import nettee.draft.driven.rdb.entity.type.builder.TypeSafeMarkers.Present;

public sealed interface TypeSafeMarkers permits Present, Missing {
    non-sealed interface Present extends TypeSafeMarkers {}
    non-sealed interface Missing extends TypeSafeMarkers {}
}

