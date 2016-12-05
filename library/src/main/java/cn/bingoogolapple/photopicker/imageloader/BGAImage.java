/**
 * Copyright 2016 bingoogolapple
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    private BGAImage() {
    }

    public static final void setImageLoader(BGAImageLoader imageLoader) {
        sImageLoader = imageLoader;
    }
    private static final BGAImageLoader getImageLoader() {
        if (sImageLoader == null) {
            sImageLoader = new BGAGlideImageLoader();
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
