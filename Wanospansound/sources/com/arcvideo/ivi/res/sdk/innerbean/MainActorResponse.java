package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class MainActorResponse {
    private final String character;
    private final String cover;
    private final String id;
    private final String n;

    public MainActorResponse(String id, String n, String character, String cover) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(n, "n");
        Intrinsics.checkNotNullParameter(character, "character");
        Intrinsics.checkNotNullParameter(cover, "cover");
        this.id = id;
        this.n = n;
        this.character = character;
        this.cover = cover;
    }

    public static /* synthetic */ MainActorResponse copy$default(MainActorResponse mainActorResponse, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mainActorResponse.id;
        }
        if ((i & 2) != 0) {
            str2 = mainActorResponse.n;
        }
        if ((i & 4) != 0) {
            str3 = mainActorResponse.character;
        }
        if ((i & 8) != 0) {
            str4 = mainActorResponse.cover;
        }
        return mainActorResponse.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.n;
    }

    public final String component3() {
        return this.character;
    }

    public final String component4() {
        return this.cover;
    }

    public final MainActorResponse copy(String id, String n, String character, String cover) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(n, "n");
        Intrinsics.checkNotNullParameter(character, "character");
        Intrinsics.checkNotNullParameter(cover, "cover");
        return new MainActorResponse(id, n, character, cover);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MainActorResponse)) {
            return false;
        }
        MainActorResponse mainActorResponse = (MainActorResponse) obj;
        return Intrinsics.areEqual(this.id, mainActorResponse.id) && Intrinsics.areEqual(this.n, mainActorResponse.n) && Intrinsics.areEqual(this.character, mainActorResponse.character) && Intrinsics.areEqual(this.cover, mainActorResponse.cover);
    }

    public final String getCharacter() {
        return this.character;
    }

    public final String getCover() {
        return this.cover;
    }

    public final String getId() {
        return this.id;
    }

    public final String getN() {
        return this.n;
    }

    public int hashCode() {
        return (((((this.id.hashCode() * 31) + this.n.hashCode()) * 31) + this.character.hashCode()) * 31) + this.cover.hashCode();
    }

    public String toString() {
        return "MainActorResponse(id=" + this.id + ", n=" + this.n + ", character=" + this.character + ", cover=" + this.cover + ')';
    }
}
