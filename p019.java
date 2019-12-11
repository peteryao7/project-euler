class p019 {
    /*
        How many Sundays fell on the first of the month during the 20th century?
        (From 1 Jan 1901 to 31 Dec 2000)

        1 Jan 1900 was a Monday.
        September, April, June, November has 30 days.
        February has 28 days and 29 days on leap years.
        The rest of the months have 31 days.
        A leap year occurs on any year evenly divisible by 4, but not on a 
            century unless it's divisible by 400.

        Zeller's congruence:
        We can use the formula for the Gregorian calendar from here:
        https://en.wikipedia.org/wiki/Zeller's_congruence
    */
    public static int daysOfWeek(int startYear, int endYear) {
        int count = 0;

        while (startYear <= endYear) {
            for (int month = 1; month <= 12; month++)
                if (getDay(startYear, month, 1) == 0)
                    count++;
            startYear++;
        }

        return count;
    }

    private static int getDay(int year, int month, int day) {
        int q = day;
        int m;
        if (month == 1)
            m = 13;
        else if (month == 2)
            m = 14;
        else
            m = month;

        int K = year % 100;
        int J = year / 100;

        return (q + (13 * (m + 1)) / 5 + K + K / 4 + J / 4 - 2 * J) % 7;
    }

    public static void main(String[] args) {
        System.out.println(daysOfWeek(1901, 2000));
    }
}