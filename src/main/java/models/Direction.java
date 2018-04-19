package models;

/**
 * Enum describing the 4 cardinal compass directions a rover can be pointing.
 * Contains logic for rotating directions, as well as the difference in both
 * x and y dimensions a rover should move when facing in this direction.
 */
public enum Direction {
    NORTH(0, "N", 0, 1),
    EAST(1, "E", 1, 0),
    SOUTH(2, "S", 0, -1),
    WEST(3, "W", -1, 0);

    private static Direction[] directions = values();

    private int index;
    private String symbol;
    private int movementX;
    private int movementY;

    Direction(int index, String symbol, int movementX, int movementY) {
        this.index = index;
        this.symbol = symbol;
        this.movementX = movementX;
        this.movementY = movementY;
    }

    public Direction rotateLeft() {
        return getDirectionAtIndex(index - 1);
    }

    public Direction rotateRight() {
        return getDirectionAtIndex(index + 1);
    }

    public int getMovementX() {
        return movementX;
    }

    public int getMovementY() {
        return movementY;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static Direction getDirectionForSymbol(String symbol) {
        for (Direction direction : values()) {
            if (direction.symbol.equals(symbol)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Symbol must be one of 'N', 'E', 'S', 'W'");
    }

    /**
     * Returns the direction with the given index mod 4, allowing us to wrap around the
     * ends of the list as we rotate directions.
     */
    private static Direction getDirectionAtIndex(int index) {
        // Math floorMod function is required as standard % operator can return negative results.
        return directions[Math.floorMod(index, directions.length)];
    }
}
