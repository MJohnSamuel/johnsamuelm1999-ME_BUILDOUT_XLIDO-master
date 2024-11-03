package com.crio.xlido.services;

import java.util.Comparator;
import com.crio.xlido.entities.Question;

public class SortPopular implements Comparator<Question> {

    @Override
    public int compare(Question q1, Question q2) {
        // TODO Auto-generated method stub
        if(q1.getUpvotedusers().size()>q2.getUpvotedusers().size()){
            return -1;
        }else if(q1.getUpvotedusers().size()<q2.getUpvotedusers().size()){
            return 1;
        }else{
            if(q1.getQid()>q2.getQid()){
                return 1;
            }
            else return -1;
        }
    }
    
}
