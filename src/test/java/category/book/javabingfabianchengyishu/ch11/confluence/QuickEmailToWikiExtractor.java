package category.book.javabingfabianchengyishu.ch11.confluence;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/22 10:19
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class QuickEmailToWikiExtractor /*extends AbstractExtractor */{

//    private ThreadPoolExecutor threadsPool;
//    private ArticleBlockingQueue<ExchangeEmailShallowDTO> emailQueue;
//
//    public QuickEmailToWikiExtractor() {
//        emailQueue = new ArticleBlockingQueue<ExchangeEmailShallowDTO>();
//        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
//        threadsPool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 10l,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(2000));
//    }
//
//    public void extract() {
//        logger.debug("开始" + getExtractorName() + "。。");
//        long start = System.currentTimeMillis();
//        // 抽取所有邮件放到队列里
//        new ExtractEmailTask().start();
//        // 把队列里的文章插入到Wiki
//        insertToWiki();
//        long end = System.currentTimeMillis();
//        double cost = (end - start) / 1000;
//        logger.debug("完成" + getExtractorName() + ",花费时间：" + cost + "秒");
//    }
//
//    /**
//     * 把队列里的文章插入到Wiki
//     */
//    private void insertToWiki() {
//// 登录Wiki,每间隔一段时间需要登录一次
//        confluenceService.login(RuleFactory.USER_NAME, RuleFactory.PASSWORD);
//        while (true) {
//// 2秒内取不到就退出
//            ExchangeEmailShallowDTO email = emailQueue.poll(2, TimeUnit.SECONDS);
//            if (email == null) {
//                break;
//            }
//            threadsPool.submit(new insertToWikiTask(email));
//        }
//    }
//
//    protected List<Article> extractEmail() {
//        List<ExchangeEmailShallowDTO> allEmails = getEmailService().queryAllEmails();
//        if (allEmails == null) {
//            return null;
//        }
//        for (ExchangeEmailShallowDTO exchangeEmailShallowDTO : allEmails) {
//            emailQueue.offer(exchangeEmailShallowDTO);
//        }
//        return null;
//    }
//
//    /**
//     * 抽取邮件任务
//     * *
//     *
//     * @author tengfei.fangtf
//     */
//    public class ExtractEmailTask extends Thread {
//        public void run() {
//            extractEmail();
//        }
//    }
}
