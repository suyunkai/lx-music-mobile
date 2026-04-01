package com.wanos.editmain.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.EditShareCodeBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditProjectCopyResponse;
import com.wanos.careditproject.data.response.EditShareCodeResponse;
import com.wanos.careditproject.databinding.DialogCreatorShareBinding;
import com.wanos.careditproject.event.ProjectChangeEvent;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.EditingActivity;
import com.wanos.media.util.ToastUtil;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorShareDialog extends Dialog implements View.OnClickListener {
    public static int TYPE_EDIT = 1;
    public static int TYPE_SHARE;
    private DialogCreatorShareBinding binding;
    private List<TextView> editShowTextList;
    private int maxLength;
    private String name;
    private String projectId;
    private int shareType;

    public interface TrackNameChangeListener {
        void onChange(String str);
    }

    public CreatorShareDialog(Context context, int i, String str) {
        super(context, R.style.FullScreenDialogTheme);
        this.name = "";
        this.maxLength = 6;
        this.shareType = i;
        this.projectId = str;
        initDialog(context);
    }

    public CreatorShareDialog(Context context, int i) {
        super(context, i);
        this.name = "";
        this.projectId = "";
        this.shareType = TYPE_SHARE;
        this.maxLength = 6;
        initDialog(context);
    }

    public void initDialog(Context context) {
        DialogCreatorShareBinding dialogCreatorShareBindingInflate = DialogCreatorShareBinding.inflate(getLayoutInflater());
        this.binding = dialogCreatorShareBindingInflate;
        setContentView(dialogCreatorShareBindingInflate.getRoot());
        ArrayList arrayList = new ArrayList();
        this.editShowTextList = arrayList;
        arrayList.add(this.binding.code0);
        this.editShowTextList.add(this.binding.code1);
        this.editShowTextList.add(this.binding.code2);
        this.editShowTextList.add(this.binding.code3);
        this.editShowTextList.add(this.binding.code4);
        this.editShowTextList.add(this.binding.code5);
        if (this.shareType == TYPE_SHARE) {
            getShareCode();
        }
        initView();
        initListener();
    }

    public void getShareCode() {
        CreatorRetrofitUtil.projectShare(this.projectId, new ResponseCallBack<EditShareCodeResponse>(getContext()) { // from class: com.wanos.editmain.dialog.CreatorShareDialog.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditShareCodeResponse editShareCodeResponse) {
                EditShareCodeBean editShareCodeBean = editShareCodeResponse.data;
                if (editShareCodeBean == null || editShareCodeBean.getShareCode().length() < 6) {
                    return;
                }
                EditingUtils.log("getShareCode code = " + editShareCodeBean.getShareCode());
                CreatorShareDialog.this.drawCode(editShareCodeBean.getShareCode());
            }
        });
    }

    public void initView() {
        if (this.shareType == TYPE_SHARE) {
            this.binding.etCode.setVisibility(8);
            this.binding.layoutEdit.setVisibility(8);
            this.binding.layoutShare.setVisibility(0);
            this.binding.tvTitle.setText("分享码");
            return;
        }
        this.binding.etCode.setVisibility(0);
        this.binding.layoutEdit.setVisibility(0);
        this.binding.layoutShare.setVisibility(8);
        this.binding.tvTitle.setText("输入分享码");
        this.binding.etCode.addTextChangedListener(new TextWatcher() { // from class: com.wanos.editmain.dialog.CreatorShareDialog.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.length() > CreatorShareDialog.this.maxLength) {
                    CreatorShareDialog.this.binding.etCode.setText(editable.subSequence(0, CreatorShareDialog.this.maxLength));
                    CreatorShareDialog.this.binding.etCode.setSelection(CreatorShareDialog.this.maxLength);
                }
                String string = CreatorShareDialog.this.binding.etCode.getText().toString();
                EditingUtils.log("drawCode 1 code = " + string);
                CreatorShareDialog.this.drawCode(string);
            }
        });
    }

    public void drawCode(String str) {
        String str2 = str.length() < 6 ? str + "       " : str;
        EditingUtils.log("drawCode code = " + str + ", showCode = " + str2);
        for (int i = 0; i < 6; i++) {
            this.editShowTextList.get(i).setText(str2.charAt(i) + "");
        }
    }

    public void toCreateProject(String str) {
        if (MediaPlayEngine.getInstance().getMediaPlayerService() != null && MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()) {
            MediaPlayEngine.getInstance().getMediaPlayerService().pause();
        }
        CreatorRetrofitUtil.projectCopyByShare(str, new ResponseCallBack<EditProjectCopyResponse>(getContext()) { // from class: com.wanos.editmain.dialog.CreatorShareDialog.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditProjectCopyResponse editProjectCopyResponse) {
                ProjectInfo projectInfo = editProjectCopyResponse.data;
                if (projectInfo != null) {
                    EventBus.getDefault().post(new ProjectChangeEvent(0, 0, projectInfo));
                    EditingActivity.openEditActivity(CreatorShareDialog.this.getContext(), editProjectCopyResponse.data.getProjectType(), editProjectCopyResponse.data.getId(), new Gson().toJson(editProjectCopyResponse.data));
                    CreatorShareDialog.this.dismiss();
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str2) {
                ToastUtil.showMsg("分享码有误，请检查后重试！");
            }
        });
    }

    public void initListener() {
        this.binding.btnBack.setOnClickListener(this);
        this.binding.btnDialogLeft.setOnClickListener(this);
        this.binding.btnDialogRight.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back || id == R.id.btn_dialog_right) {
            dismiss();
        } else if (id == R.id.btn_dialog_left) {
            toCreateProject(this.binding.etCode.getText().toString());
        }
    }
}
