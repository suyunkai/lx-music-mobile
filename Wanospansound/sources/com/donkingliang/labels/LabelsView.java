package com.donkingliang.labels;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LabelsView extends ViewGroup implements View.OnClickListener, View.OnLongClickListener {
    private static final String KEY_BG_RES_ID_STATE = "key_bg_res_id_state";
    private static final String KEY_COMPULSORY_LABELS_STATE = "key_select_compulsory_state";
    private static final String KEY_INDICATOR_STATE = "key_indicator_state";
    private static final String KEY_LABELS_STATE = "key_labels_state";
    private static final String KEY_LABEL_GRAVITY_STATE = "key_label_gravity_state";
    private static final String KEY_LABEL_HEIGHT_STATE = "key_label_height_state";
    private static final String KEY_LABEL_WIDTH_STATE = "key_label_width_state";
    private static final String KEY_LINE_MARGIN_STATE = "key_line_margin_state";
    private static final String KEY_MAX_COLUMNS_STATE = "key_max_columns_state";
    private static final String KEY_MAX_LINES_STATE = "key_max_lines_state";
    private static final String KEY_MAX_SELECT_STATE = "key_max_select_state";
    private static final String KEY_MIN_SELECT_STATE = "key_min_select_state";
    private static final String KEY_PADDING_STATE = "key_padding_state";
    private static final String KEY_SELECT_LABELS_STATE = "key_select_labels_state";
    private static final String KEY_SELECT_TYPE_STATE = "key_select_type_state";
    private static final String KEY_SINGLE_LINE_STATE = "key_single_line_state";
    private static final String KEY_SUPER_STATE = "key_super_state";
    private static final String KEY_TEXT_COLOR_STATE = "key_text_color_state";
    private static final String KEY_TEXT_SIZE_STATE = "key_text_size_state";
    private static final String KEY_TEXT_STYLE_STATE = "key_text_style_state";
    private static final String KEY_WORD_MARGIN_STATE = "key_word_margin_state";
    private boolean isIndicator;
    private boolean isSingleLine;
    private boolean isTextBold;
    private ArrayList<Integer> mCompulsorys;
    private Context mContext;
    private Drawable mLabelBg;
    private OnLabelClickListener mLabelClickListener;
    private int mLabelGravity;
    private int mLabelHeight;
    private OnLabelLongClickListener mLabelLongClickListener;
    private OnLabelSelectChangeListener mLabelSelectChangeListener;
    private int mLabelWidth;
    private ArrayList<Object> mLabels;
    private int mLineMargin;
    private int mLines;
    private int mMaxColumns;
    private int mMaxLines;
    private int mMaxSelect;
    private int mMinSelect;
    private OnSelectChangeIntercept mOnSelectChangeIntercept;
    private ArrayList<Integer> mSelectLabels;
    private SelectType mSelectType;
    private ColorStateList mTextColor;
    private int mTextPaddingBottom;
    private int mTextPaddingLeft;
    private int mTextPaddingRight;
    private int mTextPaddingTop;
    private float mTextSize;
    private int mWordMargin;
    private static final int KEY_DATA = R.id.tag_key_data;
    private static final int KEY_POSITION = R.id.tag_key_position;

    public interface LabelTextProvider<T> {
        CharSequence getLabelText(TextView textView, int i, T t);
    }

    public interface OnLabelClickListener {
        void onLabelClick(TextView textView, Object obj, int i);
    }

    public interface OnLabelLongClickListener {
        boolean onLabelLongClick(TextView textView, Object obj, int i);
    }

    public interface OnLabelSelectChangeListener {
        void onLabelSelectChange(TextView textView, Object obj, boolean z, int i);
    }

    public interface OnSelectChangeIntercept {
        boolean onIntercept(TextView textView, Object obj, boolean z, boolean z2, int i);
    }

    public interface Selectable {
        boolean isSelected();

        void onSelected(boolean z);
    }

    public enum SelectType {
        NONE(1),
        SINGLE(2),
        SINGLE_IRREVOCABLY(3),
        MULTI(4);

        int value;

        SelectType(int i) {
            this.value = i;
        }

        static SelectType get(int i) {
            if (i == 1) {
                return NONE;
            }
            if (i == 2) {
                return SINGLE;
            }
            if (i == 3) {
                return SINGLE_IRREVOCABLY;
            }
            if (i == 4) {
                return MULTI;
            }
            return NONE;
        }
    }

    public LabelsView(Context context) {
        super(context);
        this.mLabelWidth = -2;
        this.mLabelHeight = -2;
        this.mLabelGravity = 17;
        this.isSingleLine = false;
        this.isTextBold = false;
        this.mLabels = new ArrayList<>();
        this.mSelectLabels = new ArrayList<>();
        this.mCompulsorys = new ArrayList<>();
        this.mContext = context;
        showEditPreview();
    }

    public LabelsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLabelWidth = -2;
        this.mLabelHeight = -2;
        this.mLabelGravity = 17;
        this.isSingleLine = false;
        this.isTextBold = false;
        this.mLabels = new ArrayList<>();
        this.mSelectLabels = new ArrayList<>();
        this.mCompulsorys = new ArrayList<>();
        this.mContext = context;
        getAttrs(context, attributeSet);
        showEditPreview();
    }

    public LabelsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLabelWidth = -2;
        this.mLabelHeight = -2;
        this.mLabelGravity = 17;
        this.isSingleLine = false;
        this.isTextBold = false;
        this.mLabels = new ArrayList<>();
        this.mSelectLabels = new ArrayList<>();
        this.mCompulsorys = new ArrayList<>();
        this.mContext = context;
        getAttrs(context, attributeSet);
        showEditPreview();
    }

    private void getAttrs(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LabelsView);
            this.mSelectType = SelectType.get(typedArrayObtainStyledAttributes.getInt(R.styleable.LabelsView_selectType, 1));
            this.mMaxSelect = typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelsView_maxSelect, 0);
            this.mMinSelect = typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelsView_minSelect, 0);
            this.mMaxLines = typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelsView_maxLines, 0);
            this.mMaxColumns = typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelsView_maxColumns, 0);
            this.isIndicator = typedArrayObtainStyledAttributes.getBoolean(R.styleable.LabelsView_isIndicator, false);
            this.mLabelGravity = typedArrayObtainStyledAttributes.getInt(R.styleable.LabelsView_labelGravity, this.mLabelGravity);
            this.mLabelWidth = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.LabelsView_labelTextWidth, this.mLabelWidth);
            this.mLabelHeight = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.LabelsView_labelTextHeight, this.mLabelHeight);
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.LabelsView_labelTextColor)) {
                this.mTextColor = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.LabelsView_labelTextColor);
            } else {
                this.mTextColor = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
            }
            this.mTextSize = typedArrayObtainStyledAttributes.getDimension(R.styleable.LabelsView_labelTextSize, sp2px(14.0f));
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.LabelsView_labelTextPadding)) {
                int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.LabelsView_labelTextPadding, 0);
                this.mTextPaddingBottom = dimensionPixelOffset;
                this.mTextPaddingRight = dimensionPixelOffset;
                this.mTextPaddingTop = dimensionPixelOffset;
                this.mTextPaddingLeft = dimensionPixelOffset;
            } else {
                this.mTextPaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.LabelsView_labelTextPaddingLeft, dp2px(10.0f));
                this.mTextPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.LabelsView_labelTextPaddingTop, dp2px(5.0f));
                this.mTextPaddingRight = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.LabelsView_labelTextPaddingRight, dp2px(10.0f));
                this.mTextPaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.LabelsView_labelTextPaddingBottom, dp2px(5.0f));
            }
            this.mLineMargin = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.LabelsView_lineMargin, dp2px(5.0f));
            this.mWordMargin = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.LabelsView_wordMargin, dp2px(5.0f));
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.LabelsView_labelBackground)) {
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.LabelsView_labelBackground, 0);
                if (resourceId != 0) {
                    this.mLabelBg = getResources().getDrawable(resourceId);
                } else {
                    this.mLabelBg = new ColorDrawable(typedArrayObtainStyledAttributes.getColor(R.styleable.LabelsView_labelBackground, 0));
                }
            } else {
                this.mLabelBg = getResources().getDrawable(R.drawable.default_label_bg);
            }
            this.isSingleLine = typedArrayObtainStyledAttributes.getBoolean(R.styleable.LabelsView_singleLine, false);
            this.isTextBold = typedArrayObtainStyledAttributes.getBoolean(R.styleable.LabelsView_isTextBold, false);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void showEditPreview() {
        if (isInEditMode()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("Label 1");
            arrayList.add("Label 2");
            arrayList.add("Label 3");
            arrayList.add("Label 4");
            arrayList.add("Label 5");
            arrayList.add("Label 6");
            arrayList.add("Label 7");
            setLabels(arrayList);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.isSingleLine) {
            measureSingleLine(i, i2);
        } else {
            measureMultiLine(i, i2);
        }
    }

    private void measureSingleLine(int i, int i2) {
        int childCount = getChildCount();
        int measuredWidth = 0;
        int iMax = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            int i4 = this.mMaxColumns;
            if (i4 > 0 && i3 == i4) {
                break;
            }
            View childAt = getChildAt(i3);
            measureChild(childAt, i, i2);
            measuredWidth += childAt.getMeasuredWidth();
            if (i3 != childCount - 1) {
                measuredWidth += this.mWordMargin;
            }
            iMax = Math.max(iMax, childAt.getMeasuredHeight());
        }
        setMeasuredDimension(measureSize(i, measuredWidth + getPaddingLeft() + getPaddingRight()), measureSize(i2, iMax + getPaddingTop() + getPaddingBottom()));
        this.mLines = childCount > 0 ? 1 : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0076 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void measureMultiLine(int r14, int r15) {
        /*
            r13 = this;
            int r0 = r13.getChildCount()
            int r1 = android.view.View.MeasureSpec.getSize(r14)
            int r2 = r13.getPaddingLeft()
            int r1 = r1 - r2
            int r2 = r13.getPaddingRight()
            int r1 = r1 - r2
            r2 = 0
            r3 = 1
            r4 = r2
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r3
        L1b:
            if (r4 >= r0) goto L79
            android.view.View r11 = r13.getChildAt(r4)
            r13.measureChild(r11, r14, r15)
            int r12 = r11.getMeasuredWidth()
            int r12 = r12 + r5
            if (r12 > r1) goto L31
            int r12 = r13.mMaxColumns
            if (r12 <= 0) goto L47
            if (r6 != r12) goto L47
        L31:
            int r10 = r10 + 1
            int r6 = r13.mMaxLines
            if (r6 <= 0) goto L3c
            if (r10 <= r6) goto L3c
        L39:
            int r10 = r10 + (-1)
            goto L79
        L3c:
            int r6 = r13.mLineMargin
            int r8 = r8 + r6
            int r8 = r8 + r7
            int r9 = java.lang.Math.max(r9, r5)
            r5 = r2
            r6 = r5
            r7 = r6
        L47:
            int r12 = r11.getMeasuredWidth()
            int r5 = r5 + r12
            int r6 = r6 + r3
            int r11 = r11.getMeasuredHeight()
            int r7 = java.lang.Math.max(r7, r11)
            int r11 = r0 + (-1)
            if (r4 == r11) goto L76
            int r11 = r13.mWordMargin
            int r12 = r5 + r11
            if (r12 <= r1) goto L75
            int r10 = r10 + 1
            int r6 = r13.mMaxLines
            if (r6 <= 0) goto L68
            if (r10 <= r6) goto L68
            goto L39
        L68:
            int r6 = r13.mLineMargin
            int r8 = r8 + r6
            int r8 = r8 + r7
            int r5 = java.lang.Math.max(r9, r5)
            r6 = r2
            r7 = r6
            r9 = r5
            r5 = r7
            goto L76
        L75:
            int r5 = r5 + r11
        L76:
            int r4 = r4 + 1
            goto L1b
        L79:
            int r8 = r8 + r7
            int r1 = java.lang.Math.max(r9, r5)
            int r3 = r13.getPaddingLeft()
            int r1 = r1 + r3
            int r3 = r13.getPaddingRight()
            int r1 = r1 + r3
            int r14 = r13.measureSize(r14, r1)
            int r1 = r13.getPaddingTop()
            int r8 = r8 + r1
            int r1 = r13.getPaddingBottom()
            int r8 = r8 + r1
            int r15 = r13.measureSize(r15, r8)
            r13.setMeasuredDimension(r14, r15)
            if (r0 <= 0) goto La0
            r2 = r10
        La0:
            r13.mLines = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.donkingliang.labels.LabelsView.measureMultiLine(int, int):void");
    }

    private int measureSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            i2 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i2 = Math.min(i2, size);
        }
        return resolveSizeAndState(Math.max(i2, getSuggestedMinimumWidth()), i, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i7 = i3 - i;
        int childCount = getChildCount();
        int i8 = 1;
        int i9 = 0;
        int iMax = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (!this.isSingleLine && (i7 < childAt.getMeasuredWidth() + paddingLeft + getPaddingRight() || ((i6 = this.mMaxColumns) > 0 && i9 == i6))) {
                i8++;
                int i11 = this.mMaxLines;
                if (i11 > 0 && i8 > i11) {
                    return;
                }
                paddingLeft = getPaddingLeft();
                paddingTop = paddingTop + this.mLineMargin + iMax;
                i9 = 0;
                iMax = 0;
            }
            if (this.isSingleLine && (i5 = this.mMaxColumns) > 0 && i9 == i5) {
                return;
            }
            childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
            paddingLeft = paddingLeft + childAt.getMeasuredWidth() + this.mWordMargin;
            iMax = Math.max(iMax, childAt.getMeasuredHeight());
            i9++;
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState());
        ColorStateList colorStateList = this.mTextColor;
        if (colorStateList != null) {
            bundle.putParcelable(KEY_TEXT_COLOR_STATE, colorStateList);
        }
        bundle.putFloat(KEY_TEXT_SIZE_STATE, this.mTextSize);
        bundle.putInt(KEY_LABEL_WIDTH_STATE, this.mLabelWidth);
        bundle.putInt(KEY_LABEL_HEIGHT_STATE, this.mLabelHeight);
        bundle.putInt(KEY_LABEL_GRAVITY_STATE, this.mLabelGravity);
        bundle.putIntArray(KEY_PADDING_STATE, new int[]{this.mTextPaddingLeft, this.mTextPaddingTop, this.mTextPaddingRight, this.mTextPaddingBottom});
        bundle.putInt(KEY_WORD_MARGIN_STATE, this.mWordMargin);
        bundle.putInt(KEY_LINE_MARGIN_STATE, this.mLineMargin);
        bundle.putInt(KEY_SELECT_TYPE_STATE, this.mSelectType.value);
        bundle.putInt(KEY_MAX_SELECT_STATE, this.mMaxSelect);
        bundle.putInt(KEY_MIN_SELECT_STATE, this.mMinSelect);
        bundle.putInt(KEY_MAX_LINES_STATE, this.mMaxLines);
        bundle.putInt(KEY_MAX_COLUMNS_STATE, this.mMaxColumns);
        bundle.putBoolean(KEY_INDICATOR_STATE, this.isIndicator);
        if (!this.mSelectLabels.isEmpty()) {
            bundle.putIntegerArrayList(KEY_SELECT_LABELS_STATE, this.mSelectLabels);
        }
        if (!this.mCompulsorys.isEmpty()) {
            bundle.putIntegerArrayList(KEY_COMPULSORY_LABELS_STATE, this.mCompulsorys);
        }
        bundle.putBoolean(KEY_SINGLE_LINE_STATE, this.isSingleLine);
        bundle.putBoolean(KEY_TEXT_STYLE_STATE, this.isTextBold);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
            ColorStateList colorStateList = (ColorStateList) bundle.getParcelable(KEY_TEXT_COLOR_STATE);
            if (colorStateList != null) {
                setLabelTextColor(colorStateList);
            }
            setLabelTextSize(bundle.getFloat(KEY_TEXT_SIZE_STATE, this.mTextSize));
            this.mLabelWidth = bundle.getInt(KEY_LABEL_WIDTH_STATE, this.mLabelWidth);
            this.mLabelHeight = bundle.getInt(KEY_LABEL_HEIGHT_STATE, this.mLabelHeight);
            setLabelGravity(bundle.getInt(KEY_LABEL_GRAVITY_STATE, this.mLabelGravity));
            int[] intArray = bundle.getIntArray(KEY_PADDING_STATE);
            if (intArray != null && intArray.length == 4) {
                setLabelTextPadding(intArray[0], intArray[1], intArray[2], intArray[3]);
            }
            setWordMargin(bundle.getInt(KEY_WORD_MARGIN_STATE, this.mWordMargin));
            setLineMargin(bundle.getInt(KEY_LINE_MARGIN_STATE, this.mLineMargin));
            setSelectType(SelectType.get(bundle.getInt(KEY_SELECT_TYPE_STATE, this.mSelectType.value)));
            setMaxSelect(bundle.getInt(KEY_MAX_SELECT_STATE, this.mMaxSelect));
            setMinSelect(bundle.getInt(KEY_MIN_SELECT_STATE, this.mMinSelect));
            setMaxLines(bundle.getInt(KEY_MAX_LINES_STATE, this.mMaxLines));
            setMaxLines(bundle.getInt(KEY_MAX_COLUMNS_STATE, this.mMaxColumns));
            setIndicator(bundle.getBoolean(KEY_INDICATOR_STATE, this.isIndicator));
            setSingleLine(bundle.getBoolean(KEY_SINGLE_LINE_STATE, this.isSingleLine));
            setTextBold(bundle.getBoolean(KEY_TEXT_STYLE_STATE, this.isTextBold));
            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(KEY_COMPULSORY_LABELS_STATE);
            if (integerArrayList != null && !integerArrayList.isEmpty()) {
                setCompulsorys(integerArrayList);
            }
            ArrayList<Integer> integerArrayList2 = bundle.getIntegerArrayList(KEY_SELECT_LABELS_STATE);
            if (integerArrayList2 == null || integerArrayList2.isEmpty()) {
                return;
            }
            int size = integerArrayList2.size();
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = integerArrayList2.get(i).intValue();
            }
            setSelects(iArr);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setLabels(List<String> list) {
        setLabels(list, new LabelTextProvider<String>() { // from class: com.donkingliang.labels.LabelsView.1
            @Override // com.donkingliang.labels.LabelsView.LabelTextProvider
            public CharSequence getLabelText(TextView textView, int i, String str) {
                return str.trim();
            }
        });
    }

    public <T> void setLabels(List<T> list, LabelTextProvider<T> labelTextProvider) {
        innerClearAllSelect();
        removeAllViews();
        this.mLabels.clear();
        if (list != null) {
            this.mLabels.addAll(list);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                addLabel(list.get(i), i, labelTextProvider);
            }
            ensureLabelClickable();
        }
        if (this.mSelectType == SelectType.SINGLE_IRREVOCABLY) {
            setSelects(0);
        }
    }

    public <T> List<T> getLabels() {
        return this.mLabels;
    }

    private <T> void addLabel(T t, int i, LabelTextProvider<T> labelTextProvider) {
        TextView textView = new TextView(this.mContext);
        textView.setPadding(this.mTextPaddingLeft, this.mTextPaddingTop, this.mTextPaddingRight, this.mTextPaddingBottom);
        textView.setTextSize(0, this.mTextSize);
        textView.setGravity(this.mLabelGravity);
        textView.setTextColor(this.mTextColor);
        textView.setBackgroundDrawable(this.mLabelBg.getConstantState().newDrawable());
        textView.setTag(KEY_DATA, t);
        textView.setTag(KEY_POSITION, Integer.valueOf(i));
        textView.setOnClickListener(this);
        textView.setOnLongClickListener(this);
        textView.getPaint().setFakeBoldText(this.isTextBold);
        addView(textView, this.mLabelWidth, this.mLabelHeight);
        textView.setText(labelTextProvider.getLabelText(textView, i, t));
    }

    private void ensureLabelClickable() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((TextView) getChildAt(i)).setClickable((this.mLabelClickListener == null && this.mLabelLongClickListener == null && this.mSelectType == SelectType.NONE) ? false : true);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (!this.isIndicator && this.mSelectType != SelectType.NONE) {
                boolean z = true;
                if (textView.isSelected()) {
                    if (!((this.mSelectType == SelectType.MULTI && this.mCompulsorys.contains((Integer) textView.getTag(KEY_POSITION))) || (this.mSelectType == SelectType.MULTI && this.mSelectLabels.size() <= this.mMinSelect)) && this.mSelectType != SelectType.SINGLE_IRREVOCABLY) {
                        z = false;
                    }
                    if (!z && !selectChangeIntercept(textView)) {
                        setLabelSelect(textView, false);
                    }
                } else if (this.mSelectType == SelectType.SINGLE || this.mSelectType == SelectType.SINGLE_IRREVOCABLY) {
                    if (!selectChangeIntercept(textView)) {
                        innerClearAllSelect();
                        setLabelSelect(textView, true);
                    }
                } else if (this.mSelectType == SelectType.MULTI && (((i = this.mMaxSelect) <= 0 || i > this.mSelectLabels.size()) && !selectChangeIntercept(textView))) {
                    setLabelSelect(textView, true);
                }
            }
            OnLabelClickListener onLabelClickListener = this.mLabelClickListener;
            if (onLabelClickListener != null) {
                onLabelClickListener.onLabelClick(textView, textView.getTag(KEY_DATA), ((Integer) textView.getTag(KEY_POSITION)).intValue());
            }
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (!(view instanceof TextView)) {
            return false;
        }
        TextView textView = (TextView) view;
        OnLabelLongClickListener onLabelLongClickListener = this.mLabelLongClickListener;
        if (onLabelLongClickListener != null) {
            return onLabelLongClickListener.onLabelLongClick(textView, textView.getTag(KEY_DATA), ((Integer) textView.getTag(KEY_POSITION)).intValue());
        }
        return false;
    }

    private void setLabelSelect(TextView textView, boolean z) {
        if (textView.isSelected() != z) {
            textView.setSelected(z);
            if (z) {
                this.mSelectLabels.add((Integer) textView.getTag(KEY_POSITION));
            } else {
                this.mSelectLabels.remove((Integer) textView.getTag(KEY_POSITION));
            }
            OnLabelSelectChangeListener onLabelSelectChangeListener = this.mLabelSelectChangeListener;
            if (onLabelSelectChangeListener != null) {
                onLabelSelectChangeListener.onLabelSelectChange(textView, textView.getTag(KEY_DATA), z, ((Integer) textView.getTag(KEY_POSITION)).intValue());
            }
        }
    }

    private boolean selectChangeIntercept(TextView textView) {
        OnSelectChangeIntercept onSelectChangeIntercept = this.mOnSelectChangeIntercept;
        return onSelectChangeIntercept != null && onSelectChangeIntercept.onIntercept(textView, textView.getTag(KEY_DATA), textView.isSelected(), textView.isSelected() ^ true, ((Integer) textView.getTag(KEY_POSITION)).intValue());
    }

    public void clearAllSelect() {
        if (this.mSelectType != SelectType.SINGLE_IRREVOCABLY) {
            if (this.mSelectType == SelectType.MULTI && !this.mCompulsorys.isEmpty()) {
                clearNotCompulsorySelect();
            } else {
                innerClearAllSelect();
            }
        }
    }

    private void innerClearAllSelect() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            setLabelSelect((TextView) getChildAt(i), false);
        }
        this.mSelectLabels.clear();
    }

    private void clearNotCompulsorySelect() {
        int childCount = getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childCount; i++) {
            if (!this.mCompulsorys.contains(Integer.valueOf(i))) {
                setLabelSelect((TextView) getChildAt(i), false);
                arrayList.add(Integer.valueOf(i));
            }
        }
        this.mSelectLabels.removeAll(arrayList);
    }

    public void setSelects(List<Integer> list) {
        if (list != null) {
            int size = list.size();
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = list.get(i).intValue();
            }
            setSelects(iArr);
        }
    }

    public void setSelects(int... iArr) {
        if (this.mSelectType != SelectType.NONE) {
            ArrayList arrayList = new ArrayList();
            int childCount = getChildCount();
            int i = (this.mSelectType == SelectType.SINGLE || this.mSelectType == SelectType.SINGLE_IRREVOCABLY) ? 1 : this.mMaxSelect;
            for (int i2 : iArr) {
                if (i2 < childCount) {
                    TextView textView = (TextView) getChildAt(i2);
                    if (!arrayList.contains(textView)) {
                        setLabelSelect(textView, true);
                        arrayList.add(textView);
                    }
                    if (i > 0 && arrayList.size() == i) {
                        break;
                    }
                }
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                TextView textView2 = (TextView) getChildAt(i3);
                if (!arrayList.contains(textView2)) {
                    setLabelSelect(textView2, false);
                }
            }
        }
    }

    public void setCompulsorys(List<Integer> list) {
        if (this.mSelectType != SelectType.MULTI || list == null) {
            return;
        }
        this.mCompulsorys.clear();
        this.mCompulsorys.addAll(list);
        innerClearAllSelect();
        setSelects(list);
    }

    public void setCompulsorys(int... iArr) {
        if (this.mSelectType != SelectType.MULTI || iArr == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        setCompulsorys(arrayList);
    }

    public List<Integer> getCompulsorys() {
        return this.mCompulsorys;
    }

    public void clearCompulsorys() {
        if (this.mSelectType != SelectType.MULTI || this.mCompulsorys.isEmpty()) {
            return;
        }
        this.mCompulsorys.clear();
        innerClearAllSelect();
    }

    public List<Integer> getSelectLabels() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mSelectLabels);
        return arrayList;
    }

    public <T> List<T> getSelectLabelDatas() {
        ArrayList arrayList = new ArrayList();
        int size = this.mSelectLabels.size();
        for (int i = 0; i < size; i++) {
            Object tag = getChildAt(this.mSelectLabels.get(i).intValue()).getTag(KEY_DATA);
            if (tag != null) {
                arrayList.add(tag);
            }
        }
        return arrayList;
    }

    public void setLabelBackgroundResource(int i) {
        setLabelBackgroundDrawable(getResources().getDrawable(i));
    }

    public void setLabelBackgroundColor(int i) {
        setLabelBackgroundDrawable(new ColorDrawable(i));
    }

    public void setLabelBackgroundDrawable(Drawable drawable) {
        this.mLabelBg = drawable;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((TextView) getChildAt(i)).setBackgroundDrawable(this.mLabelBg.getConstantState().newDrawable());
        }
    }

    public void setLabelTextPadding(int i, int i2, int i3, int i4) {
        if (this.mTextPaddingLeft == i && this.mTextPaddingTop == i2 && this.mTextPaddingRight == i3 && this.mTextPaddingBottom == i4) {
            return;
        }
        this.mTextPaddingLeft = i;
        this.mTextPaddingTop = i2;
        this.mTextPaddingRight = i3;
        this.mTextPaddingBottom = i4;
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            ((TextView) getChildAt(i5)).setPadding(i, i2, i3, i4);
        }
    }

    public int getTextPaddingLeft() {
        return this.mTextPaddingLeft;
    }

    public int getTextPaddingTop() {
        return this.mTextPaddingTop;
    }

    public int getTextPaddingRight() {
        return this.mTextPaddingRight;
    }

    public int getTextPaddingBottom() {
        return this.mTextPaddingBottom;
    }

    public void setLabelTextSize(float f) {
        if (this.mTextSize != f) {
            this.mTextSize = f;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                ((TextView) getChildAt(i)).setTextSize(0, f);
            }
        }
    }

    public float getLabelTextSize() {
        return this.mTextSize;
    }

    public void setLabelTextColor(int i) {
        setLabelTextColor(ColorStateList.valueOf(i));
    }

    public void setLabelTextColor(ColorStateList colorStateList) {
        this.mTextColor = colorStateList;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((TextView) getChildAt(i)).setTextColor(this.mTextColor);
        }
    }

    public ColorStateList getLabelTextColor() {
        return this.mTextColor;
    }

    public void setLabelGravity(int i) {
        if (this.mLabelGravity != i) {
            this.mLabelGravity = i;
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                ((TextView) getChildAt(i2)).setGravity(i);
            }
        }
    }

    public int getLabelGravity() {
        return this.mLabelGravity;
    }

    public void setTextBold(boolean z) {
        if (this.isTextBold != z) {
            this.isTextBold = z;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                TextView textView = (TextView) getChildAt(i);
                textView.getPaint().setFakeBoldText(this.isTextBold);
                textView.invalidate();
            }
        }
    }

    public boolean isTextBold() {
        return this.isTextBold;
    }

    public void setLineMargin(int i) {
        if (this.mLineMargin != i) {
            this.mLineMargin = i;
            requestLayout();
        }
    }

    public int getLineMargin() {
        return this.mLineMargin;
    }

    public void setWordMargin(int i) {
        if (this.mWordMargin != i) {
            this.mWordMargin = i;
            requestLayout();
        }
    }

    public int getWordMargin() {
        return this.mWordMargin;
    }

    public void setSelectType(SelectType selectType) {
        if (this.mSelectType != selectType) {
            this.mSelectType = selectType;
            innerClearAllSelect();
            if (this.mSelectType == SelectType.SINGLE_IRREVOCABLY) {
                setSelects(0);
            }
            if (this.mSelectType != SelectType.MULTI) {
                this.mCompulsorys.clear();
            }
            ensureLabelClickable();
        }
    }

    public SelectType getSelectType() {
        return this.mSelectType;
    }

    public void setMaxSelect(int i) {
        if (this.mMaxSelect != i) {
            this.mMaxSelect = i;
            if (this.mSelectType == SelectType.MULTI) {
                innerClearAllSelect();
            }
        }
    }

    public int getMaxSelect() {
        return this.mMaxSelect;
    }

    public void setMinSelect(int i) {
        this.mMinSelect = i;
    }

    public int getMinSelect() {
        return this.mMinSelect;
    }

    public void setMaxLines(int i) {
        if (this.mMaxLines != i) {
            this.mMaxLines = i;
            requestLayout();
        }
    }

    public int getMaxLines() {
        return this.mMaxLines;
    }

    public void setMaxColumns(int i) {
        if (this.mMaxColumns != i) {
            this.mMaxColumns = i;
            requestLayout();
        }
    }

    public int getMaxColumns() {
        return this.mMaxColumns;
    }

    public void setIndicator(boolean z) {
        this.isIndicator = z;
    }

    public boolean isIndicator() {
        return this.isIndicator;
    }

    public void setSingleLine(boolean z) {
        if (this.isSingleLine != z) {
            this.isSingleLine = z;
            requestLayout();
        }
    }

    public boolean isSingleLine() {
        return this.isSingleLine;
    }

    public int getLines() {
        return this.mLines;
    }

    public void setOnLabelClickListener(OnLabelClickListener onLabelClickListener) {
        this.mLabelClickListener = onLabelClickListener;
        ensureLabelClickable();
    }

    public void setOnLabelLongClickListener(OnLabelLongClickListener onLabelLongClickListener) {
        this.mLabelLongClickListener = onLabelLongClickListener;
        ensureLabelClickable();
    }

    public void setOnLabelSelectChangeListener(OnLabelSelectChangeListener onLabelSelectChangeListener) {
        this.mLabelSelectChangeListener = onLabelSelectChangeListener;
    }

    public void setOnSelectChangeIntercept(OnSelectChangeIntercept onSelectChangeIntercept) {
        this.mOnSelectChangeIntercept = onSelectChangeIntercept;
    }

    private int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, getResources().getDisplayMetrics());
    }

    private int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }
}
