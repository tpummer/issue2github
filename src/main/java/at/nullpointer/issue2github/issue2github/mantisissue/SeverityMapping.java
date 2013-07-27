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
public enum SeverityMapping {
    /**
     * Severity: block
     */
    BLOCK( "block", "regression" ),
    /**
     * Severity: feature
     */
    FEATURE( "feature", "feature" ),
    /**
     * Severity: major
     */
    MAJOR( "major", "regression" ),
    /**
     * Severity: minor
     */
    MINOR( "minor", "regression" ),
    /**
     * Severity: tweak
     */
    TWEAK( "tweak", "tweak" );

    /**
     * Name of mantisbt severity
     */
    @Getter
    private String name;

    /**
     * Name of github label
     */
    @Getter
    private String label;


    /**
     * Retrieves Severity
     * 
     * @param name
     * @return severity
     */
    public static SeverityMapping getByName( String name ) {

        SeverityMapping result = null;

        for ( SeverityMapping severity : values() ) {
            String severityName = severity.getName();
            if ( severityName.equals( name ) ) {
                result = severity;
            }
        }
        return result;
    }

}
