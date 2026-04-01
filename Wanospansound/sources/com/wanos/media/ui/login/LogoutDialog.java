package com.wanos.media.ui.login;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.BaseDialog;
import com.wanos.media.databinding.DialogLogoutBinding;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class LogoutDialog extends BaseDialog implements View.OnClickListener {
    public static final String TAG = "wanos:[LogoutDialog]";
    private final Context context;
    private final DialogLogoutBinding dialogLogoutBinding;

    @Override // com.wanos.media.base.BaseDialog
    public BaseDialog.DialogShowType getType() {
        return BaseDialog.DialogShowType.Logout;
    }

    public LogoutDialog(Context context) {
        super(context, R.style.DialogStyle);
        getWindow().setBackgroundDrawableResource(R.color.p_big_bg_alpha);
        DialogLogoutBinding dialogLogoutBindingInflate = DialogLogoutBinding.inflate(getLayoutInflater());
        this.dialogLogoutBinding = dialogLogoutBindingInflate;
        this.context = context;
        setContentView(dialogLogoutBindingInflate.getRoot());
        dialogLogoutBindingInflate.tvLogoutConfirm.setOnClickListener(this);
        dialogLogoutBindingInflate.tvLogoutCancel.setOnClickListener(this);
        Util.setTextWeight(dialogLogoutBindingInflate.tvTitle, 500);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_logout_confirm) {
            Log.e(TAG, "perform logout");
            WanOSRetrofitUtil.logout(new ResponseCallBack<BaseResponse>(null) { // from class: com.wanos.media.ui.login.LogoutDialog.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    Log.e(LogoutDialog.TAG, "perform logout success");
                }
            });
            UserInfoUtil.logout();
            EventBus.getDefault().post(new LoginOrLogoutEvent(false));
            dismiss();
            return;
        }
        dismiss();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        dismiss();
    }
}
