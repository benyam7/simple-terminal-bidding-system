package services;

public class TEST {

    public static void main(String[] args){
        System.out.println("Welcome to Bidding System");
        // setup db
        // setUpDb();
        // handleFirstChoice();

        String s2S = ""+ 123;
        String[] s1Arr = s2S.split("");
        System.out.println(s1Arr);
        //System.out.println(computeJoinPoint(57, 78));


    }

    public static int computeJoinPoint(int s1, int s2){
        int jS1 = s1;
        int jS2 = s2;


        while (jS1 != jS2){

            String s1S = ""+ jS1;
            String s2S = ""+ jS2;
            String[] s1Arr = s1S.split("");
            String[] s2Arr = s2S.split("");

            for (String x: s1Arr){
                jS1 += Integer.parseInt(x);
            }

            for (String x: s2Arr){
                jS2 += Integer.parseInt(x);
            }
        }

        return jS1;
    }
}
