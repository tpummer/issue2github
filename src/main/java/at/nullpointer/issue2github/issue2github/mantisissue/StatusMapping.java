package at.nullpointer.issue2github.issue2github.mantisissue;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.eclipse.egit.github.core.service.IssueService;

/**
 * Status States
 * 
 * @author Thomas Pummer
 * 
 */
@AllArgsConstructor
public enum StatusMapping {
    /**
     * Status: Assigned
     */
    ASSIGNED( "assigned", IssueService.STATE_OPEN ),
    /**
     * Status: closed
     */
    CLOSED( "closed", IssueService.STATE_CLOSED ),
    /**
     * Status: confirmed
     */
    CONFIRMED( "confirmed", IssueService.STATE_OPEN ),
    /**
     * Status: feedback
     */
    FEEDBACK( "feedback", IssueService.STATE_OPEN ),
    /**
     * Status: new
     */
    NEW( "new", IssueService.STATE_OPEN );

    /**
     * Name of mantisbt status
     */
    @Getter
    private String name;

    /**
     * State in github
     */
    @Getter
    private String state;


    /**
     * Retrieves status
     * 
     * @param name
     * @return status
     */
    public static StatusMapping getByName( String name ) {

        StatusMapping result = null;

        for ( StatusMapping status : values() ) {
            String statusName = status.getName();
            if ( statusName.equals( name ) ) {
                result = status;
            }
        }
        return result;
    }
}
