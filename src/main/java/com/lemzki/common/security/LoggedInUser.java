package com.lemzki.common.security;

@FunctionalInterface
public interface LoggedInUser {
    User get();

     LoggedInUser ANONYMOUS = ()->User.ANONYMOUS;

}
