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
import java.util.Comparator;
import java.util.PriorityQueue;
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
       Capsule cp = new Capsule(map, c.equals('X') ? 'O':'X');
       Tree<Capsule> tmp = new Tree<>(cp);
       ArrayList<Pair> nulls = countNulls(map);
       if(!nulls.isEmpty()){
           for(int i=0; i<nulls.size(); i++){
               Pair position = nulls.get(i);
               TreeMap<Integer, ArrayList<Cell>> map1 = Board.cloneMap(map);
               map1.get(position.x).get(position.y).setC(c);
               map1.get(position.x).get(position.y).setSelected(true);
               Capsule cp1 = new Capsule(map1, c);
               Tree<Capsule> tmp1 = new Tree<>(cp1);
               tmp.addChild(tmp1);
               ArrayList<Pair> nullsc = countNulls(map1);
//               System.out.println("Hijo "+i);
//               printBoard(map1);
               for(int j=0; j<nullsc.size(); j++ ){
                   Pair position1 = nullsc.get(j);
                   TreeMap<Integer, ArrayList<Cell>> map2 = Board.cloneMap(map1);
                   map2.get(position1.x).get(position1.y).setC(c.equals('X') ? 'O':'X');
                   map2.get(position1.x).get(position1.y).setSelected(true);
                   Capsule cp2 = new Capsule(map2, c.equals('X') ? 'O':'X');
                   Tree<Capsule> tmp2 = new Tree<>(cp2);
                   tmp1.addChild(tmp2);
//                   System.out.println("Nieto "+j);
//                   printBoard(map2);
               }
           }
       }
       setUtilities(tmp);
       setMax(tmp);
       Capsule c1 = getMaxN(tmp);
       System.out.println("mejor jugada");
       printBoard(c1.getMap());
       System.out.println(c1.getMax());
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
    
    public static void setUtilities(Tree<Capsule> tree){
//        printBoard(tree.getRoot().getContent().getMap());
//        tree.getRoot().getContent().setUtility(utilityFunction(tree.getRoot().getContent().getMap(), tree.getRoot().getContent().getC()));
//        for(Tree<Capsule> tree1:tree.getRoot().getChildren()){
//            if(!tree1.getRoot().getChildren().isEmpty()){
//                setUtilities(tree1);
//            }  
//        }
        for(Tree<Capsule> tree1:tree.getRoot().getChildren()){
            for(Tree<Capsule> tree2:tree1.getRoot().getChildren()){
//                printBoard(tree2.getRoot().getContent().getMap());
                tree2.getRoot().getContent().setUtility(utilityFunction(tree2.getRoot().getContent().getMap(), tree2.getRoot().getContent().getC().equals('X') ? 'O':'X'));
//                System.out.println(tree2.getRoot().getContent().getUtility());
            }
        }
    }
    
    public static int utilityFunction(TreeMap<Integer, ArrayList<Cell>> tablero, Character c){
        int pPlayer=p(tablero,c);
//        System.out.println(">" + c + ":" + pPlayer);
        int pBot=p(tablero,c.equals('X') ? 'O':'X');
//        System.out.println(">" + (c.equals('X') ? 'O':'X') + ":" + pBot);
        return pPlayer-pBot;
    }
    
    public static int p(TreeMap<Integer, ArrayList<Cell>> tablero, Character c){
        int utilP=0;
        int filas=0;
        int columnas=0;
        int diagonales=0;
        ArrayList<Cell> F0 = tablero.get(0);
        ArrayList<Cell> F1 = tablero.get(1);
        ArrayList<Cell> F2 = tablero.get(2);
        //columnas
        for(int i=0;i<F0.size();i++){
            if((F0.get(i).getC()==c || F0.get(i).getC()=='n') && (F1.get(i).getC()==c || F1.get(i).getC()=='n') && (F2.get(i).getC()==c || F2.get(i).getC()=='n')){
                columnas++;
                }
        }
//        System.out.println("    " + columnas);
        //filas
        int tmp=0;
        for(Map.Entry<Integer, ArrayList<Cell>> par: tablero.entrySet()){  //comprobar si el caracter es el mismo o está vacío
            tmp=0;                
            ArrayList<Cell> array=par.getValue();
                    for(int i=0;i<array.size();i++){
                        if(array.get(i).getC()==c || array.get(i).getC()=='n')
                            tmp++;                  
                    } 
            if(tmp==3){
            filas++;
            }
        }
//        System.out.println("    " + filas);
        //diagonales 
        if((F0.get(0).getC()==c || F0.get(0).getC()=='n') && (F1.get(1).getC()==c || F1.get(1).getC()=='n') && (F2.get(2).getC()==c || F2.get(2).getC()=='n')){
            diagonales++;
        }        
        if((F0.get(2).getC()==c || F0.get(2).getC()=='n') && (F1.get(1).getC()==c || F1.get(1).getC()=='n') && (F2.get(0).getC()==c || F2.get(0).getC()=='n')){
            diagonales++;
        }
//        System.out.println("    " + diagonales);
        utilP=filas+columnas+diagonales;                    
        return utilP;
    }
    
   public static void setMax(Tree<Capsule> tree){
        for(Tree<Capsule> tree1:tree.getRoot().getChildren()){
           tree1.getRoot().getContent().setMax(getMaxT(tree1));
           printBoard(tree1.getRoot().getContent().getMap());
           System.out.println(tree1.getRoot().getContent().getMax());
       }
   }
   
   public static int getMaxT(Tree<Capsule> tree){
       int max = 0;
       Comparator<Capsule> cmp = (Capsule i1, Capsule i2)-> {return i1.getUtility()-i2.getUtility();};
       PriorityQueue<Capsule> q = new PriorityQueue<>(cmp);
       for(Tree<Capsule> tree1:tree.getRoot().getChildren()){
           q.offer(tree1.getRoot().getContent());
       }       
       max = q.poll().getUtility();
       return max;
   }
   
   public static Capsule getMaxN(Tree<Capsule> tree){
       Capsule c = new Capsule();
       Comparator<Capsule> cmp = (Capsule i1, Capsule i2)-> {return i2.getMax()-i1.getMax();};
       PriorityQueue<Capsule> q = new PriorityQueue<>(cmp);
       for(Tree<Capsule> tree1:tree.getRoot().getChildren()){
           q.offer(tree1.getRoot().getContent());
       }       
       c = q.poll();
       return c;
   }
}
