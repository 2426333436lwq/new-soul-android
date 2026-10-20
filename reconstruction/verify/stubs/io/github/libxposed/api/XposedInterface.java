package io.github.libxposed.api;

public interface XposedInterface {

    enum ExceptionMode {
        DEFAULT,
        PROTECTIVE,
        PASS_THROUGH,
    }

    interface Hooker {
        Object intercept(Chain chain);
    }

    interface Chain {
        Object getThisObject();

        Object getArg(int index);

        Object[] getArgs();

        Object proceed();

        Object proceed(Object... replacementArgs);
    }

    interface HookHandle {}

    interface HookBuilder {
        HookBuilder setId(String id);

        HookBuilder setExceptionMode(ExceptionMode mode);

        HookHandle intercept(Hooker hooker);
    }
}
