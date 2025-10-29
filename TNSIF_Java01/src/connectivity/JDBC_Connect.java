package connectivity;
import java.sql.*;
public class JDBC_Connect {
public static void main(String[] args) {
		
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "ragavi@04";
		
		
		try {
			
			//Load the driver - 2nd step
			Class.forName("org.postgresql.Driver");
			
			
			//Establishing the connection - 3rd step
			
			Connection con = DriverManager.getConnection(url, username, password);
			
			//Define SQL Query - 4th step
			
			Statement st = con.createStatement();
			
			//Execting the query - 5th step
			
			
			String query = "SELECT * FROM student_portal";
			
			//Process the Result - 6th Step
			
			ResultSet rs = st.executeQuery(query);
			
			
			while(rs.next())
				
			{
				String table =  rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getInt(4) + ":" + rs.getDate(5) + ":" + rs.getString(6) + ":" + rs.getString(7);
				System.out.println(table);
			}

			
			//close the resources - 7th
			
			rs.close();
			st.close();
			con.close();
	
			}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
}
