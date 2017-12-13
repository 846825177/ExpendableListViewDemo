package china.http.com.expendablelistviewdemo;

import java.util.List;

/**
 * @author 农民伯伯
 * @version 2017/12/13
 */


public class Entity {
    private String memory;
    private String Color;
    private String model;
    private List<String> price;

    public Entity(String memory, String color, String model, List<String> price) {
        this.memory = memory;
        Color = color;
        this.model = model;
        this.price = price;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "memory='" + memory + '\'' +
                ", Color='" + Color + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
