package uk.co.jakebreen.sendgridandroid;

import android.support.annotation.NonNull;
import android.util.Base64;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendGridMail {

    static final String EMPTY = "";
    static final String TYPE_PLAIN = "text/plain";
    static final String TYPE_HTML = "text/html";

    private final Map<String, String> to = new HashMap<>();
    private String subject;
    private final Map<String, String> content = new HashMap<>();
    private final Map<String, String> from = new HashMap<>();
    private final Map<String, String> replyTo = new HashMap<>();
    private String templateId;
    private int sendAt;
    private List<Attachment> attachments = new ArrayList<>();

    public SendGridMail() { }

    public void addTo(String email, @Nullable String name) {
        if (name == null) name = EMPTY;
        to.put(email, name);
    }

    public void setFrom(String email, @Nullable String name) {
        if (name == null) name = EMPTY;
        from.put(email, name);
    }

    public void setReplyTo(String email, @Nullable String name) {
        if (name == null) name = EMPTY;
        from.put(email, name);
    }

    public void setSubject(@NonNull String subject) {
        if (subject.length() == 0) subject = " ";
        this.subject = subject;
    }

    public void setTemplateId(@NonNull String templateId) {
        this.templateId = templateId;
    }

    public void setContent(@NonNull String body) {
        if (body.length() == 0) body = " ";
        content.put(TYPE_PLAIN, body);
    }

    public void setHtmlContent(@NonNull String body) {
        if (body.length() == 0) body = " ";
        content.put(TYPE_HTML, body);
    }

    public void setSendAt(int sendAt) {
        this.sendAt = sendAt;
    }

    Map<String, String> getTo() {
        return to;
    }

    String getSubject() {
        return subject;
    }

    Map<String, String> getContent() {
        return content;
    }

    Map<String, String> getFrom() {
        return from;
    }

    Map<String, String> getReplyTo() {
        return replyTo;
    }

    String getTemplateId() {
        return templateId;
    }

    int getSendAt() {
        return sendAt;
    }

    void addAttachment(String content, String filename) {
        attachments.add(new Attachment(content, filename));
    }

    List<Attachment> getAttachments() {
        return attachments;
    }

    class Attachment {

        private final String content;
        private final String filename;

        Attachment(String content, String filename) {
            this.content = Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
            this.filename = filename;
        }

        String getContent() {
            return content;
        }

        String getFilename() {
            return filename;
        }

    }
}
