package Admissions.admins;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Admission.courses.Course;
import Admissions.Student.StudentRegistration;

public class AdminInterface {

	
	

	public void AllocateCourse() {
		try(SessionFactory sessionfac = new Configuration().configure()
				.addAnnotatedClass(StudentRegistration.class).addAnnotatedClass(Admin.class).buildSessionFactory()){
		try(Session sess = sessionfac.openSession();) {
		List<StudentRegistration> StuList = sess.createQuery("FROM StudentRegistration ORDER BY Intermediate_Marks DESC",StudentRegistration.class).list();
		List<Course> courses = sess.createQuery("FROM Course", Course.class).list();

		// Initialize arrays to store the values
		int[] maxSeats = new int[courses.size()];
		int[] Seatsalocate = new int[courses.size()];
		int[] availableSeats = new int[courses.size()];
		String[] departments= new String[courses.size()];
		// Populate the arrays with data from the courses
		for (int i = 0; i < courses.size(); i++) {
		    Course course = courses.get(i);
		    maxSeats[i] = course.getTotalSeats();
		    Seatsalocate[i] = course.getAllocatedSeats();
		    departments[i]=course.getCourseCode();
		}
		
		
			Transaction transaction = null;
		
		    transaction = sess.beginTransaction();
//1 in 10
		    for (StudentRegistration Stud : StuList) {
		        Admin Ad = new Admin();
		        Ad.setRegID(Stud.getRegID());
		        Ad.setInterMark(Stud.getIntermediate_Marks());
		        Ad.setPriority_1(Stud.getPriority().getPriority1());
		        Ad.setPriority_2(Stud.getPriority().getPriority2());
		        Ad.setPriority_3(Stud.getPriority().getPriority3());

		        String[] priorities = {
		            Stud.getPriority().getPriority1(),
		            Stud.getPriority().getPriority2(),
		            Stud.getPriority().getPriority3()
		        };

		        Ad.setAllocatedSeat("No seat Allocated"); // Default value
// 3 in 3
		        // Iterate through priorities  //[ECE  CSE EE] [3,3,3] [0,1,0]
		        for (String priority : priorities) {
		            // Iterate through departments
		            for (int i = 0; i < departments.length; i++) {
		                if (priority.equalsIgnoreCase(departments[i]) && maxSeats[i] > Seatsalocate[i]) {
		                    Ad.setAllocatedSeat(departments[i]);
		                    Seatsalocate[i]++;
		                    System.out.println("Allocated seat in " + departments[i] + " for studentID" + Stud.getRegID());
		                    break; // Exit the loop once a seat is allocated
		                }
		            }
		            if (!Ad.getAllocatedSeat().equals("No seat Allocated")) {
		                break; // Exit the priority loop once a seat is allocated
		            }
		        }

		        sess.save(Ad);
		        
		    }
		    for(int i =0 ;i<Seatsalocate.length;i++) {
		    	 Course course = courses.get(i);
		    	 course.setAllocatedSeats(Seatsalocate[i]);
		    	 course.setAvailableSeats(availableSeats[i]);
		    	 sess.save(course);
		    }

		    transaction.commit();
		} catch (Exception e) {
		    e.printStackTrace(); // Handle the exception appropriately
		}
		}
	}
	public  void displayAllocateSeats() {
		 // Create Hibernate configuration and session factory
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml"); // Load your Hibernate configuration file

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // Create a new session
            try (Session session = sessionFactory.openSession()) {
                // Fetch data using Hibernate query (replace "YourEntity" with your entity class name)
                List<Admin> results = session.createQuery("from Admin", Admin.class).list();

                // Print the table header
                System.out.println("+-------+-----------+------------+------------+------------+-------------------+");
                System.out.println("| RegID | InterMark | Priority_1 | Priority_2 | Priority_3 | AllocatedSeat     |");
                System.out.println("+-------+-----------+------------+------------+------------+-------------------+");

                // Iterate through the results and print each row
                for (Admin entity : results) {
                    System.out.printf("| %5d | %9.2f | %-10s | %-10s | %-10s | %-17s |%n",
                            entity.getRegID(), entity.getInterMark(),
                            entity.getPriority_1(), entity.getPriority_2(), entity.getPriority_3(),
                            entity.getAllocatedSeat());
                }
                System.out.println("+-------+-----------+------------+------------+------------+-------------------+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public void Courses() {

		// Create Hibernate configuration and session factory
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml"); // Load your Hibernate configuration file

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // Create a new session
            try (Session session = sessionFactory.openSession()) {
                // Fetch data using Hibernate query (replace "Course" with your entity class name)
                List<Course> courses = session.createQuery("FROM Course", Course.class).list();

                // Print table structure
                System.out.println("+----+------------+------------------------------------------+------------+----------------+----------------+");
                System.out.println("| id | courseCode | courseName                               | totalSeats | availableSeats | allocatedSeats |");
                System.out.println("+----+------------+------------------------------------------+------------+----------------+----------------+");

                // Iterate through the result list and print each row
                for (Course course : courses) {
                    System.out.printf("| %2d | %-10s | %-40s | %10d | %14d | %14d |\n",
                            course.getId(),
                            course.getCourseCode(),
                            course.getCourseName(),
                            course.getTotalSeats(),
                            course.getAvailableSeats(),
                            course.getAllocatedSeats());
                }

                // Print table footer
                System.out.println("+----+------------+------------------------------------------+------------+----------------+----------------+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

