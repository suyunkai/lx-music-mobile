package com.wanos.media.ui.login.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.util.LogUtils;
import com.wanos.WanosCommunication.bean.AdDialogInfoBean;
import com.wanos.WanosCommunication.bean.GoodsInfo;
import com.wanos.WanosCommunication.bean.OrderCreatePayBean;
import com.wanos.WanosCommunication.bean.OrderInfoBean;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.SizeMode;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.GlideOptions;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.commonlibrary.utils.QRCodeUtils;
import com.wanos.commonlibrary.utils.ReStartUtil;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.BaseAdDialog;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.ActivityMemberPayBinding;
import com.wanos.media.databinding.LoadingQrCodeBinding;
import com.wanos.media.presenter.MemberPayPresenter;
import com.wanos.media.ui.advertise.dialog.AdvertiseDialog;
import com.wanos.media.ui.login.dialog.ProtocolDialog;
import com.wanos.media.util.LoadingUtil;
import com.wanos.media.util.PollUtils;
import com.wanos.media.view.MemberPayView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MemberPayActivity extends WanosBaseActivity<MemberPayPresenter> implements MemberPayView, View.OnClickListener {
    public static int isMusicOrBook;
    private int activityId;
    private int afterAction;
    private Animation animation;
    private ActivityMemberPayBinding binding;
    private LoadingController controller;
    private AdvertiseDialog dialog;
    private long expireTime;
    private int from;
    private String image;
    private ReStartUtil instance;
    private int isAuto;
    private String jumpUrl;
    private OrderInfoBean orderInfo;
    private String orderNo;
    private MemberPayPresenter presenter;
    private ProtocolDialog protocolDialog;
    private LoadingQrCodeBinding qrCodeBinding;
    private int source;
    private UserInfo userInfo;
    private List<GoodsInfo> goodsInfoList = new ArrayList();
    private final List<BaseAdDialog> adDialogList = new ArrayList();

    public static Intent getIntent(Context context) {
        return new Intent(context, (Class<?>) MemberPayActivity.class);
    }

    public static Intent getIntent(Context context, int from, int source) {
        Intent intent = new Intent(context, (Class<?>) MemberPayActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("source", source);
        return intent;
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.e(Integer.valueOf(isMusicOrBook));
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLOSE_VIP_PAY_WINDOW, "", "", "", "", 0);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ProtocolDialog protocolDialog = this.protocolDialog;
        if (protocolDialog != null && protocolDialog.isShowing()) {
            this.protocolDialog.dismiss();
        }
        outState.putString("image", this.image);
        outState.putString("jumpUrl", this.jumpUrl);
        outState.putInt("activityId", this.activityId);
        outState.putInt("afterAction", this.afterAction);
        outState.putInt("isAuto", this.isAuto);
        outState.putInt("selectedNum", this.from);
    }

    @Override // com.wanos.media.base.WanosBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.getData() != null) {
            this.from = Integer.parseInt(intent.getData().getQueryParameter("from"));
            this.source = -1;
        } else {
            this.from = intent.getIntExtra("from", -1);
            this.source = intent.getIntExtra("source", 1);
        }
        MemberPayPresenter memberPayPresenter = new MemberPayPresenter(this);
        this.presenter = memberPayPresenter;
        memberPayPresenter.attachView(this);
        this.binding = ActivityMemberPayBinding.inflate(getLayoutInflater());
        this.qrCodeBinding = LoadingQrCodeBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.instance = ReStartUtil.getInstance();
        initView();
        initVisibleIsSpoken();
        LoadingUtil.showQrLoading(this.controller, this.qrCodeBinding, this.animation, 1);
        init(savedInstanceState);
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.binding.memberPayTvAgreement.setContentDescription(getResources().getString(R.string.open_five_protocol));
            ViewCompat.setAccessibilityDelegate(this.binding.memberPayTvAgreement, new AccessibilityDelegateCompat() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    MemberPayActivity.this.initDialog();
                    return super.performAccessibilityAction(host, action, args);
                }
            });
        }
    }

    private void init(Bundle savedInstanceState) {
        if (!UserInfoUtil.isLogin()) {
            showLoginDialog(new DialogInterface.OnDismissListener() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f$0.m506xeaff4f7(dialogInterface);
                }
            }, true);
        } else if (savedInstanceState == null) {
            startReq();
        }
    }

    /* JADX INFO: renamed from: lambda$init$0$com-wanos-media-ui-login-activity-MemberPayActivity, reason: not valid java name */
    /* synthetic */ void m506xeaff4f7(DialogInterface dialogInterface) {
        if (UserInfoUtil.isLogin()) {
            initTextView();
            startReq();
        }
    }

    private void startReq() {
        this.adDialogList.clear();
        this.presenter.getAdDialogInfo(4, this.instance.isMemberRestart());
        this.presenter.requestGetMemberGoods();
    }

    @Override // com.wanos.media.base.WanosBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.image = savedInstanceState.getString("image");
        this.jumpUrl = savedInstanceState.getString("jumpUrl");
        this.activityId = savedInstanceState.getInt("activityId", 0);
        this.afterAction = savedInstanceState.getInt("afterAction", 0);
        this.isAuto = savedInstanceState.getInt("isAuto", 0);
        this.from = savedInstanceState.getInt("selectedNum", -1);
        String str = this.image;
        if (str != null && !TextUtils.isEmpty(str) && this.dialog == null && this.source != 2) {
            AdDialogInfoBean adDialogInfoBean = new AdDialogInfoBean();
            adDialogInfoBean.setImage(this.image);
            adDialogInfoBean.setJumpUrl(this.jumpUrl);
            adDialogInfoBean.setActivityId(this.activityId);
            adDialogInfoBean.setAfterAction(this.afterAction);
            adDialogInfoBean.setIsAuto(this.isAuto);
            AdvertiseDialog advertiseDialogShowAdDialog = showAdDialog(adDialogInfoBean);
            this.dialog = advertiseDialogShowAdDialog;
            advertiseDialogShowAdDialog.showDialog();
        }
        if (this.from != -1) {
            this.presenter.requestGetMemberGoods();
        }
    }

    private void initView() {
        setPlayBarVisibility(8);
        initTextView();
        initLoadingView();
        initListener();
    }

    private void initLoadingView() {
        this.animation = AnimationUtils.loadAnimation(this, R.anim.loading_anim);
        this.controller = LoadingUtil.initQRLoadingView(getApplicationContext(), this.qrCodeBinding, this.binding.memberPayCvQrImBg);
    }

    private void initQrCodeView(String address) {
        Bitmap bitmapCreateQRCode = QRCodeUtils.createQRCode(address, Util.dip2px(this, 320.0f), Util.dip2px(this, 320.0f), Util.dip2px(this, 1.0f), null);
        if (bitmapCreateQRCode != null) {
            this.binding.memberPayImQr.setImageBitmap(bitmapCreateQRCode);
            LoadingUtil.hideQrLoading(this.controller, this.qrCodeBinding);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDialog() {
        ProtocolDialog protocolDialog = new ProtocolDialog(this, ProtocolDialog.VIP_SERVICE_PRO);
        this.protocolDialog = protocolDialog;
        protocolDialog.setCancelable(true);
        this.protocolDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AdvertiseDialog showAdDialog(final AdDialogInfoBean bean) {
        AdvertiseDialog advertiseDialogNewInstance = AdvertiseDialog.newInstance();
        advertiseDialogNewInstance.setFm(getSupportFragmentManager());
        if (bean != null) {
            advertiseDialogNewInstance.setImageUrl(bean.getImage());
        }
        advertiseDialogNewInstance.setOnLeftClickListener(new AdvertiseDialog.OnLeftClickListener() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity.2
            @Override // com.wanos.media.ui.advertise.dialog.AdvertiseDialog.OnLeftClickListener
            public void onConfirm(Context context) {
                MemberPayActivity.this.image = null;
                AdDialogInfoBean adDialogInfoBean = bean;
                if (adDialogInfoBean == null || TextUtils.isEmpty(adDialogInfoBean.getJumpUrl())) {
                    MemberPayActivity.this.adConfirmClick(bean);
                } else {
                    MemberPayActivity memberPayActivity = MemberPayActivity.this;
                    memberPayActivity.jumpUrlInApp(memberPayActivity, bean.getJumpUrl());
                }
            }

            @Override // com.wanos.media.ui.advertise.dialog.AdvertiseDialog.OnLeftClickListener
            public void onCancel() {
                MemberPayActivity.this.image = null;
            }
        });
        return advertiseDialogNewInstance;
    }

    private void initTextView() {
        this.userInfo = UserInfoUtil.getUserInfo();
        Util.setTextWeight(this.binding.memberPayTvHint, 500);
        Util.setTextWeight(this.binding.memberPayTvTitleName, 500);
        setTitleText(getString(R.string.user_info_title));
        String string = getString(R.string.member_pay_agreement_text);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ClickableSpan() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                MemberPayActivity.this.initDialog();
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, 5, string.length(), 18);
        spannableString.setSpan(new ForegroundColorSpan(getColor(R.color.member_pay_agreement_text_color)), 5, string.length(), 18);
        this.binding.memberPayTvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        this.binding.memberPayTvAgreement.setHighlightColor(getColor(android.R.color.transparent));
        this.binding.memberPayTvAgreement.setText(spannableString);
        if (!TextUtils.isEmpty(this.userInfo.getAvatar())) {
            GlideUtil.setImageData(this.userInfo.getAvatar(), this.binding.memberPayImUserImg, 80, 80, R.drawable.p_user_ava_d);
        }
        this.binding.memberPayTvTitleName.setText(this.userInfo.getUserName() == null ? "" : this.userInfo.getUserName());
        TextView textView = this.binding.memberPayTvTitleBehind;
        Object[] objArr = new Object[1];
        objArr[0] = getString(this.userInfo.isVip() ? R.string.member_pay_title_renew_text : R.string.member_pay_title_open_text);
        textView.setText(getString(R.string.member_pay_title_behind_text, objArr));
    }

    private void initListener() {
        this.binding.includeMouthCard.includeSelectCard.setOnClickListener(this);
        this.binding.includeSeasonCard.includeSelectCard.setOnClickListener(this);
        this.binding.includeYearCard.includeSelectCard.setOnClickListener(this);
        this.qrCodeBinding.qrLoadingIm.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m507xa93c0b4c(view);
            }
        });
        PollUtils.getInstance().setMemberPayPollListener(new AnonymousClass4());
    }

    /* JADX INFO: renamed from: lambda$initListener$1$com-wanos-media-ui-login-activity-MemberPayActivity, reason: not valid java name */
    /* synthetic */ void m507xa93c0b4c(View view) {
        if (LoadingUtil.STATE == 2 || LoadingUtil.STATE == 3) {
            reLoad();
        }
    }

    /* JADX INFO: renamed from: com.wanos.media.ui.login.activity.MemberPayActivity$4, reason: invalid class name */
    class AnonymousClass4 implements PollUtils.MemberPayPollListener {
        AnonymousClass4() {
        }

        @Override // com.wanos.media.util.PollUtils.MemberPayPollListener
        public void timeOut() {
            MemberPayActivity.this.refreshUi(R.drawable.ic_refresh, R.string.qr_error_timeout, 3);
        }

        @Override // com.wanos.media.util.PollUtils.MemberPayPollListener
        public void paySuccess() {
            MemberPayActivity.this.refreshUi(R.drawable.ic_pay_finish, R.string.qr_pay_right, 4);
            new Handler().postDelayed(new Runnable() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m509x8f7ece32();
                }
            }, 1000L);
        }

        /* JADX INFO: renamed from: lambda$paySuccess$0$com-wanos-media-ui-login-activity-MemberPayActivity$4, reason: not valid java name */
        /* synthetic */ void m509x8f7ece32() {
            MemberPayActivity.this.finish();
        }
    }

    @Override // com.wanos.media.base.WanosBaseActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        PollUtils.getInstance().setMemberPayPollListener(null);
    }

    private void initInCardView() {
        int size = this.goodsInfoList.size();
        if (size == 1) {
            initMouthText(this.goodsInfoList.get(0).getGoodsName(), this.goodsInfoList.get(0).getGoodsPrice() + "", this.goodsInfoList.get(0).getOriginPrice());
            return;
        }
        if (size == 2) {
            initMouthText(this.goodsInfoList.get(0).getGoodsName(), this.goodsInfoList.get(0).getGoodsPrice() + "", this.goodsInfoList.get(0).getOriginPrice());
            initSeasonText(this.goodsInfoList.get(1).getGoodsName(), this.goodsInfoList.get(1).getGoodsPrice() + "", this.goodsInfoList.get(1).getOriginPrice());
        } else {
            initMouthText(this.goodsInfoList.get(0).getGoodsName(), this.goodsInfoList.get(0).getGoodsPrice() + "", this.goodsInfoList.get(0).getOriginPrice());
            initSeasonText(this.goodsInfoList.get(1).getGoodsName(), this.goodsInfoList.get(1).getGoodsPrice() + "", this.goodsInfoList.get(1).getOriginPrice());
            initYearText(this.goodsInfoList.get(2).getGoodsName(), this.goodsInfoList.get(2).getGoodsPrice() + "", this.goodsInfoList.get(2).getOriginPrice());
        }
    }

    private void reLoad() {
        int i;
        if (this.binding.includeMouthCard.includeSelectCard.isSelected()) {
            i = 0;
        } else {
            i = this.binding.includeSeasonCard.includeSelectCard.isSelected() ? 1 : 2;
        }
        LoadingUtil.showQrLoading(this.controller, this.qrCodeBinding, this.animation, 1);
        if (this.goodsInfoList.size() == 0) {
            this.presenter.requestGetMemberGoods();
        } else {
            this.presenter.createVipMember(this.goodsInfoList.get(i).getGoodsNo(), this.goodsInfoList.get(i).getGoodsPrice(), this.goodsInfoList.get(i).getGoodsName(), this.goodsInfoList.get(i).getOriginPrice());
        }
    }

    private void initMouthText(String name, String price, float originPrice) {
        this.binding.includeMouthCard.getRoot().setVisibility(0);
        this.binding.includeMouthCard.payTvTitle.setText(name);
        this.binding.includeMouthCard.payTvPrice.setText(price);
        this.binding.includeMouthCard.payTvOriginalPrice.setVisibility(originPrice != 0.0f ? 0 : 8);
        if (originPrice != 0.0f) {
            this.binding.includeMouthCard.payTvOriginalPrice.setText(initOriginalPrice(getString(R.string.include_pay_original_price_text, new Object[]{originPrice + ""})));
        }
    }

    private void initSeasonText(String name, String price, float originPrice) {
        this.binding.includeSeasonCard.getRoot().setVisibility(0);
        this.binding.includeSeasonCard.payTvTitle.setText(name);
        this.binding.includeSeasonCard.payTvPrice.setText(price);
        this.binding.includeSeasonCard.payTvOriginalPrice.setVisibility(originPrice != 0.0f ? 0 : 8);
        if (originPrice != 0.0f) {
            this.binding.includeSeasonCard.payTvOriginalPrice.setText(initOriginalPrice(getString(R.string.include_pay_original_price_text, new Object[]{originPrice + ""})));
        }
    }

    private void initYearText(String name, String price, float originPrice) {
        this.binding.includeYearCard.getRoot().setVisibility(0);
        this.binding.includeYearCard.payTvTitle.setText(name);
        this.binding.includeYearCard.payTvPrice.setText(price);
        this.binding.includeYearCard.payTvOriginalPrice.setVisibility(originPrice != 0.0f ? 0 : 8);
        if (originPrice != 0.0f) {
            this.binding.includeYearCard.payTvOriginalPrice.setText(initOriginalPrice(getString(R.string.include_pay_original_price_text, new Object[]{originPrice + ""})));
        }
    }

    private SpannableString initOriginalPrice(String price) {
        SpannableString spannableString = new SpannableString(price);
        spannableString.setSpan(new StrikethroughSpan(), 3, price.length(), 18);
        return spannableString;
    }

    private void initSelectCard() {
        this.binding.includeMouthCard.includeSelectCard.setSelected(false);
        this.binding.includeSeasonCard.includeSelectCard.setSelected(false);
        this.binding.includeYearCard.includeSelectCard.setSelected(false);
    }

    private void initSelectIcon() {
        this.binding.includeMouthCard.payImSelected.setVisibility(4);
        this.binding.includeSeasonCard.payImSelected.setVisibility(4);
        this.binding.includeYearCard.payImSelected.setVisibility(4);
    }

    @Override // com.wanos.media.view.IBaseView
    public void showLoading() {
        LoadingUtil.showQrLoading(this.controller, this.qrCodeBinding, this.animation, 1);
    }

    @Override // com.wanos.media.view.IBaseView
    public void hideLoading() {
        LoadingUtil.hideQrLoading(this.controller, this.qrCodeBinding);
    }

    @Override // com.wanos.media.view.IBaseView
    public void showFailOrError(String msg) {
        if (this.goodsInfoList.size() == 0) {
            ToastUtil.showMsg(msg);
        }
        LoadingUtil.showQrError(this.controller, this.qrCodeBinding, R.drawable.ic_refresh, getString(R.string.qr_error_failed), 2);
    }

    private void initMouthCard(String price) {
        initSelectCard();
        initSelectIcon();
        this.binding.includeMouthCard.includeSelectCard.setSelected(true);
        this.binding.includeMouthCard.payImSelected.setVisibility(0);
        this.binding.memberPayTvHint.setText(getString(R.string.member_pay_hint_text, new Object[]{price}));
    }

    private void initSeasonCard(String price) {
        initSelectCard();
        initSelectIcon();
        this.binding.includeSeasonCard.includeSelectCard.setSelected(true);
        this.binding.includeSeasonCard.payImSelected.setVisibility(0);
        this.binding.memberPayTvHint.setText(getString(R.string.member_pay_hint_text, new Object[]{price}));
    }

    private void initYearCard(String price) {
        initSelectCard();
        initSelectIcon();
        this.binding.includeYearCard.includeSelectCard.setSelected(true);
        this.binding.includeYearCard.payImSelected.setVisibility(0);
        this.binding.memberPayTvHint.setText(getString(R.string.member_pay_hint_text, new Object[]{price}));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.include_mouth_card /* 2131362366 */:
                if (!this.binding.includeMouthCard.includeSelectCard.isSelected()) {
                    this.from = this.goodsInfoList.get(0).getGoodsId();
                    initMouthCard(this.goodsInfoList.get(0).getGoodsPrice() + "");
                    reLoad();
                }
                break;
            case R.id.include_season_card /* 2131362367 */:
                if (!this.binding.includeSeasonCard.includeSelectCard.isSelected()) {
                    this.from = this.goodsInfoList.get(1).getGoodsId();
                    initSeasonCard(this.goodsInfoList.get(1).getGoodsPrice() + "");
                    reLoad();
                }
                break;
            case R.id.include_year_card /* 2131362369 */:
                if (!this.binding.includeYearCard.includeSelectCard.isSelected()) {
                    this.from = this.goodsInfoList.get(2).getGoodsId();
                    initYearCard(this.goodsInfoList.get(2).getGoodsPrice() + "");
                    reLoad();
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUi(final int icon, final int msg, final int type) {
        runOnUiThread(new Runnable() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m508xb19e989e(icon, msg, type);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$refreshUi$2$com-wanos-media-ui-login-activity-MemberPayActivity, reason: not valid java name */
    /* synthetic */ void m508xb19e989e(int i, int i2, int i3) {
        LoadingUtil.showQrError(this.controller, this.qrCodeBinding, i, getString(i2), i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adConfirmClick(AdDialogInfoBean bean) {
        if (!UserInfoUtil.isLogin()) {
            if (bean.getIsAuto() == 2) {
                showLoginDialog(bean.getActivityId(), "");
                return;
            } else {
                showLoginDialog();
                return;
            }
        }
        if (bean.getIsAuto() == 2) {
            this.presenter.giveVipMember(bean.getActivityId());
        }
    }

    @Override // com.wanos.media.view.MemberPayView
    public void getVipMemberGoodsData(List<GoodsInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.goodsInfoList = list;
        initInCardView();
        int i = 2;
        if (this.goodsInfoList.size() >= 2 && this.from == this.goodsInfoList.get(1).getGoodsId()) {
            this.from = this.goodsInfoList.get(1).getGoodsId();
            initSeasonCard(this.goodsInfoList.get(1).getGoodsPrice() + "");
            i = 1;
        } else if (this.goodsInfoList.size() >= 3 && this.from == this.goodsInfoList.get(2).getGoodsId()) {
            this.from = this.goodsInfoList.get(2).getGoodsId();
            initYearCard(this.goodsInfoList.get(2).getGoodsPrice() + "");
        } else {
            i = 0;
            this.from = this.goodsInfoList.get(0).getGoodsId();
            initMouthCard(this.goodsInfoList.get(0).getGoodsPrice() + "");
        }
        if (this.source != -1) {
            this.presenter.saveVipPrice(this.goodsInfoList.get(i).getGoodsId(), this.source);
        }
        this.presenter.createVipMember(this.goodsInfoList.get(i).getGoodsNo(), this.goodsInfoList.get(i).getGoodsPrice(), this.goodsInfoList.get(i).getGoodsName(), this.goodsInfoList.get(i).getOriginPrice());
    }

    @Override // com.wanos.media.view.MemberPayView
    public void createVipMemberSuccess(String orderNo) {
        this.orderNo = orderNo;
        this.presenter.payVipMember(orderNo);
    }

    @Override // com.wanos.media.view.MemberPayView
    public void getPayVipMember(OrderCreatePayBean data) {
        initQrCodeView(data.getPayUrl());
        this.expireTime = data.getExpireTime();
        PollUtils.getInstance().startPoll(this.orderNo, this.expireTime);
    }

    @Override // com.wanos.media.view.MemberPayView
    public void getOrderDetail(OrderInfoBean data) {
        this.orderInfo = data;
    }

    @Override // com.wanos.media.view.MemberPayView
    public void getAdDialogSuccess(final AdDialogInfoBean data) {
        if (data.getActivityId() != 0) {
            GlideUtil.setPreloadData(this, data.getImage(), SizeMode.ADVERTISEMENT, new GlideOptions.OnLoadListener() { // from class: com.wanos.media.ui.login.activity.MemberPayActivity.5
                @Override // com.wanos.commonlibrary.utils.GlideOptions.OnLoadListener
                public void onLoadSuccess(Bitmap bitmap) {
                    if (MemberPayActivity.this.isFinishing() || MemberPayActivity.this.isDestroyed()) {
                        return;
                    }
                    MemberPayActivity.this.adDialogList.clear();
                    if (MemberPayActivity.this.instance != null && MemberPayActivity.this.instance.isMemberRestart()) {
                        MemberPayActivity.this.instance.setMemberRestart(false);
                    }
                    if (MemberPayActivity.this.dialog != null && MemberPayActivity.this.dialog.isShowing()) {
                        MemberPayActivity.this.dialog.dismissAllowingStateLoss();
                    }
                    if (MemberPayActivity.this.source != 2) {
                        MemberPayActivity memberPayActivity = MemberPayActivity.this;
                        memberPayActivity.dialog = memberPayActivity.showAdDialog(data);
                        MemberPayActivity.this.adDialogList.add(MemberPayActivity.this.dialog);
                        ((BaseAdDialog) MemberPayActivity.this.adDialogList.get(0)).showDialog();
                        MemberPayActivity.this.dialog.showDialogList(MemberPayActivity.this.adDialogList);
                        MemberPayActivity.this.image = data.getImage();
                        MemberPayActivity.this.jumpUrl = data.getJumpUrl();
                        MemberPayActivity.this.activityId = data.getActivityId();
                        MemberPayActivity.this.afterAction = data.getAfterAction();
                        MemberPayActivity.this.isAuto = data.getIsAuto();
                    }
                    MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_CLICK_POP_WINDOW, data.getActivityId() + "", "", "", "", 0);
                }
            });
        }
    }
}
