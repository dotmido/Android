package com.dotmido.hellowold;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser  {
	private String jsonString;
	private ArrayList<String> arrayList;
	
	public ArrayList<String> ParseIt(String _JsonString) throws JSONException
	{
		ArrayList<String> stringParsed = new ArrayList<String>();
		JSONArray jsonArray = new JSONArray(_JsonString);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jObject = (JSONObject) jsonArray.get(i);
				stringParsed.add(jObject.getString("text"));
			}
			return stringParsed;
		
	}

	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString (String json)
	{
		jsonString = json;
	}
	public ArrayList<String> getArray()
	{
		return arrayList;
	}
	public void setArrayList (ArrayList<String> arrayList)
	{
		this.arrayList = arrayList;
	}
	
	

}
