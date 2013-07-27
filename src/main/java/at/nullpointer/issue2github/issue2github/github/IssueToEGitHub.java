package at.nullpointer.issue2github.issue2github.github;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Milestone;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.MilestoneService;
import org.eclipse.egit.github.core.service.RepositoryService;

import at.nullpointer.issue2github.issue2github.commandline.ConnectionData;
import at.nullpointer.issue2github.issue2github.mantis.BodyReader;
import at.nullpointer.issue2github.issue2github.mantis.CVSParser;
import at.nullpointer.issue2github.issue2github.mantis.CommentBuilder;
import at.nullpointer.issue2github.issue2github.mantisissue.MantisBtIssue;

/**
 * Converts the issue and transfers it
 * 
 * @author Thomas Pummer
 * 
 */
public class IssueToEGitHub {

    private transient final String repositoryParam;
    private transient final String userParam;
    private transient final String mantisbturlParam;

    private transient final TransferService transferService;

    private transient final Map<String, Milestone> milestoneMap = new HashMap<String, Milestone>();


    /**
     * Class to convert the issue and transfers it
     * 
     * @param data
     * @throws IOException
     */
    public IssueToEGitHub( final ConnectionData data )
            throws IOException {

        this.repositoryParam = data.getRepository();
        this.userParam = data.getUser();
        this.mantisbturlParam = data.getMantisurl();

        GitHubClient client = new GitHubClient();
        client.setOAuth2Token( data.getToken() );

        RepositoryService repositoryService = new RepositoryService( client );
        MilestoneService milestoneService = new MilestoneService( client );
        IssueService issueService = new IssueService( client );

        Repository repository = repositoryService.getRepository( this.userParam, this.repositoryParam );

        TransferService transferService = new TransferService( milestoneMap, issueService, milestoneService, userParam,
                repositoryParam, repository );

        this.transferService = transferService;

        readMilestones( milestoneService );
    }


    private void readMilestones( MilestoneService milestoneService )
            throws IOException {

        List<Milestone> milestones = milestoneService.getMilestones( this.userParam, this.repositoryParam, null );

        for ( Milestone milestone : milestones ) {
            this.milestoneMap.put( milestone.getTitle(), milestone );
        }
    }


    /**
     * Start the converting process
     * 
     * @param cvsFile
     * @throws IOException
     */
    public void run( final String cvsFile )
            throws IOException {

        System.out.println( "Prepare data" );

        // readAndParseCvsFile
        CVSParser cvsParser = new CVSParser();
        List<MantisBtIssue> mantisBtIssueList = cvsParser.readIssues( cvsFile );

        BodyReader bodyReader = new BodyReader();
        CommentBuilder commentBuilder = new CommentBuilder();
        // addBodyToIssues
        for ( MantisBtIssue mantisIssue : mantisBtIssueList ) {
            bodyReader.addBody( mantisIssue, this.mantisbturlParam );
            commentBuilder.addComment( mantisIssue );
        }

        System.out.println( "Start transfer" );

        // importIssues
        for ( MantisBtIssue mantisIssue : mantisBtIssueList ) {
            transferService.transferToGithub( mantisIssue );
        }

        // Priority: high, urgent -> high
        // Severity: als tag übernehmen feature
        // resolution: wenn nicht open oder reopened dann als kommentar anhängen
        //
        // fixed in version -> milestone
        //
        //
        // title -> summary
        // Product Version into Body
        //
        // Status -> open/close

    }

}
