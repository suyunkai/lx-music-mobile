package com.wanos.media.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.ZeroApplication;
import com.wanos.media.adapter.RelaxListAdapter;
import com.wanos.media.entity.PageState;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.ui.info.ZeroInfoActivity;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.RelaxInfoRunBackground;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.viewmodel.RelaxListViewModel;
import com.wanos.media.viewmodel.ViewModelIntentFactory;
import com.wanos.media.widget.ItemDecoration;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.ActivityRelaxListBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxListActivity extends AppCompatActivity {
    private static final String TAG = "RelaxListActivity";
    private ActivityRelaxListBinding binding;
    private RelaxListAdapter mAdapter;
    private RelaxListViewModel viewModel;

    public static void onLaunch(Fragment fragment, int i) {
        Intent intent = new Intent(fragment.getContext(), (Class<?>) RelaxListActivity.class);
        intent.putExtra(RelaxListViewModel.KEY_OPEN_PARAMS, i);
        fragment.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        AppCompatDelegate.setDefaultNightMode(-1);
        super.onCreate(bundle);
        this.binding = ActivityRelaxListBinding.inflate(getLayoutInflater());
        this.viewModel = (RelaxListViewModel) new ViewModelProvider(this, new ViewModelIntentFactory(getIntent())).get(RelaxListViewModel.class);
        setContentView(this.binding.getRoot());
        this.binding.emptyView.tvEmpty.setText(R.string.zero_error_empty);
        this.mAdapter = new RelaxListAdapter(this, this.viewModel.getItemList());
        this.binding.rvRelaxList.addItemDecoration(new ItemDecoration((int) getResources().getDimension(R.dimen.relax_item_space)));
        this.binding.rvRelaxList.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.relax_home_grid_code)));
        this.binding.rvRelaxList.setAdapter(this.mAdapter);
        this.viewModel.titleText.observe(this, new Observer() { // from class: com.wanos.media.view.RelaxListActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m581lambda$onCreate$0$comwanosmediaviewRelaxListActivity((String) obj);
            }
        });
        RelaxInfoRunBackground.relaxInfoRunBackground.observe(this, new Observer() { // from class: com.wanos.media.view.RelaxListActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ToastUtil.showMsg((String) obj);
            }
        });
        this.viewModel.pageState.observe(this, new Observer() { // from class: com.wanos.media.view.RelaxListActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m582lambda$onCreate$1$comwanosmediaviewRelaxListActivity((PageState) obj);
            }
        });
        this.viewModel.upDataItemList.observe(this, new Observer() { // from class: com.wanos.media.view.RelaxListActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m583lambda$onCreate$2$comwanosmediaviewRelaxListActivity((List) obj);
            }
        });
        this.binding.btnBack.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.RelaxListActivity.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                RelaxListActivity.this.finish();
            }
        });
        this.binding.errorView.btnRetry.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.RelaxListActivity.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                RelaxListActivity.this.viewModel.initThemeList(false);
            }
        });
        this.mAdapter.setOnItemClickListener(new RelaxListAdapter.OnItemClickListener() { // from class: com.wanos.media.view.RelaxListActivity$$ExternalSyntheticLambda4
            @Override // com.wanos.media.adapter.RelaxListAdapter.OnItemClickListener
            public final void onItemClick(SpaceThemeBaseInfo spaceThemeBaseInfo) {
                this.f$0.m584lambda$onCreate$3$comwanosmediaviewRelaxListActivity(spaceThemeBaseInfo);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-wanos-media-view-RelaxListActivity, reason: not valid java name */
    /* synthetic */ void m581lambda$onCreate$0$comwanosmediaviewRelaxListActivity(String str) {
        this.binding.tvTitle.setText(str);
    }

    /* JADX INFO: renamed from: com.wanos.media.view.RelaxListActivity$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$entity$PageState;

        static {
            int[] iArr = new int[PageState.values().length];
            $SwitchMap$com$wanos$media$entity$PageState = iArr;
            try {
                iArr[PageState.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.EMPTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onCreate$1$com-wanos-media-view-RelaxListActivity, reason: not valid java name */
    /* synthetic */ void m582lambda$onCreate$1$comwanosmediaviewRelaxListActivity(PageState pageState) {
        int i = AnonymousClass3.$SwitchMap$com$wanos$media$entity$PageState[pageState.ordinal()];
        if (i == 1) {
            this.binding.rvRelaxList.setVisibility(0);
            this.binding.loadingView.getRoot().setVisibility(8);
            this.binding.errorView.getRoot().setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(8);
            return;
        }
        if (i == 2) {
            this.binding.rvRelaxList.setVisibility(4);
            this.binding.loadingView.getRoot().setVisibility(0);
            this.binding.errorView.getRoot().setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(8);
            return;
        }
        if (i == 3) {
            this.binding.rvRelaxList.setVisibility(4);
            this.binding.loadingView.getRoot().setVisibility(8);
            this.binding.errorView.getRoot().setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            return;
        }
        if (i != 4) {
            return;
        }
        this.binding.rvRelaxList.setVisibility(4);
        this.binding.loadingView.getRoot().setVisibility(8);
        this.binding.errorView.getRoot().setVisibility(0);
        this.binding.emptyView.getRoot().setVisibility(8);
    }

    /* JADX INFO: renamed from: lambda$onCreate$2$com-wanos-media-view-RelaxListActivity, reason: not valid java name */
    /* synthetic */ void m583lambda$onCreate$2$comwanosmediaviewRelaxListActivity(List list) {
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: renamed from: lambda$onCreate$3$com-wanos-media-view-RelaxListActivity, reason: not valid java name */
    /* synthetic */ void m584lambda$onCreate$3$comwanosmediaviewRelaxListActivity(SpaceThemeBaseInfo spaceThemeBaseInfo) {
        if (ZeroApplication.getZeroCallback().getScreenState() != 101) {
            ToastUtil.showMsg(R.string.error_portrait_use);
            return;
        }
        if (1001 == this.viewModel.getEntryWay()) {
            ZeroInfoActivity.onLaunch(this, ZeroPageMode.XIAO_QI_STANDARD, spaceThemeBaseInfo);
            Log.i("zt", "小憩内容播放次数----" + spaceThemeBaseInfo.getThemeId());
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_NAP_PLAY, spaceThemeBaseInfo.getThemeId() + "", "", "", "", 0);
        } else if (2001 == this.viewModel.getEntryWay()) {
            ZeroInfoActivity.onLaunch(this, ZeroPageMode.MING_XIANG_STANDARD, spaceThemeBaseInfo);
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MEDITE_PLAY, spaceThemeBaseInfo.getThemeId() + "", "", "", "", 0);
        } else {
            ZeroLogcatTools.w(TAG, "模板主题点击: 类型异常，EnterWay = " + spaceThemeBaseInfo.getEnterWay());
        }
    }
}
