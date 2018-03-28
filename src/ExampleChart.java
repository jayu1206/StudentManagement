import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

@SuppressWarnings("serial")
public class ExampleChart extends ApplicationFrame {

   ExampleChart(String titel) {
      super(titel);

      final CategoryDataset dataset = createDataset();
      final JFreeChart chart = createChart(dataset);
      final ChartPanel chartPanel = new ChartPanel(chart);
      chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
      setContentPane(chartPanel);
   }

   private CategoryDataset createDataset() {
      DefaultCategoryDataset result = new DefaultCategoryDataset();

      result.addValue(450, "First long label (One)", "Category A");
      result.addValue(450, "First long label (One)", "Category B");
      result.addValue(450, "First long label (One)", "Category C");

      result.addValue(450, "First long label (Two)", "Category A");
      result.addValue(450, "First long label (Two)", "Category B");
      result.addValue(450, "First long label (Two)", "Category C");

      result.addValue(450, "Second long label (One)", "Category A");
      result.addValue(450, "Second long label (One)", "Category B");
      result.addValue(450, "Second long label (One)", "Category C");

      result.addValue(450, "Second long label (Two)", "Category A");
      result.addValue(450, "Second long label (Two)", "Category B");
      result.addValue(450, "Second long label (Two)", "Category C");

      result.addValue(450, "Third long label (One)", "Category A");
      result.addValue(450, "Third long label (One)", "Category B");
      result.addValue(450, "Third long label (One)", "Category C");

      result.addValue(450, "Third long label (Two)", "Category A");
      result.addValue(450, "Third long label (Two)", "Category B");
      result.addValue(450, "Third long label (Two)", "Category C");

      result.addValue(1350, "Fourth long label (One)", "Category A");
      result.addValue(1350, "Fourth long label (One)", "Category B");
      result.addValue(1350, "Fourth long label (One)", "Category C");

      result.addValue(1350, "Fourth long label (Two)", "Category A");
      result.addValue(1350, "Fourth long label (Two)", "Category B");
      result.addValue(1350, "Fourth long label (Two)", "Category C");

      return result;
   }

   private JFreeChart createChart(final CategoryDataset dataset) {

      final JFreeChart chart = ChartFactory.createStackedBarChart("Chart", // chart
                                                            // title
            "", // domain axis label
            "h", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL, // the plot orientation
            true, // legend
            true, // tooltips
            false // urls
            );

      chart.getLegend().setFrame(BlockBorder.NONE);
      chart.getLegend().setPosition(RectangleEdge.RIGHT);
      chart.getPlot().setBackgroundPaint(Color.white);

      GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
      renderer.setItemMargin(0.5);

      KeyToGroupMap map = new KeyToGroupMap("G1");
      map.mapKeyToGroup("First long label (One)", "G1");
      map.mapKeyToGroup("First long label (Two)", "G1");
      map.mapKeyToGroup("Second long label (One)", "G2");
      map.mapKeyToGroup("Second long label (Two)", "G2");
      map.mapKeyToGroup("Third long label (One)", "G3");
      map.mapKeyToGroup("Third long label (Two)", "G3");
      map.mapKeyToGroup("Fourth long label (One)", "G4");
      map.mapKeyToGroup("Fourth long label (Two)", "G4");
      renderer.setSeriesToGroupMap(map);

      // Colors
      renderer.setItemMargin(0.05);
      Paint p1 = new GradientPaint(0.0f, 0.0f, new Color(16, 89, 172), 0.0f,
            0.0f, new Color(201, 201, 244));
      renderer.setSeriesPaint(0, p1);
      renderer.setSeriesPaint(2, p1);
      renderer.setSeriesPaint(4, p1);
      renderer.setSeriesPaint(6, p1);
      renderer.setSeriesPaint(8, p1);
      renderer.setSeriesPaint(10, p1);
      renderer.setSeriesPaint(12, p1);

      Paint p2 = new GradientPaint(0.0f, 0.0f, new Color(10, 144, 40), 0.0f,
            0.0f, new Color(160, 240, 180));
      renderer.setSeriesPaint(1, p2);
      renderer.setSeriesPaint(3, p2);
      renderer.setSeriesPaint(5, p2);
      renderer.setSeriesPaint(7, p2);
      renderer.setSeriesPaint(9, p2);
      renderer.setSeriesPaint(11, p2);

      // Subcategories
      SubCategoryAxis domainAxis = new SubCategoryAxis("");
      domainAxis.addSubCategory("First long label");
      domainAxis.addSubCategory("Second long label");
      domainAxis.addSubCategory("Third long label");
      domainAxis.addSubCategory("Fourth long label");

      domainAxis.setCategoryMargin(0.01);
      domainAxis.setSubLabelFont(new Font(Font.SANS_SERIF, Font.PLAIN, 9));
      domainAxis.setTickLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

      domainAxis.setSubLabelFont((Font) domainAxis.getSubLabelFont()
            .deriveFont(1, AffineTransform.getRotateInstance(Math.PI / 2)));
      domainAxis.setCategoryLabelPositionOffset(100);

      domainAxis.setLabel("\n \n");

      domainAxis.setAxisLineVisible(false);
      domainAxis.setMaximumCategoryLabelLines(2);

      // all bars
      CategoryPlot plot = (CategoryPlot) chart.getPlot();
      plot.getRangeAxis().setMinorTickMarkOutsideLength(22F);
      plot.setDomainAxis(domainAxis);
      plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
      plot.setRenderer(renderer);
      plot.setFixedLegendItems(createLegendItems());
      plot.setRangeGridlinePaint(Color.black);

      return chart;
   }

   private LegendItemCollection createLegendItems() {
      LegendItemCollection result = new LegendItemCollection();
      LegendItem item1 = new LegendItem("One", "One", "One", "One",
            new Rectangle(10, 10), new GradientPaint(0.0f, 0.0f, new Color(
                  16, 89, 172), 0.0f, 0.0f, new Color(201, 201, 244)));
      LegendItem item2 = new LegendItem("Two", "Two", "Two", "Two",
            new Rectangle(10, 10), new GradientPaint(0.0f, 0.0f, new Color(
                  10, 144, 40), 0.0f, 0.0f, new Color(160, 240, 180)));

      result.add(item1);
      result.add(item2);

      return result;
   }

   public static void main(final String[] args) {
      final ExampleChart demo = new ExampleChart("Chart");
      demo.pack();
      RefineryUtilities.centerFrameOnScreen(demo);
      demo.setVisible(true);
   }
}