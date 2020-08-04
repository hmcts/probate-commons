package uk.gov.hmcts.reform.probate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class BulkScanEnvelope {

    private final String id;

    private final String action;
}