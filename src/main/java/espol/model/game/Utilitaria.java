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


    public static Tree<Board> createTree(Board board, Character c){
       Tree<Board> tmp = new Tree<>(board);
       ArrayList<Pair> nulls = countNulls(board.getMap());
       Pair pos;
       System.out.println(nulls);
       if(!nulls.isEmpty()){
           for(int i=0; i<5; i++){
               pos = nulls.get(i);
               Board btmp = Board.clone(board);
               btmp.markIn(pos, c);
               System.out.println("To mark in:" + pos);
               btmp.getGg().printBoard();
               //Tree<Board> tmp1 = createTree(btmp, c.equals('X') ? 'O':'X');
               tmp.addChild(new Tree<>(btmp));
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
