package com.G01.onlineFishAuction.DTO;

public class AuctionStatus {
    private boolean isFirstSaleWait;
    private boolean isAuctionFinished;
    private boolean isStarted;
    private boolean saleClosed;
    private SaleInfo closedSale;
    private SaleInfo currentSale;
    private SaleInfo nextSale;

    public AuctionStatus(boolean isFirstSaleWait, boolean isAuctionFinished, boolean isStarted, boolean saleClosed, SaleInfo closedSale, SaleInfo currentSale, SaleInfo nextSale) {
        this.isFirstSaleWait = isFirstSaleWait;
        this.isAuctionFinished = isAuctionFinished;
        this.isStarted = isStarted;
        this.saleClosed = saleClosed;
        this.closedSale = closedSale;
        this.currentSale = currentSale;
        this.nextSale = nextSale;
    }

    public AuctionStatus() {
    }

    public boolean getAuctionFinished() {
        return isAuctionFinished;
    }

    public boolean isFirstSaleWait() {
        return isFirstSaleWait;
    }

    public void setFirstSaleWait(boolean firstSaleWait) {
        isFirstSaleWait = firstSaleWait;
    }

    public boolean isAuctionFinished() {
        return isAuctionFinished;
    }

    public void setAuctionFinished(boolean isAuctionFinished) {
        this.isAuctionFinished = isAuctionFinished;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isSaleClosed() {
        return saleClosed;
    }

    public void setSaleClosed(boolean saleClosed) {
        this.saleClosed = saleClosed;
    }

    public SaleInfo getClosedSale() {
        return closedSale;
    }

    public void setClosedSale(SaleInfo closedSale) {
        this.closedSale = closedSale;
    }

    public SaleInfo getCurrentSale() {
        return currentSale;
    }

    public void setCurrentSale(SaleInfo currentSale) {
        this.currentSale = currentSale;
    }

    public SaleInfo getNextSale() {
        return nextSale;
    }

    public void setNextSale(SaleInfo nextSale) {
        this.nextSale = nextSale;
    }
}
