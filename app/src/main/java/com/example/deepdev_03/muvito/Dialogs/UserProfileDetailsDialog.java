package com.example.deepdev_03.muvito.Dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.deepdev_03.muvito.R;

public class UserProfileDetailsDialog extends DialogFragment implements View.OnClickListener
{
    private RelativeLayout shareHolder, complainHolder, blockHolder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, getTheme());
    }

    @Override
    public void onStart()
    {
        super.onStart();

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_user_profile_details, container, false);

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        this.shareHolder = (RelativeLayout) view.findViewById(R.id.dialog_user_profile_share_holder);
        this.shareHolder.setOnClickListener(this);
        this.complainHolder = (RelativeLayout) view.findViewById(R.id.dialog_user_profile_complain_holder);
        this.complainHolder.setOnClickListener(this);
        this.blockHolder = (RelativeLayout) view.findViewById(R.id.dialog_user_profile_block_holder);
        this.blockHolder.setOnClickListener(this);

        return view;
    }

    @Override
    public int getTheme()
    {
        return R.style.DialogSlideAnim;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.dialog_user_profile_share_holder)
        {
            Toast.makeText(getActivity().getApplicationContext(), "asd", Toast.LENGTH_SHORT).show();
            getDialog().dismiss();
        }
        if(v.getId() == R.id.dialog_user_profile_complain_holder)
        {
            Toast.makeText(getActivity().getApplicationContext(), "asd", Toast.LENGTH_SHORT).show();
            getDialog().dismiss();
        }
        if(v.getId() == R.id.dialog_user_profile_block_holder)
        {
            Toast.makeText(getActivity().getApplicationContext(), "asd", Toast.LENGTH_SHORT).show();
            getDialog().dismiss();
        }
    }
}
