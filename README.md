## *The Digital Closet* Personal Project
### UBC CPSC 210 Term Project
###### Felix Grund (professor) and Arthur Marques (TA)

My term project is called *The Digital Closet*.   

Have you ever wanted to visually create outfits using your own clothes in your closet? Do you 
wake up in the morning scrambling to find the perfect outfit amongst the cesspool you call a closet?<br>

> What will the application do?<br>

Look no further because *The Digital Closet* is the perfect app for you! Recreate your closet simply by
entering some information about each piece of clothing! Give each piece of clothing tags so that you
can easily find them within the app. Create and save your favorite oufits to remove the morning stressors
which will keep you looking beautiful and fashionable. Finally, track what you wore each day and favorite
your best sets.

> Who will use it?<br>

You might be wondering, is this the right application for you? ***OF COURSE IT IS***!!! Teenagers and young 
adults can use this app to constantly look stylish without the daily hassle of **thinking**. All the adults
going through **mid-life crises** can look stylish while contemplating your self-identity and confidence. Even
you seniors can make use of this app. It's never too late to look good. From babies to the casket, this app is
for ***ANYONE***.

> Why is this project of interest to you?<br>

This project was inspired by my laziness and how much I pointlessly stare at things on my phone. Whenever, I procrastinate
on responsibilities such as washing or folding my clothes, I end up procrastinating by playing with a trending app
or watching a YouTube video. I thought, what if I could organize my clothes with my phone! It would be
much more interesting, fun, and it might motivate me to organize my actual clothes after playing around
with the app especially if the app requires interaction and observations of the clothing items. Many others own 
multiple times the amount of clothes and accessories that I own. I can only imagine how hard it is for them to 
build the self-motivation. This app is for all the people who procrastinate on folding clothes but can also
be used by anyone who simply wants to look good without the stress!

---

### User Stories
* As a user, I want to be able to add an item of clothing to my digital closet
* As a user, I want to be able to classify clothing by Type 
* As a user, I want to be able to view and sort clothing according to their tags
* As a user, I want to be able to create sets of clothing to design outfits
* As a user, I want to be able to edit my outfits and clothes
* As a user, I want to be able to login and register an account in which my closet is saved
* as a user, I want to be able to load the closet associated with my account when I Login

---

### Setup
>Import everything in the External Libraries folder to your classpath

This project uses:
* jackson-annotations-2.11.1.jar
* jackson-core-2.11.1.jar
* jackson-databind-2.11.1.jar
* jackson-datatype-jsr310-2.11.1.jar

---

## Phase 3
### Instructions for Grader
* To run the GUI, please run the ***Main*** class in the gui package
* You can generate the ***first*** required event by inputting a username and password in the respective text fields
and press the Register button
    * You may register any number of accounts, which will be saved in the UserInfo.json file in the data directory
    * You must login by inputting a **registered** account and clicking the login button to use the application 
* You can generate the ***second*** required event by navigating to the Closet tab and into the Add Clothing Form
    * Fill out the form according to the instructions and click the Add Clothing button
    * You can add as many items of Clothing as you wish, which can be viewed in the View Clothing section
* You can trigger one of the 3 audio components by:
    * starting the application will play soothing startup music
    * closing a frame by clicking the top-right "X" or clicking the Save and Quit button will play a shut-down sound
    * clicking any button will play a button click (in the panels containing implementations)
* You can save the state of the application by navigating to the ***Save and Quit*** tab and clicking the Save and Quit Button
    * you may quit from the Main Menu or login again/to a different account
    * data is stored in the 2 user-specific files labelled *username*Closet.json and *username*StyleBoard.json
    * for the purpose of this phase of the project, there will only be data in the *username*Closet.json file
* You can reload the state of the application by logging in with the user-specific credentials
    * loaded data is user-specific
    
---

## Phase 4
### Phase 4: Task 2
> Test and design a class that is robust.  You must have at least one method that throws a checked exception. 
> You must have one test for the case where the exception is expected and another where the exception is not expected.
* An exception that I designed for my project is the DuplicateClothingException
* A method that throws this exception is the addClothing(Clothing clothing) method in the Closet class of my model 
* An example of when this exception is caught is in gui.tabbedframe.closetpane.AddClothingButtonListener
    * when adding a clothing, if the clothing already exists in the closet, exception is thrown, and a message is
     displayed to the user informing them that this clothing already exists
