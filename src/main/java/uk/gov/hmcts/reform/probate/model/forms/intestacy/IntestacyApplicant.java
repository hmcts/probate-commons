package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.OptionYesNo;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.ProbateApplicant;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IntestacyApplicant extends ProbateApplicant {

    @ApiModelProperty(value = "Relationship to the deceased")
    private String relationshipToDeceased;

    @ApiModelProperty(value = "Was applicant adopted in",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("adoptedIn")
    private Boolean adoptedIn;

    @ApiModelProperty(value = "Was adoption in England or Wales",
        allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("adoptionPlace")
    private Boolean adoptionInEnglandOrWales;

    @ApiModelProperty(value = "Was applicant adopted out",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("adoptedOut")
    private Boolean adoptedOut;

    @ApiModelProperty(value = "Was applicant parent adopted in",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("parentAdoptedIn")
    private Boolean parentAdoptedIn;

    @ApiModelProperty(value = "Was adoption in England or Wales",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("parentAdoptionPlace")
    private Boolean parentAdoptionInEnglandOrWales;

    @ApiModelProperty(value = "Was applicant parent adopted out",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("parentAdoptedOut")
    private Boolean parentAdoptedOut;

    @ApiModelProperty(value = "Was deceased adopted in",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("deceasedAdoptedIn")
    private Boolean deceasedAdoptedIn;

    @ApiModelProperty(value = "Was deceased adoption in England or Wales",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("deceasedAdoptionPlace")
    private Boolean deceasedAdoptionInEnglandOrWales;

    @ApiModelProperty(value = "Was deceased adopted out",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("deceasedAdoptedOut")
    private Boolean deceasedAdoptedOut;

    @ApiModelProperty(value = "Reason spouse not applying")
    private String spouseNotApplyingReason;

    @ApiModelProperty(value = "Does the deceased have other siblings?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("anyOtherWholeSiblings")
    private Boolean otherWholeBloodSiblings;

    @JsonProperty("anyPredeceasedWholeSiblings")
    private String wholeBloodSiblingsDiedBeforeDeceased;

    @ApiModelProperty(value = "Are all the deceased's siblings over 18?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("allWholeSiblingsOver18")
    private Boolean wholeBloodSiblingsOverEighteen;

    @ApiModelProperty(value = "Did any of these siblings have surviving children?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("anySurvivingWholeNiecesAndWholeNephews")
    private Boolean wholeBloodNiecesAndNephewsSurvived;

    @ApiModelProperty(value = "Are all of nieces and nephews 18 or older?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("allWholeNiecesAndWholeNephewsOver18")
    private Boolean wholeBloodNiecesAndNephewsOverEighteen;

    @ApiModelProperty(value = "Does the deceased have other half-siblings?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("anyOtherHalfSiblings")
    private Boolean otherHalfBloodSiblings;

    @JsonProperty("anyPredeceasedHalfSiblings")
    private String halfBloodSiblingsDiedBeforeDeceased;

    @ApiModelProperty(value = "Are all the deceased's half-siblings over 18?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("allHalfSiblingsOver18")
    private Boolean halfBloodSiblingsOverEighteen;

    @ApiModelProperty(value = "Did any of these half-siblings have surviving children?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("anySurvivingHalfNiecesAndHalfNephews")
    private Boolean halfBloodNiecesAndNephewsSurvived;

    @ApiModelProperty(value = "Are all of half-nieces and half-nephews 18 or older?",
            allowableValues = OptionYesNo.Constants.ALLOWABLE_VALUES)
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    @JsonProperty("allHalfNiecesAndHalfNephewsOver18")
    private Boolean halfBloodNiecesAndNephewsOverEighteen;

    @ApiModelProperty(value = "Is applicant not required to send documents")
    private Boolean notRequiredToSendDocuments;

    @ApiModelProperty(value = "Do you have the same parents as deceased")
    @JsonProperty("sameParents")
    private String sameParentsAsDeceased;

    @Builder
    public IntestacyApplicant(String firstName, String lastName, String alias, String aliasReason, Address address,
                              String postcode, String postcodeAddress, Boolean addressFound,
                              List<Map<String, Object>> addresses, String phoneNumber, Boolean nameAsOnTheWill,
                              String otherReason, String relationshipToDeceased, Boolean adoptedIn,
                              Boolean adoptionInEnglandOrWales, Boolean adoptedOut, Boolean parentAdoptedIn,
                              Boolean parentAdoptionInEnglandOrWales, Boolean parentAdoptedOut,
                              Boolean deceasedAdoptedIn, Boolean deceasedAdoptionInEnglandOrWales,
                              Boolean deceasedAdoptedOut, String spouseNotApplyingReason,
                              Boolean otherHalfBloodSiblings, String halfBloodSiblingsDiedBeforeDeceased,
                              Boolean halfBloodSiblingsOverEighteen, Boolean halfBloodNiecesAndNephewsSurvived,
                              Boolean halfBloodNiecesAndNephewsOverEighteen, String sameParentsAsDeceased,
                              Boolean otherWholeBloodSiblings, String wholeBloodSiblingsDiedBeforeDeceased,
                              Boolean wholeBloodSiblingsOverEighteen, Boolean wholeBloodNiecesAndNephewsSurvived,
                              Boolean wholeBloodNiecesAndNephewsOverEighteen) {
        super(firstName, lastName, alias, aliasReason, address, postcode, postcodeAddress, addressFound, addresses,
            phoneNumber, nameAsOnTheWill, otherReason);
        this.relationshipToDeceased = relationshipToDeceased;
        this.adoptedIn = adoptedIn;
        this.adoptionInEnglandOrWales = adoptionInEnglandOrWales;
        this.adoptedOut = adoptedOut;
        this.parentAdoptedIn = parentAdoptedIn;
        this.parentAdoptionInEnglandOrWales = parentAdoptionInEnglandOrWales;
        this.parentAdoptedOut = parentAdoptedOut;
        this.deceasedAdoptedIn = deceasedAdoptedIn;
        this.deceasedAdoptionInEnglandOrWales = deceasedAdoptionInEnglandOrWales;
        this.deceasedAdoptedOut = deceasedAdoptedOut;
        this.spouseNotApplyingReason = spouseNotApplyingReason;
        this.otherHalfBloodSiblings = otherHalfBloodSiblings;
        this.halfBloodSiblingsDiedBeforeDeceased = halfBloodSiblingsDiedBeforeDeceased;
        this.halfBloodSiblingsOverEighteen = halfBloodSiblingsOverEighteen;
        this.halfBloodNiecesAndNephewsSurvived = halfBloodNiecesAndNephewsSurvived;
        this.halfBloodNiecesAndNephewsOverEighteen = halfBloodNiecesAndNephewsOverEighteen;
        this.sameParentsAsDeceased = sameParentsAsDeceased;
        this.otherWholeBloodSiblings = otherWholeBloodSiblings;
        this.wholeBloodSiblingsDiedBeforeDeceased = wholeBloodSiblingsDiedBeforeDeceased;
        this.wholeBloodSiblingsOverEighteen = wholeBloodSiblingsOverEighteen;
        this.wholeBloodNiecesAndNephewsSurvived = wholeBloodNiecesAndNephewsSurvived;
        this.wholeBloodNiecesAndNephewsOverEighteen = wholeBloodNiecesAndNephewsOverEighteen;
    }
}
