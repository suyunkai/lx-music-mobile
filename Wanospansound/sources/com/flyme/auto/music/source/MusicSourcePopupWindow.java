package com.flyme.auto.music.source;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.flyme.auto.music.source.PopRecyclerView;
import com.flyme.auto.music.source.usage.MusicSourceUsageConstants;
import com.flyme.auto.music.source.usage.MusicSourceUsageManager;
import com.flyme.auto.music.source.util.MusicSourceLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MusicSourcePopupWindow extends PopupWindow {
    private static final String TAG = "MusicSource-Window";
    private final Context context;
    private List<MusicSourceEntity> data;
    private int listItemMargin;
    private int listMarginTop;
    private int maxHeight;
    private int pressedColor;
    private final String referrerAuthority;
    private View rootView;
    private int textColor;
    private MusicSourceSelectCallback musicSourceSelectCallback = null;
    private final MusicSourceAdapter adapter = new MusicSourceAdapter();

    public interface MusicSourceSelectCallback {
        void onMusicSourceSelect(MusicSourceEntity musicSourceEntity, int i);
    }

    public MusicSourcePopupWindow(Context context, List<MusicSourceEntity> list, String str) {
        this.context = context;
        this.data = list;
        this.referrerAuthority = str;
        init();
    }

    private void init() {
        this.textColor = ViewCompat.MEASURED_STATE_MASK;
        this.pressedColor = ContextCompat.getColor(this.context, R.color.music_source_press_tint);
        this.maxHeight = this.context.getResources().getDimensionPixelSize(R.dimen.source_view_max_height);
        this.listMarginTop = this.context.getResources().getDimensionPixelSize(R.dimen.source_list_margin);
        this.listItemMargin = this.context.getResources().getDimensionPixelSize(R.dimen.source_list_parent_item_height) - this.context.getResources().getDimensionPixelSize(R.dimen.source_list_item_height);
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.view_music_source_popup, (ViewGroup) null);
        this.rootView = viewInflate;
        final PopRecyclerView popRecyclerView = (PopRecyclerView) viewInflate.findViewById(R.id.recycler_view);
        popRecyclerView.setLayoutManager(new LinearLayoutManager(this.context) { // from class: com.flyme.auto.music.source.MusicSourcePopupWindow.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return popRecyclerView.getHeight() >= MusicSourcePopupWindow.this.maxHeight;
            }
        });
        popRecyclerView.setItemAnimator(null);
        popRecyclerView.setHasFixedSize(true);
        popRecyclerView.addItemDecoration(new MusicSourceItemDecorator());
        popRecyclerView.setAdapter(this.adapter);
        this.adapter.setMusicSources(this.data);
        setContentView(this.rootView);
        setWidth(this.context.getResources().getDimensionPixelSize(R.dimen.source_item_width));
        setPopHeight(true);
        setFocusable(true);
        setOutsideTouchable(true);
        popRecyclerView.addOnWindowFocusChanged(new PopRecyclerView.PopupWindowFocusChanged() { // from class: com.flyme.auto.music.source.MusicSourcePopupWindow$$ExternalSyntheticLambda0
            @Override // com.flyme.auto.music.source.PopRecyclerView.PopupWindowFocusChanged
            public final void onWindowFocusChanged(boolean z) {
                this.f$0.m231lambda$init$0$comflymeautomusicsourceMusicSourcePopupWindow(z);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$init$0$com-flyme-auto-music-source-MusicSourcePopupWindow, reason: not valid java name */
    /* synthetic */ void m231lambda$init$0$comflymeautomusicsourceMusicSourcePopupWindow(boolean z) {
        if (z) {
            return;
        }
        dismiss();
    }

    private void setPopHeight(boolean z) {
        RecyclerView recyclerView = (RecyclerView) this.rootView.findViewById(R.id.recycler_view);
        int size = ((this.data.size() - 1) * this.context.getResources().getDimensionPixelSize(R.dimen.source_list_parent_item_height)) + this.context.getResources().getDimensionPixelSize(R.dimen.source_list_item_height) + (this.listMarginTop * 2);
        if (z) {
            setHeight(Math.min(size, this.maxHeight));
        } else {
            update(getWidth(), Math.min(size, this.maxHeight));
        }
        recyclerView.setVerticalScrollBarEnabled(size >= this.maxHeight);
    }

    public void notifyDataSetChanged(List<MusicSourceEntity> list) {
        this.data = list;
        setPopHeight(false);
        this.adapter.setMusicSources(list);
    }

    public void setStyle(float f, int i, int i2, int i3) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(f);
        gradientDrawable.setColor(i);
        this.rootView.setBackground(gradientDrawable);
        this.textColor = i2;
        this.pressedColor = i3;
    }

    class MusicSourceAdapter extends RecyclerView.Adapter<MusicSourceViewHolder> {
        private final List<MusicSourceEntity> items = new ArrayList();

        MusicSourceAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public MusicSourceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return MusicSourcePopupWindow.this.new MusicSourceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_music_source_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(MusicSourceViewHolder musicSourceViewHolder, int i) {
            MusicSourceEntity musicSourceEntity = this.items.get(i);
            if (musicSourceEntity != null) {
                musicSourceViewHolder.bind(musicSourceEntity, i);
                musicSourceViewHolder.report(musicSourceEntity, i);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.items.size();
        }

        public void setMusicSources(List<MusicSourceEntity> list) {
            this.items.clear();
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }

    class MusicSourceViewHolder extends RecyclerView.ViewHolder {
        public MusicSourceViewHolder(View view) {
            super(view);
        }

        public void bind(final MusicSourceEntity musicSourceEntity, final int i) {
            TextView textView = (TextView) this.itemView.findViewById(R.id.name_text_view);
            ImageView imageView = (ImageView) this.itemView.findViewById(R.id.icon_image_view);
            ImageView imageView2 = (ImageView) this.itemView.findViewById(R.id.check_image_view);
            textView.setText(musicSourceEntity.getName());
            textView.setTypeface(Typeface.SANS_SERIF);
            imageView.setImageBitmap(musicSourceEntity.getIcon());
            if (this.itemView.getContext().getPackageName().equals(musicSourceEntity.getPackageName())) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(MusicSourcePopupWindow.this.pressedColor));
            stateListDrawable.addState(new int[1], new ColorDrawable(0));
            stateListDrawable.setEnterFadeDuration(100);
            stateListDrawable.setExitFadeDuration(300);
            this.itemView.setBackground(stateListDrawable);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.flyme.auto.music.source.MusicSourcePopupWindow$MusicSourceViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m232xd798bb61(i, musicSourceEntity, view);
                }
            });
            textView.setTextColor(MusicSourcePopupWindow.this.textColor);
        }

        /* JADX INFO: renamed from: lambda$bind$0$com-flyme-auto-music-source-MusicSourcePopupWindow$MusicSourceViewHolder, reason: not valid java name */
        /* synthetic */ void m232xd798bb61(int i, MusicSourceEntity musicSourceEntity, View view) {
            MusicSourceLog.d(MusicSourcePopupWindow.TAG, "onItemClick pos = " + i);
            if (MusicSourcePopupWindow.this.musicSourceSelectCallback != null) {
                MusicSourcePopupWindow.this.musicSourceSelectCallback.onMusicSourceSelect(musicSourceEntity, i);
            }
        }

        void report(MusicSourceEntity musicSourceEntity, int i) {
            HashMap map = new HashMap();
            map.put(MusicSourceUsageConstants.MusicSourceUsageKey.EXPOSURE_APP_NAME, musicSourceEntity.getName());
            map.put(MusicSourceUsageConstants.MusicSourceUsageKey.EXPOSURE_APP_ID, Integer.valueOf(musicSourceEntity.getCpId()));
            map.put(MusicSourceUsageConstants.MusicSourceUsageKey.EXPOSURE_APP_TYPE, musicSourceEntity.getSourceType());
            map.put(MusicSourceUsageConstants.MusicSourceUsageKey.EXPOSURE_ITEM_POS, Integer.valueOf(i));
            map.put(MusicSourceUsageConstants.MusicSourceUsageKey.EXPOSURE_SOURCE_ENTRANCE, MusicSourcePopupWindow.this.referrerAuthority);
            MusicSourceUsageManager.reportEvent(MusicSourceUsageConstants.MusicSourceUsageEvent.EVENT_EXPOSURE, null, map);
        }
    }

    class MusicSourceItemDecorator extends RecyclerView.ItemDecoration {
        MusicSourceItemDecorator() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            int itemCount = MusicSourcePopupWindow.this.adapter.getItemCount();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (childAdapterPosition == 0) {
                rect.top = MusicSourcePopupWindow.this.listMarginTop;
            }
            if (childAdapterPosition == itemCount - 1) {
                rect.bottom = MusicSourcePopupWindow.this.listMarginTop;
            } else {
                rect.bottom = MusicSourcePopupWindow.this.listItemMargin == 0 ? 20 : MusicSourcePopupWindow.this.listItemMargin;
            }
        }
    }

    public void setMusicSourceSelectCallback(MusicSourceSelectCallback musicSourceSelectCallback) {
        this.musicSourceSelectCallback = musicSourceSelectCallback;
    }
}
