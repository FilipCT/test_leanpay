package si.leanpay.taf.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Preparation for Parallel Processing
 * Currently not being used
 *
 * @author Filip Milicevic
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkingDir {
    public static final String BASE_WORKING_DIR = "./work";

    private static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

    public static void setCounter(Integer counter) {
        WorkingDir.counter.set(counter);
    }

    public static Integer getCounter() {
        return counter.get();
    }

    public static String getCurrentWorkingDir() {
        return BASE_WORKING_DIR + "/" + Thread.currentThread().getName() + "_" + counter.get();
    }
}
