package crawler;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisInsert { 

   public static void main(String args[]) throws IOException{
      
      Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
      SqlSession session = sqlSessionFactory.openSession();
      
      //Create a new student object
      Student student = new Student("Mohammad","It", 80, 984803322, "Mohammad@gmail.com" ); 
            
      //Insert student data      
      //session.insert("Student.insert", student);
      
      List<Student> stu=session.selectList("Student.getAll");
      
      for(Student st : stu ){    	  
          System.out.println(st.getId());
          System.out.println(st.getName());
          System.out.println(st.getBranch());
          System.out.println(st.getPercentage());         
          System.out.println(st.getEmail());        
          System.out.println(st.getPhone());   
       }  
      System.out.println("record read successfully");
      session.commit();
      session.close();
			
   }
   
}