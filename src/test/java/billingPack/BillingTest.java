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
}
