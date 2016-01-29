package com.example.ives.lpc_v2.Layouts;

import android.widget.Button;

/**
 * Created by Ives on 10/10/2015.
 */
public abstract class BtnRemoveOnClickedListener implements Button.OnClickListener
{
    private int position;

    public BtnRemoveOnClickedListener(int position)
    {
        this.position = position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }
}
