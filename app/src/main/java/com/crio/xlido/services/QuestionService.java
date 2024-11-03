package com.crio.xlido.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.crio.xlido.Repositories.EventRepository;

import com.crio.xlido.Repositories.QuestionRepository;
import com.crio.xlido.Repositories.UserRepository;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.User;
import com.crio.xlido.entities.Question.Reply;

public class QuestionService {
    private EventRepository eventRepository;
    private UserRepository userRepository;
    private QuestionRepository questionRepository;


    public QuestionService(EventRepository eventRepository,UserRepository userRepository 
    ,QuestionRepository questionRepository){
        this.eventRepository = eventRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public String createQuestion(List<String> tokens){
        Optional<Event> event = eventRepository.findById(Long.valueOf(tokens.get(3)));
        Optional<User> user = userRepository.findById(Long.valueOf(tokens.get(2)));

        if(!event.isPresent()){
            return "ERROR: Event with an id "+tokens.get(3) +" does not exist";
        }else if(!user.isPresent()){
            return "ERROR: User with an id "+tokens.get(2) +" does not exist";
        }else {
            Question ques = questionRepository.save(new Question(Long.valueOf(tokens.get(2)),Long.valueOf(tokens.get(3)),tokens.get(1)));
            return "Question ID: "+ ques.getQid();
        }
    }

    public String deleteQuestion(List<String> tokens){
        Optional<User> user = userRepository.findById(Long.valueOf(tokens.get(2)));
        Optional<Question> question = questionRepository.getbyQId(Long.valueOf(tokens.get(1)));
        if(!user.isPresent()){
            return "ERROR: User with an id " + tokens.get(2) + " does not exist";
        }if(!question.isPresent()){
            return "ERROR: Question with an id " + tokens.get(1) + " does not exist";
        }if(!question.get().getUserId().equals(Long.valueOf(tokens.get(2)))){
            return "ERROR: User with an id " +  user.get().getId() + " is not an author of question with an id " + question.get().getQid();
        }

        else{
            questionRepository.delete(question.get());
            return "QUESTION_DELETED " + question.get().getQid();
        }      
    }

    public String upvoteQuestion(List<String> tokens){
        Optional<Question> ques = questionRepository.getbyQId(Long.valueOf(tokens.get(1)));
        Optional<User> user = userRepository.findById(Long.valueOf(tokens.get(2)));
        if(!user.isPresent()){
            return "ERROR: User with an id " + tokens.get(2) + " does not exist";
        }if(!ques.isPresent()){
            return "ERROR: Question with an id " + tokens.get(1) + " does not exist";
        }if(ques.get().getUpvotedusers().contains(Long.valueOf(tokens.get(2)))){
            return "ERROR: User with an id " + tokens.get(2) + " has already upvoted a question with an id "+ tokens.get(1);
        }

        questionRepository.upvoteQuestion(ques.get(),user.get());
        return "QUESTION_UPVOTED " + tokens.get(1);
    }

    public String replyQuestion(List<String> tokens){
        Optional<Question> ques = questionRepository.getbyQId(Long.valueOf(tokens.get(2)));
        Optional<User> user = userRepository.findById(Long.valueOf(tokens.get(3)));

        if(!ques.isPresent()){
            return "ERROR: Question with an id " + tokens.get(2) + " does not exist";
        }if(!user.isPresent()){
            return "ERROR: User with an id " + tokens.get(3) + " does not exist";
        }
        questionRepository.ReplyQuestion(ques.get(),user.get(),tokens.get(1));
        return "REPLY_ADDED";
    }

    public String getRepliesQuestion(Question question){
        StringBuilder output = new StringBuilder();
        for(Reply reply: question.getReplies()){
            output.append("  - User "+ reply.getUserId() + ": "+ reply.getReply()).append("\n");
        }
        return output.toString();
    }

    public String listAllQuestion(List<String> tokens){
        Optional<Event> event = eventRepository.findById(Long.valueOf(tokens.get(1)));
        if(!event.isPresent()){
            return "ERROR: Event with an id " + tokens.get(1) + " does not exist";
        }
        List<Question> questions = questionRepository.questionsByEventId(Long.valueOf(tokens.get(1)));
        // questions.sort();
        if(tokens.get(2).equalsIgnoreCase("popular")){
            Collections.sort(questions, new SortPopular());
        }else{
            Collections.sort(questions, new SortRecent());
        }
        StringBuilder output = new StringBuilder();
        boolean first = true;
        for(Question question: questions){
            if(!first){
                output.append("\n");
            }
            first = false;
            String replString = getRepliesQuestion(question);
            output.append("Question ID: "+ question.getQid()).append("\n").
            append("Content: " + question.getQuestionString()).append("\n").append("Votes: " + question
            .getUpvotedusers().size()).append("\n").append("Replies:").append("\n").append(replString);
        }
        return output.toString();
    }
}
