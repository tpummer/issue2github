package at.nullpointer.issue2github.issue2github.mantis;

import at.nullpointer.issue2github.issue2github.mantisissue.MantisBtIssue;
import at.nullpointer.issue2github.issue2github.mantisissue.ResolutionMapping;

/**
 * Handles the creaton of comments
 * 
 * @author Thomas Pummer
 * 
 */
public class CommentBuilder {

    private static final char SPACE_CHAR = ' ';


    /**
     * Creates an comment out of the data of the issue
     * 
     * @param mantisIssue
     */
    public void addComment( final MantisBtIssue mantisIssue ) {

        ResolutionMapping resolution = mantisIssue.getResolution();
        if ( resolution.getText() != null ) {
            StringBuffer commentText = new StringBuffer();

            commentText.append( resolution.getText() );
            commentText.append( SPACE_CHAR );
            commentText.append( mantisIssue.getDateLastAction() );
            mantisIssue.setComment( commentText.toString() );
        }

    }

}
