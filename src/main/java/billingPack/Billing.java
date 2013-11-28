package billingPack;

import static billingPack.PlanType.GOLD;
import static billingPack.PlanType.SILVER;

/**
 * This is the class which estimate a phone bill.
 * @author seungwon
 */
public class Billing {
    /** GOLD_BASIC_MONTHLY_RATE = 49.95. */
    static final double GOLD_BASIC_MONTHLY_RATE = 49.95;
    /** GOLD_RATE_PER_ADDITIONAL_LINE = 14.50. */
    static final double GOLD_RATE_PER_ADDITIONAL_LINE = 14.50;
    /** GOLD_RATE_PER_EXCESS_MINUTE = 0.45. */
    static final double GOLD_RATE_PER_EXCESS_MINUTE = 0.45;
    /** SILVER_BASIC_MONTHLY_RATE = 29.95. */
    static final double SILVER_BASIC_MONTHLY_RATE = 29.95;
    /** SILVER_RATE_PER_ADDITIONAL_LINE = 21.50. */
    static final double SILVER_RATE_PER_ADDITIONAL_LINE = 21.50;
    /** SILVER_RATE_PER_EXCESS_MINUTE = 0.54. */
    static final double SILVER_RATE_PER_EXCESS_MINUTE = 0.54;

    /** GOLD_BASIC_MINUTE = 1000. */
    static final int GOLD_BASIC_MINUTE = 1000;
    /** SILVER_BASIC_MINUTE = 500. */
    static final int SILVER_BASIC_MINUTE = 500;

    /** FAMILY_DISCOUNT_FEE = 5. */
    static final double FAMILY_DISCOUNT_FEE = 5.0;
    /** NUMBER_FOR_FAMILY_DISCOUNT = 4. */
    static final int NUMBER_FOR_FAMILY_DISCOUNT = 4;
    /** NUMBER_OF_NORMAL_RATE_APPLIED = 3. */
    static final int NUMBER_OF_NORMAL_RATE_APPLIED = 3;

    /** internal Line class instance. */
    private Line line;
    /**
     * the below things will be changed as line's plan type.
     * Basic Monthly Rate ; this is the basic fee as plan type.
    */
    private double basicMonthlyRate;
    /** Rate Per Additional Line ; this is the additional fee as lines added. */
    private double ratePerAdditionalLine;
    /** Rate Per Excess Minute;
     * if an user exceed free minute, impose additional fee. */
    private double ratePerExcessMinute;
    /** this is an expected bill. */
    private double expectedBill;

    /**
     * When Billing instance created, line instance will be loaded.
     * @see Line
     */
    public Billing() {
        this.line = new Line("dummy", GOLD, 1, GOLD_BASIC_MINUTE);
    }

    /**
     * When Billing instance created, line instance will be loaded.
     * @param line is a Line instance
     * @see Line
     */
    public Billing(final Line line) {
        this.line = line;
        decideRate();   // Plan Type 에 따라 요금 비율 정해줌.
    }

    /**
     * decide the each rates as user plan type.
     */
    private void decideRate() {
        if (line.getPlanType() == GOLD) {
            // PlanType is Gold
            basicMonthlyRate = GOLD_BASIC_MONTHLY_RATE;
            ratePerAdditionalLine = GOLD_RATE_PER_ADDITIONAL_LINE;
            ratePerExcessMinute = GOLD_RATE_PER_EXCESS_MINUTE;
        } else {
            // PlanType is Silver
            basicMonthlyRate = SILVER_BASIC_MONTHLY_RATE;
            ratePerAdditionalLine = SILVER_RATE_PER_ADDITIONAL_LINE;
            ratePerExcessMinute = SILVER_RATE_PER_EXCESS_MINUTE;
        }
    }

    /**
     * This is a method to return the basic bill.
     * @return basic fee as plan type.
     */
    public final double getBasicBill() {
        return basicMonthlyRate;
    }

    /**
     * This is a method to get the extra fee as additional lines.
     * but, If lines are more than 3,
     * bill is applied by the Family Discount additionally.
     * @return extra fee as additional lines.
     */
    public final double getExtraFeeAsAdditionalLine() {
        double additionalFee = 0.0;

        if (line.getCount() >= 1 && line.getCount()
                < NUMBER_FOR_FAMILY_DISCOUNT) {
            // if count is 2..3
            additionalFee += (line.getCount() - 1) * ratePerAdditionalLine;
            // 라인별 요금 추가
        } else {
            // if count >= 4
            additionalFee += 2 * ratePerAdditionalLine;
        }

        return additionalFee;
    }

    /**
     * This is a method to get the extra fee as your excess consumed minutes.
     * @return extra fee as excess minute
     */
    public final double getExtraFeeAsExcessMinute() {
        double additionalFee = 0.0;


        if (line.getPlanType() == GOLD
                && line.getUsedTime() > GOLD_BASIC_MINUTE) {
            additionalFee += (line.getUsedTime() - GOLD_BASIC_MINUTE)
                    * ratePerExcessMinute;
        } else if (line.getPlanType() == SILVER
                && line.getUsedTime() > SILVER_BASIC_MINUTE) {
            additionalFee += (line.getUsedTime() - SILVER_BASIC_MINUTE)
                    * ratePerExcessMinute;
        }

        return additionalFee;
    }


    /**
     * This is a method to get the extra fee as the Family Discount.
     * if all number of line are more 3 (>3), you can apply this discount.
     * if you weren't, this method returns 0
     * @return extra fee as the Family Discount
     */
    public final double getExtraFeeAsFamilyDiscount() {
        double additionalFee = 0.0;

        if (line.getCount() < NUMBER_FOR_FAMILY_DISCOUNT) {
            return 0;
        }

        int beneficiaryCount = line.getCount() - NUMBER_OF_NORMAL_RATE_APPLIED;
        // 가족 할인 수혜 대상 인원

        additionalFee += (beneficiaryCount * FAMILY_DISCOUNT_FEE);
        // 라인별 요금 추가

        return additionalFee;
    }

    /**
     * return total bill as your line properties.
     * (line count, plan type, excess minutes)
     * @return total fee
     */
    public final double getCompleteBill() {
        expectedBill = getBasicBill()
                + getExtraFeeAsAdditionalLine()
                + getExtraFeeAsExcessMinute()
                + getExtraFeeAsFamilyDiscount();

        return expectedBill;
    }
}
