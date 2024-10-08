public class Food extends Item{

    private int hpRecovery;
    private boolean looksEdible;
    public Food(String name, String description, int hpRecovery, boolean looksEdible){
        super(name, description);
        this.hpRecovery = hpRecovery;
        this.looksEdible = looksEdible;
    }

    public int getHpRecovery() {
        return hpRecovery;
    }

    public boolean getLooksEdible(){
        return looksEdible;
    }
}
