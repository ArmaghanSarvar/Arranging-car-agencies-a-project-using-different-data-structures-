/**
 * Created by Armaghan on 12/10/2017.
 */
public class Order {
    private int time;
    private int lvl;
    private float priority;
    private String customerName;
    private String serviceName;
    private String agencyName;

    public Order(String serviceName,String agencyName, int time ,String customerName,  int lvl) {
        this.time = time;
        this.lvl = lvl;
        this.serviceName = serviceName;
        this.customerName = customerName;
        this.agencyName = agencyName;
        calculatePriority(this.lvl, this.time);
    }

    private void calculatePriority(int l, int t){
        this.priority = (float) (l * Math.pow(10,6) / t);
    }

    public float getPriority() {
        return priority;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getServiceName() {
        return serviceName;
    }
}