/**
 * This class will build a student
 * @author Zach Reents, Ezrael Powell, Roman Sepeda
 */
import java.io.Serializable;
import java.lang.*;
        
public class Student implements Comparable<Student>, Serializable {
    private String firstName;
    private String lastName;
    private char gender;
    private String major;
    
    /**
     * This method will initialize variables for the student class
     * when it is given a name, gender, and major
     * 
     * @param studentFirstName first name for the Student
     * @param studentLastName last name for the Student
     * @param studentGender gender for the Student, for sorting purposes
     * @param studentMajor  major for the Student, for sorting purposes
     */
    public Student(String studentFirstName, String studentLastName, char studentGender, 
        String studentMajor){
        firstName = studentFirstName;
        lastName = studentLastName;
        gender = studentGender;
        major = studentMajor;
    }
    
    /**
     * Returns the first name of the created student
     * 
     * @return String name of the Student
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * Returns the last name of the created student
     * 
     * @return String name of the Student
     */
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     * Returns the gender of the created student
     * 
     * @return char gender of the Student
     */
    public char getGender()
    {
        return gender;
    }
    
    /**
     * Returns the major of the created student
     * 
     * @return String major of the Student
     */    
    public String getMajor()
    {
        return major;
    }
    
    /**
     * This method will compare two students variables to one another
     * 
     * @param obj creates comparable student object
     * @return returns whether or not the students are the exact same
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        
        if (obj != null && getClass() == obj.getClass()) {
            Student other = (Student) obj;
            result = firstName.equals(other.firstName)
                    && lastName.equals(other.lastName)
                    && gender == other.gender
                    && major.equals(other.major);
        }
        return result;
    }
    
    /**
     * This method will compare two students to see if they are the same
     * 
     * @param other serves as a placeholder student object to compare with
     * @return returns whether or not the students are the exact same
     */
    public int compareTo(Student other)
    {
        int result = lastName.compareTo(other.lastName);
        if (result == 0)
            result = firstName.compareTo(other.firstName);
        else if (result == 0)
            result = major.compareTo(other.major);
        return result;
    }
    
    /**
     * returns variables in a readable string format
     * 
     * @return variables in a readable string format
     */
    public String toString()
    {
        return firstName + " " + lastName + " " + gender + " " + major;
    }
}
