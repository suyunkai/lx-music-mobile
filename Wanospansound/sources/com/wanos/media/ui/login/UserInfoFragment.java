package com.wanos.media.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.timepicker.TimeModel;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.UserAvatarInfo;
import com.wanos.WanosCommunication.response.ActiveCodeExchangeVipMemberResp;
import com.wanos.WanosCommunication.response.GetUserInfoResponse;
import com.wanos.WanosCommunication.response.UserAvatarListResponse;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.ShowLoginEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.DateUtil;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.WanosBaseFragment;
import com.wanos.media.databinding.FragmentUserInfoBinding;
import com.wanos.media.presenter.UserInfoPresenter;
import com.wanos.media.ui.login.activity.UserInfoAndOrderActivity;
import com.wanos.media.ui.login.dialog.CodeDialog;
import com.wanos.media.ui.login.dialog.DateDialog;
import com.wanos.media.ui.login.dialog.ProtocolDialog;
import com.wanos.media.util.AnitClick;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoFragment extends WanosBaseFragment<UserInfoPresenter> implements View.OnClickListener, UserInfoAndOrderActivity.OnBackPressedCallBack {
    private static final String TAG = "wanos:[UserInfoFragment]";
    private ArrayList<UserAvatarInfo> avas;
    private FragmentUserInfoBinding binding;
    private CodeDialog codeDialog;
    private DateDialog dateDialog;
    private DatePicker datePicker;
    private long defaultBirthday;
    private int defaultSex;
    private InputMethodManager imm;
    private View popupView;
    private PopupWindow popupWindow;
    private UserInfoAndOrderActivity requireActivity;
    private int picDefaultIndex = -1;
    private int picSelectIndex = -1;
    float[] numLeftMargin = {48.0f, 94.5f, 105.5f};
    float[] numWidthInner = {145.0f, 83.0f, 88.0f};
    private boolean isInEdit = false;
    private final View.OnClickListener clickListener = new AnitClick() { // from class: com.wanos.media.ui.login.UserInfoFragment.1
        @Override // com.wanos.media.util.AnitClick
        public void onAnitClick(View v) {
            UserInfoFragment.this.onHideSoftInput();
            if (v.getId() == R.id.iv_user_pic) {
                UserInfoFragment.this.toEditPor();
                UserInfoFragment.this.binding.ivUserInfoEdit.setClickable(true);
                return;
            }
            if (v.getId() == R.id.iv_user_info_edit) {
                UserInfoFragment.this.toEditInfo();
                UserInfoFragment.this.binding.ivUserInfoEdit.setClickable(false);
                return;
            }
            if (v.getId() == R.id.tv_info_confirm) {
                UserInfoFragment.this.save();
                UserInfoFragment.this.binding.ivUserInfoEdit.setClickable(true);
                return;
            }
            if (v.getId() == R.id.tv_info_cancel) {
                UserInfoFragment.this.toShowInfo();
                UserInfoFragment.this.binding.ivUserInfoEdit.setClickable(true);
                return;
            }
            if (v.getId() == R.id.ll_logout) {
                FragmentActivity activity = UserInfoFragment.this.getActivity();
                if (activity != null) {
                    LogoutDialog logoutDialog = new LogoutDialog(activity);
                    logoutDialog.setCancelable(true);
                    logoutDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.wanos.media.ui.login.UserInfoFragment.1.1
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            if ((UserInfoUtil.getUserInfo() == null || TextUtils.isEmpty(UserInfoUtil.getUserInfo().getToken())) && UserInfoFragment.this.getActivity() != null) {
                                UserInfoFragment.this.getActivity().finish();
                            }
                        }
                    });
                    logoutDialog.show();
                    return;
                }
                return;
            }
            if (v.getId() == R.id.ll_year || v.getId() == R.id.ll_month || v.getId() == R.id.ll_day) {
                UserInfoFragment.this.showDatePopupWindow();
                return;
            }
            if (v.getId() == R.id.ll_pay_vip) {
                UserInfoFragment.this.openWeChatPayActivity();
                return;
            }
            if (v.getId() == R.id.ll_redeem_code) {
                UserInfoFragment.this.showCodeDialog();
                return;
            }
            if (v.getId() == R.id.user_info_ll_layout || v.getId() == R.id.user_info_ll_layout1) {
                UserInfoFragment.this.onHideSoftInput();
                return;
            }
            if (v.getId() == R.id.tv_confirm_img) {
                if (UserInfoFragment.this.avas != null && UserInfoFragment.this.picSelectIndex != -1) {
                    UserInfoFragment userInfoFragment = UserInfoFragment.this;
                    userInfoFragment.picDefaultIndex = userInfoFragment.picSelectIndex;
                    final UserAvatarInfo userAvatarInfo = (UserAvatarInfo) UserInfoFragment.this.avas.get(UserInfoFragment.this.picSelectIndex);
                    if (userAvatarInfo != null) {
                        WanOSRetrofitUtil.updateAvatar(userAvatarInfo.getUserAvatarId(), new ResponseCallBack<BaseResponse>(null) { // from class: com.wanos.media.ui.login.UserInfoFragment.1.2
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(BaseResponse response) {
                                if (UserInfoFragment.this.isAdded()) {
                                    GlideUtil.setImageData(userAvatarInfo.getAvatarUrl(), UserInfoFragment.this.binding.ivUserPic, 108, 108, R.drawable.p_user_ava_d);
                                    UserInfoFragment.this.updateUserAvatar(userAvatarInfo.getAvatarUrl());
                                }
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int code, String msg) {
                                ToastUtil.showMsg(msg);
                            }
                        });
                    }
                }
                UserInfoFragment.this.toShowInfo();
            }
        }
    };

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.popupWindow.dismiss();
        }
        UserInfoAndOrderActivity userInfoAndOrderActivity = this.requireActivity;
        if (userInfoAndOrderActivity != null) {
            outState.putBoolean("isConfig", userInfoAndOrderActivity.isConfig);
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentUserInfoBinding.inflate(getLayoutInflater());
        this.imm = (InputMethodManager) getActivity().getSystemService("input_method");
        initView();
        initVisibleIsSpoken();
        refreshUserInfo();
        return this.binding.getRoot();
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.ivUserInfoEdit.setContentDescription(getResources().getString(R.string.edit_user_info_two));
            this.binding.ivUserPic.setContentDescription(getResources().getString(R.string.edit_user_head));
            this.binding.tvCheckMale.setContentDescription(getResources().getString(R.string.select_user_sex_man));
            this.binding.tvCheckFemale.setContentDescription(getResources().getString(R.string.select_user_sex_woman));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            UserInfoAndOrderActivity userInfoAndOrderActivity = (UserInfoAndOrderActivity) requireActivity();
            this.requireActivity = userInfoAndOrderActivity;
            if (userInfoAndOrderActivity.isConfig && this.codeDialog == null) {
                showCodeDialog();
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void refreshUserInfo() {
        WanOSRetrofitUtil.getUserInfo(new ResponseCallBack<GetUserInfoResponse>(null) { // from class: com.wanos.media.ui.login.UserInfoFragment.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetUserInfoResponse response) {
                if (response == null || response.data == null || !UserInfoFragment.this.isAdded()) {
                    return;
                }
                UserInfo userInfo = UserInfoUtil.getUserInfo();
                if (userInfo != null) {
                    response.data.setToken(userInfo.getToken());
                }
                UserInfoUtil.saveUserInfo(response.data);
                UserInfoFragment.this.initUserInfo(response.data);
                EventBus.getDefault().post(new UserInfoChangeEvent(response.data));
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (code == 20000 || code == 20001 || code == 20005) {
                    Log.e(UserInfoFragment.TAG, "error logout");
                    UserInfoUtil.logout();
                    UserInfoFragment.this.getActivity().finish();
                    EventBus.getDefault().post(new LoginOrLogoutEvent(false));
                    EventBus.getDefault().post(new ShowLoginEvent());
                }
            }
        });
    }

    private void initView() {
        this.binding.ivUserPic.setOnClickListener(this.clickListener);
        this.binding.ivUserInfoEdit.setOnClickListener(this.clickListener);
        this.binding.tvInfoConfirm.setOnClickListener(this.clickListener);
        this.binding.tvInfoCancel.setOnClickListener(this.clickListener);
        this.binding.tvConfirmImg.setOnClickListener(this.clickListener);
        this.binding.llYear.setOnClickListener(this.clickListener);
        this.binding.llMonth.setOnClickListener(this.clickListener);
        this.binding.llDay.setOnClickListener(this.clickListener);
        this.binding.llLogout.setOnClickListener(this.clickListener);
        this.binding.llPayVip.setOnClickListener(this.clickListener);
        this.binding.llRedeemCode.setOnClickListener(this.clickListener);
        this.binding.userInfoLlLayout.setOnClickListener(this.clickListener);
        this.binding.userInfoLlLayout1.setOnClickListener(this.clickListener);
        this.binding.tvCheckMale.setOnClickListener(this);
        this.binding.tvCheckFemale.setOnClickListener(this);
        this.binding.tvConfirmImg.setOnClickListener(this.clickListener);
        toShowInfo();
        final UserInfo userInfo = UserInfoUtil.getUserInfo();
        if (userInfo == null || TextUtils.isEmpty(userInfo.getToken())) {
            ToastUtil.showMsg("用户信息错误");
            if (getActivity() == null || !isAdded()) {
                return;
            }
            getActivity().finish();
            return;
        }
        initUserInfo(userInfo);
        if (!TextUtils.isEmpty(userInfo.getAvatar())) {
            GlideUtil.setImageData(userInfo.getAvatar(), this.binding.ivUserPic, 108, 108, R.drawable.p_user_ava_d);
        }
        WanOSRetrofitUtil.getUserAvatarList(new ResponseCallBack<UserAvatarListResponse>(getContext()) { // from class: com.wanos.media.ui.login.UserInfoFragment.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(UserAvatarListResponse response) {
                if (response == null || response.data == null || !UserInfoFragment.this.isAdded()) {
                    return;
                }
                UserInfoFragment.this.avas = response.data.list;
                final int i = 0;
                while (i < 20) {
                    View childAt = i < 10 ? UserInfoFragment.this.binding.llHeadTop.getChildAt(i * 2) : UserInfoFragment.this.binding.llHeadBottom.getChildAt((i - 10) * 2);
                    if (UserInfoFragment.this.avas.size() - 1 < i) {
                        if (childAt != null) {
                            childAt.setVisibility(4);
                        }
                    } else if (childAt != null) {
                        ImageView imageView = (ImageView) childAt.findViewById(R.id.pro);
                        String avatarUrl = ((UserAvatarInfo) UserInfoFragment.this.avas.get(i)).getAvatarUrl();
                        if (!TextUtils.isEmpty(avatarUrl) && UserInfoFragment.this.getContext() != null) {
                            GlideUtil.setImageData(avatarUrl, imageView, 87, 87, R.drawable.p_user_ava_d);
                            if (avatarUrl.equals(userInfo.getAvatar())) {
                                UserInfoFragment.this.picDefaultIndex = i;
                                UserInfoFragment.this.picSelectIndex = i;
                                ((ImageView) childAt.findViewById(R.id.iv_select)).setVisibility(0);
                                imageView.setBackgroundResource(R.drawable.p_user_ava_img_bg);
                            }
                        }
                        childAt.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.UserInfoFragment.3.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                if (UserInfoFragment.this.picSelectIndex != -1) {
                                    UserInfoFragment.this.unSelectView(UserInfoFragment.this.picSelectIndex);
                                }
                                UserInfoFragment.this.picSelectIndex = i;
                                UserInfoFragment.this.selectView(UserInfoFragment.this.picSelectIndex);
                            }
                        });
                    }
                    i++;
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                ToastUtil.showMsg(msg);
            }
        });
        initAvatarList();
        if (getActivity() instanceof UserInfoAndOrderActivity) {
            ((UserInfoAndOrderActivity) getActivity()).setOnBackPressedCallBack(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unSelectView(int picSelectIndex) {
        View childAt;
        if (picSelectIndex < 10) {
            childAt = this.binding.llHeadTop.getChildAt(picSelectIndex * 2);
        } else {
            childAt = this.binding.llHeadBottom.getChildAt((picSelectIndex - 10) * 2);
        }
        if (childAt != null) {
            ImageView imageView = (ImageView) childAt.findViewById(R.id.pro);
            ((ImageView) childAt.findViewById(R.id.iv_select)).setVisibility(8);
            imageView.setBackgroundResource(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectView(int picSelectIndex) {
        View childAt;
        if (picSelectIndex < 10) {
            childAt = this.binding.llHeadTop.getChildAt(picSelectIndex * 2);
        } else {
            childAt = this.binding.llHeadBottom.getChildAt((picSelectIndex - 10) * 2);
        }
        if (childAt != null) {
            ImageView imageView = (ImageView) childAt.findViewById(R.id.pro);
            ((ImageView) childAt.findViewById(R.id.iv_select)).setVisibility(0);
            imageView.setBackgroundResource(R.drawable.p_user_ava_img_bg);
        }
    }

    private void initAvatarList() {
        LinearLayout linearLayout = this.binding.llHeadBottom;
        LinearLayout linearLayout2 = this.binding.llHeadTop;
        for (int i = 0; i < 20; i++) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.item_avatar_img, (ViewGroup) null);
            View view = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 10);
            layoutParams.weight = 1.0f;
            if (i < 10) {
                if (i != 0) {
                    linearLayout2.addView(view, layoutParams);
                }
                linearLayout2.addView(viewInflate);
            } else {
                if (i != 10) {
                    linearLayout.addView(view, layoutParams);
                }
                linearLayout.addView(viewInflate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initUserInfo(UserInfo userInfo) {
        Util.setTextWeight(this.binding.tvUserName, 500);
        Util.setTextWeight(this.binding.tvUserName2, 500);
        Util.setTextWeight(this.binding.etNickName, 500);
        Util.setTextWeight(this.binding.etPhoneNumber, 500);
        Util.setTextWeight(this.binding.tvPhoneNumber, 500);
        Util.setTextWeight(this.binding.tvGender, 500);
        Util.setTextWeight(this.binding.tvBir, 500);
        Util.setTextWeight(this.binding.tvCheckMale, 500);
        Util.setTextWeight(this.binding.tvCheckFemale, 500);
        Util.setTextWeight(this.binding.tvBirYear, 500);
        Util.setTextWeight(this.binding.tvBirMonth, 500);
        Util.setTextWeight(this.binding.tvBirDay, 500);
        this.binding.tvUserName.setText(userInfo.getUserName());
        this.binding.tvUserName2.setText(userInfo.getUserName());
        this.binding.tvPhoneNumber.setText(userInfo.getMobile());
        this.binding.etPhoneNumber.setText(userInfo.getMobile());
        int sex = userInfo.getSex();
        this.binding.tvGender.setText(sex == 1 ? "男" : sex == 2 ? "女" : "--");
        this.defaultBirthday = userInfo.getBirthday();
        setBirthdayDate(userInfo.getBirthday());
        this.binding.etNickName.setText(userInfo.getUserName());
        this.defaultSex = sex;
        this.binding.tvCheckMale.setSelected(sex == 1);
        this.binding.tvCheckFemale.setSelected(sex == 2);
        this.binding.ivVipIcon.setVisibility(UserInfoUtil.isVipAndUnexpired() ? 0 : 8);
        this.binding.tvVipName.setVisibility(UserInfoUtil.isVipAndUnexpired() ? 0 : 8);
        updateVipInfo(userInfo);
    }

    private void updateVipInfo(UserInfo userInfo) {
        if (isAdded()) {
            if (UserInfoUtil.isVipAndUnexpired()) {
                String str = new SimpleDateFormat("yyyy年MM月dd日").format(Long.valueOf(userInfo.getVipEndTime() * 1000));
                this.binding.tvVipTime.setVisibility(0);
                this.binding.tvVipTime.setText(str);
            } else {
                this.binding.tvVipTime.setVisibility(8);
            }
            this.binding.ivVipIcon.setVisibility(UserInfoUtil.isVipAndUnexpired() ? 0 : 8);
            this.binding.tvVipTime.setVisibility(UserInfoUtil.isVipAndUnexpired() ? 0 : 8);
            this.binding.tvPayHint.setText(getText(UserInfoUtil.isVipAndUnexpired() ? R.string.p_pay_vip_con : R.string.p_pay_vip));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUserAvatar(String avatar) {
        UserInfo userInfo = UserInfoUtil.getUserInfo();
        userInfo.setAvatar(avatar);
        UserInfoUtil.saveUserInfo(userInfo);
        EventBus.getDefault().post(new UserInfoChangeEvent(userInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDatePopupWindow() {
        if (this.popupView == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.date_picker, (ViewGroup) null);
            this.popupView = viewInflate;
            DatePicker datePicker = (DatePicker) viewInflate.findViewById(R.id.data_picker);
            this.datePicker = datePicker;
            changeStyleAndAddText(datePicker);
            this.popupWindow = new PopupWindow(this.popupView, -2, -2, true);
        }
        CharSequence text = this.binding.tvBirYear.getText();
        CharSequence text2 = this.binding.tvBirMonth.getText();
        CharSequence text3 = this.binding.tvBirDay.getText();
        this.popupWindow.showAsDropDown(this.binding.tvBirYear, -dpToPx(getContext(), 163.0f), -dpToPx(getContext(), 405.0f), 53);
        this.datePicker.setMaxDate(System.currentTimeMillis());
        this.datePicker.init(Integer.parseInt(text.toString()), Integer.parseInt(text2.toString()) - 1, Integer.parseInt(text3.toString()), new DatePicker.OnDateChangedListener() { // from class: com.wanos.media.ui.login.UserInfoFragment.4
            @Override // android.widget.DatePicker.OnDateChangedListener
            public void onDateChanged(DatePicker datePicker2, int year, int month, int day) {
                UserInfoFragment.this.binding.tvBirYear.setText("" + year);
                UserInfoFragment.this.binding.tvBirMonth.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(month + 1)));
                UserInfoFragment.this.binding.tvBirDay.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(day)));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toShowInfo() {
        this.isInEdit = false;
        if (getActivity() instanceof UserInfoAndOrderActivity) {
            ((UserInfoAndOrderActivity) getActivity()).changeTitleVis(true);
        }
        this.binding.ivUserInfoEdit.setClickable(true);
        this.binding.llShowInfo.setVisibility(0);
        this.binding.llLogout.setVisibility(0);
        this.binding.llConfirm.setVisibility(8);
        this.binding.llVipInfo.setVisibility(0);
        this.binding.llEditInfo.setVisibility(8);
        this.binding.llEditHead.setVisibility(8);
        this.binding.pDivider.setBackgroundColor(getResources().getColor(R.color.p_user_divider, null));
        this.binding.ivUserInfoEdit.setBackgroundResource(R.drawable.p_user_info_edit_bg_1);
        this.binding.ivUserInfoEdit.setVisibility(0);
        this.binding.tvConfirmImg.setVisibility(8);
        backResetInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toEditInfo() {
        this.isInEdit = true;
        if (getActivity() instanceof UserInfoAndOrderActivity) {
            ((UserInfoAndOrderActivity) getActivity()).changeTitleVis(false);
        }
        this.binding.llShowInfo.setVisibility(8);
        this.binding.llLogout.setVisibility(8);
        this.binding.llConfirm.setVisibility(0);
        this.binding.llVipInfo.setVisibility(0);
        this.binding.llEditInfo.setVisibility(0);
        this.binding.llEditHead.setVisibility(8);
        this.binding.etNickName.setText(this.binding.tvUserName2.getText().toString().trim());
        this.binding.pDivider.setBackgroundColor(getResources().getColor(R.color.p_user_divider2, null));
        this.binding.ivUserInfoEdit.setBackgroundResource(R.drawable.p_user_info_edit_bg_2);
        this.binding.ivUserInfoEdit.setVisibility(0);
        this.binding.tvConfirmImg.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toEditPor() {
        this.isInEdit = true;
        if (getActivity() instanceof UserInfoAndOrderActivity) {
            ((UserInfoAndOrderActivity) getActivity()).changeTitleVis(false);
        }
        this.binding.llShowInfo.setVisibility(8);
        this.binding.llLogout.setVisibility(8);
        this.binding.llConfirm.setVisibility(8);
        this.binding.llVipInfo.setVisibility(8);
        this.binding.llEditInfo.setVisibility(8);
        this.binding.llEditHead.setVisibility(0);
        this.binding.pDivider.setBackgroundColor(getResources().getColor(R.color.p_user_divider2, null));
        this.binding.ivUserInfoEdit.setVisibility(8);
        this.binding.tvConfirmImg.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void save() {
        int i = 0;
        this.binding.tvInfoConfirm.setClickable(false);
        this.binding.tvInfoCancel.setClickable(false);
        final String strTrim = this.binding.etNickName.getText().toString().trim();
        if (this.binding.tvCheckMale.isSelected()) {
            i = 1;
        } else if (this.binding.tvCheckFemale.isSelected()) {
            i = 2;
        }
        if (i == 0) {
            ToastUtil.showMsg(R.string.nick_gender_alert);
            this.binding.tvInfoConfirm.setClickable(true);
            this.binding.tvInfoCancel.setClickable(true);
            return;
        }
        CharSequence text = this.binding.tvBirYear.getText();
        CharSequence text2 = this.binding.tvBirMonth.getText();
        CharSequence text3 = this.binding.tvBirDay.getText();
        if (TextUtils.isEmpty(strTrim)) {
            ToastUtil.showMsg(R.string.nick_name_alert);
            this.binding.tvInfoConfirm.setClickable(true);
            this.binding.tvInfoCancel.setClickable(true);
        } else if (strTrim.length() < 2 || strTrim.length() > 20) {
            ToastUtil.showMsg("昵称仅支持2-20个汉字、数字或字母");
            this.binding.tvInfoConfirm.setClickable(true);
            this.binding.tvInfoCancel.setClickable(true);
        } else {
            final long jTimeToLong = !text2.equals("--") ? DateUtil.timeToLong(((Object) text) + "-" + ((Object) text2) + "-" + ((Object) text3) + " 00:00:00") : 0L;
            final int i2 = i;
            WanOSRetrofitUtil.updateUserinfo(strTrim, i, jTimeToLong, new ResponseCallBack<BaseResponse>(getContext()) { // from class: com.wanos.media.ui.login.UserInfoFragment.5
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    if (UserInfoFragment.this.isAdded()) {
                        UserInfo userInfo = UserInfoUtil.getUserInfo();
                        userInfo.setUserName(strTrim);
                        userInfo.setSex(i2);
                        userInfo.setBirthday(jTimeToLong);
                        UserInfoUtil.saveUserInfo(userInfo);
                        EventBus.getDefault().post(new UserInfoChangeEvent(userInfo));
                        UserInfoFragment.this.binding.tvInfoConfirm.setClickable(true);
                        UserInfoFragment.this.binding.tvInfoCancel.setClickable(true);
                        UserInfoFragment.this.initUserInfo(userInfo);
                        UserInfoFragment.this.toShowInfo();
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ToastUtil.showMsg(msg);
                    UserInfoFragment.this.binding.tvInfoConfirm.setClickable(true);
                    UserInfoFragment.this.binding.tvInfoCancel.setClickable(true);
                }
            });
        }
    }

    @Override // com.wanos.media.ui.login.activity.UserInfoAndOrderActivity.OnBackPressedCallBack
    public boolean onBackPressed() {
        if (!this.isInEdit || !isAdded()) {
            return false;
        }
        onHideSoftInput();
        if (this.picDefaultIndex != -1) {
            unSelectView(this.picSelectIndex);
            int i = this.picDefaultIndex;
            this.picSelectIndex = i;
            selectView(i);
        } else {
            unSelectView(this.picSelectIndex);
        }
        toShowInfo();
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userInfoChangeEvent(UserInfoChangeEvent event) {
        UserInfo userInfo = UserInfoUtil.getUserInfo();
        if (userInfo != null) {
            updateVipInfo(userInfo);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        onHideSoftInput();
        if (v.getId() == R.id.tv_check_male) {
            this.binding.tvCheckMale.setSelected(true);
            this.binding.tvCheckFemale.setSelected(false);
        } else if (v.getId() == R.id.tv_check_female) {
            this.binding.tvCheckMale.setSelected(false);
            this.binding.tvCheckFemale.setSelected(true);
        }
    }

    private void changeStyleAndAddText(DatePicker view) {
        LinearLayout containerAndInit = getContainerAndInit(view);
        if (containerAndInit != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) containerAndInit.getLayoutParams();
            layoutParams.height = -1;
            containerAndInit.setLayoutParams(layoutParams);
            View view2 = (View) containerAndInit.getParent();
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) view2.getLayoutParams();
            layoutParams2.height = -1;
            layoutParams2.width = -1;
            view2.setLayoutParams(layoutParams2);
            containerAndInit.setGravity(16);
            containerAndInit.setPadding(0, 0, 0, 0);
            if (getContext() != null) {
                containerAndInit.addView(getTextView("日"));
                containerAndInit.addView(getTextView("月"), 2);
                containerAndInit.addView(getTextView("年"), 1);
            }
        }
    }

    private TextView getTextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(getContext().getColor(R.color.p_date_picker_text));
        textView.setTextSize(dpToPx(getContext(), 24.0f));
        textView.setText(text);
        return textView;
    }

    private LinearLayout getContainerAndInit(ViewGroup viewGroup) {
        LinearLayout containerAndInit = null;
        int i = 0;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof ViewGroup) {
                if (childAt instanceof NumberPicker) {
                    initNumberPickerWithIndex((NumberPicker) childAt, i);
                    i++;
                    ViewParent parent = childAt.getParent();
                    if (containerAndInit == null && (parent instanceof LinearLayout)) {
                        containerAndInit = (LinearLayout) parent;
                    }
                }
                if (containerAndInit == null) {
                    containerAndInit = getContainerAndInit((ViewGroup) childAt);
                }
            }
        }
        return containerAndInit;
    }

    private void initNumberPickerWithIndex(NumberPicker view, int i) {
        if (Build.VERSION.SDK_INT < 29 || getContext() == null) {
            return;
        }
        view.setSelectionDividerHeight(0);
        view.setTextColor(getContext().getColor(R.color.p_date_picker_text));
        view.setTextSize(dpToPx(getContext(), 48.0f));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.gravity = 5;
        layoutParams.height = -1;
        layoutParams.width = dpToPx(getContext(), this.numWidthInner[i]);
        layoutParams.leftMargin = dpToPx(getContext(), this.numLeftMargin[i]);
        layoutParams.rightMargin = 0;
        layoutParams.topMargin = 0;
        layoutParams.bottomMargin = 0;
        view.setGravity(5);
        view.setPadding(0, 0, dpToPx(getContext(), 16.0f), 0);
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCodeDialog() {
        CodeDialog codeDialogNewInstance = CodeDialog.newInstance();
        this.codeDialog = codeDialogNewInstance;
        codeDialogNewInstance.setFm(getParentFragmentManager());
        this.codeDialog.setOnClickDialogListener(new CodeDialog.OnClickListener() { // from class: com.wanos.media.ui.login.UserInfoFragment.6
            @Override // com.wanos.media.ui.login.dialog.CodeDialog.OnClickListener
            public void onLeftClick(String content) {
                WanOSRetrofitUtil.activeCodeExchange(content, new ResponseCallBack<ActiveCodeExchangeVipMemberResp>(UserInfoFragment.this.getActivity()) { // from class: com.wanos.media.ui.login.UserInfoFragment.6.1
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(ActiveCodeExchangeVipMemberResp response) {
                        if (UserInfoFragment.this.isAdded()) {
                            if (response.getData() != null && response.getData().isExchangeResult()) {
                                ToastUtil.showMsg(UserInfoFragment.this.getString(R.string.p_redeem_code_success_text));
                                UserInfoFragment.this.codeDialog.dismissAllowingStateLoss();
                                UserInfo userInfo = UserInfoUtil.getUserInfo();
                                Log.i("UserInfo：", userInfo.toString());
                                userInfo.setVip(response.getData().isExchangeResult());
                                userInfo.setVipStartTime(response.getData().getStartTime());
                                userInfo.setVipEndTime(response.getData().getEndTime());
                                UserInfoUtil.saveUserInfo(userInfo);
                                EventBus.getDefault().post(new UserInfoChangeEvent(userInfo));
                                return;
                            }
                            ToastUtil.showMsg(UserInfoFragment.this.getString(R.string.p_redeem_code_error_text));
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                        ToastUtil.showMsg(msg);
                    }
                });
            }

            @Override // com.wanos.media.ui.login.dialog.CodeDialog.OnClickListener
            public void onCloseClick() {
                if (UserInfoFragment.this.requireActivity != null) {
                    UserInfoFragment.this.requireActivity.isConfig = false;
                }
            }

            @Override // com.wanos.media.ui.login.dialog.CodeDialog.OnClickListener
            public void onProtocolClick(Context context) {
                UserInfoFragment.this.initProtocolDialog(context);
            }
        });
        UserInfoAndOrderActivity userInfoAndOrderActivity = this.requireActivity;
        if (userInfoAndOrderActivity != null) {
            userInfoAndOrderActivity.isConfig = true;
        }
        this.codeDialog.showDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initProtocolDialog(Context context) {
        if (context != null) {
            ProtocolDialog protocolDialog = new ProtocolDialog(context, ProtocolDialog.REDEEM_CODE_RULE_PRO);
            protocolDialog.setCancelable(true);
            protocolDialog.show();
        }
    }

    public static int dpToPx(Context context, float dp) {
        if (context == null) {
            return 0;
        }
        return (int) TypedValue.applyDimension(1, dp, context.getResources().getDisplayMetrics());
    }

    private void backResetInfo() {
        this.binding.tvCheckMale.setSelected(this.defaultSex == 1);
        this.binding.tvCheckFemale.setSelected(this.defaultSex == 2);
        setBirthdayDate(this.defaultBirthday);
    }

    private void setBirthdayDate(long defaultBirthday) {
        if (defaultBirthday != 0) {
            String str = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(defaultBirthday));
            this.binding.tvBir.setText(str);
            String[] strArrSplit = str.split("-");
            if (strArrSplit.length > 2) {
                this.binding.tvBirYear.setText(strArrSplit[0]);
                this.binding.tvBirMonth.setText(strArrSplit[1]);
                this.binding.tvBirDay.setText(strArrSplit[2]);
                return;
            }
            return;
        }
        this.binding.tvBir.setText("--");
        this.binding.tvBirYear.setText("2000");
        this.binding.tvBirMonth.setText("01");
        this.binding.tvBirDay.setText("01");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHideSoftInput() {
        if (getActivity().getCurrentFocus() == null || getActivity().getCurrentFocus().getWindowToken() == null) {
            return;
        }
        hideSoftInput();
    }

    private void hideSoftInput() {
        FragmentUserInfoBinding fragmentUserInfoBinding = this.binding;
        if (fragmentUserInfoBinding == null || !fragmentUserInfoBinding.etNickName.hasFocus()) {
            return;
        }
        this.imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
        this.binding.etNickName.clearFocus();
    }

    public FragmentUserInfoBinding getBinding() {
        return this.binding;
    }
}
