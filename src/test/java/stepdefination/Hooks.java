package stepdefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException
	{
		//Execute this code when ever your place id is null because with out AddPlace API you run Delete place API then you will get the place id is null
		//if you use this will get the place id with out using Addplace API
		StepDefination st=new StepDefination();
		if(st.ResponsePlaceId==null)
		{
		st.add_place_payload_with("Yeshwanth", "hindi", "yemulokata");
		st.user_calls_api_with_post_http_request("AddPlaceAPI", "POST");
		st.verify_place_id_created_in_the_maps_to_using("Yeshwanth", "GetPlaceAPI");
		}
	}

}
