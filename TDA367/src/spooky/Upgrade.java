package spooky;

/**
 * Created by Jacob on 5/3/2017.
 */
public class Upgrade {

    String upgradeName;
    int cap;
    boolean multiply;
    int value;
    int level = 0;

    public Upgrade (String upgradeName, int cap, boolean multiply, int value) {
        this.upgradeName = upgradeName;
        this.cap = cap;
        this.multiply = multiply;
        this.value = value;
    }

    public void levelUp (){
        this.level++;
    }
}
