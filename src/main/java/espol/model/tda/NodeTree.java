package espol.model.tda;

import java.util.ArrayList;
import java.util.List;

public class NodeTree<T> {

    private T content;
    private List<Tree<T>> childs;

    public NodeTree() { this(null, new ArrayList<Tree<T>>()); }
    public NodeTree(T content) { this(content, new ArrayList<Tree<T>>()); }
    public NodeTree(T content, List<Tree<T>> hijos) { this.content = content; this.childs = hijos; }
    public T getContent() { return content; }
    public void setContent(T content) { this.content = content; }
    public Tree<T> getChild(int i) { return childs.get(i); }
    public Tree<T> getLastChild() { return childs.get(childs.size()-1); }
    public void addChild(Tree<T> newChild) { this.childs.add(newChild); }
    public int childsSize(){ return childs.size(); }
    public boolean isLeaf(){ return childs.isEmpty(); }
}
