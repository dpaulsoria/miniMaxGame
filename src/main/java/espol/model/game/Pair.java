package espol.model.game;

public class Pair {
    int x; int y;
    public Pair(int x, int y) { this.x = x; this. y = y; }
    public boolean equals(Pair p) { return (x == p.x && y == p.y); }
    public String toString() { return x +", " + y; }
}
