package org.lab.util;

/** Допоміжні методи для роботи з номером місяця. */
public final class Months {
    private static final String[] IN_MONTH = {
            "січні", "лютому", "березні", "квітні", "травні", "червні",
            "липні", "серпні", "вересні", "жовтні", "листопаді", "грудні"
    };

    private Months() {
    }

    public static boolean isValid(int month) {
        return month >= 1 && month <= 12;
    }

    public static void requireValid(int month) {
        if (!isValid(month)) {
            throw new IllegalArgumentException("Місяць має бути від 1 до 12, а не " + month);
        }
    }

    /** Назва місяця у місцевому відмінку: «у березні». */
    public static String inMonth(int month) {
        requireValid(month);
        return IN_MONTH[month - 1];
    }
}
