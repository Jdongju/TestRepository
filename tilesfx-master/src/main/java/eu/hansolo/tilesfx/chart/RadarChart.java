/*
 * Copyright (c) 2017 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.tilesfx.chart;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static eu.hansolo.tilesfx.tools.Helper.adjustTextSize;
import static eu.hansolo.tilesfx.tools.Helper.clamp;
import static eu.hansolo.tilesfx.tools.Helper.getContrastColor;


/**
 * Created by hansolo on 10.06.17.
 */
public class RadarChart extends Region {
    public  enum Mode { SECTOR, POLYGON };
    private static final int                       MIN_NO_OF_SECTORS = 4;
    private static final int                       MAX_NO_OF_SECTORS = 128;
    private static final double                    PREFERRED_WIDTH   = 120;
    private static final double                    PREFERRED_HEIGHT  = 120;
    private static final double                    MINIMUM_WIDTH     = 10;
    private static final double                    MINIMUM_HEIGHT    = 10;
    private static final double                    MAXIMUM_WIDTH     = 1024;
    private static final double                    MAXIMUM_HEIGHT    = 1024;
    private              double                    size;
    private              Pane                      pane;
    private              Canvas                    chartCanvas;
    private              GraphicsContext           chartCtx;
    private              Canvas                    overlayCanvas;
    private              GraphicsContext           overlayCtx;
    private              Text                      unitText;
    private              Text                      minValueText;
    private              Text                      legend1Text;
    private              Text                      legend2Text;
    private              Text                      legend3Text;
    private              Text                      legend4Text;
    private              Text                      maxValueText;
    private              DropShadow                dropShadow;
    private              double                    legendStep;
    private              int                       decimals;
    private              String                    formatString;
    private              IntegerProperty           noOfSectors;
    private              double                    angleStep;
    private              ObservableList<ChartData> data;
    private              DoubleProperty            minValue;
    private              DoubleProperty            maxValue;
    private              DoubleProperty            threshold;
    private              DoubleProperty            range;
    private              StringProperty            unit;
    private              BooleanProperty           legendVisible;
    private              BooleanProperty           thresholdVisible;
    private              ObservableList<Stop>      gradientStops;
    private              List<Stop>                stops;
    private              ObjectProperty<Color>     chartBackgroundColor;
    private              ObjectProperty<Color>     chartForegroundColor;
    private              ObjectProperty<Color>     chartTextColor;
    private              ObjectProperty<Color>     gridColor;
    private              ObjectProperty<Paint>     chartFill;
    private              ObjectProperty<Color>     thresholdColor;
    private              ObjectProperty<Mode>      mode;
    private              double                    legendScaleFactor;
    private              InvalidationListener      resizeListener;
    private              InvalidationListener      redrawListener;
    private              InvalidationListener      redrawOverlayListener;
    private              ListChangeListener<Stop>  gradientListener;


