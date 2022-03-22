package gomoku.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {
    Move getLastMove() throws RemoteException;

    boolean move(Move move) throws RemoteException;
}
