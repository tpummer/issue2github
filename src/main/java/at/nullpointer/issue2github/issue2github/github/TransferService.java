package at.nullpointer.issue2github.issue2github.github;

import java.io.IOException;
import java.util.Map;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Milestone;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.MilestoneService;

import at.nullpointer.issue2github.issue2github.mantisissue.MantisBtIssue;
import at.nullpointer.issue2github.issue2github.mantisissue.StatusMapping;

/**
 * Service for handling the transfer issues to github
 * 
 * @author Thomas Pummer
 * 
 */
public class TransferService {

    private transient final IssueService issueService;
    private transient final String userParam;
    private transient final String repositoryParam;
    private transient final GithubIssueBuilder githubIssueBuilder;


    /**
     * Creates an TransferService
     * 
     * @param milestoneMap
     * @param issueService
     * @param milestoneService
     * @param userParam
     * @param repositoryParam
     * @param repository
     */
    public TransferService( final Map<String, Milestone> milestoneMap, final IssueService issueService,
            final MilestoneService milestoneService, final String userParam, final String repositoryParam,
            final Repository repository ) {

        super();
        this.issueService = issueService;
        this.userParam = userParam;
        this.repositoryParam = repositoryParam;

        GithubIssueBuilder githubIssueBuilder = new GithubIssueBuilder( milestoneMap, milestoneService, this.userParam,
                this.repositoryParam, repository );
        this.githubIssueBuilder = githubIssueBuilder;
    }


    /**
     * Transfers the issue
     * 
     * @param mantisIssue
     * @throws IOException
     */
    public void transferToGithub( final MantisBtIssue mantisIssue )
            throws IOException {

        Issue createdIssue = createIssue( mantisIssue );

        createdIssue = closeIssueIfNecessary( mantisIssue, createdIssue );

        addCommentToIssueIfNecessary( mantisIssue, createdIssue );

        System.out.println( "Transfered: " + mantisIssue.getId() );

    }


    /**
     * if comment exists, create comment
     * 
     * @param mantisIssue
     * @param createdIssue
     * @throws IOException
     */
    private void addCommentToIssueIfNecessary( final MantisBtIssue mantisIssue, Issue createdIssue )
            throws IOException {

        String comment = mantisIssue.getComment();
        if ( comment != null && comment.length() > 0 ) {
            this.issueService.createComment( this.userParam, this.repositoryParam, createdIssue.getNumber(), comment );
        }
    }


    /**
     * if issue is closed, close it
     * 
     * @param mantisIssue
     * @param createdIssue
     * @return
     * @throws IOException
     */
    private Issue closeIssueIfNecessary( final MantisBtIssue mantisIssue, Issue createdIssue )
            throws IOException {

        StatusMapping status = mantisIssue.getStatus();
        String state = status.getState();
        if ( state.equals( IssueService.STATE_CLOSED ) ) {
            createdIssue.setState( IssueService.STATE_CLOSED );
            createdIssue = this.issueService.editIssue( this.userParam, this.repositoryParam, createdIssue );
        }
        return createdIssue;
    }


    /**
     * Creates the issue on github
     * 
     * @param mantisIssue
     * @return
     * @throws IOException
     */
    private Issue createIssue( final MantisBtIssue mantisIssue )
            throws IOException {

        Issue issue = githubIssueBuilder.prepareGithubIssue( mantisIssue );

        Issue createdIssue = this.issueService.createIssue( userParam, repositoryParam, issue );

        return createdIssue;
    }

}
