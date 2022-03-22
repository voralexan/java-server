package gomoku.server;

import java.util.HashMap;
import java.util.Map;

import gomoku.common.Move;
import gomoku.common.Position;

public class Gomoku {

    private boolean gameOver;
    private boolean colorToMove; // false - black, true - white
    private Position lastPosition;
    private Map<Position, Boolean> goban = new HashMap<>();
    private int numOfPieces = 5;

    public Gomoku() {
        addBlack(0, 0);
    }

    public Gomoku(int numOfPieces) {
        this();
        this.numOfPieces = numOfPieces;
    }

    public boolean addWhite(int x, int y) {
        return move(x, y, true);
    }

    public boolean addBlack(int x, int y) {
        return move(x, y, false);
    }

    protected boolean move(int x, int y, boolean color) {
        Position position = new Position(x, y);
        if (colorToMove == color && goban.get(position) == null) {
            goban.put(position, color);
            colorToMove = !colorToMove;
            this.lastPosition = position;
        }
        return gameOver = isWinner(position, color);
    }

    protected boolean horizontal(Position p, boolean color) {
        Position position = new Position(p.getX() - numOfPieces + 1, p.getY());
        int count = 0;
        for (int i = 0; i < numOfPieces * 2 - 1; i++) {
            if (goban.get(position) != null && goban.get(position).equals(color)) {
                count++;
                if (count == numOfPieces) {
                    break;
                }
            } else {
                count = 0;
            }
            position.setX(position.getX() + 1);
        }
        return count == numOfPieces;
    }

    protected boolean vertical(Position p, boolean color) {
        Position position = new Position(p.getX(), p.getY() - numOfPieces + 1);
        int count = 0;
        for (int i = 0; i < numOfPieces * 2 - 1; i++) {
            if (goban.get(position) != null && goban.get(position).equals(color)) {
                count++;
                if (count == numOfPieces) {
                    break;
                }
            } else {
                count = 0;
            }
            position.setY(position.getY() + 1);
        }
        return count == numOfPieces;
    }

    protected boolean diagonal(Position p, boolean color) {
        Position position1 = new Position(p.getX() - numOfPieces + 1, p.getY() - numOfPieces + 1);
        int count1 = 0;
        Position position2 = new Position(p.getX() + numOfPieces - 1, p.getY() - numOfPieces + 1);
        int count2 = 0;
        for (int i = 0; i < numOfPieces * 2 - 1; i++) {
            if (goban.get(position1) != null && goban.get(position1).equals(color)) {
                count1++;
                if (count1 == numOfPieces) {
                    break;
                }
            } else {
                count1 = 0;
            }
            position1.setX(position1.getX() + 1);
            position1.setY(position1.getY() + 1);
            if (goban.get(position2) != null && goban.get(position2).equals(color)) {
                count2++;
                if (count2 == 5) {
                    break;
                }
            } else {
                count2 = 0;
            }
            position2.setX(position2.getX() - 1);
            position2.setY(position2.getY() + 1);
        }
        return count1 == numOfPieces || count2 == numOfPieces;
    }

    protected boolean isWinner(Position p, boolean color) {
        return diagonal(p, color) || horizontal(p, color) || vertical(p, color);
    }

    protected Boolean getColor(int x, int y) {
        return goban.get(new Position(x, y));
    }

    public Move getLastMove() {
        return new Move(lastPosition, !colorToMove, gameOver);
    }
}
