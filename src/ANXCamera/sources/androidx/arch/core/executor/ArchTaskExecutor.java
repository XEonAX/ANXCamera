package androidx.arch.core.executor;

import java.util.concurrent.Executor;

public class ArchTaskExecutor extends TaskExecutor {
    private static final Executor sIOThreadExecutor = new Executor() {
        public void execute(Runnable runnable) {
            ArchTaskExecutor.getInstance().executeOnDiskIO(runnable);
        }
    };
    private static volatile ArchTaskExecutor sInstance;
    private static final Executor sMainThreadExecutor = new Executor() {
        public void execute(Runnable runnable) {
            ArchTaskExecutor.getInstance().postToMainThread(runnable);
        }
    };
    private TaskExecutor mDefaultTaskExecutor;
    private TaskExecutor mDelegate;

    private ArchTaskExecutor() {
        DefaultTaskExecutor defaultTaskExecutor = new DefaultTaskExecutor();
        this.mDefaultTaskExecutor = defaultTaskExecutor;
        this.mDelegate = defaultTaskExecutor;
    }

    public static Executor getIOThreadExecutor() {
        return sIOThreadExecutor;
    }

    public static ArchTaskExecutor getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (ArchTaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new ArchTaskExecutor();
            }
        }
        return sInstance;
    }

    public static Executor getMainThreadExecutor() {
        return sMainThreadExecutor;
    }

    public void executeOnDiskIO(Runnable runnable) {
        this.mDelegate.executeOnDiskIO(runnable);
    }

    public boolean isMainThread() {
        return this.mDelegate.isMainThread();
    }

    public void postToMainThread(Runnable runnable) {
        this.mDelegate.postToMainThread(runnable);
    }

    public void setDelegate(TaskExecutor taskExecutor) {
        this.mDelegate = taskExecutor == null ? this.mDefaultTaskExecutor : taskExecutor;
    }
}
