package com.wanos.editmain.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.ActivityLiveHouseListBinding;
import com.wanos.editmain.fragment.LiveHouseFragment;
import com.wanos.media.base.WanosBaseActivity;

/* JADX INFO: loaded from: classes3.dex */
public class LiveHouseListActivity extends WanosBaseActivity {
    private ActivityLiveHouseListBinding binding;

    public static void build(Context context) {
        context.startActivity(new Intent(context, (Class<?>) LiveHouseListActivity.class));
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityLiveHouseListBinding activityLiveHouseListBindingInflate = ActivityLiveHouseListBinding.inflate(getLayoutInflater());
        this.binding = activityLiveHouseListBindingInflate;
        setContentView(activityLiveHouseListBindingInflate.getRoot());
        initView();
    }

    @Override // android.app.Activity
    public void recreate() {
        super.recreate();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    public void initView() {
        setPlayBarVisibility(8);
        setTitleText(R.string.live_house);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_view, new LiveHouseFragment()).commit();
    }
}
