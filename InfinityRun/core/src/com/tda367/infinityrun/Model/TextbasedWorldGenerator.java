package com.tda367.infinityrun.Model;

import com.tda367.infinityrun.Utils.Math.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.util.*;

import static com.tda367.infinityrun.Utils.Constants.meter;
import static com.tda367.infinityrun.Utils.Constants.roomHeight;
import static com.tda367.infinityrun.Utils.Constants.roomWidth;

/*
TextbasedWorldGenerator that places rooms in a indexed hasmap with unique values. Uses a text reader to read a file as WorldObjects.
It implements the WorldGenerator Interface

 the WorldGeneration takes into account for if the generated room would have "empty" neighbors, walls or exits
 */
public class TextbasedWorldGenerator implements WorldGenerator {

    private final HashMap<Integer, RoomType> allRooms = new HashMap<Integer, RoomType>();           //this is a hashmap that contains the rooms read from the constructor
    private final HashMap<IndexPoint, RoomType> madeRooms = new HashMap<IndexPoint, RoomType>();    //This is a hashmap that contains the list of the generated world, and their indexes
    private static final Random rand = new Random();
    private int maxDistanceFromSpawn;  //calculates score as the distance from start.




    //method for reading the textfile and splitting up the chunks of text into "rooms" that is then used in the worldgeneration.
    public TextbasedWorldGenerator() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("WorldRooms.txt"));
            String line = br.readLine();
            int i = 0; // keep track of y axis
            int lineRow = 1;
            List<List<java.lang.Character>> map = null;

            while (line != null) {
                line = line.toUpperCase();
                i = i % 16;
                if (i == 0) {
                    // A new room begins, reset the arrays.
                    if (map == null) {
                        map = new ArrayList<List<java.lang.Character>>(roomHeight);
                        for (int j = 0; j < roomWidth; j++) {
                            map.add(new ArrayList<java.lang.Character>());
                            for (int k = 0; k < roomHeight; k++) {
                                // E represents empty.
                                map.get(j).add('E');
                            }
                        }
                    } else {
                        addRoom(map);
                        map = new ArrayList<List<java.lang.Character>>(roomHeight);
                        for (int j = 0; j < roomWidth; j++) {
                            map.add(new ArrayList<java.lang.Character>());
                            for (int k = 0; k < roomHeight; k++) {
                                // E represents empty.
                                map.get(j).add('E');
                            }
                        }
                    }
                } else {
                    int y = i - 1;
                    if (line.length() < roomWidth) {
                        System.out.println("error in textfile. AT LINE " + lineRow);
                    } else {
                        for (int x = 0; x < roomWidth; x++) {
                            java.lang.Character _char = line.toCharArray()[x];
                            if (_char.equals(' ')) _char = 'E';
                            map.get(x).set(y, _char);
                        }
                    }
                }
                line = br.readLine();
                i++;
                lineRow++;
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("File error... ");
        }
    }


    // asserts which rooms work and which do not for the world generation using a  text database of available rooms.
    //the numbers 0,7,12,14,24 are coordinates in the room, and are the center top, center right, center left and center bottom in that order.


    private void addRoom(List<List<java.lang.Character>> map) {
        boolean r, l, u, d;
        r = u = l = d = false;
        if (checkRoomForObject(map, 12, 0)) u = true;

        if (checkRoomForObject(map, 12, 14)) d = true;

        if (checkRoomForObject(map, 0, 7)) l = true;

        if (checkRoomForObject(map, 24, 7)) r = true;
        RoomType room = new RoomType(r, l, u, d);
        if (allRooms.containsKey(room.bitmaskCode())) {
            allRooms.get(room.bitmaskCode()).addType(map);
        } else {
            allRooms.put(room.bitmaskCode(), room);
            room.addType(map);
        }
    }









    //checks if the char in the map text file is either E or ' ', meaning there is an exit to another room

    private boolean checkRoomForObject(List<List<java.lang.Character>> map, int a, int b){

        if (map.get(a).get(b).equals('E'))
            return true;
        if (map.get(a).get(b).equals(' '))
            return true;
        return false;
    }


    //this overrides the generate function with seperate cases for base and for theOTHER CASESit checks the roomtypes of
    //the surrounding rooms. From this it evaluates what rooms can be generated, and randomly picks one.
    @Override
    public List<WorldObject> generate(int x, int y) {
        if (roomExists(x, y)) return new ArrayList<WorldObject>();
        else {
            if (x == 0 && y == 0) {
                madeRooms.put(new IndexPoint(x, y), allRooms.get(7)); //this is the index number of the URL room.
                return allRooms.get(7).generate(x, y);
            } else {
                List<RoomType> possible = new ArrayList<RoomType>();
                for (Map.Entry<Integer, RoomType> a : allRooms.entrySet()) {
                    possible.add(a.getValue());
                }

                for(int loop = 0; loop < allRooms.size();loop++){



                    //this part removes rooms from the list of possible rooms that have too many entrances
                }
                if (madeRooms.containsKey(new IndexPoint(x + 1, y)) && madeRooms.get(new IndexPoint(x + 1, y)).left){
                    cleanPossible(possible, 1, true);

                }
                if (madeRooms.containsKey(new IndexPoint(x - 1, y)) && madeRooms.get(new IndexPoint(x - 1, y)).right){
                    cleanPossible(possible, 2, true);
                }
                if (madeRooms.containsKey(new IndexPoint(x, y + 1)) && madeRooms.get(new IndexPoint(x, y + 1)).down){
                    cleanPossible(possible, 4, true);
                }
                if (madeRooms.containsKey(new IndexPoint(x, y - 1)) && madeRooms.get(new IndexPoint(x, y - 1)).up){
                    cleanPossible(possible, 8, true);
                }

                //this removes rooms with too few entrances.

            if (madeRooms.containsKey(new IndexPoint(x + 1, y)) && !madeRooms.get(new IndexPoint(x + 1, y)).left){
                cleanPossible(possible, 1, false);
            }
            if (madeRooms.containsKey(new IndexPoint(x - 1, y)) && !madeRooms.get(new IndexPoint(x - 1, y)).right){
                cleanPossible(possible, 2, false);
            }

            if (madeRooms.containsKey(new IndexPoint(x, y + 1)) && !madeRooms.get(new IndexPoint(x, y + 1)).down){
                cleanPossible(possible, 4, false);
            }
            if (madeRooms.containsKey(new IndexPoint(x, y - 1)) && !madeRooms.get(new IndexPoint(x, y - 1)).up){
                cleanPossible(possible, 8, false);
            }


                RoomType madeRoom = null;
                while (madeRoom == null) {
                    int q = rand.nextInt(possible.size());
                    madeRoom = possible.get(q);
                    if (madeRoom.bitmaskCode() == 1 || madeRoom.bitmaskCode() == 2 || madeRoom.bitmaskCode() == 4 || madeRoom.bitmaskCode() == 8 && (rand.nextBoolean())){

                        q = rand.nextInt(possible.size());
                        madeRoom = possible.get(q);
                    }
                    madeRooms.put(new IndexPoint(x, y), madeRoom);
                    if (!pathOut(new HashSet<IndexPoint>(), 0, 0)) {
                        madeRoom = null;
                        possible.remove(q);
                    }
                }
                return madeRoom.generate(x, y);
            }
        }
    }
