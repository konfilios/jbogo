package jbogo.modules.l10n.util;

import java.io.PrintStream;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.TreeSet;

/**
 * Prints csv lists of l10n entities.
 */
class CsvPrinter
{
    private static void printIsoCountryCodes(PrintStream out)
    {
        int maxIdLength = 0;
        int maxNameLength = 0;
        int count = 0;

        out.println("\n\nid,name");
        for (String countryCode : Locale.getISOCountries()) {
            // Update row count
            count++;

            // Extract row data
            Locale locale = new Locale("", countryCode);
            String displayName = locale.getDisplayCountry();

            out.println(countryCode + "," + displayName);

            // Update max lengths
            maxIdLength = countryCode.length();

            if (displayName.length() > maxNameLength) {
                maxNameLength = displayName.length();
            }
        }

        System.out.println("\nRecord count: " + count);
        System.out.println("Max lengths:" +
            " id=" + maxIdLength + " chars" +
            " name=" + maxNameLength + " chars");
    }

    private static void printIsoLanguageCodes(PrintStream out)
    {
        int maxIdLength = 0;
        int maxNameLength = 0;
        int count = 0;

        out.println("\n\nid,name");
        for (String languageCode : Locale.getISOLanguages()) {
            // Update row count
            count++;

            // Extract row data
            Locale locale = new Locale(languageCode);
            String languageId = locale.getLanguage();
            String displayName = locale.getDisplayLanguage();

            out.println(languageId + "," + displayName);

            // Update max lengths
            maxIdLength = languageId.length();

            if (displayName.length() > maxNameLength) {
                maxNameLength = displayName.length();
            }
        }

        System.out.println("\nRecord count: " + count);
        System.out.println("Max lengths:" +
            " id=" + maxIdLength + " chars" +
            " name=" + maxNameLength + " chars");
    }

    private static void printTimezoneIds(PrintStream out)
    {
        int maxCodeLength = 0;
        int maxNameLength = 0;
        int count = 0;

        out.println("\n\nid,code,name");
        for (String timezoneCode : new TreeSet<>(ZoneId.getAvailableZoneIds())) {
            // Update row count
            count++;

            // Extract row data
            String displayName = ZoneId.of(timezoneCode).getDisplayName(TextStyle.FULL, Locale.US);

            out.println(
                count + "," + timezoneCode + ",\"" + displayName + "\"");

            // Update max lengths
            if (timezoneCode.length() > maxCodeLength) {
                maxCodeLength = timezoneCode.length();
            }
            if (displayName.length() > maxNameLength) {
                maxNameLength = displayName.length();
            }
        }
        System.out.println("\nRecord count: " + count);
        System.out.println("Max lengths:" +
            " id=" + Short.BYTES + " bytes" +
            " code=" + maxCodeLength + " chars" +
            " name=" + maxNameLength + " chars");
    }

    public static void main(String[] args)
    {
        printIsoCountryCodes(System.out);

        printIsoLanguageCodes(System.out);

        printTimezoneIds(System.out);

        System.out.println("\nDone");
    }
}
