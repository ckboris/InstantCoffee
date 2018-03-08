package Pojo;

/**
 *
 * @author Candace
 */
public enum Roast {
    LIGHT("Light") {
        @Override
        public String toString() {
            return "Light";
        }
    },
    DARK("Dark") {
        @Override
        public String toString() {
            return "Dark";
        }
    },
    MEDIUM("Medium") {
        @Override
        public String toString() {
            return "Medium";
        }
    };
    
    private final String label;
    
    /**
     * Constructor for Brew enum.
     * 
     * @param label The text associated with a given enum. 
     */
    Roast(String label) {
        this.label = label;
    }
    
    /**
     * Getter for text field.
     * 
     * @return The text associated with this enum.
     */
    public String getLabel() {
        return this.label;
    }
}
