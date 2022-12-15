import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class adminUser {
    static Scanner sc = new Scanner(System.in);
    Connection con;

    public adminUser(Connection con) {
        this.con = con;

    }

    public static void main(String[] args) throws SQLException {


    }

    public void listAllAuction() throws SQLException {
        String query = "Select * from current_items;";
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
    }

    public void statusAuction() throws SQLException {
        int i = 1;
        System.out.println("----< Check Status of all sold items >");

        String query = "select * from sold_items\n;";
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                System.out.println("---- Sold [item " + i + "]");

                String description = rs.getString("item_description");
                System.out.println("Description : " + description);
                int biddingPrice = rs.getInt("sold_price");

                System.out.println("Sold for : " + biddingPrice);
                Date soldDate = rs.getDate("date_end");
                System.out.println("The date where the item has been sold was : " + soldDate);
                System.out.println();


                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }

    }

    public void tradedPerCategory() throws SQLException {
        String q1 = "select count(item_id) from sold_items where item_category=\"Electronics\"; ";
        String q2 = "select count(item_id) from sold_items where item_category=\"Books\"; ";
        String q3 = "select count(item_id) from sold_items where item_category=\"Home\"; ";
        String q4 = "select count(item_id) from sold_items where item_category=\"Clothing\"; ";
        String q5 = "select count(item_id) from sold_items where item_category=\"Sporting Goods\"; ";
        int elec = 0;
        int book = 0;
        int home = 0;
        int clo = 0;
        int sport = 0;

        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(q1);
            rs.next();
            elec = rs.getInt("count(item_id)");
            rs = stmt.executeQuery(q2);
            rs.next();
            book = rs.getInt("count(item_id)");

            rs = stmt.executeQuery(q3);
            rs.next();
            home = rs.getInt("count(item_id)");
            rs = stmt.executeQuery(q4);
            rs.next();
            clo = rs.getInt("count(item_id)");

            rs = stmt.executeQuery(q5);
            rs.next();
            sport = rs.getInt("count(item_id)");
            System.out.println("Number of auction sold in Electronics : " + elec);
            System.out.println("Number of auction sold in Book : " + book);
            System.out.println("Number of auction sold in Home : " + home);
            System.out.println("Number of auction sold in Clothes : " + clo);
            System.out.println("Number of auction sold in Sport : " + sport);


        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }

    }

    public void pnl() throws SQLException {
        int sumSold = 0;
        int number = 0;
        String query = "select * from sold_items;";
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {

                int biddingPrice = rs.getInt("sold_price");
                sumSold = sumSold + biddingPrice;

                number++;
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        System.out.println("Summary of the compagny profits : ");
        System.out.println("Total number of sold items : " + number);
        System.out.println("Total liquidity : " + sumSold);
        System.out.println("Total profit: " + sumSold * 0.02);
    }

    public void buyerSeller() throws SQLException {
        String query = "select count(user_id) from users;";
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int number = rs.getInt("count(user_id)");
            System.out.println("Number of user : " + number);


        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }
    }

    public void menuAdmin() throws SQLException {

        while (true) {
            System.out.println("----< Main menu > : \n" +
                    "----(1) list all auction item\n" +
                    "----(2) Status all auction item \n" +
                    "----(3) how many traded per category\n" +
                    "----(4) compagny profit and all sold item \n" +

                    "----(5) how many buyer and seller on the platform\n" +
                    "----(Q) Quit\n");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    listAllAuction();
                    System.out.println();
                    System.out.println();
                    break;
                case "2":
                    statusAuction();
                    System.out.println();
                    System.out.println();
                    break;
                case "3":
                    tradedPerCategory();
                    System.out.println();
                    System.out.println();
                    break;
                case "4":
                    pnl();
                    System.out.println();
                    System.out.println();
                    break;
                case "5":
                    buyerSeller();
                    System.out.println();
                    System.out.println();
                    break;

                case "Q":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not in the possible choice, please try again");
            }

        }

    }

}