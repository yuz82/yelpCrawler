/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuezhao
 */
public class Business {
    private String bid;
    private String star;
    private String price;
    private String takeReserve;
    private String takeOut;
    private String acceptCreditCard;
    private String goodFor;
    private String parking;
    private String bikeParking;
    private String goodForKids;
    private String goodForGroups;
    private String attire;
    private String ambience;
    private String noiseLevel;
    private String alcohol;
    private String outdoorSeating;
    private String wifi;
    private String hasTV;
    private String waiterService;
    private String caters;
    private final String empty = "NULL";
    
    public Business(Page page, String id){
        this.bid = id;
        setStar(page);
        setPrice(page);
        setMoreInfo(page);
    }
    
    public String getBid(){
        return this.bid;
    }
    
    public void setStar(Page page){       
//        .xpath("div[@class='content-container']").xpath("div[@class='biz-page-header-left']")
//                .xpath("div[@class='rating-info clearfix']")
        String tmp = page.getHtml().xpath("div[@class='biz-page-header-left']").xpath("div[@class='rating-very-large']").xpath("meta[@itemprop='ratingValue']").toString();
        if(tmp==null){
            this.star = empty;
        }else{
            String[] tmps = tmp.split("content=\"");
            this.star = tmps[1].substring(0, 3);
        }
    }
    
    public String getStar(){
        return this.star;
    }
    
    public void setPrice(Page page){
        String p = page.getHtml().xpath("div[@class='column column-beta sidebar']").xpath("div[@class='island summary']")
                .xpath("div[@class='iconed-list-story']").xpath("dd[@class='nowrap price-description']/text()").toString();
        if(p==null){
            this.price = empty;
        }else{
            this.price = p.trim();
        }
    }
    
    public String getPrice(){
        return this.price;
    }
    
    public void setMoreInfo(Page page){
        String info = page.getHtml().xpath("div[@class='column column-beta sidebar']").xpath("div[@class='ywidget']")
                .xpath("div[@class='short-def-list']").toString();
        this.setAcceptCreditCard(info);
        this.setAlcohol(info);
        this.setAmbience(info);
        this.setAttire(info);
        this.setBikeParking(info);
        this.setCaters(info);
        this.setGoodFor(info);
        this.setGoodForGroups(info);
        this.setGoodForKids(info);
        this.setHasTV(info);
        this.setNoiseLevel(info);
        this.setOutdoorSeating(info);
        this.setParking(info);
        this.setTakeOut(info);
        this.setTakeReserve(info);
        this.setWaiterService(info);
        this.setWifi(info);
    }
    
    public String helper(String info, String keyword){
        if(info==null || !info.contains(keyword)) { return empty; }
        String[] tmp0 = info.split(keyword);
        if(tmp0==null || tmp0.length<2) { return empty; }
        String[] tmp1 = tmp0[1].split("<dd>");
        if(tmp1==null || tmp1.length<2) { return empty; }
        String[] tmp2 = tmp1[1].split("</dd>");
        if(tmp2==null) { return empty; }
        return tmp2[0].trim();
        
    }
    
    public void setTakeReserve(String info){
        final String keyword = "Takes Reservations";
        this.takeReserve = helper(info, keyword);
    }
    
    public String getTakeReserve(){
        return this.takeReserve;
    }
    
    public void setTakeOut(String info){
        final String keyword = "Take-out";
        this.takeOut = helper(info, keyword);
    }
    
    public String getTakeOut(){
        return this.takeOut;
    }
    
    public void setAcceptCreditCard(String info){
        final String keyword = "Accepts Credit Cards";
        this.acceptCreditCard = helper(info, keyword);
    }
    
    public String getAcceptCreditCard(){
        return this.acceptCreditCard;
    }
    
    public void setGoodFor(String info){
        final String keyword = "Good For";
        this.goodFor = helper(info, keyword);
    }
    
    public String getGoodFor(){
        return this.goodFor;
    }
    
    public void setParking(String info){
        final String keyword = "Parking";
        this.parking = helper(info, keyword);
    }
    
    public String getParking(){
        return this.parking;
    }
    
    public void setBikeParking(String info){
        final String keyword = "Bike Parking";
        this.bikeParking = helper(info, keyword);
    }
    
    public String getBikeParking(){
        return this.bikeParking;
    }
    
    public void setGoodForKids(String info){
        final String keyword = "Good for Kids";
        this.goodForKids = helper(info, keyword);
    }
    
    public String getGoodForKids(){
        return this.goodForKids;
    }
    
    public void setGoodForGroups(String info){
        final String keyword = "Good for Groups";
        this.goodForGroups = helper(info, keyword);
    }
    
    public String getGoodForGroups(){
        return this.goodForGroups;
    }
    
    public void setAttire(String info){
        final String keyword = "Attire";
        this.attire = helper(info, keyword);
    }
    
    public String getAttire(){
        return this.attire;
    }
    
    public void setAmbience(String info){
        final String keyword = "Ambience";
        this.ambience = helper(info, keyword);
    }
    
    public String getAmbience(){
        return this.ambience;
    }
    
    public void setNoiseLevel(String info){
        final String keyword = "Noise Level";
        this.noiseLevel = helper(info, keyword);
    }
    
    public String getNoiseLevel(){
        return this.noiseLevel;
    }
    
    public void setAlcohol(String info){
        final String keyword = "Alcohol";
        this.alcohol = helper(info, keyword);
    }
    
    public String getAlcohol(){
        return this.alcohol;
    }
    
    public void setOutdoorSeating(String info){
        final String keyword = "Outdoor Seating";
        this.outdoorSeating = helper(info, keyword);
    }
    
    public String getOutdoorSeating(){
        return this.outdoorSeating;
    }
    
    public void setWifi(String info){
        final String keyword = "Wi-Fi";
        this.wifi = helper(info, keyword);
    }
    
    public String getWifi(){
        return this.wifi;
    }
    
    public void setHasTV(String info){
        final String keyword = "Has TV";
        this.hasTV = helper(info, keyword);
    }
    
    public String getHasTV(){
        return this.hasTV;
    }
    
    public void setWaiterService(String info){
        final String keyword = "Waiter Service";
        this.waiterService = helper(info, keyword);
    }
    
    public String getWaiterService(){
        return this.waiterService;
    }
    
    public void setCaters(String info){
        final String keyword = "Caters";
        this.caters = helper(info, keyword);
    }
    
    public String getCaters(){
        return this.caters;
    }

}
