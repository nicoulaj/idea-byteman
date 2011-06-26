Building from sources
=====================

First of all, clone the project:

    git clone git://github.com/nicoulaj/idea-byteman.git


Setting up the project in Intellij IDEA
---------------------------------------

* Open the project (*File > Open project*).
* Open the module settings (*Ctrl + Alt + Shift + S*).
* Setup the project SDK with an *Intellij IDEA Plugin SDK*.

Now you can:

* Use the run configurations to run the plugin and JUnit tests.
* Use *Build > Prepare plugin for deployment* to generate the release package.


Building with Ant
-----------------

* Go the project root:

        cd idea-byteman

* Make sure `$IDEA_HOME` is defined:

        export IDEA_HOME=/path/to/IDEA

* Compile, run tests and generate release package:

        ant
