package kr.ac.shinhan.csp;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.google.gson.Gson;

import kr.ac.shinhan.csp.entity.Result;
import kr.ac.shinhan.csp.entity.UserAccount;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	
	private EditText loginAccountText;
	private EditText loginPasswordText;
	private EditText signupPasswordText;
	private EditText signupAccountText;
	private EditText signupNicknameText;
	private Button loginButton;
	private Button signupButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loginAccountText = (EditText) findViewById(R.id.accountText);
		loginPasswordText = (EditText) findViewById(R.id.passwordText);
		signupPasswordText = (EditText) findViewById(R.id.signUpPasswordText);
		signupAccountText = (EditText) findViewById(R.id.signUpAccountText);
		signupNicknameText = (EditText) findViewById(R.id.signUpNameText);
		loginButton = (Button) findViewById(R.id.loginButton);
		signupButton = (Button) findViewById(R.id.signUpButton);
		
		loginButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String account = loginAccountText.getText().toString();
				String password = loginPasswordText.getText().toString();
			}});
		
		signupButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				signUp();
			}});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void login()
	{

	}
	
	private void signUp()
	{
		String account = signupAccountText.getText().toString();
		String password = signupPasswordText.getText().toString();
		String nickName = signupNicknameText.getText().toString();
		
		UserAccount ua = new UserAccount(account,nickName,password);
		String json = new Gson().toJson(ua);
		
		AsyncTask<String, Void, String> result = new MyPostClient().execute("UserAccount", json);
		
		String resultString = "";
		try{
			resultString = result.get();
		}catch(Exception e){e.printStackTrace();}
			
		if(resultString.equals(""))
			Log.e("result", "not received");
		else 
			Log.e("result", resultString);

	}
}
