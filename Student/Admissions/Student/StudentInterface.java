package Admissions.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import Admissions.admins.Admin;

public class StudentInterface {

	public void RegistrationFrom(){		
		try(SessionFactory sf = new Configuration().configure().addAnnotatedClass(StudentRegistration.class).addAnnotatedClass(Admin.class).buildSessionFactory();
			    Session session = sf.openSession();
			    Scanner scanner = new Scanner(System.in)){
			StudentRegistration Student = new StudentRegistration();
			Name Studentname = new Name();
			Name Fathername = new Name();
			Address address = new Address();
			CoursePriority priority = new CoursePriority();

			//
			System.out.println("Enter Student First Name:");
			Studentname.setFirstname(scanner.nextLine());

			System.out.println("Enter Student Middle Name:");
			Studentname.setMiddlename(scanner.nextLine());


			//
			System.out.println("Enter Student Last Name:");
			Studentname.setLastname(scanner.nextLine());

			System.out.println("Enter Father's First Name:");
			Fathername.setFirstname(scanner.nextLine());

			System.out.println("Enter Father's Middle Name:");
			Fathername.setMiddlename(scanner.nextLine());

			System.out.println("Enter Father's Last Name:");
			Fathername.setLastname(scanner.nextLine());


			//
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			while (true) {
			    System.out.println("Enter date of birth (dd/MM/yyyy):");
			    String dateStr = scanner.next(); 
			    try {
			        // Parse the date string into a LocalDate object
			        Student.setDOB(LocalDate.parse(dateStr, dateFormatter));
			        break;
			    } catch (DateTimeParseException e) {
			        // Handle any parsing errors here
			        System.out.println("Invalid date of birth. Please enter a valid date of birth in the format dd/MM/yyyy.");
			    }
			}


			//
			System.out.println("Enter Gender:");
			Student.setGender(scanner.next());


			//
			Pattern patternadhar = Pattern.compile("^[0-9]{12}$");
			while(true){
				System.out.print("Enter your Indian Aadhar card number: \n");
				String aadharNumber = scanner.next();

				// Match the entered Aadhar number with the pattern
				Matcher matcher = patternadhar.matcher(aadharNumber);

				if (matcher.matches()) {
					Student.setAdhar_no(Long.parseLong(aadharNumber));
					break;
				}
				else {
					System.out.println("Invalid Indian Aadhar card number. Please enter a 12-digit numeric Aadhar number.");
				}

			}



			//
			Pattern patternphone = Pattern.compile("^[6789]\\d{9}$");
			while(true) {
				System.out.print("Enter your Indian phone number: \n");
				String phoneNumber = scanner.next();

				// Remove any spaces or hyphens from the input
				phoneNumber = phoneNumber.replaceAll("[\\s-]+", "");

				// Match the entered phone number with the pattern
				Matcher matcher = patternphone.matcher(phoneNumber);

				if (matcher.matches()) {
					Student.setPhone_No(Long.parseLong(phoneNumber));
					break;
				} 
				else {
					System.out.println("Invalid Indian phone number. Please enter a 10-digit number");
				}
			}



			//
			Pattern Emailpattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.[A-Za-z]{2,}$");

			while(true) {
				System.out.print("Enter an email address: \n");
				String emailAddress = scanner.next();

				// Match the entered email address with the pattern
				Matcher matcher = Emailpattern.matcher(emailAddress);

				if (matcher.matches()) {
					Student.setMail(emailAddress);
					break;
				} 
				else {
					System.out.println("Invalid email address. Please enter a valid email address.");
				}
			}


			System.out.println("Enter Door Number :");
			address.setAddress_Door_No(scanner.next());

			System.out.println("Enter Street Name:");
			address.setAddress_Street(scanner.next());

			System.out.println("Enter City Name: ");
			address.setAddress_City(scanner.next());

			System.out.println("Enter Pin Code:");
			address.setAddress_Pin_Code(scanner.next());


			//
			System.out.println("Enter Intermediate CGPA :");
			Student.setIntermediate_Marks( scanner.nextDouble());
			scanner.nextLine(); // Consume the newline character
			
			System.out.println("Department\t\t\t\t\t CODE");
			System.out.println("Electrical and Communication Engineering\t ECE\n"
					+ "Computer Science and Engineering\t\t CSE\n"
					+"Electrical and Electronics Engineering\t\t EEE\n"
					+"Civil Engineering\t\t\t\t CIVIL\n"
					+"Mechanical Engineering\t\t\t\t MECH");
			System.out.println("Enter Courses as Per Student Priority.Enter Code only");
			
			System.out.println("Enter Course Priority 1:");
			priority.setPriority1(scanner.nextLine());

			System.out.println("Enter Course Priority 2:");
			priority.setPriority2(scanner.nextLine());

			System.out.println("Enter Course Priority 3:");
			priority.setPriority3(scanner.nextLine());


			//
			Student.setStudentname(Studentname);
			Student.setFathername(Fathername);
			Student.setAddress(address);
			Student.setPriority(priority);


			//
			session.beginTransaction();
			session.save(Student);
			session.getTransaction().commit();

			System.out.println("Registration successful! Student ID: " + Student.getRegID());
		
		



		}
		catch(Exception e) {
		    System.out.println("Registration Failed.Please try again");
		}
		
	}
	
	public void ApplicationStatus() {
	    try (SessionFactory sf = new Configuration().configure().addAnnotatedClass(StudentRegistration.class).addAnnotatedClass(Admin.class).buildSessionFactory();
	         Session session = sf.openSession()) {

	        try(Scanner scanner = new Scanner(System.in);){
	        System.out.println("Enter Your Registration ID");
	        int regId = scanner.nextInt();

	        StudentRegistration stdRegForm = session.get(StudentRegistration.class, regId);
	        Admin admin = session.get(Admin.class, regId);

	        if (stdRegForm != null && admin != null) {
	            String formattedString = String.format(
	                "--------------Application Status-------------\n" +
	                "Registration ID    : %s%n" +
	                "Student Name       : %s %s %s%n" +
	                "Intermediate Marks : %s%n" +
	                "Priority 1         : %s%n" +
	                "Priority 2         : %s%n" +
	                "Priority 3         : %s%n" +
	                "Allocated Seat     : %s%n" +
	                "-------------------------------------------%n",
	                stdRegForm.getRegID(),
	                stdRegForm.getStudentname().getFirstname(),
	                stdRegForm.getStudentname().getMiddlename(),
	                stdRegForm.getStudentname().getLastname(),
	                stdRegForm.getIntermediate_Marks(),
	                admin.getPriority_1(),
	                admin.getPriority_2(),
	                admin.getPriority_3(),
	                admin.getAllocatedSeat()
	            );

	            // Print the formatted string
	            System.out.println(formattedString);
	        } else {
	            System.out.println("Application not found");
	        }
	    } catch (Exception ex) {
	        System.out.println("Failed to get Application");
	        ex.printStackTrace(); // Print the stack trace for debugging
	    }
	}
	    }



	}
	





