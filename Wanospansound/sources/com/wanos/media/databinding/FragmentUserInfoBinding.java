package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentUserInfoBinding implements ViewBinding {
    public final EditText etNickName;
    public final EditText etPhoneNumber;
    public final ImageView ivUserInfoEdit;
    public final ImageFilterView ivUserPic;
    public final ImageView ivVipIcon;
    public final LinearLayout llConfirm;
    public final LinearLayout llDay;
    public final LinearLayout llEditHead;
    public final LinearLayout llEditInfo;
    public final LinearLayout llHeadBottom;
    public final LinearLayout llHeadTop;
    public final LinearLayout llLogout;
    public final LinearLayout llMonth;
    public final LinearLayout llPayVip;
    public final LinearLayout llRedeemCode;
    public final LinearLayout llShowInfo;
    public final LinearLayout llVipInfo;
    public final LinearLayout llYear;
    public final View pDivider;
    private final LinearLayout rootView;
    public final TextView tvBir;
    public final TextView tvBirDay;
    public final TextView tvBirMonth;
    public final TextView tvBirYear;
    public final TextView tvCheckFemale;
    public final TextView tvCheckMale;
    public final TextView tvConfirmImg;
    public final TextView tvDayT;
    public final TextView tvGender;
    public final TextView tvInfoCancel;
    public final TextView tvInfoConfirm;
    public final TextView tvMonthT;
    public final TextView tvPayHint;
    public final TextView tvPhoneNumber;
    public final TextView tvUserName;
    public final TextView tvUserName2;
    public final TextView tvVipName;
    public final TextView tvVipTime;
    public final TextView tvYearT;
    public final LinearLayout userInfoLlLayout;
    public final LinearLayout userInfoLlLayout1;

    private FragmentUserInfoBinding(LinearLayout rootView, EditText etNickName, EditText etPhoneNumber, ImageView ivUserInfoEdit, ImageFilterView ivUserPic, ImageView ivVipIcon, LinearLayout llConfirm, LinearLayout llDay, LinearLayout llEditHead, LinearLayout llEditInfo, LinearLayout llHeadBottom, LinearLayout llHeadTop, LinearLayout llLogout, LinearLayout llMonth, LinearLayout llPayVip, LinearLayout llRedeemCode, LinearLayout llShowInfo, LinearLayout llVipInfo, LinearLayout llYear, View pDivider, TextView tvBir, TextView tvBirDay, TextView tvBirMonth, TextView tvBirYear, TextView tvCheckFemale, TextView tvCheckMale, TextView tvConfirmImg, TextView tvDayT, TextView tvGender, TextView tvInfoCancel, TextView tvInfoConfirm, TextView tvMonthT, TextView tvPayHint, TextView tvPhoneNumber, TextView tvUserName, TextView tvUserName2, TextView tvVipName, TextView tvVipTime, TextView tvYearT, LinearLayout userInfoLlLayout, LinearLayout userInfoLlLayout1) {
        this.rootView = rootView;
        this.etNickName = etNickName;
        this.etPhoneNumber = etPhoneNumber;
        this.ivUserInfoEdit = ivUserInfoEdit;
        this.ivUserPic = ivUserPic;
        this.ivVipIcon = ivVipIcon;
        this.llConfirm = llConfirm;
        this.llDay = llDay;
        this.llEditHead = llEditHead;
        this.llEditInfo = llEditInfo;
        this.llHeadBottom = llHeadBottom;
        this.llHeadTop = llHeadTop;
        this.llLogout = llLogout;
        this.llMonth = llMonth;
        this.llPayVip = llPayVip;
        this.llRedeemCode = llRedeemCode;
        this.llShowInfo = llShowInfo;
        this.llVipInfo = llVipInfo;
        this.llYear = llYear;
        this.pDivider = pDivider;
        this.tvBir = tvBir;
        this.tvBirDay = tvBirDay;
        this.tvBirMonth = tvBirMonth;
        this.tvBirYear = tvBirYear;
        this.tvCheckFemale = tvCheckFemale;
        this.tvCheckMale = tvCheckMale;
        this.tvConfirmImg = tvConfirmImg;
        this.tvDayT = tvDayT;
        this.tvGender = tvGender;
        this.tvInfoCancel = tvInfoCancel;
        this.tvInfoConfirm = tvInfoConfirm;
        this.tvMonthT = tvMonthT;
        this.tvPayHint = tvPayHint;
        this.tvPhoneNumber = tvPhoneNumber;
        this.tvUserName = tvUserName;
        this.tvUserName2 = tvUserName2;
        this.tvVipName = tvVipName;
        this.tvVipTime = tvVipTime;
        this.tvYearT = tvYearT;
        this.userInfoLlLayout = userInfoLlLayout;
        this.userInfoLlLayout1 = userInfoLlLayout1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentUserInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentUserInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_user_info, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentUserInfoBinding bind(View rootView) {
        int i = R.id.et_nick_name;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_nick_name);
        if (editText != null) {
            i = R.id.et_phone_number;
            EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_phone_number);
            if (editText2 != null) {
                i = R.id.iv_user_info_edit;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_user_info_edit);
                if (imageView != null) {
                    i = R.id.iv_user_pic;
                    ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(rootView, R.id.iv_user_pic);
                    if (imageFilterView != null) {
                        i = R.id.iv_vip_icon;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_vip_icon);
                        if (imageView2 != null) {
                            i = R.id.ll_confirm;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_confirm);
                            if (linearLayout != null) {
                                i = R.id.ll_day;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_day);
                                if (linearLayout2 != null) {
                                    i = R.id.ll_edit_head;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_edit_head);
                                    if (linearLayout3 != null) {
                                        i = R.id.ll_edit_info;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_edit_info);
                                        if (linearLayout4 != null) {
                                            i = R.id.ll_head_bottom;
                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_head_bottom);
                                            if (linearLayout5 != null) {
                                                i = R.id.ll_head_top;
                                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_head_top);
                                                if (linearLayout6 != null) {
                                                    i = R.id.ll_logout;
                                                    LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_logout);
                                                    if (linearLayout7 != null) {
                                                        i = R.id.ll_month;
                                                        LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_month);
                                                        if (linearLayout8 != null) {
                                                            i = R.id.ll_pay_vip;
                                                            LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_pay_vip);
                                                            if (linearLayout9 != null) {
                                                                i = R.id.ll_redeem_code;
                                                                LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_redeem_code);
                                                                if (linearLayout10 != null) {
                                                                    i = R.id.ll_show_info;
                                                                    LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_show_info);
                                                                    if (linearLayout11 != null) {
                                                                        i = R.id.ll_vip_info;
                                                                        LinearLayout linearLayout12 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_vip_info);
                                                                        if (linearLayout12 != null) {
                                                                            i = R.id.ll_year;
                                                                            LinearLayout linearLayout13 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_year);
                                                                            if (linearLayout13 != null) {
                                                                                i = R.id.p_divider;
                                                                                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.p_divider);
                                                                                if (viewFindChildViewById != null) {
                                                                                    i = R.id.tv_bir;
                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bir);
                                                                                    if (textView != null) {
                                                                                        i = R.id.tv_bir_day;
                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bir_day);
                                                                                        if (textView2 != null) {
                                                                                            i = R.id.tv_bir_month;
                                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bir_month);
                                                                                            if (textView3 != null) {
                                                                                                i = R.id.tv_bir_year;
                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bir_year);
                                                                                                if (textView4 != null) {
                                                                                                    i = R.id.tv_check_female;
                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_check_female);
                                                                                                    if (textView5 != null) {
                                                                                                        i = R.id.tv_check_male;
                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_check_male);
                                                                                                        if (textView6 != null) {
                                                                                                            i = R.id.tv_confirm_img;
                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_confirm_img);
                                                                                                            if (textView7 != null) {
                                                                                                                i = R.id.tv_day_t;
                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_day_t);
                                                                                                                if (textView8 != null) {
                                                                                                                    i = R.id.tv_gender;
                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_gender);
                                                                                                                    if (textView9 != null) {
                                                                                                                        i = R.id.tv_info_cancel;
                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_cancel);
                                                                                                                        if (textView10 != null) {
                                                                                                                            i = R.id.tv_info_confirm;
                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_confirm);
                                                                                                                            if (textView11 != null) {
                                                                                                                                i = R.id.tv_month_t;
                                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_month_t);
                                                                                                                                if (textView12 != null) {
                                                                                                                                    i = R.id.tv_pay_hint;
                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_pay_hint);
                                                                                                                                    if (textView13 != null) {
                                                                                                                                        i = R.id.tv_phone_number;
                                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_phone_number);
                                                                                                                                        if (textView14 != null) {
                                                                                                                                            i = R.id.tv_user_name;
                                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_user_name);
                                                                                                                                            if (textView15 != null) {
                                                                                                                                                i = R.id.tv_user_name_2;
                                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_user_name_2);
                                                                                                                                                if (textView16 != null) {
                                                                                                                                                    i = R.id.tv_vip_name;
                                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_vip_name);
                                                                                                                                                    if (textView17 != null) {
                                                                                                                                                        i = R.id.tv_vip_time;
                                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_vip_time);
                                                                                                                                                        if (textView18 != null) {
                                                                                                                                                            i = R.id.tv_year_t;
                                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_year_t);
                                                                                                                                                            if (textView19 != null) {
                                                                                                                                                                LinearLayout linearLayout14 = (LinearLayout) rootView;
                                                                                                                                                                i = R.id.user_info_ll_layout1;
                                                                                                                                                                LinearLayout linearLayout15 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.user_info_ll_layout1);
                                                                                                                                                                if (linearLayout15 != null) {
                                                                                                                                                                    return new FragmentUserInfoBinding(linearLayout14, editText, editText2, imageView, imageFilterView, imageView2, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, linearLayout13, viewFindChildViewById, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, linearLayout14, linearLayout15);
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
