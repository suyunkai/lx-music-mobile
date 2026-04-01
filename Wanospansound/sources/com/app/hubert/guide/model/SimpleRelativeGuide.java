package com.app.hubert.guide.model;

import android.view.View;
import com.app.hubert.guide.core.Controller;

/* JADX INFO: loaded from: classes.dex */
public class SimpleRelativeGuide extends RelativeGuide {
    private int nextViewId;
    private int skipViewId;

    public SimpleRelativeGuide(int i, int i2, int i3, int i4) {
        this(i, i2);
        this.skipViewId = i3;
        this.nextViewId = i4;
    }

    public SimpleRelativeGuide(int i, int i2) {
        super(i, i2);
    }

    @Override // com.app.hubert.guide.model.RelativeGuide
    protected void onLayoutInflated(View view, final Controller controller) {
        View viewFindViewById;
        View viewFindViewById2;
        super.onLayoutInflated(view, controller);
        int i = this.skipViewId;
        if (i != 0 && (viewFindViewById2 = view.findViewById(i)) != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.app.hubert.guide.model.SimpleRelativeGuide.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    controller.remove();
                }
            });
        }
        int i2 = this.nextViewId;
        if (i2 == 0 || (viewFindViewById = view.findViewById(i2)) == null) {
            return;
        }
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.app.hubert.guide.model.SimpleRelativeGuide.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                controller.showNextOrRemove();
            }
        });
    }

    public void setSkipViewId(int i) {
        this.skipViewId = i;
    }

    public void setNextViewId(int i) {
        this.nextViewId = i;
    }
}
