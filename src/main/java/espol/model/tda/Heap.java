/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package espol.model.tda;

import java.util.Comparator;
//import tree.*;

/**
 *
 * @author rdavi
 */
public class Heap<E> {
    private Comparator<E> cmp;
//    private BinaryTree<E> tree;
    private E[] elements;
    private int capacity = 100;
    private int effectiveSize;
    private boolean isMax;
    
    public Heap(Comparator<E> cmp, boolean isMax) {
        elements = (E[])(new Object[capacity]);
//        tree = new BinaryTree<E>(); 
        effectiveSize = 0;
        this.cmp = cmp;
        this.isMax = isMax;
//        this.setTree(createTree(0,this.getTree()));
        
    }
    public Heap(Comparator<E> cmp,E[] elements, boolean isMax) {
        this.elements = elements;
//        tree = new BinaryTree<E>();
        effectiveSize = elements.length;
        this.cmp = cmp;
        this.isMax = isMax;
        this.makeHeap();
    }
    
    private void addCapacity() {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }
    
    public Comparator<E> getCmp() {
        return cmp;
    }

//    public BinaryTree<E> getTree() {
//        return tree;
//    }
//
//    public void setTree(BinaryTree<E> tree) {
//        this.tree = tree;
//    }

    public int size() {
        return effectiveSize;
    }
    
    public boolean isEmpty(){
        return effectiveSize==0;
    }
    
    public static int factorial(int n){     
        if(n == 0){
            return 1;
        }
        return (n*factorial(n-1));
    }
    
    public int posL(int i){
        int i1 = (2*i)+1;
        if(i1<effectiveSize){return i1;}
        return -1;
    }

    public int posR(int i){
        int i1 = (2*i)+2;
        if(i1<effectiveSize){return i1;}
        return -1;
    }
    
    public int posParent(int i){return(i-1)/2;}
    
    public void intercambiar(int ip, int ic){
        E parent = elements[ip];
        E child = elements[ic];
        elements[ip] = child;
        elements[ic] = parent;
    }
    
    public void adjustMax(int i){
        if(i<=(effectiveSize-1) && posL(i)!=-1 && posR(i)!=-1){
            E element = elements[i];
            E elementR = elements[posR(i)];
            E elementL = elements[posL(i)];
            if(cmp.compare(element, elementR)<0){
                if(cmp.compare(elementR, elementL)<0){
                    intercambiar(i,posL(i));
                    adjustMax(posL(i));
                }else{
                    intercambiar(i,posR(i));
                    adjustMax(posR(i));
                }
            }else if(cmp.compare(element, elementL)<0){
                if(cmp.compare(elementL, elementR)<0){
                    intercambiar(i,posR(i));
                    adjustMax(posR(i));
                }else{
                    intercambiar(i,posL(i));
                    adjustMax(posL(i));
                }
            }    
        }
    }
    
    public void adjustMin(int i){
        if(i<=(effectiveSize-1) && posL(i)!=-1 && posR(i)!=-1){
            E element = elements[i];
            E elementR = elements[posR(i)];
            E elementL = elements[posL(i)];
            if(cmp.compare(element, elementR)>0){
                if(cmp.compare(elementR, elementL)>0){
                    intercambiar(i,posL(i));
                    adjustMin(posL(i));
                }else{
                    intercambiar(i,posR(i));
                    adjustMin(posR(i));
                }
            }else if(cmp.compare(element, elementL)>0){
                if(cmp.compare(elementL, elementR)>0){
                    intercambiar(i,posR(i));
                    adjustMin(posR(i));
                }else{
                    intercambiar(i,posL(i));
                    adjustMin(posL(i));
                }
            }    
        }
    }
        
    public void adjust(int i){
        if(this.isMax){ adjustMax(i);
        }else{adjustMin(i);}
    }
    
    public void insertMax(E element){
        if(effectiveSize==capacity){this.addCapacity();}
        if(effectiveSize!=0){
            effectiveSize++;
            int ic = effectiveSize-1;
            elements[ic]=element;
            int ip = posParent(ic);
            while(this.cmp.compare(elements[ic], elements[ip])>0 && ip!=-1){
                intercambiar(ip,ic);
                ic = ip;
                ip = posParent(ic);
            }
//            this.setTree(createTree(0,this.getTree()));
        }else if (effectiveSize==0){
            elements[0]=element;
            effectiveSize++;
//            this.setTree(createTree(0,this.getTree()));
        }
    }

    public void insertMin(E element){
        if(effectiveSize==capacity){this.addCapacity();}
        if(effectiveSize!=0){
            effectiveSize++;
            int ic = effectiveSize-1;
            elements[ic]=element;
            int ip = posParent(ic);
            while(this.cmp.compare(elements[ic], elements[ip])<0 && ip!=-1){
                intercambiar(ip,ic);
                ic = ip;
                ip = posParent(ic);
            }
//            this.setTree(createTree(0,this.getTree()));
        }else if (effectiveSize==0){
            elements[0]=element;
            effectiveSize++;
//            this.setTree(createTree(0,this.getTree()));
        }
    }    
    
    public void insert(E element){
        if(this.isMax){ insertMax(element);
        }else{insertMin(element);}
    }
    
    public E remove(){
        E element = elements[0];
        intercambiar(0,effectiveSize-1);
        elements[effectiveSize-1] = null;
        effectiveSize--;
        adjust(0);
//        this.setTree(createTree(0,this.getTree()));
        return element;
    }
    
    public E get(int i){
     return elements[i];
    }
    
    public void makeHeap(){
        for(int i=(effectiveSize/2)-1; i>=0; i--){adjust(i);}
//        this.setTree(createTree(0,this.getTree()));
    }
    
//    public BinaryTree<E> createTree(int i, BinaryTree<E> tree){
//        if(elements[i]!=null){
//            tree.setRoot(new BinaryNode(elements[i]));
//            if(posR(i)!=-1){tree.setRight(createTree(posR(i), new BinaryTree<E>()));}
//            if(posL(i)!=-1){tree.setLeft(createTree(posL(i), new BinaryTree<E>()));}
//        }
//        return tree;
//    }
    
    public static void main(String[] args){
        Comparator<Integer> cmp = (Integer i1, Integer i2)-> {return i1-i2;};
        Heap<Integer> h = new Heap(cmp, false);
        h.insert(-1);
        h.insert(-2);
        h.insert(2);
        h.insert(1);
        h.insert(1);
        h.insert(0);
        h.insert(1);
        System.out.println("Prueba de desencolamiento");
        while(!h.isEmpty()){
            System.out.println("Elemento removido: "+h.remove());
            System.out.println("////////");
        }
    }
}
