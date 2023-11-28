package com.south.african.numbers.app.utils.constant;

public class Constant {

    public static final class HttpStatus {

        public static final String RESPONSE_200 = "200";
        public static final String RESPONSE_201 = "201";
        public static final String ERROR_400 = "400";
        public static final String ERROR_404 = "404";
        public static final String ERROR_415 = "415";
        public static final String ERROR_500 = "500";

        public static final String UNSUPPORTED_MEDIA_TYPE = "Unsupported Media Type";

        public static final String OK = "Ok";
        public static final String CREATED = "Created";
        public static final String BAD_REQUEST = "Bad request";
        public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    }

    public static final class DataModel {

        public static final String PHONE_NUMBER_RESPONSE = "PhoneNumberResponse";
        public static final String ERROR_RESULT = "ErrorResult";

        public static final String SAVE_RESPONSE = "SaveResponse";

        public static final String PHONE_NUMBER_LIST_RESPONSE = "PhoneNumberListResponse";
    }

    public static final class APIMessage {

        public static final String SAVE_DESCRIPTION = "Save all phone numbers to DB from a CSV file";

        public static final String FIND_ALL_PHONE_NUMBERS_DESCRIPTION = "Find all phone numbers from DB";

        public static final String FIND_PHONE_NUMBERS_STATUS_DESCRIPTION = "Find phone numbers by status";

        public static final String FIND_PHONE_NUMBER = "Find single phone number";



    }

    public static final class APIResponseMessage {

        public static final String PHONE_NUMBER_LIST = "The phone number list is empty";

        public static final String PHONE_NUMBER = "The requested phone number could not be found in the database";

        public static final String SAVE_MESSAGE = " south african phone numbers have been saved";
    }

    public static final class Config {

        public static final String TITLE = "South African Mobile Phone Numbers";

        public static final String DESCRIPTION = "App for saving, correcting, and managing South African phone numbers";

        public static final String V_1 = "1.0";
    }
}
