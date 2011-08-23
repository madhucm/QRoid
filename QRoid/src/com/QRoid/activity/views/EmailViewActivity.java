package com.QRoid.activity.views;

import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.QRoid.main.R;

public class EmailViewActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_view);
	}

	public void showQRemailImage(View view)
	{
		EditText editText  = (EditText)findViewById(R.id.email_view_entry);
		String text = editText.getText().toString();

		if(text.length() > 0)
		{
			Intent in = new Intent(EmailViewActivity.this,QRImageView.class);
			String url = "&chl=mailto%3A%2F%2F"
				+ URLEncoder.encode(text);
			in.putExtra("url", url);
			startActivity(in);
			finish();
		}else
		{
			Toast.makeText(this, "Please Enter Email.",
					Toast.LENGTH_SHORT).show();
		}		

	}

	public void goHome(View view)
	{
		finish();
	}

	@Override
	public void finish()
	{
		super.finish();
	}

}
