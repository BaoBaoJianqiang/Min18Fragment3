package com.example.jianqiang.mypluginlibrary;

import android.app.Fragment;

public class BaseFragment extends Fragment {
	private int containerId;

	public int getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = containerId;
	}
}

