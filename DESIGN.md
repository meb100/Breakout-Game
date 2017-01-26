# Design - Breakout: Laboratory Edition
By: Matthew Barbano

## Goals
1. Implement a functioning game using the JavaFX library
2. Organize the code in descriptively-named methods/functions and using inheritance priniciples.
3. Divide the project into modules with unique functions, such as managing animation,
creating a level, and managing objects on the screen.

## Adding New Features
1. To add a new level, modify the screens array in Driver.java and its access in Driver.java.
Then, add the level-unique algorithm in a helper method in BlockGrid.java
2. To add a new powerup, create a new class extending PowerupBlock.java and implement
the collisionWithBall() method.

## Major Design Choices
1. I created a GameObject hierarchy because it seems that the JavaFX shapes on
screen needed to store a large amount of information about themselves not inherent
in the JavaFX ImageView implementation and know how to take actions specific to my
game.
2. I created the Screen hierarchy (Startup and Level are subclasses) because I
wanted a place to store status's named constant values and because Startup
and Level represented what came up on the game screen.
3. I used the status variable to control the game loop because it was
not specific to a Screen subclass and could be accessed anytime.

## Assumptions/Decisions to Resolve Ambiguities
1. I decided to work on a single level at first, assuming all of the other
levels would work properly.
2. When implementing Level, I assumed that calls to the instances of GameObject hierarchy
would work as I imagined, even before I created the hierarchy.