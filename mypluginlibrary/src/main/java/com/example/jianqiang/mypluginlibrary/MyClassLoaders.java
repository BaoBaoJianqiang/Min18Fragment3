package com.example.jianqiang.mypluginlibrary;

import java.util.HashMap;

import dalvik.system.DexClassLoader;

public class MyClassLoaders {
    public static final HashMap<String, DexClassLoader> classLoaders = new HashMap<String, DexClassLoader>();
}
