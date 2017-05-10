package spooky;

public class Upgrade {

    String upgradeName;
    int cap;
    boolean multiply;
    int basevalue;
    int level = 0;

    public Upgrade(String upgradeName, int cap, boolean multiply, int basevalue) {
        this.upgradeName = upgradeName;
        this.cap = cap;
        this.multiply = multiply;
        this.basevalue = basevalue;
    }

    public void levelUp() {
        this.level++;
    }

    public String getUpgradeName() {
        return this.upgradeName;
    }

    public int getCap() {
        return this.cap;
    }

    public int getValue() {
        if (this.multiply) {
            return 100 + this.basevalue * this.level;
        }
        return this.basevalue * this.level;
    }

    public boolean doMultiply() {
        return this.multiply;
    }

}
