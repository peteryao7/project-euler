import java.util.*;

class p079 {
    /*
        https://projecteuler.net/problem=79

        A common security method used for online banking is to ask the user
        for three random characters from a passcode. For example, if the
        passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters;
        the expected reply would be: 317.

        The text file, keylog.txt, contains fifty successful login attempts.

        Given that the three characters are always asked for in order, analyse
        the file so as to determine the shortest possible secret passcode of
        unknown length.

        keylog.txt: https://projecteuler.net/project/resources/p079_keylog.txt

        -----

        Guess and Check peepoClown:
        I solved this problem manually step by step. First, I noticed there
        were some duplicate logs in the txt, so I removed them, then sorted
        the ones left to see if I could find a pattern manually.
        
        Looking at the logs left, I noticed that no entry had more than one of
        the same number and 4 and 5 didn't exist, so the passcode has to be of
        length 8. Any valid code should be a solution. Then I looked at the
        numbers whose first digit had the fewest logs, so there were only 2
        logs that started with 2 and 1 that started with 8, so 2890 had to
        happen in that order. Then from there, I could piece together the other
        4 numbers.
    */
    static String[] KEYLOGS = {
        "319","680","180","690","129","620","762","689","762","318",
        "368","710","720","710","629","168","160","689","716","731",
        "736","729","316","729","729","710","769","290","719","680",
        "318","389","162","289","162","718","729","319","790","680",
        "890","362","319","760","316","729","380","319","728","716"
    };
    
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (String s : KEYLOGS) {
            set.add(s);
        }

        System.out.println("Set size: " + set.size());

        List<String> distinctLogs = new ArrayList<>();
        for (String s : set) {
            distinctLogs.add(s);
        }

        Collections.sort(distinctLogs);

        for (String s : distinctLogs) {
            System.out.println(s);
        }
    }
}