package com.QRoid.activity.views;

import java.net.URLEncoder;

import com.QRoid.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ContactViewActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_view);
	}

	public void showContactQRImage(View view)
	{
		String name = ((EditText) findViewById(R.id.contactName)).getText().toString();
		String company = ((EditText) findViewById(R.id.contactCompany)).getText().toString();;
		String phone = ((EditText) findViewById(R.id.contactPhone)).getText().toString();;
		String email = ((EditText) findViewById(R.id.contactEmail)).getText().toString();;
		String address = ((EditText) findViewById(R.id.contactAddress)).getText().toString();;
		String website = ((EditText) findViewById(R.id.contactWebsite)).getText().toString();;

		Intent in = new Intent(ContactViewActivity.this,QRImageView.class);
		String url = "&chl=MECARD%3AN%3A"+URLEncoder.encode(name)+"%3BTEL%3A"+URLEncoder.encode(phone)+"%3BURL%3A"+URLEncoder.encode(website)+"%3BEMAIL%3A"+URLEncoder.encode(email)+"%3BADR%3A"+URLEncoder.encode(address);
		in.putExtra("url", url);
		startActivity(in);
		finish();
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
