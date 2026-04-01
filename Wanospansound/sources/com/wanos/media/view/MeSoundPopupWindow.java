package com.wanos.media.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import androidx.core.view.GravityCompat;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.SoundMePopupBinding;

/* JADX INFO: loaded from: classes3.dex */
public class MeSoundPopupWindow extends PopupWindow {
    private static final String TAG = "MeSoundPopupWindow";
    private final IPopupWindowListener mIPopupWindowListener;
    private final SoundMePopupBinding mViewBinding;

    public interface IPopupWindowListener {
        void onDelete();

        void onShare();

        void onTopHome();
    }

    public static MeSoundPopupWindow show(Context context, View view, boolean z, boolean z2, IPopupWindowListener iPopupWindowListener) {
        if (context == null || view == null) {
            ZeroLogcatTools.e(TAG, "零压空间==>我的声音==>PopupWindow弹出错误");
            return null;
        }
        MeSoundPopupWindow meSoundPopupWindow = new MeSoundPopupWindow(context, z, z2, iPopupWindowListener);
        meSoundPopupWindow.showAsDropDown(view, -((int) context.getResources().getDimension(R.dimen.me_popup_width)), 0, GravityCompat.END);
        return meSoundPopupWindow;
    }

    public MeSoundPopupWindow(Context context, boolean z, boolean z2, IPopupWindowListener iPopupWindowListener) {
        super(-2, -2);
        this.mIPopupWindowListener = iPopupWindowListener;
        SoundMePopupBinding soundMePopupBindingInflate = SoundMePopupBinding.inflate(LayoutInflater.from(context), null, false);
        this.mViewBinding = soundMePopupBindingInflate;
        setContentView(soundMePopupBindingInflate.getRoot());
        setOutsideTouchable(true);
        soundMePopupBindingInflate.btnShare.setVisibility(z2 ? 0 : 8);
        soundMePopupBindingInflate.btnTop.setVisibility(z ? 0 : 8);
        soundMePopupBindingInflate.btnDelete.setOnClickListener(new AnitClick() { // from class: com.wanos.media.view.MeSoundPopupWindow.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                MeSoundPopupWindow.this.dismiss();
                MeSoundPopupWindow.this.mIPopupWindowListener.onDelete();
            }
        });
        soundMePopupBindingInflate.btnTop.setOnClickListener(new AnitClick() { // from class: com.wanos.media.view.MeSoundPopupWindow.2
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                MeSoundPopupWindow.this.dismiss();
                MeSoundPopupWindow.this.mIPopupWindowListener.onTopHome();
            }
        });
        soundMePopupBindingInflate.btnShare.setOnClickListener(new AnitClick() { // from class: com.wanos.media.view.MeSoundPopupWindow.3
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                MeSoundPopupWindow.this.dismiss();
                MeSoundPopupWindow.this.mIPopupWindowListener.onShare();
                MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MY_SOUND_SPACE_SHARE);
            }
        });
    }
}
