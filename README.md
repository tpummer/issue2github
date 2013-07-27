issue2github
============

This tool reads issues from a mantisbt exported csv file, adds the body from the mantisbt online, converts it to a representation of a github issue and finally transferes it to a github repository

usage: issue2github -f &lt;file&gt; -m &lt;mantisurl&gt; -r &lt;repository&gt; -t &lt;token&gt; -u
       &lt;user&gt;       
* -f &lt;file&gt;         file where the cvs data of the mantis issues can be found. you can export it in your mantisbt installation                   
* -m &lt;mantisurl&gt;    mantisbt url where the body of the issues can be retrieved                   
* -r &lt;repository&gt;   repository for the issues 
* -t &lt;token&gt;        token for github account 
* -u &lt;user&gt;         username of github account
