import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.HashMap;


public class Matchmaker {

    public static void main (String[] args) throws InterruptedException {
        intro();
        int weightedValueTotal = weights.questionWeights();
        int compatibilityScore = 100 - weightedValueTotal;
        summary(compatibilityScore, threshHolds(compatibilityScore));

    }

    public static void intro() throws InterruptedException {
        System.out.println("""
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                 
                                    Welcome to MatchMaker v745
                           The least optimized way to find your true love!!
                                  Featured on "100 Worst programs"
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                  
                
               
                MatchMaker v745 is a matchmaking program that uses complex proprietary
                algorithms to determine if you and Daniel are a match made in heaven, 
                friends, or whether you should just give up on love.
                """);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("""
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                You will be given a series of multiple choice questions that have 
                preset answers for them. Your job is to simply pick the answer that 
                best describes who you are by entering in the corresponding letter. 
                Based on your answers, a compatibility score will be calculated and 
                matched against Daniels. Please think carefully for each question!!
                
                Please press enter when ready!
                """);
        Scanner keyIn = new Scanner(System.in);
        keyIn.nextLine();

    }

    public static String threshHolds(int compScore) {
        if (compScore >= 80) {
            return "soulmates";
        } else if (compScore >= 50) {
            return "friends";
        } else {
            return "not friends";
        }
    }

    public static void summary(int comp, String threshHold) throws InterruptedException {
        System.out.println("Thank You! \nPlease wait while our super " +
                "complex matchmaking algorithms calculate your answers...");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("\n \nBased on our algorithms, you achieved a compatibility score of: " + comp);
        if (threshHold.toLowerCase().equals("soulmates")) {
            System.out.println("According to your score, you and Daniel are soulmates!!");
        } else if (threshHold.toLowerCase().equals("friends")) {
            System.out.println("According to your score, you and Daniel could be friends!!");
        } else {
            System.out.println("According to you score, you should probably user another matchmaking service...");
        }
    }

}

class questions extends Matchmaker {
    public static String questionOne = """
            White Sox or Cubs?
                A - What's baseball?
                B - Northside!!
                C - I'm more of a Chicago fan...
                D - White Sox are the black and white team, right?
                E - South Side Hit Men!!""";
    public static String questionTwo = """
            How many concerts/music festivals have you been to?
                A - Too many people and way to crowded for me...
                B - I went to this one concert a couple years ago...
                C - I go to some occasionally, mostly when (insert band name here) comes to town.
                D - I go to at least 3 or 4 every year!
                E - Let's see, riot fest for sure, lola depending on the line up, Spring awakening you know it! 
                    Can't forget about Wisconsin, Summer Set, Rock Fest, Country Jam USA duh. Oh, and always 
                    attend (band x,y,z) when they're in town""";
    public static String questionThree = """
                How much of an introvert/extrovert are you?
                    A - I don't go out at all, like being around people? Eww! The sun is too bright, and I'd rather just 
                    stay home in my pajamas....
                    B - I don't like going out that much, mostly just to show face.
                    C - I have to go out and socialize everyday otherwise I think the world is going to end!
                    D - I go out almost every day, but don’t necessarily feel the need to...
                    E - I like to go out and socialize, but I also need time in between just for myself.
                """;
    public static String questionFour = """
                Out of these spots, where would you like to vacation?
                    A - Where ever the party is at, duh...
                    B - If it has a beach, I am there!
                    C - Some where I haven't been before.
                    D - Some place where there is a lot of activities to do
                    E - Anywhere with mountains, rivers, trees, anything outdoors, beautiful sights, some where adventurous!
                """;
    public static String questionFive = """
                PlayStation vs Xbox vs PC?
                    A - PS gang!
                    B - none or no preference
                    C - Xbox for life
                    D - All of them
                    E - PC master race!
                """;


}
class weights {
    public static int questionWeights(){
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        hmap.put(questions.questionOne, 1);
        hmap.put(questions.questionTwo, 2);
        hmap.put(questions.questionThree, 3);
        hmap.put(questions.questionFour, 3);
        hmap.put(questions.questionFive,1);

        int weightedValueOne = (hmap.get(questions.questionOne)) * getValues.firstValue;
        int weightedValueTwo = (hmap.get(questions.questionTwo)) * getValues.secondValue;
        int weightedValueThree = (hmap.get(questions.questionThree)) * getValues.thirdValue;
        int weightedValueFour = (hmap.get(questions.questionFour)) * getValues.fourthValue;
        int weightedValueFive = (hmap.get(questions.questionFive)) * getValues.fifthValue;
        int totalWeightedValues = weightedValueOne + weightedValueTwo + weightedValueThree + weightedValueFour + weightedValueFive;
        return totalWeightedValues;


    }


}
class getValues {
    public static int firstValue = questionValue(questionAnswer(questions.questionOne));
    public static int secondValue = questionValue(questionAnswer(questions.questionTwo));
    public static int thirdValue = questionValue(questionAnswer(questions.questionThree));
    public static int fourthValue = questionValue(questionAnswer(questions.questionFour));
    public static int fifthValue = questionValue(questionAnswer(questions.questionFive));


    public static int questionValue(String letter) {
        return switch (letter.toUpperCase()) {
            case "A" -> 5;
            case "B" -> 4;
            case "C" -> 3;
            case "D" -> 2;
            case "E" -> 1;
            default -> 0;
        };
    }
    public static boolean validate(String letter) {
        return letter.toUpperCase().equals("A") || letter.toUpperCase().equals("B") ||
                letter.toUpperCase().equals("C") || letter.toUpperCase().equals("D") ||
                letter.toUpperCase().equals("E");
    }
    public static String questionAnswer(String question) {
        Scanner myObj = new Scanner(System.in);
        System.out.println(question);
        String letter = myObj.nextLine();

        boolean validateCheck = validate(letter);
        while (!validateCheck) {
            myObj = new Scanner(System.in);
            System.out.println("Please Enter a Valid Character (A,B,C,D, or E)");
            letter = myObj.nextLine();

            validateCheck = validate(letter);
        }
        return letter;

    }
}
