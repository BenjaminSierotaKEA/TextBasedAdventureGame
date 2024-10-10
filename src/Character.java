public abstract class Character {
    int health;
    int armourClass;

    String name;

    public abstract void recieveAttack(int hitRoll, int damageRoll, UserInterface ui, Weapon weapon, Character Attacker);

    public String getName(){
        return name;
    }
}
