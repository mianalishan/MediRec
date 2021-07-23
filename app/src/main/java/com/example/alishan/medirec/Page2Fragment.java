package com.example.alishan.medirec;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.CartesianSeriesLine;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.EnumsAnchor;
import com.anychart.anychart.Mapping;
import com.anychart.anychart.MarkerType;
import com.anychart.anychart.Set;
import com.anychart.anychart.Stroke;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

/* Fragment used as page 2 */
public class Page2Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page2, container, false);
        AnyChartView anyChartView = rootView.findViewById(R.id.any_chart_view);
        DBHelper db=new DBHelper(getActivity().getApplicationContext());
        Report_Activity  ra = (Report_Activity) getActivity();
        ArrayList<Report_Model> arr=db.getRecordByPatientId(ra.getP_id());
        Cartesian cartesian = AnyChart.line();

        cartesian.setAnimation(true);

        cartesian.setPadding(10d, 20d, 5d, 20d);

        cartesian.getCrosshair().setEnabled(true);
        cartesian.getCrosshair()
                .setYLabel(true)
                .setYStroke((Stroke) null, null, null, null, null);

        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);

        cartesian.setTitle(" Trend of Temperature For Today ");

        cartesian.getYAxis().setTitle(" Temperature ( F )");
        cartesian.getYAxis().getLabels().setPadding(8d, 8d, 8d, 8d);
        cartesian.getXAxis().getLabels().setPadding(5d, 5d, 5d, 5d);
        List<DataEntry> seriesData = new ArrayList<>();
       /* seriesData.add(new CustomDataEntry("2/11", 100, 20 ));
        seriesData.add(new CustomDataEntry("3/11", 105, 40));
        seriesData.add(new CustomDataEntry("4/11", 108, 62));
        seriesData.add(new CustomDataEntry("5/11", 110, 18));
        seriesData.add(new CustomDataEntry("10/12", 120, 30));
        seriesData.add(new CustomDataEntry("11/12", 125, 39));
        seriesData.add(new CustomDataEntry("12/12", 100, 80));
        seriesData.add(new CustomDataEntry("14/12", 130, 23));*/
        for(int i=0;i<arr.size();i++)
        {
            String temp=arr.get(i).getTemperature();
            seriesData.add(new Page2Fragment.CustomDataEntry(arr.get(i).getTime(), Integer.parseInt(temp)));
        }

        Set set = new Set(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        CartesianSeriesLine series1 = cartesian.line(series1Mapping);
        series1.setName(" F ");
        series1.getHovered().getMarkers().setEnabled(true);
        series1.getHovered().getMarkers()
                .setType(MarkerType.CIRCLE)
                .setSize(4d);
        series1.getTooltip()
                .setPosition("right")
                .setAnchor(EnumsAnchor.LEFT_CENTER)
                .setOffsetX(5d)
                .setOffsetY(5d);

       /* CartesianSeriesLine series2 = cartesian.line(series2Mapping);
        series2.setName("Systolic");
        series2.getHovered().getMarkers().setEnabled(true);
        series2.getHovered().getMarkers()
                .setType(MarkerType.CIRCLE)
                .setSize(4d);

        series2.getTooltip()
                .setPosition("right")
                .setAnchor(EnumsAnchor.LEFT_CENTER)
                .setOffsetX(5d)
                .setOffsetY(5d);*/

        /*CartesianSeriesLine series3 = cartesian.line(series3Mapping);
        series3.setName("Tequila");
        series3.getHovered().getMarkers().setEnabled(true);
        series3.getHovered().getMarkers()
                .setType(MarkerType.CIRCLE)
                .setSize(4d);
        series3.getTooltip()
                .setPosition("right")
                .setAnchor(EnumsAnchor.LEFT_CENTER)
                .setOffsetX(5d)
                .setOffsetY(5d);*/

        cartesian.getLegend().setEnabled(true);
        cartesian.getLegend().setFontSize(13d);
        cartesian.getLegend().setPadding(0d, 0d, 2d, 0d);

        anyChartView.setChart(cartesian);
        anyChartView.buildDrawingCache();
        anyChartView.getDrawingCache();


        return rootView;
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value) {
            super(x, value);
            //setValue("value2", value2);
            //setValue("value3", value3);
        }


    }
}


