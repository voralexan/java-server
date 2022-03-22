# Gomoku

#### Start RMI registry
start rmiregistry -J-Djava.rmi.server.codebase=file:common/target/common-0.0.1-SNAPSHOT.jar

#### Start the game server
java -cp "common/target/common-0.0.1-SNAPSHOT.jar;server/target/server-0.0.1-SNAPSHOT.jar" gomoku.server.Server

#### Start the clients
java -cp "common/target/common-0.0.1-SNAPSHOT.jar;client/target/client-0.0.1-SNAPSHOT.jar" gomoku.client.Client white

java -cp "common/target/common-0.0.1-SNAPSHOT.jar;client/target/client-0.0.1-SNAPSHOT.jar" gomoku.client.Client black