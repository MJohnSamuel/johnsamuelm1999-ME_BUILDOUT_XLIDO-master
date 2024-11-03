package com.crio.xlido.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Question {
    private final Long userId;
    private final Long eventId;
    private final String questionString;
    private final Long Qid;
    private final Set<Long> Upvotedusers ;
    private final LocalDateTime createdAt;
    private final List<Reply> replies;


    public static class Reply{
        private final Long userId;
        private final String reply;

        public Reply(Long userId, String reply){
            this.userId = userId;
            this.reply = reply;
        }

        public Long getUserId() {
            return userId;
        }

        public String getReply() {
            return reply;
        }
    }

    public Question(Long userId, Long eventId, String questionString) {
        this.Qid=null;
        this.createdAt= null;
        this.replies = new ArrayList<>();
        this.Upvotedusers = new HashSet<>();
        this.userId = userId;
        this.eventId = eventId;
        this.questionString = questionString;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<Long> getUpvotedusers() {
        return Upvotedusers;
    }

    public void addReplyList(Reply reply){
        this.replies.add(reply);
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getQid() {
        return Qid;
    }

    public String getQuestionString() {
        return questionString;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public Question(Long Qid, Question question,LocalDateTime createdAt){
        this.Qid=Qid;
        this.createdAt = createdAt;
        this.replies = question.replies;
        this.Upvotedusers = question.Upvotedusers;
        this.userId = question.userId;
        this.eventId = question.eventId;
        this.questionString = question.questionString;
    }

    
}
