package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	
	@Before("@Deleteplace")
	public void addPlaceBeforeDelete() throws IOException
	{
		if(AddPlace.placeId==null) {
		AddPlace addPlace = new AddPlace();
		addPlace.add_place_playload_with("Hook Ravi", "Franch", "Asia");
		addPlace.user_call_with_http_request("AddPlaceAPI", "POST");
		addPlace.verify_place_id_created_map_to_using("Hook Ravi", "getPlaceAPI");
		}
	}
}
