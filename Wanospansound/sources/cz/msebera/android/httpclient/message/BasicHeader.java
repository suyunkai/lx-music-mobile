package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class BasicHeader implements Header, Cloneable, Serializable {
    private static final HeaderElement[] EMPTY_HEADER_ELEMENTS = new HeaderElement[0];
    private static final long serialVersionUID = -5427236326487562174L;
    private final String name;
    private final String value;

    public BasicHeader(String str, String str2) {
        this.name = (String) Args.notNull(str, "Name");
        this.value = str2;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // cz.msebera.android.httpclient.Header
    public HeaderElement[] getElements() throws ParseException {
        if (getValue() != null) {
            return BasicHeaderValueParser.parseElements(getValue(), (HeaderValueParser) null);
        }
        return EMPTY_HEADER_ELEMENTS;
    }

    @Override // cz.msebera.android.httpclient.NameValuePair
    public String getName() {
        return this.name;
    }

    @Override // cz.msebera.android.httpclient.NameValuePair
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatHeader((CharArrayBuffer) null, this).toString();
    }
}
