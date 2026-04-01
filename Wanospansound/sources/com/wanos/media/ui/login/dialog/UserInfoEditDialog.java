package com.wanos.media.ui.login.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.DateUtil;
import com.wanos.commonlibrary.utils.SharedPreferUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.databinding.DialogUserInfoEditBinding;
import com.wanos.media.ui.login.dialog.DateDialog;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoEditDialog extends Dialog implements View.OnClickListener, DateDialog.DateSelectListener {
    private final Context context;
    private DateDialog dateDialog;
    private boolean isSendRequesting;
    private OnUserChangeClickListener onUserChangeClickListener;
    private final DialogUserInfoEditBinding userInfoEditBinding;

    public interface OnUserChangeClickListener {
        void OnUserChangeClickListener(UserInfo userInfo);
    }

    public UserInfoEditDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.isSendRequesting = false;
        this.context = context;
        DialogUserInfoEditBinding dialogUserInfoEditBindingInflate = DialogUserInfoEditBinding.inflate(getLayoutInflater());
        this.userInfoEditBinding = dialogUserInfoEditBindingInflate;
        setContentView(dialogUserInfoEditBindingInflate.getRoot());
        findViewById(R.id.bt_save).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.et_birthday_year).setOnClickListener(this);
        findViewById(R.id.et_birthday_month).setOnClickListener(this);
        findViewById(R.id.et_birthday_day).setOnClickListener(this);
        dialogUserInfoEditBindingInflate.etNickName.setFilters(new InputFilter[]{new InputFilter() { // from class: com.wanos.media.ui.login.dialog.UserInfoEditDialog.1
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (Pattern.compile("[0-9a-zA-Z|一-龥]+").matcher(source.toString()).matches()) {
                    return null;
                }
                return "";
            }
        }, new InputFilter.LengthFilter(6)});
    }

    public OnUserChangeClickListener getOnUserChangeClickListener() {
        return this.onUserChangeClickListener;
    }

    public void setOnUserChangeClickListener(OnUserChangeClickListener onUserChangeClickListener) {
        this.onUserChangeClickListener = onUserChangeClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_save) {
            save();
            return;
        }
        if (id == R.id.et_birthday_year || id == R.id.et_birthday_month || id == R.id.et_birthday_day) {
            showDatePopupWindow();
        } else if (id == R.id.btn_back) {
            cancel();
        }
    }

    private void save() {
        if (this.isSendRequesting) {
            return;
        }
        final String string = this.userInfoEditBinding.etNickName.getText().toString();
        int i = (!this.userInfoEditBinding.rbGanderMan.isChecked() && this.userInfoEditBinding.rbGanderWomen.isChecked()) ? 2 : 1;
        CharSequence text = this.userInfoEditBinding.etBirthdayYear.getText();
        CharSequence text2 = this.userInfoEditBinding.etBirthdayMonth.getText();
        CharSequence text3 = this.userInfoEditBinding.etBirthdayDay.getText();
        if (TextUtils.isEmpty(string)) {
            ToastUtil.showMsg(R.string.nick_name_alert);
            return;
        }
        if (string.length() < 2 || string.length() > 6) {
            ToastUtil.showMsg("昵称仅支持2-6个汉字、数字或字母");
            return;
        }
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(text2) || TextUtils.isEmpty(text3)) {
            ToastUtil.showMsg(R.string.please_set_birthday);
            return;
        }
        final long jTimeToLong = DateUtil.timeToLong(((Object) text) + "-" + ((Object) text2) + "-" + ((Object) text3) + " 00:00:00");
        this.isSendRequesting = true;
        final int i2 = i;
        WanOSRetrofitUtil.updateUserinfo(string, i, jTimeToLong, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.media.ui.login.dialog.UserInfoEditDialog.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse response) {
                UserInfoEditDialog.this.isSendRequesting = false;
                UserInfo personalInfo = SharedPreferUtils.getPersonalInfo();
                personalInfo.setUserName(string);
                personalInfo.setSex(i2);
                personalInfo.setBirthday(jTimeToLong);
                SharedPreferUtils.savePersonalInfo(personalInfo);
                if (UserInfoEditDialog.this.onUserChangeClickListener != null) {
                    UserInfoEditDialog.this.onUserChangeClickListener.OnUserChangeClickListener(personalInfo);
                }
                EventBus.getDefault().post(new UserInfoChangeEvent(personalInfo));
                UserInfoEditDialog.this.cancel();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                UserInfoEditDialog.this.isSendRequesting = false;
                ToastUtil.showMsg(msg);
                if (code == 20000 || code == 20001 || code == 20005) {
                    UserInfoEditDialog.this.dismiss();
                }
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(false);
            WindowInsetsController insetsController = getWindow().getInsetsController();
            insetsController.hide(WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(2);
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
        super.show();
        updateView();
    }

    private void updateView() {
        UserInfo personalInfo = SharedPreferUtils.getPersonalInfo();
        if (personalInfo != null) {
            String userId = personalInfo.getUserId();
            String userName = personalInfo.getUserName();
            int sex = personalInfo.getSex();
            long birthday = personalInfo.getBirthday();
            this.userInfoEditBinding.tvIdValue.setText(userId);
            if (!TextUtils.isEmpty(userName)) {
                this.userInfoEditBinding.etNickName.setText(userName);
            }
            if (sex == 1) {
                this.userInfoEditBinding.rbGanderMan.setChecked(true);
            } else if (sex == 2) {
                this.userInfoEditBinding.rbGanderWomen.setChecked(true);
            }
            if (birthday > 0) {
                String[] strArrSplit = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(birthday)).split("-");
                if (strArrSplit.length > 2) {
                    this.userInfoEditBinding.etBirthdayYear.setText(strArrSplit[0]);
                    this.userInfoEditBinding.etBirthdayMonth.setText(strArrSplit[1]);
                    this.userInfoEditBinding.etBirthdayDay.setText(strArrSplit[2]);
                }
            }
        }
    }

    private void showDatePopupWindow() {
        if (this.dateDialog == null) {
            DateDialog dateDialog = new DateDialog(getContext(), R.style.Dialog);
            this.dateDialog = dateDialog;
            dateDialog.setDateSelectListener(this);
        }
        CharSequence text = this.userInfoEditBinding.etBirthdayYear.getText();
        CharSequence text2 = this.userInfoEditBinding.etBirthdayMonth.getText();
        CharSequence text3 = this.userInfoEditBinding.etBirthdayDay.getText();
        this.dateDialog.showAsDropDown(this.userInfoEditBinding.etBirthdayYear, 0, -Util.dip2px(getContext(), 293.0f), (TextUtils.isEmpty(text) || TextUtils.isEmpty(text2) || TextUtils.isEmpty(text3)) ? 0L : DateUtil.timeToLong(((Object) text) + "-" + ((Object) text2) + "-" + ((Object) text3) + " 00:00:00"));
    }

    @Override // com.wanos.media.ui.login.dialog.DateDialog.DateSelectListener
    public void onDateSelectListener(long date) {
        String[] strArrSplit = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(date)).split("-");
        if (strArrSplit.length > 2) {
            this.userInfoEditBinding.etBirthdayYear.setText(strArrSplit[0]);
            this.userInfoEditBinding.etBirthdayMonth.setText(strArrSplit[1]);
            this.userInfoEditBinding.etBirthdayDay.setText(strArrSplit[2]);
        }
    }
}
