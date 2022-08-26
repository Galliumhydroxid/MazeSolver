import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LeftHandTraversal extends Algorithm {
    GifSequenceWriter gifWriter;
    @Override
    public List<Vector2> solve(Maze maze, BufferedImage image) throws IOException {
        System.out.println("Solving maze...");
        long startTime = System.currentTimeMillis();
        File output = new File("output.gif");
        output.createNewFile();
        gifWriter = new GifSequenceWriter(new FileImageOutputStream(output), image.getType(), 1, true);
        BufferedImage currentImage = image;
        Vector2 current = maze.getStart();
        Vector2 last = new Vector2(current.getX(), -1);
        List<Vector2> path = new ArrayList<>();
        while (!current.equals(maze.getEnd())) {
            Vector2 temp = current;
            current = getNextMove(maze.getNeighbors(current), current, last);
            path.add(current);
            last = temp;

            //image stuff
            currentImage.setRGB(current.getX(), current.getY(), ImageUtilities.BLUE);
            if (last != null) {
                currentImage.setRGB(last.getX(), last.getY(), ImageUtilities.GREEN);
            }
            gifWriter.writeToSequence(currentImage);
            ImageUtilities.saveImage(currentImage);
        }
        gifWriter.close();
        long endTime = System.currentTimeMillis();
        System.out.println("Solved maze in " + (endTime - startTime) + "ms");
        return path;
    }

    private Vector2 getNextMove(Set<Vector2> neighbors, Vector2 current, Vector2 last) {
        if (last.getX() == current.getX()) {
            if (last.getY() < current.getY()) {
                if (neighbors.contains(new Vector2(current.getX() + 1, current.getY()))) {
                    return new Vector2(current.getX() + 1, current.getY());
                }
                else if (neighbors.contains(new Vector2(current.getX(), current.getY() + 1))) {
                    return new Vector2(current.getX(), current.getY() + 1);
                }
                else if (neighbors.contains(new Vector2(current.getX() - 1, current.getY()))) {
                    return new Vector2(current.getX() - 1, current.getY());
                }
            } else {
                if (neighbors.contains(new Vector2(current.getX() - 1, current.getY()))) {
                    return new Vector2(current.getX() - 1, current.getY());
                }
                else if (neighbors.contains(new Vector2(current.getX(), current.getY() - 1))) {
                    return new Vector2(current.getX(), current.getY() - 1);
                }
                else if (neighbors.contains(new Vector2(current.getX() + 1, current.getY()))) {
                    return new Vector2(current.getX() + 1, current.getY());
                }
            }
        } else {
            if (last.getX() < current.getX()) {
                if (neighbors.contains(new Vector2(current.getX(), current.getY() - 1))) {
                    return new Vector2(current.getX(), current.getY() - 1);
                }
                else if (neighbors.contains(new Vector2(current.getX() + 1, current.getY()))) {
                    return new Vector2(current.getX() + 1, current.getY());
                }
                else if (neighbors.contains(new Vector2(current.getX(), current.getY() + 1))) {
                    return new Vector2(current.getX(), current.getY() + 1);
                }
            } else {
                if (neighbors.contains(new Vector2(current.getX(), current.getY() + 1))) {
                    return new Vector2(current.getX(), current.getY() + 1);
                }
                else if (neighbors.contains(new Vector2(current.getX() - 1, current.getY()))) {
                    return new Vector2(current.getX() - 1, current.getY());
                }
                else if (neighbors.contains(new Vector2(current.getX(), current.getY() - 1))) {
                    return new Vector2(current.getX(), current.getY() - 1);
                }
            }
        }
        return last;
    }
}
