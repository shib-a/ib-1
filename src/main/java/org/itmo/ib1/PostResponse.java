package org.itmo.ib1;

import org.springframework.web.util.HtmlUtils;

import java.time.Instant;

public record PostResponse(Long id, String author, String content, Instant createdAt) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), HtmlUtils.htmlEscape(post.getAuthorName()),
                HtmlUtils.htmlEscape(post.getContent()), post.getCreatedAt());
    }
}
