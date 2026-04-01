package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.wanos.bean.EditGetPictureListBean;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.DialogSelectProjectIconBinding;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.view.adapter.SelectProjectIconAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SelectProjectIconDialog extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "wanos:[CreatorSettingDialog]";
    private SelectProjectIconAdapter adapter;
    private DialogSelectProjectIconBinding binding;
    private SelectIconListener listener;
    private String selectPic;

    public interface SelectIconListener {
        void onSelectIcon(String str);
    }

    public SelectProjectIconDialog(String str, SelectIconListener selectIconListener) {
        this.selectPic = str;
        this.listener = selectIconListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = DialogSelectProjectIconBinding.inflate(getLayoutInflater());
        initView();
        setCancelable(true);
        return this.binding.getRoot();
    }

    private void initView() {
        this.binding.btnClose.setOnClickListener(this);
        this.binding.btnSure.setOnClickListener(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<EditGetPictureListBean.ProjectPictureInfo> picList = EditingParams.getInstance().getPicList();
        this.binding.rvIconList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.adapter = new SelectProjectIconAdapter(getActivity(), picList, this.selectPic);
        this.binding.rvIconList.setAdapter(this.adapter);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_close) {
            dismissAllowingStateLoss();
            return;
        }
        if (id == R.id.btn_sure) {
            String selectPic = this.adapter.getSelectPic();
            SelectIconListener selectIconListener = this.listener;
            if (selectIconListener != null) {
                selectIconListener.onSelectIcon(selectPic);
            }
            dismissAllowingStateLoss();
        }
    }
}
