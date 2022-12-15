import java.io.Console;
import java.sql.*;
import java.util.Scanner;


public class Main {
    static String userNameSql = "root";
    static String passwordSql = "Vanille987";
    static String urlSql = "jdbc:mysql://localhost:3306/project";
    static Scanner sc = new Scanner(System.in);
    static Console cn = System.console();


    public static void executeStatement(String statement, Connection con) throws SQLException {

        try (Statement stmt = con.createStatement()) {
            stmt.execute(statement);


        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }

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
            System.out.println(e);
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

    public static void showMetaData(ResultSet rs) throws SQLException {
        //show the columns names
        ResultSetMetaData rsMetaData = rs.getMetaData();
        System.out.println("List of column names in the current table: ");
        //Retrieving the list of column names
        int count = rsMetaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }
    }

    public static boolean isUserInDatabase(String mail, String pw, Connection con) throws SQLException {
        String query = "select count(user_id) from users where users.email=\"" + mail + "\" && users.pw=\"" + pw + "\";";
        int number = 0;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);


            rs.next();
            number = rs.getInt("count(user_id)");


        } catch (SQLException e) {
            System.out.println(e);
            //JDBCTutorialUtilities.printSQLException(e);
        }

        return number >= 1;


    }


    public static void main(String[] args) throws SQLException {

        urlSql = args[0];
        userNameSql = args[1];
        passwordSql = args[2];
        System.out.println(urlSql);
        System.out.println(userNameSql);
        System.out.println(passwordSql);

        Connection con = DriverManager.getConnection(urlSql, userNameSql, passwordSql);
        executeStatement("use project", con);


        System.out.println("----< Login menu >\n" +
                "----(1) Login.\n" +
                "----(2) Sign up.\n" +
                "----(3) Login as Administrator.\n" +
                "----(4) Quit");


        outer:
        while (true) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    boolean notOkay1 = true;
                    while (notOkay1) {
                        System.out.println("----< Login >\n" +
                                "---- email:  \n" +
                                "---- password:");
                        sc.nextLine();

                        String email = sc.nextLine();
                        String pw = sc.nextLine();
                        if (isUserInDatabase(email, pw, con)) {
                            System.out.println("You're connected !");
                            normalUser user = new normalUser(email, pw, con);
                            user.secondLevelMenu();
                            notOkay1 = false;

                        } else {
                            System.out.println("Wrong connection Id please try again");
                        }

                    }


                    break;
                case 2:
                    boolean notOkay = true;
                    while (notOkay) {
                        System.out.println("----< Sign up >\n" +
                                "---- first name:\n" +
                                "---- last name:\n" +
                                "---- email:\n" +
                                "---- password:\n" +
                                "---- Is everything well entered ? Y/N");
                        System.out.println("Please enter each information one by one and press enter");
                        sc.nextLine();

                        String firstName = sc.nextLine();

                        String lastName = sc.nextLine();
                        String email = sc.nextLine();
                        String password = sc.nextLine();
                        String isOk = (String) sc.nextLine().toString();


                        if (isOk.equals("Y")) {
                            notOkay = false;
                            int id = getLastId("users", con) + 1;
                            String statement = "insert into users Values(" + id + ",\"" + firstName + "\",\"" + lastName + "\",\"" + email + "\",\"" + password +
                                    "\"," + 0 + "," + 0 + "," + 0 + "," + 0 + ");";
                            System.out.println(statement);
                            executeStatement(statement, con);
                            System.out.println("You have been registered");
                        }
                    }

                    break;
                case 3:
                    boolean notOkay2 = true;
                    while (notOkay2) {
                        System.out.println("----< Login as administrator >\n" +
                                "---- email:  \n" +
                                "---- password:");
                        sc.nextLine();
                        System.out.println("Please enter the mail");
                        String email = sc.nextLine();
                        System.out.println("Please enter the password");
                        String pw = sc.nextLine();
                        if (email.equals("admin@admin") && pw.equals("admin")) {
                            System.out.println("You're connected as administrator !");
                            adminUser admin = new adminUser(con);
                            admin.menuAdmin();
                            notOkay2 = false;

                        } else {
                            System.out.println("Wrong connection Id please try again");
                        }

                    }
                    break;
                case 4:
                    break outer;
                default:
                    System.out.println("Please type again:");
                    break;

            }
        }
        System.out.println("Bye !");
        con.close();


    }

}