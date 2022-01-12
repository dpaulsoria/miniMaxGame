/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.model.game;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author rdavi
 */
public class Capsule {
    private TreeMap<Integer, ArrayList<Cell>> map;
    private int utility;
    private int max;

    public Capsule(TreeMap<Integer, ArrayList<Cell>> map) {
        this.map = map;
        utility = 0;
        max = 0;
    }

    public TreeMap<Integer, ArrayList<Cell>> getMap() {
        return map;
    }

    public void setMap(TreeMap<Integer, ArrayList<Cell>> map) {
        this.map = map;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    
}
