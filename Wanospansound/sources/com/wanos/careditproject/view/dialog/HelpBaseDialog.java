package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.HelpUIAdapter;
import com.wanos.careditproject.databinding.DialogHelpViewBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HelpBaseDialog extends DialogFragment implements View.OnClickListener {
    protected HelpUIAdapter adapter;
    private DialogHelpViewBinding binding;
    List<HelpUIAdapter.HelpUIBean> list;

    protected void initData() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = DialogHelpViewBinding.inflate(getLayoutInflater());
        setCancelable(true);
        iniView();
        return this.binding.getRoot();
    }

    protected void iniView() {
        this.binding.btnCloseHelp.setOnClickListener(this);
        initPager();
    }

    public void initPager() {
        initData();
        this.binding.vpContent.setAdapter(new HelpUIAdapter(this, this.list));
        this.binding.vpContent.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.careditproject.view.dialog.HelpBaseDialog.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                HelpBaseDialog.this.updateIndicators(i);
            }
        });
        initializeIndicators(this.list.size());
    }

    private void initializeIndicators(int i) {
        this.binding.IndicatorList.removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            ImageView imageView = new ImageView(getContext());
            if (i2 == 0) {
                imageView.setImageResource(R.drawable.help_circle_1);
            } else {
                imageView.setImageResource(R.drawable.help_circle_0);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.help_Indicator_size), getResources().getDimensionPixelSize(R.dimen.help_Indicator_size));
            layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.help_Indicator_margin), 0, getResources().getDimensionPixelSize(R.dimen.help_Indicator_margin), 0);
            imageView.setLayoutParams(layoutParams);
            this.binding.IndicatorList.addView(imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIndicators(int i) {
        for (int i2 = 0; i2 < this.binding.IndicatorList.getChildCount(); i2++) {
            ImageView imageView = (ImageView) this.binding.IndicatorList.getChildAt(i2);
            if (i2 == i) {
                imageView.setImageResource(R.drawable.help_circle_1);
            } else {
                imageView.setImageResource(R.drawable.help_circle_0);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_close_help) {
            dismiss();
        } else if (id == R.id.view_root) {
            dismiss();
        }
    }
}
