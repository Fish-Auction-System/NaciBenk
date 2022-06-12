package com.G01.onlineFishAuction.DTO;

public class AuctionStatus {
    private boolean isFirstSaleWait;
    private boolean isAuctionFinished;
    private boolean isStarted;
    private boolean saleClosed;
    private SaleInfo closedSale;
    private SaleInfo currentSale;
    private SaleInfo nextSale;
    private int pageCodeCst;
    private int pageCodeCH;
    private int pageCodeCM;


    public AuctionStatus(boolean isFirstSaleWait, boolean isAuctionFinished, boolean isStarted, boolean saleClosed, SaleInfo closedSale, SaleInfo currentSale, SaleInfo nextSale) {
        this.isFirstSaleWait = isFirstSaleWait;
        this.isAuctionFinished = isAuctionFinished;
        this.isStarted = isStarted;
        this.saleClosed = saleClosed;
        this.closedSale = closedSale;
        this.currentSale = currentSale;
        this.nextSale = nextSale;
        this.setPageCodeCH();
        this.setPageCodeCM();
        this.setPageCodeCst();
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

    public int getPageCodeCst() {
        return pageCodeCst;
    }

    public void setPageCodeCst() {
        if(isAuctionFinished){
            if(isStarted){
                System.out.println("unexpected status auction cannot be finished while isStarted true");
                return;
            }
            this.pageCodeCst = 6;
        }else{
            if(isStarted && !saleClosed && !isFirstSaleWait){
                if(currentSale==null){
                    System.out.println("invalid status cst current sale cannot be null under this conditions (started not finished not closed not wait)");
                    return;
                }else{
                    this.pageCodeCst = 1;
                }
            }
            else if(isStarted && isFirstSaleWait){
                this.pageCodeCst=3;
            }else if(isStarted &&   saleClosed){
                this.pageCodeCst = 2;
            }else{
                System.out.println("invalid status for cst");
                return;
            }
        }

    }

    public int getPageCodeCH() {
        return pageCodeCH;
    }

    public void setPageCodeCH() {
        if(isAuctionFinished){
            if(isStarted){
                System.out.println("unexpected status auction cannot be finished while isStarted true");
                return;
            }
            this.pageCodeCH = 6;
            return;
        }
        if(isStarted && saleClosed && isFirstSaleWait){
            if(isAuctionFinished){
                System.out.println("unexpected status auction cannot be finished while isStarted true");
                return;
            }else if(nextSale==null){
                System.out.println("unexpected status next sale cannot be null while auction is not finished");
                return;
            }else{
                this.pageCodeCH = 4;
            }
        }else if(isStarted && !saleClosed && !isFirstSaleWait){
            if(currentSale == null){
                System.out.println("current sale cannot be null while auction is tarted not finished and sale not closed");
            }
            this.pageCodeCH = 1;
        }
        // değiştirildi
        else if(isStarted && saleClosed){
            this.pageCodeCH = 4;
        }else{
            System.out.println("invalid status for ch");
        }
        // değiştirildi

    }

    public int getPageCodeCM() {
        return pageCodeCM;
    }

    public void setPageCodeCM() {
        if(isAuctionFinished){
            this.pageCodeCM = 6;
        }else{
            if(isStarted && !saleClosed && !isFirstSaleWait){
                if(currentSale==null){
                    System.out.println("invalid status cst current sale cannot be null under this conditions (started not finished not closed not wait)");
                    return;
                }else{
                    this.pageCodeCM = 1;
                }
            }
            else if(isStarted && isFirstSaleWait){
                this.pageCodeCM=3;
            }else if(isStarted &&   saleClosed){
                this.pageCodeCM = 2;
            }else{
                System.out.println("invalid status for cst");
                return;
            }
        }
    }
}
