package at.nullpointer.issue2github.issue2github.mantis;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import at.nullpointer.issue2github.issue2github.mantisissue.MantisBtIssue;

/**
 * Class is responsible for retrieving the body of an issue
 * 
 * @author Thomas Pummer
 * 
 */
public class BodyReader {

    private static final int BODY_START_LENGTH = 53;
    private static final String BODY_START = "<td class=\"category\">Description</td><td colspan=\"5\">";
    private static final char BODY_END = '<';
    private static final String TEXT_OLD_ISSUE_NUMBER = "Old Issue Number: ";
    private static final String TEXT_DATE_SUBMITTED = "Date Submitted: ";
    private static final String HTML_BREAK_LINE = "<br/>";


    /**
     * Retrieves the Body of an Issue and adds it to the issue
     * 
     * @param mantisIssue
     * @param mantisbturlParam
     * @throws ClientProtocolException
     * @throws IOException
     */
    public void addBody( final MantisBtIssue mantisIssue, final String mantisbturlParam )
            throws ClientProtocolException, IOException {

        String fullResponse = retrieveHtmlBody( mantisIssue, mantisbturlParam );
        String description = extractDescription( fullResponse );
        String result = createBody( mantisIssue, description );
        mantisIssue.setBody( result );

    }


    private String createBody( final MantisBtIssue mantisIssue, String description ) {

        StringBuffer bodyTextBuffer = new StringBuffer( 155 );

        bodyTextBuffer.append( TEXT_OLD_ISSUE_NUMBER );
        bodyTextBuffer.append( mantisIssue.getId() );
        bodyTextBuffer.append( HTML_BREAK_LINE );
        bodyTextBuffer.append( TEXT_DATE_SUBMITTED );
        bodyTextBuffer.append( mantisIssue.getDateSubmitted() );
        bodyTextBuffer.append( HTML_BREAK_LINE );
        bodyTextBuffer.append( description );

        String result = bodyTextBuffer.toString();
        return result;
    }


    private String extractDescription( String fullResponse ) {

        int indexOfBody = fullResponse.indexOf( BODY_START ) + BODY_START_LENGTH;
        int indexOfEndBody = fullResponse.indexOf( BODY_END, indexOfBody );

        String description = fullResponse.substring( indexOfBody, indexOfEndBody );
        return description;
    }


    private String retrieveHtmlBody( final MantisBtIssue mantisIssue, final String mantisbturlParam )
            throws IOException, ClientProtocolException {

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet( mantisbturlParam + mantisIssue.getId() );
        HttpResponse response = httpclient.execute( httpGet );
        HttpEntity entity = response.getEntity();
        StringWriter writer = new StringWriter();
        IOUtils.copy( entity.getContent(), writer );
        String fullResponse = writer.toString();
        EntityUtils.consume( entity );
        httpGet.releaseConnection();
        return fullResponse;
    }

}
