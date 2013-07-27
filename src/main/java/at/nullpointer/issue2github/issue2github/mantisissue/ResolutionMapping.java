package at.nullpointer.issue2github.issue2github.mantisissue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Resolution States
 * 
 * @author Thomas Pummer
 * 
 */
@AllArgsConstructor
public enum ResolutionMapping {
    /**
     * Resolution: fixed
     */
    FIXED( "fixed", "fixed" ),
    /**
     * Resolution: not fixable
     */
    NOT_FIXABLE( "not fixable", "not fixable" ),
    /**
     * Resolution: open
     */
    OPEN( "open", null ),
    /**
     * Resolution: reopened
     */
    REOPENED( "reopened", null ),
    /**
     * Resolution: suspended
     */
    SUSPENDED( "suspended", "suspended" ),
    /**
     * Resolution: unable to reproduce
     */
    UNABLE_TO_REPRODUCE( "unable to reproduce", "unable to reproduce" ),
    /**
     * Resolution: wont fix
     */
    WONT_FIX( "won't fix", "won't fix" );

    /**
     * Name of mantisbt resolution
     */
    @Getter
    private String name;

    /**
     * Text of github comment
     */
    @Getter
    private String text;


    /**
     * Retrieves resolution
     * 
     * @param name
     * @return resolution
     */
    public static ResolutionMapping getByName( final String name ) {

        ResolutionMapping result = null;

        for ( ResolutionMapping resolution : values() ) {
            String resolutionName = resolution.getName();
            if ( resolutionName.equals( name ) ) {
                result = resolution;
            }
        }
        return result;
    }
}