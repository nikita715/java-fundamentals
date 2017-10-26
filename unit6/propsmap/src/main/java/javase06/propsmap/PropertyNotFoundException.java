package javase06.propsmap;

public class PropertyNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Property not found";
    }
}
