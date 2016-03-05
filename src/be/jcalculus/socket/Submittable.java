package be.jcalculus.socket;

public interface Submittable {

	default public String submit(String request) {
		return request.toUpperCase();
	}

}
