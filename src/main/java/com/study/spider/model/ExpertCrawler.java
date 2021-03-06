package com.study.spider.model;

import com.study.spider.pojo.Teacher;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class ExpertCrawler extends WebCrawler {

    private final static Pattern IGNORE_URL_FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    Pattern TD_FILTERS = Pattern.compile("^(<td.*?>(.*?)</td>){1,}$");

    //    Pattern A_FILTERS = Pattern.compile("^<a.*?>(.*?)<\\/a>/ig$");
    Pattern A_FILTERS = Pattern.compile("<a\\b[^>]+\\bhref=\"([^\"]*)\"[^>]*>([\\s\\S]*?)</a>");

    public static Set<String> completedSet = new ConcurrentSkipListSet<String>();
    public static List<Teacher> teacherList = new LinkedList<Teacher>();

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !IGNORE_URL_FILTERS.matcher(href).matches()
                && href.startsWith("http://www.sckjfp.com/Index/userInfoList/p/")
                && href.matches("^http://www.sckjfp.com/Index/userInfoList/p/(\\d){1,}.html$");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        //System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData
                && !completedSet.contains(url)) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            //System.out.println("Text length: " + text.length());
            //System.out.println("Html length: " + html.length());
            //System.out.println("Number of outgoing links: " + links.size());

            Document doc = Jsoup.parse(html);
            String cssQuery = ".supply_search .search_content .search_res table #tbl tr";
            Elements trList = doc.select(cssQuery);
            for (int i = 0; i < trList.size(); i++) {
                Element tr = trList.get(i);
                //System.out.println("-->" + tr.html() + "<--");
                Elements tdList = tr.children();
                Teacher teacher = new Teacher();
                for (int j = 0; j < tdList.size(); j++) {
                    Element td = tdList.get(j);
                    if (j == 0) {
                        Matcher m = A_FILTERS.matcher(td.html());
                        if (m.find()) {
                            teacher.setUrl(m.group(1));
                        }
                    }

                    if (j >= 6) {
                        continue;
                    } else {
                        try {
                            Field field = Teacher.ORDER_MAP.get(j + 2);
                            Method setMethod = Teacher.class.getMethod("set"
                                    + field.getName().substring(0, 1).toUpperCase()
                                    + field.getName().substring(1), String.class);
                            setMethod.invoke(teacher, td.text());
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
                teacherList.add(teacher);
//                System.out.println(teacher);
            }
            completedSet.add(url);
//            System.out.println(url);
//            Controller.countDownLatch.countDown();
        }
    }
}
