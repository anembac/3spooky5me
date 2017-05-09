package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class Speed extends Upgrade {

    public Speed(String upgradeName, int cap, boolean multiply, int basevalue) {
        super(upgradeName, cap, multiply, basevalue);
    }

    @Override
    public int getValueInt(){
        return 100 + super.getValueInt();
    }

    @Override
    public void frame(float dt) {
        /*
        if(input.movementKeyLeft) {
        character.moveLeft;
        } else if {input.movementKeyRight) {
        character.moveRight;
        }
         */
    }
}
