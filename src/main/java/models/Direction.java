package models;

/**
 * Enum describing the 4 cardinal compass directions a rover can be pointing.
 */
public enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private String symbol;

    Direction(String symbol) {
        this.symbol = symbol;
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
}
