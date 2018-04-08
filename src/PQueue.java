import java.util.Vector;

/**
 * Created by Armaghan on 12/10/2017.
 */
public class PQueue {
    private MyVector maxHeap;

    public PQueue() {
        maxHeap = new MyVector();
        maxHeap.addElement(null);
    }

    public void add(Order o){
        maxHeap.add(o);
        heapify(1);
    }

    private void swap(int i, int j) {
        Order temp = maxHeap.get(i);
        maxHeap.set(i, maxHeap.get(j));
        maxHeap.set(j, temp);
    }

    private void heapify(int root){
        int bc; // the biggest child index
        for (int i = maxHeap.size()/2 ; i >= root ; i--) {
            bc = biggestCIndex(i);
            if (maxHeap.elementAt(i).getPriority() < maxHeap.elementAt(bc).getPriority()){
                swap(i , bc);
                if (hasChild(bc))
                    heapify(bc);
            }
        }
    }

    private int biggestCIndex(int dadIndex){
        float lcp = getLCP(dadIndex);
        float rcp = getRCP(dadIndex);
        if (rcp > lcp )
            return 2 * dadIndex + 1;
        return 2 * dadIndex;
    }

    private boolean hasLC(int dadIndex){
        return dadIndex * 2<= maxHeap.size();
    }

    private boolean hasRC(int dadIndex){
        return dadIndex * 2 + 1 <= maxHeap.size();
    }

    private float getLCP(int dadIndex){
        if (hasLC(dadIndex))
            return maxHeap.elementAt(dadIndex * 2).getPriority();
        return -1;
    }

    private float getRCP(int dadIndex){
        if (hasRC(dadIndex))
            return maxHeap.elementAt(dadIndex * 2 + 1).getPriority();
        return -1;
    }

    private boolean hasChild(int dadIndex){
           return hasLC(dadIndex);
    }

    public void printHeap() {
        if (maxHeap.size() == 0){
            System.out.println("All orders are done!");
            return;
        }
        System.out.println("customer name: " + maxHeap.get(1).getCustomerName());
        System.out.println("service name: " + maxHeap.get(1).getServiceName());
        System.out.println("agency name: " + maxHeap.get(1).getAgencyName());
        System.out.println("-----------------------------");
        maxHeap.remove(1);
        heapify(1);
        printHeap();
    }

    public Vector<Order> getMaxHeap() {
        return maxHeap;
    }

    public void setMaxHeap(MyVector maxHeap) {
        this.maxHeap = maxHeap;
    }
}