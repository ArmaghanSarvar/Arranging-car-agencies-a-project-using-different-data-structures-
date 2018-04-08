/**
 * Created by Armaghan on 12/2/2017.
 */

public class Service {
    private String name, model , costumerIn, techIn , cost;

    public Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCostumerIn(String costumerIn) {
        this.costumerIn = costumerIn;
    }

    public void setTechIn(String techIn) {
        this.techIn = techIn;
    }

    public String getTechIn() {
        return techIn;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getModel() {
        return model;
    }
}
