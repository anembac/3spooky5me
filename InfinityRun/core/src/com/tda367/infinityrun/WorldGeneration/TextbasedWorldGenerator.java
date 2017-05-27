package com.tda367.infinityrun.WorldGeneration;

import com.tda367.infinityrun.Enemy;
import com.tda367.infinityrun.Math.Vec2;
import com.tda367.infinityrun.RoomTiles.*;
import com.tda367.infinityrun.WeaponTypes.Sword;
import com.tda367.infinityrun.WorldObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Mikael on 5/22/2017.
 */
public class TextbasedWorldGenerator implements WorldGenerator {

    private HashMap<Integer, RoomType> allRooms = new HashMap<Integer, RoomType>();
    private HashMap<IndexPoint, RoomType> madeRooms = new HashMap<IndexPoint, RoomType>();
    private static Random rand = new Random();
    int maxDistanceFromSpawn ;
    public TextbasedWorldGenerator()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("WorldRooms.txt"));
            String line = br.readLine();
            int i = 0; // keep track of y axis
            int lineRow = 1;
            List<List<Character>> map = null;

            while(line != null) {
                line = line.toUpperCase();
                i = i % 16;
                if(i == 0) {
                    // A new room begins, reset the arrays.
                    if (map == null) {
                        map = new ArrayList<List<Character>>(15);
                        for(int j = 0; j < 25; j++) {
                            map.add(new ArrayList<Character>());
                            for(int k = 0; k < 15; k++) {
                                // E represents empty.
                                map.get(j).add('E');
                            }
                        }
                    } else {
                        addRoom(map);
                        map = new ArrayList<List<Character>>(15);
                        for(int j = 0; j < 25; j++) {
                            map.add(new ArrayList<Character>());
                            for(int k = 0; k < 15; k++) {
                                // E represents empty.
                                map.get(j).add('E');
                            }
                        }
                    }
                } else {
                    int y = i - 1;
                    if(line.length() < 25) {
                        System.out.println("error in textfile. AT LINE " + lineRow);
                    } else {
                        for(int x = 0; x < 25; x++) {
                            Character _char = line.toCharArray()[x];
                            if(_char == ' ') _char = 'E';
                            map.get(x).set(y, _char);
                        }
                    }
                }
                line = br.readLine();
                i++;
                lineRow++;
            }
            br.close();
        }
        catch (Exception ex){ System.out.println("File error... ");}
    }

    private void addRoom(List<List<Character>> map)
    {
        boolean r,l,u,d;
        r = u = l = d = false;
        if(map.get(12).get(0) == 'E') u = true;
        if(map.get(12).get(14) == 'E') d = true;
        if(map.get(0).get(6) == 'E') l = true;
        if(map.get(24).get(6) == 'E') r = true;
        RoomType room = new RoomType(r,l,u,d);
        if(allRooms.containsKey(room.bitmaskCode()))
        {
            allRooms.get(room.bitmaskCode()).addType(map);
        }
        else
        {
            allRooms.put(room.bitmaskCode(), room);
            room.addType(map);
        }
    }

    @Override
    public List<WorldObject> generate(int x, int y) {
        if(roomExists(x,y)) return new ArrayList<WorldObject>();
        else
        {
            if(x == 0 && y == 0) {
                madeRooms.put(new IndexPoint(x,y), allRooms.get(7));
                return allRooms.get(7).generate(x,y);
            } else {
                List<RoomType> possible = new ArrayList<RoomType>();
                for(Map.Entry<Integer,RoomType> a :  allRooms.entrySet()) {
                    possible.add(a.getValue());
                }

                if(madeRooms.containsKey(new IndexPoint(x+1,y)) && madeRooms.get(new IndexPoint(x+1,y)).left) cleanPossible(possible,1);
                if(madeRooms.containsKey(new IndexPoint(x-1,y)) && madeRooms.get(new IndexPoint(x-1,y)).right) cleanPossible(possible,2);
                if(madeRooms.containsKey(new IndexPoint(x,y+1)) && madeRooms.get(new IndexPoint(x,y+1)).down) cleanPossible(possible,4);
                if(madeRooms.containsKey(new IndexPoint(x,y-1)) && madeRooms.get(new IndexPoint(x,y-1)).up) cleanPossible(possible,8);

                RoomType madeRoom = null;
                while(madeRoom == null)
                {
                    int q = rand.nextInt(possible.size());
                    madeRoom = possible.get(q);
                    madeRooms.put(new IndexPoint(x,y), madeRoom);
                    if(!pathOut(new HashSet<IndexPoint>(), 0,0)){
                        madeRoom = null;
                        possible.remove(q);
                    }
                }
                return madeRoom.generate(x,y);
            }
        }
    }

    private boolean pathOut(HashSet<IndexPoint> checked, int x, int y) {
        IndexPoint pos = new IndexPoint(x,y);
        checked.add(pos);
        if(madeRooms.containsKey(pos)) {
            return  ((madeRooms.get(pos).down && !checked.contains(new IndexPoint(x,y-1))) ? pathOut(checked,x,y-1) : false) ||
                    ((madeRooms.get(pos).up && !checked.contains(new IndexPoint(x,y+1))) ? pathOut(checked,x,y+1) : false) ||
                    ((madeRooms.get(pos).right && !checked.contains(new IndexPoint(x+1,y))) ? pathOut(checked,x+1,y) : false) ||
                    ((madeRooms.get(pos).left && !checked.contains(new IndexPoint(x-1,y))) ? pathOut(checked,x-1,y) : false);
        }
        else return true;
    }

    private void cleanPossible(List<RoomType> possible, int i)
    {
        for(int k = 0; k < possible.size();) {
            if((possible.get(k).bitmaskCode() & i) == 0) {
                possible.remove(k);
                continue;
            }
            k++;
        }
    }

    public int getMaxDistance(){
        return maxDistanceFromSpawn;
    }

    @Override
    public boolean roomExists(int x, int y) {
        return madeRooms.containsKey(new IndexPoint(x,y));
    }

    class RoomType
    {
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;

        List<List<List<Character>>> allTypes = new ArrayList<List<List<Character>>>();

        public RoomType(boolean r, boolean l, boolean u, boolean d)
        {
            right = r;
            left = l;
            up = u;
            down = d;
        }

        public int bitmaskCode() {
            return (right ? 1 : 0) + (left ? 2 : 0) + (up ? 4 : 0) + (down ? 8 : 0);
        }

        public void addType(List<List<Character>> input)
        {
            allTypes.add(input);
        }

        public boolean isRight() {
            return right;
        }

        public List<WorldObject> generate(int ox, int oy)
        {
            int difficulty = (int)Math.sqrt(ox*ox+oy+oy);
            if(difficulty > maxDistanceFromSpawn){
                maxDistanceFromSpawn = difficulty;
            }

            List<WorldObject> output = new ArrayList<WorldObject>();
            int k = rand.nextInt(allTypes.size());
            for(int x = 0; x < 25; x++)
            {
                for(int y = 0; y < 15; y++)
                {
                    float nx = ox * 64 * 25+x*64;
                    float ny = oy*64*15+64*(14-y);
                    Vec2 pos = new Vec2(nx, ny);
                    switch (allTypes.get(k).get(x).get(y))
                    {
                        case 'B' : output.add(new BrickObject(pos)); break;
                        case '_' : output.add(new Platform(pos)); break;
                        case 'C' : output.add(new CoinObject(pos)); break;
                        case 'Q' : { int rnd = new Random().nextInt((100)+1);
                            if (rnd < (((difficulty/6)+Math.sqrt(difficulty)*(Math.sin(difficulty)*difficulty*difficulty)/2)+0.43)*100){

                            Enemy enemy = (new Enemy(pos, new Vec2(64,64),1*(difficulty/4),1*(difficulty/8),1*(difficulty/32),1*(difficulty/8),1*(difficulty*2),1*(difficulty/16),1*(difficulty/24),1*(difficulty/24)));
                            output.add(enemy);
                            enemy.setMeleeWeapon();}
                        } break;
                        case 'S' : output.add(new SpikeObject(pos, (difficulty)));
                            output.add(new SpikeObject((new Vec2(pos.x+32,pos.y)), (difficulty))); break;

                    }
                }
            }
            return output;
        }
    }

}
