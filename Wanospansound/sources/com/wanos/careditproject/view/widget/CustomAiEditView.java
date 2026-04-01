package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.careditproject.R;
import com.wanos.careditproject.adapter.AiStyleAdapter;
import com.wanos.careditproject.data.bean.AiRandomState;
import com.wanos.careditproject.databinding.WidgetAiEditViewBinding;
import com.wanos.careditproject.view.AiStylePopupWindow;
import com.wanos.careditproject.view.viewmodel.CustomAiEditViewModel;
import com.wanos.careditproject.view.widget.CustomAiEditView;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.ToastUtil;

/* JADX INFO: loaded from: classes3.dex */
public class CustomAiEditView extends FrameLayout {
    private static final String TAG = "CustomAiEditView";
    private WidgetAiEditViewBinding binding;
    private AiStylePopupWindow mAiEmotionPopupWindow;
    private AiStylePopupWindow mAiStylePopupWindow;
    private final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private final Rect mGlobalLayoutRect;
    private boolean mKeyBoardState;
    private final TextWatcher mTextWatcher;
    private CustomAiEditViewModel viewModel;

    public enum EditState {
        SELECT_MODE,
        INPUT_MODE,
        SELECT_ED_MODE
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CustomAiEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTextWatcher = new TextWatcher() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence != null) {
                    CustomAiEditView.this.binding.tvInputLength.setText(charSequence.length() + "/100");
                    CustomAiEditView.this.viewModel.setInputContent(charSequence);
                    if (charSequence.length() > 0) {
                        CustomAiEditView.this.viewModel.setEditMode(EditState.INPUT_MODE);
                    }
                }
            }
        };
        this.mGlobalLayoutRect = new Rect();
        this.mKeyBoardState = false;
        this.mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView.2
            private int mHistoreHeight = 0;
            private int minKeyboardHeightPx;

            {
                this.minKeyboardHeightPx = CustomAiEditView.this.getResources().getDimensionPixelSize(R.dimen.ai_keyboard_height);
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (CustomAiEditView.this.viewModel.editModel.getValue() != EditState.INPUT_MODE) {
                    CustomAiEditView.this.mGlobalLayoutRect.set(0, 0, 0, 0);
                    CustomAiEditView.this.getRootView().getWindowVisibleDisplayFrame(CustomAiEditView.this.mGlobalLayoutRect);
                    int iHeight = CustomAiEditView.this.mGlobalLayoutRect.height();
                    int i = this.mHistoreHeight;
                    if (i == 0) {
                        this.mHistoreHeight = iHeight;
                        return;
                    }
                    boolean z = Math.abs(iHeight - i) > this.minKeyboardHeightPx;
                    if (CustomAiEditView.this.mKeyBoardState == z) {
                        return;
                    }
                    CustomAiEditView.this.mKeyBoardState = z;
                    if (z) {
                        Log.i(CustomAiEditView.TAG, "onGlobalLayout: 软键盘弹出，编辑状态 = " + CustomAiEditView.this.viewModel.editModel.getValue());
                        if (CustomAiEditView.this.viewModel.editModel.getValue() == EditState.SELECT_MODE) {
                            CustomAiEditView.this.viewModel.setEditMode(EditState.SELECT_ED_MODE);
                            return;
                        }
                        return;
                    }
                    Log.i(CustomAiEditView.TAG, "onGlobalLayout: 软键盘收回，编辑状态 = " + CustomAiEditView.this.viewModel.editModel.getValue());
                    if (CustomAiEditView.this.viewModel.editModel.getValue() == EditState.SELECT_ED_MODE) {
                        CustomAiEditView.this.viewModel.setEditMode(EditState.SELECT_MODE);
                        return;
                    }
                    return;
                }
                Log.d(CustomAiEditView.TAG, "onGlobalLayout: 输入模式，跳出");
            }
        };
        this.binding = WidgetAiEditViewBinding.inflate(LayoutInflater.from(context), this, true);
        CustomAiEditViewModel customAiEditViewModel = (CustomAiEditViewModel) new ViewModelProvider((ViewModelStoreOwner) context).get(CustomAiEditViewModel.class);
        this.viewModel = customAiEditViewModel;
        LifecycleOwner lifecycleOwner = (LifecycleOwner) context;
        customAiEditViewModel.createButtonEnable.observe(lifecycleOwner, new Observer() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m435xc94bef35((Boolean) obj);
            }
        });
        this.viewModel.selectedTextBackgroundColor.observe(lifecycleOwner, new Observer() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m436xf2a04476((Integer) obj);
            }
        });
        this.viewModel.editModel.observe(lifecycleOwner, new Observer() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m437x1bf499b7((CustomAiEditView.EditState) obj);
            }
        });
        this.viewModel.styleContent.observe(lifecycleOwner, new Observer() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m438x4548eef8((String) obj);
            }
        });
        this.viewModel.emotionContent.observe(lifecycleOwner, new Observer() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m439x6e9d4439((String) obj);
            }
        });
        this.viewModel.requestRandomPrompt.observe(lifecycleOwner, new Observer() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setLabelsText((String) obj);
            }
        });
        this.viewModel.randomPromptState.observe(lifecycleOwner, new Observer() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m440x97f1997a((AiRandomState) obj);
            }
        });
        this.binding.llSelect.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m441xc145eebb(view);
            }
        });
        this.binding.etInput.setOnKeyListener(new View.OnKeyListener() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(CustomAiEditView.TAG, "onKey: " + keyEvent.getAction() + " keyCode: " + i);
                if (keyEvent.getAction() != 1 || i != 67) {
                    return false;
                }
                CustomAiEditView.this.viewModel.setEditMode(EditState.INPUT_MODE);
                return false;
            }
        });
        this.binding.btnStyleType.setOnClickListener(new AnonymousClass4(context));
        this.binding.btnEmotionType.setOnClickListener(new AnonymousClass5(context));
        this.binding.btnRandomContent.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView.6
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (CustomAiEditView.this.viewModel.randomPromptState.getValue() != AiRandomState.LOADING) {
                    CustomAiEditView.this.viewModel.requestRandomPrompt();
                } else {
                    ToastUtil.showMsg(StringUtils.getString(R.string.ai_random_action));
                }
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-careditproject-view-widget-CustomAiEditView, reason: not valid java name */
    /* synthetic */ void m435xc94bef35(Boolean bool) {
        this.binding.btnCreate.setEnabled(bool.booleanValue());
    }

    /* JADX INFO: renamed from: lambda$new$1$com-wanos-careditproject-view-widget-CustomAiEditView, reason: not valid java name */
    /* synthetic */ void m436xf2a04476(Integer num) {
        this.binding.tvSelectMode1.setBackgroundColor(num.intValue());
        this.binding.tvSelectMode2.setBackgroundColor(num.intValue());
        this.binding.tvSelectMode3.setBackgroundColor(num.intValue());
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.view.widget.CustomAiEditView$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$careditproject$view$widget$CustomAiEditView$EditState;

        static {
            int[] iArr = new int[EditState.values().length];
            $SwitchMap$com$wanos$careditproject$view$widget$CustomAiEditView$EditState = iArr;
            try {
                iArr[EditState.INPUT_MODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$view$widget$CustomAiEditView$EditState[EditState.SELECT_MODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$view$widget$CustomAiEditView$EditState[EditState.SELECT_ED_MODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$new$2$com-wanos-careditproject-view-widget-CustomAiEditView, reason: not valid java name */
    /* synthetic */ void m437x1bf499b7(EditState editState) {
        int i = AnonymousClass7.$SwitchMap$com$wanos$careditproject$view$widget$CustomAiEditView$EditState[editState.ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.binding.etInput.setText("");
                this.binding.llSelect.setVisibility(0);
                this.binding.etInput.setVisibility(8);
                this.binding.tvInputLength.setVisibility(8);
                return;
            }
            if (i != 3) {
                return;
            }
            this.binding.llSelect.setVisibility(0);
            this.binding.etInput.setVisibility(0);
            this.binding.etInput.setCursorVisible(false);
            this.binding.tvInputLength.setVisibility(8);
            showSoftInput();
            return;
        }
        this.binding.llSelect.setVisibility(8);
        this.binding.etInput.setVisibility(0);
        this.binding.etInput.setCursorVisible(true);
        this.binding.tvInputLength.setVisibility(0);
        AiStylePopupWindow aiStylePopupWindow = this.mAiStylePopupWindow;
        if (aiStylePopupWindow != null && aiStylePopupWindow.isShowing()) {
            this.mAiStylePopupWindow.dismiss();
        }
        AiStylePopupWindow aiStylePopupWindow2 = this.mAiEmotionPopupWindow;
        if (aiStylePopupWindow2 == null || !aiStylePopupWindow2.isShowing()) {
            return;
        }
        this.mAiEmotionPopupWindow.dismiss();
    }

    /* JADX INFO: renamed from: lambda$new$3$com-wanos-careditproject-view-widget-CustomAiEditView, reason: not valid java name */
    /* synthetic */ void m438x4548eef8(String str) {
        this.binding.btnStyleType.setText(str);
    }

    /* JADX INFO: renamed from: lambda$new$4$com-wanos-careditproject-view-widget-CustomAiEditView, reason: not valid java name */
    /* synthetic */ void m439x6e9d4439(String str) {
        this.binding.btnEmotionType.setText(str);
    }

    /* JADX INFO: renamed from: lambda$new$5$com-wanos-careditproject-view-widget-CustomAiEditView, reason: not valid java name */
    /* synthetic */ void m440x97f1997a(AiRandomState aiRandomState) {
        if (aiRandomState == AiRandomState.LOADING) {
            this.binding.btnRandomContent.setVisibility(4);
            this.binding.lottieAnim.setVisibility(0);
            this.binding.lottieAnim.setAnimation(R.raw.anim_ai_play);
            this.binding.lottieAnim.playAnimation();
            return;
        }
        this.binding.lottieAnim.pauseAnimation();
        this.binding.lottieAnim.clearAnimation();
        this.binding.btnRandomContent.setVisibility(0);
        this.binding.lottieAnim.setVisibility(8);
    }

    /* JADX INFO: renamed from: lambda$new$6$com-wanos-careditproject-view-widget-CustomAiEditView, reason: not valid java name */
    /* synthetic */ void m441xc145eebb(View view) {
        if (this.viewModel.randomPromptState.getValue() == AiRandomState.LOADING) {
            ToastUtil.showMsg(StringUtils.getString(R.string.ai_random_action));
        } else if (this.viewModel.editModel.getValue() == EditState.SELECT_MODE) {
            this.viewModel.setEditMode(EditState.SELECT_ED_MODE);
        }
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.view.widget.CustomAiEditView$4, reason: invalid class name */
    class AnonymousClass4 extends AnitClick {
        final /* synthetic */ Context val$context;

        AnonymousClass4(Context context) {
            this.val$context = context;
        }

        @Override // com.wanos.media.util.AnitClick
        public void onAnitClick(View view) {
            if (CustomAiEditView.this.viewModel.editModel.getValue() != EditState.INPUT_MODE && CustomAiEditView.this.viewModel.editModel.getValue() != EditState.SELECT_ED_MODE) {
                if (CustomAiEditView.this.viewModel.randomPromptState.getValue() == AiRandomState.LOADING) {
                    ToastUtil.showMsg(StringUtils.getString(R.string.ai_random_action));
                    return;
                }
                CustomAiEditView.this.mAiStylePopupWindow = new AiStylePopupWindow(this.val$context, 0, CustomAiEditView.this.viewModel.styleContent.getValue());
                CustomAiEditView.this.mAiStylePopupWindow.showAsDropDown(view, -((int) ((CustomAiEditView.this.mAiStylePopupWindow.getWidth() - view.getWidth()) / 2.0f)), this.val$context.getResources().getDimensionPixelOffset(R.dimen.ai_window_top));
                CustomAiEditView.this.mAiStylePopupWindow.setItemClickListener(new AiStyleAdapter.OnItemClickListener() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$4$$ExternalSyntheticLambda0
                    @Override // com.wanos.careditproject.adapter.AiStyleAdapter.OnItemClickListener
                    public final void onItemClick(String str) {
                        this.f$0.m442xb3055d6(str);
                    }
                });
                return;
            }
            Log.w(CustomAiEditView.TAG, "onAnitClick: 编辑模式下无法选择风格类型");
        }

        /* JADX INFO: renamed from: lambda$onAnitClick$0$com-wanos-careditproject-view-widget-CustomAiEditView$4, reason: not valid java name */
        /* synthetic */ void m442xb3055d6(String str) {
            CustomAiEditView.this.viewModel.setStyleContent(str);
            CustomAiEditView.this.mAiStylePopupWindow.dismiss();
        }
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.view.widget.CustomAiEditView$5, reason: invalid class name */
    class AnonymousClass5 extends AnitClick {
        final /* synthetic */ Context val$context;

        AnonymousClass5(Context context) {
            this.val$context = context;
        }

        @Override // com.wanos.media.util.AnitClick
        public void onAnitClick(View view) {
            if (CustomAiEditView.this.viewModel.editModel.getValue() != EditState.INPUT_MODE && CustomAiEditView.this.viewModel.editModel.getValue() != EditState.SELECT_ED_MODE) {
                if (CustomAiEditView.this.viewModel.randomPromptState.getValue() == AiRandomState.LOADING) {
                    ToastUtil.showMsg(StringUtils.getString(R.string.ai_random_action));
                    return;
                }
                CustomAiEditView.this.mAiEmotionPopupWindow = new AiStylePopupWindow(this.val$context, 1, CustomAiEditView.this.viewModel.emotionContent.getValue());
                CustomAiEditView.this.mAiEmotionPopupWindow.showAsDropDown(view, -((int) ((CustomAiEditView.this.mAiEmotionPopupWindow.getWidth() - view.getWidth()) / 2.0f)), this.val$context.getResources().getDimensionPixelOffset(R.dimen.ai_window_top));
                CustomAiEditView.this.mAiEmotionPopupWindow.setItemClickListener(new AiStyleAdapter.OnItemClickListener() { // from class: com.wanos.careditproject.view.widget.CustomAiEditView$5$$ExternalSyntheticLambda0
                    @Override // com.wanos.careditproject.adapter.AiStyleAdapter.OnItemClickListener
                    public final void onItemClick(String str) {
                        this.f$0.m443xb3055d7(str);
                    }
                });
                return;
            }
            Log.w(CustomAiEditView.TAG, "onAnitClick: 编辑模式下无法选择情绪类型");
        }

        /* JADX INFO: renamed from: lambda$onAnitClick$0$com-wanos-careditproject-view-widget-CustomAiEditView$5, reason: not valid java name */
        /* synthetic */ void m443xb3055d7(String str) {
            CustomAiEditView.this.viewModel.setEmotionContent(str);
            CustomAiEditView.this.mAiEmotionPopupWindow.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.binding.etInput.addTextChangedListener(this.mTextWatcher);
        getRootView().getViewTreeObserver().addOnGlobalLayoutListener(this.mGlobalLayoutListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.binding.etInput.removeTextChangedListener(this.mTextWatcher);
        getRootView().getViewTreeObserver().removeOnGlobalLayoutListener(this.mGlobalLayoutListener);
    }

    public void hideSoftInput() {
        KeyboardUtils.hideSoftInput(this.binding.etInput);
    }

    private void showSoftInput() {
        KeyboardUtils.showSoftInput(this.binding.etInput);
    }

    public void setLabelsText(String str) {
        if (StringUtils.isEmpty(str)) {
            Log.e(TAG, "setLabelsText: text = " + str);
            return;
        }
        this.viewModel.setEditMode(EditState.INPUT_MODE);
        this.binding.etInput.setText(str);
        this.binding.etInput.setSelection(str.length());
    }

    public String getAiContent() {
        if (this.viewModel.editModel.getValue() == EditState.INPUT_MODE) {
            return this.binding.etInput.getText().toString().trim();
        }
        return StringUtils.getString(R.string.ai_content_1) + this.binding.btnStyleType.getText().toString().trim() + StringUtils.getString(R.string.ai_content_2) + this.binding.btnEmotionType.getText().toString().trim() + StringUtils.getString(R.string.ai_content_3);
    }

    public AiRandomState getRandomState() {
        return this.viewModel.randomPromptState.getValue();
    }

    public void setCreateClickListener(View.OnClickListener onClickListener) {
        this.binding.btnCreate.setOnClickListener(onClickListener);
    }
}
