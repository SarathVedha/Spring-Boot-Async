package com.vedha.service;

import java.util.Map;

public interface AsyncService {

    Map<String, String> demo(String name);

    Map<String, String> normal(String name);

    Map<String, String> multiThreading(String name);

    Map<String, String> async(String name);

    Map<String, String> asyncWithReturn(String name);
}
