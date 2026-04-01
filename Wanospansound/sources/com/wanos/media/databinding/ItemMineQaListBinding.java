package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemMineQaListBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvAnswer;
    public final TextView tvIconA;
    public final TextView tvIconQ;
    public final TextView tvQuestion;

    private ItemMineQaListBinding(ConstraintLayout rootView, TextView tvAnswer, TextView tvIconA, TextView tvIconQ, TextView tvQuestion) {
        this.rootView = rootView;
        this.tvAnswer = tvAnswer;
        this.tvIconA = tvIconA;
        this.tvIconQ = tvIconQ;
        this.tvQuestion = tvQuestion;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemMineQaListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMineQaListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_mine_qa_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemMineQaListBinding bind(View rootView) {
        int i = R.id.tv_answer;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_answer);
        if (textView != null) {
            i = R.id.tv_icon_a;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_icon_a);
            if (textView2 != null) {
                i = R.id.tv_icon_q;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_icon_q);
                if (textView3 != null) {
                    i = R.id.tv_question;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_question);
                    if (textView4 != null) {
                        return new ItemMineQaListBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
