package com.tda367.infinityrun.Roomtemplates;

import com.tda367.infinityrun.WorldObject;

import java.awt.*;
import java.util.*;

/**
 * Created by kaffe on 5/12/17.
 */
public class LogicalMapper extends RoomTemplate {

    private HashMap<IndexPoint, RoomTemplate> rooms = new HashMap<IndexPoint, RoomTemplate>();
    private static int exits = 2;
    private ArrayList<RoomTemplate> roomIndexes = new ArrayList<RoomTemplate>();

    private int checkedL;
    private int checkedU;
    private int checkedR;
    private int checkedD;

    public LogicalMapper() {
        roomIndexes.add(0, new RoomU());
        roomIndexes.add(1, new RoomR());
        roomIndexes.add(2, new RoomD());
        roomIndexes.add(3, new RoomL());
        roomIndexes.add(4, new RoomUR());
        roomIndexes.add(5, new RoomUD());
        roomIndexes.add(6, new RoomUL());
        roomIndexes.add(7, new RoomDL());
        roomIndexes.add(8, new RoomRL());
        roomIndexes.add(9, new RoomRD());
        roomIndexes.add(10, new RoomURD());
        roomIndexes.add(11, new RoomURL());
        roomIndexes.add(12, new RoomRDL());
        roomIndexes.add(13, new RoomUDL());
        roomIndexes.add(14, new RoomURDL());
    }

    /*
    returns -1 for no room exist, 0 for a blocked path and 1 for path.
    (same for all of the check methods)
     */
    private int checkUp(int x, int y) {
        try {
            if (rooms.get(new IndexPoint(x, y + 1)).d) {
            }
        } catch (NullPointerException n) {
            System.out.println("Up");
            return 0;
        }
        if (rooms.get(new IndexPoint(x, y + 1)).d) {
            return 1;
        } else {
            return -1;
        }
    }

    private int checkRight(int x, int y) {
        try {
            if (rooms.get(new IndexPoint(x + 1, y)).l) {
            }
        } catch (NullPointerException n) {
            System.out.println("Right");
            return 0;
        }
        if (rooms.get(new IndexPoint(x + 1, y)).l) {
            return 1;
        } else {
            return -1;
        }
    }

    private int checkDown(int x, int y) {
        try {
            if (rooms.get(new IndexPoint(x, y - 1)).u) {
            }
        } catch (NullPointerException n) {
            System.out.println("Down");
            return 0;
        }
        if (rooms.get(new IndexPoint(x, y - 1)).u) {
            return 1;
        } else {
            return -1;
        }
    }

    private int checkLeft(int x, int y) {
        try {
            if (rooms.get(new IndexPoint(x - 1, y)).r) {
            }
        } catch (NullPointerException n) {
            System.out.println("Left");
            return 0;
        }
        if (rooms.get(new IndexPoint(x - 1, y)).r) {
            return 1;
        } else {
            return -1;
        }
    }


    private RoomTemplate roomRandomizer() {

        //removes 2 exits for every connection.

        if (checkedU == 1) {
            exits -= 2;
        }
        if (checkedR == 1) {
            exits -= 2;
        }
        if (checkedD == 1) {
            exits -= 2;
        }
        if (checkedL == 1) {
            exits -= 2;
        }

        //checks what rooms are available

        ArrayList<RoomTemplate> possibleRooms = new ArrayList<RoomTemplate>();
        possibleRooms.addAll(roomIndexes);
        for (int i = 0; i < possibleRooms.size(); i++) {
            if (checkedU > 0 && !possibleRooms.get(i).u) {
                possibleRooms.remove(possibleRooms.get(i).d);
                i--;
            } else if (checkedR > 0 && !possibleRooms.get(i).r) {
                possibleRooms.remove(possibleRooms.get(i));
                i--;
            } else if (checkedD > 0 && !possibleRooms.get(i).d) {
                possibleRooms.remove(possibleRooms.get(i));
                i--;
            } else if (checkedL > 0 && !possibleRooms.get(i).l) {
                possibleRooms.remove(possibleRooms.get(i));
                i--;
            } else if (checkedU < 0 && possibleRooms.get(i).u) {
                possibleRooms.remove(possibleRooms.get(i));
                i--;
            } else if (checkedR < 0 && possibleRooms.get(i).r) {
                possibleRooms.remove(possibleRooms.get(i));
                i--;
            } else if (checkedD < 0 && possibleRooms.get(i).d) {
                possibleRooms.remove(possibleRooms.get(i));
                i--;
            } else if (checkedL < 0 && possibleRooms.get(i).l) {
                possibleRooms.remove(possibleRooms.get(i));
                i--;
            }
            /*
            if (exits + room.roomExits < 2) {
                possibleRooms.remove(room);
            }
        */

        }
        //randomly picks a room of what's left in the list.
        int rnd = new Random().nextInt(possibleRooms.size());
        exits += possibleRooms.get(rnd).roomExits;
        return possibleRooms.get(rnd);
    }

    private void
    getSurrounding(int x, int y) {
        checkedU = checkUp(x, y);
        checkedR = checkRight(x, y);
        checkedD = checkDown(x, y);
        checkedL = checkLeft(x, y);
    }

    public ArrayList<WorldObject> mapper(int x, int y) {
        getSurrounding(x, y);
        RoomTemplate room = roomRandomizer();
        rooms.put(new IndexPoint(x, y), room);
        room.addRoomObjects(x, y);
        return room.roomObjects;
    }

    public boolean roomExists(int x, int y)
    {
        return rooms.containsKey(new IndexPoint(x,y));
    }
}


