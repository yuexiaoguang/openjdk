package jdk.nashorn.internal.runtime.regexp.joni;

public interface WarnCallback {
    WarnCallback DEFAULT = new WarnCallback() {
        @Override
        public void warn(String message) {
            System.err.println(message);
        }
    };

    void warn(String message);
}
