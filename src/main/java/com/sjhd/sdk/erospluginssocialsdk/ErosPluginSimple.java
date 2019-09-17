package com.sjhd.sdk.erospluginssocialsdk;

import android.widget.Toast;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

@WeexModule(name="testplugin",lazyLoad = true)
public class ErosPluginSimple extends WXModule {
    @JSMethod(uiThread = true)
    public void hello(){
        Toast.makeText(mWXSDKInstance.getContext(),"test",Toast.LENGTH_LONG).show();
    }
}
