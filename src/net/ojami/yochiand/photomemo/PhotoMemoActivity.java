package net.ojami.yochiand.photomemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

// メイン画面(Activity)
public class PhotoMemoActivity extends Activity {
    // 描画対象のViewを保持するメンバ変数
    private PhotoMemoView mView;

    // 画面(Activity)が生成されるときの処理
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 描画対象のViewを生成し、
        mView = new PhotoMemoView(this);
        // 描画対象のViewを画面に表示する
        setContentView(mView);
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
                Toast.makeText(this, R.string.message_no_function, Toast.LENGTH_SHORT).show();
                break;
            // メニュー「線の色」の処理
            case R.id.menu_paintcolor:
                // まだ処理を実装していない旨のメッセージを表示する
                Toast.makeText(this, R.string.message_no_function, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
