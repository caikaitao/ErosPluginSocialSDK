package com.sjhd.sdk.erospluginssocialsdk.module;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.weex.plugin.annotation.WeexModule;
import com.sjhd.sdk.erospluginssocialsdk.R;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

@WeexModule(name = "FackbookModule", lazyLoad = true)
public class FackbookModule extends WXModule {
    private static FackbookModule fackbookModule;
    private ProgressDialog mProgressDialog;
    private Context mContext;

    public static FackbookModule getInstance(){
        if (fackbookModule == null){
            synchronized (FackbookModule.class){
                if (fackbookModule == null){
                    fackbookModule = new FackbookModule();
                }
            }
        }

        return fackbookModule;
    }


    private FackbookModule(){
        init();
    }

    private void init() {
        if (mContext == null) {
            this.mContext = mWXSDKInstance.getContext();

        }
    }


    @JSMethod(uiThread = true)
    public void login(final JSCallback callback) {
        init();

        UMShareConfig config = new UMShareConfig();

        config.isNeedAuthOnGetUserInfo(true);
        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_SSO);
        UMShareAPI umShareAPI = UMShareAPI.get(mWXSDKInstance.getContext());

        umShareAPI.setShareConfig(config);
        umShareAPI.getPlatformInfo((Activity) mContext, SHARE_MEDIA.FACEBOOK, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //授权开始的回调
                mProgressDialog = new ProgressDialog(mContext);
                mProgressDialog.setMessage("请稍后...");
                mProgressDialog.show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> data) {
                //map 转 json
                String dataJson = "";
                if (data != null) {
                    dataJson = JSON.toJSONString(data);
                }
                callback.invoke(dataJson);
                mProgressDialog.dismiss();
                showToast("success");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                mProgressDialog.dismiss();
                showToast(throwable.getMessage());
                callback.invoke(null);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                showToast("be already canceled..");
                mProgressDialog.dismiss();

            }
        });
    }

    @JSMethod(uiThread = true)
    public void logout(final JSCallback callback) {
        init();
        UMShareAPI.get(mContext).deleteOauth((Activity) mContext, SHARE_MEDIA.FACEBOOK, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //授权开始的回调
                mProgressDialog = new ProgressDialog(mContext);
                mProgressDialog.setMessage("waiting...");
                mProgressDialog.show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                showToast("Success");
                callback.invoke(map);
                mProgressDialog.dismiss();

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                mProgressDialog.dismiss();
                showToast(throwable.getMessage());

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                showToast("be already canceled..");
                mProgressDialog.dismiss();
            }
        });
    }

    @JSMethod(uiThread = true)
    public void shareText(String text) {
        if (!isLogin()) {
            showToast("Please log in first!");
            return;
        }
        new ShareAction((Activity) mWXSDKInstance.getContext()).withText(text)
            .setPlatform(SHARE_MEDIA.FACEBOOK)
            .setCallback(new UMShareListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    mProgressDialog = new ProgressDialog(mWXSDKInstance.getContext());
                    SocializeUtils.safeShowDialog(mProgressDialog);
                }

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    showToast("Success");
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    showToast("fail");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    Toast.makeText((Activity) mWXSDKInstance.getContext(), "be already canceled..", Toast.LENGTH_LONG).show();
                }
            }).share();
    }

    @JSMethod(uiThread = true)
    public void shareImage(String image, String title, String desc) {
        if (!isLogin()) {
            showToast("Please log in first!");
            return;
        }
        UMImage imageUrl = new UMImage((Activity) mWXSDKInstance.getContext(), image);
        imageUrl.setTitle(title);
        imageUrl.setDescription(desc);
        new ShareAction((Activity) mWXSDKInstance.getContext()).withMedia(imageUrl)
            .setPlatform(SHARE_MEDIA.FACEBOOK)
            .setCallback(new UMShareListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    mProgressDialog = new ProgressDialog(mWXSDKInstance.getContext());
                    SocializeUtils.safeShowDialog(mProgressDialog);
                }

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    showToast("Success");
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    showToast("fail");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    Toast.makeText((Activity) mWXSDKInstance.getContext(), "be already canceled..", Toast.LENGTH_LONG).show();
                }
            }).share();
    }

    @JSMethod(uiThread = true)
    public void shareUrl(String url, String title, String desc) {
        if (!isLogin()) {
            showToast("Please log in first!");
            return;
        }
        UMWeb web = new UMWeb(url);
        web.setThumb(new UMImage((Activity) mWXSDKInstance.getContext(), R.drawable.thumb));
        web.setTitle(title);
        web.setDescription(desc);
        new ShareAction((Activity) mWXSDKInstance.getContext()).withMedia(web)
            .setPlatform(SHARE_MEDIA.FACEBOOK)
            .setCallback(new UMShareListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    mProgressDialog = new ProgressDialog(mWXSDKInstance.getContext());
                    SocializeUtils.safeShowDialog(mProgressDialog);
                }

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    showToast("Success");
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    showToast("fail");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    Toast.makeText((Activity) mWXSDKInstance.getContext(), "be already canceled..", Toast.LENGTH_LONG).show();
                }
            }).share();
    }

    @JSMethod(uiThread = true)
    public void shareVideo(String url, String title, String desc) {
        if (!isLogin()) {
            showToast("Please log in first!");
            return;
        }
        UMVideo video = new UMVideo(url);
        video.setTitle(title);
        video.setDescription(desc);
        new ShareAction((Activity) mWXSDKInstance.getContext()).withMedia(video)
            .setPlatform(SHARE_MEDIA.FACEBOOK)
            .setCallback(new UMShareListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    mProgressDialog = new ProgressDialog(mWXSDKInstance.getContext());
                    SocializeUtils.safeShowDialog(mProgressDialog);
                }

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    showToast("Success");
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    showToast("fail");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    Toast.makeText((Activity) mWXSDKInstance.getContext(), "be already canceled..", Toast.LENGTH_LONG).show();
                }
            }).share();
    }

    @JSMethod(uiThread = true)
    public void shareTextAndImage(String image, String text, String title, String desc) {
        if (!isLogin()) {
            showToast("Please log in first!");
            return;
        }
        UMImage imageUrl = new UMImage((Activity) mWXSDKInstance.getContext(), image);
        imageUrl.setTitle(title);
        imageUrl.setDescription(desc);
        new ShareAction((Activity) mWXSDKInstance.getContext())
            .withText(text)
            .withMedia(imageUrl)
            .setPlatform(SHARE_MEDIA.FACEBOOK)
            .setCallback(new UMShareListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    mProgressDialog = new ProgressDialog(mWXSDKInstance.getContext());
                    SocializeUtils.safeShowDialog(mProgressDialog);
                }

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    showToast("Success");
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    showToast("fail");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    SocializeUtils.safeCloseDialog(mProgressDialog);
                    Toast.makeText((Activity) mWXSDKInstance.getContext(), "be already canceled..", Toast.LENGTH_LONG).show();
                }
            }).share();
    }

//    private Context safeContext(Context context) {
//        if (!(context instanceof Activity)) {
//            context = RouterTracker.peekActivity();
//        }
//        return context;
//    }

    private void showToast(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    @JSMethod(uiThread = true)
    public boolean isLogin() {
        UMShareAPI umShareAPI = UMShareAPI.get(mWXSDKInstance.getContext());
        return umShareAPI.isAuthorize((Activity) mContext, SHARE_MEDIA.FACEBOOK);
    }

    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        UMShareAPI.get( mWXSDKInstance.getContext()).release();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get( mWXSDKInstance.getContext()).onActivityResult(requestCode, resultCode, data);
    }



}
