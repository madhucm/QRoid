package com.QRoid.activity.views;

import java.net.URLEncoder;

import com.QRoid.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class URLViewActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.url_view);
	}

	public void showQRurlImage(View view)
	{
		EditText editText  = (EditText)findViewById(R.id.url_view_entry);
		String text = editText.getText().toString();

		if(text.length() > 0)
		{
			Intent in = new Intent(URLViewActivity.this,QRImageView.class);
			String url = "&chl=http%3A%2F%2F"
				+ URLEncoder.encode(text);
			in.putExtra("url", url);
			startActivity(in);
			finish();
		}else
		{
			Toast.makeText(this, "Please Enter Text.",
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
