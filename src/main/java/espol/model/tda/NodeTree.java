package espol.model.tda;

import java.util.ArrayList;
import java.util.List;

public class NodeTree<T> {

    private T content;
    private List<Tree<T>> children;

    public NodeTree() { this(null, new ArrayList<Tree<T>>()); }
    public NodeTree(T content) { this(content, new ArrayList<Tree<T>>()); }
    public NodeTree(T content, List<Tree<T>> hijos) { this.content = content; this.children = hijos; }
    public T getContent() { return content; }
    public void setContent(T content) { this.content = content; }
    public List<Tree<T>> getChildren() { return children; }
    public Tree<T> getChild(int i) { return children.get(i); }
    public Tree<T> getLastChild() { return children.get(children.size()-1); }
    public void addChild(Tree<T> newChild) { this.children.add(newChild); }
    public int childrenSize(){ return children.size(); }
    public boolean isLeaf(){ return children.isEmpty(); }
}
