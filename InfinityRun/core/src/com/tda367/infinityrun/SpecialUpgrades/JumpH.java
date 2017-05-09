package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */
public class JumpH extends Upgrade {

    public JumpH(String upgradeName, int cap, boolean multiply, int baseValue) {
        super(upgradeName, cap, multiply, baseValue);
    }

    @Override
    public int getValue(){
        return 100 + super.getValue();
    }

    //@Override
    public void frame(float dt) {
        /*if(data.positionY <= data.groundlevel + 0.00001 && input.JumpKeyPressed){
        character.doJump;
        }
        */
    }

}
