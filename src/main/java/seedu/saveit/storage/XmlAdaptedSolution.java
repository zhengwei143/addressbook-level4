package seedu.saveit.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.saveit.commons.exceptions.IllegalValueException;
import seedu.saveit.model.issue.PrimarySolution;
import seedu.saveit.model.issue.Solution;
import seedu.saveit.model.issue.solution.Remark;
import seedu.saveit.model.issue.solution.SolutionLink;

/**
 * JAXB-friendly adapted version of the Solution.
 */
public class XmlAdaptedSolution {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Solution's %s field is missing!";

    @XmlElement(required = true)
    private String solutionLink;

    @XmlElement(required = true)
    private String remark;

    @XmlElement
    private boolean isPrimarySolution;


    /**
     * Constructs an XmlAdaptedSolution. This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedSolution() {
    }

    /**
     * Constructs a {@code XmlAdaptedSolution} with the given {@code solutionName}.
     */
    public XmlAdaptedSolution(String solutionLink, String remark) {
        this.solutionLink = solutionLink;
        this.remark = remark;
    }

    /**
     * Converts a given Solution into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedSolution(Solution source) {
        solutionLink = source.getLink().toString();
        remark = source.getRemark().toString();
        isPrimarySolution = source.isPrimarySolution();
    }

    /**
     * Converts this jaxb-friendly adapted solution object into the model's Solution object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted issue
     */
    public Solution toModelType() throws IllegalValueException {
        if (solutionLink == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SolutionLink.class.getSimpleName()));
        }
        if (!SolutionLink.isValidLink(solutionLink)) {
            throw new IllegalValueException(SolutionLink.MESSAGE_SOLUTION_LINK_CONSTRAINTS);
        }
        final SolutionLink modelSolutionLink = new SolutionLink(solutionLink);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_REMARK_CONSTRAINTS);
        }
        final Remark modelRemark = new Remark(remark);



        if (isPrimarySolution) {
            return new PrimarySolution(modelSolutionLink, modelRemark);
        } else {
            return new Solution(modelSolutionLink, modelRemark);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedSolution)) {
            return false;
        }

        return solutionLink.equals(((XmlAdaptedSolution) other).solutionLink)
                && remark.equals(((XmlAdaptedSolution) other).remark)
                && isPrimarySolution == (((XmlAdaptedSolution) other).isPrimarySolution);
    }
}
