# BoardGamesGeek 
Automation Test Task

**Scenario:**

1. Open homepage of the site
2. Navigate to the page of the game with highest rank in "The Hotness" left side menu
3. Retrieve information about a particular game from site api
4. Parse response to get most voted option in Language Dependence poll
5. Assert Language Dependence text presented in the game page Description block equals the most voted Language Dependence level.

Run the following gradle task from the directory path where build.gradle file is located:
`gradle cucumber`

