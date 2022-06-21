package nfs.bankco.Utils.locals;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Locale;

public class LocalsUtility {
    static String supportedLanguages = "en,fr";

    public static Locale getBrowserLanguage(HttpServletRequest req, HttpServletResponse res) {
        Enumeration locales = req.getLocales();
        Locale browserLocale = null;
        while (locales.hasMoreElements()) {
            Locale locale = (Locale) locales.nextElement();
            if (supportedLanguages.contains(locale.toString())) {
                browserLocale = locale;
                break;
            }
        }
        return browserLocale;
    }
}
