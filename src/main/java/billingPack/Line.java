package billingPack;

/**
 * Created with IntelliJ IDEA.
 * User: seung-wongim
 * Date: 2013. 11. 27.
 * Time: 오후 8:05
 * To change this template use File | Settings | File Templates.
 */
public class Line {
    private String name;
    private PlanType planType;
    private int count;
    private int usedTime;

    public Line() {
    }
    public Line(String name, PlanType pt, int cnt, int uTime) {
        this.name = name;
        this.planType = pt;
        this.count = cnt;
        this.usedTime = uTime;
    }

}
