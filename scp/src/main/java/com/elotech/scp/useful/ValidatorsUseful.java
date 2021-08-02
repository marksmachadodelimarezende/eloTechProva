package com.elotech.scp.useful;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Optional;

public class ValidatorsUseful {

    public static boolean isValidString(String value) {
        return value != null && value.length() > 0;
    }

    public static boolean isValidSintaxEmail(String email) {
        boolean isChecked = false;
        if (isValidString(email)) {
            if (email.contains("@") && email.contains(".")) {
                int posArr = email.indexOf("@");
                String sufix = email.substring(posArr);
                int qtdeCharsSufix = sufix.replace("@", "").length();
                if (posArr > 0 && qtdeCharsSufix > 0 && isValidString(sufix.replace("@", ""))) {
                    sufix = email.substring(posArr + 1);
                    isChecked = (!sufix.contains("@") && sufix.contains("."));
                }
            }
        }
        return isChecked;
    }

}
