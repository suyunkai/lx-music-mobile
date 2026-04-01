package com.wanos.careditproject.view.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.bean.EditGetPictureListBean;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.ProjectCoverPicAdapter;
import com.wanos.careditproject.data.bean.WorkCateBean;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.data.response.EditGetPictureListResponse;
import com.wanos.careditproject.databinding.DialogCreatorProjectPublishBinding;
import com.wanos.careditproject.service.PublishService;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.WanosStringUtils;
import com.wanos.careditproject.view.dialog.SelectProjectIconDialog;
import com.wanos.careditproject.view.viewmodel.ProjectPublishViewModel;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.util.AnitClick;
import com.wanos.media.viewmodel.CollectionDialogViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorProjectPublishActivity extends WanosBaseActivity implements View.OnClickListener, ProjectCoverPicAdapter.OnSelectConverPicListener {
    private static final String KEY_TRACK_ID = "track_id";
    private static final String KEY_TRACK_INDEX = "track_index";
    private DialogCreatorProjectPublishBinding binding;
    private List<WorkCateBean> cateList;
    private String coverImgUrl;
    private Intent intentService;
    protected ProjectCoverPicAdapter projectCoverPicAdapter;
    protected ProjectPublishViewModel viewModel;
    private PUBLISHSTATE publishState = PUBLISHSTATE.WAITING;
    private boolean isPublishing = false;
    private boolean isAI = false;
    private String projectId = "";
    private boolean openTemplate = false;
    private String[] testData = {"测试1", "ceshi2", "ceshi3", "测试1", "ceshi2", "ceshi3", "测试1", "ceshi2", "ceshi3"};

    public enum PUBLISHSTATE {
        WAITING,
        RUNNING,
        DONE
    }

    public static void open(Context context, String str, String str2, String str3, String str4, EditingUtils.EditProjectType editProjectType) {
        Intent intent = new Intent(context, (Class<?>) CreatorProjectPublishActivity.class);
        intent.putExtra("isAI", false);
        intent.putExtra("projectId", str);
        intent.putExtra(CollectionDialogViewModel.KEY_TITLE, "");
        intent.putExtra("desc", str3);
        intent.putExtra("pic", str4);
        intent.putExtra("projectType", editProjectType.value);
        intent.setFlags(537001984);
        context.startActivity(intent);
    }

    public static void openByAi(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) CreatorProjectPublishActivity.class);
        intent.putExtra("isAI", true);
        intent.putExtra(KEY_TRACK_ID, str);
        intent.putExtra(KEY_TRACK_INDEX, str2);
        intent.putExtra(CollectionDialogViewModel.KEY_TITLE, str3);
        intent.putExtra("desc", "");
        intent.putExtra("pic", "");
        intent.putExtra("projectType", EditingUtils.EditProjectType.MUSIC.value);
        intent.setFlags(537001984);
        context.startActivity(intent);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DialogCreatorProjectPublishBinding dialogCreatorProjectPublishBindingInflate = DialogCreatorProjectPublishBinding.inflate(getLayoutInflater());
        this.binding = dialogCreatorProjectPublishBindingInflate;
        setContentView(dialogCreatorProjectPublishBindingInflate.getRoot());
        setPlayBarVisibility(8);
        setTitleBarVisibility(8);
        this.viewModel = (ProjectPublishViewModel) new ViewModelProvider(this).get(ProjectPublishViewModel.class);
        if (bundle == null) {
            Intent intent = getIntent();
            this.isAI = intent.getBooleanExtra("isAI", false);
            this.viewModel.init(intent.getStringExtra("projectId"), intent.getStringExtra(CollectionDialogViewModel.KEY_TITLE), intent.getStringExtra("desc"), intent.getStringExtra("pic"), intent.getIntExtra("projectType", 1));
        }
        iniView();
    }

    private void iniView() {
        this.binding.etProjectName.setHint("输入作品名称");
        this.binding.etProjectName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        this.binding.tvName.setText("作品名称");
        updateView();
        initSelectCoverRecyclerView();
        initWorkCateData();
        initListener();
        updateOpenTemplate();
        updateAgreement();
    }

    private void updateView() {
        if (!this.viewModel.title.equals("")) {
            this.binding.etProjectName.setText(this.viewModel.title);
        }
        if (!this.viewModel.desc.equals("")) {
            this.binding.etProjectIntroduction.setText(this.viewModel.desc);
        }
        updateIcon();
        updateToCommunity();
        EditingUtils.log("updateView viewModel.title: " + this.viewModel.title + ", viewModel.desc: " + this.viewModel.desc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIcon() {
        if (this.viewModel.pic.equals("")) {
            return;
        }
        GlideUtil.loadImage(this.viewModel.pic, this.binding.imIcon);
    }

    private void updateOpenTemplate() {
        this.binding.switchOpen.setSelected(this.viewModel.openTemplate);
    }

    private void updateToCommunity() {
        this.binding.switchToCommunity.setSelected(this.viewModel.toCommunity);
        this.binding.switchToMine.setSelected(!this.viewModel.toCommunity);
    }

    private void updateAgreement() {
        this.binding.ivSelect.setSelected(this.viewModel.toAgree);
    }

    private void initSelectCoverRecyclerView() {
        CreatorRetrofitUtil.getProjectPictureList(new ResponseCallBack<EditGetPictureListResponse>(this) { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(EditGetPictureListResponse editGetPictureListResponse) {
                if (CreatorProjectPublishActivity.this.isFinishing() || CreatorProjectPublishActivity.this.isDestroyed() || editGetPictureListResponse.data == null || editGetPictureListResponse.data.getList() == null || editGetPictureListResponse.data.getList().size() <= 0) {
                    return;
                }
                List<EditGetPictureListBean.ProjectPictureInfo> list = editGetPictureListResponse.data.getList();
                EditingParams.getInstance().setPicList(list);
                if (!CreatorProjectPublishActivity.this.viewModel.pic.equals("") || list.size() <= 0) {
                    return;
                }
                CreatorProjectPublishActivity.this.viewModel.pic = list.get(0).getPicture();
                CreatorProjectPublishActivity.this.updateIcon();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                ToastUtil.showMsg(str);
            }
        });
    }

    private void initWorkCateData() {
        ArrayList arrayList = new ArrayList();
        List<WorkCateBean> value = this.viewModel.workCateList.getValue();
        this.cateList = value;
        if (value != null) {
            Iterator<WorkCateBean> it = value.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getName());
            }
        } else {
            arrayList.add("");
        }
        initWorkCateView(arrayList);
        this.viewModel.initWorkCateList(this.viewModel.projectType == EditingUtils.EditProjectType.MUSIC.value ? 1 : 2);
    }

    private void initWorkCateView(List<String> list) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.layout_spinner_item, list);
        arrayAdapter.setDropDownViewResource(R.layout.layout_spinner_item);
        this.binding.spinnerType.setAdapter((SpinnerAdapter) arrayAdapter);
        if (this.viewModel.workTypeIndex < list.size()) {
            this.binding.spinnerType.setSelection(this.viewModel.workTypeIndex, false);
        } else {
            this.binding.spinnerType.setSelection(0, false);
        }
    }

    private void initListener() {
        this.binding.btnBack.setOnClickListener(this);
        this.binding.btnDialogRight.setOnClickListener(this);
        this.binding.switchOpen.setOnClickListener(this);
        this.binding.ivSelect.setOnClickListener(this);
        this.binding.tvAgreement.setOnClickListener(this);
        this.binding.btnToCommunity.setOnClickListener(this);
        this.binding.btnToMine.setOnClickListener(this);
        this.binding.tvSelectIcon.setOnClickListener(this);
        this.binding.publishLoadingView.setOnClickListener(this);
        this.binding.viewRoot.setOnClickListener(this);
        this.binding.btnDialogLeft.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.2
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (CreatorProjectPublishActivity.this.viewModel.toAgree) {
                    EditingUtils.log("发布作品");
                    CreatorProjectPublishActivity.this.startPublish();
                } else {
                    ToastUtil.showMsg("请先同意协议");
                }
            }
        });
        this.binding.spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                EditingUtils.log("CreatorProjectPublishDialog onItemClick position = " + i);
                CreatorProjectPublishActivity.this.viewModel.workTypeIndex = CreatorProjectPublishActivity.this.binding.spinnerType.getSelectedItemPosition();
            }
        });
        this.binding.etProjectName.addTextChangedListener(new TextWatcher() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                CreatorProjectPublishActivity.this.viewModel.title = CreatorProjectPublishActivity.this.binding.etProjectName.getText().toString().trim();
            }
        });
        this.binding.etProjectIntroduction.addTextChangedListener(new TextWatcher() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                CreatorProjectPublishActivity.this.viewModel.desc = CreatorProjectPublishActivity.this.binding.etProjectIntroduction.getText().toString().trim();
            }
        });
        this.binding.etProjectName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.6
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                EditingUtils.log("ActivityTopLayout onEditorAction actionId = " + i);
                if (i != 6 && i != 5) {
                    return false;
                }
                CreatorProjectPublishActivity.this.hideSoftInput();
                return false;
            }
        });
        this.binding.etProjectIntroduction.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.7
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                EditingUtils.log("ActivityTopLayout onEditorAction actionId = " + i);
                if (i != 6 && i != 5) {
                    return false;
                }
                CreatorProjectPublishActivity.this.hideSoftInput();
                return false;
            }
        });
        this.viewModel.workCateList.observe(this, new Observer() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m427xdce902ea((List) obj);
            }
        });
        this.viewModel.publishResult.observe(this, new Observer() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m428x7956ff49((ProjectPublishViewModel.PublishResult) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$0$com-wanos-careditproject-view-dialog-CreatorProjectPublishActivity, reason: not valid java name */
    /* synthetic */ void m427xdce902ea(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((WorkCateBean) it.next()).getName());
        }
        initWorkCateView(arrayList);
    }

    /* JADX INFO: renamed from: lambda$initListener$1$com-wanos-careditproject-view-dialog-CreatorProjectPublishActivity, reason: not valid java name */
    /* synthetic */ void m428x7956ff49(ProjectPublishViewModel.PublishResult publishResult) {
        closeLoadingView();
        if (publishResult.isSuccess) {
            ToastUtil.showMsg(R.string.publish_success);
            finish();
            this.isPublishing = false;
        } else {
            ToastUtil.showMsg(publishResult.msg);
            this.isPublishing = false;
        }
    }

    public void hideSoftInput() {
        if (this.binding.etProjectName.hasFocus()) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            this.binding.etProjectName.clearFocus();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            finish();
            return;
        }
        if (id == R.id.btn_change_cover) {
            return;
        }
        if (id == R.id.btn_dialog_right || id == R.id.btn_back) {
            finish();
            return;
        }
        if (id == R.id.switch_open) {
            this.viewModel.openTemplate = !r3.openTemplate;
            updateOpenTemplate();
            return;
        }
        if (id == R.id.iv_select) {
            this.viewModel.toAgree = !r3.toAgree;
            updateAgreement();
            return;
        }
        if (id == R.id.tv_agreement) {
            EditProtocolDialog editProtocolDialog = new EditProtocolDialog(this, EditProtocolDialog.REDEEM_CODE_RULE_PRO);
            editProtocolDialog.setCancelable(true);
            editProtocolDialog.show();
        } else if (id == R.id.btn_to_community) {
            this.viewModel.toCommunity = true;
            updateToCommunity();
        } else if (id == R.id.btn_to_mine) {
            this.viewModel.toCommunity = false;
            updateToCommunity();
        } else if (id == R.id.tv_select_icon) {
            new SelectProjectIconDialog(this.viewModel.pic, new SelectProjectIconDialog.SelectIconListener() { // from class: com.wanos.careditproject.view.dialog.CreatorProjectPublishActivity.8
                @Override // com.wanos.careditproject.view.dialog.SelectProjectIconDialog.SelectIconListener
                public void onSelectIcon(String str) {
                    CreatorProjectPublishActivity.this.viewModel.pic = str;
                    CreatorProjectPublishActivity.this.updateIcon();
                }
            }).show(getSupportFragmentManager(), "selectProjectIcon");
        }
    }

    @Override // com.wanos.careditproject.adapter.ProjectCoverPicAdapter.OnSelectConverPicListener
    public void onSelectConverPicListener(String str) {
        this.coverImgUrl = str;
        EditingUtils.log("onSelectConverPicListener coverImgUrl=" + this.coverImgUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPublish() {
        List<WorkCateBean> value = this.viewModel.workCateList.getValue();
        this.cateList = value;
        if (value == null || value.size() <= this.viewModel.workTypeIndex) {
            ToastUtil.showMsg("作品类型有问题，请重试");
            return;
        }
        WorkCateBean workCateBean = this.cateList.get(this.viewModel.workTypeIndex);
        String str = this.viewModel.title;
        if (str.isEmpty()) {
            ToastUtil.showMsg(getString(R.string.no_name));
            return;
        }
        if (this.viewModel.desc.isEmpty()) {
            ToastUtil.showMsg(getString(R.string.no_desc));
            return;
        }
        String str2 = this.viewModel.pic;
        if (str2.isEmpty()) {
            ToastUtil.showMsg(getString(R.string.no_cover));
            return;
        }
        int id = workCateBean.getId();
        int i = this.viewModel.openTemplate ? 1 : 2;
        int i2 = this.viewModel.toCommunity ? 2 : 1;
        if (!StringUtils.isEmpty(str)) {
            if (WanosStringUtils.isContainOnlyCnLetterOrDigit(str)) {
                if (this.isPublishing) {
                    return;
                }
                this.isPublishing = true;
                if (!this.isAI) {
                    showLoadingView();
                    this.viewModel.publishProject(this.isAI, str, str2, id, i, i2, "", "");
                    return;
                }
                String stringExtra = getIntent().getStringExtra(KEY_TRACK_ID);
                String stringExtra2 = getIntent().getStringExtra(KEY_TRACK_INDEX);
                if (StringUtils.isEmpty(stringExtra) || StringUtils.isEmpty(stringExtra2)) {
                    ToastUtil.showMsg("数据异常，发布失败~");
                    return;
                } else {
                    showLoadingView();
                    this.viewModel.publishProject(this.isAI, str, str2, id, i, i2, stringExtra, stringExtra2);
                    return;
                }
            }
            ToastUtil.showMsg(R.string.tips_name_0);
            return;
        }
        ToastUtil.showMsg("名称和介绍不能为空！");
    }

    private void startPublishService(String str) {
        Intent intent = new Intent(this, (Class<?>) PublishService.class);
        this.intentService = intent;
        startService(intent);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity
    public void showLoadingView() {
        this.binding.publishLoadingView.setVisibility(0);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity
    public void closeLoadingView() {
        this.binding.publishLoadingView.setVisibility(8);
    }
}
