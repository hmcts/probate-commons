package uk.gov.hmcts.reform.probate.utils;

import java.math.BigDecimal;

public class NumberUtils {

    public static Boolean equals(BigDecimal a, BigDecimal b) {
        return (a == b) || (a != null && a.compareTo(b) == 0);
    }
}
