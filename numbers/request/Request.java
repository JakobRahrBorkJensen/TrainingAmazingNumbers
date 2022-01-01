package numbers.request;

import numbers.utils.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO class for the Request object, containing each of the expected inputs in a request
 */
public class Request {
    // Starting number (first to be investigated)
    private long number;
    // Number of results to be returned
    private int count;
    // List of properties, that each resulting number should have
    private List<Property> desiredProperties = new ArrayList<>();
    // List of properties, that the resulting numbers should not have
    private List<Property> unwantedProperties = new ArrayList<>();

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Property> getDesiredProperties() {
        return desiredProperties;
    }

    public void setDesiredProperties(List<Property> desiredProperties) {
        this.desiredProperties = desiredProperties;
    }

    public List<Property> getUnwantedProperties() {
        return unwantedProperties;
    }

    public void setUnwantedProperties(List<Property> unwantedProperties) {
        this.unwantedProperties = unwantedProperties;
    }

    /**
     * Determines if any properties has been provided in the request
     */
    public static boolean IsAnyPropertiesSpecified(Request request) {
        return request.getDesiredProperties().size() > 0 || request.getUnwantedProperties().size() > 0;
    }
}
