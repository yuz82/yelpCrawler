/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic.processor.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Review;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import static us.codecraft.webmagic.processor.example.YelpReviewPageProcessor.failedIds;
import us.codecraft.webmagic.utils.FilePersistentBase;

/**
 *
 * @author yuezhao
 */
public class MyFilePipeline extends FilePersistentBase implements Pipeline {
        

        private Logger logger = LoggerFactory.getLogger(getClass());
        /**
         * create a FilePipeline with default path"/data/webmagic/"
         */
        public MyFilePipeline() {
            setPath("/data/webmagic/");
        }
        public MyFilePipeline(String path) {
            setPath(path);
        }
        
        @Override
        public void process(ResultItems resultItems, Task task) {
            String path = this.path;
            final String spliter = ", ";
            final String quota = "\"";
            try {
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
                        getFile(path), true),"UTF-8"));
                //System.out.println("start write");
                for (Map.Entry<String, Review> entry : resultItems.getAll().entrySet()) {
                    Review r = entry.getValue();
                    /**
                     * Json format
                     */
                    printWriter.print("{");
                    printWriter.print("\"review_id\": \"" + entry.getKey() +"\""+ spliter +
                            "\"date\": \"" + r.getDate() +"\""+ spliter +
                            "\"useful\": \"" + r.getUseful()+"\""+ spliter +
                            "\"funny\": \"" + r.getFunny()+"\""+ spliter +
                            "\"cool\": \"" + r.getCool()+"\""+ spliter +
                            "\"ratingValue\": \"" + r.getRatingValue() +"\""+spliter +
                            "\"business_id\": \"" + r.getBiz().getBid() + "\""+ spliter +
                            "\"star\": \"" + r.getBiz().getStar() + "\""+ spliter +
                            "\"price\": \"" + r.getBiz().getPrice() + "\""+ spliter +
                            "\"takeReserve\": \"" + r.getBiz().getTakeReserve() + "\""+ spliter +
                            "\"takeOut\": \"" + r.getBiz().getTakeOut() + "\""+ spliter +
                            "\"acceptCreditCard\": \"" + r.getBiz().getAcceptCreditCard() + "\""+ spliter +
                            "\"goodFor\": \"" + r.getBiz().getGoodFor() + "\""+ spliter +
                            "\"parking\": \"" + r.getBiz().getParking() + "\""+ spliter +
                            "\"bikeParking\": \"" + r.getBiz().getBikeParking() + "\""+ spliter +
                            "\"goodForKids\": \"" + r.getBiz().getGoodForKids() + "\""+ spliter +
                            "\"goodForGroups\": \"" + r.getBiz().getGoodForGroups() + "\""+ spliter +
                            "\"attire\": \"" + r.getBiz().getAttire() + "\""+ spliter +
                            "\"ambience\": \"" + r.getBiz().getAmbience() + "\""+ spliter +
                            "\"noiseLevel\": \"" + r.getBiz().getNoiseLevel() + "\""+ spliter +
                            "\"alcohol\": \"" + r.getBiz().getAlcohol() + "\""+ spliter +
                            "\"outdoorSeating\": \"" + r.getBiz().getOutdoorSeating() + "\""+ spliter +
                            "\"wifi\": \"" + r.getBiz().getWifi() + "\""+ spliter +
                            "\"hasTV\": \"" + r.getBiz().getHasTV() + "\""+ spliter +
                            "\"waiterService\": \"" + r.getBiz().getWaiterService() + "\""+ spliter +
                            "\"caters\": \"" + r.getBiz().getCaters() + "\""+ spliter 
                    );
                    if(failedIds.contains(r.getBiz().getBid())){
                        printWriter.print("\"failed\": \"" + "1" +"\""+spliter);
                    }else{
                        printWriter.print("\"failed\": \"" + "0" +"\""+spliter);
                    }
                    printWriter.println("\"text\": \"" + r.getText()+"\""+ "}" + spliter);

                }
                //System.out.println("end write");
                printWriter.close();
            } catch (IOException e) {
                logger.warn("write file error", e);
            }
        }

    
    }