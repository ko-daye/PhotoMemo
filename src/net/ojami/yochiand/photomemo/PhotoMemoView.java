package net.ojami.yochiand.photomemo;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

// 描画対象となるViewを定義するクラス
public class PhotoMemoView extends ImageView {
    // コンストラクタ
    public PhotoMemoView(Context context) {
        super(context);
        // 背景を白にする
        setBackgroundColor(Color.WHITE);
    }
}
