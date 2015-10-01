package com.example;

// Janet Weber DUE 10/5/2015
// Chicago Bulls Arrays Lab (Week 7)

// Using the information in the link provided (http://stats.nba.com/team/#!/1610612741/players/)
// to create an array to store the player stat information and calculate the following items:
// What is the average amount of points scored by the team?
// What is the average amount of rebounds for the top 3 percentage 3 point shooters?
// Based on the stats who is a better player Derek Rose or Jimmy Butler?

public class MyClass {

    public static void main(String[] args) {
        // ********************************************
        // DATA - from link in description above : ALL Parallel arrays
        // *******************************************
        String[] playerName = {"Paul Gasol", "Derrick Rose", "Jimmy Butler", "Aaron Brooks",
                "Taj Gibson", "Mike Dunleavy", "Nikola Mirotic", "Joakim Noah",
                "Tony Snell", "Kirk Heinrich", "Doug McDermott", "E'Twaun Moore",
                "Nazr Mohammed", "Cameron Bairstow"};
        int[] gamesPlayed = {78, 51, 65, 82, 62, 63, 82, 67, 72, 66, 36, 56, 23, 18};
        double[] points = {18.5, 17.7, 20.0, 11.6, 10.3, 9.4, 10.2, 7.2, 6.0, 5.7,
                3.0, 2.7, 1.2, 0.6};
        double[] freeThrowsMade = {3.8, 3.0, 5.9, 1.8, 2.1, 1.1, 2.8, 1.6, 0.6, 0.5,
                0.3, 0.2, 0.0, 0, 2};
        double[] threePtPerc = {46.2, 28.0, 37.8, 38.7, 0.0, 40.7, 31.6, 0.0, 37.1, 34.5,
                31.7, 34.2, 0.0, 0.0};
        double[] steals = {0.3, 0.7, 1.8, 0.7, 0.6, 0.6, 0.7, 0.7, 0.4, 0.7, 0.1, 0.4, 0.2, 0.1};
        double[] rebounds = {2.0, 0.4, 3.2, 1.2, 0.8, 5.8, 9.6, 1.8, 3.9, 1.7, 4.9, 11.8, 6.4, 2.4};

        // *******************************************************
        double totalPts,           // used to sum points
                avgTeamPts,        // holds resulting average team points
                tmpDbl,            // temporary variable used in array sort to swap
                rbnds,             // used to sum rebounds
                avgRbnds;          // holds resulting average rebounds

        int[] tallyArr = {0, 0};    // Butler is index 0, Rose is index 1 - keeps count of stats won

        int currentMin,             // used to hold index containing current miminum value in sort
                butler,             // used to hold array index for player Jimmy Butler
                rose,               // used to hold array index for player Derrick Rose
                tmpInt;             // temporary variable used in array sort to swap

        String tmpStr;              // temporary variable used in array sort to swap

        // *******************************************************
        // Display something about what program does
        System.out.println("Random Stats on the Chicago Bulls!");
        System.out.println("");

        // ****************************************************************
        // find average points per game for the team
        // ******************************************************************
        totalPts = 0;               // initialize variable to hold sum of all points for team
        for (int i = 0; i < playerName.length; i++) {   // process thru team multiplying games
            totalPts += (gamesPlayed[i] * points[i]);   //   played by points for each player
        }                                               //   and add to sum (totalPts)

        avgTeamPts = totalPts / 82;                     //  compute average and display
        System.out.println("The average amount of points scored by Chicago Bulls as a team => " +
                avgTeamPts);

        // ************************************************************************
        // find average amount of rebounds/game for top three 3 point shooters
        // First, sort all arrays by 3 pt percentage
        // ************************************************************************8
        System.out.println("");
        for (int i=0; i<threePtPerc.length; i++)  {
            currentMin = i;
            for (int j = i+1; j<threePtPerc.length; j++) {
                if (threePtPerc[j] < threePtPerc[currentMin]) {
                    currentMin = j;
                }
            }
            // SWAP ALL parallel array elements if necessary - 7 total
            if (currentMin != i) {
                tmpDbl = threePtPerc[i];
                threePtPerc[i] = threePtPerc[currentMin];   // 3 point perc array
                threePtPerc[currentMin] = tmpDbl;

                tmpDbl = steals[i];
                steals[i] = steals[currentMin];             // steal array
                steals[currentMin] = tmpDbl;

                tmpDbl = points[i];
                points[i] = points[currentMin];             // points array
                points[currentMin] = tmpDbl;

                tmpDbl = freeThrowsMade[i];                 // free throws made array
                freeThrowsMade[i] = freeThrowsMade[currentMin];
                freeThrowsMade[currentMin] = tmpDbl;

                tmpInt = gamesPlayed[i];
                gamesPlayed[i] = gamesPlayed[currentMin];   // games played array
                gamesPlayed[currentMin] = tmpInt;

                tmpDbl = rebounds[i];
                rebounds[i] = rebounds[currentMin];         // rebounds
                rebounds[currentMin] = tmpDbl;

                tmpStr = playerName[i];
                playerName[i] = playerName[currentMin];     // player name
                playerName[currentMin] = tmpStr;
            }
        }

        // ALL arrays now sorted by three point percentage (low to high)
        //    Need top three so use last three array indexes and get those rebounds
        rbnds = 0;
        System.out.print("The average rebounds per game for the top three " +
                "players in 3-point percentage: ");
        for (int i=threePtPerc.length-3; i<threePtPerc.length; i++) {
            rbnds += rebounds[i];               // sum rebounds for these three
        }
        avgRbnds = rbnds/3;                     // compute average
        System.out.println(avgRbnds + " rebounds");

        // make it display nicely
        for (int i=threePtPerc.length-3; i<threePtPerc.length; i++) {
            System.out.println("     " + playerName[i] + ": " + threePtPerc[i] +
                    "% 3-point avg   " + rebounds[i] + " rebounds per game.");
        }
        // ***************************************************************
        // Lastly, find better player - Derek Rose or Jimmy Butler
        //  Use points scored, free throws, and steals
        // ****************************************************************
        butler = 99;   // initialize these to 99 so we'll know they get set correctly
        rose = 99;
        for (int i = 0; i < playerName.length; i++) {       // process thru array to find Rose and
            if (playerName[i].matches("Jimmy Butler")) {    //   Butler, save those indexes
                butler = i;
            }
            if (playerName[i].matches("Derrick Rose")) {
                rose = i;
            }
        }

        if ((butler != 99) && (rose != 99)) {               // indexes are both set correctly
            // if butler has more points, give him a tally for winning this stat (0)
            //   otherwise give it to rose (1)
            if (points[butler] > points[rose]) {
                tallyArr[0] += 1;
            } else {
                tallyArr[1] += 1;
            }

            // if butler has more free throws, give him a tally for winning this stat (0)
            //    otherwise, give it to rose (1)
            if (freeThrowsMade[butler] > freeThrowsMade[rose]) {
                tallyArr[0] += 1;
            } else {
                tallyArr[1] += 1;
            }

            // if butler has more steals, give him a tally for winning this stat (0)
            //    otherwise, give it to rose (1)
            if (steals[butler] > steals[rose]) {
                tallyArr[0] += 1;
            } else {
                tallyArr[1] += 1;
            }

            // Whoever has more tallies has won the most stats and is the better player
            System.out.println("");
            System.out.println("Who is better?  Rose or Butler?");
            System.out.println("    (calculated using free throws made, points, and steals)");
            System.out.println("");
            if (tallyArr[0] > tallyArr[1]) {
                System.out.println("     " + playerName[butler] + " is the better player.");
            } else {
                System.out.println("     " + playerName[rose] + "is the better player.");
            }
            System.out.println("     " + tallyArr[0] + " stats won by " + playerName[butler]);
            System.out.println("     " + tallyArr[1] + " stats won by " + playerName[rose]);
        } else { // indexes didn't get set correctly so couldn't compare
            System.out.println("Error. Player index invalid");
        }
    }
}
