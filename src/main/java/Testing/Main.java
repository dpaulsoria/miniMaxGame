package Testing;

import espol.model.game.Cell;
import espol.model.game.Pair;
import Testing.Persona;

import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
    public static void main(String args[]) {
        TreeMap<Integer, ArrayList<Persona>> map = new TreeMap<>();
        for (int i = 0; i<3; i++) {
            ArrayList<Persona> tmp = new ArrayList<>();
            for (int j = 0; j<3; j++) {
                Persona cell = new Persona(new Pair(i,j), false);
                tmp.add(cell);
            }
            map.put(i, tmp);
        }
        System.out.println(map);
        System.out.println();
        System.out.println(cloneMap(map));
    }

    public static TreeMap<Integer, ArrayList<Persona>> cloneMap(TreeMap<Integer, ArrayList<Persona>> map) {
        TreeMap<Integer, ArrayList<Persona>> newMap = new TreeMap<>();
        for (int i = 0; i<3; i++) {
            ArrayList<Persona> tmp = new ArrayList<>();
            for (int j = 0; j<3; j++) {
                Persona newCell = new Persona(map.get(i).get(j).getPosition().clone(), map.get(i).get(j).isSelected());
                tmp.add(newCell);
            }
            newMap.put(i, tmp);
        }
        return newMap;
    }
}
