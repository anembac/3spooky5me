package com.tda367.infinityrun.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.tda367.infinityrun.Model.Character;

/**
 * Created by kaffe on 5/26/17.
 */


public class LoadCharacter {
    private static final FileHandle saveFile = Gdx.files.local("savedata.txt");
    private final int saveID;


    private LoadCharacter(int ID) {
        saveID = ID;
    }


    public static Character loadCharacter(int Id) {
        LoadCharacter loader = new LoadCharacter(Id);
        int levels[] = loader.getSaveData();
        Character loadedCharacter
                = new Character(
                levels[0],
                levels[1],
                levels[2],
                levels[3],
                levels[4],
                levels[5],
                levels[6],
                levels[7],
                levels[8],
                levels[9]);

        loadedCharacter.setCharacterID(Id);

        return loadedCharacter;

    }

    private int[] getSaveData() {

        int[] returnData;

        String fullSaveData = saveFile.readString();
        //System.out.println("SaveID: "+saveID);
        int start = fullSaveData.indexOf("STARTOFSAVE" + saveID)
                + "STARTOFSAVE".length() + Integer.toString(saveID).length() + 1;
        int end = fullSaveData.indexOf("END" + saveID); //character at end index is not included
        //System.out.println("Start: "+start);
        //System.out.println("End: "+end);
        String activeSaveData = fullSaveData.substring(start, end);

        int relevantAmount = activeSaveData.length()
                - activeSaveData.replace(":", "").length();

        //System.out.println("RelevantAmount: " + relevantAmount);

        returnData = new int[relevantAmount];
        for (int i = 0; i < relevantAmount; i++) {
            /* //FOR DEBUGGING
            System.out.println("Loopindex: "+i);
            System.out.println("Start2: "+(activeSaveData.indexOf(" ")+1));
            System.out.println("End2: "+activeSaveData.indexOf("\n"));*/

            returnData[i] = Integer.parseInt((
                    activeSaveData.substring(
                            (activeSaveData.indexOf(" ") + 1), activeSaveData.indexOf("\n")
                    )
            ));
            //System.out.println("Set 1: "+activeSaveData);
            activeSaveData = activeSaveData.substring((activeSaveData.indexOf("\n") + 1));
            //System.out.println("Set 2: "+activeSaveData);

        }

        return returnData;
    }


    public static int getNumberOfSaves() {
        if(saveDataExists()){
            String tmp = saveFile.readString();
            int endindex = tmp.lastIndexOf("END");
            String tmp2 = tmp.substring(endindex + 3);
            return Integer.parseInt(tmp2);
        }else{
            return 0;
        }

    }

    public static boolean saveDataExists(){
        return Gdx.files.local("savedata.txt").exists();
    }

}
