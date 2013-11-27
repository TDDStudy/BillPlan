package billingPack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static billingPack.PlanType.GOLD;

/**
 * Created with IntelliJ IDEA.
 * User: seung-wongim
 */
public class LineTest {
    Line line;

    @Before
    public void setUp() {
        line = new Line("Seungwon", GOLD, 3, 1000);
    }

    @Test
    public void getName_test() {
        Assert.assertEquals("Seungwon", line.getName());
    }
}
