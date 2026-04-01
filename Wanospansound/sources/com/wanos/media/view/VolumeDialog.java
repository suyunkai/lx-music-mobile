package com.wanos.media.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.media.adapter.VolumeDialogAdapter;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.entity.VolumeModifyEntity;
import com.wanos.media.ui.info.ZeroInfoActivity;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.LinearItemDecoration;
import com.wanos.media.util.PictureCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.viewmodel.VolumeDialogViewModel;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.DialogVolumeBinding;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class VolumeDialog extends WanosBaseDialog<DialogVolumeBinding, VolumeDialogViewModel> {
    private static final String FM_TAG = "volume_modify_dialog";
    private static final String TAG = "VolumeDialog";

    @Override // com.wanos.media.base.WanosBaseDialog
    protected int initGravity() {
        return 80;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
    }

    public static void showVolumeDialog(FragmentManager fragmentManager, String str, ArrayList<VolumeModifyEntity> arrayList) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(FM_TAG);
        if (fragmentFindFragmentByTag instanceof VolumeDialog) {
            ((VolumeDialog) fragmentFindFragmentByTag).dismiss();
        }
        VolumeDialog volumeDialog = new VolumeDialog();
        Bundle bundle = new Bundle();
        bundle.putString("dialog_bg", str);
        bundle.putParcelableArrayList("dialog_data", arrayList);
        volumeDialog.setArguments(bundle);
        volumeDialog.show(fragmentManager, FM_TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogVolumeBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogVolumeBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<VolumeDialogViewModel> initViewModel() {
        return VolumeDialogViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogVolumeBinding) this.binding).btnFinish.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.VolumeDialog.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                VolumeDialog.this.dismiss();
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initData(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments == null || this.binding == 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = ((DialogVolumeBinding) this.binding).dialogContent.getLayoutParams();
        layoutParams.height = (int) (ScreenUtils.getScreenHeight() * 0.72f);
        ((DialogVolumeBinding) this.binding).dialogContent.setLayoutParams(layoutParams);
        setBackgroundImage(arguments);
        ArrayList parcelableArrayList = arguments.getParcelableArrayList("dialog_data");
        if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
            return;
        }
        VolumeDialogAdapter volumeDialogAdapter = new VolumeDialogAdapter(parcelableArrayList);
        ((DialogVolumeBinding) this.binding).rvContent.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        ((DialogVolumeBinding) this.binding).rvContent.addItemDecoration(new LinearItemDecoration(0, getResources().getDimensionPixelOffset(R.dimen.relax_volume_item_space)));
        ((DialogVolumeBinding) this.binding).rvContent.setAdapter(volumeDialogAdapter);
        volumeDialogAdapter.setOnItemListener(new VolumeDialogAdapter.OnItemListener() { // from class: com.wanos.media.view.VolumeDialog.2
            @Override // com.wanos.media.adapter.VolumeDialogAdapter.OnItemListener
            public void onItemVolume(VolumeModifyEntity volumeModifyEntity) {
                ZeroLogcatTools.d(VolumeDialog.TAG, "音源 = " + volumeModifyEntity.getBallName() + " 音量 = " + volumeModifyEntity.getBallVolume());
                FragmentActivity activity = VolumeDialog.this.getActivity();
                if (activity instanceof ZeroInfoActivity) {
                    ZeroLogcatTools.d(VolumeDialog.TAG, "通知Activity设置音量");
                    ((ZeroInfoActivity) activity).setBallVolume(volumeModifyEntity);
                }
            }
        });
    }

    private void setBackgroundImage(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        final File file = new File(PictureCacheUtils.getImageCachePath(bundle.getString("dialog_bg")));
        if (file.exists() && file.isFile()) {
            ((DialogVolumeBinding) this.binding).vBlurColor.setBackgroundColor(Color.parseColor("#9A9A9A"));
            ThreadUtils.executeByCpu(new ThreadUtils.SimpleTask<Bitmap>() { // from class: com.wanos.media.view.VolumeDialog.3
                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public Bitmap doInBackground() throws Throwable {
                    Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
                    float height = bitmapDecodeFile.getHeight() * 0.72f;
                    return Bitmap.createBitmap(bitmapDecodeFile, 0, (int) (bitmapDecodeFile.getHeight() - height), bitmapDecodeFile.getWidth(), (int) height);
                }

                @Override // com.blankj.utilcode.util.ThreadUtils.Task
                public void onSuccess(Bitmap bitmap) {
                    if (VolumeDialog.this.binding == null) {
                        return;
                    }
                    if (bitmap != null) {
                        ((DialogVolumeBinding) VolumeDialog.this.binding).ivBg.setImageBitmap(bitmap);
                        ((DialogVolumeBinding) VolumeDialog.this.binding).vBlurColor.setBackgroundColor(0);
                    } else {
                        ((DialogVolumeBinding) VolumeDialog.this.binding).vBlurColor.setBackgroundColor(Color.parseColor("#9A9A9A"));
                    }
                }
            });
        } else {
            ((DialogVolumeBinding) this.binding).vBlurColor.setBackgroundColor(Color.parseColor("#9A9A9A"));
        }
    }
}
