package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.issue.Remark;
import seedu.address.model.issue.Solution;
import seedu.address.model.issue.solution.SolutionLink;

/**
 * JAXB-friendly adapted version of the Solution.
 */
public class XmlAdaptedSolution {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Solution's %s field is missing!";

    @XmlElement(required = true)
    private String link;
    @XmlElement(required = true)
    private String remark;

    /**
     * Constructs an XmlAdaptedSolution.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedSolution() {}

    /**
     * Constructs a {@code XmlAdaptedSolution} with the given {@code solutionName}.
     */
    public XmlAdaptedSolution(String link, String remark) {
        this.link = link;
        this.remark = remark;
    }

    /**
     * Converts a given Solution into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedSolution(Solution source) {
        link = source.link;
        remark = source.remark;
    }

    /**
     * Converts this jaxb-friendly adapted solution object into the model's Solution object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted issue
     */
    public Solution toModelType() throws IllegalValueException {
        if (link == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Solution.class.getSimpleName()));
        }
        if (remark == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Solution.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        if (!SolutionLink.isValidLink(link)) {
            throw new IllegalValueException(SolutionLink.MESSAGE_SOLUTION_LINK_CONSTRAINTS);
        }

        return new Solution(link,remark);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedSolution)) {
            return false;
        }

        return solutionName.equals(((XmlAdaptedSolution) other).solutionName);
    }
}
