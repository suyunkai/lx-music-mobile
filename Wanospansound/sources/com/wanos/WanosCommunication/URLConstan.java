package com.wanos.WanosCommunication;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class URLConstan {
    public static String BASE_CREATOR_URL = "https://audio.wavworks.com/";
    public static String BASE_LOGIN = "https://wanos-common.wavworks.com/";
    public static String BASE_SEARCH_URL = "https://media.wavworks.com/";
    public static String BASE_STAT_URL = "https://wanos-common.wavworks.com/";
    public static String BASE_URL = "https://jili.wavworks.com/";
    public static String BASE_URL_NEW = "https://media.wavworks.com/";
    public static String BASE_ZERO = "https://car-studio.wavworks.com/";
    private static final String DEV_CREATOR_URL = "https://audio-dev.wanos.vip/";
    private static final String DEV_LOGIN_URL = "https://wanos-common-dev.wanos.vip/";
    private static final String DEV_SEARCH_URL = "https://media-dev.wanos.vip/";
    private static final String DEV_STAT_URL = "https://wanos-common-dev.wanos.vip/";
    private static final String DEV_URL = "https://jili-dev.wanos.vip/";
    private static final String DEV_URL_NEW = "https://media-dev.wanos.vip/";
    private static final String DEV_ZERO_URL = "https://car-studio-dev.wanos.vip/";
    private static final String PRE_CREATOR_URL = "https://audio-pre.wavworks.com/";
    private static final String PRE_LOGIN_URL = "https://wanos-common-pre.wavworks.com/";
    private static final String PRE_SEARCH_URL = "https://media-pre.wavworks.com/";
    private static final String PRE_STAT_URL = "https://wanos-common-pre.wavworks.com/";
    private static final String PRE_URL = "https://jili-pre.wavworks.com/";
    private static final String PRE_URL_NEW = "https://media-pre.wavworks.com/";
    private static final String PRE_ZERO_URL = "https://car-studio-pre.wavworks.com/";
    private static final String PROD_CREATOR_URL = "https://audio.wavworks.com/";
    private static final String PROD_LOGIN_URL = "https://wanos-common.wavworks.com/";
    private static final String PROD_SEARCH_URL = "https://media.wavworks.com/";
    private static final String PROD_STAT_URL = "https://wanos-common.wavworks.com/";
    private static final String PROD_URL = "https://jili.wavworks.com/";
    private static final String PROD_URL_NEW = "https://media.wavworks.com/";
    private static final String PROD_ZERO_URL = "https://car-studio.wavworks.com/";
    private static final String SHOW_CREATOR_URL = "https://audio-demo.wavworks.com/";
    private static final String SHOW_LOGIN_URL = "https://wanos-common-demo.wavworks.com/";
    private static final String SHOW_SEARCH_URL = "https://media-demo.wavworks.com/";
    private static final String SHOW_STAT_URL = "https://wanos-common-demo.wavworks.com/";
    private static final String SHOW_URL = "https://jili-demo.wavworks.com/";
    private static final String SHOW_URL_NEW = "https://media-demo.wavworks.com/";
    private static final String SHOW_ZERO_URL = "https://car-studio-demo.wavworks.com/";
    private static final String TEST_CREATOR_URL = "https://audio-test.wanos.vip/";
    private static final String TEST_LOGIN_URL = "https://wanos-common-test.wanos.vip/";
    private static final String TEST_SEARCH_URL = "https://media-test.wanos.vip/";
    private static final String TEST_STAT_URL = "https://wanos-common-test.wanos.vip/";
    private static final String TEST_URL = "https://jili-test.wanos.vip/";
    private static final String TEST_URL_NEW = "https://media-test.wanos.vip/";
    private static final String TEST_ZERO_URL = "https://car-studio-test.wanos.vip/";
    private static final String WJF_URL = "http://192.168.0.214:8190/";

    public static boolean isDevEnvironment() {
        return TextUtils.equals(BASE_URL, DEV_URL);
    }
}
