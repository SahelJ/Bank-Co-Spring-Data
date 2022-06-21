package nfs.bankco.Utils.form;

import java.util.Map;

public class FormUtility {

    /*
     *  Verification de la validité des informations fournient par l'utilisateur
     */
    public static Map<String,String> checkStringValidity(Map<String, String> errors, String fieldName, String fieldData, int minLength, int maxLength) {
        if (fieldData.isEmpty() || fieldData.length() == 0) {
            errors.put(fieldName,"Veuillez renseigner ce champ");
        } else if ((fieldData.length() < minLength && fieldData.length() > maxLength)) {
            errors.put(fieldName,"Ce champ doit contenir entre " + minLength + " et " + maxLength + " caracteres");
        }
        return errors;
    }
    /*
     *  Verification de la validité des documents fournient par l'utilisateur
     */
    public static Map<String,String> checkDocumentValidity(Map<String, String> errors, String fieldName, String document) {

        return  errors;
    }
}
