package barber.common;

public class Offer {
	public String offerId;
	public String name;
	public String time;
	public String cost;

	public Offer() {
		
	}

	public Offer(String offerId, String name, String time, String cost) {
		this.offerId = offerId;
		this.name = name;
		this.time = time;
		this.cost = cost;
	}

	public String getOfferId() {
		return offerId;
	}

	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}

	public String getCost() {
		return cost;
	}

	public void setChar(String condition, String mileage) {
		this.time = condition;
	}

	@Override
	public String toString() {
		String str = offerId + "\n" + name + "\n" + time + "\n" + cost + "\n";
		return str;
	}
}
