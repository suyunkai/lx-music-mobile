package com.wanos.careditproject.view.dialog;

import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.HelpUIAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class HelpPosDrawDialog extends HelpBaseDialog {
    @Override // com.wanos.careditproject.view.dialog.HelpBaseDialog
    protected void initData() {
        this.list = new ArrayList();
        this.list.add(new HelpUIAdapter.HelpUIBean(R.drawable.edit_help_obj_1, "固定位置摆放", "拖动小球放置某个固定位置，当前轨道声音片段时刻线后内容则在该位置播放"));
        this.list.add(new HelpUIAdapter.HelpUIBean(R.drawable.edit_help_obj_2, "动态轨迹绘制", "拖动小球放置起始位置，点击“录制”，拖动小球轨迹绘制，松手自动生成声音运动轨迹。"));
        this.list.add(new HelpUIAdapter.HelpUIBean(R.drawable.edit_help_obj_3, "查看位置信息", "实时查看当前声音片段的水平面位置信息。"));
    }
}
