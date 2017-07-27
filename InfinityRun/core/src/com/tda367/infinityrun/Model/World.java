package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Controller.IInput;
import com.tda367.infinityrun.Controller.InputGDX;
import com.tda367.infinityrun.Utils.Constants;
import com.tda367.infinityrun.Utils.Math.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/*
Class for binding the game together and where the different objects interact with eachother.
 */
public class World extends Observable {

    private final List<WorldObject> worldObjects;

    private final Character hero;
    private WorldGenerator generator = null;
    private boolean running = true;
    private InputState state;

    public World(WorldGenerator gen, Character hero) {
        CollisionManager.getInstance().forceNewInstance();
        worldObjects = new ArrayList<WorldObject>();
        generator = gen;
        state = new InputState(false,false,false,false,false,false);
        addWorldObject(hero);
        this.hero = hero;
        hero.equipWeapon();
    }
    public Character getHero() {
        return hero;
    }

   // public void setInput(IInput input) {
     //   this.input = input;
   // }


    //this tells the TextbasedWorldGenerator what rooms are to be generated. the coordinates are used with offsets and the location
    // of the player
    private void generateWorld() {
        int x = (int) (Math.floor((hero.getPosition().x + hero.getDrawingRect().bounds.x / 2)
                / (Constants.roomWidth * Constants.meter)));

        int y = (int) (Math.floor((hero.getPosition().y + hero.getDrawingRect().bounds.y / 2)
                / (Constants.roomHeight * Constants.meter)));


        //this will only occur once.
        //creates the first room
        addRoomIfItDoesntExist(x - 0, y - 0);


        //generates the rooms adjacent to the current room to prevent unwanted errors.
        //creates the non-corner rooms
       addRoomIfItDoesntExist(x + 1, y);
        addRoomIfItDoesntExist(x - 1, y);
        addRoomIfItDoesntExist(x - 0, y - 1);
        addRoomIfItDoesntExist(x - 0, y + 1);

      //the diagonal rooms are generated last.
        //creates diagonal rooms
        addRoomIfItDoesntExist(x - 1, y - 1);
        addRoomIfItDoesntExist(x - 1, y + 1);
        addRoomIfItDoesntExist(x + 1, y - 1);
        addRoomIfItDoesntExist(x + 1, y + 1);
    }

    //unmakes game
    public boolean gameDone()
    {
        return !running;
    }



    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }


    //frame function that rewrites WorldObjects/enemies.
    public void frame(float dt) {
        // 25 15
        setChanged();
        notifyObservers();
        generateWorld();
        //addWorldObjects(generator2.generate(0,0));
        List<WorldObject> objectsToRemove = new ArrayList<WorldObject>();
        Vec2 heroPos = WOWrapper.worldObjectCenter(hero);
        for (WorldObject obj : worldObjects) {
            if (Vec2.distance(WOWrapper.worldObjectCenter(obj), heroPos) > 1500)
                continue; // no need to make logic that far away, the player wont see this anyway.
            obj.frame(dt, heroPos.x, heroPos.y, state);


            if (obj instanceof LivingObject) {
                // This object might have a different position now, the easiest way is to remove it and then re add it.
                CollisionManager.getInstance().updatePosition(obj);
            }
            if (obj.getDespawn()) {
                objectsToRemove.add(obj);
                if(obj instanceof Character) running = false;
            }
        }

        for (WorldObject wo : objectsToRemove) {
            CollisionManager.getInstance().removeObject(wo);
            worldObjects.remove(wo);
        }

        hero.setMaxdistance(generator.getMaxDistance());

    }

    // to add the player etc to the world.
    public void addWorldObject(WorldObject obj) {
        CollisionManager.getInstance().addWorldObject(obj);
        worldObjects.add(obj);
    }

    private void addWorldObjects(List<WorldObject> objs) {

        for (WorldObject wo : objs) {
            addWorldObject(wo);
        }
    }



    private void addRoomIfItDoesntExist(int x, int y) {
        {
            List<WorldObject> newWorldObjects = generator.generate(x, y);
            for (int i = 0; i < newWorldObjects.size(); i++) {
                for (int j = 0; j < worldObjects.size(); j++) {
                    if (newWorldObjects.get(i).equals(worldObjects.get(j)))
                        System.out.println("Error worldobject already added!?");
                }
            }
            addWorldObjects(newWorldObjects);
        }
    }

//    public void updateState(boolean forward, boolean back, boolean jump, boolean attack, boolean goToMenu, boolean goToShop) {
//        state = new InputState(forward, back, jump, attack, goToMenu,goToShop);
//    }

    public InputState getInputState(){
        return state;
    }

}
