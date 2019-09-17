package com.sjhd.sdk.erospluginssocialsdk.module;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;



import com.alibaba.fastjson.JSON;
import com.alibaba.weex.plugin.annotation.WeexModule;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sjhd.sdk.erospluginssocialsdk.achiever.ShareImageAchiever;
import com.sjhd.sdk.erospluginssocialsdk.achiever.ShareMiniProgramAchiever;
import com.sjhd.sdk.erospluginssocialsdk.achiever.ShareMusicAchiever;
import com.sjhd.sdk.erospluginssocialsdk.achiever.ShareTextAchiever;
import com.sjhd.sdk.erospluginssocialsdk.achiever.ShareTextImageAchiever;
import com.sjhd.sdk.erospluginssocialsdk.achiever.ShareWebAchiever;
import com.sjhd.sdk.erospluginssocialsdk.model.ShareInfoBean;
import com.sjhd.sdk.erospluginssocialsdk.model.ShareMediaType;
import com.sjhd.sdk.erospluginssocialsdk.model.SharePlatform;
import com.sjhd.sdk.erospluginssocialsdk.model.UmengPlagformBean;
import com.sjhd.sdk.erospluginssocialsdk.util.ShareUtil;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

@WeexModule(name = "SJSocialShare", lazyLoad = true)
public class UmengModule extends WXModule {
    private Context mContext;
    private ProgressDialog dialog;
    private UMShareAPI umShareAPI;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 0x100;
    private static final int RC_GET_TOKEN = 9002;
    private OnGetLoginInfoListener mOnGetLoginInfoListener;

    @JSMethod
    public void initUM(String umengAppKey) {
        if (!TextUtils.isEmpty(umengAppKey)) {
            UMConfigure.init(mWXSDKInstance.getContext(), umengAppKey, "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
            UMConfigure.setLogEnabled(true);

        }
    }

    @JSMethod
    public void initWechat(UmengPlagformBean model) {
        PlatformConfig.setWeixin(model.getAppKey(), model.getAppSecret());

    }

    @JSMethod
    public void initFacebook(UmengPlagformBean model) {
        //在项目的app module 配置
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_SSO);
        umShareAPI = UMShareAPI.get(mWXSDKInstance.getContext());

        umShareAPI.setShareConfig(config);
    }

