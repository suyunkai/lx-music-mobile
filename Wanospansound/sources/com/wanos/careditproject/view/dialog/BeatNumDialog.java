package com.wanos.careditproject.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.ProjectBeatNumbeAdapter;
import com.wanos.careditproject.model.BeatNumberInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BeatNumDialog extends PopupWindow {
    private static final int FULL_SCREEN_FLAG = 4356;
    private List<BeatNumberInfo> beatNumberInfoList;
    private final CardView cardView;
    private Context context;
    private final View inflate;
    private BeatNumberInfo info;
    private int measuredHeight;
    private ProjectBeatSelectListener projectBeatSelectListener;

    public interface ProjectBeatSelectListener {
        void onProjectBeatSelectListener(BeatNumberInfo beatNumberInfo);
    }

    public BeatNumDialog(Context context, int i, BeatNumberInfo beatNumberInfo, int i2) {
        super(context);
        this.context = context;
        this.info = beatNumberInfo;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_beat_number, (ViewGroup) null);
        this.inflate = viewInflate;
        CardView cardView = (CardView) viewInflate.findViewById(R.id.card_view);
        this.cardView = cardView;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) cardView.getLayoutParams();
        layoutParams.width = i2;
        cardView.setLayoutParams(layoutParams);
        setContentView(viewInflate);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setOutsideTouchable(true);
        setFocusable(true);
        initView();
    }

    public int getMeasuredHeight() {
        return this.measuredHeight;
    }

    private void initView() {
        initData();
        RecyclerView recyclerView = (RecyclerView) this.inflate.findViewById(R.id.rv_beat_list);
        ProjectBeatNumbeAdapter projectBeatNumbeAdapter = new ProjectBeatNumbeAdapter(this.context, this.beatNumberInfoList, this.info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        recyclerView.setAdapter(projectBeatNumbeAdapter);
        this.cardView.measure(0, 0);
        this.measuredHeight = this.cardView.getMeasuredHeight();
        projectBeatNumbeAdapter.setOnSelectBeatListener(new ProjectBeatNumbeAdapter.OnSelectBeatListener() { // from class: com.wanos.careditproject.view.dialog.BeatNumDialog.1
            @Override // com.wanos.careditproject.adapter.ProjectBeatNumbeAdapter.OnSelectBeatListener
            public void onSelectBeatListener(BeatNumberInfo beatNumberInfo) {
                if (BeatNumDialog.this.projectBeatSelectListener != null) {
                    BeatNumDialog.this.projectBeatSelectListener.onProjectBeatSelectListener(beatNumberInfo);
                }
                BeatNumDialog.this.dismiss();
            }
        });
    }

    private void initData() {
        ArrayList arrayList = new ArrayList();
        this.beatNumberInfoList = arrayList;
        arrayList.add(new BeatNumberInfo(2, 4));
        this.beatNumberInfoList.add(new BeatNumberInfo(3, 4));
        this.beatNumberInfoList.add(new BeatNumberInfo(4, 4));
        this.beatNumberInfoList.add(new BeatNumberInfo(6, 8));
    }

    public void setProjectBeatSelectListener(ProjectBeatSelectListener projectBeatSelectListener) {
        this.projectBeatSelectListener = projectBeatSelectListener;
    }

    public void showAsDropDown(View view, BeatNumberInfo beatNumberInfo) {
        this.info = beatNumberInfo;
        showAsDropDown(view);
    }

    public void showAsDropDown(View view, int i, int i2, BeatNumberInfo beatNumberInfo) {
        this.info = beatNumberInfo;
        showAsDropDown(view, i, i2);
    }

    public void showAsDropDown(View view, int i, int i2, int i3, BeatNumberInfo beatNumberInfo) {
        this.info = beatNumberInfo;
        showAsDropDown(view, i, i2, i3);
    }
}
