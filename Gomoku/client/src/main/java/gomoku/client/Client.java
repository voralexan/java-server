package gomoku.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import gomoku.common.Game;
import gomoku.common.Move;
import gomoku.common.Position;

public class Client implements Runnable {

    private Game server;
    private boolean myColor;

    protected Client(String name, Game server) throws RemoteException {
        this.server = server;
        this.myColor = name.equalsIgnoreCase("white");

    }

    private Move getMove(Scanner scanner) {
        System.out.print("Your Move: ");
        String input = scanner.nextLine();
        String[] coords = input.split(",");
        Position position = new Position();
        position.setX(Integer.valueOf(coords[0]));
        position.setY(Integer.valueOf(coords[1]));
        Move move = new Move();
        move.setColor(myColor);
        move.setPosition(position);
        return move;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                Move lastMove = server.getLastMove();
                if (lastMove.isColor() != myColor) {
                    System.out.println("Last Move: " + lastMove.getPosition().getX() + "," + lastMove.getPosition().getY());
                    if (lastMove.isGameOver()) {
                        System.out.print("You lose. Try again!");
                        break;
                    }
                    if (server.move(getMove(scanner))) {
                        System.out.print("You won. Congratulations!");
                        break;
                    }
                }
                Thread.sleep(1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException {
        String serverURL = "rmi://localhost/RMIServer";
        Game server = (Game) Naming.lookup(serverURL);
        new Thread(new Client(args[0], server)).start();
    }
}
