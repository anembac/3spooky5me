package com.tda367.infinityrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.tda367.infinityrun.Math.Vec2;

import java.util.logging.FileHandler;

/**
 * Created by kaffe on 5/26/17.
 */
public class LoadCharacter {
    private static FileHandle saveFile = Gdx.files.local("savedata.txt");
    private int saveID;


    private LoadCharacter(int ID){
        saveID = ID;
    }


    public static Character loadCharacter (int Id){
            LoadCharacter loader = new LoadCharacter(Id);
            int levels[] = loader.getSaveData();

        // we count in a special way because 0 is coins and 7 is looting with a special method
        Character loadedCharacter
                =  new Character(new Vec2(800, 450),
                levels[1],
                levels[2],
                levels[3],
                levels[4],
                levels[5],
                levels[6],
                levels[8],
                levels[9] );

        loadedCharacter.setCoins(levels[0]);
        loadedCharacter.setLooting(levels[7]);

        return loadedCharacter;

    }

    private int[] getSaveData(){

        int[] returnData;

        String fullSaveData = saveFile.readString();

        int start = fullSaveData.indexOf("STARTOFSAVE"+saveID)
                +"STARTOFSAVE".length()+Integer.toString(saveID).length()+1;
        int end = fullSaveData.indexOf("END"+saveID)-1;

        String activeSaveData = fullSaveData.substring(start,end);

        int relevantAmount = activeSaveData.length()
                - activeSaveData.replace(":","").length();

        returnData = new int[relevantAmount];
        for(int i = 0; i < relevantAmount; i++){
            System.out.println(i);
            returnData[i] = Integer.parseInt((
                    activeSaveData.substring(
                            activeSaveData.indexOf(" ")+1, activeSaveData.indexOf("\n")
                    )
            ));
            System.out.println(i);
            activeSaveData = activeSaveData.substring(activeSaveData.indexOf(" "));

        }

        return returnData;
    }


    public static int getNumberOfSaves(){

        String tmp = saveFile.readString();
        int endindex = tmp.lastIndexOf("END");
        String tmp2 = tmp.substring(endindex+3);
        return Integer.parseInt(tmp2);
    }

}
