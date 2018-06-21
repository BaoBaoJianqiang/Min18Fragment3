package jianqiang.com.plugin1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.BaseFragment;
import com.example.jianqiang.mypluginlibrary.PluginItem;
import com.example.jianqiang.mypluginlibrary.PluginManager;

public class Fragment1 extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment1, container, false);

		view.findViewById(R.id.load_fragment2_btn).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Fragment2 fragment2 = new Fragment2();
				Bundle args = new Bundle();
				args.putString("username", "baobao");
				fragment2.setArguments(args);

				getFragmentManager()
						.beginTransaction()
						.addToBackStack(null)  //将当前fragment加入到返回栈中
						.replace(Fragment1.this.getContainerId(), fragment2).commit();
			}
		});

		view.findViewById(R.id.loadHostFragment).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Fragment fragment3 = (Fragment)RefInvoke.createObject("jianqiang.com.hostapp.Fragment3");
				Bundle args = new Bundle();
				args.putString("username", "baobao");
				fragment3.setArguments(args);

				getFragmentManager()
						.beginTransaction()
						.addToBackStack(null)  //将当前fragment加入到返回栈中
						.replace(Fragment1.this.getContainerId(), fragment3).commit();
			}
		});

		view.findViewById(R.id.loadPlugin2Fragment).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String dexPath = null;
				for(PluginItem item : PluginManager.plugins) {
					if(item.pluginPath.contains("plugin2")) {
						dexPath = item.pluginPath;
						break;
					}
				}

				Intent intent = new Intent(AppConstants.ACTION);
				intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
				intent.putExtra(AppConstants.EXTRA_PACKANE_NAME, "com.jianqiang.plugin2");
				intent.putExtra(AppConstants.EXTRA_CLASS, "com.jianqiang.plugin2.Fragment4");
				startActivity(intent);
			}
		});

		return view;
	}
}

