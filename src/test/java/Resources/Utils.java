package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification request;
	 public static ResponseSpecification response;
	
	public RequestSpecification RequestSpecification() throws IOException
	{
		if(request==null)  //If we don't put static then it will treat like a null while execute the 2nd testcase then it will print the only first testcase log
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		 request=new RequestSpecBuilder().setBaseUri(Utils.getGlobalValues("baseURI")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				 .setContentType(ContentType.JSON).build();
		 return request;
		}
		return request;
	}
	public  ResponseSpecification responseSpecification()
	{
		response=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return response;
	}

	public static String getGlobalValues(String key) throws IOException
	{
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\thiru\\EndToEndAPITesting\\src\\test\\java\\Resources\\global.properties");
		p.load(fis);
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response resp,String key)
	{
		String responseData=resp.asString();
		//System.out.println(responseData);
		JsonPath js=new JsonPath(responseData);
		return js.get(key);
	}
}

