import java.util.ArrayList;
import java.util.Random;

public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String itemName, String itemDescription){
        super(itemName, itemDescription);
    }

    public MeleeWeapon(String itemName, String itemDescription, int usesRemaining, int dicetype, int diceAmount, int accuracyBonus, int damageBonus){
        super(itemName, itemDescription, usesRemaining, dicetype, diceAmount, accuracyBonus, damageBonus);
    }

    public int[] attack(){

        int hitRoll;
        int damageRoll;
        Random random = new Random();

        hitRoll = random.nextInt(1, 20 + 1) + accuracyBonus;

        //now we make the damage roll:
        damageRoll = damageBonus;

        //handling critical hits:
        int diceRolls;
        if(hitRoll == 20){
            diceRolls = diceAmount *2;
        }else{
            diceRolls = diceAmount;
        }

        //Rolling for damage:
        for(int i = 0; i < diceRolls; i++){
            damageRoll += random.nextInt(1, dicetype + 1);
        }
        int[] attackData = new int[] {hitRoll, damageRoll};
        return attackData;
    }
}
