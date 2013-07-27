package at.nullpointer.issue2github.issue2github.commandline;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Provides all functions necessary to handle the commandLine options
 * 
 * @author Thomas Pummer
 * 
 */
public class OptionManager {

    /**
     * Applicationname
     */
    private static final String APP_NAME = "issue2github";


    /**
     * Extractes the connection Data from the command line
     * 
     * @param cmd
     * @return ConnectionData Object
     */
    public ConnectionData extractConnectionData( final CommandLine cmd ) {

        ConnectionData data = new ConnectionData();

        String tokenValue = cmd.getOptionValue( OptionConstants.TOKEN_OPTION_NAME );
        data.setToken( tokenValue );

        String userValue = cmd.getOptionValue( OptionConstants.USER_OPTION_NAME );
        data.setUser( userValue );

        String repositoryValue = cmd.getOptionValue( OptionConstants.REPO_OPTION_NAME );
        data.setRepository( repositoryValue );

        String mantisurlValue = cmd.getOptionValue( OptionConstants.MURL_OPTION_NAME );
        data.setMantisurl( mantisurlValue );

        String fileValue = cmd.getOptionValue( OptionConstants.FILE_OPTION_NAME );
        data.setFile( fileValue );

        return data;
    }


    /**
     * Prepares the options
     * 
     * @return Options
     */
    @SuppressWarnings( "static-access" )
    public Options prepareOptions() {

        Option optionToken = OptionBuilder.withArgName( OptionConstants.TOKEN_ARG_NAME ).hasArg()
                .withDescription( OptionConstants.TOKEN_DESCRIPTION ).isRequired()
                .create( OptionConstants.TOKEN_OPTION_NAME );
        Option optionUser = OptionBuilder.withArgName( OptionConstants.USER_ARG_NAME ).hasArg()
                .withDescription( OptionConstants.USER_DESCRIPTION ).isRequired()
                .create( OptionConstants.USER_OPTION_NAME );
        Option optionRepository = OptionBuilder.withArgName( OptionConstants.REPO_ARG_NAME ).hasArg()
                .withDescription( OptionConstants.REPO_DESCRIPTION ).isRequired()
                .create( OptionConstants.REPO_OPTION_NAME );
        Option optionUrl = OptionBuilder.withArgName( OptionConstants.MURL_ARG_NAME ).hasArg()
                .withDescription( OptionConstants.MURL_DESCRIPTION ).isRequired()
                .create( OptionConstants.MURL_OPTION_NAME );
        Option optionFile = OptionBuilder.withArgName( OptionConstants.FILE_ARG_NAME ).hasArg()
                .withDescription( OptionConstants.FILE_DESCRIPTION ).isRequired()
                .create( OptionConstants.FILE_OPTION_NAME );

        // create Options object
        Options options = new Options();

        options.addOption( optionToken );
        options.addOption( optionUser );
        options.addOption( optionRepository );
        options.addOption( optionUrl );
        options.addOption( optionFile );

        return options;
    }


    /**
     * Parses the args if there are options
     * 
     * @param options
     * @param args
     * @return CommandLine
     * @throws ParseException
     */
    public CommandLine parseOptions( final Options options, final String... args )
            throws ParseException {

        CommandLineParser parser = new BasicParser();

        CommandLine cmd = parser.parse( options, args );

        return cmd;

    }


    /**
     * Prints instructions how the program should be used
     * 
     * @param options
     * @param exception
     */
    public void printUsage( final Options options, final ParseException exception ) {

        System.out.println( exception.getMessage() );
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( APP_NAME, options, true );
    }

}