//ensures that te world can never be closed in bar incredibly unlikely scenarios.
    private boolean pathOut(HashSet<IndexPoint> checked, int x, int y) {
        IndexPoint pos = new IndexPoint(x, y);
        checked.add(pos);
        if (madeRooms.containsKey(pos)) {
            return ((madeRooms.get(pos).down && !checked.contains(new IndexPoint(x, y - 1))) ? pathOut(checked, x, y - 1) : false) ||
                    ((madeRooms.get(pos).up && !checked.contains(new IndexPoint(x, y + 1))) ? pathOut(checked, x, y + 1) : false) ||
                    ((madeRooms.get(pos).right && !checked.contains(new IndexPoint(x + 1, y))) ? pathOut(checked, x + 1, y) : false) ||
                    ((madeRooms.get(pos).left && !checked.contains(new IndexPoint(x - 1, y))) ? pathOut(checked, x - 1, y) : false);
        } else return true;
    }


        //this method helps the roomgeneration by comparing bitmasks from the possible rooms to the direction that was chosen (int i).

        //it takes in a  argument list of roomtypes, that is the list that is to be evaluated and reworked to the right number of rooms
        // it takes in an argument int i. This is the bitmask that the roomlist is compared to(and for the relevant cases here, should be directions in the nearby rooms)
        //last argument is a boolean that determines if the bitmask is positive or negative. This is if the evaluation is for walls or entrances.
        // Both need to be checked for proper generation of the world

    private void cleanPossible(List<RoomType> possible, int i, boolean state) {

        for (int k = 0; k < possible.size(); ) {
            if (state) {
                if ((possible.get(k).bitmaskCode() & i) == 0) {


                    possible.remove(k);
                    continue;
                }
                k++;

            }
            if (!state){
                if ((possible.get(k).bitmaskCode() & i) != 0) {


                    possible.remove(k);
                    continue;
                }
                k++;
            }
        }
    }




    public int getMaxDistance() {
        return maxDistanceFromSpawn;
    }



    @Override
    public boolean roomExists(int x, int y) {
        return madeRooms.containsKey(new IndexPoint(x, y));
    }

    class RoomType {
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;

        final List<List<List<java.lang.Character>>> allTypes = new ArrayList<List<List<java.lang.Character>>>();


        //writes rooms as roomtypes; combinations of where they have/doesn't have exits
        public RoomType(boolean r, boolean l, boolean u, boolean d) {
            right = r;
            left = l;
            up = u;
            down = d;
        }

        public int bitmaskCode() {
            return (right ? 1 : 0) + (left ? 2 : 0) + (up ? 4 : 0) + (down ? 8 : 0);
        }

        public void addType(List<List<java.lang.Character>> input) {
            allTypes.add(input);
        }



// the general generation method. Because of all the variables in this, it's still quite complicated even after separating many things into other functions.
// this is the part that generates worldobjects into the world, based on the models in the other generate method.

        public List<WorldObject> generate(int ox, int oy) {

           /* This is the distance formula, and whenever you reach a new maximum distance for the session,
            the difficulty of the game will increase

            Findbugs complains about this, however we need the root
            as an integer, precision is less important. */

            int difficulty = (int) Math.sqrt(ox * ox + oy * oy);
            if (difficulty > maxDistanceFromSpawn) {
                maxDistanceFromSpawn = difficulty;
            }



            //this generates the rooms dependent upon the letters in the WorldRooms.txt file. This ensures that enemies will be of reasonable difficulty, and that WorldObjects will be placed
            // in the correct position.
            List<WorldObject> output = new ArrayList<WorldObject>();
            int k = rand.nextInt(allTypes.size());
            for (int x = 0; x < roomWidth; x++) {
                for (int y = 0; y < roomHeight; y++) {
                    float nx = ox * meter * roomWidth + x * meter;
                    float ny = oy * meter * roomHeight + meter * (14 - y);
                    Vec2 pos = new Vec2(nx, ny);
                    switch (allTypes.get(k).get(x).get(y)) {
                        case 'A':
                            output.add(new AnvilObject(pos));
                            break;
                        case 'B':
                            output.add(new BrickObject(pos));
                            break;
                        case '_':
                            output.add(new Platform(pos));
                            break;
                        case 'C':
                            output.add(new CoinObject(pos));
                            break;
                        case 'Q': {
                            int rnd = new Random().nextInt((100) + 1);
                            if (rnd < (((difficulty / 6) + Math.sqrt(difficulty) * (Math.sin(difficulty) * difficulty * difficulty) / 2) + 0.43) * 100)
                            //this is a difficulty scaling algorithm, and the numbers are nothing special.
                            {

                                Enemy enemy = (new Enemy(pos, new Vec2(meter, meter),
                                        1 * (difficulty / 4), 1 * (difficulty / 8),
                                        1 * (difficulty / 32), 1 * (difficulty / 8),
                                        1 * (difficulty * 2), 1 * (difficulty / 16),
                                        1 * (difficulty / 24), 1 * (difficulty / 24)));
                                output.add(enemy);
                            }
                        }
                        break;
                        case 'S':
                            output.add(new SpikeObject(pos, (difficulty)));
                            output.add(new SpikeObject((new Vec2(pos.x + 32, pos.y)), (difficulty)));
                            break;
                        default:
                            break;

                    }
                }
            }
            return output;
        }
    }

}
