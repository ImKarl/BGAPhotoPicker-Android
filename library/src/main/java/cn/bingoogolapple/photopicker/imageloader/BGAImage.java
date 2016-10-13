package cn.bingoogolapple.photopicker.imageloader;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import cn.bingoogolapple.photopicker.util.BGAPhotoPickerUtil;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:16/6/25 下午5:03
 * 描述:
 */
public class BGAImage {
    private static BGAImageLoader sImageLoader;

    public static enum ImageLoader {
        Glide,
        Picasso,
        UIL,
        XUtils;
    }

    private BGAImage() {
    }

    public static final void setImageLoader(BGAImageLoader imageLoader) {
        sImageLoader = imageLoader;
    }
    public static final void setImageLoader(ImageLoader imageLoader) {
        switch (imageLoader) {
            case Glide:
                sImageLoader = new BGAGlideImageLoader();
                break;
            case Picasso:
                sImageLoader = new BGAPicassoImageLoader();
                break;
            case UIL:
                sImageLoader = new BGAUILImageLoader();
                break;
            case XUtils:
                sImageLoader = new BGAXUtilsImageLoader();
                break;
        }
    }
    private static final BGAImageLoader getImageLoader() {
        if (sImageLoader == null) {
            synchronized (BGAImage.class) {
                if (sImageLoader == null) {
                    if (isClassExists("com.bumptech.glide.Glide")) {
                        sImageLoader = new BGAGlideImageLoader();
                    } else if (isClassExists("com.squareup.picasso.Picasso")) {
                        sImageLoader = new BGAPicassoImageLoader();
                    } else if (isClassExists("com.nostra13.universalimageloader.core.ImageLoader")) {
                        sImageLoader = new BGAUILImageLoader();
                    } else if (isClassExists("org.xutils.x")) {
                        sImageLoader = new BGAXUtilsImageLoader();
                    } else {
                        throw new RuntimeException("必须在你的build.gradle文件中配置「Glide、Picasso、universal-image-loader、XUtils3」中的某一个图片加载库的依赖");
                    }
                }
            }
        }
        return sImageLoader;
    }

    private static final boolean isClassExists(String classFullName) {
        try {
            Class.forName(classFullName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void displayImage(Activity activity, ImageView imageView, String path, @DrawableRes int loadingResId, @DrawableRes int failResId, int width, int height, final BGAImageLoader.DisplayDelegate delegate) {
        // 三星note3 图片尺寸过大，显示不了
        if (width > 1080) {
            width = 1080;
        }
        if (width > BGAPhotoPickerUtil.getScreenWidth(activity)*0.8) {
            width = (int) (BGAPhotoPickerUtil.getScreenWidth(activity)*0.8);
        }

        if (height > 1080) {
            height = 1080;
        }
        if (height > BGAPhotoPickerUtil.getScreenHeight(activity)*0.8) {
            height = (int) (BGAPhotoPickerUtil.getScreenHeight(activity)*0.8);
        }

        getImageLoader().displayImage(activity, imageView, path, loadingResId, failResId, width, height, delegate);
    }

    public static void downloadImage(Context context, String path, final BGAImageLoader.DownloadDelegate delegate) {
        getImageLoader().downloadImage(context, path, delegate);
    }

    public static void pause(Activity activity) {
        getImageLoader().pause(activity);
    }

    public static void resume(Activity activity) {
        getImageLoader().resume(activity);
    }
}
