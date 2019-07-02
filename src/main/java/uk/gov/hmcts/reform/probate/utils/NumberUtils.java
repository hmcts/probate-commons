package uk.gov.hmcts.reform.probate.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class NumberUtils {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    public Boolean equals(BigDecimal a, BigDecimal b) {
        return (a == b) || (a != null && a.compareTo(b) == 0);
    }

    public BigDecimal penniesToPounds(Long value) {
        return BigDecimal.valueOf(value).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal defaultToZero(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    public Long defaultToZero(Long value) {
        return value == null ? 0L : value;
    }
}
