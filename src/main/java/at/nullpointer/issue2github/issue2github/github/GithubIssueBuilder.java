package at.nullpointer.issue2github.issue2github.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Label;
import org.eclipse.egit.github.core.Milestone;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.MilestoneService;

import at.nullpointer.issue2github.issue2github.mantisissue.MantisBtIssue;
import at.nullpointer.issue2github.issue2github.mantisissue.PriorityMapping;
import at.nullpointer.issue2github.issue2github.mantisissue.SeverityMapping;

/**
 * GithubIssueBuilder transforms an MantisIssue to an GithubIssue
 * 
 * @author Thomas Pummer
 * 
 */
public class GithubIssueBuilder {

    private transient final Map<String, Milestone> milestoneMap;
    private transient final MilestoneService milestoneService;
    private transient final String userParam;
    private transient final String repositoryParam;
    private transient final Repository repository;


    /**
     * Creats and initializes an GithubIssueBuilder
     * 
     * @param milestoneMap
     * @param milestoneService
     * @param userParam
     * @param repositoryParam
     * @param repository
     */
    public GithubIssueBuilder( final Map<String, Milestone> milestoneMap, final MilestoneService milestoneService,
            final String userParam, final String repositoryParam, final Repository repository ) {

        this.milestoneMap = milestoneMap;
        this.milestoneService = milestoneService;
        this.userParam = userParam;
        this.repositoryParam = repositoryParam;
        this.repository = repository;
    }


    /**
     * Creates an transferable GithubIssue
     * 
     * @param mantisIssue
     * @return an Issue
     * @throws IOException
     */
    public Issue prepareGithubIssue( final MantisBtIssue mantisIssue )
            throws IOException {

        Issue issue = new Issue();
        issue.setBody( mantisIssue.getBody() );

        List<Label> labels = new ArrayList<Label>();

        // priority
        PriorityMapping priority = mantisIssue.getPriority();
        if ( priority.getLabel() != null ) {
            Label labelPriority = new Label();
            labelPriority.setName( priority.getLabel() );
            labels.add( labelPriority );
        }

        // severity
        Label labelSeverity = new Label();
        SeverityMapping severity = mantisIssue.getSeverity();
        labelSeverity.setName( severity.getLabel() );
        labels.add( labelSeverity );

        issue.setLabels( labels );

        // milestones
        Milestone milestone = this.milestoneMap.get( mantisIssue.getMilestone() );
        if ( milestone == null && mantisIssue.getMilestone() != null && mantisIssue.getMilestone().length() > 0 ) {
            Milestone newMilestone = new Milestone();
            newMilestone.setTitle( mantisIssue.getMilestone() );
            Milestone createdMilestone = this.milestoneService.createMilestone( this.userParam, this.repositoryParam,
                    newMilestone );
            this.milestoneMap.put( mantisIssue.getMilestone(), createdMilestone );
            milestone = createdMilestone;
        }

        issue.setMilestone( milestone );
        issue.setState( IssueService.STATE_OPEN );
        issue.setTitle( mantisIssue.getSummary() );
        issue.setAssignee( this.repository.getOwner() );
        return issue;
    }

}
