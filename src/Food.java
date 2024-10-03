public class Food extends Item{

    int hpRecovery;
    public Food(String name, String description, int hpRecovery){
        super(name, description);
        this.hpRecovery = hpRecovery;
    }

    public int getHpRecovery() {
        return hpRecovery;
    }
}
