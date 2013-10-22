package net.ojami.yochiand.photomemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

// メイン画面(Activity)
public class PhotoMemoActivity extends Activity {
	// 描画対象のViewを保持するメンバ変数
	private PhotoMemoView mView;
	
	Uri mImageUri;
	String mFilePath = null;

	// 画面(Activity)が生成されるときの処理
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 描画対象のViewを生成し、
		mView = new PhotoMemoView(this);
		// 描画対象のViewを画面に表示する
		setContentView(mView);

		// TODO 撮影した写真を取り込む
	}

	// 端末のメニューボタンが押されたときの処理
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// res/menu/menu.xmlを読み込み、メニューを生成する
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// メニューの項目が選ばれたときの処理
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 選ばれたメニューの種類(ID)に応じて、処理する
		switch (item.getItemId()) {
		// メニュー「線の太さ」の処理
		case R.id.menu_paintwidth:
			// まだ処理を実装していない旨のメッセージを表示する
			Toast.makeText(this, R.string.message_no_function,
					Toast.LENGTH_SHORT).show();
			break;
		// メニュー「線の色」の処理
		case R.id.menu_paintcolor:
			// まだ処理を実装していない旨のメッセージを表示する
			Toast.makeText(this, R.string.message_no_function,
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_camapp:
			// まだ処理を実装していない旨のメッセージを表示する
//			Toast.makeText(this, R.string.message_no_function,
//					Toast.LENGTH_SHORT).show();

			// TODO カメラアプリを暗黙的インテントで呼び出す
			Intent intent = new Intent();
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			
//			intent.putExtra(MediaStore.EXTRA_OUTPUT, "/data/data/net.ojami.yochiand.photomemo/" + Calendar.getInstance().getTimeInMillis());
			
//			String filename = System.currentTimeMillis() + ".jpg";
//			
//		    ContentValues values = new ContentValues();
//		    values.put(MediaStore.Images.Media.TITLE, filename);
//		    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//		    mImageUri = getContentResolver().insert(
//		            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//		    Log.i("URI", mImageUri.toString());
//		    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
		    
		    // SDカードに保存
		    String mDirectory = Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName();
		    String mFilename = "pht" + System.currentTimeMillis() + ".jpg";
		    mFilePath = mDirectory + "/" + mFilename;
		    
		    Log.i("FilePath", mFilePath);
			
		    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mFilePath)));
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		Log.i("CODE", String.valueOf(requestCode));
		Log.i("CODE", String.valueOf(resultCode));
		if (requestCode == 0) {
			if (resultCode == Activity.RESULT_OK)  {
				createContent();
				setMView();
			} else {
//			    getContentResolver().delete(mImageUri, null, null);
			}
		}
	}
	private void createContent() {
		
	    ContentValues values = new ContentValues();
	    values.put(MediaStore.Images.Media.TITLE, mFilePath);
	    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
	    mImageUri = getContentResolver().insert(
	            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	    Log.i("URI", mImageUri.toString());
		
	}
	
	private void setMView() {
		
		if (mImageUri == null) {
			return;
		}
		
		Bitmap bitmap = null;
		
		BitmapFactory.Options opt = new BitmapFactory.Options();
//		opt.inSampleSize = 10;
		
		InputStream is = null;
		
		try {
			is = getContentResolver().openInputStream(mImageUri);
			
			Log.i("URI", is.toString());
			bitmap = BitmapFactory.decodeStream(is, null, opt);
			mView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		if (mImageUri != null) {
			outState.putString("mImageUri", mImageUri.toString());
		}
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (mImageUri != null) {
			mImageUri = Uri.parse((String) savedInstanceState.get("mImageUri"));
			setMView();
		}
	}
}
