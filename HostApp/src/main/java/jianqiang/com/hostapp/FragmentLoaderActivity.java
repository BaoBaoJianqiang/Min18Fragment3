package jianqiang.com.hostapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.BaseFragment;
import com.example.jianqiang.mypluginlibrary.MyClassLoaders;
import com.example.jianqiang.mypluginlibrary.PluginManager;

import dalvik.system.DexClassLoader;

public class FragmentLoaderActivity extends Activity {
    private DexClassLoader classLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String pluginName = getIntent().getStringExtra(AppConstants.EXTRA_PLUGIN_NAME);
        String mClass = getIntent().getStringExtra(AppConstants.EXTRA_CLASS);
        String mDexPath = getIntent().getStringExtra(AppConstants.EXTRA_DEX_PATH);

        classLoader = MyClassLoaders.classLoaders.get(mDexPath);

        super.onCreate(savedInstanceState);

        FrameLayout rootView = new FrameLayout(this);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.setId(android.R.id.primary);
        setContentView(rootView);

        BaseFragment fragment = null;
        try {
            if(classLoader == null) {
                fragment = (BaseFragment) getClassLoader().loadClass(mClass).newInstance();
            } else {
                fragment = (BaseFragment) classLoader.loadClass(mClass).newInstance();
            }

            fragment.setContainerId(android.R.id.primary);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(android.R.id.primary, fragment);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return PluginManager.mNowResources;
    }
}
