import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public final class ImageUtilities {

    public static final String PATH = "src/maze.png";

    public static final int BLACK = 0xFF000000;
    public static final int WHITE = 0xFFFFFFFF;
    public static final int RED = 0xFFFF0000;
    public static final int GREEN = 0xFF00FF00;
    public static final int BLUE = 0xFF0000FF;

    private ImageUtilities() {
    }

    public static NodeType[][] convert(BufferedImage image) {
        System.out.println("Converting image to maze...");
        long time = System.currentTimeMillis();
        NodeType[][] data = new NodeType[image.getHeight()][image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                switch(image.getRGB(x, y)) {
                    case BLACK:
                        data[y][x] = NodeType.WALL;
                        break;
                    case WHITE:
                        data[y][x] = NodeType.PATH;
                        break;
                    default:
                        data[y][x] = NodeType.UNDEFINED;
                        break;
                }
            }
        }
        System.out.println("Converted image to maze in " + (System.currentTimeMillis() - time) + "ms");
        return data;
    }

    public static void invert(String path) throws IOException {
        System.out.println("Inverting image...");
        long time = System.currentTimeMillis();
        BufferedImage image = ImageUtilities.loadImageFromResources(path);
        image = ImageUtilities.invertColor(image);
        ImageUtilities.saveImage(image);
        System.out.println("Inverted image in " + (System.currentTimeMillis() - time) + "ms");
    }

    public static void saveImage(BufferedImage image) throws IOException {
        ImageIO.write(image, "png", new File(PATH));
    }

    public static BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(new File(path).toURI().toURL());
    }

    public static BufferedImage loadImageFromResources(String path) throws IOException {
        URL url = ImageUtilities.class.getResource("/resources/images/"+path);
        assert url != null;
        return ImageIO.read(url);
    }

    private static BufferedImage invertColor(BufferedImage image) {
        BufferedImage inverted = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int alpha = (rgb >> 24) & 0xFF;
                int newRgb = (alpha << 24) | (255 - red << 16) | (255 - green << 8) | (255 - blue);
                inverted.setRGB(x, y, newRgb);
            }
        }
        return inverted;
    }
}
