package connectivity;
import java.sql.*;

public class PreparedStatementDemo {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "ragavi@04";

        String first_name = "Shruthi";
        String last_name = "R";
        int age = 20;
        String dob = "2005-07-12"; // yyyy-mm-dd format
        String department = "CSE";
        String email = "shruthi@example.com";

        String query = "INSERT INTO student_portal(first_name, last_name, age, dob, department, email) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            // Load the driver - 2nd step
            Class.forName("org.postgresql.Driver");

            // Establishing the connection - 3rd step
            Connection con = DriverManager.getConnection(url, username, password);

            // Define SQL Query - 4th step
            PreparedStatement st = con.prepareStatement(query);

            // Executing the query - 5th step
            st.setString(1, first_name);
            st.setString(2, last_name);
            st.setInt(3, age);
            st.setDate(4, java.sql.Date.valueOf(dob));
            st.setString(5, department);
            st.setString(6, email);

            // Process the Result - 6th Step
            int rs = st.executeUpdate();
            System.out.println(rs + " row/s affected");

            // close the resources - 7th
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
