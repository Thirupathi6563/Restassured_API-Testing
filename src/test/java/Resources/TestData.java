package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public AddPlace AddPlace(String name,String language,String address)
	{
		 AddPlace a=new AddPlace();
		a.setAccuracy(10);
		a.setAddress(address);
		a.setLanguage(language);
		a.setName(name);
		a.setPhone_number("9441426563");
		a.setWebsite("https://wwww.facebook.com");
		Location l=new Location();
		l.setLng(11.001);
		l.setLat(512.3456);
		a.setLocation(l);
	

		List<String> listdata=new ArrayList();
		listdata.add("shoe park");
		listdata.add("shoe");
		a.setTypes(listdata);
		return a;
		
	}
	
	public String deletePlacePayload(String placeid)
	{
		String body= "{\r\n  \"place_id\":\""+placeid+"\"\r\n}";
		return body;
		
}

}
