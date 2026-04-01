package ecarx.xsf.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IMusicPlaybackInfo extends IInterface {
    String getAlbum() throws RemoteException;

    String getAppIcon() throws RemoteException;

    String getAppName() throws RemoteException;

    String getArtist() throws RemoteException;

    Uri getArtwork() throws RemoteException;

    String getCurrentLyricSentence() throws RemoteException;

    long getDuration() throws RemoteException;

    PendingIntent getLaunchIntent() throws RemoteException;

    int getLoopMode() throws RemoteException;

    Uri getLyric() throws RemoteException;

    String getLyricContent() throws RemoteException;

    Uri getMediaPath() throws RemoteException;

    Uri getNextArtwork() throws RemoteException;

    String getPackageName() throws RemoteException;

    int getPlaybackStatus() throws RemoteException;

    PendingIntent getPlayerIntent() throws RemoteException;

    int getPlayingItemPositionInQueue() throws RemoteException;

    String getPlayingMediaListId() throws RemoteException;

    int getPlayingMediaListType() throws RemoteException;

    Uri getPreviousArtwork() throws RemoteException;

    String getRadioFrequency() throws RemoteException;

    int getRadioMode() throws RemoteException;

    String getRadioStationName() throws RemoteException;

    int getSourceType() throws RemoteException;

    String getTitle() throws RemoteException;

    String getUuid() throws RemoteException;

    int getVip() throws RemoteException;

    boolean isCollected() throws RemoteException;

    boolean isDownloaded() throws RemoteException;

    boolean isSupportCollect() throws RemoteException;

    boolean isSupportDownload() throws RemoteException;

    boolean isSupportLoopModeSwitch() throws RemoteException;

    boolean isSupportVrCtrlPlayStatus() throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicPlaybackInfo {
        private static final String DESCRIPTOR = "ecarx.xsf.mediacenter.IMusicPlaybackInfo";
        static final int TRANSACTION_getAlbum = 4;
        static final int TRANSACTION_getAppIcon = 26;
        static final int TRANSACTION_getAppName = 25;
        static final int TRANSACTION_getArtist = 3;
        static final int TRANSACTION_getArtwork = 16;
        static final int TRANSACTION_getCurrentLyricSentence = 14;
        static final int TRANSACTION_getDuration = 7;
        static final int TRANSACTION_getLaunchIntent = 1;
        static final int TRANSACTION_getLoopMode = 18;
        static final int TRANSACTION_getLyric = 13;
        static final int TRANSACTION_getLyricContent = 12;
        static final int TRANSACTION_getMediaPath = 10;
        static final int TRANSACTION_getNextArtwork = 17;
        static final int TRANSACTION_getPackageName = 27;
        static final int TRANSACTION_getPlaybackStatus = 11;
        static final int TRANSACTION_getPlayerIntent = 33;
        static final int TRANSACTION_getPlayingItemPositionInQueue = 8;
        static final int TRANSACTION_getPlayingMediaListId = 30;
        static final int TRANSACTION_getPlayingMediaListType = 32;
        static final int TRANSACTION_getPreviousArtwork = 15;
        static final int TRANSACTION_getRadioFrequency = 5;
        static final int TRANSACTION_getRadioMode = 19;
        static final int TRANSACTION_getRadioStationName = 6;
        static final int TRANSACTION_getSourceType = 9;
        static final int TRANSACTION_getTitle = 2;
        static final int TRANSACTION_getUuid = 24;
        static final int TRANSACTION_getVip = 31;
        static final int TRANSACTION_isCollected = 21;
        static final int TRANSACTION_isDownloaded = 23;
        static final int TRANSACTION_isSupportCollect = 20;
        static final int TRANSACTION_isSupportDownload = 22;
        static final int TRANSACTION_isSupportLoopModeSwitch = 28;
        static final int TRANSACTION_isSupportVrCtrlPlayStatus = 29;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicPlaybackInfo asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMusicPlaybackInfo)) {
                return (IMusicPlaybackInfo) iInterfaceQueryLocalInterface;
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
                    PendingIntent launchIntent = getLaunchIntent();
                    parcel2.writeNoException();
                    if (launchIntent != null) {
                        parcel2.writeInt(1);
                        launchIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String title = getTitle();
                    parcel2.writeNoException();
                    parcel2.writeString(title);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String artist = getArtist();
                    parcel2.writeNoException();
                    parcel2.writeString(artist);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String album = getAlbum();
                    parcel2.writeNoException();
                    parcel2.writeString(album);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String radioFrequency = getRadioFrequency();
                    parcel2.writeNoException();
                    parcel2.writeString(radioFrequency);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String radioStationName = getRadioStationName();
                    parcel2.writeNoException();
                    parcel2.writeString(radioStationName);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    long duration = getDuration();
                    parcel2.writeNoException();
                    parcel2.writeLong(duration);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int playingItemPositionInQueue = getPlayingItemPositionInQueue();
                    parcel2.writeNoException();
                    parcel2.writeInt(playingItemPositionInQueue);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sourceType = getSourceType();
                    parcel2.writeNoException();
                    parcel2.writeInt(sourceType);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri mediaPath = getMediaPath();
                    parcel2.writeNoException();
                    if (mediaPath != null) {
                        parcel2.writeInt(1);
                        mediaPath.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int playbackStatus = getPlaybackStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(playbackStatus);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String lyricContent = getLyricContent();
                    parcel2.writeNoException();
                    parcel2.writeString(lyricContent);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri lyric = getLyric();
                    parcel2.writeNoException();
                    if (lyric != null) {
                        parcel2.writeInt(1);
                        lyric.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentLyricSentence = getCurrentLyricSentence();
                    parcel2.writeNoException();
                    parcel2.writeString(currentLyricSentence);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri previousArtwork = getPreviousArtwork();
                    parcel2.writeNoException();
                    if (previousArtwork != null) {
                        parcel2.writeInt(1);
                        previousArtwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri artwork = getArtwork();
                    parcel2.writeNoException();
                    if (artwork != null) {
                        parcel2.writeInt(1);
                        artwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri nextArtwork = getNextArtwork();
                    parcel2.writeNoException();
                    if (nextArtwork != null) {
                        parcel2.writeInt(1);
                        nextArtwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int loopMode = getLoopMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(loopMode);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int radioMode = getRadioMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(radioMode);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSupportCollect = isSupportCollect();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSupportCollect ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCollected = isCollected();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCollected ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSupportDownload = isSupportDownload();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSupportDownload ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDownloaded = isDownloaded();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDownloaded ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    String uuid = getUuid();
                    parcel2.writeNoException();
                    parcel2.writeString(uuid);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String appName = getAppName();
                    parcel2.writeNoException();
                    parcel2.writeString(appName);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    String appIcon = getAppIcon();
                    parcel2.writeNoException();
                    parcel2.writeString(appIcon);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    String packageName = getPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(packageName);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSupportLoopModeSwitch = isSupportLoopModeSwitch();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSupportLoopModeSwitch ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSupportVrCtrlPlayStatus = isSupportVrCtrlPlayStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSupportVrCtrlPlayStatus ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    String playingMediaListId = getPlayingMediaListId();
                    parcel2.writeNoException();
                    parcel2.writeString(playingMediaListId);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vip = getVip();
                    parcel2.writeNoException();
                    parcel2.writeInt(vip);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int playingMediaListType = getPlayingMediaListType();
                    parcel2.writeNoException();
                    parcel2.writeInt(playingMediaListType);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    PendingIntent playerIntent = getPlayerIntent();
                    parcel2.writeNoException();
                    if (playerIntent != null) {
                        parcel2.writeInt(1);
                        playerIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class a implements IMusicPlaybackInfo {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static IMusicPlaybackInfo f692a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f693b;

            a(IBinder iBinder) {
                this.f693b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f693b;
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final PendingIntent getLaunchIntent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaunchIntent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getTitle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTitle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getArtist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getArtist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getAlbum() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlbum();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getRadioFrequency() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioFrequency();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getRadioStationName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioStationName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final long getDuration() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDuration();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final int getPlayingItemPositionInQueue() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlayingItemPositionInQueue();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final int getSourceType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final Uri getMediaPath() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaPath();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final int getPlaybackStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlaybackStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getLyricContent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLyricContent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final Uri getLyric() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLyric();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getCurrentLyricSentence() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentLyricSentence();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final Uri getPreviousArtwork() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreviousArtwork();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final Uri getArtwork() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getArtwork();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final Uri getNextArtwork() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNextArtwork();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final int getLoopMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLoopMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final int getRadioMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final boolean isSupportCollect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportCollect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final boolean isCollected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCollected();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final boolean isSupportDownload() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportDownload();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final boolean isDownloaded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDownloaded();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getUuid() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUuid();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getAppName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getAppIcon() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppIcon();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final boolean isSupportLoopModeSwitch() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportLoopModeSwitch();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final boolean isSupportVrCtrlPlayStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportVrCtrlPlayStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final String getPlayingMediaListId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlayingMediaListId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final int getVip() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVip();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final int getPlayingMediaListType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlayingMediaListType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
            public final PendingIntent getPlayerIntent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f693b.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlayerIntent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicPlaybackInfo iMusicPlaybackInfo) {
            if (a.f692a != null || iMusicPlaybackInfo == null) {
                return false;
            }
            a.f692a = iMusicPlaybackInfo;
            return true;
        }

        public static IMusicPlaybackInfo getDefaultImpl() {
            return a.f692a;
        }
    }
}
