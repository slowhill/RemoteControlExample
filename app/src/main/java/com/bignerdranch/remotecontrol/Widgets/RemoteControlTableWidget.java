package com.bignerdranch.remotecontrol.Widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import com.bignerdranch.remotecontrol.R;
import com.google.common.base.Optional;

/**
 * Created by dustin on 2016-07-24.
 */
public class RemoteControlTableWidget extends TableLayout {

    private RemoteButtonWidget mOneButton;
    private RemoteButtonWidget mTwoButton;
    private RemoteButtonWidget mThreeButton;
    private RemoteButtonWidget mFourButton;
    private RemoteButtonWidget mFiveButton;
    private RemoteButtonWidget mSixButton;
    private RemoteButtonWidget mSevenButton;
    private RemoteButtonWidget mEightButton;
    private RemoteButtonWidget mNineButton;
    private RemoteButtonWidget mZeroButton;

    private RemoteButtonWidget mEnterButton;
    private RemoteButtonWidget mDeleteButton;

    private static final String DEFAULT_ZERO_VALUE = "0";
    private String mWorkingValue = DEFAULT_ZERO_VALUE;
    private Optional<RemoteEventListener> mListener = Optional.absent();

    private View.OnClickListener mNumberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RemoteButtonWidget view = (RemoteButtonWidget)v;

            if (mWorkingValue.equals(DEFAULT_ZERO_VALUE)) {
                mWorkingValue = view.getButtonValue();
            } else {
                mWorkingValue = mWorkingValue.concat(view.getButtonValue());
            }

            if (mListener.isPresent()) {
                mListener.get().onWorkingValueChanged(mWorkingValue);
            }
        }
    };

    private View.OnClickListener mDeleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mWorkingValue = DEFAULT_ZERO_VALUE;

            if (mListener.isPresent()) {
                mListener.get().onDeleteButtonClicked();
            }
        }
    };

    private View.OnClickListener mEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener.isPresent()) {
                mListener.get().onEnterButtonClicked(mWorkingValue, DEFAULT_ZERO_VALUE);
            }

            mWorkingValue = DEFAULT_ZERO_VALUE;
        }
    };

    public RemoteControlTableWidget(Context context) {
        super(context);
        setup();
    }

    public RemoteControlTableWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public void setRemoteEventListener(RemoteEventListener listener) {
        mListener = Optional.fromNullable(listener);
    }

    private void setup() {
        bindChildViews();
        setupClickListeners();
    }

    private void bindChildViews() {
        inflate(getContext(), R.layout.view_remote_controls, this);

        mOneButton = (RemoteButtonWidget) findViewById(R.id.remote_control_oneButton);
        mTwoButton = (RemoteButtonWidget) findViewById(R.id.remote_control_twoButton);
        mThreeButton = (RemoteButtonWidget) findViewById(R.id.remote_control_threeButton);
        mFourButton = (RemoteButtonWidget) findViewById(R.id.remote_control_fourButton);
        mFiveButton = (RemoteButtonWidget) findViewById(R.id.remote_control_fiveButton);
        mSixButton = (RemoteButtonWidget) findViewById(R.id.remote_control_sixButton);
        mSevenButton = (RemoteButtonWidget) findViewById(R.id.remote_control_sevenButton);
        mEightButton = (RemoteButtonWidget) findViewById(R.id.remote_control_eightButton);
        mNineButton = (RemoteButtonWidget) findViewById(R.id.remote_control_nineButton);
        mZeroButton = (RemoteButtonWidget) findViewById(R.id.remote_control_zeroButton);
        mEnterButton = (RemoteButtonWidget) findViewById(R.id.remote_control_enterButton);
        mDeleteButton = (RemoteButtonWidget) findViewById(R.id.remote_control_deleteButton);
    }

    private void setupClickListeners() {
        mOneButton.setOnClickListener(mNumberClickListener);
        mTwoButton.setOnClickListener(mNumberClickListener);
        mThreeButton.setOnClickListener(mNumberClickListener);
        mFourButton.setOnClickListener(mNumberClickListener);
        mFiveButton.setOnClickListener(mNumberClickListener);
        mSixButton.setOnClickListener(mNumberClickListener);
        mSevenButton.setOnClickListener(mNumberClickListener);
        mEightButton.setOnClickListener(mNumberClickListener);
        mNineButton.setOnClickListener(mNumberClickListener);
        mZeroButton.setOnClickListener(mNumberClickListener);
        mEnterButton.setOnClickListener(mEnterClickListener);
        mDeleteButton.setOnClickListener(mDeleteClickListener);
    }

    public interface RemoteEventListener {
        void onWorkingValueChanged(String workingValue);
        void onDeleteButtonClicked();
        void onEnterButtonClicked(String workingValue, String defaultValue);
    }
}
