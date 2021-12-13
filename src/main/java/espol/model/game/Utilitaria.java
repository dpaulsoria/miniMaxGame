/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.model.game;

import espol.model.tda.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author rdavi
 */
public class Utilitaria {
    public static ArrayList<Pair> countNulls(TreeMap<Integer, ArrayList<Cell>> mapa){
        ArrayList<Pair> nulls = new ArrayList();
        
        for(Map.Entry<Integer, ArrayList<Cell>> par: mapa.entrySet()){
            for(Cell c:par.getValue()){
                if(c.getC().equals('n')){
                    nulls.addLast(c.getPosition());
                }
            }
        }
        
        return nulls;
    }
}
