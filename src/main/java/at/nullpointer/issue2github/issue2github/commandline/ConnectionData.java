package at.nullpointer.issue2github.issue2github.commandline;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Value Object holding the data for connection
 * 
 * @author Thomas Pummer
 * 
 */
@Data
@NoArgsConstructor( access = AccessLevel.PUBLIC )
public class ConnectionData {

    /**
     * Github Token
     */
    String token;

    /**
     * Username
     */
    String user;

    /**
     * Repository
     */
    String repository;

    /**
     * Mantis URL
     */
    String mantisurl;

    /**
     * CVS file
     */
    String file;

}
