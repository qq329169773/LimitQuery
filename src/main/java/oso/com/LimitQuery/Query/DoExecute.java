package oso.com.LimitQuery.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangrui25 on 2016/6/28.
 */
public class DoExecute {

    public static List<Rule> rules = new ArrayList<Rule>();

    public static boolean execute(DoRequest dorequest){

        Rule rule = getMatchedRule(dorequest);
        if( null != rule){
            if(!rule.isAllow()){
                System.out.println(dorequest.uri + " Limit Request..");
            }
        }
        return false ;
    }

    public static Rule getMatchedRule(DoRequest doRequest){
        for(Rule rule : rules){
            if(rule.equals(doRequest.uri)){
                return rule ;
            }
        }
        return null ;
    }

    public static void addRule(Rule rule){
        rules.add(rule);
    }
    public static void main(String[] args){

    }

}
