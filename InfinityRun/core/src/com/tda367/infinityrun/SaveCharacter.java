package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.StringBuilder;
import com.sun.deploy.util.StringUtils;
import com.tda367.infinityrun.Character;

public final class SaveCharacter {
    private static FileHandle saveFile = Gdx.files.local("savedata.txt");
    private static StringBuilder saveText = new StringBuilder("");
    private static int saveID = 0;
    private Character hero;
    private boolean newChar;

    private SaveCharacter(Character hero, boolean newChar){
        this.hero = hero;
        this.newChar = newChar;
    }


    private void applySave(String saveString){
        if(saveFile.exists()){
            if(newChar){
                //hero.setCharacterID();
                saveFile.writeString(saveString + "\n", true);
            }
            saveFile.writeString(saveString + "\n", true);

        }
        else if(Gdx.files.isLocalStorageAvailable()){
            saveFile.writeString(1 + "\n" + saveString + "\n", false);
        }else{
            System.out.println("Application was unable to create a new save file due to lack of local storage.");
            System.out.println("The character was not saved.");
        }

    }


    private String generateSave(Character hero){

        saveText.append("STARTOFSAVE " + ++saveID);
        saveText.append("\n");
        saveText.append("Coins: " + hero.getCoins());
        saveText.append("\n");
        for( int i = 0; i<hero.getUpgrades().size(); i++){
            Upgrade currentUpgrade = hero.getUpgrades().get(hero.getUpgrades().keySet().toArray()[i]);
            saveText.append(hero.getUpgrades().keySet().toArray()[i]);
            saveText.append("level: " + currentUpgrade.getLevel());
            saveText.append("\n");
        }
        saveText.append("ENDOFSAVE" + saveID);
        return saveText.toString();
    }

    public static void saveCharacter(Character hero, boolean newChar){
        SaveCharacter s = new SaveCharacter(hero, newChar);
        s.applySave(s.generateSave(hero));
    }

//    private static int getNumberOfSaves(){
//
//        //Method for occurence count stolen from a comment by James Oltmans in https://stackoverflow.com/a/8910767
//        //who knows if it actually works
//        int count = (saveFile.readString().length()
//                - saveFile.readString().replace("STARTOFSAVE", "").length())/saveFile.readString().length();
//        return count;
//    }

    private void setSaveID(int id){
        saveID = id;
        hero.setCharacterID(id);
    }




}
