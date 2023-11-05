package com.lighting.apidemo;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.testng.Assert;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        baseURI="https://reqres.in";
        //Map<String,Object> body=new HashMap<String, Object>();
        JSONObject body=new JSONObject();
        body.put("name","morpheus");
        body.put("job", "leader");
        
        Response rsp=given().contentType(ContentType.JSON).body(body.toString()).log().all().post("/api/users").andReturn();
        int statuscode =rsp.getStatusCode();
        String statusline=rsp.getStatusLine();
       String header=rsp.header("Content-Type");
       Headers head=rsp.headers();
       for(Header item:head.asList())
       {
    	 System.out.println(item.getName());
    	 System.out.println(item.getValue());
       }
       String rspbody=rsp.getBody().asString();
       String data=(String)JsonPath.read(rspbody, "$.name");
       Assert.assertEquals(statuscode, 201,"statuslineassertionpass");
       
      
       
       }
}
