//package com.sjhd.sdk.erospluginssocialsdk.module;
//
//import android.app.Activity;
//import android.content.Intent;
//
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.widget.Toast;
//
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.weex.plugin.annotation.WeexModule;
//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.auth.api.signin.GoogleSignInResult;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.taobao.weex.annotation.JSMethod;
//import com.taobao.weex.bridge.JSCallback;
//import com.taobao.weex.common.WXModule;
//
//@WeexModule(name = "GoogleModule")
//public class GoogleModule extends WXModule {
//
//    private static GoogleModule googleModule;
//    private GoogleSignInClient mGoogleSignInClient;
//    private static final int RC_SIGN_IN = 0x100;
//    private OnGetLoginInfoListener mOnGetLoginInfoListener;
//
//    private GoogleModule(){
//
//    }
//
//    public static GoogleModule getInstance(){
//        if (googleModule == null){
//            synchronized (GoogleModule.class){
//                if (googleModule == null){
//                    googleModule = new GoogleModule();
//                }
//            }
//        }
//
//        return googleModule;
//    }
//
//
//    public void init(String clientId) {
//        // Initialize Firebase Auth
//        //     mAuth = FirebaseAuth.getInstance();
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(clientId)
//            .requestId()
//            .requestProfile()
//            .requestEmail()
//            .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(mWXSDKInstance.getContext(), gso);
//
//
//    }
//
//    @JSMethod(uiThread = true)
//    public void login(final JSCallback success,final JSCallback fail) {
//        if (mGoogleSignInClient == null) {
//            showToast("please Initialization google api.");
//            fail.invoke("please Initialization google api.");
//            return;
//        }
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        ((Activity) (mWXSDKInstance.getContext())).startActivityForResult(signInIntent, RC_SIGN_IN);
//
//        setOnGetLoginInfoListener(new OnGetLoginInfoListener() {
//            @Override
//            public void setGoogleInfo(GoogleSignInAccount result) {
//
//                String str = JSON.toJSONString(result);
//                success.invoke(str);
//
//            }
//        });
//
//    }
//
//    @JSMethod(uiThread = true)
//    public void logout(final JSCallback success,final JSCallback fail) {
//        if (mGoogleSignInClient == null) {
//            showToast("please Initialization google api.");
//            fail.invoke("please Initialization google api.");
//            return;
//        }
//        mGoogleSignInClient.signOut()
//            .addOnCompleteListener((Activity) mWXSDKInstance.getContext(), new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    //退出成功
//                    showToast("Success");
//                    success.invoke("Success");
//                }
//            });
//
//    }
//
//    @Override
//    public void onActivityCreate() {
//        super.onActivityCreate();
//
//    }
//
//
//    @Override
//    public void onActivityStart() {
//        super.onActivityStart();
//
//
//    }
//
//    @Override
//    public void onActivityDestroy() {
//        super.onActivityDestroy();
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            String str = JSON.toJSONString(result);
//            if (result.isSuccess()) {
//                // The signed in account is stored in the result.
//
//                GoogleSignInAccount signedInAccount = result.getSignInAccount();
//                mOnGetLoginInfoListener.setGoogleInfo(signedInAccount);
//            } else {
//                String message = result.getStatus().getStatusMessage();
//
//                showToast(message);
//
//            }
//
//            Log.e("result",str);
//
//        }
//
//    }
//
//    private void showToast(String msg) {
//        Toast.makeText(mWXSDKInstance.getContext(), "" + msg, Toast.LENGTH_SHORT).show();
//    }
//
//    private void setOnGetLoginInfoListener(OnGetLoginInfoListener listener) {
//        mOnGetLoginInfoListener = listener;
//    }
//
//    interface OnGetLoginInfoListener {
//        void setGoogleInfo(GoogleSignInAccount result);
//    }
//
//}
