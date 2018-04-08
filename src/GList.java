import java.util.Scanner;

/**
 * Created by Armaghan on 12/2/2017.
 */
public class GList {
    public GNode head;
    private Scanner scanner= new Scanner(System.in);

    public GList() {
        head = null;
    }

    public void add_service(String serviceName) {
        if (!isUnique(head , serviceName)) {
            System.out.println("this service already exists");
            return;
        }
        GNode newService = new GNode();
        newService.setTag(0);
        newService.setUnion(new Union(newSer(serviceName)));
        if (head == null){
            head= newService;
            System.out.println("your service is added!");
            return;
        }
        GNode h1= head;
        while (h1.nextNode !=null){
            h1= h1.nextNode;
        }
        h1.nextNode = newService;
        System.out.println("your service is added!");
    }


    public void add_subservice(String subName, String serviceName){
        GNode headT = head;
        headT = findService(serviceName , headT);
        if(headT == null){
            System.out.println("this service you want to add subservice to, doesn't exist");
            return;
        }
        Union union= headT.getUnion();
        GNode p = union.nextSubNode;
        if (!isUnique(head , subName)) {
            System.out.println("this subservice already exists in the list");
            return;
        }
        GNode sub= new GNode();
        sub.setUnion(new Union(newSer(subName)));
        headT.setTag(1);
        if (p == null) {
            union.nextSubNode = sub;
            System.out.println("your subservice is added!");
            return;
        }
        while (p.nextNode != null){
            p = p.nextNode;
        }
        p.nextNode = sub;

        System.out.println("your subservice is added!");
    }

    private Service newSer(String name){
        System.out.println("enter the model of your car");
        String model= scanner.nextLine();
        System.out.println("enter the costumer information");
        String cIn = scanner.nextLine();
        System.out.println("enter the technical information");
        String tIn = scanner.nextLine();
        System.out.println("enter the cost");
        String cost = scanner.nextLine();
        Service service= new Service(name);
        service = addInformation(service, model, cIn, tIn, cost);
        return service;
    }

    private Service addInformation(Service service, String model, String cIn, String tIn, String cost){
        service.setModel(model);
        service.setCostumerIn(cIn);
        service.setTechIn(tIn);
        service.setCost(cost);
        return service;
    }

    private GNode findService(String serviceName , GNode h) {
        while (h != null) {
            if (h.getTag() == 0) {
                if (h.getUnion().getService().getName().equals(serviceName)) {
                    return h;
                }
            } else {
                if (h.getUnion().getService().getName().equals(serviceName)) {
                    return h;
                }
                GNode service = findService(serviceName , h.getUnion().nextSubNode);
                if( service != null)
                    return service;
            }
            h = h.nextNode;
        }
        return null;
    }

    private boolean exist(String name , GNode head){
        if (head == null)
            return false;
        GNode pointer= head;
        while (pointer != null){
            if (pointer.getUnion().getService().getName().equals(name))
                return true;
            pointer= pointer.nextNode;
        }
        return false;
    }

    private boolean isUnique(GNode p, String s){    // this is a recursive function so it needs the pointer
        while (p != null) {
            if (p.getTag() == 0) {     // data
                if (p.getUnion().getService().getName().equals(s))
                    return false;
            }
            else if (p.getTag() == 1) {  // link
                if (p.getUnion().getService().getName().equals(s))
                    return false;
                if (! isUnique(p.getUnion().nextSubNode, s))
                    return isUnique(p.getUnion().nextSubNode, s);
            }
            p = p.nextNode;
        }
        return true;
    }

    public void printGList(GNode q){
        if (q == null){
            System.out.print("there is no service !");
            return;
        }
        System.out.print("[");
        while (q != null){
            if (q.getTag() == 0){
                System.out.print(q.getUnion().getService().getName());
                if (q.nextNode != null)
                    System.out.print(" ");
            }
            else if(q.getTag() == 1){
                System.out.print(q.getUnion().getService().getName());
                printGList(q.getUnion().nextSubNode);
                if (q.nextNode != null)
                    System.out.print(" ");
            }
            q = q.nextNode;
        }
        System.out.print("]");
    }

    public void printSubOfService(String serviceName) {
        GNode pointer = head;
        if (!exist(serviceName , head)) {
            System.out.println("this service doesn't exist");
            return;
        }
        while (pointer != null) {
            Union union = pointer.getUnion();
            if (union.getService().getName().equals(serviceName)) {
                GNode sub= pointer.getUnion().nextSubNode;
                if (sub == null){
                    System.out.println("this service doesn't have any subservices");
                    return;
                }
                while (sub != null){
                    System.out.println(sub.getUnion().getService().getName());
                    sub = sub.nextNode;
                }
            }
            pointer = pointer.nextNode;
        }
    }

    public boolean isSubservice(String childName, String dadName){
        GNode pointer= head;
        while (pointer != null) {
            Union union = pointer.getUnion();
            if (union.getService().getName().equals(dadName)) {
                GNode sub= pointer.getUnion().nextSubNode;
                if (sub == null)
                    return false;
                while (sub != null){
                    if (sub.getUnion().getService().getName().equals(childName))
                        return true;
                    sub = sub.nextNode;
                }
            }
            pointer = pointer.nextNode;
        }
        return false;
    }

    public void printMainServices(){
        GNode headT = head;
        while (headT!= null){
            System.out.println(headT.getUnion().getService().getName());
            headT= headT.nextNode;
        }
    }
}