package barber.server;

import java.util.ArrayList;
import java.util.List;

import barber.common.Offer;
import barber.common.Barber;
import grpc.EchoServiceGrpc;
import grpc.ReqAllOffers;
import grpc.ResAllOffers;
import grpc.NewOffer;
import grpc.ResDelOffer;
import grpc.ReqDelOffer;
import grpc.ReqFreeTime;
import grpc.ResFreeTime;
import grpc.ResDelEntry;
import grpc.ResAddEntry;
import grpc.ReqDelEntry;
import grpc.ReqAddEntry;

import io.grpc.ServerBuilder;

public class Server extends EchoServiceGrpc.EchoServiceImplBase {
	private Barber barber = new Barber();
	private List<Offer> cars = new ArrayList<>();
	@Override
	public void allOffers(ReqAllOffers request, io.grpc.stub.StreamObserver<ResAllOffers> responseObserver) {
		String res = barber.getAllOffers();
		ResAllOffers response = ResAllOffers.newBuilder().setMessage(res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void reqNewOffer(NewOffer request, io.grpc.stub.StreamObserver<ResAllOffers> responseObserver) {
		String[] s = new String[3];
		s[0] = request.getName();
		s[1] = request.getTime();
		s[2] = request.getCost();
		String res = "Created a new offer with ID - " + barber.addOffer(s[0], s[1], s[2]);
		ResAllOffers response = ResAllOffers.newBuilder().setMessage(res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}


	@Override
	public void delOffer(ReqDelOffer request, io.grpc.stub.StreamObserver<ResDelOffer> responseObserver) {
		String[] s = new String[3];
		s[0] = request.getId();
		String res = barber.delOffer(s[0]);
		ResDelOffer response = ResDelOffer.newBuilder().setMessage(res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void getFreeTime(ReqFreeTime request, io.grpc.stub.StreamObserver<ResFreeTime> responseObserver) {
		String res = barber.getFreeTime();
		ResFreeTime response = ResFreeTime.newBuilder().setMessage(res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void addEntry(ReqAddEntry request, io.grpc.stub.StreamObserver<ResAddEntry> responseObserver) {
		String[] s = new String[3];
		s[0] = request.getTime();
		s[1] = request.getId();
		String res = barber.addEntry(s[0], s[1]);
		ResAddEntry response = ResAddEntry.newBuilder().setMessage(res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void delEntry(ReqDelEntry request, io.grpc.stub.StreamObserver<ResDelEntry> responseObserver) {
		String s = new String();
		s = request.getId();
		String res = barber.delEntry(s);
		ResDelEntry response = ResDelEntry.newBuilder().setMessage(res).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	public static void main(String[] args) throws Exception {
		io.grpc.Server server = ServerBuilder.forPort(8080).addService(new Server()).build();
		server.start();
		System.out.println("Server started");
		server.awaitTermination();
	}
}
