package at.nullpointer.issue2github.issue2github;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import at.nullpointer.issue2github.issue2github.commandline.ConnectionData;
import at.nullpointer.issue2github.issue2github.commandline.OptionManager;
import at.nullpointer.issue2github.issue2github.github.IssueToEGitHub;

/**
 * Issue2Github transferes issues from a mantisbt installation to a github repository tested with 1.2.15
 * 
 * @author Thomas Pummer
 * 
 */
public class Issue2Git {

    /**
     * Prepares the transfer
     * 
     * @param args
     * @throws ParseException
     */
    public static void main( final String... args )
            throws ParseException {

        Issue2Git issue2git = new Issue2Git();
        issue2git.run( args );
    }


    /**
     * Prepares the transfer
     * 
     * @param args
     */
    public void run( final String... args ) {

        OptionManager optionManager = new OptionManager();

        Options options = optionManager.prepareOptions();

        try {
            CommandLine cmd = optionManager.parseOptions( options, args );

            ConnectionData data = optionManager.extractConnectionData( cmd );

            IssueToEGitHub issue2github = new IssueToEGitHub( data );
            issue2github.run( data.getFile() );

            System.out.println( "Done" );

        } catch ( ParseException e ) {
            optionManager.printUsage( options, e );
        } catch ( IOException e ) {
            System.out.println( e.getClass().toString() + ": " + e.getMessage() );
        }

    }

}