    // ******************** Constructors **************************************
    public RadarChart() { this(null); }
    public RadarChart(final List<ChartData> DATA) {
        minValue              = new DoublePropertyBase(0) {
            @Override public void set(final double MIN_VALUE) {
                super.set(clamp(-Double.MAX_VALUE, getMaxValue(), MIN_VALUE));
                range.set(getMaxValue() - get());
            }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "minValue"; }
        };
        maxValue              = new DoublePropertyBase(100) {
            @Override protected void invalidated() {
                set(clamp(getMinValue(), Double.MAX_VALUE, get()));
                range.set(get() - getMinValue());
                redraw();
            }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "maxValue"; }
        };
        threshold             = new DoublePropertyBase(1) {
            @Override protected void invalidated() {
                set(clamp(minValue.get(), maxValue.get(), get()));
                range.set(getMaxValue() - get());
                redraw();
            }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "threshold"; }
        };
        range                 = new DoublePropertyBase(100) {
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "range"; }
        };
        noOfSectors           = new IntegerPropertyBase(MIN_NO_OF_SECTORS) {
            @Override public void set(final int NO_OF_SECTORS) {
                super.set(clamp(1, MAX_NO_OF_SECTORS, NO_OF_SECTORS));
                angleStep = 360.0 / get();
            }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "noOfSectors"; }
        };
        unit                  = new StringPropertyBase("") {
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "unit"; }
        };
        legendVisible         = new BooleanPropertyBase(false) {
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "legendVisible"; }
        };
        thresholdVisible      = new BooleanPropertyBase() {
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "thresholdVisible"; }
        };
        mode                  = new ObjectPropertyBase<Mode>(Mode.POLYGON) {
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "mode"; }
        };
        gradientStops         = FXCollections.observableArrayList();
        decimals              = 0;
        formatString          = new StringBuilder("%.").append(decimals).append("f").toString();
        data                  = FXCollections.observableArrayList();
        legendScaleFactor     = 1.0;
        chartBackgroundColor  = new ObjectPropertyBase<Color>(Color.TRANSPARENT) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "chartBackgroundColor"; }
        };
        chartForegroundColor  = new ObjectPropertyBase<Color>(Tile.FOREGROUND) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "chartForegroundColor"; }
        };
        chartTextColor        = new ObjectPropertyBase<Color>(Tile.FOREGROUND) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "chartTextColor"; }
        };
        gridColor             = new ObjectPropertyBase<Color>(Tile.GRAY) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "gridColor"; }
        };
        chartFill             = new ObjectPropertyBase<Paint>(Tile.BLUE) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "chartFile"; }
        };
        thresholdColor        = new ObjectPropertyBase<Color>(Tile.LIGHT_RED) {
            @Override protected void invalidated() { redraw(); }
            @Override public Object getBean() { return RadarChart.this; }
            @Override public String getName() { return "thresholdColor"; }
        };
        resizeListener        = o -> resize();
        redrawListener        = o -> redraw();
        redrawOverlayListener = o -> drawOverlay();
        gradientListener      = change -> {
            stops.clear();
            for (Stop stop : getGradientStops()) {
                if (Double.compare(stop.getOffset(), 0.0) == 0) { stops.add(new Stop(0, stop.getColor())); }
                stops.add(new Stop(stop.getOffset() * 0.69924 + 0.285, stop.getColor()));
            }
            redraw();
        };
        initData(DATA);

        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initData(final List<ChartData> DATA) {
        if (null == DATA || DATA.isEmpty()) {
            for (int i = 0; i < (getNoOfSectors() + 1); i++) { addData(new ChartData(0d)); }
        } else {
            setData(DATA);
        }
    }

    private void init() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (Double.compare(getMinWidth(), 0.0) <= 0 || Double.compare(getMinHeight(), 0.0) <= 0) {
            setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getMaxWidth(), 0.0) <= 0 || Double.compare(getMaxHeight(), 0.0) <= 0) {
            setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        stops = new ArrayList<>(16);
        for (Stop stop : getGradientStops()) {
            if (Double.compare(stop.getOffset(), 0.0) == 0) stops.add(new Stop(0, stop.getColor()));
            stops.add(new Stop(stop.getOffset() * 0.69924 + 0.285, stop.getColor()));
        }

        chartCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        chartCtx    = chartCanvas.getGraphicsContext2D();

        overlayCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        overlayCtx    = overlayCanvas.getGraphicsContext2D();

        unitText = new Text(getUnit());
        unitText.setTextAlignment(TextAlignment.CENTER);
        unitText.setTextOrigin(VPos.CENTER);
        unitText.setFont(Fonts.latoLight(0.045 * PREFERRED_WIDTH));

        legendStep = (getMaxValue() - getMinValue()) / 5d;
        dropShadow = new DropShadow(BlurType.TWO_PASS_BOX, Color.BLACK, 5, 0, 0, 0);

        minValueText = new Text(String.format(Locale.US, formatString, getMinValue()));
        minValueText.setTextAlignment(TextAlignment.CENTER);
        minValueText.setTextOrigin(VPos.CENTER);
        minValueText.setVisible(isLegendVisible());
        minValueText.setEffect(dropShadow);

        legend1Text = new Text(String.format(Locale.US, formatString, getMinValue() + legendStep));
        legend1Text.setTextAlignment(TextAlignment.CENTER);
        legend1Text.setTextOrigin(VPos.CENTER);
        legend1Text.setVisible(isLegendVisible());
        legend1Text.setEffect(dropShadow);

        legend2Text = new Text(String.format(Locale.US, formatString, getMinValue() + legendStep * 2));
        legend2Text.setTextAlignment(TextAlignment.CENTER);
        legend2Text.setTextOrigin(VPos.CENTER);
        legend2Text.setVisible(isLegendVisible());
        legend2Text.setEffect(dropShadow);

        legend3Text = new Text(String.format(Locale.US, formatString, getMinValue() + legendStep * 3));
        legend3Text.setTextAlignment(TextAlignment.CENTER);
        legend3Text.setTextOrigin(VPos.CENTER);
        legend3Text.setVisible(isLegendVisible());
        legend3Text.setEffect(dropShadow);

        legend4Text = new Text(String.format(Locale.US, formatString, getMinValue() + legendStep * 3));
        legend4Text.setTextAlignment(TextAlignment.CENTER);
        legend4Text.setTextOrigin(VPos.CENTER);
        legend4Text.setVisible(isLegendVisible());
        legend4Text.setEffect(dropShadow);

        maxValueText = new Text(String.format(Locale.US, formatString, getMaxValue()));
        maxValueText.setTextAlignment(TextAlignment.CENTER);
        maxValueText.setTextOrigin(VPos.CENTER);
        maxValueText.setVisible(isLegendVisible());
        maxValueText.setEffect(dropShadow);

        // Add all nodes
        pane = new Pane(chartCanvas, overlayCanvas, unitText, minValueText, legend1Text, legend2Text, legend3Text, legend4Text, maxValueText);
        pane.setBackground(new Background(new BackgroundFill(getChartBackgroundColor(), new CornerRadii(1024), Insets.EMPTY)));

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        widthProperty().addListener(resizeListener);
        heightProperty().addListener(resizeListener);
        unit.addListener(redrawListener);
        legendVisible.addListener(redrawListener);
        minValue.addListener(redrawListener);
        maxValue.addListener(redrawListener);
        //data.addListener((MapChangeListener<Integer, ChartData>) change -> redraw());
        thresholdVisible.addListener(redrawOverlayListener);
        mode.addListener(redrawListener);
        noOfSectors.addListener(redrawListener);
        gradientStops.addListener(gradientListener);
    }

    public void dispose() {
        widthProperty().removeListener(resizeListener);
        heightProperty().removeListener(resizeListener);
        unit.removeListener(redrawListener);
        legendVisible.removeListener(redrawListener);
        minValue.removeListener(redrawListener);
        maxValue.removeListener(redrawListener);
        thresholdVisible.removeListener(redrawOverlayListener);
        mode.removeListener(redrawListener);
        noOfSectors.removeListener(redrawListener);
        gradientStops.removeListener(gradientListener);
    }


    // ******************** Methods *******************************************
    public double getMinValue() { return minValue.get(); }
    public void setMinValue(final double VALUE) { minValue.set(VALUE); }
    public ReadOnlyDoubleProperty minValueProperty() { return minValue; }

    public double getMaxValue() { return maxValue.get(); }
    public void setMaxValue(final double VALUE) { maxValue.set(VALUE); }
    public ReadOnlyDoubleProperty maxValueProperty() { return maxValue; }

    public double getRange() { return range.get(); }
    public ReadOnlyDoubleProperty rangeProperty() { return range; }

    public boolean isThresholdVisible() { return thresholdVisible.get(); }
    public void setThresholdVisible(final boolean VISIBLE) { thresholdVisible.set(VISIBLE); }
    public BooleanProperty thresholdVisibleProperty() { return thresholdVisible; }

    public ObservableList<Stop> getGradientStops() { return gradientStops; }
    public void setGradientStops(final List<Stop> STOPS) { gradientStops.setAll(STOPS); }
    public void setGradientStops(final Stop... STOPS) { gradientStops.setAll(STOPS); }
    public void addGradientStop(final Stop STOP) { gradientStops.add(STOP); }

    public String getUnit() { return unit.get(); }
    public void setUnit(final String TEXT) { unit.set(TEXT); }
    public StringProperty unitProperty() { return unit; }

    public boolean isLegendVisible() { return legendVisible.get(); }
    public void setLegendVisible(final boolean VISIBLE) { legendVisible.set(VISIBLE); }
    public BooleanProperty legendVisibleProperty() { return legendVisible; }

    public ObservableList<ChartData> getData() { return data; }
    public void setData(final List<ChartData> DATA) {
        if (DATA.size() < MIN_NO_OF_SECTORS) throw new IllegalArgumentException("Not enough sectors (min. " + MIN_NO_OF_SECTORS + "needed)");
        if (DATA.size() > MAX_NO_OF_SECTORS) throw new IllegalArgumentException("Too many sectors (max. " + MAX_NO_OF_SECTORS + " sectors allowed)");
        DATA.forEach(d -> addData(d));
    }
    public void addData(final ChartData DATA) {
        if (data.size() > (getNoOfSectors() + 1)) throw new IllegalArgumentException("Too many sectors (max. " + getNoOfSectors() + " sectors allowed)");
        data.add(DATA);
        noOfSectors.set(data.size());
    }

    public void reset() {
        data.clear();
        initData(data);
    }

    public Color getChartBackgroundColor() { return chartBackgroundColor.getValue(); }
    public void setChartBackgroundColor(Color COLOR) { chartBackgroundColor.setValue(COLOR); }
    public ObjectProperty<Color> chartBackgroundColorProperty() { return chartBackgroundColor; }

    public Color getChartForegroundColor() { return chartForegroundColor.getValue(); }
    public void setChartForegroundColor(final Color COLOR) { chartForegroundColor.setValue(COLOR); }
    public ObjectProperty<Color> chartForegroundColorProperty() { return chartForegroundColor; }

    public Color getChartTextColor() { return chartTextColor.getValue(); }
    public void setChartTextColor(Color COLOR) { chartTextColor.setValue(COLOR); }
    public ObjectProperty<Color> chartTextColorProperty() { return chartTextColor; }

    public Color getGridColor() { return gridColor.getValue(); }
    public void setGridColor(Color COLOR) { gridColor.setValue(COLOR); }
    public ObjectProperty<Color> gridColorProperty() { return gridColor; }

    public Paint getChartFill() { return chartFill.getValue(); }
    public void setChartFill(final Paint PAINT) { chartFill.setValue(PAINT); }
    public ObjectProperty<Paint> chartFillProperty() { return chartFill; }

    public Color getThresholdColor() { return thresholdColor.getValue(); }
    public void setThresholdColor(final Color COLOR) { thresholdColor.setValue(COLOR); }
    public ObjectProperty<Color> thresholdColorProperty() { return thresholdColor; }

    public void scaleLegendToValue(final double VALUE) {
        legendScaleFactor = VALUE;
        redrawText();
    }

    public Mode getMode() { return mode.get(); }
    public void setMode(final Mode MODE) { mode.set(MODE); }
    public ObjectProperty<Mode> modeProperty() { return mode; }

    public int getNoOfSectors() { return noOfSectors.get(); }
    public IntegerProperty noOfSectorsProperty() { return noOfSectors; }


    // ******************** Style related *************************************
    @Override public String getUserAgentStylesheet() {
        return RadarChart.class.getResource("radarchart.css").toExternalForm();
    }

    //public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() { return FACTORY.getCssMetaData(); }

    //@Override public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() { return FACTORY.getCssMetaData(); }


    // ******************** Private Methods ***********************************
    private void resize() {
        double width  = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        size          = width < height ? width : height;

        if (size > 0) {
            pane.setMaxSize(size, size);
            pane.relocate((getWidth() - size) * 0.5, (getHeight() - size) * 0.5);
            pane.setBackground(new Background(new BackgroundFill(getChartBackgroundColor(), new CornerRadii(1024), Insets.EMPTY)));

            chartCanvas.setWidth(size);
            chartCanvas.setHeight(size);

            overlayCanvas.setWidth(size);
            overlayCanvas.setHeight(size);

            redraw();
        }
    }

    private void redrawText() {
        Color textColor = getChartTextColor();
        dropShadow.setColor(null == textColor ? Color.BLACK : getContrastColor(textColor));
        dropShadow.setRadius(size * 0.025);

        unitText.setFill(textColor);
        unitText.setText(getUnit());
        unitText.setFont(Fonts.latoRegular(size * 0.1));
        adjustTextSize(unitText, size * 0.22, size * 0.1);
        unitText.relocate((size - unitText.getLayoutBounds().getWidth()) * 0.5, (size - unitText.getLayoutBounds().getHeight()) * 0.5);

        minValueText.setVisible(isLegendVisible());
        legend1Text.setVisible(isLegendVisible());
        legend2Text.setVisible(isLegendVisible());
        legend3Text.setVisible(isLegendVisible());
        legend4Text.setVisible(isLegendVisible());
        maxValueText.setVisible(isLegendVisible());

        if (isLegendVisible()) {
            Font font = Fonts.latoRegular(size * 0.025);

            minValueText.setFill(textColor);
            minValueText.setText(String.format(Locale.US, formatString, getMinValue()));
            minValueText.setFont(font);
            minValueText.relocate((size - minValueText.getLayoutBounds().getWidth()) * 0.5, 0.3435 * size);

            legendStep = getRange() / 5.0;

            legend1Text.setFill(textColor);
            legend1Text.setText(String.format(Locale.US, formatString, (getMinValue() + legendStep) * legendScaleFactor));
            legend1Text.setFont(font);
            legend1Text.relocate((size - legend1Text.getLayoutBounds().getWidth()) * 0.5, 0.29 * size);

            legend2Text.setFill(textColor);
            legend2Text.setText(String.format(Locale.US, formatString, (getMinValue() + legendStep * 2) * legendScaleFactor));
            legend2Text.setFont(font);
            legend2Text.relocate((size - legend2Text.getLayoutBounds().getWidth()) * 0.5, 0.225 * size);

            legend3Text.setFill(textColor);
            legend3Text.setText(String.format(Locale.US, formatString, (getMinValue() + legendStep * 3) * legendScaleFactor));
            legend3Text.setFont(font);
            legend3Text.relocate((size - legend3Text.getLayoutBounds().getWidth()) * 0.5, 0.1599 * size);

            legend4Text.setFill(textColor);
            legend4Text.setText(String.format(Locale.US, formatString, (getMinValue() + legendStep * 4) * legendScaleFactor));
            legend4Text.setFont(font);
            legend4Text.relocate((size - legend4Text.getLayoutBounds().getWidth()) * 0.5, 0.097 * size);

            maxValueText.setFill(textColor);
            maxValueText.setText(String.format(Locale.US, formatString, getMaxValue()));
            maxValueText.setFont(font);
            maxValueText.relocate((size - maxValueText.getLayoutBounds().getWidth()) * 0.5, 0.048 * size);
        }
    }

    public void redraw() {
        chartCanvas.setCache(false);
        drawChart();
        chartCanvas.setCache(true);
        chartCanvas.setCacheHint(CacheHint.QUALITY);

        overlayCanvas.setCache(false);
        drawOverlay();
        overlayCanvas.setCache(true);
        overlayCanvas.setCacheHint(CacheHint.QUALITY);

        redrawText();
    }

    private void drawChart() {
        final double CENTER_X      = 0.5 * size;
        final double CENTER_Y      = CENTER_X;
        final double CIRCLE_SIZE   = 0.9 * size;
        final double DATA_RANGE    = getRange();
        final double RANGE         = 0.35714 * CIRCLE_SIZE;
        final double OFFSET        = 0.14286 * CIRCLE_SIZE;
        final int    NO_OF_SECTORS = getNoOfSectors();
        final double MIN_VALUE     = getMinValue();
        final double MAX_VALUE     = getMaxValue();

        // clear the chartCanvas
        chartCtx.clearRect(0, 0, size, size);

        // draw the chart background
        //chartCtx.setFill(getChartFill());
        //chartCtx.fillOval((size - CIRCLE_SIZE) * 0.5, (size - CIRCLE_SIZE) * 0.5, CIRCLE_SIZE, CIRCLE_SIZE);

        // draw the chart data
        chartCtx.save();
        if (gradientStops.isEmpty()) {
            chartCtx.setFill(getChartFill());
        } else {
            chartCtx.setFill(new RadialGradient(0, 0,
                                                CENTER_X, CENTER_Y,
                                                CIRCLE_SIZE * 0.5,
                                                false, CycleMethod.NO_CYCLE,
                                                stops));
        }

        double radiusFactor;
        switch(mode.get()) {
            case POLYGON:
                chartCtx.beginPath();
                chartCtx.moveTo(CENTER_X, 0.36239 * size);
                for (int i = 0 ; i < NO_OF_SECTORS ; i++) {
                    radiusFactor = (clamp(MIN_VALUE, MAX_VALUE, (data.get(i).getValue()) - MIN_VALUE) / DATA_RANGE);
                    //chartCtx.lineTo(CENTER_X, CENTER_Y - OFFSET - radiusFactor * RANGE);
                    chartCtx.lineTo(CENTER_X, CENTER_Y - OFFSET - radiusFactor * RANGE);

                    chartCtx.translate(CENTER_X, CENTER_Y);
                    chartCtx.rotate(angleStep);
                    chartCtx.translate(-CENTER_X, -CENTER_Y);
                }
                radiusFactor = ((clamp(MIN_VALUE, MAX_VALUE, data.get(NO_OF_SECTORS - 1).getValue()) - MIN_VALUE) / DATA_RANGE);
                chartCtx.lineTo(CENTER_X, CENTER_Y - OFFSET - radiusFactor * RANGE);
                chartCtx.closePath();
                chartCtx.fill();

                break;
            case SECTOR:
                chartCtx.translate(CENTER_X, CENTER_Y);
                chartCtx.rotate(-90);
                chartCtx.translate(-CENTER_X, -CENTER_Y);
                // sector mode
                for (int i = 0 ; i < NO_OF_SECTORS ; i++) {
                    radiusFactor = (clamp(MIN_VALUE, MAX_VALUE, (data.get(i).getValue() - MIN_VALUE)) / DATA_RANGE);
                    chartCtx.beginPath();
                    chartCtx.moveTo(CENTER_X, CENTER_Y);
                    chartCtx.arc(CENTER_X, CENTER_Y, radiusFactor * RANGE + OFFSET, radiusFactor * RANGE + OFFSET, 0, -angleStep);
                    chartCtx.closePath();
                    chartCtx.fill();

                    chartCtx.translate(CENTER_X, CENTER_Y);
                    chartCtx.rotate(angleStep);
                    chartCtx.translate(-CENTER_X, -CENTER_Y);
                }
                break;
        }
        chartCtx.restore();
    }

    private void drawOverlay() {
        final Paint  CHART_BKG     = getChartBackgroundColor();
        final double CENTER_X      = 0.5 * size;
        final double CENTER_Y      = CENTER_X;
        final double CIRCLE_SIZE   = 0.90 * size;
        final double DATA_RANGE    = getRange();
        final double RANGE         = 0.35714 * CIRCLE_SIZE;
        final double OFFSET        = 0.14286 * CIRCLE_SIZE;
        final int    NO_OF_SECTORS = getNoOfSectors();
        final double MIN_VALUE     = getMinValue();
        double radius;

        // clear the chartCanvas
        overlayCtx.clearRect(0, 0, size, size);

        // draw center point
        overlayCtx.save();
        overlayCtx.setFill(CHART_BKG);
        overlayCtx.translate(CENTER_X - OFFSET, CENTER_Y - OFFSET);
        overlayCtx.fillOval(0, 0, 2 * OFFSET, 2 * OFFSET);
        overlayCtx.restore();

        // draw concentric rings
        overlayCtx.setLineWidth(1);
        overlayCtx.setStroke(getGridColor());
        double ringStepSize = (CIRCLE_SIZE - CIRCLE_SIZE * 0.28571) / 20.0;
        double pos          = 0.5 * (size - CIRCLE_SIZE);
        double ringSize     = CIRCLE_SIZE;
        for (int i = 0 ; i < 11 ; i++) {
            overlayCtx.strokeOval(pos, pos, ringSize, ringSize);
            pos      += ringStepSize;
            ringSize -= 2 * ringStepSize;
        }

        // draw star lines
        overlayCtx.save();
        for (int i = 0 ; i < NO_OF_SECTORS ; i++) {
            overlayCtx.strokeLine(CENTER_X, 0.37 * size, CENTER_X, 0.5 * (size - CIRCLE_SIZE));
            overlayCtx.translate(CENTER_X, CENTER_Y);
            overlayCtx.rotate(angleStep);
            overlayCtx.translate(-CENTER_X, -CENTER_Y);
        }
        overlayCtx.restore();

        // draw threshold line
        if (isThresholdVisible()) {
            radius = ((threshold.get() - MIN_VALUE) / DATA_RANGE);
            overlayCtx.setLineWidth(clamp(1d, 3d, size * 0.005));
            overlayCtx.setStroke(getThresholdColor());
            overlayCtx.strokeOval(0.5 * size - OFFSET - radius * RANGE, 0.5 * size - OFFSET - radius * RANGE,
                                  2 * (radius * RANGE + OFFSET), 2 * (radius * RANGE + OFFSET));
        }

        // prerotate if sectormode
        overlayCtx.save();

        if (Mode.SECTOR == mode.get()) {
            overlayCtx.translate(CENTER_X, CENTER_Y);
            overlayCtx.rotate(angleStep * 0.5);
            overlayCtx.translate(-CENTER_X, -CENTER_Y);
        }

        // draw text
        overlayCtx.save();
        overlayCtx.setFont(Fonts.latoRegular(0.04 * size));
        overlayCtx.setTextAlign(TextAlignment.CENTER);
        overlayCtx.setTextBaseline(VPos.CENTER);
        overlayCtx.setFill(getChartForegroundColor());
        for (int i = 0 ; i < NO_OF_SECTORS ; i++) {
            overlayCtx.fillText(data.get(i).getName(), CENTER_X, size * 0.02);
            overlayCtx.translate(CENTER_X, CENTER_Y);
            overlayCtx.rotate(angleStep);
            overlayCtx.translate(-CENTER_X, -CENTER_Y);
        }
        overlayCtx.restore();

        overlayCtx.restore();
    }
}
