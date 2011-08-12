package com.QRoid.activity.views;

import java.net.URLEncoder;

import com.QRoid.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SMSViewActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_view);
	}

	public void showQRSmsImage(View view)
	{
		EditText editPhone = (EditText) findViewById(R.id.phone_number_entry);
		String phoneNumber = editPhone.getText().toString();
		EditText editMessage = (EditText) findViewById(R.id.message_entry);
		String smsMessage = editMessage.getText().toString();

		if(phoneNumber.length() == 10 && editMessage.length() > 0)
		{
			Intent in = new Intent(SMSViewActivity.this,QRImageView.class);
			String url ="http://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=smsto%3A"+URLEncoder.encode(phoneNumber)+"%3A"+URLEncoder.encode(smsMessage);
			in.putExtra("url", url);
			startActivity(in);
			finish();
		}else
		{
			Toast.makeText(this, "Please Enter 10 digit Phone Number or Message.",
					Toast.LENGTH_LONG).show();
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
