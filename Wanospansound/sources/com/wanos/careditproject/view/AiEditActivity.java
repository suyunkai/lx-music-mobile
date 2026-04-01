package com.wanos.careditproject.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.AiPromptEntity;
import com.wanos.careditproject.data.bean.AiRandomState;
import com.wanos.careditproject.databinding.ActivityAiEditBinding;
import com.wanos.careditproject.view.viewmodel.AiEditViewModel;
import com.wanos.careditproject.view.widget.AiFlowView;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.ToastUtil;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class AiEditActivity extends WanosBaseActivity {
    private static final String TAG = "wanos[AiEditActivity]";
    private ActivityAiEditBinding binding;

    public static void onLaunchActivity(Fragment fragment) {
        fragment.startActivity(new Intent(fragment.getContext(), (Class<?>) AiEditActivity.class));
    }

    public static void onLaunchActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) AiEditActivity.class));
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setSoftInputMode(16);
        final AiEditViewModel aiEditViewModel = (AiEditViewModel) new ViewModelProvider(this).get(AiEditViewModel.class);
        this.binding = ActivityAiEditBinding.inflate(getLayoutInflater());
        BarUtils.setStatusBarColor(this, Color.parseColor("#20272E"));
        BarUtils.setStatusBarLightMode((Activity) this, false);
        setContentView(this.binding.getRoot());
        setTitleBarVisibility(8);
        setPlayBarVisibility(8);
        this.binding.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.AiEditActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m414lambda$onCreate$0$comwanoscareditprojectviewAiEditActivity(view);
            }
        });
        this.binding.getRoot().setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiEditActivity.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                Log.d(AiEditActivity.TAG, "onAnitClick: ");
                if (AiEditActivity.this.getCurrentFocus() instanceof EditText) {
                    AiEditActivity.this.binding.aiEditView.hideSoftInput();
                }
            }
        });
        this.binding.lableView.setOnItemClickListener(new AiFlowView.OnItemClickListener() { // from class: com.wanos.careditproject.view.AiEditActivity.2
            @Override // com.wanos.careditproject.view.widget.AiFlowView.OnItemClickListener
            public void onItemClick(String str) {
                if (AiEditActivity.this.binding.aiEditView.getRandomState() != AiRandomState.LOADING) {
                    AiEditActivity.this.binding.aiEditView.setLabelsText(str);
                } else {
                    ToastUtil.showMsg(StringUtils.getString(R.string.ai_random_action));
                }
            }
        });
        this.binding.aiEditView.setCreateClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiEditActivity.3
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (aiEditViewModel.isCanCreate()) {
                    if (AiEditActivity.this.binding.aiEditView.getRandomState() != AiRandomState.LOADING) {
                        String aiContent = AiEditActivity.this.binding.aiEditView.getAiContent();
                        Log.i(AiEditActivity.TAG, "onAnitClick: aiContent = " + aiContent);
                        aiEditViewModel.setLastClickTime();
                        AiPlayActivity.startActivity(AiEditActivity.this, aiContent);
                        return;
                    }
                    ToastUtil.showMsg(StringUtils.getString(R.string.ai_random_action));
                    return;
                }
                ToastUtil.showMsg("操作太快了");
            }
        });
        aiEditViewModel.labelsData.observe(this, new Observer() { // from class: com.wanos.careditproject.view.AiEditActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m415lambda$onCreate$2$comwanoscareditprojectviewAiEditActivity((List) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-wanos-careditproject-view-AiEditActivity, reason: not valid java name */
    /* synthetic */ void m414lambda$onCreate$0$comwanoscareditprojectviewAiEditActivity(View view) {
        onBackPressed();
    }

    /* JADX INFO: renamed from: lambda$onCreate$2$com-wanos-careditproject-view-AiEditActivity, reason: not valid java name */
    /* synthetic */ void m415lambda$onCreate$2$comwanoscareditprojectviewAiEditActivity(List list) {
        this.binding.lableView.setTableList((List) list.stream().map(new Function() { // from class: com.wanos.careditproject.view.AiEditActivity$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AiPromptEntity.PromptBean) obj).getPrompt_content().trim();
            }
        }).collect(Collectors.toList()));
    }
}
