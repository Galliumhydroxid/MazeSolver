public class Vector2 {
    private int x;
    private int y;
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector2) {
            Vector2 otherVector = (Vector2) other;
            return otherVector.getX() == x && otherVector.getY() == y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * y;
    }
}
