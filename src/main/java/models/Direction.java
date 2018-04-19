package models;

/**
 * Enum describing the 4 cardinal compass directions a rover can be pointing.
 */
public enum Direction {
    NORTH(0, "N"),
    EAST(1, "E"),
    SOUTH(2, "S"),
    WEST(3, "W");

    private static Direction[] directions = values();

    private int index;
    private String symbol;

    Direction(int index, String symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public Direction rotateLeft() {
        return getDirectionAtIndex(index - 1);
    }

    public Direction rotateRight() {
        return getDirectionAtIndex(index + 1);
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
