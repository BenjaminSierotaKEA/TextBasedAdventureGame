public abstract class Weapon extends Item{

    private int usesRemaining;


    public Weapon(String itemName, String itemDescription, int usesRemaining){
        super(itemName, itemDescription);
        this.usesRemaining = usesRemaining;
    }

    public Weapon(String itemName, String itemDescription){
        super(itemName, itemDescription);
        this.usesRemaining = 0;
    }

    abstract public void attack();
}
