import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;


public class normalUser {

    static Scanner sc = new Scanner(System.in);
    Connection con;
    int userId;

    public normalUser(String email, String pw, Connection con) throws SQLException// add the con stuff
    {
        this.con = con;
        // check if user in the database
        this.userId = getIdFromLog(email, pw, con);
        System.out.println(this.userId);
    }


    public static int getIdFromLog(String email, String pw, Connection con) throws SQLException {
        String query = "select Max(user_id) from users where users.email=\"" + email + "\" && users.pw=\"" + pw + "\";";
        int id = -1;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            rs.next();
            id = rs.getInt("Max(user_id)");


        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }
        return id;
    }

    public static void getQuery(String query, Connection con) throws SQLException {

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs);
                String first_name = rs.getString("first_name");
                System.out.println(first_name);
                int user_id = rs.getInt("user_id");
                System.out.println(user_id);

                System.out.println(first_name + ", " + user_id);
            }
        } catch (SQLException e) {
            System.out.println("bug");
            //JDBCTutorialUtilities.printSQLException(e);
        }


    }

    public static void executeStatement(String statement, Connection con) throws SQLException {

        try (Statement stmt = con.createStatement()) {
            stmt.execute(statement);


        } catch (SQLException e) {
            System.out.println("bug");
            //JDBCTutorialUtilities.printSQLException(e);
        }

    }

    public static int getLastId(String tableName, Connection con) throws SQLException {
        String query = "";
        String whichCol = "";
        switch (tableName) {

            case "users":

                query = "SELECT MAX(user_id) FROM users;";
                whichCol = "MAX(user_id)";
                break;
            case "current_item":
                query = "SELECT MAX(item_id) FROM current_items;";
                whichCol = "MAX(item_id)";
                break;
            case "sold_items":
                query = "SELECT MAX(item_id) FROM sold_items;";
                whichCol = "MAX(item_id)";
            default:
                System.out.println("pas dans les choix possible");
        }

        int id = -1;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            rs.next();
            id = rs.getInt(whichCol);


            //maybe rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }

        return id;


    }

    public static void menu() throws SQLException {

    }

    public static void main(String[] args) throws SQLException {

        /*Connection con =DriverManager.getConnection(urlSql, userNameSql, passwordSql);
        ;
        normalUser u=new normalUser("cal@gmail.com","Cal1",con);
        u.secondLevelMenu();*/


    }

    public void sellItemMenu() throws SQLException {
        System.out.println("----< Sell item >\n" +
                "---- select from the following category : (Enter the number)\n" +
                "    (1) Electronics\n" +
                "    (2) Books\n" +
                "    (3) Home\n" +
                "    (4) Clothing\n" +
                "    (5) Sporting Goods\n" +
                "---- condition: (Enter the number)\n" +
                "    (1) New\n" +
                "    (2) Like-New\n" +
                "    (3) Used (Good)\n" +
                "    (4) Used (Acceptable)\n" +
                "---- name\n" +
                "---- description:      // one line description\n" +
                "---- buy-it-now price: // if user’s input is not numeric, repeat this prompt.\n" +
                "                       // after storing the buy-it-now price, go back to the previous menu.\n" +
                "---- bid ending date:  //  bid ending date\n" +
                "Is everything good ?(if you select N you do the process again) [Y/N]");
        outer:
        while (true) {
            System.out.println("Category");
            int category = sc.nextInt();

            while (category > 5 || category < 1) {
                System.out.println("Please try again do not correspond to the choices possible");

                category = sc.nextInt();
            }
            String stringcategory = "";
            switch (category) {
                case 1:
                    stringcategory = "Electronics";
                    break;
                case 2:
                    stringcategory = "Books";
                    break;
                case 3:
                    stringcategory = "Home";
                    break;
                case 4:
                    stringcategory = "Clothing";
                    break;
                case 5:
                    stringcategory = "Sporting Goods";
                    break;
                default:
                    System.out.println("bug dans les categories");
                    break;
            }
            System.out.println("Condition");
            int condition = sc.nextInt();
            while (condition > 4 || condition < 1) {
                System.out.println("Please try again do not correspond to the choices possible");
                condition = sc.nextInt();
            }
            String stringCondition = "";

            switch (condition) {
                case 1:
                    stringCondition = "New";
                    break;
                case 2:
                    stringCondition = "Like-New";
                    break;
                case 3:
                    stringCondition = "Used (Good)";
                    break;
                case 4:
                    stringCondition = "Used (Acceptable)";
                    break;

                default:
                    System.out.println("bug condition");

            }

            System.out.println("Please enter the name of the object");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.println("Description :");
            String description = sc.nextLine();
            System.out.println("buy it now price :");
            int buyItNow = -1;

            while (true) {
                try {
                    buyItNow = sc.nextInt();

                } catch (Exception e) {
                    System.out.println("The number selected do not appear to be possible, please try again");
                    sc.nextLine();
                    continue;
                }
                if (buyItNow > 0) {
                    break;
                } else {
                    System.out.println("Please choose a positive number, >0");
                }

            }
            System.out.println("Please choose the floor price of your item");
            int floorPrice = -1;

            while (true) {
                try {
                    floorPrice = sc.nextInt();

                } catch (Exception e) {
                    System.out.println("The number selected do not appear to be possible, please try again bis");
                    sc.nextLine();
                    continue;
                }
                if (floorPrice > 0) {
                    break;
                } else {
                    System.out.println("Please choose a positive number, >0");
                }

            }
            System.out.println("Please select the day of the end of your bid (include 0, like 01 for the first day)");
            sc.nextLine();
            String day = sc.nextLine();
            System.out.println("Please select the month of the end of your bid(include 0 like 01 for january) ");
            String month = sc.nextLine();
            System.out.println("Please select the Year of the end of your bid(complete year like 2022)");
            String year = sc.nextLine();
            String finaldate = year + "-" + month + "-" + day;
            System.out.println("Is everything okay([Y/N]");
            String okay = sc.nextLine();
            okay.toUpperCase();

            String query = "insert into current_items values(";
            int lastId = getLastId("current_item", this.con) + 1;
            query = query + lastId + ",";
            query = query + this.userId + ",";
            query = query + -1 + ",";//-1 mean no one is currently buying it
            query = query + "\"" + name + "\",";
            query = query + "\"" + stringcategory + "\",";
            query = query + "\"" + description + "\",";
            query = query + "\"" + stringCondition + "\",";
            query = query + 0 + ",";
            query = query + buyItNow + ",";
            query = query + floorPrice + ",";
            query = query + 0 + ",";
            query = query + "\"\"" + ",";
            String timeNow = java.time.LocalDate.now().toString();
            query = query + "\'" + timeNow + "\'" + ",";
            query = query + "\'" + finaldate + "\')";


            //System.out.println(query);

            if (okay.equals("Y")) {
                try (Statement stmt = con.createStatement()) {
                    stmt.execute(query);
                    System.out.println("item added to your shop !");
                    break outer;


                } catch (SQLException e) {
                    System.out.println(e);
                    System.out.println("An error occurred please follow the instructions !");
                    //JDBCTutorialUtilities.printSQLException(e);
                }
            }


        }
    }

    public void showStatusItems() throws SQLException {
        String query = "select * from current_items where current_items.seller_id=" + this.userId + ";";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                System.out.println("---- [Item " + i + "]");
                String description = rs.getString("item_description");
                System.out.println("---- Description : " + description);
                String status = rs.getString("number_bid");
                System.out.println("---- Status : " + status);
                int currentBiddingPrice = rs.getInt("bid_price");
                System.out.println("---- Current Bidding Price : " + currentBiddingPrice);
                Date datePosted = rs.getDate("date_posted");
                System.out.println("---- Date Posted : " + datePosted);
                Date dateEnd = rs.getDate("date_end");
                System.out.println("---- End Date : " + dateEnd);
                String history = rs.getString("bid_history");
                System.out.println("---- Bidding History : " + history);
                int floorPrice = rs.getInt("floor_price");
                System.out.println("---- Floor Price : " + floorPrice);
                int getItNowPrice = rs.getInt("buy_it_now");
                System.out.println("---- Buy it Now price : " + getItNowPrice);
                System.out.println();


                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }

    }

    public void searchFunction(String choiceTotal) throws SQLException {
        String query = "select * from current_items where ";

        switch (choiceTotal) {
            case "11":
                query = query + "current_items.item_category=\"Electronics\"";
                break;
            case "12":
                query = query + "current_items.item_category=\"Books\"";
                break;
            case "13":
                query = query + "current_items.item_category=\"Home\"";
                break;
            case "14":
                query = query + "current_items.item_category=\"Clothing\"";
                break;
            case "15":
                query = query + "current_items.item_category=\"Sporting Goods\"";
                break;
            case "20":
                System.out.println("Please enter a keyword for your search");
                String keyword = sc.nextLine();
                query = query + "current_items.item_description LIKE \'%" + keyword + "%\'";
                break;
            case "30":
                System.out.println("Please enter the first name of the seller");
                String firstName = sc.nextLine();
                System.out.println("Please enter the second_name of the seller");
                String secondName = sc.nextLine();

                query = "SELECT *\n" +
                        "FROM current_items\n" +
                        "INNER JOIN users ON current_items.seller_id=users.user_id where first_name=\"" + firstName + "\" and second_name=\"" + secondName + "\"";
                break;
            case "40":
                System.out.println("Please enter the day(complete day, like 01 for the first not 1)");
                String day = sc.nextLine();
                System.out.println("Please enter the month(complete month, like 01 for january not 1");
                String month = sc.nextLine();
                System.out.println("Please enter the year(complete year, like 2022)");
                String year = sc.nextLine();
                String finalDate = "\'" + year + "-" + month + "-" + day + "\'";
                query = "SELECT * FROM current_items WHERE current_items.date_posted>" + finalDate + "";

                break;


        }
        query = query + "and current_items.seller_id!=" + this.userId + ";";//so that it don't show the stuff of the current user
        System.out.println(query);
        int i = 1;
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                System.out.println("---- [Item " + i + "]");
                String description = rs.getString("item_description");
                System.out.println("---- Description : " + description);
                String status = rs.getString("number_bid");
                System.out.println("---- Status : " + status);
                int currentBiddingPrice = rs.getInt("bid_price");
                System.out.println("---- Current Bidding Price : " + currentBiddingPrice);
                Date datePosted = rs.getDate("date_posted");
                System.out.println("---- Date Posted : " + datePosted);
                Date dateEnd = rs.getDate("date_end");
                System.out.println("---- End Date : " + dateEnd);
                String history = rs.getString("bid_history");
                System.out.println("---- Bidding History : " + history);
                int floorPrice = rs.getInt("floor_price");
                System.out.println("---- Floor Price : " + floorPrice);
                int getItNowPrice = rs.getInt("buy_it_now");
                System.out.println("---- Buy it Now price : " + getItNowPrice);
                System.out.println();


                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }
        if (i == 1) {
            System.out.println("It seems that there is no result for your answer");
            return;
        }
        System.out.println("--- Which item do you want to bid? (Enter the number or ’B’ to go back to the previous menu):");
        String item = sc.nextLine();
        if (item.equals("B") || item.equals("b")) {

            return;


        }
        int itemInt = Integer.parseInt(item);
        System.out.println("--- Bidding price? (Enter the price or ’buy’ to pay for the buy-it-now price) (of course higher than the current one):");
        int biddingPrice = sc.nextInt();
        //this part will get the id of the item to update it after that
        int id = -1;
        int counter = 1;
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {

                if (counter == itemInt) {
                    id = rs.getInt("item_id");
                    int currentBidding = rs.getInt("bid_price");

                    if (currentBidding >= biddingPrice) {
                        System.out.println("annulation, it has to be greater than the current price");
                        return;
                    }
                    break;
                }

                counter++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }
        query = "UPDATE current_items\n" +
                "SET current_buyer_id = " + this.userId + ", bid_price=" + biddingPrice + ",bid_history=CONCAT(bid_history, \";" + biddingPrice + "\")\n" +
                "WHERE item_id = " + id + ";";
        System.out.println(query);
        executeStatement(query, this.con);
        System.out.println("congratulation you're now the current buyer of this item");

    }

    public void searchItemMenu() throws SQLException {
        System.out.println("\n" +
                "----< Search item > : (Enter the number)\n" +
                "----(1) Search items by category\n" +
                "----(2) Search items by description keyword\n" +
                "----(3) Search items by seller\n" +
                "----(4) Search items by date posted\n" +
                "----(5) Go Back\n" +
                "----(6) Quit");
        String choice = sc.nextLine();
        if (choice.equals("5")) {
            return;
        }
        if (choice.equals("6")) {
            System.exit(0);
        }
        String choiceCategory = "0";
        if (choice.equals("1")) {
            System.out.println("----< Search items by category > : (Enter the number)\n" +
                    "----(1) Electronics\n" +
                    "----(2) Books\n" +
                    "----(3) Home\n" +
                    "----(4) Clothing\n" +
                    "----(5) Sporting Goods");
            choiceCategory = sc.nextLine();
        }

        String choiceTotal = choice + choiceCategory;
        searchFunction(choiceTotal);
    }

    public void statusAuction() throws SQLException {
        int i = 1;
        System.out.println("----< Check Status of your Bid >");
        System.out.println("---- Won items :");
        String query = "select * from sold_items\n" +
                "Inner join users on sold_items.buyer_id=users.user_id where users.user_id=" + this.userId + ";";
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                System.out.println("---- [item " + i + "]");
                System.out.println("---You won the item---");
                String description = rs.getString("item_description");
                System.out.println("Description : " + description);
                int biddingPrice = rs.getInt("sold_price");

                System.out.println("You have won the item for : " + biddingPrice);
                Date soldDate = rs.getDate("date_end");
                System.out.println("The date where the item has been sold was : " + soldDate);


                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }
        System.out.println("----------------------------------------------------");
        System.out.println();
        query = "select * from current_items\n" +
                "Inner join users on current_items.current_buyer_id=users.user_id where users.user_id=" + this.userId + ";";
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                System.out.println("---- [item " + i + "]");
                System.out.println("---You are still the current higher bidder ---");
                String description = rs.getString("item_description");
                System.out.println("Description : " + description);
                int biddingPrice = rs.getInt("bid_price");

                System.out.println("Current bidding price : " + biddingPrice);
                Date soldDate = rs.getDate("date_end");
                System.out.println("The end date of the auction is : " + soldDate);


                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }

    }

    public void checkAccountBalance() throws SQLException {
        int i = 1;
        System.out.println("----< Check your Account >");
        String query = "select * from sold_items\n" +
                "Inner join users on sold_items.seller_id=users.user_id where users.user_id=" + this.userId + ";";
        int sumSold = 0;
        int sumBought = 0;
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                System.out.println("---- Sold [item " + i + "]");

                String description = rs.getString("item_description");
                System.out.println("Description : " + description);
                int biddingPrice = rs.getInt("sold_price");
                sumSold = sumSold + biddingPrice;
                System.out.println("sold price : " + biddingPrice);
                Date soldDate = rs.getDate("date_end");
                System.out.println("you sold the item on : " + soldDate);
                System.out.println();
                System.out.println();


                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }
        query = "select * from sold_items\n" +
                "Inner join users on sold_items.buyer_id=users.user_id where users.user_id=" + this.userId + ";";

        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                System.out.println("---- Purchased [item " + i + "]");

                String description = rs.getString("item_description");
                System.out.println("Description : " + description);
                int biddingPrice = rs.getInt("sold_price");
                sumBought = sumBought + biddingPrice;
                System.out.println("Purchased price : " + biddingPrice);
                Date soldDate = rs.getDate("date_end");
                System.out.println("you bought the item on : " + soldDate);
                System.out.println();
                System.out.println();


                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }
        System.out.println();
        System.out.println();
        System.out.println(
                "[Your Balance Summary]");
        System.out.println("sold : " + sumSold);
        System.out.println("commission : -" + 0.02 * sumSold);
        System.out.println("purchased : " + sumBought);
        System.out.println("Total balance : " + (sumSold * 0.98 - sumBought));
    }

    public void secondLevelMenu() throws SQLException {

        outer:
        while (true) {
            System.out.println("----< Main menu > : \n" +
                    "----(1) Sell item\n" +
                    "----(2) Status of Your Item Listed on Auction \n" +
                    "----(3) Search item\n" +
                    "----(4) Check Status of your Bid \n" +
                    "----(5) Check your Account\n" +
                    "----(Q) Quit\n");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("sell item Menu");
                    this.sellItemMenu();
                    System.out.println();
                    System.out.println();

                    break;
                case "2":
                    System.out.println("status check");
                    this.showStatusItems();
                    System.out.println();
                    System.out.println();
                    break;
                case "3":
                    System.out.println("search item");
                    searchItemMenu();
                    System.out.println();
                    System.out.println();

                    break;
                case "4":
                    System.out.println("status check items");
                    statusAuction();
                    System.out.println();
                    System.out.println();

                    break;
                case "5":
                    System.out.println("check account");
                    checkAccountBalance();
                    System.out.println();
                    System.out.println();
                    break;
                case "Q":
                    System.out.println("Good bye!");
                    System.exit(0);
                    break outer;
                default:
                    System.out.println("not in the possible choices, please try again");
                    break;

            }

        }

    }

}


