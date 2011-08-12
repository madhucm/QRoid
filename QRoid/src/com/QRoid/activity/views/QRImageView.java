package com.QRoid.activity.views;


import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.QRoid.main.BuildMainPage;
import com.QRoid.main.R;

public class QRImageView extends Activity{
	
	String qrURL = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrview);
		Bundle bundle = getIntent().getExtras();

		if(bundle == null)
			return;

		qrURL = bundle.getString("url");
		new QRImageLoader().execute(qrURL);

	}
	
	class QRImageLoader extends AsyncTask<String, Void, Void>{
		ImageView imageView = (ImageView) findViewById(R.id.QRImage);;
		Bitmap qrBitmap = null;
		private ProgressDialog Dialog = new ProgressDialog(QRImageView.this);

		protected void onPreExecute() {
			Dialog.setMessage("Processing QRImage...");
			Dialog.show();
		}

		@Override
		protected Void doInBackground(String... arg0) {
			try {
				URL imageURL = new URL(arg0[0]);
				qrBitmap = BitmapFactory.decodeStream(imageURL.openStream());

			} catch (Exception e) {
				Log.d("QRDisplay", e.getMessage());
			}
			return null;
		}

		protected void onPostExecute(Void unused)
		{
			 Dialog.dismiss();
			imageView.setImageBitmap(qrBitmap);

		}
	}
	
	public void goHome(View view)
	{
		Intent in = new Intent(QRImageView.this,BuildMainPage.class);
		startActivity(in);
		finish(); //Close Image View Activity.
	}

	@Override
	public void finish()
	{
		super.finish();
	}
}
