package com.dh.xtremeRental.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IEmailService {

    void sendEmail(String toUser, String subject, String message);

}
