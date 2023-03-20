package uk.gov.hmcts.reform.probate.model;

import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.RegistrarDirection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistrarDirectionCreator {

    public static List<CollectionMember<RegistrarDirection>> buildRegistrarDirections() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        List<CollectionMember<RegistrarDirection>> directions = new ArrayList<>();
        CollectionMember<RegistrarDirection> registrarDirectionCollectionMember1 = new CollectionMember<>(null,
                RegistrarDirection.builder()
                        .addedDateTime(LocalDateTime.parse("2023-01-01T23:45:45.890Z", dateTimeFormatter))
                        .decision("Decision 1")
                        .furtherInformation("Further information 1")
                        .build());
        CollectionMember<RegistrarDirection> registrarDirectionCollectionMember2 = new CollectionMember<>(null,
                RegistrarDirection.builder()
                        .addedDateTime(LocalDateTime.parse("2023-01-02T23:45:45.890Z", dateTimeFormatter))
                        .decision("Decision 2")
                        .build());

        directions.add(registrarDirectionCollectionMember1);
        directions.add(registrarDirectionCollectionMember2);

        return directions;
    }

    private RegistrarDirectionCreator() {
    }
}
