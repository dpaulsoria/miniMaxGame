package espol.model.tda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = new BinaryNode<>();
    }
    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }
    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }
    public BinaryNode<T> getRoot() {
        return root;
    }
    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }
    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }
    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }
    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }
    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }
    public boolean isEmpty() {
        return this.root == null;
    }
    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }
    public int size() {
        return size(root);
    }
    private int size(BinaryNode<T> node) {
        if (node == null) return 0;
        else return(size(node.getLeft().getRoot()) + size(node.getRight().getRoot()) + 1);
    }
    
    public static BinaryTree<String> buildExpressionTree(String exp) {
        String[] tokens = exp.split(" ");
        Stack<BinaryTree<String>> s = new Stack();
        System.out.println(tokens.length);
        for (int i = 0; i<exp.length()-1; i++) {
            System.out.println("i: " + i);
            System.out.println("token: " + tokens[i]);
            String token = tokens[i];
            BinaryTree<String> t = new BinaryTree<>(token);
            if (isOperand(token)) s.push(t);
            else { 
                if (!s.isEmpty()) t.getRoot().setRight(s.pop()); 
                if (!s.isEmpty()) t.getRoot().setLeft(s.pop()); 
            }
            s.push(t);
        } 
        return s.pop();
    }
    
    private static boolean isOperand(String token) {
        return !isOperator(token);
    }
    
    private static boolean isOperator(String token) {
        return token.equalsIgnoreCase("+") ||
               token.equalsIgnoreCase("-") ||
               token.equalsIgnoreCase("*") ||
               token.equalsIgnoreCase("/");
    }
    
    public static Integer evaluateExpressionTree(BinaryTree<String> t) {
        if (t.isEmpty()) return 0;
        else if (t.isLeaf()) return Integer.parseInt(t.getRoot().getContent());
        else {
            int Lv = 0;
            int Rv = 0;
            
            if (t.getRoot().hasLeft()) Lv = evaluateExpressionTree(t.getLeft());
            // Implementar getNeutral
            if (t.getRoot().hasRight()) Rv = evaluateExpressionTree(t.getRight());
            // Implementar getNeutral
            return operate(t.getRoot().getContent(), Lv, Rv);
        }
    }
    
    private static Integer operate(String op, int l, int r) {
        switch (op) {
            case "+": return l+r;
            case "-": return l-r;
            case "*": return l*r;
            case "/": return (r != 0) ? l/r:0;
            default: return Integer.MIN_VALUE;
        }
    }
    
    
    private int countNodes() {
        if (isEmpty()) return 0;

        int c=1;
        int left = (this.getRoot().hasLeft())? 1:0;
        int right = (this.getRoot().hasRight())? 1:0;
        
        c+=left+right;
        
        if(this.getRoot().hasLeft()){
            c+= getLeft().countNodes();
        }
        if(this.getRoot().hasRight()){
            c+= getRight().countNodes();
        }
        return c;
    }
    
    public int countLeavesRecursive() {
        if (isEmpty()) return 0;
        if (isLeaf()) return 1;

        int LL = 0;
        int Lr = 0;
        if (this.root.hasLeft()) {
            LL = this.root.getLeft().countLeavesRecursive();
        }
        if (this.root.hasRight()) {
            Lr = this.root.getRight().countLeavesRecursive();
        }
        return LL + Lr;
        
    }

    public int countLeavesIterative() {
        if (isEmpty()) return 0;
        Stack<BinaryTree<T>> stack = new Stack();
        int c = 0;
        stack.push(this);
        while (!stack.empty()) {
            BinaryTree<T> subtree = stack.pop();
            if (subtree.root.hasLeft()) {
                stack.push(subtree.root.getLeft());
            }
            if (subtree.root.hasRight()) {
                stack.push(subtree.root.getRight());
            }
            if (subtree.isLeaf()) {
                c++;
            }
        }

        return c;
    }

    // 1 - Taller
    public BinaryNode<T> searchRecursive(T c, Comparator<T> cmp) {
        if (c == null || isEmpty() || cmp == null) return null;

        if (cmp.compare(this.root.getContent(), c) == 0) return this.root;
        else {
            BinaryNode<T> tmp = null;
            if (root.hasLeft()) {
                return this.root.getLeft().searchRecursive(c, cmp);
            }
            if (tmp == null & root.hasRight()) {
                return root.getRight().searchRecursive(c, cmp);
            }
            return tmp;
        }        
    }

    public BinaryNode<T> searchIterative(T c, Comparator<T> cmp) {
        if (isEmpty() || c == null || cmp == null) return null;
        Stack<BinaryTree<T>> stack = new Stack();        
        stack.push(this);
        while (!stack.empty()) {
            if (cmp.compare(c, stack.get(0).root.getContent()) == 0) {
                return stack.get(0).root;
            }
            BinaryTree<T> subtree = stack.pop();
            if (subtree.root.hasLeft()) {
                stack.push(subtree.root.getLeft());
            }
            if (subtree.root.hasRight()) {
                stack.push(subtree.root.getRight());
            }
            if (cmp.compare(c, subtree.root.getContent()) == 0) {
                return subtree.root;
            }
        }
        return null;
    }
    
    // 2 - Taller
    public BinaryNode<T> getMinRecursive(Comparator<T> c) {
        if (isEmpty()) return null;
        else if (this.isLeaf()) return root;
        else {
            BinaryNode<T> n = this.root;
            if (this.root.hasLeft()) {
                BinaryNode<T> travellerLeft = this.root.getLeft().getMinRecursive(c);
                if (c.compare(n.getContent(), travellerLeft.getContent()) > 0) {
                    n = travellerLeft;
                }
            }
            if (this.root.hasRight()) {
                BinaryNode<T> travellerRight = this.root.getRight().getMinRecursive(c);
                if (c.compare(n.getContent(), travellerRight.getContent()) > 0) {
                    n = travellerRight;
                }
            }
            return n;
        }
    }

    public BinaryNode<T> getMinIterative(Comparator<T> cmp) {
        if (isEmpty()) return null;
        Stack<BinaryTree<T>> stack = new Stack<>();
        BinaryNode<T> MinNode;
        MinNode = this.root;
        stack.push(this);
        while (!stack.isEmpty()) {
            BinaryTree<T> st = stack.pop();
            if (cmp.compare(st.root.getContent(), MinNode.getContent()) < 0) {
                MinNode = st.root;
            }
            if (st.root.hasLeft()) {
                stack.push(st.root.getLeft());
            }
            if (st.root.hasRight()) {
                stack.push(st.root.getRight());
            }
        }
        return MinNode;        
    }
    
    // 3 - Taller
    public int countDescendantsRecursive() {
        if (isEmpty() || isLeaf()) return 0;
        int L = 0;
        if (this.root.hasLeft()) {
            L++;
            L += this.root.getLeft().countDescendantsRecursive();
        }
        if (this.root.hasRight()) {
            L++;
            L += this.root.getRight().countDescendantsRecursive();
        }
        return L;
        
    }

    public int countDescendantsIterative() {        
        if (isEmpty()) return 0;
        Stack<BinaryTree<T>> stack = new Stack();
        int c = 0;
        stack.push(this);
        while (!stack.empty()) {
            BinaryTree<T> st = stack.pop();
            if (st.root.hasLeft()) {
                c++;
                stack.push(st.root.getLeft());
            }
            if (st.root.hasRight()) {
                c++;
                stack.push(st.root.getRight());
            }

        }        
        return c;
    }
    
    
    
    //Tarea: 

    // 1
    

    public BinaryNode<T> findParentRecursive(BinaryNode<T> n) {
        BinaryNode<T> result = null;
        if (isEmpty() || isLeaf() || getRoot() == n) return result;
        
        if (root.hasLeft()) {
            BinaryNode<T> Ln = root.getLeft().getRoot();
            if (Ln == n) return root;
            else result = this.root.getLeft().findParentRecursive(n);
        }
        if (root.hasRight()) {
            BinaryNode<T> Rn = root.getRight().getRoot();
            if (Rn == n) return root;
            else result = root.getRight().findParentRecursive(n);
        }
        return result;
    }


    public BinaryNode<T> findParentIterative(BinaryNode<T> n) {
        if (n == null || isEmpty() || n == getRoot()) return null;
        
        Stack<BinaryTree<T>> stack = new Stack();
        stack.push(this);
        while (!stack.empty()) {
            BinaryTree<T> subtree = stack.pop();

            if (subtree.getRoot().hasLeft()) {
                BinaryNode<T> Ln = subtree.root.getLeft().getRoot();
                if (Ln == n) {
                    return subtree.root;
                } else {
                    stack.push(subtree.root.getLeft());
                }
            }
            if (subtree.getRoot().hasRight()) {
                BinaryNode<T> Rn = subtree.root.getRight().getRoot();
                if (Rn == n) {
                    return subtree.root;
                } else {
                    stack.push(subtree.root.getRight());
                }
            }
            if (subtree.isLeaf()) {
                // Empty
            }
        }
        
        return null;
    }
    // 2
    public int countLevelsRecursive() {
        if (isEmpty()) return 0;
        if (isLeaf()) return 1;

        int LL = 0;
        int LR = 0;
        if (getRoot().hasLeft()) {
            LL++;
            LL += root.getLeft().countLeavesRecursive();
        }
        if (getRoot().hasRight()) {
            LR++;
            LR += root.getRight().countLeavesRecursive();
        }
        return (LL > LR ? LL:LR);
    }

    public int countLevelsIterative() {
        if (isEmpty()) return 0;        
        Stack<BinaryTree<T>> stack = new Stack();
        int c = 0;
        stack.push(this);
        while (!stack.empty()) {

            BinaryTree<T> st = stack.pop();
            if (!st.isLeaf()) {
                if (st.getRoot().hasLeft()) {
                    stack.push(st.root.getLeft());
                }
                else {
                    stack.push(new BinaryTree<>(new BinaryNode<>(st.root.getContent())));
                }
                if (st.getRoot().hasRight()) {

                    stack.push(st.root.getRight());
                }
                else {
                    stack.push(new BinaryTree<>(new BinaryNode<>(st.root.getContent())));
                }
            } else {
                c++;
            }
        }
        return c;
    }
    
    // 3
    
    public boolean isLeftyIterative(){
        if (isEmpty() || isLeaf()) return true;
        else {
            Stack<BinaryTree<T>> stack = new Stack();
            stack.push(this);
            int Left = 0;
            int Right = 0;
            while(!stack.isEmpty()){                
                BinaryTree<T> st = stack.pop();
                if (st.getRoot().hasLeft()) {
                    Left += st.getLeft().countDescendantsRecursive();
                    System.out.println("Root: " + st.getLeft().getRoot().toString() + " L:" + Left);
                    stack.push(st.getLeft());
                }
                if (st.getRoot().hasRight()) {
                    Right += st.getRight().countDescendantsRecursive();
                    System.out.println("Root: " + st.getRight().getRoot().toString() + " R:" + Right);
                    stack.push(st.getRight());
                }
            }
            System.out.println("Left: " + Left + ", Right: " + Right);
            return (Left > (Right + Left)/2);
        }
    
    }
    
    public boolean isLeftyRecursive() {
        return (logicaIsLeftyRecursive()>0);
    }
    private int logicaIsLeftyRecursive() {
        int c = 0;
        if (isEmpty() || isLeaf()) return 1;
        else {
            int LL = 0;
            int LR = 0;
            if (getRoot().hasLeft()) {
                c = this.root.getLeft().logicaIsLeftyRecursive();
                if (c == -1) return -1;
                else LL += c + 1;
                
            }
            if (getRoot().hasRight()) {
                c = this.root.getRight().logicaIsLeftyRecursive();
                if (c == -1) return -1;
                else LR += c + 1;
                
            }
            return (LL > (LL + LR)/2) ? LL+LR:-1;            
        }
    }
    
    
    // 4
    
    public boolean isIdenticalRecursive(BinaryTree<T> tmp) {
        boolean c1 = true;
        boolean c2 = true;
        
        if (this.getRoot().hasLeft() && tmp.getRoot().hasLeft()) {
            c1 = this.root.getLeft().isIdenticalRecursive(tmp.getLeft());
        } else if (!getRoot().hasLeft() && !tmp.getRoot().hasLeft()) {
        } else {
            return false;
        }
        if (this.getRoot().hasRight() && tmp.getRoot().hasRight()) {
            c2 = this.root.getRight().isIdenticalRecursive(tmp.getRight());
        } else if (!getRoot().hasRight() && !tmp.getRoot().hasRight()) {
        } else {
            return false;
        }
        if (c1 && c2) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean isIdenticalIterative(BinaryTree<T> t) {

        if (isEmpty() && t.isEmpty()) return true;
        if (isEmpty() || t.isEmpty()) return false;
        
        Stack<BinaryTree<T>> stack = new Stack();
        Stack<BinaryTree<T>> stackCopy = new Stack();        

        stack.push(this);
        stackCopy.push(t);
        
        while (!stack.empty() && !stackCopy.empty()) {
            BinaryTree<T> subtree = stack.pop();
            BinaryTree<T> subtreeCopy = stackCopy.pop();
            
            if (subtree.getRoot().getContent() != subtreeCopy.getRoot().getContent()) return false;

            if (subtree.getRoot().hasLeft() && subtreeCopy.getRoot().hasLeft()) {                
                stack.push(subtree.root.getLeft());
                stackCopy.push(subtreeCopy.root.getLeft());
            } else if (!subtree.getRoot().hasLeft() && !subtreeCopy.getRoot().hasLeft()) {
                // Empty
            } else {
                return false;
            }
            
            if (subtree.getRoot().hasRight() && subtreeCopy.getRoot().hasRight()) {                
                stack.push(subtree.root.getRight());
                stackCopy.push(subtreeCopy.root.getRight());
            } else if (!subtree.getRoot().hasRight() && !subtreeCopy.getRoot().hasRight()) {
                // Empty
            } else {
                return false;
            }
        }        
        
        return true;

    }
    
    // 5
    
    public static void largestValueOfEachLevelIterative(BinaryTree<Integer> t){
        if(t.isEmpty()) return;
        Queue<BinaryTree<Integer>> stack = new LinkedList<>();
        stack.offer(t);
        
        while(!stack.isEmpty()){
            int size = stack.size();
            int result = Integer.MIN_VALUE;
            for(int i=0; i<size;i++){
                BinaryTree<Integer> Current = stack.poll();
                BinaryTree<Integer> Left = Current.getLeft();
                BinaryTree<Integer> Right = Current.getRight();
                BinaryNode<Integer> Parent = Current.root;
                
                if(Parent != null && result < Parent.getContent()) result=Parent.getContent();
                if(Left!=null) stack.offer(Left);
                if(Right!=null) stack.offer(Right);
            }
            System.out.println(result);

        }
    }
    
    public static void largest(BinaryTree<Integer> t, Integer level, Map<Integer, BinaryNode<Integer>> map){
        if (t.isEmpty()) System.out.println("");
        if (map.containsKey(level)) {
            if (map.get(level).getContent() < t.getRoot().getContent()) map.put(level, t.getRoot());
        } else  map.put(level, t.getRoot());

        if (t.getRoot().hasLeft()) BinaryTree.largest(t.getLeft(), level+1, map);
        if (t.getRoot().hasRight()) BinaryTree.largest(t.getRight(), level+1, map);

    }
    
    public static void largestValueOfEachLevelRecursive(BinaryTree<Integer> t) {
        Map<Integer, BinaryNode<Integer>> map = new TreeMap<>();
        BinaryTree.largest(t, 0, map);
        map.forEach((k, v) -> {
            System.out.print(v + " ");
        });
    }
 
    // 6

    public int countNodesWithOnlyChildRecursive(){
        if (isEmpty()) return 0;
        int c = 0;
        if(getRoot().hasLeft() && !getRoot().hasRight() || !getRoot().hasLeft() && getRoot().hasRight()){
            c++;
        }
        if(getRoot().hasLeft()){
            c += this.getLeft().countNodesWithOnlyChildRecursive();
        }
        if(getRoot().hasRight()){
            c += this.getRight().countNodesWithOnlyChildRecursive();
        }
        return c;
    }
    public int countNodesWithOnlyChildIterative(){
        if(isEmpty()) return 0;
        int c;
        Stack<BinaryTree<T>> stack = new Stack();
        stack.push(this);
        c=0;
        
        while(!stack.isEmpty()){
            BinaryTree<T> st = stack.pop();
            BinaryTree<T> Lt = st.getLeft();
            BinaryTree<T> Rt = st.getRight();
            
            if(!st.isLeaf()){
                int cL = (Lt!=null)? 1:0;
                int cR = (Rt!=null)? 1:0;
                if((cL+cR)==1) c++;
            }
            
            if(Lt != null){
                stack.push(Lt);

            }
            if(Rt != null){
                stack.push(Rt);
            }
        }
        return c;
    
    }
    // 7
    public boolean isHeightBalancedRecursive(){
        if(isEmpty()) return true;
        if(getRoot().hasLeft() && getRoot().hasRight()) 
            return getLeft().isHeightBalancedRecursive() && getRight().isHeightBalancedRecursive();
        else if(!getRoot().hasLeft() && !getRoot().hasRight()) return true;
        else{
            BinaryTree<T> hoja = (getRoot().hasLeft())? getLeft():getRight();
            return hoja.isLeaf();
        }
    }

    public boolean isHeightBalancedIterative(){
        if(isEmpty()) return true;
        int L=0;
        int R=0;
        
        if(getLeft() != null){
            Iterator<List<BinaryTree<T>>> it = getLeft().lvlIterator();
            while(it.hasNext()){
                L++;
                it.next();
            }
        }
        if(getRight() != null){
            Iterator<List<BinaryTree<T>>> it = getRight().lvlIterator();
            while(it.hasNext()){
                R++;
                it.next();
            }
        }
        return Math.abs(L-R)<=1;
    }
    
    private Iterator<List<BinaryTree<T>>> lvlIterator(){
        return new levelIterator<>(this);
    }
    private static class levelIterator<T> implements Iterator<List<BinaryTree<T>>>{

        private final Stack<BinaryTree<T>> stack;
        private final List<BinaryTree<T>> list;

        public levelIterator(BinaryTree<T> arbol) {
            stack = new Stack<>();
            list = new ArrayList<>();
            if(arbol!=null){
                stack.push(arbol);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public List<BinaryTree<T>> next() {
            list.clear();
            while(!stack.isEmpty()){
                list.add(stack.pop());
            }
            list.forEach(a ->{
                if(a.getLeft()!=null) stack.push(a.getLeft());
                if(a.getRight()!=null) stack.push(a.getRight());
            });
            return list;
        }
    }
    
    public void print() {
        if (isEmpty()) return;
        System.out.print(root.toString() + " ");
        if (root.hasLeft()) getLeft().print();
        if (root.hasRight()) getRight().print();
    }
    
    public static void findIntersection(BinaryTree<Integer> t1, BinaryTree<Integer> t2) {
        if (t1.isEmpty() || t2.isEmpty()) return;
        int root = t1.getRoot().getContent() + t2.getRoot().getContent();
//        System.out.println(root);
//        System.out.println(" " + t1.getRoot().getContent());
//        System.out.println(" " + t2.getRoot().getContent());
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(new BinaryNode(root));
        BinaryTree.getTree(t1, t2, tree);
        
        tree.print();
        
    }
    
    public static void getTree(BinaryTree<Integer> t1, BinaryTree<Integer> t2, BinaryTree<Integer> tree) {
        int root;
        if (t1.getRoot().hasLeft() && t2.getRoot().hasLeft()) {
            System.out.println("t1: " + t1.getRoot().toString());
            System.out.println("t2: " + t2.getRoot().toString());
            root = t1.getLeft().getRoot().getContent() + t2.getLeft().getRoot().getContent();
            System.out.println("->" + root);
            System.out.println(" " + t1.getLeft().getRoot().getContent());
            System.out.println(" " + t2.getLeft().getRoot().getContent());
            System.out.println("Root: " + tree.getRoot());
            tree.getRoot().setLeft(new BinaryTree(root));
            System.out.println(root);
            BinaryTree.getTree(t1.getLeft(), t2.getLeft(), tree);
        }
        if (t1.getRoot().hasRight() && t2.getRoot().hasRight()) {            
            System.out.println("t1: " + t1.getRoot().toString());
            System.out.println("t2: " + t2.getRoot().toString());
            root = t1.getRight().getRoot().getContent() + t2.getRight().getRoot().getContent();
            System.out.println("->" + root);
            System.out.println(" " + t1.getRight().getRoot().getContent());
            System.out.println(" " + t2.getRight().getRoot().getContent());
            tree.getRoot().setRight(new BinaryTree(root));
            BinaryTree.getTree(t1.getRight(), t2.getRight(), tree);
        }

    }
    
    
    
    
//    public static void findIntersection(BinaryTree<Integer> t1, BinaryTree<Integer> t2) {
//        if (t1.isEmpty() || t2.isEmpty()) return;
//        Map<Integer, LinkedList<BinaryNode<Integer>>> map = new TreeMap<>();
//        LinkedList<BinaryNode<Integer>> list = new LinkedList<>();
//        list.add(t1.getRoot());
//        list.add(t2.getRoot());
//        map.put(0, list);
//        BinaryTree.getTree(t1, t2, 1, map);
////        map.forEach((k, v) -> {
////            System.out.print(v + " ");
////        });
////        
//        System.out.println(map.toString());
//        
//    }
//    
//    public static void getTree(BinaryTree<Integer> t1, BinaryTree<Integer> t2, int level, Map<Integer, LinkedList<BinaryNode<Integer>>> map) {
//        LinkedList<BinaryNode<Integer>> tmp = new LinkedList<>();
//        if (t1.getRoot().hasLeft() && t2.getRoot().hasLeft()) {
//            if (map.containsKey(level)) {
//                map.get(level).add(t1.getLeft().getRoot());
//                map.get(level).add(t2.getLeft().getRoot());
//            }
//            else  {
//                tmp.add(t1.getLeft().getRoot());
//                tmp.add(t2.getLeft().getRoot());
//                map.put(level, tmp);
//            }
//            
//            BinaryTree.getTree(t1.getLeft(), t2.getLeft(), level + 1, map);
//        }
//        if (t1.getRoot().hasRight() && t2.getRoot().hasRight()) {
//            if (map.containsKey(level)) {
//                map.get(level).add(t1.getRight().getRoot());
//                map.get(level).add(t2.getRight().getRoot());
//            }
//            else  {
//                tmp.add(t1.getRight().getRoot());
//                tmp.add(t2.getRight().getRoot());
//                map.put(level, tmp);
//            }
//            
//            BinaryTree.getTree(t1.getRight(), t2.getRight(), level + 1, map);
//        }
//
//    }
    
//    public static BinaryTree<Integer> findIntersection(BinaryTree<Integer> t1, BinaryTree<Integer> t2) {
//        if (t1.isEmpty() || t2.isEmpty()) return null;
//        
//        Stack<BinaryTree<Integer>> s1 = new Stack();
//        s1.push(t1);
//        Stack<BinaryTree<Integer>> s2 = new Stack();
//        s2.push(t2);
//        BinaryTree<Integer> result = new BinaryTree<>();
//        
//        System.out.println("Root1: " + t1.getRoot().getContent());
//        System.out.println("Root2: " + t2.getRoot().getContent());
//        
//        int root = t1.getRoot().getContent() + t2.getRoot().getContent();
//        result.setRoot(new BinaryNode(root));
//        
//        
//        while (!s1.isEmpty() && !s2.isEmpty()) {
//            BinaryTree<Integer> tmp1 = s1.pop();
//            BinaryTree<Integer> tmp2 = s2.pop();
//            System.out.println("  L 1 ->  " + tmp1.getRoot().getLeft().getRoot().toString());
//            System.out.println("  L 2 ->  " + tmp2.getRoot().getLeft().getRoot().toString());
//            System.out.println("  R 1 ->  " + tmp1.getRoot().getRight().getRoot().toString());
//            System.out.println("  R 2 ->  " + tmp2.getRoot().getRight().getRoot().toString());
//            
//            if (tmp1.getRoot().hasLeft() && tmp2.getRoot().hasLeft()) {
//                root = tmp1.getLeft().getRoot().getContent() + tmp2.getLeft().getRoot().getContent();
//                System.out.println(root);
//                result.setLeft(new BinaryTree(root));
//                s1.push(tmp1.getLeft());
//                s2.push(tmp2.getLeft());
//                
//                System.out.println("L 1 ->  " + tmp1.getRoot().getLeft().getRoot().toString());
//                System.out.println("L 2 ->  " + tmp2.getRoot().getLeft().getRoot().toString());
//            }
//            if (tmp1.getRoot().hasRight() && tmp2.getRoot().hasRight() && tmp1.getRight().getRoot().equals(tmp2.getRight().getRoot())) {
//                root = tmp1.getRight().getRoot().getContent() + tmp2.getRight().getRoot().getContent();
//                System.out.println(root);
//                result.setRight(new BinaryTree(root));
//                s1.push(tmp1.getRight());
//                s2.push(tmp2.getRight());
//                
//                System.out.println("R 1 ->  " + tmp1.getRoot().getRight().getRoot().toString());
//                System.out.println("R 2 ->  " + tmp2.getRoot().getRight().getRoot().toString());
//            }
//            
//        }
//        return result;
//    }
//    
    
    
    public void recursivepreOrden(BinaryNode<T> root) {

        if (root != null) {
            System.out.print(root.getContent() + " ");
            if (root.getLeft() != null) {
                root.getLeft().recursivepreOrden();
            }
            if (root.getRight() != null) {
                root.getRight().recursivepreOrden();
            }

            // res = res + root.getLeft().preOrden();
            //res = res + root.getRight().preOrden();
        }

    }
    public void recursivepreOrden() {
        recursivepreOrden(this.root);

    }

    public void recursiveInOrden(BinaryNode<T> root) {

        if (root != null) {

            if (root.getLeft() != null) {
                root.getLeft().recursiveInOrden();
            }
            System.out.print(root.getContent() + " ");
            if (root.getRight() != null) {
                root.getRight().recursiveInOrden();
            }
        }
    }

    public void recursiveInOrden() {
        recursiveInOrden(this.root);

    }
}
