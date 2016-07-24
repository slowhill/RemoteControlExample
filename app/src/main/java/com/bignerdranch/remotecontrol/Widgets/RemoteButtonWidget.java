package com.bignerdranch.remotecontrol.Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.bignerdranch.remotecontrol.R;

public class RemoteButtonWidget extends Button {

    public static final int TYPE_NUMBER = 0;
    public static final int TYPE_ENTER = 1;
    public static final int TYPE_DELETE = 2;

    private int mButtonType = 0;
    private int mButtonValue = 0;

    public RemoteButtonWidget(Context context) {
        super(context);
    }

    public RemoteButtonWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public RemoteButtonWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    public String getButtonValue() {
        return Integer.toString(mButtonValue);
    }

    private void setup(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RemoteButtonWidget, 0, 0);

        try {
            mButtonType = attributes.getInt(R.styleable.RemoteButtonWidget_buttonType, 0);
            mButtonValue = attributes.getInt(R.styleable.RemoteButtonWidget_numberValue, 0);
        } finally {
            attributes.recycle();
        }

        switch (mButtonType) {
            case TYPE_DELETE:
                this.setText(R.string.remote_button_delete);
                break;
            case TYPE_ENTER:
                this.setText(R.string.remote_button_enter);
                break;
            case TYPE_NUMBER:
            default:
                this.setText(Integer.toString(mButtonValue));
                break;
        }
    }
}
