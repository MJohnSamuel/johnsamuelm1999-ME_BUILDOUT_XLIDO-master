package com.crio.xlido.services;

import java.util.Comparator;
import com.crio.xlido.entities.Question;

public class SortRecent implements Comparator<Question> {

    @Override
    public int compare(Question q1, Question q2) {
        // TODO Auto-generated method stub
        if(q1.getCreatedAt().isBefore(q2.getCreatedAt())){
            return 1;
        }else if(q1.getCreatedAt().isAfter(q2.getCreatedAt())){
            return -1;
        }
        return 0;
    }
    
}
