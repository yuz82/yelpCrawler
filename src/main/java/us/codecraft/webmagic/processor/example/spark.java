/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic.processor.example;

/**
 *
 * @author yuezhao
 */
public interface spark {
    /**
         * Spark 
         */
        /*
        SparkConf sparkConf = new SparkConf().setAppName("YelpReviewPageProcessor");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);

        JavaRDD<List<Review>> reviewRDD = ctx.textFile(args[0], 1).map(
                new Function<String, List<Review>>() {

            @Override
            public List<Review> call(String line) throws Exception {
                YelpReviewPageProcessor pp = new YelpReviewPageProcessor();

                Random rand = new Random();
                pp.getSite().setSleepTime(rand.nextInt(10000)+1000);
                pp.business_id = line;

                Spider.create(pp)
                    .addUrl("https://www.yelp.com/biz/"+pp.business_id)
                    //.addPipeline(pp.new MyFilePipeline(path))
                    .thread(10)
                    .run(); 
                return pp.reviews;
            }
        }
        );
        
        JavaRDD<String> output = reviewRDD.map(new Function<List<Review>, String>() {
            
            final String quota = "\"";
            final String spliter = ", ";
            final String end = "\n";
            @Override
            public String call(List<Review> list) throws Exception {
                StringBuilder sb = new StringBuilder();
                for(Review r : list){
                    sb.append(r.getId()).append(spliter).append(r.getBid()).append(spliter)
                            .append(r.getDate()).append(spliter).append(r.getRatingValue()).append(spliter)
                            .append(quota).append(r.getText()).append(quota).append(end);
//                    sb.append("{")
//                    .append(quota+"review_id"+quota+":"+quota+r.getId()+quota+spliter)
//                            .append(quota+"business_id"+quota+":"+quota+r.getBid()+quota+spliter)
//                            .append(quota+"date"+quota+":"+quota+r.getDate()+quota+spliter)
//                            .append(quota+"ratingValue"+quota+":"+quota+r.getRatingValue()+quota+spliter)
//                            .append(quota+"review_id"+quota+":"+quota+r.getText()+quota)
//                    .append("}");
                }
                return sb.toString();
            }
        });
        
        output.saveAsTextFile("hdfs://master:54310/user/yelpData/output");
        ctx.stop();
        */
}