* This is tested in the test.model.ClosetTests Class in which:
    * testAddClothingMultipleNoException is a test case where no exception is expected
    * testAddClothingExpectDuplicateClothingException is a test case where the exception is expected

### Phase 4: Task 3
*Problem 1*
* Poor Cohesion in the gui.tabbedframe.closetpane.viewclothing.ViewClothingPanel Class
    * Class contains much design for designing complicated panel components inside the
    ViewClothing JPanel related to viewing specific types of clothing
    * There are too many fields that need dedicated methods just to initialize them 

Solution 1
* Refactored the 6 identical split pane designs into one class ViewSplitPane which extends JSplitPane
    * contains a method to set which category of clothing will be put into the split pane
    * 6 split panes can be created just by instantiated 6 ViewSplitPane objects

*Problem 2*
* High coupling with values widely used in many of the gui classes
    * Semantic coupling issues with insufficient single point of control 
    * Problematic values:
        * Font style "Comic Sans MS" is individually assigned for each JTextField or JLabel
        * Font objects are constant for different groups of JLabels
        * Strings that represent the sizing chart using JLabels used in a couple classes
            * Lengthy strings may be hard to change if specification for the sizing chart changes
        * Each OptionPanel in the gui.tabbedpane.closetpane package has a Title which indicates the closet function in the
        respective JPanel.
            * JLabel and the configuration method calls can be put in an abstract class to reduce duplication

Solution 2
* made the String "Comic Sans MS" a constant, separate constant for MainMenu and TabbedPane in case a
different font style is desired
    * constants are called TABBED_PANE_FONT_STYLE and MAIN_MENU_FONT_STYLE
* The Font objects corresponding to titles of panes in closetpane package (TITLE_FONT)),
 the fonts for label textfields (LABEL_FONT) and the font for form request textfields (REQUEST_FONT) are given constants
 in the OptionPanelConstructor abstract class
* Sizing chart separated into 5 constant strings in abstract class OptionPaneConstructor
    * SHIRT_SIZING, PANTS_SIZING, SHOES_SIZING, SOCKS_SIZING, ACCESSORIES_SIZING
    * refactored necessary values in AddClothingPanel and EditClothingPanel classes
* Identical title design methods put into OptionPanel interface and OptionPanelConstructor abstract class with
configuration implementations (makeFormTitleLabel()) and a protected field (JLabel formTitle) 
    * Each class extending OptionPanel can call the formTitle.setText(*String*) method to set the text respectively
    
*Problem 3*
> if the above two design issues/solutions were insufficient for the phase 4 criteria

* The classes extending the ClothingCollection abstract class and the StyleBoard class in the model package are iterated through by
calling the List fields in them. This increases the coupling which can be reduced by using the iterator pattern

Solution
* Made the ClothingCollection abstract class implement Iterable<Clothing> and implemented the overriden iterator() method
    * Both the Closet class and Outfit class that extend ClothingCollection will also be iterable
* Made the StyleBoard class implement Iterable<Outfit> and implemented the overriden iterator() method 
* Modified all for loops that reference the list fields (e.g. myCloset.clothes, myOutfit.clothes, myStyleBoard.styleboard) reference
the objects directly.

### Phase 4 Task 3: UML Diagram Notes
* As I haven't fully implemented all of the functions of my program into a GUI, my UML Class Diagram's GUI section only
contains the class hierarchy and association relationships of the classes I've implemented for the current features
    * These features include: All Closet functions, Save and Quit, MainMenu functions.
    * These do not include: StyleBoard functions (even though a panel where StyleBoard function would be was made)
* Added 1 dependency relationship in the GUI section of the UML for clarity
    * arrow is highlighted in red
* Diagram is organized into sections by the major packages that contain the classes. 
    * Organized by 'notes' boxes surrounding groups of classes with labels in the top left corner of the 'note' box






