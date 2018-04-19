package models;

/**
 * Stores the dimensions of the plateau that rovers are maneuvering around.
 */
public class PlateauSize {
    private int maxX;
    private int maxY;

    public PlateauSize(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean contains(RoverPosition position) {
        int x = position.getX();
        int y = position.getY();
        return (x >= 0) && (x <= maxX) && (y >= 0) && (y <= maxY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlateauSize)) return false;
        PlateauSize that = (PlateauSize) o;
        return maxX == that.maxX &&
                maxY == that.maxY;
    }

    @Override
    public String toString() {
        return String.format("%d %d", maxX, maxY);
    }
}
