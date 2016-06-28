package oso.com.LimitQuery.Query;

/**
 * Created by zhangrui25 on 2016/6/28.
 */
public class Rule {

    public String uri ;
    public long startTime ;
    public long lastTime ;
    public int limitQuery ;
    public int queryCount ;
    public long duration ;

    public Rule(String uri ,long duration , int limitQuery){
        this.uri = uri ;
        this.duration = duration ;
        this.limitQuery = limitQuery;
    }

    public boolean isAllow(){
        lastTime = System.currentTimeMillis();
        queryCount++ ;
        return queryCount <= limitQuery && lastTime - startTime <= duration ;
    }
}
