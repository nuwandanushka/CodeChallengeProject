package com.java.codeChallenge.enums;

public class JSONObjectEnum {

    public enum Ticket {
        ID("_id","ID"),
        URL("url","URL"),
        EXTERNAL_ID("external_id","External Id"),
        CREATED_AT("created_at","Created at"),
        TYPE("type","Type"),
        SUBJECT("subject","Subject"),
        DESCRIPTION("description","Description"),
        PRIORITY("priority","Priority"),
        STATUS("status","Status"),
        SUBMITTER_ID("submitter_id","Submitter id"),
        ASSIGNEE_ID("assignee_id","Assignee id"),
        ORGANIZATION_ID("organization_id","Organization id"),
        TAGS("tags","TAGS"),
        HAS_INCIDENTS("has_incidents","Has Incident"),
        DUE_AT("due_at","Due at"),
        VIA("via","Via"),
        ORGANIZATION_NAME("organization_name","Organization Name"),
        SUBMITTER_NAME("submitter_name","Submitter Name"),
        ASSIGNEE_NAME("assignee_name","Assignee Name");


        public String key;
        public  String value;

        Ticket(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public enum User {
        ID("_id","ID"),
        URL("url","URL"),
        EXTERNAL_ID("external_id","External Id"),
        NAME("name","Name"),
        ALIAS("alias","Alias"),
        CREATED_AT("created_at","Created at"),
        ACTIVE("active","Active"),
        VERIFIED("verified","Verified"),
        SHARED("shared","Shared"),
        LOCALE("locale","Locale"),
        TIMEZONE("timezone","Time zone"),
        LAST_LOGIN_AT("last_login_at","Last Login at"),
        PHONE("phone","Phone"),
        SIGNATURE("signature","Signature"),
        ORGANIZATION_ID("organization_id","Organization id"),
        TAGS("tags","TAGS"),
        SUSPENDED("suspended","Suspended"),
        ROLE("role","Role"),
        ORGANIZATION_NAME("organization_name","Organization Name"),
        SUBMITTER_NAME("submitted_ticket_subject","Submitter Name"),
        ASSIGNEE_NAME("assignee_ticket_subject","Assignee Name");


        public String key;
        public  String value;

        User(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public enum Organization {
        ID("_id","ID"),
        URL("url","URL"),
        EXTERNAL_ID("external_id","External Id"),
        NAME("name","Name"),
        DOMAIN_NAMES("domain_names","Domain Names"),
        CREATED_AT("created_at","Created at"),
        DETAILS("details","Details"),
        SHARED_TICKETS("shared_tickets","Shared Tickets"),
        TAGS("tags","TAGS"),
        TICKET_SUBJECT("ticket_subject","Ticket subject"),
        USER_NAME("user_name","User Name");


        public String key;
        public  String value;

        Organization(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
