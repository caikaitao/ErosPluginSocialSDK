package com.sjhd.sdk.erospluginssocialsdk.achiever;

import android.app.Activity;


import com.sjhd.sdk.erospluginssocialsdk.model.ShareInfoBean;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Carry on 2018/3/6.
 */

public interface ShareAchiever {
    void shareAction(Activity activity, SHARE_MEDIA platform, ShareInfoBean shareBean,
                     UMShareListener
                         shareListener);
}
