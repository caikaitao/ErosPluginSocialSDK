package com.sjhd.sdk.erospluginssocialsdk.util;

import com.sjhd.sdk.erospluginssocialsdk.model.SharePlatform;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Carry on 2018/3/5.
 */

public class ShareUtil {

    public static SHARE_MEDIA getShareMedia(String platform) {
        switch (platform) {
            case SharePlatform.P_WECHATSESSION:
                return SHARE_MEDIA.WEIXIN;
            case SharePlatform.P_WECHATTIMELINE:
                return SHARE_MEDIA.WEIXIN_CIRCLE;
            case SharePlatform.P_FACEBOOK:
                return SHARE_MEDIA.FACEBOOK;

        }
        return null;
    }

}
