package com.flyme.auto.music.source.usage;

/* JADX INFO: loaded from: classes2.dex */
public enum MusicSourceType {
    SOURCE_TYPE_LOCAL(0),
    SOURCE_TYPE_USB(1),
    SOURCE_TYPE_BT(2),
    SOURCE_TYPE_FM(3),
    SOURCE_TYPE_AM(4),
    SOURCE_TYPE_AUX(5),
    SOURCE_TYPE_ONLINE(6),
    SOURCE_TYPE_USB2(7),
    SOURCE_TYPE_STATION(8),
    SOURCE_TYPE_NET_NEWS(9),
    SOURCE_TYPE_NET_VIDEO(10),
    SOURCE_TYPE_DAB(11),
    SOURCE_TYPE_ONLINE_RADIO(12),
    SOURCE_TYPE_CARPLAY(13),
    SOURCE_TYPE_ANDROID_AUTO(14),
    SOURCE_TYPE_CARLIFE(15),
    SOURCE_TYPE_HICAR(16),
    SOURCE_TYPE_KTV(17),
    SOURCE_TYPE_SHORT_VIDEO(18),
    SOURCE_TYPE_LONG_VIDEO(19),
    SOURCE_TYPE_FLYME_LINK(20),
    SOURCE_TYPE_FAVORITE_FM(33),
    SOURCE_TYPE_FAVORITE_AM(34),
    SOURCE_TYPE_FAVORITE_MUSIC(35);

    private int type;

    MusicSourceType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
