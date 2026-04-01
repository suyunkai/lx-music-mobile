package com.wanos.media.ui.audiobook.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.base.BaseDialog;
import com.wanos.media.databinding.DialogPlayerSpeedSettingBinding;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerSpeedDialog extends BaseDialog implements View.OnClickListener {
    private final DialogPlayerSpeedSettingBinding binding;
    private final OnStateListener listener;

    public interface OnStateListener {
        void onChange();
    }

    @Override // com.wanos.media.base.BaseDialog
    public BaseDialog.DialogShowType getType() {
        return BaseDialog.DialogShowType.speedSet;
    }

    public PlayerSpeedDialog(Context context, OnStateListener l) {
        super(context, R.style.DialogStyle);
        getWindow().setBackgroundDrawable(getContext().getDrawable(android.R.color.transparent));
        this.listener = l;
        DialogPlayerSpeedSettingBinding dialogPlayerSpeedSettingBindingInflate = DialogPlayerSpeedSettingBinding.inflate(getLayoutInflater());
        this.binding = dialogPlayerSpeedSettingBindingInflate;
        setContentView(dialogPlayerSpeedSettingBindingInflate.getRoot());
        Util.setTextWeight(dialogPlayerSpeedSettingBindingInflate.tvAudioBookName, 500);
        setCancelable(true);
        ViewGroup.LayoutParams layoutParams = dialogPlayerSpeedSettingBindingInflate.cvView.getLayoutParams();
        layoutParams.width = context.getResources().getDimensionPixelSize(R.dimen.ab_speed_set_w);
        layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.ab_speed_set_h);
        dialogPlayerSpeedSettingBindingInflate.cvView.setLayoutParams(layoutParams);
        dialogPlayerSpeedSettingBindingInflate.btnBack.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.btn0.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.btn1.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.btn2.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.btn3.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.btn4.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.btn5.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.viewWin.setOnClickListener(this);
        dialogPlayerSpeedSettingBindingInflate.cvView.setOnClickListener(this);
        updateSpeedState(MediaSharedPreferUtils.getDoubleSpeed());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            onBackPressed();
            return;
        }
        if (id != R.id.view_win) {
            switch (id) {
                case R.id.btn_0 /* 2131361946 */:
                    setSpeed(0.75f);
                    break;
                case R.id.btn_1 /* 2131361947 */:
                    setSpeed(1.0f);
                    break;
                case R.id.btn_2 /* 2131361948 */:
                    setSpeed(1.25f);
                    break;
                case R.id.btn_3 /* 2131361949 */:
                    setSpeed(1.5f);
                    break;
                case R.id.btn_4 /* 2131361950 */:
                    setSpeed(1.75f);
                    break;
                case R.id.btn_5 /* 2131361951 */:
                    setSpeed(2.0f);
                    break;
            }
            return;
        }
        onBackPressed();
    }

    protected void updateSpeedState(float s) {
        this.binding.btn0.setBackgroundResource(R.drawable.ab_btn_speed);
        this.binding.iv0.setImageResource(R.drawable.ab_speed_0);
        this.binding.btn1.setBackgroundResource(R.drawable.ab_btn_speed);
        this.binding.iv1.setImageResource(R.drawable.ab_speed_1);
        this.binding.btn2.setBackgroundResource(R.drawable.ab_btn_speed);
        this.binding.iv2.setImageResource(R.drawable.ab_speed_2);
        this.binding.btn3.setBackgroundResource(R.drawable.ab_btn_speed);
        this.binding.iv3.setImageResource(R.drawable.ab_speed_3);
        this.binding.btn4.setBackgroundResource(R.drawable.ab_btn_speed);
        this.binding.iv4.setImageResource(R.drawable.ab_speed_4);
        this.binding.btn5.setBackgroundResource(R.drawable.ab_btn_speed);
        this.binding.iv5.setImageResource(R.drawable.ab_speed_5);
        if (s == 0.75f) {
            this.binding.btn0.setBackgroundResource(R.drawable.ab_btn_speed_select);
            this.binding.iv0.setImageResource(R.drawable.ab_speed_01);
            return;
        }
        if (s == 1.0f) {
            this.binding.btn1.setBackgroundResource(R.drawable.ab_btn_speed_select);
            this.binding.iv1.setImageResource(R.drawable.ab_speed_11);
            return;
        }
        if (s == 1.25f) {
            this.binding.btn2.setBackgroundResource(R.drawable.ab_btn_speed_select);
            this.binding.iv2.setImageResource(R.drawable.ab_speed_21);
            return;
        }
        if (s == 1.5f) {
            this.binding.btn3.setBackgroundResource(R.drawable.ab_btn_speed_select);
            this.binding.iv3.setImageResource(R.drawable.ab_speed_31);
        } else if (s == 1.75f) {
            this.binding.btn4.setBackgroundResource(R.drawable.ab_btn_speed_select);
            this.binding.iv4.setImageResource(R.drawable.ab_speed_41);
        } else if (s == 2.0f) {
            this.binding.btn5.setBackgroundResource(R.drawable.ab_btn_speed_select);
            this.binding.iv5.setImageResource(R.drawable.ab_speed_51);
        }
    }

    protected void setSpeed(float s) {
        updateSpeedState(s);
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        if (mediaPlayerService != null) {
            mediaPlayerService.setPlaySpeed(s);
        }
        this.listener.onChange();
    }
}
