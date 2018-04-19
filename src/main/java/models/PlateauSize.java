package models;

/**
 * Stores the width and height of the plateau that rovers are maneuvering around.
 */
public class PlateauSize {
    private int width;
    private int height;

    public PlateauSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean contains(RoverPosition position) {
        int x = position.getX();
        int y = position.getY();
        return (x >= 0) && (x <= width) && (y >= 0) && (y <= height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlateauSize)) return false;
        PlateauSize that = (PlateauSize) o;
        return width == that.width &&
                height == that.height;
    }

    @Override
    public String toString() {
        return String.format("%d %d", width, height);
    }
}
