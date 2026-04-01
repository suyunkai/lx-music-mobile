package com.wanos.media.ui.login.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.PopupWindow;
import com.wanos.commonlibrary.utils.DateUtil;
import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class DateDialog extends PopupWindow implements CalendarView.OnDateChangeListener {
    public static final String TAG = "wanos:[DateDialog]";
    private final Context context;
    private long date;
    private final CalendarView datePicker;
    private DateSelectListener dateSelectListener;
    private final View inflate;

    public interface DateSelectListener {
        void onDateSelectListener(long date);
    }

    public DateDialog(Context context, int themeResId) {
        super(context);
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_date, (ViewGroup) null);
        this.inflate = viewInflate;
        setContentView(viewInflate);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setOutsideTouchable(true);
        setFocusable(true);
        CalendarView calendarView = (CalendarView) viewInflate.findViewById(R.id.date_view);
        this.datePicker = calendarView;
        calendarView.setOnDateChangeListener(this);
    }

    public void setDateSelectListener(DateSelectListener dateSelectListener) {
        this.dateSelectListener = dateSelectListener;
    }

    public void showAsDropDown(View view, long date) {
        this.date = date;
        showAsDropDown(view);
        updateView();
    }

    public void showAsDropDown(View anchor, int xoff, int yoff, long date) {
        this.date = date;
        showAsDropDown(anchor, xoff, yoff);
        updateView();
    }

    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity, long date) {
        this.date = date;
        showAsDropDown(anchor, xoff, yoff, gravity);
        updateView();
    }

    private void updateView() {
        this.datePicker.setMaxDate(SystemAndServerTimeSynchUtil.getSytemTrueTime());
        long j = this.date;
        if (j > 0) {
            this.datePicker.setDate(j);
        } else {
            this.datePicker.setDate(DateUtil.timeToLong("1990-06-01 00:00:00"));
        }
    }

    @Override // android.widget.CalendarView.OnDateChangeListener
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        int i = month + 1;
        Log.d(TAG, "year : " + year + ",month : " + i + ",dayOfMonth : " + dayOfMonth);
        if (this.dateSelectListener != null) {
            this.dateSelectListener.onDateSelectListener(DateUtil.timeToLong(year + "-" + i + "-" + dayOfMonth + " 00:00:00"));
        }
        dismiss();
    }
}
