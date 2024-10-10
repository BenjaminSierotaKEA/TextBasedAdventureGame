import java.util.ArrayList;

public abstract class Weapon extends Item{

    protected int usesRemaining;
    //this contains the sides of the dice used in the damage roll:
    protected int dicetype;
    protected int diceAmount;
    protected int accuracyBonus;
    protected int damageBonus;


    public Weapon(String itemName, String itemDescription, int usesRemaining){
        super(itemName, itemDescription);
        this.usesRemaining = usesRemaining;
        dicetype = 6;
        diceAmount = 2;
        accuracyBonus = 1;
        damageBonus = 3;
    }

    public Weapon(String itemName, String itemDescription){
        super(itemName, itemDescription);
        this.usesRemaining = 0;
        dicetype = 6;
        diceAmount = 2;
        accuracyBonus = 1;
        damageBonus = 3;
    }

    public Weapon(String itemName, String itemDescription, int usesRemaining, int dicetype, int diceAmount, int accuracyBonus, int damageBonus){
        super(itemName, itemDescription);
        this.usesRemaining = usesRemaining;
        this.dicetype = dicetype;
        this.diceAmount = diceAmount;
        this.accuracyBonus = accuracyBonus;
        this.damageBonus = damageBonus;
    }

    abstract public int[] attack();

    public int getUsesRemaining(){
        return usesRemaining;
    }
}
