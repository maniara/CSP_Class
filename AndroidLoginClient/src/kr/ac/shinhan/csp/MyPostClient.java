package kr.ac.shinhan.csp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import kr.ac.shinhan.csp.entity.Result;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

public class MyPostClient extends AsyncTask<String, Void, String>{
	
	private String serviceRootURL = "http://shcspkdy.appspot.com/service/";
	
	private HttpURLConnection getConnection(String url)
	{
		
		URL serviceURL;
		HttpURLConnection conn = null;
		try {
			serviceURL = new URL(serviceRootURL + url);
			conn = (HttpURLConnection) serviceURL.openConnection();
			conn.setRequestProperty("Accept","application/json");
			conn.setRequestProperty("Content-Type","application/json");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	private void sendString(HttpURLConnection conn, String json)
	{
		try{
			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes());
			os.flush();
		}catch(Exception e)
		{e.printStackTrace();}
		
	}
	
	private String receiveString(HttpURLConnection conn)
	{
		String output = "";
		try{
			String s;
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while((s = br.readLine()) != null)
			{
				output = output + s;
			}
		}catch(Exception e){}
		
		return output;
		
	}

	@Override
	protected String doInBackground(String... params) {
		
		Log.d("check Json",params[1]);
		
		HttpURLConnection conn = getConnection(params[0]);
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sendString(conn,params[1]);
		String rcvJson = receiveString(conn);
		
		//Log.d("check receive",rcvJson);
		
		return rcvJson;
	}
}

