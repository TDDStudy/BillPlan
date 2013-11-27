package billingPack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static billingPack.PlanType.*;

public class BillingTest {
    Billing billing;

    @Before
    public void setUp() {

    }

    @Test
    public void expectedSimpleBill_gold_2line() {
        billing = new Billing(new Line("seungwon", GOLD, 2, 1000));
        Assert.assertEquals(64.45, billing.getExpectedSimpleBill(), 2);
    }

    @Test
    public void expectedSimpleBill_gold_1line() {
        billing = new Billing(new Line("seungwon", GOLD, 1, 1000));
        Assert.assertEquals(49.95, billing.getExpectedSimpleBill(), 2);
    }

    @Test
    public void expectedSimpleBill_silver_1line() {
        billing = new Billing(new Line("seungwon", SILVER, 1, 1000));
        Assert.assertEquals(29.95, billing.getExpectedSimpleBill(), 2);
    }

    @Test
    public void expectedSimpleBill_silver_3line() {
        billing = new Billing(new Line("seungwon", SILVER, 3, 1000));
        Assert.assertEquals(72.95, billing.getExpectedSimpleBill(), 2);
    }


    @Test
    public void calculateExcessMinutes_gold_999m() {
        billing = new Billing(new Line("seungwon", GOLD, 1, 999));
        Assert.assertEquals(49.95, billing.getBillIncludingExcessMinutes(), 2);
    }

    @Test
    public void calculateExcessMinutes_gold_1000m() {
        billing = new Billing(new Line("seungwon", GOLD, 1, 1000));
        Assert.assertEquals(49.95, billing.getBillIncludingExcessMinutes(), 2);
    }

    @Test
    public void calculateExcessMinutes_gold_1001m() {
        billing = new Billing(new Line("seungwon", GOLD, 1, 1001));
        Assert.assertEquals(50.40, billing.getBillIncludingExcessMinutes(), 2);
    }

    @Test
    public void calculateExcessMinutes_gold_1010m() {
        billing = new Billing(new Line("seungwon", GOLD, 1, 1010));
        Assert.assertEquals(54.45, billing.getBillIncludingExcessMinutes(), 2);
    }

    @Test
    public void calculateExcessMinutes_silver_499m() {
        billing = new Billing(new Line("seungwon", SILVER, 1, 499));
        Assert.assertEquals(29.95, billing.getBillIncludingExcessMinutes(), 2);
    }

    @Test
    public void calculateExcessMinutes_silver_500m() {
        billing = new Billing(new Line("seungwon", SILVER, 1, 500));
        Assert.assertEquals(29.95, billing.getBillIncludingExcessMinutes(), 2);
    }

    @Test
    public void calculateExcessMinutes_silver_520m() {
        billing = new Billing(new Line("seungwon", SILVER, 1, 520));
        Assert.assertEquals(40.75, billing.getBillIncludingExcessMinutes(), 2);
    }

    @Test
    public void calculateFamilyDiscount_gold_2line() {
        billing = new Billing(new Line("seungwon", GOLD, 2, 999));
        Assert.assertEquals(64.45, billing.getBillIncludingFamilyDiscount(), 2);
    }

    @Test
    public void calculateFamilyDiscount_gold_3line() {
        billing = new Billing(new Line("seungwon", GOLD, 3, 999));
        Assert.assertEquals(78.95, billing.getBillIncludingFamilyDiscount(), 3);
    }

    @Test
    public void calculateFamilyDiscount_gold_4line() {
        billing = new Billing(new Line("seungwon", GOLD, 4, 999));
        Assert.assertEquals(83.95, billing.getBillIncludingFamilyDiscount(), 2);
    }

    @Test
    public void calculateFamilyDiscount_silver_4line() {
        billing = new Billing(new Line("seungwon", SILVER, 4, 499));
        Assert.assertEquals(77.95, billing.getBillIncludingFamilyDiscount(), 2);
    }

    @Test
    public void calculateFamilyDiscount_silver_5line() {
        billing = new Billing(new Line("seungwon", SILVER, 5, 499));
        Assert.assertEquals(82.95, billing.getBillIncludingFamilyDiscount(), 2);
    }
}
