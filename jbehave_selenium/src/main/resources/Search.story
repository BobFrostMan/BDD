Narrative:
As a regular user
I want to use search feature
so that I can find required information quickly

Scenario: Search return results by simple request
Meta:
@debug
Given open main page
When user enters '<text>' into header search
And user click search button
Then result is displayed

Examples:
|text|
|java|
|noSQL|






