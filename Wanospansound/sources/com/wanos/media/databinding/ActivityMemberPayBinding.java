package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMemberPayBinding implements ViewBinding {
    public final IncludeMemberPayCardBinding includeMouthCard;
    public final IncludeMemberPayCardBinding includeSeasonCard;
    public final IncludeMemberPayCardBinding includeYearCard;
    public final CardView memberPayCvQrBg;
    public final CardView memberPayCvQrImBg;
    public final CardView memberPayCvUserImgBg;
    public final ImageView memberPayImQr;
    public final ImageView memberPayImUserImg;
    public final LinearLayout memberPayLlSelectCard;
    public final LinearLayout memberPayLlTitle;
    public final TextView memberPayTvAgreement;
    public final TextView memberPayTvHint;
    public final TextView memberPayTvTitleBehind;
    public final TextView memberPayTvTitleFront;
    public final TextView memberPayTvTitleName;
    private final LinearLayout rootView;

    private ActivityMemberPayBinding(LinearLayout rootView, IncludeMemberPayCardBinding includeMouthCard, IncludeMemberPayCardBinding includeSeasonCard, IncludeMemberPayCardBinding includeYearCard, CardView memberPayCvQrBg, CardView memberPayCvQrImBg, CardView memberPayCvUserImgBg, ImageView memberPayImQr, ImageView memberPayImUserImg, LinearLayout memberPayLlSelectCard, LinearLayout memberPayLlTitle, TextView memberPayTvAgreement, TextView memberPayTvHint, TextView memberPayTvTitleBehind, TextView memberPayTvTitleFront, TextView memberPayTvTitleName) {
        this.rootView = rootView;
        this.includeMouthCard = includeMouthCard;
        this.includeSeasonCard = includeSeasonCard;
        this.includeYearCard = includeYearCard;
        this.memberPayCvQrBg = memberPayCvQrBg;
        this.memberPayCvQrImBg = memberPayCvQrImBg;
        this.memberPayCvUserImgBg = memberPayCvUserImgBg;
        this.memberPayImQr = memberPayImQr;
        this.memberPayImUserImg = memberPayImUserImg;
        this.memberPayLlSelectCard = memberPayLlSelectCard;
        this.memberPayLlTitle = memberPayLlTitle;
        this.memberPayTvAgreement = memberPayTvAgreement;
        this.memberPayTvHint = memberPayTvHint;
        this.memberPayTvTitleBehind = memberPayTvTitleBehind;
        this.memberPayTvTitleFront = memberPayTvTitleFront;
        this.memberPayTvTitleName = memberPayTvTitleName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMemberPayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMemberPayBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_member_pay, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMemberPayBinding bind(View rootView) {
        int i = R.id.include_mouth_card;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.include_mouth_card);
        if (viewFindChildViewById != null) {
            IncludeMemberPayCardBinding includeMemberPayCardBindingBind = IncludeMemberPayCardBinding.bind(viewFindChildViewById);
            i = R.id.include_season_card;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.include_season_card);
            if (viewFindChildViewById2 != null) {
                IncludeMemberPayCardBinding includeMemberPayCardBindingBind2 = IncludeMemberPayCardBinding.bind(viewFindChildViewById2);
                i = R.id.include_year_card;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.include_year_card);
                if (viewFindChildViewById3 != null) {
                    IncludeMemberPayCardBinding includeMemberPayCardBindingBind3 = IncludeMemberPayCardBinding.bind(viewFindChildViewById3);
                    i = R.id.member_pay_cv_qr_bg;
                    CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.member_pay_cv_qr_bg);
                    if (cardView != null) {
                        i = R.id.member_pay_cv_qr_im_bg;
                        CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.member_pay_cv_qr_im_bg);
                        if (cardView2 != null) {
                            i = R.id.member_pay_cv_user_img_bg;
                            CardView cardView3 = (CardView) ViewBindings.findChildViewById(rootView, R.id.member_pay_cv_user_img_bg);
                            if (cardView3 != null) {
                                i = R.id.member_pay_im_qr;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.member_pay_im_qr);
                                if (imageView != null) {
                                    i = R.id.member_pay_im_user_img;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.member_pay_im_user_img);
                                    if (imageView2 != null) {
                                        i = R.id.member_pay_ll_select_card;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.member_pay_ll_select_card);
                                        if (linearLayout != null) {
                                            i = R.id.member_pay_ll_title;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.member_pay_ll_title);
                                            if (linearLayout2 != null) {
                                                i = R.id.member_pay_tv_agreement;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.member_pay_tv_agreement);
                                                if (textView != null) {
                                                    i = R.id.member_pay_tv_hint;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.member_pay_tv_hint);
                                                    if (textView2 != null) {
                                                        i = R.id.member_pay_tv_title_behind;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.member_pay_tv_title_behind);
                                                        if (textView3 != null) {
                                                            i = R.id.member_pay_tv_title_front;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.member_pay_tv_title_front);
                                                            if (textView4 != null) {
                                                                i = R.id.member_pay_tv_title_name;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.member_pay_tv_title_name);
                                                                if (textView5 != null) {
                                                                    return new ActivityMemberPayBinding((LinearLayout) rootView, includeMemberPayCardBindingBind, includeMemberPayCardBindingBind2, includeMemberPayCardBindingBind3, cardView, cardView2, cardView3, imageView, imageView2, linearLayout, linearLayout2, textView, textView2, textView3, textView4, textView5);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
