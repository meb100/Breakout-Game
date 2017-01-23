# Readme - Breakout: Laboratory Edition

## Author
Matthew Barbano

## Dates
- Started: 1/14/17
- Finished: 1/22/17
- Hours Spent (estimate): 40

## Roles
N/A - Individual Project

## Sources
### Online
- StackOverflow
- JavaFX 8 Documentation
- Java 8 Documentation
- ExampleBounce.java from CS 308
- https://coderanch.com/t/580998/java/remove-node
- https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=java%20can%20abstract%20class%20have%20constructor
- http://stackoverflow.com/questions/22014950/javafx-moving-image-with-arrow-keys-and-spacebar
- http://stackoverflow.com/questions/23394547/how-do-i-remove-a-scene-in-a-stage-in-javafx
- https://www.tutorialspoint.com/java/java_documentation.htm
- http://stackoverflow.com/questions/541920/multiple-line-code-example-in-javadoc-comment
- https://bugs.eclipse.org/bugs/show_bug.cgi?id=425554
- https://daringfireball.net/projects/markdown/basics
- https://en.support.wordpress.com/markdown-quick-reference/
- http://stackoverflow.com/questions/14165066/private-fields-from-abstract-class-cannot-be-accessed-in-the-subclass

### Human
- Dr. Duvall (Professor)
- Robert Steilberg (UTA)
- Nathaniel Brooke (Student)
- Jimmy Shackford (Student)
- Jake Conroy (Student)

## Startup File
Run Driver.java to play the game.


## Files for Testing
N/A, since is a game. Extensive testing conducted by playing game
by running Driver.java.

## Data/Resource Files
The following are images needed for the game:
- burn.jpg
- catalyst.jpg
- glassware.jpg
- hcl.jpg
- HotPlate.jpg
- logo.jpg
- msds.jpg
- rules.jpg
- StirBar.jpg

## Other Information for Running Project
### Normal Gameplay Keyboard Input:
- Space Bar: Proceed to level 1 (on splash screen)
- Left/Right Arrows: Move hot plate (in a level)
- A/D: Move hot plate twice as fast as normal (in a level).
- Q/E: Move hot plate three times as fast as normal (in a level).

### Cheat Keys:
- B = Restore all 5 chemical burns
- C = Clear all blocks from current level
- 1, 2, 3 = Jump to that level

### Game Loop:
The game begins with a splash screen. Pressing the space bar takes you to
level 1. Beating each level takes you to the next one. If you lose all 5 lives
(chemical burns) in a level, you get a game over and return to the splash
screen. If you beat all 3 levels, you return to the splash screen.

## Known Bugs
- I did some significant restructuring to the GameObject inheritence hierarchy
near the end of the project. I have tested the result and it appears to work
correctly, but if any major bugs are found, please run the version of my project
from the commit with comment "Changed Images."
- The algorithm for detecting which side of a block/the hot plate the stir bar
hits (bounceOff() in Ball.java) could be improved. Now, it sometimes cannot 
distinguish between sides if the stir bar hits near a corner. However, it works
well in the majority of cases.

## Extra Features
Easter egg on splash screen: Press R, Y, or B to change the background color
to Red, Yellow, or Blue, respectively.

## Impressions of Assignment
Overall, I enjoyed this assignment. I feel significantly more comfortable with
JavaFX and Git, as well as with refactoring increasingly larger projects.