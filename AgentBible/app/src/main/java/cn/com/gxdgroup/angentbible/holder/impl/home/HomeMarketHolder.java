package cn.com.gxdgroup.angentbible.holder.impl.home;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/17.
 *
 * @description:
 */

public class HomeMarketHolder extends BaseHolder {
    @BindView(R.id.iv_arrow)
    ImageView mIvArrow;
    @BindView(R.id.chart)
    CombinedChart mChart;

    private final int itemcount = 12;

    protected String[] mMonths;

    public HomeMarketHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_home_market);
    }

    @Override
    public void initView() {


        initChar();
    }

    private void initChar() {

        int gridColorId = Color.rgb(239, 239, 239);
        int labelTextColor = Color.rgb(112, 112, 112);

        mMonths = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"};
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(true);//如果启用，chart 绘图区后面的背景矩形将绘制。
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setGridBackgroundColor(Color.WHITE);//设置网格背景应与绘制的颜色。
        mChart.setNoDataText("暂无数据");//设置当 chart 为空时显示的描述文字。
        mChart.setDrawBorders(false);//启用/禁用绘制图表边框（chart周围的线）。
        mChart.setBorderColor(Color.RED);//设置 chart 边框线的颜色。
        mChart.setBorderWidth(2);//设置 chart 边界线的宽度，单位 dp。
        mChart.setTouchEnabled(true); //启用/禁用与图表的所有可能的触摸交互。
        mChart.setDragEnabled(true);
        mChart.setDoubleTapToZoomEnabled(false);// 设置为false以禁止通过在其上双击缩放图表。
        mChart.setScaleYEnabled(false);//启用/禁用缩放在y轴。
        mChart.setPinchZoom(true);//: 如果设置为true，捏缩放功能。 如果false，x轴和y轴可分别放大。


        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);


        //----Y轴---
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setGranularity(1f);
        rightAxis.setGridColor(gridColorId);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setTextColor(labelTextColor);//设置轴标签的颜色。
        rightAxis.setLabelCount(6);
        rightAxis.setDrawAxisLine(true);// 设置为true，则绘制网格线。

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularity(1f);
        leftAxis.setGridColor(gridColorId);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(labelTextColor);//设置轴标签的颜色。
        leftAxis.setLabelCount(6);
        leftAxis.setDrawAxisLine(true);// 设置为true，则绘制网格线。

        //----X轴---
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinValue(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(labelTextColor);//设置轴标签的颜色。
        xAxis.setGridColor(gridColorId);//设置该轴的网格线颜色。
        xAxis.setAxisLineColor(gridColorId);//设置轴线的轴的颜色。,使用这种引用不行：R.color.grid_color
        xAxis.setLabelCount(mMonths.length);
        xAxis.setDrawAxisLine(true); //设置为true，则绘制网格线。
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置XAxis出现的位置


        xAxis.setValueFormatter(new AxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) value % mMonths.length];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });


        CombinedData data = new CombinedData();

        data.setData(generateLineData());
        data.setData(generateBarData2());
//        data.setData(generateBubbleData());
//        data.setData(generateScatterData());
//        data.setData(generateCandleData());
//        data.setValueTypeface(mTfLight);//设置该 DataSet 的文本的字体（Typeface）。

        xAxis.setAxisMaxValue(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();

    }

    /**
     * 设置折线图的数据
     */
    private LineData generateLineData() {

        int lineColorId = Color.rgb(51, 153, 255);

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(index + 0.5f, getRandom(15, 5)));

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(lineColorId);
        set.setLineWidth(2.5f);
        set.setCircleColor(lineColorId);
        set.setCircleRadius(5f);
        set.setFillColor(lineColorId);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);//折线(LINEAR) 曲滑折线(CUBIC_BEZIER)

        set.setDrawValues(false);// 启用/禁用 绘制所有 DataSets 数据对象包含的数据的值文本。
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        set.setHighLightColor(Color.TRANSPARENT);// 设置点击某个点时，横竖两条线的颜色
        d.addDataSet(set);

        return d;
    }


    /**
     * 设置柱状图的数据
     * @return
     */
    private BarData generateBarData2() {

        int count =12;
        int range = 20;
        int barColor = Color.rgb(214, 237, 255);
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        float start = 0f;
        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            yVals1.add(new BarEntry(i + 1f, val));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "Bar 1");
        set1.setColor(barColor);
        set1.setDrawValues(false);
        set1.setValueTextColor(barColor);
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(0.8f);
        return data;
    }


    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();

        for (int index = 0; index < itemcount; index++) {
            entries1.add(new BarEntry(0, getRandom(25, 25)));

            // stacked
            entries2.add(new BarEntry(0, new float[]{getRandom(13, 12), getRandom(13, 12)}));
        }

        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(entries2, "");
        set2.setStackLabels(new String[]{"Stack 1", "Stack 2"});
        set2.setColors(new int[]{Color.rgb(61, 165, 255), Color.rgb(23, 197, 255)});
        set2.setValueTextColor(Color.rgb(61, 165, 255));
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;

        //float barSpace = 0.02f; // x2 dataset
        float barSpace = 100f; // x2 dataset

        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }
}
