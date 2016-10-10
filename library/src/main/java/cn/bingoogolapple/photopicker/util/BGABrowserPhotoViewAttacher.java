package cn.bingoogolapple.photopicker.util;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:16/7/15 上午10:35
 * 描述:
 */
public class BGABrowserPhotoViewAttacher extends PhotoViewAttacher {

    public BGABrowserPhotoViewAttacher(ImageView imageView) {
        super(imageView);
    }

    public void setUpdateBaseMatrix() {
        ImageView view = getImageView();
        if (view == null) return;
        update();
    }
}