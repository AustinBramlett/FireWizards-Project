package com.model;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.problems.Comment;

public class CommentTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // sees if a comment can be made with the values needed
    @Test
    public void testCreateComment() {
        UUID user = UUID.randomUUID();
        UUID problem = UUID.randomUUID();

        Comment comment = new Comment(user, problem, "Hello");

        assertEquals("Hello", comment.getCommentText());
        assertEquals(user, comment.getSender());
        assertEquals(problem, comment.getProblemID());
        assertEquals(0, comment.getScore());
    }

    // checks i f adding a reply works
    @Test
    public void testAddReply() {
        Comment parent = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Parent");
        Comment reply = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Reply");

        parent.addReply(reply);

        assertEquals(1, parent.getReplies().size());
    }

    // cant add null reply
    @Test
    public void testAddNullReply() {
        Comment parent = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Parent");

        parent.addReply(null);

        assertEquals(0, parent.getReplies().size());
    }

    // can edit a comment and change the text
    @Test
    public void testEditComment() {
        Comment comment = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Old");

        comment.editComment("New");

        assertEquals("New", comment.getCommentText());
    }

    // null edits should not change the text
    @Test
    public void testEditCommentEmpty() {
        Comment comment = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Old");

        comment.editComment("");

        assertEquals("Old", comment.getCommentText());
    }

    // checks if deleting comment works tells you its delted 
    @Test
    public void testDeleteComment() {
        Comment comment = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Hello");
        Comment reply = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Reply");

        comment.addReply(reply);
        comment.deleteComment();

        assertEquals("[deleted]", comment.getCommentText());
        assertEquals(0, comment.getReplies().size());
    }

    // can reply to a reply 
    @Test
    public void testNestedReplies() {
        Comment parent = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Parent");
        Comment reply1 = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Reply1");
        Comment reply2 = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Reply2");

        parent.addReply(reply1);
        reply1.addReply(reply2);

        assertEquals(1, parent.getReplies().size());
        assertEquals(1, parent.getReplies().get(0).getReplies().size());
    }
}