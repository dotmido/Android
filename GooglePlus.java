package com.dotmido.hellowold;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class GooglePlus {

	/*
	 * @Override protected String doInBackground(String... uri) { HttpClient
	 * httpClient = new DefaultHttpClient(); HttpResponse response; String
	 * responseString =null; try { response = httpClient.execute(new
	 * HttpGet(uri[0])); StatusLine statusLine = response.getStatusLine(); if
	 * (statusLine.getStatusCode() == HttpStatus.SC_OK) { ByteArrayOutputStream
	 * outStream = new ByteArrayOutputStream();
	 * response.getEntity().writeTo(outStream); responseString =
	 * outStream.toString(); outStream.close(); } else {
	 * response.getEntity().getContent().close(); throw new
	 * IOException(statusLine.getReasonPhrase()); }
	 * 
	 * } catch(ClientProtocolException ex) {
	 * 
	 * } catch(IOException ex) {
	 * 
	 * } // TODO Auto-generated method stub return responseString; }
	 * 
	 * @Override protected void onPostExecute(String result) { // TODO
	 * Auto-generated method stub super.onPostExecute(result); }
	 */
	public String getData(String uri) throws ClientProtocolException,
			IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(new HttpGet(uri));
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.getEntity().writeTo(out);
			String responseString = out.toString();
			out.close();
			return responseString;
			// ..more logic
		} else {
			// Closes the connection.
			response.getEntity().getContent().close();
			throw new IOException(statusLine.getReasonPhrase());

		}

	}

	public String httpPostRequest(String uri) {
		String resString = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(uri);
		try {
			HttpResponse res = client.execute(post);
			resString = res.toString();

		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return resString;
	}

	// build uri to pass it for the request method
	public String buildUri(String... args) {
		String finalUri = "https://www.googleapis.com/plus/v1";
		finalUri += "/"+args[0];
		return finalUri;
	}

	//request method - simple
	public String GetData_Simple(String uri) throws Exception {
		BufferedReader in = null;
		String Data = null;
		try {
			HttpClient client = new DefaultHttpClient();
			URI link = new URI(uri);
			HttpGet request = new HttpGet();
			request.setURI(link);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String l = "";
			String nl = System.getProperty("line.separator");
			while ((l = in.readLine()) != null) {
				sb.append(l + nl);

			}
			in.close();
			Data = sb.toString();
			return Data;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
					return Data;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return Data;

	}

}
