//package com.chenzeshenga.processor;
//
//import java.util.List;
//
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.processor.PageProcessor;
//import us.codecraft.webmagic.selector.Selectable;
//
//public class BondProcessor implements PageProcessor {
//
//	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
//
//	public static final String list = "http://quote.eastmoney.com/centerv2/zqsc/zqzs";
//
//	@Override
//	public void process(Page page) {
////		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
////		// 设置webClient的相关参数
////		webClient.getOptions().setJavaScriptEnabled(true);
////		webClient.getOptions().setCssEnabled(false);
////		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
////		// webClient.getOptions().setTimeout(50000);
////		webClient.getOptions().setThrowExceptionOnScriptError(false);
////		// 模拟浏览器打开一个目标网址
////		HtmlPage rootPage = webClient.getPage(url);
//
//		List<String> list = page.getHtml().xpath("//*[@id=\"common_table\"]/thead/tr/").all();
//
//		for (int i = 0; i < list.size(); i++) {
//			System.err.println(list.get(i));
//		}
//		// System.err.println(str);
//
//		// for (Selectable selectable : list) {
//		// List<String> alls=selectable.all();
//		// for (int i = 0; i < alls.size(); i++) {
//		// System.err.println(alls.get(i));
//		// }
//		// }
//
//	}
//
//	@Override
//	public Site getSite() {
//		return site;
//	}
//
//	public static void main(String[] args) {
//		Spider.create(new BondProcessor()).addUrl("http://quote.eastmoney.com/centerv2/zqsc/zqzs").thread(5).run();
//	}
//
//}
