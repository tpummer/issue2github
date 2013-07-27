package at.nullpointer.issue2github.issue2github.mantisissue;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MantisBTIssue Representation
 * 
 * @author Thomas Pummer
 * 
 */
@Data
@NoArgsConstructor( access = AccessLevel.PUBLIC )
public class MantisBtIssue {

    /**
     * Issue id
     */
    private Integer id;
    /**
     * Issue priority
     */
    private PriorityMapping priority;
    /**
     * Issue severity
     */
    private SeverityMapping severity;
    /**
     * Issue product version
     */
    private String productVersion;
    /**
     * Issue summary
     */
    private String summary;
    /**
     * Issue body
     */
    private String body;
    /**
     * Issue status
     */
    private StatusMapping status;
    /**
     * Issue resolution
     */
    private ResolutionMapping resolution;
    /**
     * Issue milestone
     */
    private String milestone;
    /**
     * Issue submitted date
     */
    private String dateSubmitted;
    /**
     * Issue last action date
     */
    private String dateLastAction;
    /**
     * Issue comment
     */
    private String comment;

}
