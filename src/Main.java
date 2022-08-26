import java.io.IOException;

public class Main {
    public final static String WORKING_PATH = "src/maze.png";
    public static void main(String[] args) throws IOException {
        String path = args[0];
        ImageUtilities.invert(path);
        Maze maze = new Maze(ImageUtilities.loadImage(WORKING_PATH));
        LeftHandTraversal traversal = new LeftHandTraversal();
        traversal.solve(maze, ImageUtilities.loadImage(WORKING_PATH));
    }
}
