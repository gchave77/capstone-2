Object Oriented Programming Concept Questions

As you should know by now, there are 4 pillars of Object Oriented Programming.

********************
1. Encapsulation
Encapsulation in Java is a process of wrapping code and data together into a single unit, like a capsule which is mixed of several medicines as mentioned by Sarah Bates. 
The whole idea behind encapsulation is to hide the implementation details from users. If a data member is private it means it can only be accessed within the same class. No outside class can access private data member (variable) of another class. ... That's why encapsulation is known as data hiding.

How to implement encapsulation in java:
a) Make the instance variables private so that they cannot be accessed directly from outside the class. You can only set and get values of these variables through the methods of the class.
b) Have getter and setter methods in the class to set and get the values of the fields.

    class EncapsulationDemo{
        private int ssn;
        private String empName;
        private int empAge;
    
        //Getter and Setter methods
    
        public int getEmpSSN(){
            return ssn;
        }
    
        public String getEmpName(){
            return empName;
        }
    
        public int getEmpAge(){
            return empAge;
        }
    
        public void setEmpAge(int newValue){
            empAge = newValue;
        }
    
        public void setEmpName(String newValue){
            empName = newValue;
        }
    
        public void setEmpSSN(int newValue){
            ssn = newValue;
        }
    }
    public class EncapsTest{
        public static void main(String args[]){
             EncapsulationDemo obj = new EncapsulationDemo();
             obj.setEmpName("Mario");
             obj.setEmpAge(32);
             obj.setEmpSSN(112233);
             System.out.println("Employee Name: " + obj.getEmpName());
             System.out.println("Employee SSN: " + obj.getEmpSSN());
             System.out.println("Employee Age: " + obj.getEmpAge());
        } 
    }

Advantages of encapsulation
It improves maintainability and flexibility and re-usability: for e.g. In the above code the implementation code of void setEmpName(String name) and String getEmpName() can be changed at any point of time. Since the implementation is purely hidden for outside classes they would still be accessing the private field empName using the same methods (setEmpName(String name) and getEmpName()). Hence the code can be maintained at any point of time without breaking the classes that uses the code. This improves the re-usability of the underlying class.


********************
2. Inheritance

Inheritance Example
In this example, we have a base class Teacher and a sub class PhysicsTeacher. Since class PhysicsTeacher extends the designation and college properties and work() method from base class, we need not declare these properties and method in sub class.
Here we have collegeName, designation and work() method which are common to all the teachers so we have declared them in the base class, this way the child classes like MathTeacher, MusicTeacher and PhysicsTeacher do not need to write this code and can be used directly from base class.

    class Teacher {
       String designation = "Teacher";
       String collegeName = "Beginnersbook";
       void does(){
        System.out.println("Teaching");
       }
    }
    
    public class PhysicsTeacher extends Teacher{
       String mainSubject = "Physics";
       public static void main(String args[]){
        PhysicsTeacher obj = new PhysicsTeacher();
        System.out.println(obj.collegeName);
        System.out.println(obj.designation);
        System.out.println(obj.mainSubject);
        obj.does();
       }
    }

Based on the above example we can say that PhysicsTeacher IS-A Teacher. This means that a child class has IS-A relationship with the parent class. This type of inheritance is known as IS-A relationship between child and parent class.

The aim of inheritance is to provide the reusability of code so that a class has to write only the unique features and rest of the common properties and functionalities can be extended from the another class.


********************
3. Abstraction

Abstraction can be used with Inheritance and a process where you show only “relevant” data and “hide” unnecessary details of an object from the user. 

Or, if the Class functionality will be useful across a wide range of disparate objects, In that case, use an Interface. 

For example, when you login to your Amazon account online, you enter your user_id and password and press login. What happens when you press login, how your data is sent to amazon, and how it gets verified is all abstracted away from the you.

An abstract class can have both abstract and regular methods:

    abstract class Animal {
      public abstract void animalSound();
      public void sleep() {
        System.out.println("Zzz");
      }
    }

From the example above, it is not possible to create an object of the Animal class:

    Animal myObj = new Animal(); // will generate an error

To access the abstract class, it must be inherited from another class, like a class that defines a type of animal. 


********************
4. Polymorphism
   
Polymorphism is the capability of a method to do different things based on the object that it is acting upon. For example, lets say we have a class Animal that has a method sound(). Since this is a generic class, we can’t give it a implementation like: Roar, Meow, Oink etc. We had to give a generic message.

    public class Animal{
       ...
       public void sound(){
          System.out.println("Animal is making a sound");   
       }
    }

Now lets say we two subclasses of Animal class: Horse and Cat that extends (Inheritance) Animal class. We can provide the implementation to the same method like this:

    public class Horse extends Animal{
    ...
        @Override
        public void sound(){
            System.out.println("Neigh");
        }
    }

and

    public class Cat extends Animal{
    ...
        @Override
        public void sound(){
            System.out.println("Meow");
        }
    }

As you can see that although we had the common action for all subclasses sound(), there were different ways to do the same action. 



Please write a 1-3 paragraphs explaining these 4 concepts further.  Please provide a sufficient enough explanation about these pillars, as well as some examples to illustrate the practical use cases of these principles.  

