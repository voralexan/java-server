package gomoku.common;

import java.io.Serializable;

public class Move implements Serializable {

    private Position position;
    private boolean color;
    private boolean gameOver;

    public Move() {
    }

    public Move(Position position, boolean color) {
        this.position = position;
        this.color = color;
    }

    public Move(Position position, boolean color, boolean gameOver) {
        this(position, color);
        this.gameOver = gameOver;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
