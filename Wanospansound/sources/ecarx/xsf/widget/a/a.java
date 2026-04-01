package ecarx.xsf.widget.a;

import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.mediacenter.CommercialInfoHelper;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import ecarx.eas.xsf.mediacenter.IMediaListsEx;
import ecarx.xsf.mediacenter.IContent;
import ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc;
import ecarx.xsf.mediacenter.IMediaLists;
import ecarx.xsf.widget.IReceiveWidgetInfoCallback;
import ecarx.xsf.widget.interfaces.IControl;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements IControl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IMediaCenterWidgetApiSvc f832a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final IEASFrameworkService f833b;

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void init(IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback) {
    }

    public a(IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc, IEASFrameworkService iEASFrameworkService) {
        this.f832a = iMediaCenterWidgetApiSvc;
        this.f833b = iEASFrameworkService;
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPlay() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.playCtrlPlay();
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPause() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.playCtrlPause();
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlNext() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.playCtrlNext();
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPrevious() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.playCtrlPrevious();
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPlayRecommend() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.playCtrlPlayRecommend();
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlCancelRecommend() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.playCtrlCancelRecommend();
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void selectMediaPlay(int i, String str) {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.selectMediaPlay(i, str);
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final List<IContent> getContentList() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                return iMediaCenterWidgetApiSvc.getContentList();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final IMediaLists getMultiMediaList() {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                return iMediaCenterWidgetApiSvc.getMultiMediaList(null);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final IMediaListsEx getMultiMediaListEx() {
        IExContent exData;
        if (this.f833b != null) {
            try {
                IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
                if (iMediaCenterWidgetApiSvc == null || (exData = iMediaCenterWidgetApiSvc.getExData(1, "getMultiMediaListEx", null, null, null)) == null) {
                    return null;
                }
                return CommercialInfoHelper.getIMediaListsExByIExContent(exData);
            } catch (Throwable unused) {
                return null;
            }
        }
        return CommercialInfoHelper.changeIMediaLists2Ex(getMultiMediaList());
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final int ctrlCollect(int i, boolean z) {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                return iMediaCenterWidgetApiSvc.playCtrlCollect(i, z);
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void selectListMediaPlay(int i, int i2, String str) {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.selectListMediaPlay(i, i2, str);
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPlayMediaList(int i) {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.ctrlPlayMediaList(i);
            }
        } catch (Exception unused) {
        }
    }

    @Override // ecarx.xsf.widget.interfaces.IControl
    public final void ctrlPauseMediaList(int i) {
        try {
            IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvc = this.f832a;
            if (iMediaCenterWidgetApiSvc != null) {
                iMediaCenterWidgetApiSvc.ctrlPauseMediaList(i);
            }
        } catch (Exception unused) {
        }
    }
}
