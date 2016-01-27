package com.cefalo.vaadin.experiments.charts;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 * Created by jobaer on 1/27/16.
 */
@Push
@Widgetset("com.cefalo.vaadin.MyAppWidgetset")
public class DynamicUpdateUI extends UI {
    Chart chart = new Chart(ChartType.AREASPLINE);
    DataSeries series = new DataSeries();

    @Override
    protected void init(VaadinRequest request) {
        chart.setSizeFull();
        setContent(chart);

        // Prepare the data display
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Dynamic update of charts");
        conf.setSeries(series);

        // Start the data feed thread
        new FeederThread().start();
    }

    class FeederThread extends Thread {
        int count = 0;

        @Override
        public void run() {
            try {
                // Update the data for a while
                while (count < 100) {
                    Thread.sleep(1000);

                    access(new Runnable() {
                        @Override
                        public void run() {
                            double y = Math.random();
                            series.add(
                                new DataSeriesItem(count++, y),
                                true, count > 10);
                        }
                    });
                }

                // Inform that we have stopped running
                access(new Runnable() {
                    @Override
                    public void run() {
                        setContent(new Label("Done!"));
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @WebServlet(urlPatterns = "/push/*", name = "MyPushServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DynamicUpdateUI.class, productionMode = false)
    public static class MyPushServlet extends VaadinServlet {

    }

}