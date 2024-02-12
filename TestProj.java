import java.sql.*;
import java.util.Scanner;

public class TestProj {
    public static void main(String[] args) throws SQLException {
        String jdbcURL = "";
        String username = "";
        String password = "";

        try {

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Успешное подключение ");

//            String valueAdd = scanner.nextLine();
//            boolean isDataExist = isDataExits(connection,valueAdd);
//            boolean isEitherUpdateOrChange = false;

            boolean isAddOrChangeOrDelete = false;

            while (!isAddOrChangeOrDelete) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Вы хотите (удалить) или (добавить) данные ?:");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("добавить")) {
                    System.out.println("Какие данные нужно добавить ");
                    scanner = new Scanner(System.in);
                    String valueAdd = scanner.nextLine();

                    if (isDataExits(connection, valueAdd)) {
                        System.out.println("Данные есть в data base ");
                        getFromResultSet(connection, valueAdd);
                    } else {
                        System.out.println("Данные не найдены, но уже добавлены.\nСозданы новые данные.\nВведите количество штук:");
                        int quantity = scanner.nextInt();
                        insertData(connection, valueAdd, quantity);

                    }
                    System.out.println("Хотели бы изменить данные? Введите Yes или No");
                    scanner.nextLine();
                    String answereUpdate = scanner.nextLine();

                    if (answereUpdate.equalsIgnoreCase("Yes")) {
                        System.out.println("Введите какой компонент нужно изменить :");
                        scanner = new Scanner(System.in);
                        valueAdd = scanner.nextLine();
                        System.out.println("на какое имя нужно изминить ?: ");
                        scanner = new Scanner(System.in);
                        String newValue = scanner.nextLine();
                        updateData(connection, valueAdd, newValue);
                        System.out.println("изиминили");
                        isAddOrChangeOrDelete = true;

                    } else if (answereUpdate.equalsIgnoreCase("No")) {
                        isAddOrChangeOrDelete = true;
                    }

                } else if (answer.equalsIgnoreCase("удалить")) {
                    System.out.println("Что нужно удалить? :");
                    scanner = new Scanner(System.in);
                    String valueForDelete = scanner.nextLine();
                    deleteData(connection, valueForDelete);
                    System.out.println("Данные удалены");
                    isAddOrChangeOrDelete = true;
                }

            }


        } catch (SQLException e) {
            System.out.println("Ошибка пдключения");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isDataExits(Connection conn, String valueAdd) throws SQLException {
        String query = "SELECT * FROM engine_a WHERE name = ? ";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, valueAdd);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public static void getFromResultSet(Connection conn, String valueAdd) throws SQLException {
        String query = "SELECT * FROM engine_a WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, valueAdd);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int quality = resultSet.getInt("quality");
                System.out.println("name = " + name + " : " + "quality = " + quality);
            }
        }
    }


    public static void insertData(Connection conn, String addValue, int quality) throws SQLException {
        String query = "INSERT INTO engine_a (name, quality) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, addValue);
            preparedStatement.setInt(2, quality);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Данные добавленны");
            }
        }
    }

    public static void updateData(Connection conn, String addValue, String newValue) throws SQLException {
        String qyery = "UPDATE engine_a SET name = ? WHERE name =  ?";
        try (PreparedStatement statement = conn.prepareStatement(qyery)) {
            statement.setString(1, newValue);
            statement.setString(2, addValue);
            statement.executeUpdate();

        }
    }

    public static void deleteData(Connection conn, String addValueForDelete) throws SQLException {
        String query = "DELETE FROM engine_a WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, addValueForDelete);
            statement.executeUpdate();
        }
    }


}









