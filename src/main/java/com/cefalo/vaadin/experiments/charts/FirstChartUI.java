package com.cefalo.vaadin.experiments.charts;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.util.Random;

/**
 * Created by jobaer on 1/24/2016.
 */
@Widgetset("com.cefalo.vaadin.MyAppWidgetset")
public class FirstChartUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Chart chart = dynamicSpline();

        setContent(chart);
    }

    private Chart emailRecommendations() {
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();

        conf.setTitle(new Title("E-mail Recommendations"));

        XAxis xAxis = new XAxis();
        xAxis.setCategories(
            "1h", "2h", "3h", "4h", "5h", "6h",
            "7h", "8h", "9h", "10h", "11h", "12h",
            "13h", "142h", "15h", "16h", "17h", "18h",
            "19h", "20h", "21h", "22h", "23h", "24h"
        );
        Labels labels = xAxis.getLabels();
        labels.setStep(2);
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setType(AxisType.LOGARITHMIC);
        yAxis.setTitle("Number of Recommendations");
        StackLabels sLabels = new StackLabels(true);
        yAxis.setStackLabels(sLabels);
        conf.addyAxis(yAxis);

        Legend legend = new Legend();
        legend.setFloating(true);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);
        legend.setX(0);
        legend.setY(20);
        conf.setLegend(legend);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() { return '"
                + "'+this.series.name +': '+ this.y +''+' Total: '+ this.point.stackTotal;}");
        conf.setTooltip(tooltip);

        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.NORMAL);
        conf.setPlotOptions(plotOptions);

        ListSeries day = new ListSeries("Day",
            54090, 3400, 4020, 1002, 2003, 23000, 34000, 4500, 11000, 22300, 4300, 5600,
            5590, 6400, 7020, 2002, 3003, 24000, 54000, 6500, 11000, 22400, 2300, 7600
        );
        PlotOptionsColumn options1 = new PlotOptionsColumn();
        options1.setColor(new SolidColor(213, 238, 251));
        day.setPlotOptions(options1);


        conf.addSeries(day);
        ListSeries sales = new ListSeries("Sales",
            5090, 34000, 19020, 17002, 5003, 25000, 3000, 5600, 61000, 2300, 4800, 4600,
            9590, 44700, 15020, 20002, 3003, 40300, 5000, 3500, 1000, 82400, 2500, 5600
        );
        PlotOptionsColumn salesOptions = new PlotOptionsColumn();
        salesOptions.setColor(new SolidColor(26, 184, 152));
        sales.setPlotOptions(salesOptions);
        conf.addSeries(sales);

        chart.drawChart(conf);
        return chart;
    }

    private Chart basicColumn() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Test data");
        ListSeries series = new ListSeries("Diameters");
        series.setData(4900,  12100,  12800,
            6800,  143000, 125000,
            51100, 49500);
        conf.addSeries(series);
        return chart;
    }

    private Chart dynamicSpline() {
        final Random random = new Random();

        final Chart chart = new Chart();
        chart.setWidth("500px");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SPLINE);
        configuration.getTitle().setText("Live random data");

        XAxis xAxis = configuration.getxAxis();
        xAxis.setType(AxisType.DATETIME);
        xAxis.setTickPixelInterval(150);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle("Value");
        PlotLine plotline = new PlotLine();
        plotline.setValue(0);
        plotline.setWidth(1);
        plotline.setColor(new SolidColor("#808080"));
        yAxis.setPlotLines(new PlotLine[] { plotline });

        // FIXME missing generated API
        configuration.getTooltip().setEnabled(false);
        configuration.getLegend().setEnabled(false);

        final DataSeries series = new DataSeries();
        series.setPlotOptions(new PlotOptionsSpline());
        series.setName("Random data");
        for (int i = -19; i <= 0; i++) {
            series.add(new DataSeriesItem(
                System.currentTimeMillis() + i * 1000, random.nextDouble()));
        }

        AbstractChartExample.runWhileAttached(chart, new Runnable() {

            @Override
            public void run() {
                final long x = System.currentTimeMillis();
                final double y = random.nextDouble();
                series.add(new DataSeriesItem(x, y), true, true);
            }
        }, 1000, 1000);

        configuration.setSeries(series);

        chart.drawChart(configuration);
        return chart;
    }

    @WebServlet(urlPatterns = "/charts/*", name = "MyLoginServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = FirstChartUI.class, productionMode = false)
    public static class MyLoginServlet extends VaadinServlet {

    }
}
