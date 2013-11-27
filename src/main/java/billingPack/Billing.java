package billingPack;

import static billingPack.PlanType.*;

public class Billing {
    private Line line;
    private double basicMonthlyRate;
    private double ratePerAdditionalLine;
    private double ratePerExcessMinute;
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

    public double getBillIncludingExcessMinutes() {
        if (line.getCount() > 1) return -1;

        if (line.getPlanType() == GOLD) {
            basicMonthlyRate = 49.95;
            ratePerExcessMinute = 0.45;
            expectedBill = basicMonthlyRate;
            if (line.getUsedTime() > 1000) {
                expectedBill += (line.getUsedTime()-1000) * ratePerExcessMinute;
            }
        }
        else {
            basicMonthlyRate = 29.95;
            ratePerExcessMinute = 0.54;
            expectedBill = basicMonthlyRate;
            if (line.getUsedTime() > 500) {
                expectedBill += (line.getUsedTime()-500) * ratePerExcessMinute;
            }
        }

        return expectedBill;
    }


    public double getBillIncludingFamilyDiscount() {
        if (line.getCount() < 4) {
            return getExpectedSimpleBill();
        }

        int BeneficiaryCount = line.getCount()-3;   // 가족 할인 수혜 대상 인원

        if (line.getPlanType() == GOLD) {
            basicMonthlyRate = 49.95;
            ratePerAdditionalLine = 14.50;
            expectedBill = basicMonthlyRate + (2*ratePerAdditionalLine) + (BeneficiaryCount*5);    // 라인별 요금 추가
        }
        else {
            basicMonthlyRate = 29.95;
            ratePerAdditionalLine = 21.50;
            expectedBill = basicMonthlyRate + (2*ratePerAdditionalLine) + (BeneficiaryCount*5);    // 라인별 요금 추가
        }

        return expectedBill;
    }
}
