package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */

public class HermesSandals extends Upgrade {
    private boolean isUsed = false;

    public HermesSandals(String upgradeName, int cap, boolean multiply, int basevalue) {
        super(upgradeName, cap, multiply, basevalue);
    }

    @Override
    public void frame(float dt) {
        /*if(data.positionY > data.groundlevel + 0.00001 && input.JumpKeyPressed && !isUsed){
        character.doJump;
        isUsed = true;
        } if(data.positionY <= data.groundlevel + 0.00001){
        isUsed = false;
        }
        */
    }
}
