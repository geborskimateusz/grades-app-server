package com.helpers.api.v1.controller;

public class TestApiUrlStrings {

    public static final Long STUDENT_ID = 5L;
    public static final Long MESSAGE_ID = 5L;
    private static final String ACTIVE_USER_ID = "5";

    private static final String API_V_1_MESSAGES = "/api/v1/messages/";

    public static final String SENT_MESSAGE_TYPE = "sent";

    public static final String MESSAGE = "/message";

    public static final String RECEIVED = "received/";
    public static final String API_V_1_MESSAGES_RECEIVED_5 = API_V_1_MESSAGES + RECEIVED + ACTIVE_USER_ID;
    public static final String API_V_1_MESSAGES_RECEIVED_5_MESSAGE = API_V_1_MESSAGES + RECEIVED + MESSAGE_ID + MESSAGE;

    public static final String SENT = "sent/";
    public static final String API_V_1_MESSAGES_SENT_5 = API_V_1_MESSAGES + SENT + ACTIVE_USER_ID;
    public static final String API_V_1_MESSAGES_SENT_5_MESSAGE = API_V_1_MESSAGES + SENT + MESSAGE_ID + MESSAGE;


    public static final String DELETED = "deleted/";
    public static final String API_V_1_MESSAGES_DELETED_5 = API_V_1_MESSAGES + DELETED + ACTIVE_USER_ID;
    public static final String API_V_1_MESSAGES_DELETED_5_MESSAGE = API_V_1_MESSAGES + DELETED + MESSAGE_ID + MESSAGE;
    public static final String API_V_1_MESSAGES_DELETED_5_TEMP = API_V_1_MESSAGES + DELETED + "/temp/" + ACTIVE_USER_ID + "/" + SENT_MESSAGE_TYPE;

    public static final String API_V_1_MESSAGES_POST_MESSAGE = API_V_1_MESSAGES + "message";

    public static final String $MESSAGES = "$.messages";
    public static final String $FIRST_MESSAGE = "$.messages[1].";
    public static final String $FIRST_MESSAGE_OWNER_NAME = $FIRST_MESSAGE + "userDetails.firstName";
    public static final String $FIRST_MESSAGE_TITLE = $FIRST_MESSAGE + "title";

    public static final String $MESSAGE_OWNER_NAME = "userDetails.firstName";
    public static final String $MESSAGE_TITLE =  "title";

}
