package org.example.jt.realapp2.model;

import org.json.JSONObject;

/**
 * Created by jt on 1/19/15.
 */

enum DownloadStatus {IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK}
public abstract class ParseJSON {
    protected JSONObject mJsonObject;
    protected DownloadStatus mDownloadStatus;

    public ParseJSON(JSONObject mJsonObject) {
        this.mJsonObject = mJsonObject;
    }

    public JSONObject getJsonObject() {
        return mJsonObject;
    }

    abstract boolean processJSON();

}
