Building from sources
=====================

Prerequisites
-------------

### JFlex plugin setup

* Install the [JFlex plugin](http://plugins.intellij.net/plugin/?id=263) for IntelliJ IDEA.
* Checkout IntelliJ IDEA Community Edition project somewhere:

        git clone git://git.jetbrains.org/idea/community.git

* Configure the plugin as follows:

         ![JFlex plugin setup](http://i.imgur.com/Ai4qe.png)


### IntelliJ IDEA Plugin SDK setup

* Create a new *Intellij IDEA Plugin SDK*
* Edit it and add `IDEA_HOME/lib/idea.jar` to its classpath.


Project setup
-------------

* Clone the project:

        git clone git://github.com/nicoulaj/idea-byteman.git

* Open the project (*File > Open project*).
* Open the module settings (*Ctrl + Alt + Shift + S*).
* Setup the project SDK with your *Intellij IDEA Plugin SDK*.
