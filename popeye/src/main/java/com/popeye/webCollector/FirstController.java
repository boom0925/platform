package com.popeye.webCollector;

public class FirstController {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		  FirstCrawler crawler=new FirstCrawler("dd", false);
//	        crawler.addSeed("http://www.zhihu.com/question/21003086");
//	        crawler.addRegex("http://www.zhihu.com/.*");
//	        crawler.start(5);
		FirstCrawler crawler = new FirstCrawler("crawl", false);
	        /*start crawl with depth of 4*/
	        crawler.start(1);
	}

}
