package billingPack;

import static billingPack.PlanType.*;

public class Billing {
    private Line line;
    private double basicMonthlyRate;
    private double ratePerAdditionalLine;
    private double expectedBill;

    public Billing() {}
    public Billing(Line line) {
        this.line = line;
    }

    public double getExpectedSimpleBill() {
        if (this.line == null)
            return -1;
        else if (line.getPlanType() == GOLD) {
            // PlanType is Gold
            basicMonthlyRate = 49.95;
            ratePerAdditionalLine = 14.50;
        }
        else {
            // PlanType is Silver
            basicMonthlyRate = 29.95;
            ratePerAdditionalLine = 21.50;
        }

        expectedBill = basicMonthlyRate;    // 기본금 추가
        if (line.getCount() > 1) {
            expectedBill += (line.getCount()-1) * ratePerAdditionalLine;    // 라인별 요금 추가
        }

        return expectedBill;
    }
}
