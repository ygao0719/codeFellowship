# CodeFellowship

1. Build an app that allows users to create their profile on CodeFellowship.
2. The site should allow users to create an ApplicationUser on the “sign up” page.
3. The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
4. Allow users to follow other users. Following a user means that their 
posts show up in the logged-in user’s feed, where they can see what all 
of their followed users have posted recently.


## Direction to run
1. Clone the repo and type in ./gradlew bootRun
2. Open the browser and go to localhost:8080/, try following url:
- /signup
- /login
- /users/{id}: user's profile page
- /users/{id}/followees: see all people user is following.
- /welcome : 
  - allow user to add post to their profile page. 
  - checkout people's profile around you
  - follow friends around you.

### Deployed to AWS Elastic Beastalk
URL: Codefellowship-env.7bmxnypfx9.us-west-2.elasticbeanstalk.com 