package models;

/**
 * Describes the current coordinate position of a rover, including its cardinal direction.
 */
public class RoverPosition {
    private int x;
    private int y;
    private Direction direction;

    public RoverPosition(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void rotateLeft() {
        direction = direction.rotateLeft();
    }

    public void rotateRight() {
        direction = direction.rotateRight();
    }

    public void moveAtCurrentHeading() {
        x += direction.getMovementX();
        y += direction.getMovementY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoverPosition)) return false;
        RoverPosition that = (RoverPosition) o;
        return x == that.x &&
                y == that.y &&
                direction == that.direction;
    }

    @Override
    public String toString() {
        return String.format("%d %d %s", x, y, direction);
    }
}
