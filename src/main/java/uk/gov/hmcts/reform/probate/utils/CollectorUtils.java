package uk.gov.hmcts.reform.probate.utils;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorUtils {

    private CollectorUtils() {

    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                if (list.size() == 0) {
                    return null;
                } else if (list.size() > 1) {
                    throw new IllegalStateException();
                }
                return list.get(0);
            }
        );
    }
}
