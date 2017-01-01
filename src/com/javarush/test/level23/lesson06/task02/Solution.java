package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {
    public final static class Constants{
        static final String NOT_ACCESS = "Server is not accessible for now.";
        static final String NOT_AUTH = "User is not authorized.";
        static final String USER_BANNED = "User is banned.";
        static final String ACC_DENIED = "Access is denied.";
    }

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.NOT_ACCESS);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.NOT_ACCESS, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.NOT_AUTH);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.NOT_AUTH, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.USER_BANNED);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.USER_BANNED, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.ACC_DENIED);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.ACC_DENIED, cause);
        }
    }
}
