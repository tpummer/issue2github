package at.nullpointer.issue2github.issue2github.commandline;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for the option handling
 * 
 * @author Thomas Pummer
 * 
 */
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class OptionConstants {

    /**
     * Option Name of MantisURL
     */
    public static final String MURL_OPTION_NAME = "m";
    /**
     * Option Description of MantisURL
     */
    public static final String MURL_DESCRIPTION = "mantisbt url where the body of the issues can be retrieved";
    /**
     * Option Arg Name of MantisURL
     */
    public static final String MURL_ARG_NAME = "mantisurl";
    /**
     * Option Name of Reposiotry
     */
    public static final String REPO_OPTION_NAME = "r";
    /**
     * Option Description of Reposiotry
     */
    public static final String REPO_DESCRIPTION = "repository for the issues";
    /**
     * Option Arg Name of Reposiotry
     */
    public static final String REPO_ARG_NAME = "repository";
    /**
     * Option Name of User
     */
    public static final String USER_OPTION_NAME = "u";
    /**
     * Option Description of User
     */
    public static final String USER_DESCRIPTION = "username of github account";
    /**
     * Option Arg Name of User
     */
    public static final String USER_ARG_NAME = "user";
    /**
     * Option Name of Token
     */
    public static final String TOKEN_OPTION_NAME = "t";
    /**
     * Option Description of User
     */
    public static final String TOKEN_DESCRIPTION = "token for github account";
    /**
     * Option Arg Name of User
     */
    public static final String TOKEN_ARG_NAME = "token";
    /**
     * Option Name of file
     */
    public static final String FILE_OPTION_NAME = "f";
    /**
     * Option Description of file
     */
    public static final String FILE_DESCRIPTION = "file where the cvs data of the mantis issues can be found. you can export it in your mantisbt installation";
    /**
     * Option Arg Name of file
     */
    public static final String FILE_ARG_NAME = "file";

}
