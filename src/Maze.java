import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Maze {
    private NodeType[][] maze;
    private Vector2 start;

    public Vector2 getStart() {
        return start;
    }

    public Set<Vector2> getNeighbors(Vector2 current) {
        Set<Vector2> neighbors = new HashSet<>();
        if (current.getX() > 0 && maze[current.getY()][current.getX() - 1] == NodeType.PATH) {
            neighbors.add(new Vector2(current.getX() - 1, current.getY()));
        }
        if (current.getX() < maze[0].length - 1 && maze[current.getY()][current.getX() + 1] == NodeType.PATH) {
            neighbors.add(new Vector2(current.getX() + 1, current.getY()));
        }
        if (current.getY() > 0 && maze[current.getY() - 1][current.getX()] == NodeType.PATH) {
            neighbors.add(new Vector2(current.getX(), current.getY() - 1));
        }
        if (current.getY() < maze.length - 1 && maze[current.getY() + 1][current.getX()] == NodeType.PATH) {
            neighbors.add(new Vector2(current.getX(), current.getY() + 1));
        }
        return neighbors;
    }

    public Vector2 getEnd() {
        return end;
    }

    private Vector2 end;
    public Maze(NodeType[][] input) {
        maze = input;
        determineStartAndEnd();
    }

    public Maze(BufferedImage image) {
        maze = ImageUtilities.convert(image);
        determineStartAndEnd();
    }

    public Maze(String path) throws IOException {
        this(ImageUtilities.loadImage(path));
    }

    private void determineStartAndEnd() {
        for (int x = 0; x < maze[0].length; x++) {
            if (maze[0][x] == NodeType.PATH) {
                start = new Vector2(x, 0);
                break;
            }
        }
        for (int x = 0; x < maze[0].length; x++) {
            if (maze[maze.length - 1][x] == NodeType.PATH) {
                end = new Vector2(x, maze[0].length - 1);
                break;
            }
        }
    }
}
