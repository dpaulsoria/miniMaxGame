/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package espol.model.tda;

import java.util.Comparator;

/**
 *
 * @author rdavi
 */
public class Heap<E> {
    private Comparator<E> cmp;
    private E[] elements;
    private int capacity = 100;
    private int effectiveSize;
    private boolean isMax;
    
    public Heap(Comparator<E> cmp, boolean isMax) {
        elements = (E[])(new Object[capacity]);
        effectiveSize = 0;
        this.cmp = cmp;
        this.isMax = isMax;
        
    }
    public Heap(Comparator<E> cmp,E[] elements, boolean isMax) {
        this.elements = elements;
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
        }else if (effectiveSize==0){
            elements[0]=element;
            effectiveSize++;
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
        }else if (effectiveSize==0){
            elements[0]=element;
            effectiveSize++;
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
        return element;
    }
    
    public E get(int i){
     return elements[i];
    }
    
    public void makeHeap(){
        for(int i=(effectiveSize/2)-1; i>=0; i--){adjust(i);}
    }
}
