/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 *
 * @author yuezhao
 */
public class MyPage {

    private Request request;

    private MyResultItems resultItems = new MyResultItems();

    private Html html;

    private Json json;

    private String rawText;

    private Selectable url;

    private int statusCode;

    private boolean needCycleRetry;

    private List<Request> targetRequests = new ArrayList<Request>();

    public MyPage() {
    }

    public MyPage setSkip(boolean skip) {
        resultItems.setSkip(skip);
        return this;

    }

    /**
     * store extract results
     *
     * @param key key
     * @param field field
     */
    public void putField(String key, Review field) {
        resultItems.put(key, field);
    }

    /**
     * get html content of page
     *
     * @return html
     */
    public Html getHtml() {
        if (html == null) {
            html = new Html(UrlUtils.fixAllRelativeHrefs(rawText, request.getUrl()));
        }
        return html;
    }

    /**
     * get json content of page
     *
     * @return json
     * @since 0.5.0
     */
    public Json getJson() {
        if (json == null) {
            json = new Json(rawText);
        }
        return json;
    }

    /**
     * @param html html
     * @deprecated since 0.4.0
     * The html is parse just when first time of calling {@link #getHtml()}, so use {@link #setRawText(String)} instead.
     */
    public void setHtml(Html html) {
        this.html = html;
    }

    public List<Request> getTargetRequests() {
        return targetRequests;
    }

    /**
     * add urls to fetch
     *
     * @param requests requests
     */
    public void addTargetRequests(List<String> requests) {
        synchronized (targetRequests) {
            for (String s : requests) {
                if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                    continue;
                }
                s = UrlUtils.canonicalizeUrl(s, url.toString());
                targetRequests.add(new Request(s));
            }
        }
    }

    /**
     * add urls to fetch
     *
     * @param requests requests
     * @param priority priority
     */
    public void addTargetRequests(List<String> requests, long priority) {
        synchronized (targetRequests) {
            for (String s : requests) {
                if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                    continue;
                }
                s = UrlUtils.canonicalizeUrl(s, url.toString());
                targetRequests.add(new Request(s).setPriority(priority));
            }
        }
    }

    /**
     * add url to fetch
     *
     * @param requestString requestString
     */
    public void addTargetRequest(String requestString) {
        if (StringUtils.isBlank(requestString) || requestString.equals("#")) {
            return;
        }
        synchronized (targetRequests) {
            requestString = UrlUtils.canonicalizeUrl(requestString, url.toString());
            targetRequests.add(new Request(requestString));
        }
    }

    /**
     * add requests to fetch
     *
     * @param request request
     */
    public void addTargetRequest(Request request) {
        synchronized (targetRequests) {
            targetRequests.add(request);
        }
    }

    /**
     * get url of current page
     *
     * @return url of current page
     */
    public Selectable getUrl() {
        return url;
    }

    public void setUrl(Selectable url) {
        this.url = url;
    }

    /**
     * get request of current page
     *
     * @return request
     */
    public Request getRequest() {
        return request;
    }

    public boolean isNeedCycleRetry() {
        return needCycleRetry;
    }

    public void setNeedCycleRetry(boolean needCycleRetry) {
        this.needCycleRetry = needCycleRetry;
    }

    public void setRequest(Request request) {
        this.request = request;
        this.resultItems.setRequest(request);
    }

    public MyResultItems getResultItems() {
        return resultItems;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getRawText() {
        return rawText;
    }

    public MyPage setRawText(String rawText) {
        this.rawText = rawText;
        return this;
    }

    @Override
    public String toString() {
        return "Page{" +
                "request=" + request +
                ", resultItems=" + resultItems +
                ", rawText='" + rawText + '\'' +
                ", url=" + url +
                ", statusCode=" + statusCode +
                ", targetRequests=" + targetRequests +
                '}';
    }
}

