package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SpouseNotApplyingReason {

    RENUNCIATED("Renunciated"), MENTALLY_INCAPABLE("Mentally incapable"), OTHER("Other");

    private final String description;
}
