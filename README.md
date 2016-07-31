# Expresso for Reddit

# What can this App currently do?
* It displays reddit posts from the subreddit of your choice in a fast and minimal way.
* The app has infinite scrolling so that you don't have to change pages which is annoying. 
* Read the comments from each post. The comments are neatly arranged in a tree structure and color coded for clarity.
* Web-view for each post. When you click on an image from let's say Imgur.com, the image opens in a web-view.
* Data like upvotes, subreddit name and username is displayed on each post or comment.

# What technology does it use?
* Reddit's data is up for grabs, it is a combination of nested objects and arrays and is easy enough to access.
* I used Picasso for image loading, which inturn uses singleton pattern and makes do with adding just a few lines of code.
* I used Volley for HTTP requests along with singleton class.
* Used cardview to display posts and comments.

# What I plan to do in the future?
* I plan to add user login so that you can only see the subreddits you subscribe to and reply to comments and so on.
* De-clutter the user interface. 

# Most Challenging part of the project so far?
* Displaying the comments in a tree-like structure, becasue each comment can have N replies and each reply can Have M replies. So the solution is recursive. Storing comments of each level in an Arraylist and displaying them in cardview in a tree like and color coded structure proved to be challenging.