    @JSMethod
    public void initGoogle(String model) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(model)
                .requestId()
                .requestProfile()
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(mWXSDKInstance.getContext(), gso);
    }

    @JSMethod
    public void loginWithPlatformType(String platfrom, final JSCallback success, final JSCallback fail) {
        init();
        switch (platfrom) {
            case SharePlatform.P_WECHATSESSION:
            case SharePlatform.P_FACEBOOK:

                umShareAPI.getPlatformInfo((Activity) mContext, ShareUtil.getShareMedia(platfrom), new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        SocializeUtils.safeShowDialog(dialog);
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                        SocializeUtils.safeCloseDialog(dialog);
                        Toast.makeText(mContext, "success", Toast.LENGTH_LONG).show();
                        String dataJson = "";
                        if (data != null) {
                            dataJson = JSON.toJSONString(data);
                        }
                        success.invoke(dataJson);

                    }

                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        SocializeUtils.safeCloseDialog(dialog);
                        Toast.makeText(mContext, "fail：" + t.getMessage(), Toast.LENGTH_LONG).show();
                        fail.invoke(t.getMessage());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        SocializeUtils.safeCloseDialog(dialog);
                        Toast.makeText(mContext, "be already canceled..", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case SharePlatform.P_GOOGLE:
                if (mGoogleSignInClient == null) {
                  //  showToast("please Initialization google api.");
                    fail.invoke("please Initialization google api.");
                    return;
                }
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                ((Activity) (mWXSDKInstance.getContext())).startActivityForResult(signInIntent, RC_GET_TOKEN);

                setOnGetLoginInfoListener(new OnGetLoginInfoListener() {
                    @Override
                    public void setGoogleInfo(GoogleSignInAccount result) {

                        String str = JSON.toJSONString(result);
                        success.invoke(str);

                    }

                    @Override
                    public void failEvent(String result) {
                        fail.invoke(result);
                    }
                });
                break;
        }
    }

    @JSMethod
    public void logoutWithPlatformType(String platfrom, final JSCallback success, final JSCallback fail) {
        init();
        switch (platfrom) {
            case SharePlatform.P_WECHATSESSION:
            case SharePlatform.P_FACEBOOK:
                umShareAPI.get(mContext).deleteOauth((Activity) mContext, ShareUtil.getShareMedia(platfrom), new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        SocializeUtils.safeShowDialog(dialog);
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                        SocializeUtils.safeCloseDialog(dialog);
                        Toast.makeText(mContext, "success", Toast.LENGTH_LONG).show();
                        String dataJson = "";
                        if (data != null) {
                            dataJson = JSON.toJSONString(data);
                        }
                        success.invoke(dataJson);

                    }

                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        SocializeUtils.safeCloseDialog(dialog);
                        Toast.makeText(mContext, "fail：" + t.getMessage(), Toast.LENGTH_LONG).show();
                        fail.invoke(t.getMessage());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        SocializeUtils.safeCloseDialog(dialog);
                        Toast.makeText(mContext, "be already canceled..", Toast.LENGTH_LONG).show();
                    }
                });
                break;


            case SharePlatform.P_GOOGLE:
                if (mGoogleSignInClient == null) {
                 //   showToast("please Initialization google api.");
                    fail.invoke("please Initialization google api.");
                    return;
                }
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener((Activity) mWXSDKInstance.getContext(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //退出成功

                                success.invoke("Success");
                            }
                        });
                break;
        }
    }

    @JSMethod
    public void refreshTokenWithPlatformType(String platfrom, final JSCallback success, final JSCallback fail) {
        init();
        switch (platfrom) {
            case SharePlatform.P_GOOGLE:
                if (mGoogleSignInClient == null) {
                   // showToast("please Initialization google api.");
                    fail.invoke("please Initialization google api.");
                    return;
                }
                mGoogleSignInClient.silentSignIn()
                        .addOnCompleteListener((Activity) mWXSDKInstance.getContext(), new OnCompleteListener<GoogleSignInAccount>() {
                            @Override
                            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                                try{
                                    GoogleSignInAccount account = task.getResult(ApiException.class);
                                    String str = JSON.toJSONString(account);

                                    success.invoke(str);

                                } catch (ApiException e) {
                                    String message = e.toString();
                                    fail.invoke(e.toString());
                                }
                            }
                        });
                break;
        }
    }

    @JSMethod
    public void shareWithInfo(ShareInfoBean model, final JSCallback success, final JSCallback fail) {
        init();
        switch (model.getPlatform()) {
            case SharePlatform.P_WECHATSESSION:
            case SharePlatform.P_WECHATTIMELINE:
            case SharePlatform.P_FACEBOOK:
                //其他平台 以分享方式判断
                if (ShareMediaType.TEXT.getKey().equals(model.getShareType())) {
                    //文本分享
                    new ShareTextAchiever().shareAction((Activity) mContext, ShareUtil.getShareMedia(model.getPlatform()),
                            model, new mUMShareListener(success, fail));
                } else if (ShareMediaType.IMAGE.getKey().equals(model.getShareType())) {
                    new ShareImageAchiever().shareAction((Activity) mContext, ShareUtil.getShareMedia(model.getPlatform()),
                            model, new mUMShareListener(success, fail));
                    //图片分享
                } else if (ShareMediaType.TEXTIMAGE.getKey().equals(model.getShareType())) {
                    new ShareTextImageAchiever().shareAction((Activity) mContext, ShareUtil.getShareMedia
                            (model.getPlatform()), model, new mUMShareListener(success, fail));
                    //图文分享
                } else if (ShareMediaType.MUSIC.getKey().equals(model.getShareType())) {
                    //音乐
                    new ShareMusicAchiever().shareAction((Activity) mContext, ShareUtil.getShareMedia(model.getPlatform()),
                            model, new mUMShareListener(success, fail));
                } else if (ShareMediaType.MINIPROGRAM.getKey().equals(model.getShareType())) {
                    //小程序
                    new ShareMiniProgramAchiever().shareAction((Activity) mContext, ShareUtil.getShareMedia
                            (model.getPlatform()), model, new mUMShareListener(success, fail));
                } else {
                    //网页
                    new ShareWebAchiever().shareAction((Activity) mContext, ShareUtil.getShareMedia(model.getPlatform()),
                            model, new mUMShareListener(success, fail));
                }
                break;
            case SharePlatform.P_GOOGLE:

                break;
        }
    }

    class mUMShareListener implements UMShareListener {
        private JSCallback success;
        private JSCallback fail;

        public mUMShareListener(JSCallback success, JSCallback fail) {
            this.success = success;
            this.fail = fail;
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            SocializeUtils.safeCloseDialog(dialog);

            if (success != null) {
               // Toast.makeText(mContext, "success", Toast.LENGTH_LONG).show();
                success.invoke("Success");
            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            if (fail != null) {
               // Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                fail.invoke(t.getMessage());
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mContext, "onCancel", Toast.LENGTH_LONG).show();
            SocializeUtils.safeCloseDialog(dialog);
        }
    }

    private void init() {
        if (mContext == null) {
            this.mContext = mWXSDKInstance.getContext();

        }
        if (dialog == null) {
            dialog = new ProgressDialog(mContext);
        }

    }

//    private Context safeContext(Context context) {
//        if (!(context instanceof Activity)) {
//            context = RouterTracker.peekActivity();
//        }
//        return context;
//    }

    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        UMShareAPI.get(mWXSDKInstance.getContext()).release();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_TOKEN) {
            try{
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //String str = JSON.toJSONString(account);

                mOnGetLoginInfoListener.setGoogleInfo(account);

            } catch (ApiException e) {
                String message = e.toString();
                mOnGetLoginInfoListener.failEvent(message);
            }

        } else {
            UMShareAPI.get(mWXSDKInstance.getContext()).onActivityResult(requestCode, resultCode, data);
        }


    }

    interface OnGetLoginInfoListener {
        void setGoogleInfo(GoogleSignInAccount result);
        void failEvent(String result);
    }

    private void showToast(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    private void setOnGetLoginInfoListener(OnGetLoginInfoListener listener) {
        mOnGetLoginInfoListener = listener;
    }

}