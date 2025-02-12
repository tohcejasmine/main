package seedu.address.model.cap.module.rank;

import seedu.address.model.cap.module.DegreeClassification;

/**
 * Represents a class for a new student to the school that has no degree classification yet.
 */
public class Freshie extends Rank {

    private static final String TITLE = "FRESHIE";
    private static final double MAXIMUM_CAP = 0.0;
    private static final double MINIMUM_CAP = 0.0;
    private static final String imageFilePath = "/images/freshie.png";

    public Freshie() {
        super(DegreeClassification.FRESHIE, TITLE);
    }

    public static double getMaximumCap() {
        return MAXIMUM_CAP;
    }

    public static double getMinimumCap() {
        return MINIMUM_CAP;
    }

    @Override
    public String getRankImageFilePath() {
        return imageFilePath;
    }

    /**
     * To check if the cap given is within the range of the catagorisation of this rank/ class.
     * @param cap CUMULATIVE AVERAGE POINT
     * @return
     */
    public static boolean isWithinRange(double cap) {
        if (cap >= MINIMUM_CAP && cap <= MAXIMUM_CAP) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return TITLE;
    }
}
