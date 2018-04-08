import java.util.Vector;

/**
 * Created by Armaghan on 12/4/2017.
 */

public class ANode {
    public ANode next;
    private String AName;
    private Vector<GNode> serviceList = new Vector<>();  // offers
    PQueue pQueue;

    public ANode() {
        pQueue= new PQueue();
    }

    public void addToPQueue(Order order){
        pQueue.add(order);
        System.out.println("your order is added to MaxHeap!");
    }

    public String getAName() {
        return AName;
    }

    public void setAName(String AName) {
        this.AName = AName;
    }

    public Vector<GNode> getServiceList() {
        return serviceList;
    }

    public PQueue getpQueue() {
        return pQueue;
    }

}