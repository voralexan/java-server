package gomoku.server;

import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import gomoku.common.Game;
import gomoku.common.Move;
import gomoku.common.Position;

public class Server extends UnicastRemoteObject implements Game {
    private Gomoku gomoku;

    protected Server() throws RemoteException {
        gomoku = new Gomoku();
    }

    public static void main(String args[]) throws RemoteException, MalformedURLException {
        Naming.rebind("RMIServer", new Server());
    }

    @Override
    public Move getLastMove() throws RemoteException {
        return gomoku.getLastMove();
    }

    @Override
    public boolean move(Move move) throws RemoteException {
        Position p = move.getPosition();
        return gomoku.move(p.getX(), p.getY(), move.isColor());
    }
}
