package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.StringBuilder;
// WARNING: unless u want a headache, do not touch magical saving box
public final class SaveCharacter {
        private static FileHandle saveFile = Gdx.files.local("savedata.txt");
    private static StringBuilder saveText = new StringBuilder("");
    private int saveID = 0;
    private Character hero;

    private SaveCharacter(Character hero, int newChar) {
        this.hero = hero;
        this.saveID = newChar;
    }


    //This is the method that actually writes to a text file. If the file doesn't exist it is created (provided that
    // the application has access to local storage. If a saveID already exists within the save it only changes that
    // part of the file. rather than appending it to the end.
    private void applySave(String saveString) {
        if (saveFile.exists()) {
            String tmp = saveFile.readString();
            int tmp2 = tmp.indexOf("STARTOFSAVE"+saveID);
            if(tmp.contains("END"+saveID)){
                //Not as cryptic as it seems, 3 is the length of "END" and we want the index of the end of the sequence
                int tmp3 = tmp.indexOf("END"+saveID)+3+Integer.toString(saveID).length();

                //Takes the string before and after the desired section of the save, then rewrites the entire file
                //with only the specific section altered.
                String tmp4 = tmp.substring(0,tmp2);
                String tmp5 = tmp.substring(tmp3);
                saveFile.writeString(tmp4 + saveString + tmp5, false);
            }else{
                saveFile.writeString("\n"+saveString,true);
            }

            //if storage exists, creates the file, and saves the character with ID 1.
        } else if (Gdx.files.isLocalStorageAvailable()) {
            saveID = 1;
            saveFile.writeString(saveString , false);
        } else {
            System.out.println("Application was unable to create a new save file due to lack of local storage.");
            System.out.println("The character was not saved.");
        }

    }

    //Uses a stringbuilder to construct a string containing all the needed information about the hero. The string starts
    //with "STARTOFSAVE#" where # is the ID of the character. It ends with "END#", this is so it will be easy to search
    //for specific saves later on.
    private String generateSave(Character hero) {
        saveText.append("STARTOFSAVE" + saveID);
        saveText.append("\n");
        saveText.append("Coins: " + hero.getCoins());
        saveText.append("\n");
        for (int i = 0; i < hero.getUpgrades().size(); i++) {
            Upgrade currentUpgrade = hero.getUpgrades().get(hero.getUpgrades().keySet().toArray()[i]);
            saveText.append(hero.getUpgrades().keySet().toArray()[i]);
            saveText.append("level: " + currentUpgrade.getLevel());
            saveText.append("\n");
        }
        saveText.append("END" + saveID);
        return saveText.toString();
    }


    //Creates a private instance of saveCharacter (this class)
    //Applies different saveIDs depending on if a savefile already exists, and if so if the current character already
    //has a save.
    //A new character will get an ID of one number larger than the largest current ID.
    public static void saveCharacter(Character hero, int saveID) {
        if(saveFile.exists() && saveID==0){
            saveID = getLastID()+1;
        }else if(!saveFile.exists() && saveID==0){
            saveID = 1;
        }
        SaveCharacter s = new SaveCharacter(hero, saveID);
        s.applySave(s.generateSave(hero));
    }


    //This method finds the last ID by searching for the last occurence of "END", which marks the end of a character
    //save, and then adds 3 to that index to get the number at the very end of the file, that number is the last ID.
    private static int getLastID(){
        String tmp = saveFile.readString();
        int endindex = tmp.lastIndexOf("END");
        String tmp2 = tmp.substring(endindex+3);
        return Integer.parseInt(tmp2);

    }
}
