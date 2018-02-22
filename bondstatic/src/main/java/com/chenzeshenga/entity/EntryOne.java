package com.chenzeshenga.entity;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl = "http://quote.eastmoney.com/centerv2/zqsc/zqzs", pipelines = { "consolePipeline",
		"allSortPipeline" }, downloader = "htmlUnitDownloder")
public class EntryOne implements HtmlBean {

	private static final long serialVersionUID = 3657244126297944349L;

	@Request
	private HttpRequest request;

	// @HtmlField(cssPath = "#common_table > thead > tr > th:nth-child(2) > a")
	@HtmlField(cssPath = "body")
	private String str1;

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	// public static void main(String[] args) {
	// GeccoEngine.create().classpath("com.chenzeshenga.entity").start("http://quote.eastmoney.com/centerv2/zqsc/zqzs")
	// .interval(2000).start();
	// }

	public static void main(String[] args) throws Exception {
		HttpRequest request = new HttpGetRequest("http://quote.eastmoney.com/centerv2/zqsc/zqzs");
		request.setCharset("GBK");
		GeccoEngine.create().proxy(false).classpath("com.chenzeshenga.entity")
				// 开始抓取的页面地址
				.start(request)
				// 开启几个爬虫线程
				.thread(1).run();
	}

}
