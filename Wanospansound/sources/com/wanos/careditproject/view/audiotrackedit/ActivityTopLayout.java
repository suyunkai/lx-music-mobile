package com.wanos.careditproject.view.audiotrackedit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectUpdateResponse;
import com.wanos.careditproject.databinding.LayoutActivityEditTopBinding;
import com.wanos.careditproject.event.ProjectChangeEvent;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.WanosStringUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class ActivityTopLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    protected LayoutActivityEditTopBinding binding;
    protected boolean isNameChanged;
    protected Context mContext;
    protected IProjectNameChangeListener nameChangeListener;
    protected boolean nameEdit;
    protected boolean softKeyboardShow;

    public interface IProjectNameChangeListener {
        void onChange(String str);
    }

    public ActivityTopLayout(Context context) {
        this(context, null);
    }

    public ActivityTopLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityTopLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.nameEdit = false;
        this.softKeyboardShow = false;
        this.isNameChanged = false;
        initView(context);
    }

    public void initView(Context context) {
        this.binding = LayoutActivityEditTopBinding.inflate(LayoutInflater.from(context), this, true);
        this.mContext = context;
        initListener();
    }

    public void initListener() {
        this.binding.etProjectName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wanos.careditproject.view.audiotrackedit.ActivityTopLayout.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                EditingUtils.log("ActivityTopLayout onEditorAction actionId = " + i);
                if (i != 6) {
                    return false;
                }
                final String string = textView.getText().toString();
                if (!StringUtils.isEmpty(string)) {
                    if (WanosStringUtils.isContainOnlyCnLetterOrDigit(string)) {
                        ActivityTopLayout.this.isNameChanged = true;
                        final ProjectInfo curProjectInfo = EditingParams.getInstance().getCurProjectInfo();
                        if (curProjectInfo != null) {
                            CreatorRetrofitUtil.projectUpdate(curProjectInfo.getId(), string, curProjectInfo.getPicture(), new ResponseCallBack<EditProjectUpdateResponse>(ActivityTopLayout.this.getContext()) { // from class: com.wanos.careditproject.view.audiotrackedit.ActivityTopLayout.1.1
                                @Override // com.wanos.WanosCommunication.ResponseCallBack
                                public void onResponseSuccessful(EditProjectUpdateResponse editProjectUpdateResponse) {
                                    ProjectInfo projectInfo = new ProjectInfo();
                                    projectInfo.setId(curProjectInfo.getId());
                                    projectInfo.setTitle(string);
                                    projectInfo.setPicture(curProjectInfo.getPicture());
                                    EventBus.getDefault().post(new ProjectChangeEvent(1, 0, projectInfo));
                                    ToastUtil.showMsg("修改成功！");
                                    ActivityTopLayout.this.setName(string);
                                }

                                @Override // com.wanos.WanosCommunication.ResponseCallBack
                                public void onResponseFailure(int i2, String str) {
                                    ToastUtil.showMsg("修改失败请重试！");
                                }
                            });
                            return false;
                        }
                        ToastUtil.showMsg("修改失败请重试！");
                        return false;
                    }
                    ToastUtil.showMsg(R.string.tips_name_0);
                    return false;
                }
                ToastUtil.showMsg("名称不能为空！");
                return false;
            }
        });
        ((Activity) this.mContext).getWindow().getDecorView().findViewById(android.R.id.content).getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void removeListener() {
        ((Activity) this.mContext).getWindow().getDecorView().findViewById(android.R.id.content).getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    public void setName(String str) {
        if (str.length() > 10) {
            str = str.substring(0, 10) + "...";
        }
        this.binding.tvProjectName.setText(str);
        this.binding.etProjectName.setText(str);
    }

    public void setNameEdit(boolean z) {
        this.nameEdit = z;
        if (z) {
            this.binding.tvProjectName.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.audiotrackedit.ActivityTopLayout.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ActivityTopLayout.this.binding.tvProjectName.setVisibility(8);
                    ActivityTopLayout.this.binding.etProjectName.setVisibility(0);
                    ActivityTopLayout.this.binding.etProjectName.setFocusable(true);
                    ActivityTopLayout.this.binding.etProjectName.setFocusableInTouchMode(true);
                    ActivityTopLayout.this.binding.etProjectName.requestFocus();
                    ActivityTopLayout.this.binding.etProjectName.setSelection(ActivityTopLayout.this.binding.etProjectName.getText().length());
                    InputMethodManager inputMethodManager = (InputMethodManager) ActivityTopLayout.this.mContext.getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.showSoftInput(ActivityTopLayout.this.binding.etProjectName, 1);
                    }
                }
            });
        }
    }

    public void setNameChangeListener(IProjectNameChangeListener iProjectNameChangeListener) {
        this.nameChangeListener = iProjectNameChangeListener;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        View viewFindViewById = ((Activity) this.mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        Rect rect = new Rect();
        viewFindViewById.getWindowVisibleDisplayFrame(rect);
        if (viewFindViewById.getRootView().getHeight() - rect.bottom > Util.dip2px(getContext(), 150.0f)) {
            this.softKeyboardShow = true;
            this.isNameChanged = false;
            return;
        }
        if (this.softKeyboardShow) {
            this.binding.tvProjectName.setVisibility(0);
            this.binding.etProjectName.setVisibility(8);
            if (!this.isNameChanged) {
                this.binding.etProjectName.setText(this.binding.tvProjectName.getText());
            }
        }
        this.softKeyboardShow = false;
    }
}
