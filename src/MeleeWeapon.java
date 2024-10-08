public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String itemName, String itemDescription){
        super(itemName, itemDescription);
    }

    public void attack(){
        System.out.println("You swing the " + super.getName() + " at the air and hit nothing");
    }
}
