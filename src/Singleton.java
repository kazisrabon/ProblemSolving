//Assignment 11
class Singleton {

    private static Singleton singleton_instance = null;

//    static method to return only one instance
//    if the instance is not created then create an instance of the class
//    otherwise return the previously created instance
    public static Singleton Singleton(){
        if (singleton_instance == null)
            singleton_instance = new Singleton();

        return singleton_instance;
    }
}
