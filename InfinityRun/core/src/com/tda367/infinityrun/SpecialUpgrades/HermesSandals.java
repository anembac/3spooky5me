package com.tda367.infinityrun.SpecialUpgrades;
import com.tda367.infinityrun.InputState;
import com.tda367.infinityrun.ObjectModifiers;
import com.tda367.infinityrun.Upgrade;

/**
 * Created by Jacob on 5/9/2017.
 */

public class HermesSandals extends Upgrade {
    private boolean isUsed = false;

    public HermesSandals(int cap, int basevalue) {
        super(cap, basevalue);
    }

    @Override
    public void frame(float dt, InputState input, ObjectModifiers modify) {
        /*if(data.positionY > data.groundlevel + 0.00001 && input.JumpKeyPressed && !isUsed){
        character.doJump;
        isUsed = true;
        } if(data.positionY <= data.groundlevel + 0.00001){
        isUsed = false;
        }
        */
    }
}
