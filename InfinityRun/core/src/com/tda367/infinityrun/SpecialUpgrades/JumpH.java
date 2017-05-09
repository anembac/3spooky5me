package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class JumpH extends Upgrade {

    public JumpH(String upgradeName, int cap, boolean multiply, int basevalue) {
        super(upgradeName, cap, multiply, basevalue);
    }

    @Override
    public int getValue(){
        return 100 + super.getValue();
    }

    @Override
    public void frame(float dt) {
        //add basic jump
    }

}
