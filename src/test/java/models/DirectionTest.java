package models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class DirectionTest {
    @Test
    public void startingDirection_rotateLeft_returnsExpectedDirection() {
        assertThat(Direction.SOUTH.rotateLeft()).isEqualTo(Direction.EAST);
    }

    @Test
    public void startingDirectionAtWrapAround_rotateLeft_returnsExpectedDirection() {
        assertThat(Direction.NORTH.rotateLeft()).isEqualTo(Direction.WEST);
    }

    @Test
    public void startingDirection_rotateRight_returnsExpectedDirection() {
        assertThat(Direction.SOUTH.rotateRight()).isEqualTo(Direction.WEST);
    }

    @Test
    public void startingDirectionAtWrapAround_rotateRight_returnsExpectedDirection() {
        assertThat(Direction.WEST.rotateRight()).isEqualTo(Direction.NORTH);
    }
}