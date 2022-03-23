package barber.common;

import com.google.protobuf.Int32Value;

import java.util.*;

public class Barber {
	private final Map<String,Integer> TIMES = new HashMap<>();

	List<Offer> offer_list = new ArrayList<>();
	List<Entry> entry_list = new ArrayList<>();
	public Barber() {
		TIMES.put("8:00",30);
		TIMES.put("9:00",60);
		TIMES.put("10:00",60);
		Offer offer1 = new Offer("1", "Hair",  "60", "100");
		Offer offer2 = new Offer("2", "Beard",  "30", "50");
		offer_list.add(offer1);
		offer_list.add(offer2);
	}

	public List<Offer> getOfferList() {
		return offer_list;
	}

	public Offer getOffer(String offerId) {
		return offer_list.get(Integer.parseInt(offerId)-1);
	}
	public String getAllOffers() {
		String res = "\n";
		for (Offer c : offer_list) {
			res += c.toString() + "\n";
		}
		return res;
	}

	public String addOffer(String name, String time, String cost) {
		Integer i = offer_list.size() + 1;
		Offer c = new Offer(i.toString(), name, time, cost);
		offer_list.add(c);
		return i.toString();
	}

	public String delOffer(String id) {
		try {
			offer_list.remove(Integer.valueOf(id)-1);
		}
		catch (IndexOutOfBoundsException | NumberFormatException e) {
			return "Offer with such id not found!";
		}
		return "Offer with id=" + id +" deleted";
	}
	public String getFreeTime() {
		return "Free times:"+TIMES.toString();
	}
	public String addEntry(String time, String id) {
		Integer i = entry_list.size() + 1;
		String offer_time = null;
		for (Offer c : offer_list) {
			if (c.offerId.equals(id)){
				offer_time = c.time;
			}
		}
		if (offer_time == null){
			return "Something went wrong";
		}
		for (String c : TIMES.keySet()) {
			if ( c.equals(time) && TIMES.get(c) >= Integer.parseInt(offer_time) ){
				Entry e = new Entry(i.toString(), time, String.valueOf(TIMES.get(c)));
				TIMES.remove(c);
				entry_list.add(e);
				return "Succesfully added entry with ID - " + i.toString();
			}
		}
		return "Something went wrong";
	}
	public String delEntry(String id) {
		try {
			for (Entry c: entry_list){
				if (Integer.parseInt(c.entry_id) == Integer.parseInt(id)){
					TIMES.put(c.time,Integer.parseInt(c.lenght));
					entry_list.remove(c);
					return "Entry with id=" + id +" deleted";
				}
			}

		}
		catch (IndexOutOfBoundsException | NumberFormatException e) {
			return "Entry with such id not found!";
		}
		return "Entry with such id not found!";
	}
}
