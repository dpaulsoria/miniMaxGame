package espol.model.tda;

import java.util.Comparator;

public class Heap<T> {
    private Comparator<T> cmp;
    private T[] array;
    private int capacity;
    private int effectiveSize;
    private boolean isMax;

    public Heap(int max, Comparator<T> c, boolean b) {
        cmp = c;
        capacity =  max;
        array = (T[]) new Object[capacity];
        effectiveSize = 0;
        isMax = b;
    }

    public Comparator<T> getCmp() {
        return cmp;
    }

    public void setCmp(Comparator<T> cmp) {
        this.cmp = cmp;
    }
    /*
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
*/
    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEffectiveSize() {
        return effectiveSize;
    }

    public void setEffectiveSize(int effectiveSize) {
        this.effectiveSize = effectiveSize;
    }

    public boolean isMax() {
        return isMax;
    }

    public void setMax(boolean max) {
        isMax = max;
    }

    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    public T getChild(int index) {
        if (index > effectiveSize) return null;
        return array[index];
    }

    public T getLeft(int i) {
        return getChild(i*2 + 1);
    }

    public T getRight(int i) {
        return getChild(i*2 + 2);
    }

    public T getFather(int i) {
        if (i != 0) {
            if (i > effectiveSize) return null;
            return array[(i-1)/2];
        }
        return null;
    }
    // Cambiar
    public void imprimirArreglo() {
        for (int i = 0; i < effectiveSize; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print("\n");
        for (int i = 0; i < effectiveSize; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    public void buildHeap() {
        for (int i = (effectiveSize / 2) - 1; i >= 0; i--) {
            fix(i);
        }
    }
    public void fix(int i) {
        int maxIndex = i;
        int Left = ((i * 2) + 1);
        int Right = (i * 2 + 2);

        if (Left >= 0 && Left < effectiveSize && cmp.compare(array[Left], array[i]) >= 0) {
            maxIndex = Left;
            if (Right >= 0 && Right < effectiveSize && cmp.compare(array[Right], array[maxIndex]) >= 0) {
                maxIndex = Right;
                if (maxIndex != i) {
                    T tmp = array[maxIndex];
                    array[maxIndex] = array[i];
                    array[i] = tmp;
                    fix(maxIndex);
                }
            } else {
                if (maxIndex != i) {
                    T tmp = array[maxIndex];
                    array[maxIndex] = array[i];
                    array[i] = tmp;
                    fix(maxIndex);
                }
            }
        } else if (Right >= 0 && Right < effectiveSize && cmp.compare(array[Right], array[maxIndex]) >= 0) {
            maxIndex = Right;
            if (maxIndex != i) {
                T tmp = array[maxIndex];
                array[maxIndex] = array[i];
                array[i] = tmp;
                fix(maxIndex);
            }
        }
    }

    public T remove() {
        T maxValue = null;
        if (!isEmpty()) {
            maxValue = array[0];
            array[0] = array[effectiveSize - 1];
            effectiveSize--;
            fix(0);
        }
        return maxValue;
    }

    private int insert(T t, int pos) {
        fix(pos);
        return indexFather(pos);
    }

    public void insert(T t) {
        if (effectiveSize >= capacity) {
            addCapacity();
        }
        array[effectiveSize] = t;
        effectiveSize++;
        int programa = indexFather(effectiveSize - 1);
        while (programa > 0) {
            programa = insert(t, programa);
        }
        programa = insert(t, programa);
    }

    public int indexFather(int i) {
        return (i - 1) / 2;
    }

    private void addCapacityT() {
        T[] tmp = (T[]) new Object[capacity * 2];
        if (capacity >= 0) System.arraycopy(array, 0, tmp, 0, capacity);
        array = tmp;
        capacity = capacity * 2;
    }
    private void addCapacity() {
        T[] tmp = (T[]) new Object[this.capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = array[i];
        }
        array = tmp;
        capacity = capacity * 2;
    }
}
