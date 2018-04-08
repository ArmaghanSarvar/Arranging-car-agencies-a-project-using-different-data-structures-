/**
 * Created by Armaghan on 12/2/2017.
 */

public class GNode {
    public GNode nextNode;      //pointer
    private int tag;  // 0 = service , 1 = link
    private Union union;
    public int numOfAgency;

    public GNode(){
        nextNode = null;
        numOfAgency = 0;
    }

    public void setUnion(Union union) {
        this.union = union;
    }

    public Union getUnion() {
        return union;
    }

    public int getNumOfAgency() {
        return numOfAgency;
    }

    public void setNumOfAgency(int numOfAgency) {
        this.numOfAgency = numOfAgency;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }
}

class Union{
    private Service service;
    public GNode nextSubNode;   //pointer

    public Union(Service s){
        this.service = s;
        this.nextSubNode = null;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
}