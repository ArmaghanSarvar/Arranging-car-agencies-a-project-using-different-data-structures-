import java.util.Vector;

/**
 * Created by Armaghan on 12/1/2017.
 */

public class AgencyList {
    private ANode head;
    private GList gList;

    public AgencyList(GList gList) {
        head = null;
        this.gList = gList;
    }

    public void add_offer(String serviceName, String AName) {
        ANode headT = head;
        GNode offer = glistOffer(serviceName);
        if (getAgency(AName) == null){
            System.out.println("this agency doesn't exist");
            return ;
        }
        while (headT != null) {
            if (headT.getAName().equals(AName)) {
                if (offer == null) {
                    System.out.println("the offer is not in main services");
                    return;
                }
                if (isNewOffer(offer, headT)) {
                    headT.getServiceList().add(offer);
                    offer.setNumOfAgency(offer.getNumOfAgency() + 1);
                }
            }
            headT = headT.next;
        }

        System.out.println("your offer is added!");
    }

    public boolean isNewOffer(GNode offer, ANode h) {
        Vector<GNode> vec = h.getServiceList();
        for (int i = 0; i < vec.size(); i++) {
            String nam = (vec.get(i)).getUnion().getService().getName();
            if (offer.getUnion().getService().getName().equals(nam))
                return false;
        }
        return true;
    }

    public void printOffers(String aName) {
        ANode headT = head;
        while (headT != null) {
            if (headT.getAName().equals(aName)) {
                for (int i = 0; i < headT.getServiceList().size(); i++)
                    System.out.println(headT.getServiceList().get(i).getUnion().getService().getName());
            }
            headT = headT.next;
        }
    }

    private GNode glistOffer(String serviceName) {
        if (gList.head == null) {
            System.out.println("empty service list");
            return null;
        }
        GNode pointer = gList.head;
        while (pointer != null) {
            if ((pointer.getUnion().getService().getName()).equals(serviceName))
                return pointer;
            pointer = pointer.nextNode;
        }
        return null;
    }

    public void addAgency(String aName) {
        if (exist(aName)) {
            System.out.println("this agency already exists");
            return;
        }
        ANode aNode = new ANode();
        aNode.setAName(aName);
        if (head == null) {
            head = aNode;
            System.out.println("your agency is added!");
            return;
        }
        ANode pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = aNode;
        System.out.println("your agency is added!");

    }

    private boolean exist(String name) {
        if (head == null)
            return false;
        ANode p = head;
        while (p != null) {
            if (p.getAName().equals(name))
                return true;
            p = p.next;
        }
        return false;
    }

    public void printAList() {
        if (head == null) {
            System.out.print("there is no Agency yet!");
            return;
        }
        System.out.print("<");
        ANode p = head;
        while (p != null) {
            System.out.print(p.getAName());
            if (p.next != null)
                System.out.print(" ");
            p = p.next;
        }
        System.out.print(">");
    }

    private GNode deleteFromGlist(GNode gn) {
        System.out.println("deleting from genlist");
        GNode node = gList.head;
        GNode q = node;
        if (node == null) {
            System.out.println("the serviceList is empty");
            return null;
        }

        while (node != null) {
            if (node == gn) {
                if (node == gList.head)
                    gList.head = node.nextNode;
                else
                    q.nextNode = node.nextNode;
                return node;
            }
            q = node;
            node = node.nextNode;
        }
        return null;
    }

    public void delete(String serviceName, String AName) {
        if (!exist(AName)) {
            System.out.println("this agency doesn't exist");
            return;
        }
        ANode wantedAg = getAgency(AName);
        GNode wantedSer = getMainService(serviceName);
        if (wantedSer == null) {         // service is not in the Glist
            System.out.println("the service you want to delete doesn't exist");
            return;
        }
        if (!serviceInOffers(serviceName , AName)){
            System.out.println("this agency doesn't offer this service");
            return;
        }
        if (wantedSer.getNumOfAgency() > 1) {  // several agencies
            wantedSer.setNumOfAgency(wantedSer.getNumOfAgency() - 1);
        } else {
            deleteFromGlist(wantedSer);
        }
        if (wantedAg == null) {
            System.out.println("the agency you want to delete from, doesn't exist");
            return;
        }
        wantedAg.getServiceList().remove(wantedSer);  // deleting the service from vector of services
    }

    private boolean serviceInOffers(String sername, String aName){
        for (int i = 0; i < getAgency(aName).getServiceList().size() ; i++) {
            if (getAgency(aName).getServiceList().elementAt(i).getUnion().getService().getName().equals(sername))
                return true;
        }
        return false;
    }

    public ANode getAgency(String AName) {
        ANode pointer = head;
        while (pointer != null) {
            if (pointer.getAName().equals(AName)) {
                return pointer;
            }
            pointer = pointer.next;
        }
        return null;
    }

    private GNode getMainService(String serviceName) {
        GNode gNode = gList.head;
        while (gNode != null) {
            if (gNode.getUnion().getService().getName().equals(serviceName)) {
                return gNode;
            }
            gNode = gNode.nextNode;
        }
        return null;
    }

    private boolean isInGenList(GNode p , String sername){
        while (p != null) {
            if (p.getTag() == 0) {
                if (p.getUnion().getService().getName().equals(sername)) {
                    return true;
                }
            } else {
                if (p.getUnion().getService().getName().equals(sername)) {
                    return true;
                }
                boolean b = isInGenList(p.getUnion().nextSubNode,sername );
                if(b)
                    return true;
            }
            p = p.nextNode;
        }
        return false;
    }

    private GNode findDad(String subName) {
        GNode pointer = gList.head;
        while (pointer != null) {
            Union union = pointer.getUnion();
            if (gList.isSubservice(subName, union.getService().getName())) {
                return pointer;
            }
        pointer= pointer.nextNode;
        }
        return null;
    }

    public boolean isOffered(String serviceName, String aName){   // this method is for checking orders
        GNode p = gList.head;
        GNode dad = findDad(serviceName);
        if (getAgency(aName) == null){
            System.out.println("this agency doesn't exist");
            return false;
        }
        if (!isInGenList(p , serviceName)){
            System.out.println("this service you want to order doesn't exist");
            return false;
        }
        for (int i = 0; i < getAgency(aName).getServiceList().size() ; i++) {  // checking offers
            if (getAgency(aName).getServiceList().elementAt(i).getUnion().getService().getName().equals(serviceName))
                return true;
        }
        //now , the order is in glist but it's not main service(offer)! so we should check if it's father is
        //offered by the agency
        for (int i = 0; i <  getAgency(aName).getServiceList().size() ; i++) {
            String s=getAgency(aName).getServiceList().elementAt(i).getUnion().getService().getName();
            if (dad != null){
                if (dad.getUnion().getService().getName().equals(s)) {
                    return true;
                }
            }
        }
        System.out.println("this order is not in offers");
        return false;
    }
}