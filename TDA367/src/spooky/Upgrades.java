package spooky;

import java.util.List;

public class Upgrades {
    private int characterID;
    private List<Upgrade> upgradesList;


    public Upgrades(int characterID) {
        this.characterID = characterID;
        upgradesList.add(0, new Upgrade("Health Points", -1, false, 20));
        upgradesList.add(1, new Upgrade("Melee Weapon Handling", -1, true, 5));
        upgradesList.add(2, new Upgrade("Ranged Weapon Handling", -1, true, 5));
        upgradesList.add(3, new Upgrade("Health Regeneration", -1, false, 1));
        upgradesList.add(4, new Upgrade("Quiver Size", -1, false, 5));
        upgradesList.add(5, new Upgrade("Better Looting", -1, true, 2));
    }
}
