public class Item {
    private String name;
    private String description;
    private int amount;
    public Item (String name, String description){
        this.name = name;
        this.description = description;
        this.amount = 1;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
}
