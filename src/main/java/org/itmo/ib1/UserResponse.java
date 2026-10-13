package org.itmo.ib1;

import org.springframework.web.util.HtmlUtils;

public record UserResponse(Long id, String username) {
    public static UserResponse from(ApplicationUser user) {
        return new UserResponse(user.getId(), HtmlUtils.htmlEscape(user.getUsername()));
    }
}
