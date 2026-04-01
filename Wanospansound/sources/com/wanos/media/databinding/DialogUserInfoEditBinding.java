package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogUserInfoEditBinding implements ViewBinding {
    public final LinearLayout btSave;
    public final LinearLayout btnBack;
    public final TextView etBirthdayDay;
    public final TextView etBirthdayMonth;
    public final TextView etBirthdayYear;
    public final EditText etNickName;
    public final LinearLayout llBirthday;
    public final LinearLayout llContentView;
    public final LinearLayout llEditNickName;
    public final LinearLayout llGander;
    public final LinearLayout llId;
    public final RadioButton rbGanderMan;
    public final RadioButton rbGanderWomen;
    public final RadioGroup rgGander;
    private final FrameLayout rootView;
    public final TextView tvId;
    public final TextView tvIdValue;

    private DialogUserInfoEditBinding(FrameLayout rootView, LinearLayout btSave, LinearLayout btnBack, TextView etBirthdayDay, TextView etBirthdayMonth, TextView etBirthdayYear, EditText etNickName, LinearLayout llBirthday, LinearLayout llContentView, LinearLayout llEditNickName, LinearLayout llGander, LinearLayout llId, RadioButton rbGanderMan, RadioButton rbGanderWomen, RadioGroup rgGander, TextView tvId, TextView tvIdValue) {
        this.rootView = rootView;
        this.btSave = btSave;
        this.btnBack = btnBack;
        this.etBirthdayDay = etBirthdayDay;
        this.etBirthdayMonth = etBirthdayMonth;
        this.etBirthdayYear = etBirthdayYear;
        this.etNickName = etNickName;
        this.llBirthday = llBirthday;
        this.llContentView = llContentView;
        this.llEditNickName = llEditNickName;
        this.llGander = llGander;
        this.llId = llId;
        this.rbGanderMan = rbGanderMan;
        this.rbGanderWomen = rbGanderWomen;
        this.rgGander = rgGander;
        this.tvId = tvId;
        this.tvIdValue = tvIdValue;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogUserInfoEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogUserInfoEditBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_user_info_edit, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogUserInfoEditBinding bind(View rootView) {
        int i = R.id.bt_save;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bt_save);
        if (linearLayout != null) {
            i = R.id.btn_back;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_back);
            if (linearLayout2 != null) {
                i = R.id.et_birthday_day;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.et_birthday_day);
                if (textView != null) {
                    i = R.id.et_birthday_month;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.et_birthday_month);
                    if (textView2 != null) {
                        i = R.id.et_birthday_year;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.et_birthday_year);
                        if (textView3 != null) {
                            i = R.id.et_nick_name;
                            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_nick_name);
                            if (editText != null) {
                                i = R.id.ll_birthday;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_birthday);
                                if (linearLayout3 != null) {
                                    i = R.id.ll_content_view;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_content_view);
                                    if (linearLayout4 != null) {
                                        i = R.id.ll_edit_nick_name;
                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_edit_nick_name);
                                        if (linearLayout5 != null) {
                                            i = R.id.ll_gander;
                                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_gander);
                                            if (linearLayout6 != null) {
                                                i = R.id.ll_id;
                                                LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_id);
                                                if (linearLayout7 != null) {
                                                    i = R.id.rb_gander_man;
                                                    RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_gander_man);
                                                    if (radioButton != null) {
                                                        i = R.id.rb_gander_women;
                                                        RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_gander_women);
                                                        if (radioButton2 != null) {
                                                            i = R.id.rg_gander;
                                                            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, R.id.rg_gander);
                                                            if (radioGroup != null) {
                                                                i = R.id.tv_id;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_id);
                                                                if (textView4 != null) {
                                                                    i = R.id.tv_id_value;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_id_value);
                                                                    if (textView5 != null) {
                                                                        return new DialogUserInfoEditBinding((FrameLayout) rootView, linearLayout, linearLayout2, textView, textView2, textView3, editText, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, radioButton, radioButton2, radioGroup, textView4, textView5);
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
