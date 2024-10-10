import java.util.ArrayList;

public class Enemy extends Character{
    private String name;
    private String description;

    private int maxHealth;
    private int health;

    private int armourClass;

    private Weapon weapon;

    private Room currentRoom;

    public Enemy(String name, String description, int maxHealth, int armourClass, Weapon weapon, Room currentRoom){
        this.name = name;
        this.description = description;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.armourClass = armourClass;
        this.weapon = weapon;
        this.currentRoom = currentRoom;

    }

    public void recieveAttack(int hitRoll, int damageRoll, UserInterface ui, Weapon attackingWeapon, Character attacker){
        if(hitRoll > armourClass){
            health -= damageRoll;
            ui.playerAttacksEnemy(this.name, hitRoll, damageRoll, true, attackingWeapon, health);
            if(health <= 0){
                //death logic
                currentRoom.addItem(weapon);
                currentRoom.removeEnemy(this);
            }

        }else{
            ui.playerAttacksEnemy(this.name, hitRoll, damageRoll, false, attackingWeapon, health);
        }
    }

    public int[] attack(Character target, UserInterface ui){

        int[] attackData = weapon.attack();

        //first entry in this list is the attack roll, the second is the damage roll

        target.recieveAttack(attackData[0], attackData[1], ui, weapon, this);

        return attackData;

    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
}
