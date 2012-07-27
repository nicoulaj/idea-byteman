Building from sources
=====================

First of all, clone the project:

    git clone git://github.com/nicoulaj/idea-byteman.git


Setting up the project in Intellij IDEA
---------------------------------------

### JFlex plugin setup

* Install the [JFlex plugin](http://plugins.intellij.net/plugin/?id=263) for IntelliJ IDEA.
* Checkout IntelliJ IDEA Community Edition project somewhere:

        git clone git://git.jetbrains.org/idea/community.git

* Configure the plugin as follows:

![JFlex plugin setup](http://i.imgur.com/Ai4qe.png)

### Grammar kit plugin setup

* Install the [Grammar kit plugin](http://plugins.intellij.net/plugin?pluginId=6606) for IntelliJ IDEA.

### IntelliJ IDEA plugin SDK setup

* Open the project (*File > Open project*).
* Open the module settings (*Ctrl + Alt + Shift + S*).
* Setup the project SDK with an *Intellij IDEA Plugin SDK*.
* Edit your *Intellij IDEA Plugin SDK* and add `$IDEA_HOME/lib/idea.jar` to its classpath.

### Building

* Right-click `src/main/java/net/nicoulaj/idea/byteman/lang/parser/Byteman.bnf` and execute `Generate parser code` to update generated sources.
* Use the run configurations to run the plugin and JUnit tests.
* Use *Build > Prepare plugin for deployment* to generate the release package.


Building with Ant
-----------------

* Go the project root:

        cd idea-byteman

* Generate sources, compile, run tests and generate release package:

        ant
