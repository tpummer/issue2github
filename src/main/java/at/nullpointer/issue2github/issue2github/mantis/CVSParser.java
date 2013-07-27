package at.nullpointer.issue2github.issue2github.mantis;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import at.nullpointer.issue2github.issue2github.mantisissue.MantisBtIssue;
import at.nullpointer.issue2github.issue2github.mantisissue.PriorityMapping;
import at.nullpointer.issue2github.issue2github.mantisissue.ResolutionMapping;
import at.nullpointer.issue2github.issue2github.mantisissue.SeverityMapping;
import at.nullpointer.issue2github.issue2github.mantisissue.StatusMapping;

/**
 * Handels the parsing of a cvs file
 * 
 * @author Thomas Pummer
 * 
 */
public class CVSParser {

    private static final String SEPERATOR = ",";
    private static final int POS_ID = 0;
    private static final int POS_MILESTONE = 18;
    private static final int POS_PRIORITY = 4;
    private static final int POS_PROD_VERSION = 7;
    private static final int POS_RESOLUTION = 17;
    private static final int POS_SEVERITY = 5;
    private static final int POS_STATUS = 16;
    private static final int POS_SUMMARY = 15;
    private static final int POS_DATEUPDATED = 14;
    private static final int POS_DATESUBMITTED = 9;


    /**
     * Reads the issues from a cvs file
     * 
     * @param filepath
     * @return List of issues
     * @throws IOException
     */
    public List<MantisBtIssue> readIssues( final String filepath )
            throws IOException {

        List<MantisBtIssue> result = new ArrayList<MantisBtIssue>();

        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath( filepath );
        for ( String line : Files.readAllLines( path, Charset.forName( "UTF-8" ) ) ) {
            String[] split = line.split( SEPERATOR );
            MantisBtIssue mantisIssue = new MantisBtIssue();

            mantisIssue.setId( Integer.parseInt( split[ POS_ID ] ) );
            try {
                mantisIssue.setMilestone( split[ POS_MILESTONE ] );
            } catch ( ArrayIndexOutOfBoundsException e ) {
                mantisIssue.setMilestone( null );
            }
            mantisIssue.setPriority( PriorityMapping.getByName( split[ POS_PRIORITY ] ) );
            mantisIssue.setProductVersion( split[ POS_PROD_VERSION ] );
            mantisIssue.setResolution( ResolutionMapping.getByName( split[ POS_RESOLUTION ] ) );
            mantisIssue.setSeverity( SeverityMapping.getByName( split[ POS_SEVERITY ] ) );
            mantisIssue.setStatus( StatusMapping.getByName( split[ POS_STATUS ] ) );
            mantisIssue.setSummary( split[ POS_SUMMARY ] );
            mantisIssue.setDateLastAction( split[ POS_DATEUPDATED ] );
            mantisIssue.setDateSubmitted( split[ POS_DATESUBMITTED ] );

            result.add( mantisIssue );
        }
        return result;
    }
}
