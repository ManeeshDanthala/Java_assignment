import java.util.*;
public class Assignment{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        //taking number of events input
        int n = sc.nextInt();
        //priority queue
        PriorityQueue<Student> pq = new PriorityQueue<Student>(n,new customComparator());
        //line escape buffer
        sc.nextLine();
        for(int i=0;i<n;i++){
            //taking line input and splitting
            String temp[] = sc.nextLine().split(" ");
            //if event is ENTER
            if(temp[0].equalsIgnoreCase("ENTER"))
            //add record to queue
                pq.add(new Student(temp[1],Double.parseDouble(temp[2]),Integer.parseInt(temp[3])));
            else{
                //if SERVED event but queue is empty due to wrong order of events so cant remove student record
                if(pq.isEmpty()){
                    System.out.println("EMPTY queue, Cant be served with empty queue. Please check events order");
                    continue;
                }
                //if SERVED event and queue not empty then remove record
                pq.poll();
            }
        }
        if(pq.isEmpty())
            //if queue is empty then print empty
            System.out.println("EMPTY");
        else
            //else print all records in queue
            while(!pq.isEmpty())
                System.out.println(pq.poll().getName());
    }
    //method for comparing strings order of precedence
    public static int compareStrings(String str1,String str2){
        for(int i=0;i<str1.length()&&i<str2.length();i++){
            //when any letter mismatches
            if(str1.charAt(i)!=str2.charAt(i)){
                if(str1.charAt(i)<str2.charAt(i))
                    return 1;
                else
                    return 2;
            }
        }
        //if loop ends without return means both are equal but then 2 possibilities 
        //1.Both strings equal length
        if(str1.equals(str2))
            return 0;
        //2.two strings have unequal length but they are equlal till short string length example: sun,sunny
        else if(str1.length()<str2.length())
            return 1;
        else return 2;
    }
}
//custom student class
class Student{
    //members
    private String name;
    private double cgpa;
    private int token;
    //override toString
    public String toString(){
        return (name+" "+cgpa+" "+token);
    }
    //constructor
    Student(String name,double cgpa,int token){
        this.token=token;
        this.cgpa=cgpa;
        this.name=name;
    }
    //getter methods
    public String getName(){
        return name;
    }
    public double getCgpa(){
        return cgpa;
    }
    public int getToken(){
        return token;
    }
}
//comparator for ordering in priority queue
class customComparator implements Comparator<Student>{
    public int compare(Student s1,Student s2){
        //check for cgpa
        if(s1.getCgpa()<s2.getCgpa())
            return 1;
        else if(s1.getCgpa()>s2.getCgpa())
            return -1;
        else{
            //check for names if cgpa is same
            int p = Assignment.compareStrings(s1.getName(),s2.getName());
            if(p==1)
                return -1;
            else if(p==2)
                return 1;
            else{
                //check for token if cgpa and name are same
                if(s1.getToken()<s2.getToken())
                    return -1;
                else
                    return 1;
            }
        }
    }
}



