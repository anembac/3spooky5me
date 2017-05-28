package com.tda367.infinityrun;

import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.WorldGeneration.WorldGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 4/3/2017.
 */
public class World {

    private final List<WorldObject> worldObjects;
    private IInput input;
    private final Character hero;
    private WorldGenerator generator = null;
    private final Shop shop;


    public World(WorldGenerator gen, Character hero) {
        CollisionManager.getInstance().forceNewInstance();
        worldObjects = new ArrayList<WorldObject>();
        generator = gen;
        // not sure where to put this really since the entry points for the project are tied to libgdx
        input = new InputGDX();
        addWorldObject(hero);
        this.hero = hero;
        hero.setMeleeWeapon();
        shop = new Shop(getHero());
    }

    public Character getHero(){
        return hero;
    }

    public void setInput(IInput input)
    {
        this.input = input;
    }

    private void generateWorld() {
        int x = (int)(Math.floor((hero.getPosition().x+hero.getDrawingRect().bounds.x/2)
                / (Constants.roomWidth*Constants.meter)));

        int y = (int)(Math.floor((hero.getPosition().y+hero.getDrawingRect().bounds.y/2)
                / (Constants.roomHeight*Constants.meter)));

        //creates the first room
        addRoomIfItDoesntExist(x - 0, y-0);

        //creates the non-corner rooms
        addRoomIfItDoesntExist(x+1,y);
        addRoomIfItDoesntExist(x-1,y);
        addRoomIfItDoesntExist(x-0,y-1);
        addRoomIfItDoesntExist(x-0,y+1);


        //creates diagonal rooms
        addRoomIfItDoesntExist(x-1,y-1);
        addRoomIfItDoesntExist(x-1,y+1);
        addRoomIfItDoesntExist(x+1,y-1);
        addRoomIfItDoesntExist(x+1,y+1);
    }

    public List<WorldObject> getWorldObjects()
    {
        return worldObjects;
    }


    public void frame(float dt) {
        // 25 15

        generateWorld();
        //addWorldObjects(generator2.generate(0,0));
        List<WorldObject> objectsToRemove = new ArrayList<WorldObject>();

        input.collectInput();

        Vec2 heroPos = WOWrapper.worldObjectCenter(hero);
        for (WorldObject obj : worldObjects) {
            if(Vec2.distance(WOWrapper.worldObjectCenter(obj), heroPos) > 1500) continue; // no need to make logic that far away, the player wont see this anyway.
            obj.frame(dt, heroPos.x, heroPos.y, input.getInput());

            if(obj instanceof LivingObject) {
                // This object might have a different position now, the easiest way is to remove it and then re add it.
                CollisionManager.getInstance().updatePosition(obj);
                if(obj != hero && obj.getDespawn()){
                    hero.lootEnemy();
                }
            }
            if(obj.getDespawn()) {
                objectsToRemove.add(obj);
            }
        }

        for(WorldObject wo : objectsToRemove) {
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

    private void addWorldObjects(List<WorldObject> objs)
    {

        for(WorldObject wo : objs)
        {
            addWorldObject(wo);
        }
    }

    private void addRoomIfItDoesntExist(int x, int y)
    {
        if(!generator.roomExists(x,y)){

     }
        {
            List<WorldObject> newWorldObjects = generator.generate(x,y);
            for(int i = 0; i < newWorldObjects.size(); i++)
            {
                for(int j = 0; j < worldObjects.size(); j++)
                {
                    if(newWorldObjects.get(i) == worldObjects.get(j)) System.out.println("Error worldobject already added!?");
                }
            }
            addWorldObjects(newWorldObjects);
        }
    }

    public Shop getShop() {
        return shop;
    }

}
