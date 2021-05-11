package com.yaof.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @Author yaof
 * @Date 2021-04-27
 */
public class JpgUtils {


    public static void createImage(String path) throws IOException{
        Map<String, List<Integer>> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        map.put("苹果", Arrays.asList(new Integer[]{54, 78}));
        map.put("李子", Arrays.asList(new Integer[]{23, 43}));
        map.put("西瓜", Arrays.asList(new Integer[]{23, 33}));
        map.put("橘子", Arrays.asList(new Integer[]{65, 82}));

        list.addAll(Arrays.asList(new String[]{"苹果", "李子", "西瓜", "橘子"}));

        getNumBarChart(map, list, path);
    }

    /**
     *  升降数的柱形图
     *  参数 map 存 msg ： 三期发病数   keyList 按大到小的顺序寸的key
     *  返回  生成图片名称
     */
    public static void getNumBarChart(Map<String, List<Integer>> map, List<String> keyList, String path) throws IOException {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //统计图名称
        String picTitle = "xxxxxxx";

        Iterator<String> it = keyList.iterator();
        while(it.hasNext()){
            String key = it.next();
            List<Integer> vList = map.get(key);
            dataset.addValue(vList.get(0), "本月", key);
            dataset.addValue(vList.get(1), "上月", key);
        }

        JFreeChart chart = ChartFactory.createBarChart(picTitle, // 标题
                "", // X轴
                "", // Y轴
                dataset, // dataset
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例
                true, // 是否显示工具提示
                true); // 是否生成URL

        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        CategoryAxis domainAxis = categoryplot.getDomainAxis();
        TextTitle textTitle = chart.getTitle();

//		domainAxis.setLowerMargin(0.05);  //与左边的空区
//		domainAxis.setUpperMargin(0.05);  //右边的空区
//		domainAxis.setCategoryMargin(0.04); //不同类别间的间距

        // 柱图的呈现器
        BarRenderer renderer = new BarRenderer();
        //        renderer.setIncludeBaseInRange(true);     // 显示每个柱的数值，并修改该数值的字体属性
        renderer
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseOutlinePaint(Color.BLACK); // 设置柱子边框颜色
        renderer.setDrawBarOutline(true); // 设置柱子边框可见
        renderer.setMaximumBarWidth(0.05);//设置柱子最大宽度
        //柱子最大宽度设置，会使下面设置柱子间距离方法失效
        renderer.setItemMargin(0);		  // 设置同key  三时期的柱子间距离

        categoryplot.setRenderer(renderer);

        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

        ChartFrame frame = new ChartFrame(picTitle, chart);
        frame.pack();


        ChartUtilities.saveChartAsJPEG(new File(path), chart, 640, 480);

    }


    /**
     * 提供静态方法：获取报表图形2：柱状图
     *
     * @param title  标题
     * @param datas  数据
     * @param type   分类（第一季，第二季.....）
     * @param danwei 柱状图的数量单位
     * @param font   字体
     */
    public static String createPort(String title, Map<String, Map<String, Double>> datas, String type, String danwei, Font font) {
        try {
            //种类数据集
            DefaultCategoryDataset ds = new DefaultCategoryDataset();

            //获取迭代器：
            Set<Map.Entry<String, Map<String, Double>>> set1 = datas.entrySet();    //总数据
            Iterator iterator1 = (Iterator) set1.iterator();                        //第一次迭代
            Iterator iterator2 = null;
            HashMap<String, Double> map = null;
            Set<Map.Entry<String, Double>> set2 = null;
            Map.Entry entry1 = null;
            Map.Entry entry2 = null;

            while (iterator1.hasNext()) {
                entry1 = (Map.Entry) iterator1.next();                    //遍历分类

                map = (HashMap<String, Double>) entry1.getValue();//得到每次分类的详细信息
                set2 = map.entrySet();                               //获取键值对集合
                iterator2 = set2.iterator();                        //再次迭代遍历
                while (iterator2.hasNext()) {
                    entry2 = (Map.Entry) iterator2.next();
                    ds.setValue(Double.parseDouble(entry2.getValue().toString()),//每次统计数量
                            entry2.getKey().toString(),                         //名称
                            entry1.getKey().toString());                        //分类
                    System.out.println("当前：--- " + entry2.getKey().toString() + "--"
                            + entry2.getValue().toString() + "--"
                            + entry1.getKey().toString());
                }
                System.out.println("-------------------------------------");
            }

            //创建柱状图,柱状图分水平显示和垂直显示两种//
            JFreeChart chart = ChartFactory.createBarChart(title, type, danwei, ds, PlotOrientation.VERTICAL, true, true, true);
            //设置整个图片的标题字体
            chart.getTitle().setFont(font);
            //设置提示条字体
            font = new Font("宋体", Font.BOLD, 15);
            chart.getLegend().setItemFont(font);

            //得到绘图区
            CategoryPlot plot = chart.getCategoryPlot();//
            //得到绘图区的域轴(横轴),设置标签的字体
            plot.getDomainAxis().setLabelFont(font);
            Color c = new Color(36, 63, 110);
            plot.getRenderer().setSeriesPaint(0, c);//设置第一个柱子的颜色
            plot.getRenderer().setSeriesPaint(1, new Color(0, 128, 11));

            //设置横轴标签项字体
            plot.getDomainAxis().setTickLabelFont(font);

            //设置范围轴(纵轴)字体
            plot.getRangeAxis().setLabelFont(font);
            //存储成图片

            //设置chart的背景图片
//            chart.setBackgroundImage(ImageIO.read(new File("f:/test/1.jpg")));
//
//            plot.setBackgroundImage(ImageIO.read(new File("f:/test/2.jpg")));

            plot.setForegroundAlpha(1.0f);
//            UUID uuid = UUID.randomUUID();
            ChartUtilities.saveChartAsJPEG(new File("D:/test.png"), chart, 600, 400);
            String s = "D:/test.png";
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createImage() {
        Map<String, Map<String, Double>> datas = new HashMap<String, Map<String, Double>>();
        Map<String, Double> map1 = new HashMap<String, Double>();
        Map<String, Double> map2 = new HashMap<String, Double>();
        Map<String, Double> map3 = new HashMap<String, Double>();
        Map<String, Double> map4 = new HashMap<String, Double>();
        //设置第一期的投票信息
        map1.put("预算", (double) 600);
        map1.put("实际开支", (double) 700);


        //设置第二期的投票信息
        map2.put("预算", (double) 1300);
        map2.put("实际开支", (double) 900);


        //设置第三期的投票信息
        map3.put("预算", (double) 2000);
        map3.put("实际开支", (double) 1700);


        //设置第四期的投票信息
        map4.put("预算", (double) 3000);
        map4.put("实际开支", (double) 2500);


        //压入数据
        datas.put("销售部", map1);
        datas.put("运营部", map2);
        datas.put("开发部", map3);
        datas.put("财务部", map4);

        Font font = new Font("宋体", Font.BOLD, 20);
        createPort("各部门开支情况", datas, "部门", "单位（元）", font);
    }
}
