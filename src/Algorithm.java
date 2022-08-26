import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public abstract class Algorithm {
    public abstract List<Vector2> solve(Maze maze, BufferedImage image) throws IOException;
}
