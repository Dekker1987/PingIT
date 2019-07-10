package com.pingit.dekker.pingit.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.pingit.dekker.pingit.R;

import java.util.ArrayList;

/**
 * Created by Sergii on 01.08.2018.
 */

public class PingGraphFragment extends Fragment {

    private LineChart lineChart;
    private XAxis xl;
    private YAxis yl;
    private YAxis y2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.ping_graph_frag,container,false);

        lineChart = view.findViewById(R.id.lineChart);

        lineChart.setHighlightPerTapEnabled(true);
        lineChart.setTouchEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setPinchZoom(true);
        lineChart.setBackgroundColor(Color.parseColor("#282828"));

        xl = lineChart.getXAxis();

        yl = lineChart.getAxisLeft();
        yl.setTextColor(Color.parseColor("#B8B8B8"));

        y2 = lineChart.getAxisRight();
        y2.setEnabled(false);


        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(1,60f));
        yValues.add(new Entry(2,50f));
        yValues.add(new Entry(3,70f));
        yValues.add(new Entry(4,30f));
        yValues.add(new Entry(5,50f));
        yValues.add(new Entry(6,60f));
        yValues.add(new Entry(7,65f));
        yValues.add(new Entry(8,80f));
        yValues.add(new Entry(9,90f));

        LineDataSet set1 = new LineDataSet(yValues,"Data Set 1");

        set1.setFillAlpha(110);
        set1.setLineWidth(3);

        ArrayList <ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);


        lineChart.setData(data);

        return view;
    }

    public void showPingResult(String pingResult){
        Log.e("debug","PingGraphFragment:: " + pingResult);
//        textView7.setText(pingResult);
    }
}

