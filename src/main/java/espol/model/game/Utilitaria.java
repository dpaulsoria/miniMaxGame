/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.model.game;

import espol.model.tda.Tree;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import espol.model.game.Board;
import javafx.scene.image.Image;

/**
 *
 * @author rdavi
 */
public class Utilitaria {
    public static int count =0;
    public static ArrayList<Pair> countNulls(TreeMap<Integer, ArrayList<Cell>> mapa){
        ArrayList<Pair> nulls = new ArrayList();
        
        for(Map.Entry<Integer, ArrayList<Cell>> par: mapa.entrySet()){
            for(Cell c:par.getValue()){
                if(c.getC().equals('n')){
                    nulls.add(c.getPosition());
                }
            }
        }
        
        return nulls;
    }


    public static Tree<Capsule> createTree(TreeMap<Integer, ArrayList<Cell>> map, Character c){
       Capsule cp = new Capsule(map);
       Tree<Capsule> tmp = new Tree<>(cp);
       ArrayList<Pair> nulls = countNulls(map);
       if(!nulls.isEmpty()){
           for(int i=0; i<1; i++){
               count++;
               System.out.println(count);
               Pair position = nulls.get(i);
               TreeMap<Integer, ArrayList<Cell>> map1 = Board.cloneMap(map);
               map1.get(position.x).get(position.y).setC(c);
               map1.get(position.x).get(position.y).setSelected(true);
               map1.get(position.x).get(position.y).setImage((c.equals('X') ? Images.X:Images.O));
               Capsule cp1 = new Capsule(map1);
               Tree<Capsule> tmp1 = new Tree<>(cp1);
//                       createTree(map1, c.equals('X') ? 'O':'X');
               tmp.addChild(tmp1);
//               createTree(map1, c.equals('X') ? 'O':'X');
           }
       }
       return tmp;
    }
    
//    public static void setUtilities(Tree<Board> board){
//        board.getRoot().getContent().setUtility(board.getRoot().getContent().getGg().utilityFunction());
//        for(Tree<Board> tree:board.getRoot().getChilds())){
//            setUtilities(tree);
//        }
//    }
   
}
