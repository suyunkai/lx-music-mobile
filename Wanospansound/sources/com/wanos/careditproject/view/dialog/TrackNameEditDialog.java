package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.DialogTrackNameEditBinding;
import com.wanos.careditproject.utils.EditingUtils;
import java.lang.Character;

/* JADX INFO: loaded from: classes3.dex */
public class TrackNameEditDialog extends Dialog implements View.OnClickListener {
    private DialogTrackNameEditBinding binding;
    private boolean isFiltering;
    private TrackNameChangeListener listener;
    private String name;

    public interface TrackNameChangeListener {
        void onChange(String str);
    }

    public TrackNameEditDialog(Context context, String str, TrackNameChangeListener trackNameChangeListener) {
        super(context, R.style.FullScreenDialogTheme);
        this.isFiltering = false;
        this.name = str;
        this.listener = trackNameChangeListener;
        initDialog(context);
    }

    public TrackNameEditDialog(Context context, int i) {
        super(context, i);
        this.name = "";
        this.isFiltering = false;
        initDialog(context);
    }

    public void initDialog(Context context) {
        DialogTrackNameEditBinding dialogTrackNameEditBindingInflate = DialogTrackNameEditBinding.inflate(getLayoutInflater());
        this.binding = dialogTrackNameEditBindingInflate;
        setContentView(dialogTrackNameEditBindingInflate.getRoot());
        initView();
        initListener();
    }

    public void initView() {
        if (!this.name.equals("")) {
            this.binding.etTrackName.setText(this.name);
        }
        this.binding.etTrackName.addTextChangedListener(new TextWatcher() { // from class: com.wanos.careditproject.view.dialog.TrackNameEditDialog.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TrackNameEditDialog.this.isFiltering) {
                    return;
                }
                EditingUtils.log("onTextChanged 1");
                String strFilterEmojis = TrackNameEditDialog.this.filterEmojis(charSequence.toString());
                if (charSequence.toString().equals(strFilterEmojis)) {
                    return;
                }
                TrackNameEditDialog.this.isFiltering = true;
                TrackNameEditDialog.this.binding.etTrackName.setText(strFilterEmojis);
                TrackNameEditDialog.this.binding.etTrackName.setSelection(strFilterEmojis.length());
                TrackNameEditDialog.this.isFiltering = false;
            }
        });
    }

    public void initListener() {
        this.binding.btnBack.setOnClickListener(this);
        this.binding.btnDialogLeft.setOnClickListener(this);
        this.binding.btnDialogRight.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_dialog_left) {
            String string = this.binding.etTrackName.getText().toString();
            if (!StringUtils.isEmpty(string)) {
                TrackNameChangeListener trackNameChangeListener = this.listener;
                if (trackNameChangeListener != null) {
                    trackNameChangeListener.onChange(string);
                }
                dismiss();
                return;
            }
            ToastUtils.showShort("名称不能为空！");
            return;
        }
        if (id == R.id.btn_dialog_right) {
            dismiss();
        } else if (id == R.id.btn_back) {
            dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String filterEmojis(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c2 : str.toCharArray()) {
            if (!Character.isSurrogate(c2) && !isEmoji(c2)) {
                sb.append(c2);
            }
        }
        return sb.toString();
    }

    private boolean isEmoji(char c2) {
        return Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.EMOTICONS || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.TIBETAN || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.HANGUL_JAMO || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.HANGUL_SYLLABLES || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || Character.UnicodeBlock.of(c2) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
    }
}
