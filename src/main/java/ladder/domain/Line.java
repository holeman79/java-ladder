package ladder.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private final List<Direction> directions;

    private Line(final List<Direction> directions) {
        this.directions = directions;
    }

    public static Line initLine(final LadderWidth ladderWidth, final DirectionMakingStrategy directionMakingStrategy) {
        final List<Direction> directions = new ArrayList<>();
        Direction direction = directionMakingStrategy.generate();
        directions.add(direction);
        for (int i = 1; i < ladderWidth.getValue() - 1; i++) {
            direction = direction.next(directionMakingStrategy.generate());
            directions.add(direction);
        }
        directions.add(direction.last());
        return new Line(directions);
    }

    public Position move(final Position position) {
        return directions.get(position.getValue())
                .move(position);
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public LadderWidth getLadderWidth() {
        return new LadderWidth(directions.size());
    }
}