# RemoteControl - Example for abstraction

This was based from chapter 24 of `Android Programming: The Big Nerd Ranch Guide`, which illustrates a use-case for the `<includes>` and `style` attributes of Android. I didn't like the implementation that was used to illustrate this point, and wrote how I would approach it personally.

(Note that the book is intended for a splash introduction into programming for Android, so admittedly my proposed approach is a bit overkill for the intended audience, but I still like this better. I think it makes more sense in actual real-life development scenarios.)

---

### Motivation
First, why I didn't like the illustration provided by the book:

The book uses the `<includes layout>` function to repeat `TableRow` widgets in the layout to create its grid of actionable buttons. While this technically works, I believe `<includes>` is better suited for a single-purpose layout component that can be used across multiple views. `Toolbar` is a good example of this, as it can be repeatable across different screens and configured differently as the screen suits it. This is a good use case as the toolbar has one repeatable functionality/purpose.

However, the buttons in this project each have different (although very minor) functions: they differ in the number they represent. Since `<includes>` refers to a specific layout view, the IDs of the widgets within the specified layouts cannot be changed and renders the child widgets inflexible to changes in relation to each other. This is exemplified by the fact that the book defines each button's function and behaviour by for-loops and tracking by an index.

Second, the fragment in question knows too much about how the remote works. Since it is responsible for assigning click listeners to each individual button, it is now also implicitly responsible for handling how to parse that interpretation. It's clear to see that the rows of buttons work together to create a resulting string, with additional logic given to the Delete/Enter buttons - these can be wrapped into a custom widget that simply returns the results of their operations.

---

### Approach
I've changed the implementation by doing the following:

1. Extend `button` to create a custom widget for our remote buttons. By giving it xml attributes for the button type (`NUMBER` \ `ENTER`\ `DELETE`)* and the number it represents, this allows for a non-programmatic approach to assigning the appropriate values so that the parent view/activity doesn't have to.
2. Create a custom widget for the `TableView` of buttons. This widget is now responsible for interacting with the buttons by assigning it appropriate click listeners. Once an operation has been done, we notify the parent of this widget via callback method.
3. The fragment now is only responsible for creating a callback that will change the textviews as necessary. 

\* This enum doesn't actually do much in the ways of business logic, but I thought adding it would provide flexibility to extend the logic of the buttons if needed.

---
### TODOs
1. Add tests. This should work as intended, but having tests is always nice.