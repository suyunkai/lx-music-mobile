package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.EditGetPictureListBean;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.ProjectCoverPicAdapter;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditGetPictureListResponse;
import com.wanos.careditproject.data.response.EditProjectUpdateResponse;
import com.wanos.careditproject.databinding.DialogCreatorProjectModifyBinding;
import com.wanos.careditproject.databinding.LayoutCreatorDialogItemBinding;
import com.wanos.careditproject.event.ProjectChangeEvent;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.WanosStringUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorProjectModifyDialog extends Dialog implements View.OnClickListener, ProjectCoverPicAdapter.OnSelectConverPicListener {
    private DialogCreatorProjectModifyBinding binding;
    protected LayoutCreatorDialogItemBinding bindingItem;
    private String coverImgUrl;
    private String desc;
    private Intent intentService;
    private boolean isProject;
    boolean isProjectChanging;
    private boolean isPublishing;
    private boolean isPull;
    private String pic;
    private ProjectCoverPicAdapter projectCoverPicAdapter;
    private String projectId;
    private String title;

    public CreatorProjectModifyDialog(Context context, boolean z, boolean z2, String str, String str2, String str3, String str4) {
        super(context, R.style.FullScreenDialogTheme);
        this.isPublishing = false;
        this.isProjectChanging = false;
        this.isPull = z2;
        this.isProject = z;
        this.projectId = str;
        this.title = str2;
        this.desc = str3;
        this.pic = str4;
        initDialog();
    }

    public CreatorProjectModifyDialog(Context context, int i) {
        super(context, i);
        this.isPublishing = false;
        this.title = "";
        this.desc = "";
        this.pic = "";
        this.projectId = "";
        this.isProjectChanging = false;
        initDialog();
    }

    public void initDialog() {
        DialogCreatorProjectModifyBinding dialogCreatorProjectModifyBindingInflate = DialogCreatorProjectModifyBinding.inflate(getLayoutInflater());
        this.binding = dialogCreatorProjectModifyBindingInflate;
        this.bindingItem = LayoutCreatorDialogItemBinding.bind(dialogCreatorProjectModifyBindingInflate.getRoot());
        setContentView(this.binding.getRoot());
        iniView();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    private void iniView() {
        this.bindingItem.tvName.setText(this.isProject ? "输入项目名称" : "输入作品名称");
        if (!this.title.equals("")) {
            this.bindingItem.etProjectName.setText(this.title);
        }
        initSelectCoverRecyclerView();
        initListener();
    }

    private void initSelectCoverRecyclerView() {
        final ArrayList arrayList = new ArrayList();
        List<EditGetPictureListBean.ProjectPictureInfo> picList = EditingParams.getInstance().getPicList();
        if (picList != null) {
            Iterator<EditGetPictureListBean.ProjectPictureInfo> it = picList.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getPicture());
            }
            initPic(arrayList);
        }
        CreatorRetrofitUtil.getProjectPictureList(new ResponseCallBack<EditGetPictureListResponse>(getContext()) { // from class: com.wanos.careditproject.view.dialog.CreatorProjectModifyDialog.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditGetPictureListResponse editGetPictureListResponse) {
                if (editGetPictureListResponse.data == null || editGetPictureListResponse.data.getList() == null || editGetPictureListResponse.data.getList().size() <= 0) {
                    return;
                }
                arrayList.clear();
                Iterator<EditGetPictureListBean.ProjectPictureInfo> it2 = editGetPictureListResponse.data.getList().iterator();
                while (it2.hasNext()) {
                    arrayList.add(it2.next().getPicture());
                }
                CreatorProjectModifyDialog.this.initPic(arrayList);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                ToastUtils.showShort(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPic(List<String> list) {
        ProjectCoverPicAdapter projectCoverPicAdapter = this.projectCoverPicAdapter;
        if (projectCoverPicAdapter != null) {
            projectCoverPicAdapter.setData(list);
            this.projectCoverPicAdapter.notifyDataSetChanged();
            return;
        }
        this.coverImgUrl = this.pic.equals("") ? list.get(0) : this.pic;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        ProjectCoverPicAdapter projectCoverPicAdapter2 = new ProjectCoverPicAdapter(getContext(), list, this.coverImgUrl);
        this.projectCoverPicAdapter = projectCoverPicAdapter2;
        projectCoverPicAdapter2.setOnSelectConverPicListener(this);
        this.bindingItem.rvCoverImgList.setLayoutManager(linearLayoutManager);
        this.bindingItem.rvCoverImgList.setAdapter(this.projectCoverPicAdapter);
    }

    private void initListener() {
        this.binding.btnBack.setOnClickListener(this);
        this.binding.btnDialogLeft.setOnClickListener(this);
        this.binding.btnDialogRight.setOnClickListener(this);
        this.bindingItem.etProjectName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectModifyDialog.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                EditingUtils.log("ActivityTopLayout onEditorAction actionId = " + i);
                if (i != 6 && i != 5) {
                    return false;
                }
                CreatorProjectModifyDialog.this.hideSoftInput();
                return false;
            }
        });
    }

    public void hideSoftInput() {
        if (this.bindingItem.etProjectName.hasFocus()) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            this.bindingItem.etProjectName.clearFocus();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            onBackPressed();
            return;
        }
        if (id == R.id.btn_change_cover) {
            if (this.bindingItem.rvCoverImgList.getVisibility() == 0) {
                this.bindingItem.rvCoverImgList.setVisibility(8);
                return;
            } else {
                this.bindingItem.rvCoverImgList.setVisibility(0);
                return;
            }
        }
        if (id == R.id.btn_dialog_left) {
            EditingUtils.log("id == R.id.btn_comfirm");
            if (this.isProject) {
                projectUpdate();
                return;
            } else {
                productionUpdate();
                return;
            }
        }
        if (id == R.id.btn_dialog_right) {
            dismiss();
        } else if (id == R.id.btn_back) {
            dismiss();
        }
    }

    @Override // com.wanos.careditproject.adapter.ProjectCoverPicAdapter.OnSelectConverPicListener
    public void onSelectConverPicListener(String str) {
        this.coverImgUrl = str;
        EditingUtils.log("onSelectConverPicListener coverImgUrl=" + this.coverImgUrl);
    }

    private void productionUpdate() {
        final String string = this.bindingItem.etProjectName.getText().toString();
        final String str = this.coverImgUrl;
        final String str2 = "描述";
        if (!string.equals("")) {
            CreatorRetrofitUtil.worksUpdate(this.projectId, string, "描述", str, new ResponseCallBack<BaseResponse>(getContext()) { // from class: com.wanos.careditproject.view.dialog.CreatorProjectModifyDialog.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse baseResponse) {
                    ProjectInfo projectInfo = new ProjectInfo();
                    projectInfo.setId(CreatorProjectModifyDialog.this.projectId);
                    projectInfo.setTitle(string);
                    projectInfo.setContent(str2);
                    projectInfo.setPicture(str);
                    projectInfo.setUpdateTime(System.currentTimeMillis());
                    EventBus.getDefault().post(new ProjectChangeEvent(1, 1, projectInfo));
                    ToastUtils.showShort("修改成功！");
                    CreatorProjectModifyDialog.this.dismiss();
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i, String str3) {
                    ToastUtils.showShort("修改失败请重试！");
                }
            });
        } else {
            ToastUtils.showShort("名称不能为空！");
        }
    }

    private void projectUpdate() {
        final String string = this.bindingItem.etProjectName.getText().toString();
        final String str = this.coverImgUrl;
        if (!StringUtils.isEmpty(string)) {
            if (WanosStringUtils.isContainOnlyCnLetterOrDigit(string)) {
                if (this.isProjectChanging) {
                    return;
                }
                this.isProjectChanging = true;
                CreatorRetrofitUtil.projectUpdate(this.projectId, string, str, new ResponseCallBack<EditProjectUpdateResponse>(getContext()) { // from class: com.wanos.careditproject.view.dialog.CreatorProjectModifyDialog.4
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(EditProjectUpdateResponse editProjectUpdateResponse) {
                        CreatorProjectModifyDialog.this.isProjectChanging = false;
                        ProjectInfo projectInfo = new ProjectInfo();
                        projectInfo.setId(CreatorProjectModifyDialog.this.projectId);
                        projectInfo.setTitle(string);
                        projectInfo.setPicture(str);
                        projectInfo.setUpdateTime(System.currentTimeMillis());
                        EventBus.getDefault().post(new ProjectChangeEvent(1, 0, projectInfo));
                        ToastUtil.showMsg("修改成功！");
                        CreatorProjectModifyDialog.this.dismiss();
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int i, String str2) {
                        CreatorProjectModifyDialog.this.isProjectChanging = false;
                        ToastUtil.showMsg(str2);
                    }
                });
                return;
            }
            ToastUtil.showMsg(R.string.tips_name_0);
            return;
        }
        ToastUtil.showMsg("名称不能为空！");
    }
}
