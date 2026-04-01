package cz.msebera.android.httpclient.impl.client.cache;

import androidx.media3.extractor.metadata.icy.IcyHeaders;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.message.HeaderGroup;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
class CacheEntryUpdater {
    private final ResourceFactory resourceFactory;

    CacheEntryUpdater() {
        this(new HeapResourceFactory());
    }

    CacheEntryUpdater(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }

    public HttpCacheEntry updateCacheEntry(String str, HttpCacheEntry httpCacheEntry, Date date, Date date2, HttpResponse httpResponse) throws IOException {
        Args.check(httpResponse.getStatusLine().getStatusCode() == 304, "Response must have 304 status code");
        return new HttpCacheEntry(date, date2, httpCacheEntry.getStatusLine(), mergeHeaders(httpCacheEntry, httpResponse), httpCacheEntry.getResource() != null ? this.resourceFactory.copy(str, httpCacheEntry.getResource()) : null, httpCacheEntry.getRequestMethod());
    }

    protected Header[] mergeHeaders(HttpCacheEntry httpCacheEntry, HttpResponse httpResponse) {
        String value;
        if (entryAndResponseHaveDateHeader(httpCacheEntry, httpResponse) && entryDateHeaderNewerThenResponse(httpCacheEntry, httpResponse)) {
            return httpCacheEntry.getAllHeaders();
        }
        HeaderGroup headerGroup = new HeaderGroup();
        headerGroup.setHeaders(httpCacheEntry.getAllHeaders());
        HeaderIterator headerIterator = httpResponse.headerIterator();
        while (headerIterator.hasNext()) {
            Header headerNextHeader = headerIterator.nextHeader();
            if (!"Content-Encoding".equals(headerNextHeader.getName())) {
                for (Header header : headerGroup.getHeaders(headerNextHeader.getName())) {
                    headerGroup.removeHeader(header);
                }
            }
        }
        HeaderIterator it = headerGroup.iterator();
        while (it.hasNext()) {
            Header headerNextHeader2 = it.nextHeader();
            if ("Warning".equalsIgnoreCase(headerNextHeader2.getName()) && (value = headerNextHeader2.getValue()) != null && value.startsWith(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                it.remove();
            }
        }
        HeaderIterator headerIterator2 = httpResponse.headerIterator();
        while (headerIterator2.hasNext()) {
            Header headerNextHeader3 = headerIterator2.nextHeader();
            if (!"Content-Encoding".equals(headerNextHeader3.getName())) {
                headerGroup.addHeader(headerNextHeader3);
            }
        }
        return headerGroup.getAllHeaders();
    }

    private boolean entryDateHeaderNewerThenResponse(HttpCacheEntry httpCacheEntry, HttpResponse httpResponse) {
        Date date = DateUtils.parseDate(httpCacheEntry.getFirstHeader("Date").getValue());
        Date date2 = DateUtils.parseDate(httpResponse.getFirstHeader("Date").getValue());
        return (date == null || date2 == null || !date.after(date2)) ? false : true;
    }

    private boolean entryAndResponseHaveDateHeader(HttpCacheEntry httpCacheEntry, HttpResponse httpResponse) {
        return (httpCacheEntry.getFirstHeader("Date") == null || httpResponse.getFirstHeader("Date") == null) ? false : true;
    }
}
