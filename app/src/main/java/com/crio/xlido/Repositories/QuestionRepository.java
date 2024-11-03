package com.crio.xlido.Repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.User;
import com.crio.xlido.entities.Question.Reply;
import java.util.Optional;

public class QuestionRepository {
    private final Map<Long,Question> storage = new HashMap<>();
    private AtomicLong id = new AtomicLong(0);


    public Question save(Question question){
        LocalDateTime localDateTime = LocalDateTime.now();
        Question ques = new Question(id.incrementAndGet(), question,localDateTime);
        storage.putIfAbsent(ques.getQid(), ques);
        return ques;
    }

    public void delete(Question entity){
        storage.remove(entity.getQid());
    }

    public Optional<Question> getbyQId(Long id){
        return Optional.ofNullable(storage.get(id));
    }

    public void upvoteQuestion(Question question, User user) {
        storage.get(question.getQid()).getUpvotedusers().add(user.getId());
    }

    public void ReplyQuestion(Question question, User user, String content) {
        Reply reply = new Reply(user.getId(), content);
        storage.get(question.getQid()).addReplyList(reply);
    }

    public List<Question> questionsByEventId(Long eventId){
        List<Question> questions = new ArrayList<>();
        for(Map.Entry<Long,Question> entry: storage.entrySet()){
            if(entry.getValue().getEventId()==eventId){
                questions.add(entry.getValue());
            }
        }
        return questions;
    }
}
