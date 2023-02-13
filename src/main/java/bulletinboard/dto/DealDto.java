package bulletinboard.dto;

import bulletinboard.model.DealStatus;

public class DealDto {
    private String userOwner;
    private String userBuyer;
    private float dealPrice;
    private float minPrice;
    private String dateTime;
    private DealStatus dealStatus;

    public String getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(String userOwner) {
        this.userOwner = userOwner;
    }

    public String getUserBuyer() {
        return userBuyer;
    }

    public void setUserBuyer(String userBuyer) {
        this.userBuyer = userBuyer;
    }

    public float getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(float dealPrice) {
        this.dealPrice = dealPrice;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }
}
