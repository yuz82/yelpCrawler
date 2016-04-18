/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic.processor.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Review;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.MyFilePipeline;

import us.codecraft.webmagic.Business;

/**
 *
 * @author yuezhao
 */
public class YelpReviewPageProcessor implements PageProcessor{
        
 
    private Site site = Site.me().setCycleRetryTimes(5);
	public List<Review> reviews = new ArrayList();
	public Business business;
	public String business_id;
	public static HashSet<String> failedIds = new HashSet();
	public static HashSet<String> allIds = new HashSet();

	// Customized Parameters
	public static int startNum = 0;
	public static int endNum = 2000;
	public static int timespan = 1000; // 1s

	@Override
	public void process(Page page) {
		/**
		 * Get urls for next page
		 */
		String str = page.getHtml().xpath("link[@rel='next']").toString();
		if (str != null) {
			String[] tmp1 = str.split("href=\"");
			String[] tmp2 = tmp1[1].split("\"");
			String next = tmp2[0]; // tmp1[1].substring(0, tmp1[1].length() -
									// 2);
			page.addTargetRequest(next);
		}
		// get info of a biz //"div[@class='']"
		business = new Business(page, business_id);

		/**
		 * Get Review of a single page
		 */
		// get review list
		List<String> sections = page.getHtml()
				.xpath("div[@class='review-list']")
				.xpath("div[@class='review review--with-sidebar']").all();
		// parse review-id and use it to get Review
		for (int i = 0; i < sections.size(); i++) {
			String[] tmps = sections.get(i).split("data-review-id=\"");
			String id = tmps[1].substring(0, 22);
			Review review = new Review(page, id, business);
			reviews.add(review);
			page.putField(id, review);
		}
                
                //page.getRequest()

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {          
            
		/**
		 * local
		 */
		String[] userAgents = {
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)",
				"Windows NT 6.0",
				"Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070803 Firefox/1.5.0.12",
				"Opera/8.0 (Macintosh; PPC Mac OS X; U; en)",
				"Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Version/3.1 Safari/525.13",
				"Opera/9.80 (Android 4.0.3; Linux; Opera Mobi/ADR-1210241554) Presto/2.11.355 Version/12.10",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3",
				"IUC(U;iOS 5.1.1;Zh-cn;320*480;)/UCWEB8.9.1.271/42/800" };
		// List<String[]> httpProxies = new ArrayList<String[]>();
		// String[] s1 = {"173.63.3.206", "3128", "http"}, s2 =
		// {"104.236.25.172", "80", "http"};
		// httpProxies.add(s1); httpProxies.add(s2);

		// local
		// write to json file
//		String path = "/root/WebMagic/data/allBiz" + startNum + "-" + endNum + ".json";
//		String path = "/home/ec2-user/yelp/badBiz" + startNum + "-" + endNum + ".json";
		 String path = "/Users/yuezhao/Desktop/spider/test.json";  // test

//		String inputDir = "/root/WebMagic/data";  // for vm
//		String inputDir = "/home/ec2-user/yelp";  // for AWS EC2
		 String inputDir = "/Users/yuezhao/Desktop/spider/input";  // test

		String inspectionFile = "/BusinessID_FoodInspection.txt";
		String allBizFile = "/FinalAllBusinessID.txt";

		File output = new File(path);
		if (!output.exists()) {
			output.createNewFile();
		}
		try {
			/**
			 * write the header of output file
			 */
			String start = "[\n", end = "{}]";
			// Files.write(Paths.get(path), start.getBytes(),
			// StandardOpenOption.APPEND);

			/**
			 * Read failed file from csv file
			 */
//			File failedFile = new File(inputDir + inspectionFile);
//			BufferedReader reader = new BufferedReader(new FileReader(
//					failedFile));
//			String str = new String();
//			while ((str = reader.readLine()) != null) {
//				failedIds.add(str);
//			}
//			File file = new File(inputDir + allBizFile); // allBizFile
//			boolean flag = false;
			
			/**
			 * read all files from csv file
			 */
			File allFile = new File(inputDir + allBizFile);
			BufferedReader reader = new BufferedReader(new FileReader(allFile));
			String str = new String();
			while ((str = reader.readLine()) != null) {
				allIds.add(str);
			}
			File file = new File(inputDir + inspectionFile); 
			boolean flag = true;
			
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int count = 0;

//			while (count++ < startNum && (line = br.readLine()) != null) {
//				continue;
//			}

			while (count++ < endNum && (line = br.readLine()) != null) { 
																			
				if(flag){ //read all files
					if(allIds.contains(line)){
						continue;
					}
				}
				
				YelpReviewPageProcessor pp = new YelpReviewPageProcessor();

				Random rand = new Random();
				Site site = pp.getSite();
				site.setSleepTime(rand.nextInt(timespan) + 0);

				pp.business_id = line;
				System.out.println(count + " " +pp.business_id);

				/**
				 * test site
				 */
				// userAgent
				int idx = rand.nextInt(userAgents.length);
				String userAgent = (userAgents[idx]);
				site.setUserAgent(userAgent);

				// httpProxy
				// site.setHttpProxyPool(httpProxies);

				// System.out.println("header:\t"+site.getHeaders());
				// System.out.println("userAgent:\t"+site.getUserAgent());
				// System.out.println("httpProxy:\t"+site.getHttpProxy());

				/**
				 * Json format
				 */
				Spider.create(pp)
						.addUrl("https://www.yelp.com/biz/" + pp.business_id)
						.addPipeline(new MyFilePipeline(path)).thread(10).run();

			}
			/**
			 * write the end of output file
			 */
			// Files.write(Paths.get(path), end.getBytes(),
			// StandardOpenOption.APPEND);

		} catch (IOException e) {
			System.out.println("Error when create or write file");
		}

	}
}
