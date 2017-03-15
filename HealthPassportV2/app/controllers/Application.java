package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import views.html.*;

public class Application extends Controller {

	static String title="";
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result data(){
    	try{
    		MultipartFormData body = request().body().asMultipartFormData();
    	    FilePart picture = body.getFile("image");
    	    File image=picture.getFile();
			FileInputStream fis=new FileInputStream(image);
			if(fis!=null){
				DynamicForm dynamicForm = Form.form().bindFromRequest();
				title=dynamicForm.get("text1");
				String sql = "INSERT INTO details (Title,Image) VALUES (?,?)";
				fis.close();
				//Connection connection=play.db.DB.getConnection();
				//PreparedStatement stmt = connection.prepareStatement(sql);
				//stmt.setString(1,title);
				//stmt.setBinaryStream(2,  (InputStream)fis,(int)image.length());        	
				//stmt.executeUpdate();
				//fis.close();
				//stmt.close();
			}
			
    	}
    	//catch(FileNotFoundException fn){
    		//return ok("Failed");
    	//}
    	/*catch(SQLException  se){
    		return ok("SQL Exception");
    	}*/
    	catch(Exception ie){
    		return ok("IO Exception");
    	}
    	
    	return ok("File Uploaded Successfully..."+title);
    }

}
