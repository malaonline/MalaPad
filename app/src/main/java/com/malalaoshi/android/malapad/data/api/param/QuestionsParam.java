package com.malalaoshi.android.malapad.data.api.param;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kang on 16/12/28.
 */

public class QuestionsParam extends BaseParam {
    @SerializedName("question_group")
    private Long groupId;

    public QuestionsParam(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
