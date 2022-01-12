package espol.model.tda;

import java.util.List;

public class Tree<T> {

    private NodeTree<T> root;
    public Tree() { this.root = new NodeTree<>(); }

    public Tree(T content) { this.root = new NodeTree<>(content); }
    public NodeTree<T> getRoot() { return root; }
    public void setRoot(NodeTree<T> root) { this.root = root; }
    public Tree<T> getChild(int i) { return root.getChild(i); }
    public Tree<T> getLastChild() { return root.getChild(root.childrenSize() - 1); }
    public void addChildren(List<Tree<T>> newChildrens) {
        for(Tree<T> t:newChildrens) {
            addChild(t);
        }
    }
    public void addChild(Tree<T> newChild) { root.addChild(newChild); }
    public boolean isEmpty() { return this.root == null; }
    public boolean isLeaf() { return this.root.isLeaf(); }
    
    public void printTree(){
    
    }
    
}