package com.hj.spring_jpa.jpa.cascade;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentAccountAuditAware implements AuditorAware<CommentAccount> {

    @Override
    public Optional<CommentAccount> getCurrentAuditor() {
        System.out.println("looking for current user");
        return Optional.empty();
    }
}
