import java.util.Vector;

public class MyVector extends Vector<Order> {
    @Override
    public synchronized int size() {
        return super.size() - 1;
    }
}
