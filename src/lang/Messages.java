/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import logic.Round;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author Thanasis
 */
public class Messages {
    // https://murygin.wordpress.com/2010/04/23/parameter-substitution-in-resource-bundles/

    private ResourceBundle resourceBundle;

    public Messages(String bundleName, Locale locale) {
        resourceBundle = ResourceBundle.getBundle(bundleName, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
        /*
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
         */
    }

    public String getString(String key, String[] descInfo) {
        return String.format(resourceBundle.getString(key), (Object[]) descInfo);
    }
}
