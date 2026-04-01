package ecarx.xsf.widget;

import android.os.IBinder;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import ecarx.eas.xsf.mediacenter.IMediaListsEx;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMediaLists;
import ecarx.xsf.widget.interfaces.IControl;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetAPI extends BaseWidgetAPI implements IControl {
    private static volatile WidgetAPI instance;
    private IBinder iBinder;
    private IEASFrameworkService iEasBinder;
    private a impl;

    public static WidgetAPI get() {
        if (instance == null) {
            synchronized (WidgetAPI.class) {
                if (instance == null) {
                    instance = new WidgetAPI();
                }
            }
        }
        return instance;
    }

    private WidgetAPI() {
    }

    public final void updateMediaCenterSvc(IBinder iBinder) {
        this.iBinder = iBinder;
    }

    public final void updateIEASFrameworkService(IEASFrameworkService iEASFrameworkService) {
        this.iEasBinder = iEASFrameworkService;
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void init(IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback) {
        if (this.impl == null) {
            this.impl = new a();
        }
        this.impl.a(this.iBinder, this.iEasBinder);
        this.impl.a(iReceiveWidgetInfoCallback);
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPlay() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlPlay();
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPause() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlPause();
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlNext() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlNext();
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPrevious() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlPrevious();
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPlayRecommend() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlPlayRecommend();
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlCancelRecommend() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlCancelRecommend();
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void selectMediaPlay(int i, String str) {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.selectMediaPlay(i, str);
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final List<IContent> getContentList() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            return iControlA.getContentList();
        }
        return null;
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final IMediaLists getMultiMediaList() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            return iControlA.getMultiMediaList();
        }
        return null;
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final IMediaListsEx getMultiMediaListEx() {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            return iControlA.getMultiMediaListEx();
        }
        return null;
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final int ctrlCollect(int i, boolean z) {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            return iControlA.ctrlCollect(i, z);
        }
        return 0;
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void selectListMediaPlay(int i, int i2, String str) {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.selectListMediaPlay(i, i2, str);
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPlayMediaList(int i) {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlPlayMediaList(i);
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPauseMediaList(int i) {
        IControl iControlA = this.impl.a();
        if (iControlA != null) {
            iControlA.ctrlPauseMediaList(i);
        }
    }
}
