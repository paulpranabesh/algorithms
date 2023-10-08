package com.example.algorithm.mapping;

/**
 You are with your friends in a castle, where there are multiple rooms named after flowers. Some of the rooms contain treasures - we call them the treasure rooms.

 Each room contains a single instruction that tells you which room to go to next.

 *** instructions_1 ***

 lily ---------     daisy  sunflower
 |       |     |
 v       v     v
 jasmin ->  tulip      violet    -> rose --->
 ^    |      ^             ^     |
 |    |      |             |     |
 ------    iris            -------

 This is given as a list of pairs of (source_room, destination_room)

 instructions_1 = [
 ["jasmin", "tulip"],
 ["lily", "tulip"],
 ["tulip", "tulip"],
 ["rose", "rose"],
 ["violet", "rose"],
 ["sunflower", "violet"],
 ["daisy", "violet"],
 ["iris", "violet"]
 ]

 Write a function that takes two parameters as input:
 * a list containing the treasure rooms, and
 * a list of instructions represented as pairs of (source_room, destination_room)

 and returns a collection of all the rooms that satisfy the following two conditions:

 * at least two *other* rooms have instructions pointing to this room
 * this room's instruction immediately points to a treasure room

 treasure_rooms_1 = ["lily", "tulip", "violet", "rose"]

 filter_rooms(treasure_rooms_1, instructions_1) => ["tulip", "violet"]
 * tulip can be accessed from rooms lily and jasmin. Tulip's instruction points to a treasure room (tulip itself)
 * violet can be accessed from daisy, sunflower and iris. Violet's instruction points to a treasure room (rose)

 Additional inputs

 treasure_rooms_2 = ["lily", "jasmin", "violet"]

 filter_rooms(treasure_rooms_2, instructions_1) => []
 * none of the rooms reachable from tulip or violet are treasure rooms

 *** instructions_2 ***

 lily --------             ------
 |          |      |
 v          v      |
 jasmin ->  tulip -- > violet ---^

 instructions_2 = [
 ["jasmin", "tulip"],
 ["lily", "tulip"],
 ["tulip", "violet"],
 ["violet", "violet"]
 ]

 treasure_rooms_3 = ["violet"]

 filter_rooms(treasure_rooms_3, instructions_2) => [tulip]
 * tulip can be accessed from rooms lily and jasmin. Tulip's instruction points to a treasure room (violet)

 All the test cases:
 filter_rooms(treasure_rooms_1, instructions_1)    => ["tulip", "violet"]
 filter_rooms(treasure_rooms_2, instructions_1)    => []
 filter_rooms(treasure_rooms_3, instructions_2)    => [tulip]

 Complexity Analysis variables:
 T: number of treasure rooms
 I: number of instructions given
 */
import java.util.*;
import java.util.stream.Collectors;


public class CastleProblem {
    public static void main(String[] argv) {
        String[][] instructions_1 = {
                {"jasmin", "tulip"},
                {"lily", "tulip"},
                {"tulip", "tulip"},
                {"rose", "rose"},
                {"violet", "rose"},
                {"sunflower", "violet"},
                {"daisy", "violet"},
                {"iris", "violet"}
        };
        String[][] instructions_2 = {
                {"jasmin", "tulip"},
                {"lily", "tulip"},
                {"tulip", "violet"},
                {"violet", "violet"}
        };
        String[] treasure_rooms_1 = {"lily", "tulip", "violet", "rose"};
        String[] treasure_rooms_2 = {"lily", "jasmin", "violet"};
        String[] treasure_rooms_3 = {"violet"};
        Set<String>  set = getQualifiedRooms(instructions_2, treasure_rooms_3);
        System.out.println(set);
    }

    public static Set<String> getQualifiedRooms(String[][] instructions, String[] treasureRooms){
        Map<String, Set<String>> fromRoomMap = new HashMap<>();
        Map<String, String> moveMap = new HashMap<>();
        Set<String> treasureRoomsSet = new HashSet<>();

        Arrays.stream(treasureRooms).forEach(treasureRoomsSet::add);

        Arrays.stream(instructions).forEach(it->moveMap.put(it[0], it[1]));
        Arrays.stream(instructions).forEach(it->{
            String toRoom = it[1];
            String fromRoom = it[0];
            if(!toRoom.equalsIgnoreCase(fromRoom)){
                fromRoomMap.putIfAbsent(toRoom, new HashSet<>());
                fromRoomMap.computeIfPresent(it[1], (curr, prev)->{
                    prev.add(fromRoom);
                    return prev;
                });
            }
        });

        Map<String, Set<String>> filteredMap = fromRoomMap.entrySet().stream().filter(e->
                e.getValue().size()>=2).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));

        return filteredMap.keySet().stream().filter(k-> treasureRoomsSet.contains(moveMap.get(k))
                ).collect(Collectors.toCollection(HashSet::new));
    }
}

