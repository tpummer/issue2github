issue2github
============

This tool reads issues from a mantisbt exported csv file, adds the body from the mantisbt online, converts it to a representation of a github issue and finally transferes it to a github repository

usage: issue2github -f <file> -m <mantisurl> -r <repository> -t <token> -u
       <user>
 -f <file>         file where the cvs data of the mantis issues can be
                   found. you can export it in your mantisbt installation
 -m <mantisurl>    mantisbt url where the body of the issues can be
                   retrieved
 -r <repository>   repository for the issues
 -t <token>        token for github account
 -u <user>         username of github account
