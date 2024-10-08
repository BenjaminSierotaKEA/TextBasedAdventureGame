public class RangedWeapon extends Weapon{

    private int maxAmmo;
    private int ammo;
    public RangedWeapon(String itemName, String itemDescription, int maxAmmo){

        super(itemName, itemDescription);
        //for now, we simply set the current ammo to max ammo, and all weapons will be made with full ammo:
        this.maxAmmo = maxAmmo;
        this.ammo = maxAmmo;
    }

    @Override
    public void attack() {
        if(ammo > 0){
            System.out.println("You shoot the " + super.getName() + "into the air and hit nothing");
            ammo--;
            System.out.println("the weapon has " + ammo + "Shots remaining");

        }else{
            System.out.println("the " + super.getName() + " is out of ammo");
        }
    }
}
