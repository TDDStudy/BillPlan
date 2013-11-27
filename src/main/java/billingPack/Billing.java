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
        double additionalFee = 0.0;

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

//        expectedBill = basicMonthlyRate;    // 기본금 추가
        if (line.getCount() >= 1 && line.getCount() < 4) {
            // if count is 2..3
            additionalFee += (line.getCount()-1) * ratePerAdditionalLine;    // 라인별 요금 추가
        }
        else {
            // if count >= 4
            additionalFee += 2*ratePerAdditionalLine;
        }

        return basicMonthlyRate + additionalFee;
    }

    public double getBillIncludingExcessMinutes() {
        double additionalFee = 0.0;

//        if (line.getCount() > 1) return -1;

        if (line.getPlanType() == GOLD) {
            basicMonthlyRate = 49.95;
            ratePerExcessMinute = 0.45;
//            expectedBill = basicMonthlyRate;
            if (line.getUsedTime() > 1000) {
                additionalFee += (line.getUsedTime()-1000) * ratePerExcessMinute;
            }
        }
        else {
            basicMonthlyRate = 29.95;
            ratePerExcessMinute = 0.54;
//            expectedBill = basicMonthlyRate;
            if (line.getUsedTime() > 500) {
                additionalFee += (line.getUsedTime()-500) * ratePerExcessMinute;
            }
        }

        return additionalFee;
    }


    public double getBillIncludingFamilyDiscount() {
        double additionalFee = 0.0;

        if (line.getCount() < 4) {
//            return getExpectedSimpleBill();
            return 0;
        }

        int BeneficiaryCount = line.getCount()-3;   // 가족 할인 수혜 대상 인원

        if (line.getPlanType() == GOLD) {
            basicMonthlyRate = 49.95;
            ratePerAdditionalLine = 14.50;
//            expectedBill = basicMonthlyRate;
            additionalFee += /*(2*ratePerAdditionalLine)*/ + (BeneficiaryCount*5);    // 라인별 요금 추가
        }
        else {
            basicMonthlyRate = 29.95;
            ratePerAdditionalLine = 21.50;
//            expectedBill = basicMonthlyRate;
            additionalFee += /*(2*ratePerAdditionalLine)*/ + (BeneficiaryCount*5);    // 라인별 요금 추가
        }

        return additionalFee;
    }

    public double getCompleteBill() {
        expectedBill = getExpectedSimpleBill() + getBillIncludingExcessMinutes() + getBillIncludingFamilyDiscount();

        return expectedBill;
    }
}
