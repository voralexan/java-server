package barber.client;

import java.util.Scanner;


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

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;


public class Client {
	public static void main(String[] args) {
		EchoServiceGrpc.EchoServiceBlockingStub client = createClient("localhost", 8080);
		System.out.println("Connected to server");
		Scanner console = new Scanner(System.in);
		String message;
		while (true) {
			System.out.println("Client - 1");
			System.out.println("Employee - 2");
			System.out.println("Exit - 0");
			message = console.nextLine();
			if (message.equals("0")) {
				break;
			} else if (message.equals("1")) {
				System.out.println("Get all offers - 1");
				System.out.println("Get free time - 2");
				System.out.println("Create entry - 3");
				System.out.println("Delete entry - 4");
				System.out.println("Exit - 0");
				while (true) {

					message = console.nextLine();
					if (message.equals("0")) {
						break;
					} else if (message.equals("1")) {
						ReqAllOffers request = ReqAllOffers.newBuilder().build();
						ResAllOffers response = client.allOffers(request);
						System.out.println(response.getMessage());
					} else if (message.equals("2")) {
						ReqFreeTime request = ReqFreeTime.newBuilder().build();
						ResFreeTime response = client.getFreeTime(request);
						System.out.println(response.getMessage());
					} else if (message.equals("3")) {
						String[] str = new String[2];
						System.out.println("Enter time:");
						str[0] = console.nextLine();
						System.out.println("Enter offer ID:");
						str[1] = console.nextLine();
						ReqAddEntry request = ReqAddEntry.newBuilder().setTime(str[0]).setId(str[1]).build();
						ResAddEntry response = client.addEntry(request);
						System.out.println(response.getMessage());
					} else if (message.equals("4")) {
						String str = new String();
						System.out.println("Enter entry ID to delete:");
						str = console.nextLine();
						ReqDelEntry request = ReqDelEntry.newBuilder().setId(str).build();
						ResDelEntry response = client.delEntry(request);
						System.out.println(response.getMessage());
					}
				}
			} else if (message.equals("2")) {
				System.out.println("Get all offers - 1");
				System.out.println("Add new offer - 2");
				System.out.println("Delete offer - 3");
				System.out.println("Exit - 0");
				while (true) {
					message = console.nextLine();
					if (message.equals("0")) {
						break;
					} else if (message.equals("1")) {
						ReqAllOffers request = ReqAllOffers.newBuilder().build();
						ResAllOffers response = client.allOffers(request);
						System.out.println(response.getMessage());
					} else if (message.equals("2")) {
						String[] str = new String[3];
						System.out.println("Enter name:");
						str[0] = console.nextLine();
						System.out.println("Enter time:");
						str[1] = console.nextLine();
						System.out.println("Enter cost:");
						str[2] = console.nextLine();
						NewOffer request = NewOffer.newBuilder().setName(str[0]).setTime(str[1]).setCost(str[2]).build();
						ResAllOffers response = client.reqNewOffer(request);
						System.out.println(response.getMessage());
					} else if (message.equals("3")) {
						String str = new String();
						System.out.println("Enter offer ID to delete:");
						str = console.nextLine();
						ReqDelOffer request = ReqDelOffer.newBuilder().setId(str).build();
						ResDelOffer response = client.delOffer(request);
						System.out.println(response.getMessage());
					}
				}
			}
		}
		console.close();
	}

	private static EchoServiceGrpc.EchoServiceBlockingStub createClient(String host, int port) {
		Channel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		return EchoServiceGrpc.newBlockingStub(channel);
	}
}
