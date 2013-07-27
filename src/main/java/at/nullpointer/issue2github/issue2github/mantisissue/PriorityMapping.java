package at.nullpointer.issue2github.issue2github.mantisissue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Priority States
 * 
 * @author Thomas Pummer
 * 
 */
@AllArgsConstructor
public enum PriorityMapping {
    /**
     * Normal Priority
     */
    NORMAL( "normal", null ),
    /**
     * High Priority
     */
    HIGH( "high", "high" ),
    /**
     * Urgent Priority
     */
    URGENT( "urgent", "high" );

    /**
     * Name of mantisbt priority
     */
    @Getter
    private String name;

    /**
     * Name of github label
     */
    @Getter
    private String label;


    /**
     * Retrieves Priority
     * 
     * @param name
     * @return priority
     */
    public static PriorityMapping getByName( final String name ) {

        PriorityMapping result = null;

        for ( PriorityMapping priority : values() ) {
            String priorityName = priority.getName();
            if ( priorityName.equals( name ) ) {
                result = priority;
            }
        }
        return result;
    }
}
