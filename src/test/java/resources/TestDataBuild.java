package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddMap;
import pojo.Location;

public class TestDataBuild {

	Location lac = new Location();
	AddMap m= new AddMap();
	public AddMap addPlaceData()
	{
		
	    // Write code here that turns the phrase above into concrete action
		
		lac.setLat(-38.383493);
		lac.setLng(33.38455);
		
		m.setAccuracy(50);
		m.setAddress("Test, 29 bridge");
		m.setLanguage("French-IN");
		m.setName("Ravi");
		m.setPhone_number("(+91)9971881915");
		m.setWebsite("https://rahulshettyacademy.com");
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe");
		myList.add("shop");
		m.setTypes(myList);
		m.setLocation(lac);
		
		return m;
	}
	
	public AddMap addPlaceDataWithParameter(String name, String language, String address)
	{
		lac.setLat(-38.383493);
		lac.setLng(33.38455);
		
		m.setAccuracy(50);
		m.setAddress(address);
		m.setLanguage(language);
		m.setName(name);
		m.setPhone_number("(+91)9971881915");
		m.setWebsite("https://rahulshettyacademy.com");
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe");
		myList.add("shop");
		m.setTypes(myList);
		m.setLocation(lac);
		
		return m;
	}
	
	public String deletePaylod(String placeId) 
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
