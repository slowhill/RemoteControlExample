package com.bignerdranch.remotecontrol;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.remotecontrol.Widgets.RemoteControlTableWidget;

/**
 * Created by dustin on 2016-07-21.
 */
public class RemoteControlFragment extends Fragment {

    private TextView mSelectedTextView;
    private TextView mWorkingTextView;
    private RemoteControlTableWidget mRemoteControlTable;

    public RemoteControlFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_control, container, false);

        mSelectedTextView = (TextView)view.findViewById(R.id.remote_control_selected);
        mWorkingTextView = (TextView)view.findViewById(R.id.remote_control_working);
        mRemoteControlTable = (RemoteControlTableWidget)view.findViewById(R.id.remote_control_controls);
        mRemoteControlTable.setRemoteEventListener(new RemoteControlTableWidget.RemoteEventListener() {
            @Override
            public void onWorkingValueChanged(String workingValue) {
                mWorkingTextView.setText(workingValue);
            }

            @Override
            public void onDeleteButtonClicked() {
                mSelectedTextView.setText("");
                mWorkingTextView.setText("");
            }

            @Override
            public void onEnterButtonClicked(String workingValue, String defaultValue) {
                mWorkingTextView.setText(defaultValue);
                mSelectedTextView.setText(workingValue);
            }
        });

        return view;
    }


}
