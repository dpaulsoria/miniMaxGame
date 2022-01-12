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
           for(int i=0; i<nulls.size(); i++){
               Pair position = nulls.get(i);
               TreeMap<Integer, ArrayList<Cell>> map1 = Board.cloneMap(map);
               map1.get(position.x).get(position.y).setC(c);
               map1.get(position.x).get(position.y).setSelected(true);
               Capsule cp1 = new Capsule(map1);
               Tree<Capsule> tmp1 = new Tree<>(cp1);
               tmp.addChild(tmp1);
               ArrayList<Pair> nullsc = countNulls(map1);
               System.out.println("Hijo "+i);
               printBoard(map1);
               for(int j=0; j<nullsc.size(); j++ ){
                   Pair position1 = nullsc.get(j);
                   TreeMap<Integer, ArrayList<Cell>> map2 = Board.cloneMap(map1);
                   map2.get(position1.x).get(position1.y).setC(c.equals('X') ? 'O':'X');
                   map2.get(position1.x).get(position1.y).setSelected(true);
                   Capsule cp2 = new Capsule(map2);
                   Tree<Capsule> tmp2 = new Tree<>(cp2);
                   tmp1.addChild(tmp2);
                   System.out.println("Nieto "+j);
                   printBoard(map2);
               }
           }
       }
       return tmp;
    }
    
        public static void printBoard(TreeMap<Integer, ArrayList<Cell>> tablero){
        ArrayList<Cell> F0 = tablero.get(0);
        ArrayList<Cell> F1 = tablero.get(1);
        ArrayList<Cell> F2 = tablero.get(2);
        ArrayList<Character> F0P= new ArrayList<>();
        ArrayList<Character> F1P= new ArrayList<>();
        ArrayList<Character> F2P= new ArrayList<>();
        for(int i=0;i<F0.size();i++){
            if(F0.get(i).getC()=='n'){
                F0P.add('-');
            }
            else{
                Character tmpC=F0.get(i).getC();
                F0P.add(tmpC);
            }
        }
        for(int i=0;i<F1.size();i++){
            if(F1.get(i).getC()=='n'){
                F1P.add('-');
            }
            else{
                Character tmpC=F1.get(i).getC();
                F1P.add(tmpC);
            
            }
        }
        for(int i=0;i<F2.size();i++){
            if(F2.get(i).getC()=='n'){
                F2P.add('-');
            }
            else{
                Character tmpC=F2.get(i).getC();
                F2P.add(tmpC);
            
            }
        }
       
        System.out.println(F0P.get(0)+" "+ F0P.get(1)+" "+ F0P.get(2));
        System.out.println(F1P.get(0)+" "+ F1P.get(1)+" "+ F1P.get(2));
        System.out.println(F2P.get(0)+" "+ F2P.get(1)+" "+ F2P.get(2));
    }
    
//    public static void setUtilities(Tree<Board> board){
//        board.getRoot().getContent().setUtility(board.getRoot().getContent().getGg().utilityFunction());
//        for(Tree<Board> tree:board.getRoot().getChilds())){
//            setUtilities(tree);
//        }
//    }
   
}
