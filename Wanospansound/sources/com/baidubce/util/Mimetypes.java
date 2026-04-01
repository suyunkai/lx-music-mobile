package com.baidubce.util;

import com.alibaba.android.arouter.utils.Consts;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
public class Mimetypes {
    public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
    private static Mimetypes mimetypes;
    private HashMap<String, String> extensionToMimetypeMap = new HashMap<>();

    private Mimetypes() {
    }

    public static synchronized Mimetypes getInstance() {
        String str;
        Mimetypes mimetypes2 = mimetypes;
        if (mimetypes2 != null) {
            return mimetypes2;
        }
        Mimetypes mimetypes3 = new Mimetypes();
        mimetypes = mimetypes3;
        InputStream resourceAsStream = mimetypes3.getClass().getResourceAsStream("/mime.types");
        if (resourceAsStream != null) {
            BLog.debug("Loading mime types from file in the classpath: mime.types");
            try {
                try {
                    mimetypes.loadAndReplaceMimetypes(resourceAsStream);
                    try {
                        resourceAsStream.close();
                    } catch (IOException e) {
                        e = e;
                        str = "";
                        BLog.debug(str, (Throwable) e);
                    }
                } finally {
                }
            } catch (IOException e2) {
                BLog.error("Failed to load mime types from file in the classpath: mime.types", (Throwable) e2);
                try {
                    resourceAsStream.close();
                } catch (IOException e3) {
                    e = e3;
                    str = "";
                    BLog.debug(str, (Throwable) e);
                }
            }
        } else {
            BLog.warn("Unable to find 'mime.types' file in classpath");
        }
        return mimetypes;
    }

    public void loadAndReplaceMimetypes(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return;
            }
            String strTrim = line.trim();
            if (!strTrim.startsWith("#") && strTrim.length() != 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(strTrim, " \t");
                if (stringTokenizer.countTokens() > 1) {
                    String strNextToken = stringTokenizer.nextToken();
                    while (stringTokenizer.hasMoreTokens()) {
                        String strNextToken2 = stringTokenizer.nextToken();
                        this.extensionToMimetypeMap.put(strNextToken2.toLowerCase(), strNextToken);
                        BLog.debug("Setting mime type for extension '" + strNextToken2.toLowerCase() + "' to '" + strNextToken + "'");
                    }
                } else {
                    BLog.debug("Ignoring mimetype with no associated file extensions: '" + strTrim + "'");
                }
            }
        }
    }

    public String getMimetype(String str) {
        int i;
        int iLastIndexOf = str.lastIndexOf(Consts.DOT);
        if (iLastIndexOf > 0 && (i = iLastIndexOf + 1) < str.length()) {
            String lowerCase = str.substring(i).toLowerCase();
            if (this.extensionToMimetypeMap.keySet().contains(lowerCase)) {
                String str2 = this.extensionToMimetypeMap.get(lowerCase);
                BLog.debug("Recognised extension '" + lowerCase + "', mimetype is: '" + str2 + "'");
                return str2;
            }
            BLog.debug("Extension '" + lowerCase + "' is unrecognized in mime type listing, using default mime type: 'application/octet-stream'");
            return "application/octet-stream";
        }
        BLog.debug("File name has no extension, mime type cannot be recognised for: " + str);
        return "application/octet-stream";
    }

    public String getMimetype(File file) {
        return getMimetype(file.getName());
    }
}
