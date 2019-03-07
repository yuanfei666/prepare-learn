package top.forethought.practice;

import javax.management.relation.RelationSupport;
import java.util.ArrayList;
import java.util.List;

public class Added {
    public static void main(String [] args){
  System.out.println(addBinary("11","1"));
    }
    public static String addBinary(String a, String b) {
        int carray=0;
        List<String> result=new ArrayList<>();

        char [] aChars=a.toCharArray();
        char [] bChars=b.toCharArray();
        int i=aChars.length-1;
        int j=bChars.length-1;
        boolean added=true;
        while(added){
            added=false;
            if(i>=0){
                carray+= aChars[i--]-'0';
                added=true;

            }
            if(i>=0){
                carray+= aChars[j--]-'0';
                added=true;
            }
            if(added){
                result.add((carray%2)+"");
                carray= carray/2;
            }

        }
        if(carray!=0){
            result.add(carray+"");
        }


        StringBuffer sb=new StringBuffer();
        for( j=result.size()-1;j>=0;j--){
            sb.append(result.get(j));
        }


        return sb.toString();
    }

    }

