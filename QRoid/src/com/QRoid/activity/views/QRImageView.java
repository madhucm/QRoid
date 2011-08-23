package com.QRoid.activity.views;


import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.QRoid.common.QRConstants;
import com.QRoid.main.BuildMainPage;
import com.QRoid.main.R;

public class QRImageView extends Activity
{

	String qrURL = null;
	SharedPreferences imageSizePref = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrview);
		imageSizePref = this.getSharedPreferences("imagePref",MODE_WORLD_READABLE);
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
				String urlString = QRConstants.QR_URL+getImageSize()+arg0[0];
				URL imageURL = new URL(urlString);
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

	private String getImageSize()
	{
		String imageSize = imageSizePref.getString("imageSize", "350x350");
		return imageSize;
	}
}
