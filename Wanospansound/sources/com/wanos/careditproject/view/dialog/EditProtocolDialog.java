package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.response.GetServiceProtocolResponse;
import com.wanos.careditproject.data.repo.CreatorRetrofitUtil;
import com.wanos.careditproject.databinding.DialogEditProtocolBinding;
import com.wanos.commonlibrary.R;
import com.wanos.commonlibrary.utils.SharedPreferUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;

/* JADX INFO: loaded from: classes3.dex */
public class EditProtocolDialog extends Dialog implements View.OnClickListener {
    public String content;
    private Context context;
    private final DialogEditProtocolBinding dialogProtocol;
    private static String[] titles = {"服务条款", "会员服务协议", "儿童隐私政策", "隐私政策", "兑换规则"};
    public static int SERVICE_PRO = 0;
    public static int VIP_SERVICE_PRO = 1;
    public static int CHILD_PRI_SERVICE_PRO = 2;
    public static int PRI_SERVICE_PRO = 3;
    public static int REDEEM_CODE_RULE_PRO = 4;

    public EditProtocolDialog(Context context, final int i) {
        super(context, R.style.DialogStyle);
        getWindow().setBackgroundDrawableResource(com.wanos.careditproject.R.color.p_big_bg_alpha);
        this.context = context;
        DialogEditProtocolBinding dialogEditProtocolBindingInflate = DialogEditProtocolBinding.inflate(getLayoutInflater());
        this.dialogProtocol = dialogEditProtocolBindingInflate;
        setContentView(dialogEditProtocolBindingInflate.getRoot());
        dialogEditProtocolBindingInflate.btnBack.setOnClickListener(this);
        dialogEditProtocolBindingInflate.tvTitle.setText("发布素材服务协议");
        Util.setTextWeight(dialogEditProtocolBindingInflate.tvTitle, 500);
        Log.e("获取本地协议版本 ：", SharedPreferUtils.getProtocolVersion(i + 1) + "");
        CreatorRetrofitUtil.getProjectPublishContract(new ResponseCallBack<GetServiceProtocolResponse>(getOwnerActivity()) { // from class: com.wanos.careditproject.view.dialog.EditProtocolDialog.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetServiceProtocolResponse getServiceProtocolResponse) {
                if (getServiceProtocolResponse == null || getServiceProtocolResponse.data == null) {
                    return;
                }
                EditProtocolDialog.this.dialogProtocol.tvContent.setText(getServiceProtocolResponse.data.getContent());
                SharedPreferUtils.saveProtocolVersion(i + 1, getServiceProtocolResponse.data.getVersion());
                SharedPreferUtils.saveProtocolContent(i + 1, getServiceProtocolResponse.data.getContent());
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i2, String str) {
                ToastUtil.showMsg(str);
            }
        });
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
        if (view.getId() == com.wanos.careditproject.R.id.btn_back) {
            dismiss();
        }
    }
}
