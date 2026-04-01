package oooOO;

import kotlinx.coroutines.flow.Flow;
import retrofit2.http.POST;

/* JADX INFO: loaded from: classes3.dex */
public interface oooOOO00 {
    @POST("/robot/send")
    Flow<String> oooOO0oo();
}
