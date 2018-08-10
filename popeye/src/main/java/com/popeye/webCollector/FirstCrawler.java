package com.popeye.webCollector;

import java.util.regex.Pattern;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
public class FirstCrawler extends BreadthCrawler{

	public FirstCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		  this.addSeed("http://www.zhihu.com/question/21003086");
	        for(int pageIndex = 2; pageIndex <= 5; pageIndex++) {
	            String seedUrl = String.format("http://www.zhihu.com/question/%d", pageIndex);
	            this.addSeed(seedUrl);
	        }

	        /*fetch url like "https://blog.github.com/2018-07-13-graphql-for-octokit/" */
	        this.addRegex("http://www.zhihu.com/question/[0-9]+");
	        /*do not fetch jpg|png|gif*/
	        //this.addRegex("-.*\\.(jpg|png|gif).*");
	        /*do not fetch url contains #*/
	        //this.addRegex("-.*#.*");

	        setThreads(50);
	        getConf().setTopN(100);

	        //enable resumable mode
	        //setResumable(true);
	}
//
//	@Override
//	public void visit(Page page,CrawlDatums next) {
//		  String question_regex="^http://www.zhihu.com/question/[0-9]+";
//	        if(Pattern.matches(question_regex, page.url())){
//	            System.out.println("正在抽取"+page.url());
//	            /*抽取标题*/
//	            String title=page.doc().title();
//	            System.out.println(title);
//	            /*抽取提问内容*/
//	            String question=page.doc().select("div[id=zh-question-detail]").text();
//	            System.out.println(question);
//
//	        }
//		
//	}

	   @Override
	    public void visit(Page page, CrawlDatums next) {
	        String url = page.url();
	        /*if page is news page*/
	        if (page.matchUrl("http://www.zhihu.com/question/[0-9]+")) {

	        	  System.out.println("正在抽取"+page.url());
		            /*抽取标题*/
		            String title=page.doc().title();
		            System.out.println(title);
		            /*抽取提问内容*/
		            String question=page.doc().select("div[id=zh-question-detail]").text();
		            System.out.println(question);
	        }
	    }
}
