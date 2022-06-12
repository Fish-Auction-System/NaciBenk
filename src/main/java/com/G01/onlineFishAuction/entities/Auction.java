package com.G01.onlineFishAuction.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auction")
public class Auction implements Comparable {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="date")
    private float date;
    @Column(name="quota")
    private int quota;
    @Column(name="is_finished")
    private int is_finished;


    public Auction(String name, float date, int id, int quota, int is_finished) {
        this.name = name;
        this.date = date;
        this.id = id;
        this.quota = quota;
        this.is_finished = is_finished;
    }
    public Auction(){

    }

    public int getIs_finished(){
        return is_finished;
    }

    public void setIs_finished(){
        is_finished=1;
    }

    public String getName() {
        return name;
    }

    public float getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getQuota() {
        return quota;
    }

    @Override
    public int compareTo(Object o) {
        if(o==null){
            return 1;
        }else{
            if(o.getClass()==this.getClass()){
                Auction other = (Auction)o;
                float otherDate = other.getDate();
                float thisDate = this.getDate();
                if(thisDate>otherDate){
                    return 1;
                }else if(thisDate==otherDate){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return 1;
            }
        }
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", quota=" + quota +
                ", is_finished=" + is_finished +
                '}';
    }
}
