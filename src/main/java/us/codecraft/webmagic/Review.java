/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic;

/**
 *
 * @author yuezhao
 */
public class Review {
    
        private String id;
        private String date;
        private String ratingValue;
        private String text;
        private String useful;
        private String funny;
        private String cool;
        private Business biz;

        public Review(){}

        public Review(Page page, String id, Business biz){
            setId(id);
            setDate(page, id);
            setRatingValue(page, id);
            setText(page, id);
            setUseful(page, id);
            setFunny(page, id);
            setCool(page, id);
            setBiz(biz);
        }

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }

        public void setDate(Page page, String id){
            this.date = page.getHtml().xpath("div[@data-review-id='"+id+"']").xpath("span[@class='rating-qualifier']/text()").toString().trim();
        }
        public String getDate(){
            return this.date;
        }

        public void setRatingValue(Page page, String id){
            String str = page.getHtml().xpath("div[@data-review-id='"+id+"']").xpath("meta[@itemprop='ratingValue']").toString();
            String[] tmps = str.split("content=\"");
            this.ratingValue = tmps[1].substring(0, 3);
        }
        public String getRatingValue(){
            return this.ratingValue;
        }

        public void setText(Page page, String id){
            this.text = page.getHtml().xpath("div[@data-review-id='"+id+"']").xpath("p[@itemprop='description']/text()").toString();
            this.text = this.text.replace("\\", "").replace("\"", "\\\"");
        }
        public String getText(){
            return this.text;
        }
        
        public void setUseful(Page page, String id){
            this.useful = page.getHtml().xpath("div[@data-review-id='"+id+"']").xpath("div[@class='rateReview voting-feedback']")
                    .xpath("span[@class='i-wrap ig-wrap-common i-ufc-useful-common-wrap button-content']")
                    .xpath("span[@class='count']/text()").toString();
            if(this.useful.equals("")){
                this.useful = "0";
            }
        }
        public String getUseful(){
            return this.useful;
        }
        
        public void setFunny(Page page, String id){
            this.funny = page.getHtml().xpath("div[@data-review-id='"+id+"']").xpath("div[@class='rateReview voting-feedback']")
                    .xpath("span[@class='i-wrap ig-wrap-common i-ufc-funny-common-wrap button-content']")
                    .xpath("span[@class='count']/text()").toString();
            if(this.funny.equals("")){
                this.funny = "0";
            }
        }
        public String getFunny(){
            return this.funny;
        }
        
        public void setCool(Page page, String id){
            this.cool = page.getHtml().xpath("div[@data-review-id='"+id+"']").xpath("div[@class='rateReview voting-feedback']")
                    .xpath("span[@class='i-wrap ig-wrap-common i-ufc-cool-common-wrap button-content']")
                    .xpath("span[@class='count']/text()").toString();
            if(this.cool.equals("")){
                this.cool = "0";
            }
        }
        public String getCool(){
            return this.cool;
        }
        
        public void setBiz(Business biz){
            this.biz = biz;
        }
        
        public Business getBiz(){
            return this.biz;
        }
    }
    
