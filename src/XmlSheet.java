
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;




@WebServlet("/xmlsheet")
public class XmlSheet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public XmlSheet() {
        super();
       
    }
  

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String filename="C:/Users/nixinn/hi.xls";
		HSSFWorkbook bo=new HSSFWorkbook();
		HSSFSheet sheet=bo.createSheet("new sheet");
		HSSFRow rowhead=sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("id");
		rowhead.createCell(1).setCellValue("name");
		rowhead.createCell(2).setCellValue("email");
		rowhead.createCell(3).setCellValue("subject");
		rowhead.createCell(4).setCellValue("phone");
		rowhead.createCell(5).setCellValue("textarea");
		Connection con=Dbconnection.getMySqlConnection();
		String sql="select id name email subject phone textareafrom interior.exterior ";
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int i=1;
			while(rs.next()){
			
			HSSFRow row=    sheet.createRow((short)i);
			row.createCell(0).setCellValue(Integer.toString(rs.getInt("id")));
			row.createCell(1).setCellValue(rs.getString("name"));
			row.createCell(2).setCellValue(rs.getString("email"));
			row.createCell(3).setCellValue(rs.getString("subject"));
			row.createCell(4).setCellValue(rs.getString("phone"));
			row.createCell(5).setCellValue(rs.getString("textarea"));
			
			i++;
			}
			FileOutputStream fileOut =  new FileOutputStream(filename);
			bo.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

			}catch(Exception e){}
		
	}

	

}