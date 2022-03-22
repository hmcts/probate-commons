package uk.gov.hmcts.reform.probate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ScannedDocument {

    private final String controlNumber;

    private final String fileName;

    private final String type;

    private final String subtype;

    private final LocalDateTime scannedDate;

    private final ProbateDocumentLink url;

    private final String exceptionRecordReference;

    private final LocalDateTime deliveryDate;
}