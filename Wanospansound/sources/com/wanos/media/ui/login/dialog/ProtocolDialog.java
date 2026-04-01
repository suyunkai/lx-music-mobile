package com.wanos.media.ui.login.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetServiceProtocolResponse;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.SharedPreferUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.databinding.DialogProtocolBinding;

/* JADX INFO: loaded from: classes3.dex */
public class ProtocolDialog extends Dialog implements View.OnClickListener {
    public static final String TAG = "wanos:[ProtocolDialog]";
    public String content;
    private final Context context;
    private final DialogProtocolBinding dialogProtocol;
    private static final String[] titles = {"服务条款", "会员服务协议", "儿童隐私政策", "隐私政策", "兑换规则"};
    public static int SERVICE_PRO = 0;
    public static int VIP_SERVICE_PRO = 1;
    public static int CHILD_PRI_SERVICE_PRO = 2;
    public static int PRI_SERVICE_PRO = 3;
    public static int REDEEM_CODE_RULE_PRO = 4;

    public ProtocolDialog(Context context, final int type) {
        super(context, R.style.DialogStyle);
        getWindow().setBackgroundDrawableResource(R.color.p_big_bg_alpha);
        this.context = context;
        DialogProtocolBinding dialogProtocolBindingInflate = DialogProtocolBinding.inflate(getLayoutInflater());
        this.dialogProtocol = dialogProtocolBindingInflate;
        setContentView(dialogProtocolBindingInflate.getRoot());
        dialogProtocolBindingInflate.btnBack.setOnClickListener(this);
        if (type >= 0) {
            String[] strArr = titles;
            if (type < strArr.length) {
                dialogProtocolBindingInflate.tvTitle.setText(strArr[type]);
            }
        }
        Util.setTextWeight(dialogProtocolBindingInflate.tvTitle, 500);
        initVisibleIsSpoken();
        int i = type + 1;
        int protocolVersion = SharedPreferUtils.getProtocolVersion(i);
        Log.i(TAG, "获取本地协议版本 ：" + protocolVersion + "");
        WanOSRetrofitUtil.getServiceProtocol(new ResponseCallBack<GetServiceProtocolResponse>(getOwnerActivity()) { // from class: com.wanos.media.ui.login.dialog.ProtocolDialog.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetServiceProtocolResponse response) {
                if (response == null || response.data == null) {
                    return;
                }
                if (response.data.isUpdate()) {
                    ProtocolDialog.this.dialogProtocol.tvContent.setText(response.data.getContent());
                    SharedPreferUtils.saveProtocolVersion(type + 1, response.data.getVersion());
                    SharedPreferUtils.saveProtocolContent(type + 1, response.data.getContent());
                } else {
                    String protocolContent = SharedPreferUtils.getProtocolContent(type + 1);
                    if (!TextUtils.isEmpty(protocolContent)) {
                        ProtocolDialog.this.dialogProtocol.tvContent.setText(protocolContent);
                    } else {
                        SharedPreferUtils.saveProtocolContent(type + 1, response.data.getContent());
                        ProtocolDialog.this.dialogProtocol.tvContent.setText(response.data.getContent());
                    }
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                ToastUtil.showMsg(msg);
            }
        }, protocolVersion, i);
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.dialogProtocol.btnBack.setContentDescription(this.context.getResources().getString(R.string.close_back_click));
        }
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
        if (view.getId() == R.id.btn_back) {
            dismiss();
        }
    }
}
