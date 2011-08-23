package com.QRoid.main;


import java.util.Vector;
import android.util.Log;

import com.QRoid.activity.views.ContactViewActivity;
import com.QRoid.activity.views.EmailViewActivity;
import com.QRoid.activity.views.SMSViewActivity;
import com.QRoid.activity.views.TextViewActivity;
import com.QRoid.activity.views.URLViewActivity;
import com.QRoid.common.QRConstants;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BuildMainPage extends ListActivity 
{
	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;
	Intent intent = null;
	static String[] title = null;
	static String[] detail = null;
	SharedPreferences imageSizePref = null;
	SharedPreferences.Editor prefsEditor = null;
	
	private Integer[] imgid = {
			R.drawable.text,R.drawable.url,R.drawable.sms,
			R.drawable.contact,R.drawable.email
	};

	//Show menu options only for main screen.
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.config_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle menu selection
		switch (item.getItemId())
		{
		case R.id.config_screen:
			showImageScreenDialog();			
			return true;
		
		case R.id.quit_menu:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showImageScreenDialog()
	{
		final CharSequence[] items = getResources().getStringArray(R.array.image_size_array);
		final CharSequence[] size = getResources().getStringArray(R.array.image_size_value);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Image Size");
		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	setImageSize(size[item].toString());		    	
		        Toast.makeText(getApplicationContext(), size[item], Toast.LENGTH_SHORT).show();
		        dialog.cancel();
		    }
		});
		/*builder.setCancelable(false);
	       builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               
	           }
	       })
	       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });*/
		AlertDialog alert = builder.create();
		alert.getListView().setItemChecked(1, true); // It didnot work :-(
		alert.show();
		
	}

	private void setImageSize(String imageSizeValue)
	{
		 prefsEditor.putString("imageSize", imageSizeValue);
		 prefsEditor.commit();	
		 Log.d(imageSizeValue, "Image Size Changed");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		title =  getResources().getStringArray(R.array.title);
		detail = getResources().getStringArray(R.array.detail);
		mInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		imageSizePref = this.getSharedPreferences("imagePref",MODE_WORLD_READABLE);
		prefsEditor = imageSizePref.edit();
		data = new Vector<RowData>();
		for(int i=0;i<title.length;i++){
			try {
				rd = new RowData(i,title[i],detail[i]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			data.add(rd);
		}
		CustomAdapter adapter = new CustomAdapter(this, R.layout.list,
				R.id.title, data);
		setListAdapter(adapter);
		getListView().setTextFilterEnabled(true);
	}

	public void onListItemClick(ListView parent, View v, int position,
			long id) {
		int listPosition = position +1;
		if(listPosition == 1)
		{
			intent = new Intent(BuildMainPage.this,TextViewActivity.class);
			startActivity(intent);
		}else if(listPosition == 2)
		{
			intent = new Intent(BuildMainPage.this,URLViewActivity.class);
			startActivity(intent);
		}else if(listPosition == 3)
		{
			intent = new Intent(BuildMainPage.this,SMSViewActivity.class);
			startActivity(intent);
		}else if(listPosition == 4)
		{
			intent = new Intent(BuildMainPage.this,ContactViewActivity.class);
			startActivity(intent);
		}else if(listPosition == 5)
		{
			intent = new Intent(BuildMainPage.this,EmailViewActivity.class);
			startActivity(intent);
		}

	}

	@Override
	public void finish()
	{
		super.finish();
	}

	private class RowData
	{
		protected int mId;
		protected String mTitle;
		protected String mDetail;
		RowData(int id,String title,String detail){
			mId=id;
			mTitle = title;
			mDetail=detail;
		}
		@Override
		public String toString() {
			return mId+" "+mTitle+" "+mDetail;
		}
	}

	private class CustomAdapter extends ArrayAdapter<RowData>
	{
		public CustomAdapter(Context context, int resource,
				int textViewResourceId, java.util.List<RowData> objects) {               
			super(context, resource, textViewResourceId, objects);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{   
			ViewHolder holder = null;
			TextView title = null;
			TextView detail = null;
			ImageView i11=null;
			RowData rowData= getItem(position);
			if(null == convertView){
				convertView = mInflater.inflate(R.layout.list, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			title = holder.gettitle();
			title.setText(rowData.mTitle);
			detail = holder.getdetail();
			detail.setText(rowData.mDetail);                                                     
			i11=holder.getImage();
			i11.setImageResource(imgid[rowData.mId]);
			return convertView;
		}

		private class ViewHolder 
		{
			private View mRow;
			private TextView title = null;
			private TextView detail = null;
			private ImageView i11=null; 
			public ViewHolder(View row) {
				mRow = row;
			}
			public TextView gettitle() {
				if(null == title){
					title = (TextView) mRow.findViewById(R.id.title);
				}
				return title;
			}     
			public TextView getdetail() {
				if(null == detail){
					detail = (TextView) mRow.findViewById(R.id.detail);
				}
				return detail;
			}
			public ImageView getImage() {
				if(null == i11){
					i11 = (ImageView) mRow.findViewById(R.id.img);
				}
				return i11;
			}
		}
	} 
}