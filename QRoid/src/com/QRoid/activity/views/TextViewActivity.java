package com.QRoid.activity.views;
import java.net.URLEncoder;

import com.QRoid.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TextViewActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_view);
	}
	
	public void showQRtextImage(View view)
	{
		EditText editText  = (EditText)findViewById(R.id.text_view_entry);
		String text = editText.getText().toString();
		
		if(text.length() > 0)
		{
			Intent in = new Intent(TextViewActivity.this,QRImageView.class);
			String url = "http://chart.apis.google.com/chart?cht=qr&chs=350x350&chl="
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
