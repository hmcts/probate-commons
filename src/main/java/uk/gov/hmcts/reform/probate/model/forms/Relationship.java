package uk.gov.hmcts.reform.probate.model.forms;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Relationship {

    SPOUSE("Spouse"), CHILD("Child"), ADOPTED_CHILD("Adopted Child"), OTHER("Other");

    private final String description;
}
