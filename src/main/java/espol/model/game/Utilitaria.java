/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.model.game;

import espol.model.tda.Tree;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import espol.model.game.Board;

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
       if(!nulls.isEmpty()){
           for(int i=0; i<nulls.size(); i++){
               Pair position = nulls.get(i);
               Board btmp = Board.clone(board);
               btmp.markIn(position, c);
               Tree<Board> tmp1 = createTree(btmp, c.equals('X') ? 'O':'X');
               tmp.addChild(tmp1);
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
