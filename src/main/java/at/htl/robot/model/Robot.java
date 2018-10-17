package at.htl.robot.model;

public class Robot {
    private int x;
    private int y;
    private Direction direction = Direction.SOUTH;
    private Mode mode = Mode.RESTRICT;

    //region Getter and Setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
    //endregion


    public void stepForward() {
        if (this.direction == Direction.NORTH) {
            if (y == 1) {

                if (mode == Mode.TELEPORT) {
                    y = 10;
                }
            } else {

                this.y--;
            }
        } else if (this.direction == Direction.EAST) {
            if (x == 10) {
                if (mode == Mode.TELEPORT) {
                    x = 1;
                }
            } else {
                this.x++;
            }
        } else if (this.direction == Direction.SOUTH) {
            if (y == 10) {
                if (mode == Mode.TELEPORT) {
                    y = 1;
                }
            } else {
                this.y++;
            }
        } else if (this.direction == Direction.WEST) {
            if (x == 1) {
                if (mode == Mode.TELEPORT) {
                    x = 10;
                }
            } else {
                this.x--;
            }
        }
    }

    public void rotateLeft() {
        if (this.direction == Direction.NORTH) {
            this.direction = Direction.WEST;
        } else if (this.direction == Direction.WEST) {
            this.direction = Direction.SOUTH;
        } else if (this.direction == Direction.SOUTH) {
            this.direction = Direction.EAST;
        } else if (this.direction == Direction.EAST) {
            this.direction = Direction.NORTH;
        }
    }
}