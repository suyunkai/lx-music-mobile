package ecarx.xsf.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import ecarx.xsf.mediacenter.IMediaCenterClientToken;
import ecarx.xsf.mediacenter.IMusicClient;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.INewsClient;
import ecarx.xsf.mediacenter.INewsPlaybackInfo;
import ecarx.xsf.mediacenter.IRecommend;
import ecarx.xsf.mediacenter.IVideoClient;
import ecarx.xsf.mediacenter.IVideoPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.vr.IMusicIntentObserver;
import ecarx.xsf.mediacenter.vr.INewsIntentObserver;
import ecarx.xsf.mediacenter.vr.IRadioIntentObserver;
import ecarx.xsf.mediacenter.vr.channel.IVrChannelInfo;
import ecarx.xsf.mediacenter.vr.channel.IVrChannelObserver;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaCenterSvc extends IInterface {
    boolean asyncSendVrChannelResult(IMediaCenterClientToken iMediaCenterClientToken, IVrChannelInfo iVrChannelInfo, String str) throws RemoteException;

    boolean cancelMusicCtrlCapabilityDeclaration(IMusicIntentObserver iMusicIntentObserver) throws RemoteException;

    boolean cancelNewsCtrlCapabilityDeclaration(INewsIntentObserver iNewsIntentObserver) throws RemoteException;

    boolean cancelRadioCtrlCapabilityDeclaration(IRadioIntentObserver iRadioIntentObserver) throws RemoteException;

    boolean cancelSupportCollectTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException;

    boolean cancelSupportDownloadTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException;

    boolean cancelVrChannelCapability(IMediaCenterClientToken iMediaCenterClientToken, IVrChannelInfo iVrChannelInfo) throws RemoteException;

    void declareMediaCenterCapability(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException;

    boolean declareMusicCtrlCapability(int[] iArr, IMusicIntentObserver iMusicIntentObserver) throws RemoteException;

    boolean declareNewsCtrlCapability(INewsIntentObserver iNewsIntentObserver) throws RemoteException;

    boolean declareRadioCtrlCapability(int[] iArr, IRadioIntentObserver iRadioIntentObserver) throws RemoteException;

    boolean declareSupportCollectTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException;

    boolean declareSupportDownloadTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException;

    boolean declareVrChannelCapability(IMediaCenterClientToken iMediaCenterClientToken, IVrChannelInfo iVrChannelInfo, IVrChannelObserver iVrChannelObserver) throws RemoteException;

    void declareVrCtrlPriority(String str, int i, IMusicIntentObserver iMusicIntentObserver, IRadioIntentObserver iRadioIntentObserver, INewsIntentObserver iNewsIntentObserver) throws RemoteException;

    IBinder getMediaControlClientApi() throws RemoteException;

    IBinder getMediaControllerApi() throws RemoteException;

    IBinder getStateBinder() throws RemoteException;

    String queryCurrentFocusClient(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException;

    IMediaCenterClientToken registerInMusic(String str, IMusicClient iMusicClient) throws RemoteException;

    IMediaCenterClientToken registerInNews(String str, INewsClient iNewsClient) throws RemoteException;

    IMediaCenterClientToken registerInVideo(String str, IVideoClient iVideoClient) throws RemoteException;

    IMediaCenterClientToken registerMusic(IMusicClient iMusicClient) throws RemoteException;

    IMediaCenterClientToken registerNews(INewsClient iNewsClient) throws RemoteException;

    IMediaCenterClientToken registerVideo(IVideoClient iVideoClient) throws RemoteException;

    boolean requestPlay(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException;

    boolean unregister(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException;

    void updateCollectMsg(IMediaCenterClientToken iMediaCenterClientToken, int i, String str) throws RemoteException;

    void updateCollectMsgByUUID(IMediaCenterClientToken iMediaCenterClientToken, int i, int i2, String str, int i3, String str2) throws RemoteException;

    void updateCurrentLyric(IMediaCenterClientToken iMediaCenterClientToken, String str) throws RemoteException;

    void updateCurrentProgress(IMediaCenterClientToken iMediaCenterClientToken, long j) throws RemoteException;

    boolean updateCurrentRecommendInfo(IMediaCenterClientToken iMediaCenterClientToken, IRecommend iRecommend) throws RemoteException;

    void updateCurrentSourceType(IMediaCenterClientToken iMediaCenterClientToken, int i) throws RemoteException;

    void updateErrorMsg(IMediaCenterClientToken iMediaCenterClientToken, int i, String str) throws RemoteException;

    boolean updateMediaContent(IMediaCenterClientToken iMediaCenterClientToken, List<IContent> list) throws RemoteException;

    void updateMediaContentTypeList(IMediaCenterClientToken iMediaCenterClientToken, List<IMediaContentType> list) throws RemoteException;

    void updateMediaList(IMediaCenterClientToken iMediaCenterClientToken, int i, int i2, List<IMedia> list) throws RemoteException;

    void updateMediaPlayList(IMediaCenterClientToken iMediaCenterClientToken, IMediaList iMediaList) throws RemoteException;

    void updateMediaSourceTypeList(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException;

    boolean updateMultiMediaList(IMediaCenterClientToken iMediaCenterClientToken, IMediaLists iMediaLists) throws RemoteException;

    boolean updateMusicPlaybackState(IMediaCenterClientToken iMediaCenterClientToken, IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException;

    boolean updateNewsPlaybackState(IMediaCenterClientToken iMediaCenterClientToken, INewsPlaybackInfo iNewsPlaybackInfo) throws RemoteException;

    void updatePlaylist(IMediaCenterClientToken iMediaCenterClientToken, int i, List<IMedia> list) throws RemoteException;

    boolean updateVideoPlaybackState(IMediaCenterClientToken iMediaCenterClientToken, IVideoPlaybackInfo iVideoPlaybackInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaCenterSvc {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.IMediaCenterSvc";
        static final int TRANSACTION_asyncSendVrChannelResult = 36;
        static final int TRANSACTION_cancelMusicCtrlCapabilityDeclaration = 24;
        static final int TRANSACTION_cancelNewsCtrlCapabilityDeclaration = 28;
        static final int TRANSACTION_cancelRadioCtrlCapabilityDeclaration = 26;
        static final int TRANSACTION_cancelSupportCollectTypes = 16;
        static final int TRANSACTION_cancelSupportDownloadTypes = 18;
        static final int TRANSACTION_cancelVrChannelCapability = 35;
        static final int TRANSACTION_declareMediaCenterCapability = 30;
        static final int TRANSACTION_declareMusicCtrlCapability = 23;
        static final int TRANSACTION_declareNewsCtrlCapability = 27;
        static final int TRANSACTION_declareRadioCtrlCapability = 25;
        static final int TRANSACTION_declareSupportCollectTypes = 15;
        static final int TRANSACTION_declareSupportDownloadTypes = 17;
        static final int TRANSACTION_declareVrChannelCapability = 34;
        static final int TRANSACTION_declareVrCtrlPriority = 22;
        static final int TRANSACTION_getMediaControlClientApi = 32;
        static final int TRANSACTION_getMediaControllerApi = 33;
        static final int TRANSACTION_getStateBinder = 31;
        static final int TRANSACTION_queryCurrentFocusClient = 42;
        static final int TRANSACTION_registerInMusic = 19;
        static final int TRANSACTION_registerInNews = 20;
        static final int TRANSACTION_registerInVideo = 21;
        static final int TRANSACTION_registerMusic = 1;
        static final int TRANSACTION_registerNews = 2;
        static final int TRANSACTION_registerVideo = 3;
        static final int TRANSACTION_requestPlay = 5;
        static final int TRANSACTION_unregister = 4;
        static final int TRANSACTION_updateCollectMsg = 41;
        static final int TRANSACTION_updateCollectMsgByUUID = 44;
        static final int TRANSACTION_updateCurrentLyric = 14;
        static final int TRANSACTION_updateCurrentProgress = 10;
        static final int TRANSACTION_updateCurrentRecommendInfo = 13;
        static final int TRANSACTION_updateCurrentSourceType = 8;
        static final int TRANSACTION_updateErrorMsg = 37;
        static final int TRANSACTION_updateMediaContent = 38;
        static final int TRANSACTION_updateMediaContentTypeList = 43;
        static final int TRANSACTION_updateMediaList = 29;
        static final int TRANSACTION_updateMediaPlayList = 40;
        static final int TRANSACTION_updateMediaSourceTypeList = 7;
        static final int TRANSACTION_updateMultiMediaList = 39;
        static final int TRANSACTION_updateMusicPlaybackState = 6;
        static final int TRANSACTION_updateNewsPlaybackState = 12;
        static final int TRANSACTION_updatePlaylist = 9;
        static final int TRANSACTION_updateVideoPlaybackState = 11;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaCenterSvc asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaCenterSvc)) {
                return (IMediaCenterSvc) iInterfaceQueryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaCenterClientToken iMediaCenterClientTokenRegisterMusic = registerMusic(IMusicClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaCenterClientTokenRegisterMusic != null ? iMediaCenterClientTokenRegisterMusic.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaCenterClientToken iMediaCenterClientTokenRegisterNews = registerNews(INewsClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaCenterClientTokenRegisterNews != null ? iMediaCenterClientTokenRegisterNews.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaCenterClientToken iMediaCenterClientTokenRegisterVideo = registerVideo(IVideoClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaCenterClientTokenRegisterVideo != null ? iMediaCenterClientTokenRegisterVideo.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregister = unregister(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregister ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestPlay = requestPlay(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestPlay ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateMusicPlaybackState = updateMusicPlaybackState(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), IMusicPlaybackInfo.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateMusicPlaybackState ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMediaSourceTypeList(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCurrentSourceType(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePlaylist(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.createTypedArrayList(IMedia.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCurrentProgress(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateVideoPlaybackState = updateVideoPlaybackState(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), IVideoPlaybackInfo.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateVideoPlaybackState ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateNewsPlaybackState = updateNewsPlaybackState(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), INewsPlaybackInfo.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateNewsPlaybackState ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateCurrentRecommendInfo = updateCurrentRecommendInfo(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), IRecommend.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateCurrentRecommendInfo ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCurrentLyric(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeclareSupportCollectTypes = declareSupportCollectTypes(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeclareSupportCollectTypes ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelSupportCollectTypes = cancelSupportCollectTypes(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelSupportCollectTypes ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeclareSupportDownloadTypes = declareSupportDownloadTypes(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeclareSupportDownloadTypes ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelSupportDownloadTypes = cancelSupportDownloadTypes(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelSupportDownloadTypes ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaCenterClientToken iMediaCenterClientTokenRegisterInMusic = registerInMusic(parcel.readString(), IMusicClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaCenterClientTokenRegisterInMusic != null ? iMediaCenterClientTokenRegisterInMusic.asBinder() : null);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaCenterClientToken iMediaCenterClientTokenRegisterInNews = registerInNews(parcel.readString(), INewsClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaCenterClientTokenRegisterInNews != null ? iMediaCenterClientTokenRegisterInNews.asBinder() : null);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMediaCenterClientToken iMediaCenterClientTokenRegisterInVideo = registerInVideo(parcel.readString(), IVideoClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iMediaCenterClientTokenRegisterInVideo != null ? iMediaCenterClientTokenRegisterInVideo.asBinder() : null);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    declareVrCtrlPriority(parcel.readString(), parcel.readInt(), IMusicIntentObserver.Stub.asInterface(parcel.readStrongBinder()), IRadioIntentObserver.Stub.asInterface(parcel.readStrongBinder()), INewsIntentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeclareMusicCtrlCapability = declareMusicCtrlCapability(parcel.createIntArray(), IMusicIntentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeclareMusicCtrlCapability ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelMusicCtrlCapabilityDeclaration = cancelMusicCtrlCapabilityDeclaration(IMusicIntentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelMusicCtrlCapabilityDeclaration ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeclareRadioCtrlCapability = declareRadioCtrlCapability(parcel.createIntArray(), IRadioIntentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeclareRadioCtrlCapability ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelRadioCtrlCapabilityDeclaration = cancelRadioCtrlCapabilityDeclaration(IRadioIntentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelRadioCtrlCapabilityDeclaration ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeclareNewsCtrlCapability = declareNewsCtrlCapability(INewsIntentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeclareNewsCtrlCapability ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelNewsCtrlCapabilityDeclaration = cancelNewsCtrlCapabilityDeclaration(INewsIntentObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelNewsCtrlCapabilityDeclaration ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMediaList(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.createTypedArrayList(IMedia.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    declareMediaCenterCapability(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder stateBinder = getStateBinder();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(stateBinder);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder mediaControlClientApi = getMediaControlClientApi();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(mediaControlClientApi);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder mediaControllerApi = getMediaControllerApi();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(mediaControllerApi);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeclareVrChannelCapability = declareVrChannelCapability(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? IVrChannelInfo.CREATOR.createFromParcel(parcel) : null, IVrChannelObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeclareVrChannelCapability ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelVrChannelCapability = cancelVrChannelCapability(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? IVrChannelInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelVrChannelCapability ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAsyncSendVrChannelResult = asyncSendVrChannelResult(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? IVrChannelInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAsyncSendVrChannelResult ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateErrorMsg(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateMediaContent = updateMediaContent(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createTypedArrayList(IContent.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateMediaContent ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateMultiMediaList = updateMultiMediaList(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? IMediaLists.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateMultiMediaList ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMediaPlayList(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? IMediaList.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCollectMsg(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strQueryCurrentFocusClient = queryCurrentFocusClient(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(strQueryCurrentFocusClient);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMediaContentTypeList(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.createTypedArrayList(IMediaContentType.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCollectMsgByUUID(IMediaCenterClientToken.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IMediaCenterSvc {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMediaCenterSvc f686a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f687b;

            a(IBinder iBinder) {
                this.f687b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f687b;
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IMediaCenterClientToken registerMusic(IMusicClient iMusicClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMusicClient != null ? iMusicClient.asBinder() : null);
                    if (!this.f687b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMusic(iMusicClient);
                    }
                    parcelObtain2.readException();
                    return IMediaCenterClientToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IMediaCenterClientToken registerNews(INewsClient iNewsClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNewsClient != null ? iNewsClient.asBinder() : null);
                    if (!this.f687b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerNews(iNewsClient);
                    }
                    parcelObtain2.readException();
                    return IMediaCenterClientToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IMediaCenterClientToken registerVideo(IVideoClient iVideoClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iVideoClient != null ? iVideoClient.asBinder() : null);
                    if (!this.f687b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerVideo(iVideoClient);
                    }
                    parcelObtain2.readException();
                    return IMediaCenterClientToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean unregister(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (!this.f687b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregister(iMediaCenterClientToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean requestPlay(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (!this.f687b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestPlay(iMediaCenterClientToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean updateMusicPlaybackState(IMediaCenterClientToken iMediaCenterClientToken, IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeStrongBinder(iMusicPlaybackInfo != null ? iMusicPlaybackInfo.asBinder() : null);
                    if (!this.f687b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateMusicPlaybackState(iMediaCenterClientToken, iMusicPlaybackInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateMediaSourceTypeList(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f687b.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMediaSourceTypeList(iMediaCenterClientToken, iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateCurrentSourceType(IMediaCenterClientToken iMediaCenterClientToken, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.f687b.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCurrentSourceType(iMediaCenterClientToken, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updatePlaylist(IMediaCenterClientToken iMediaCenterClientToken, int i, List<IMedia> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.f687b.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePlaylist(iMediaCenterClientToken, i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateCurrentProgress(IMediaCenterClientToken iMediaCenterClientToken, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeLong(j);
                    if (!this.f687b.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCurrentProgress(iMediaCenterClientToken, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean updateVideoPlaybackState(IMediaCenterClientToken iMediaCenterClientToken, IVideoPlaybackInfo iVideoPlaybackInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeStrongBinder(iVideoPlaybackInfo != null ? iVideoPlaybackInfo.asBinder() : null);
                    if (!this.f687b.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateVideoPlaybackState(iMediaCenterClientToken, iVideoPlaybackInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean updateNewsPlaybackState(IMediaCenterClientToken iMediaCenterClientToken, INewsPlaybackInfo iNewsPlaybackInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeStrongBinder(iNewsPlaybackInfo != null ? iNewsPlaybackInfo.asBinder() : null);
                    if (!this.f687b.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateNewsPlaybackState(iMediaCenterClientToken, iNewsPlaybackInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean updateCurrentRecommendInfo(IMediaCenterClientToken iMediaCenterClientToken, IRecommend iRecommend) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeStrongBinder(iRecommend != null ? iRecommend.asBinder() : null);
                    if (!this.f687b.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateCurrentRecommendInfo(iMediaCenterClientToken, iRecommend);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateCurrentLyric(IMediaCenterClientToken iMediaCenterClientToken, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.f687b.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCurrentLyric(iMediaCenterClientToken, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean declareSupportCollectTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f687b.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().declareSupportCollectTypes(iMediaCenterClientToken, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean cancelSupportCollectTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f687b.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelSupportCollectTypes(iMediaCenterClientToken, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean declareSupportDownloadTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f687b.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().declareSupportDownloadTypes(iMediaCenterClientToken, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean cancelSupportDownloadTypes(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f687b.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelSupportDownloadTypes(iMediaCenterClientToken, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IMediaCenterClientToken registerInMusic(String str, IMusicClient iMusicClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iMusicClient != null ? iMusicClient.asBinder() : null);
                    if (!this.f687b.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerInMusic(str, iMusicClient);
                    }
                    parcelObtain2.readException();
                    return IMediaCenterClientToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IMediaCenterClientToken registerInNews(String str, INewsClient iNewsClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iNewsClient != null ? iNewsClient.asBinder() : null);
                    if (!this.f687b.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerInNews(str, iNewsClient);
                    }
                    parcelObtain2.readException();
                    return IMediaCenterClientToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IMediaCenterClientToken registerInVideo(String str, IVideoClient iVideoClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iVideoClient != null ? iVideoClient.asBinder() : null);
                    if (!this.f687b.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerInVideo(str, iVideoClient);
                    }
                    parcelObtain2.readException();
                    return IMediaCenterClientToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void declareVrCtrlPriority(String str, int i, IMusicIntentObserver iMusicIntentObserver, IRadioIntentObserver iRadioIntentObserver, INewsIntentObserver iNewsIntentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iMusicIntentObserver != null ? iMusicIntentObserver.asBinder() : null);
                    parcelObtain.writeStrongBinder(iRadioIntentObserver != null ? iRadioIntentObserver.asBinder() : null);
                    parcelObtain.writeStrongBinder(iNewsIntentObserver != null ? iNewsIntentObserver.asBinder() : null);
                    if (!this.f687b.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().declareVrCtrlPriority(str, i, iMusicIntentObserver, iRadioIntentObserver, iNewsIntentObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean declareMusicCtrlCapability(int[] iArr, IMusicIntentObserver iMusicIntentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStrongBinder(iMusicIntentObserver != null ? iMusicIntentObserver.asBinder() : null);
                    if (!this.f687b.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().declareMusicCtrlCapability(iArr, iMusicIntentObserver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean cancelMusicCtrlCapabilityDeclaration(IMusicIntentObserver iMusicIntentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMusicIntentObserver != null ? iMusicIntentObserver.asBinder() : null);
                    if (!this.f687b.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelMusicCtrlCapabilityDeclaration(iMusicIntentObserver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean declareRadioCtrlCapability(int[] iArr, IRadioIntentObserver iRadioIntentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStrongBinder(iRadioIntentObserver != null ? iRadioIntentObserver.asBinder() : null);
                    if (!this.f687b.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().declareRadioCtrlCapability(iArr, iRadioIntentObserver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean cancelRadioCtrlCapabilityDeclaration(IRadioIntentObserver iRadioIntentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRadioIntentObserver != null ? iRadioIntentObserver.asBinder() : null);
                    if (!this.f687b.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelRadioCtrlCapabilityDeclaration(iRadioIntentObserver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean declareNewsCtrlCapability(INewsIntentObserver iNewsIntentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNewsIntentObserver != null ? iNewsIntentObserver.asBinder() : null);
                    if (!this.f687b.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().declareNewsCtrlCapability(iNewsIntentObserver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean cancelNewsCtrlCapabilityDeclaration(INewsIntentObserver iNewsIntentObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNewsIntentObserver != null ? iNewsIntentObserver.asBinder() : null);
                    if (!this.f687b.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelNewsCtrlCapabilityDeclaration(iNewsIntentObserver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateMediaList(IMediaCenterClientToken iMediaCenterClientToken, int i, int i2, List<IMedia> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeTypedList(list);
                    if (!this.f687b.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMediaList(iMediaCenterClientToken, i, i2, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void declareMediaCenterCapability(IMediaCenterClientToken iMediaCenterClientToken, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.f687b.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().declareMediaCenterCapability(iMediaCenterClientToken, iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IBinder getStateBinder() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f687b.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStateBinder();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IBinder getMediaControlClientApi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f687b.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaControlClientApi();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final IBinder getMediaControllerApi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f687b.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaControllerApi();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean declareVrChannelCapability(IMediaCenterClientToken iMediaCenterClientToken, IVrChannelInfo iVrChannelInfo, IVrChannelObserver iVrChannelObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (iVrChannelInfo != null) {
                        parcelObtain.writeInt(1);
                        iVrChannelInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iVrChannelObserver != null ? iVrChannelObserver.asBinder() : null);
                    if (!this.f687b.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().declareVrChannelCapability(iMediaCenterClientToken, iVrChannelInfo, iVrChannelObserver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean cancelVrChannelCapability(IMediaCenterClientToken iMediaCenterClientToken, IVrChannelInfo iVrChannelInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (iVrChannelInfo != null) {
                        parcelObtain.writeInt(1);
                        iVrChannelInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f687b.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelVrChannelCapability(iMediaCenterClientToken, iVrChannelInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean asyncSendVrChannelResult(IMediaCenterClientToken iMediaCenterClientToken, IVrChannelInfo iVrChannelInfo, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (iVrChannelInfo != null) {
                        parcelObtain.writeInt(1);
                        iVrChannelInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.f687b.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().asyncSendVrChannelResult(iMediaCenterClientToken, iVrChannelInfo, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateErrorMsg(IMediaCenterClientToken iMediaCenterClientToken, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f687b.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateErrorMsg(iMediaCenterClientToken, i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean updateMediaContent(IMediaCenterClientToken iMediaCenterClientToken, List<IContent> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeTypedList(list);
                    if (!this.f687b.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateMediaContent(iMediaCenterClientToken, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final boolean updateMultiMediaList(IMediaCenterClientToken iMediaCenterClientToken, IMediaLists iMediaLists) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (iMediaLists != null) {
                        parcelObtain.writeInt(1);
                        iMediaLists.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f687b.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateMultiMediaList(iMediaCenterClientToken, iMediaLists);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateMediaPlayList(IMediaCenterClientToken iMediaCenterClientToken, IMediaList iMediaList) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (iMediaList != null) {
                        parcelObtain.writeInt(1);
                        iMediaList.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f687b.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMediaPlayList(iMediaCenterClientToken, iMediaList);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateCollectMsg(IMediaCenterClientToken iMediaCenterClientToken, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.f687b.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCollectMsg(iMediaCenterClientToken, i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final String queryCurrentFocusClient(IMediaCenterClientToken iMediaCenterClientToken) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    if (!this.f687b.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryCurrentFocusClient(iMediaCenterClientToken);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateMediaContentTypeList(IMediaCenterClientToken iMediaCenterClientToken, List<IMediaContentType> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeTypedList(list);
                    if (!this.f687b.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateMediaContentTypeList(iMediaCenterClientToken, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMediaCenterSvc
            public final void updateCollectMsgByUUID(IMediaCenterClientToken iMediaCenterClientToken, int i, int i2, String str, int i3, String str2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaCenterClientToken != null ? iMediaCenterClientToken.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str2);
                    try {
                        if (!this.f687b.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().updateCollectMsgByUUID(iMediaCenterClientToken, i, i2, str, i3, str2);
                        } else {
                            parcelObtain2.readException();
                        }
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        public static boolean setDefaultImpl(IMediaCenterSvc iMediaCenterSvc) {
            if (a.f686a != null || iMediaCenterSvc == null) {
                return false;
            }
            a.f686a = iMediaCenterSvc;
            return true;
        }

        public static IMediaCenterSvc getDefaultImpl() {
            return a.f686a;
        }
    }
}
