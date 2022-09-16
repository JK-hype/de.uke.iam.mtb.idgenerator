package de.uke.iam.mtb.idgenerator.util;

import java.sql.Date;

public class MtbIdCreator {

    public static String createId(String firstName, String lastName, Date birthday) {
        return firstName + "_" + lastName + "_" + birthday;
    }

    private MtbIdCreator() {

    }
}
