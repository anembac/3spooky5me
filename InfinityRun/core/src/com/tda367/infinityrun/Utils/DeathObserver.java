package com.tda367.infinityrun.Utils;

import com.tda367.infinityrun.Model.Character;

import java.util.Observable;
import java.util.Observer;

public class DeathObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Observer reached");
        String s = (String)arg;
        if(o instanceof Character && s.substring(0,4).equals("dead")){
            SaveCharacter.saveCharacter((Character)o, Integer.parseInt(s.substring(4)));
        }
    }
}
