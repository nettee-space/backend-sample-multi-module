package nettee.common.status;

public final class StatusCodeConstants {
    private StatusCodeConstants() {}

    public static final class Default {
        private Default() {}

        public static final int GENERAL_PURPOSE_BIT_SIZE = 7;
        public static final int SYSTEM_INFORMATION_BIT_SIZE = 8;
        public static final int CATEGORY_BIT_SIZE = 8;
        public static final int INSTANCE_DETAIL_BIT_SIZE = 8;

        public static final int GENERAL_PURPOSE_SHIFT =
                SYSTEM_INFORMATION_BIT_SIZE + CATEGORY_BIT_SIZE + INSTANCE_DETAIL_BIT_SIZE;
        public static final int SYSTEM_INFORMATION_SHIFT = CATEGORY_BIT_SIZE + INSTANCE_DETAIL_BIT_SIZE;
        public static final int CATEGORY_SHIFT = INSTANCE_DETAIL_BIT_SIZE;
    }
}
