package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Issue;
import seedu.address.model.issue.IssueStatement;
import seedu.address.model.issue.Phone;
import seedu.address.model.issue.Remark;
import seedu.address.model.issue.Solution;
import seedu.address.model.issue.Tag;

/**
 * JAXB-friendly version of the Issue.
 */
public class XmlAdaptedIssue {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Issue's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String address;

    @XmlElement
    private List<XmlAdaptedSolution> solutions = new ArrayList<>();

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedIssue.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedIssue() {}

    /**
     * Constructs an {@code XmlAdaptedIssue} with the given issue details.
     */
    public XmlAdaptedIssue(String name, String address, String phone, List<XmlAdaptedSolution> solutions, List<XmlAdaptedTag> tagged) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        if (solutions != null){
            this.solutions = new ArrayList<>(solutions);
        }
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
    }

    /**
     * Converts a given Issue into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedIssue
     */
    public XmlAdaptedIssue(Issue source) {
        name = source.getStatement().issue;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        solutions = source.getSolutions().stream()
                .map(XmlAdaptedSolution::new)
                .collect(Collectors.toList());
        tagged = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this jaxb-friendly adapted issue object into the model's Issue object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted issue
     */
    public Issue toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Solution> issueSolutions = new ArrayList<>();
        for (XmlAdaptedSolution solution : solutions) {
            issueSolutions.add(solution.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                IssueStatement.class.getSimpleName()));
        }
        if (!IssueStatement.isValidIssueStatement(name)) {
            throw new IllegalValueException(IssueStatement.MESSAGE_ISSUE_STATEMENT_CONSTRAINTS);
        }
        final IssueStatement modelName = new IssueStatement(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(address)) {
            throw new IllegalValueException(Remark.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        final Remark modelAddress = new Remark(address);

        final Set<Solution> modelSolutions = new HashSet<>(issueSolutions);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Issue(modelName, modelPhone, modelAddress, modelSolutions, modelTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedIssue)) {
            return false;
        }

        XmlAdaptedIssue otherPerson = (XmlAdaptedIssue) other;
        return Objects.equals(name, otherPerson.name)
                && Objects.equals(phone, otherPerson.phone)
                && Objects.equals(address, otherPerson.address)
                && solutions.equals(otherPerson.solutions)
                && tagged.equals(otherPerson.tagged);
    }
}
