import java.util.ArrayList;
import java.util.Random;

public class RangedWeapon extends Weapon{

    public RangedWeapon(String itemName, String itemDescription, int maxAmmo){

        super(itemName, itemDescription, maxAmmo);
        //for now, we simply set the current ammo to max ammo, and all weapons will be made with full ammo:

    }

    public RangedWeapon(String itemName, String itemDescription, int usesRemaining, int dicetype, int diceAmount, int accuracyBonus, int damageBonus){
        super(itemName, itemDescription, usesRemaining, dicetype, diceAmount, accuracyBonus, damageBonus);
    }

    @Override
    public int[] attack() {
        int hitRoll;
        int damageRoll;
        Random random = new Random();
        if(super.usesRemaining > 0){

            super.usesRemaining--;

            hitRoll = random.nextInt(1, 20 + 1) + accuracyBonus;

            //handling critical hits:
            int diceRolls;
            if(hitRoll == 20){
                diceRolls = diceAmount *2;
            }else{
                diceRolls = diceAmount;
            }

            //now we make the damage roll:
            damageRoll = damageBonus;
            for(int i = 0; i < diceRolls; i++){
                damageRoll += random.nextInt(1, dicetype + 1);
            }
            int[] attackData = new int[] {hitRoll, damageRoll};
            return attackData;

        }else{
            int[] attackData = new int[] {0, 0};
            return attackData;
        }
    }
}
