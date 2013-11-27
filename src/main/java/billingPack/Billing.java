package billingPack;

/**
 * Created with IntelliJ IDEA.
 * User: seung-wongim
 * Date: 2013. 11. 27.
 * Time: 오후 8:53
 * To change this template use File | Settings | File Templates.
 */
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
        return expectedBill;
    }
}
