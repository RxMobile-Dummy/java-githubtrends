TrendingRepo :
- Created a Java based android application lists the most trending repositories that fetches data from GitHub repo API.


Features

- Home Screen
  - We have made user friendly design of home screen.
  - We have made attractive navigation tab bar at the top of the page.
    - Trending repo list screen.
      - Users can search their favourite technology GitHub repository list by clicking/ taping on technology name according to that list will be displayed
      - Users can filter-out the list according to repository name along with the short description , with the help of such kind of details user can get idea bout the repo easily.
      - User can share the repo with the help of SHARE button.
- Detail Screen
  - We have made user friendly design of repository details screen.
  - We have made attractive details page with the below details.
    - Repo Details screen
      - Users can get the particular repo will give the details like name the stars, watchers, forks.
      - Users can share repositories they like with the help of  SHARE button.
      - User can visit the repo details page with the help of VISIT SITE  in browser.

Package Structure

com.radixweb.trendingrepo
```sh
├── app
│   ├── libs
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── com
│   │   │   │   │   ├── radixweb
│   │   │   │   │   │   ├── trendingrepo
│   │   │   │   │   │   │   ├── data
│   │   │   │   │   │   │   │   ├── local
│   │   │   │   │   │   │   │   │   ├── converter
│   │   │   │   │   │   │   │   │   ├── dao
│   │   │   │   │   │   │   │   │   ├── entity
│   │   │   │   │   │   │   │   │   └── AppDatabase.java
│   │   │   │   │   │   │   │   ├── remote
│   │   │   │   │   │   │   │   │   ├── api
│   │   │   │   │   │   │   │   │   ├── interceptor
│   │   │   │   │   │   │   │   │   └── model
│   │   │   │   │   │   │   │   ├── repository
│   │   │   │   │   │   │   ├── di
│   │   │   │   │   │   │   │   ├── component
│   │   │   │   │   │   │   │   ├── module
│   │   │   │   │   │   │   ├── factory
│   │   │   │   │   │   │   ├── ui
│   │   │   │   │   │   │   │   ├── activity
│   │   │   │   │   │   │   │   ├── adapter
│   │   │   │   │   │   │   │   └── viewmodel
│   │   │   │   │   │   │   ├── utils
│   │   │   ├── res
│   │   │   │   ├── anim
│   │   │   │   ├── drawable
│   │   │   │   ├── font
│   │   │   │   ├── layout
│   │   │   │   ├── menu
│   │   │   │   ├── values
│   │   │   └── AndroidManifest.xml
├── README.md
```

Tech components :

 - Room Persistence Library : A local database that servers as a single source of truth for data presented to the user.

 - MVVM architecture : It’s pattern provides an easy way to structure the project codes. The reason why MVVM is widely accepted is that it provides modularity, testability, and a more clean and maintainable codebase.

 - Live Data :  LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. 

 - ViewModel : For implementing class that is responsible for preparing and managing the data for an Activity or a Fragment 

 - Lifecycle : For implementing helps developers understand which states activities go through when a user navigates through an app

 - RxJava2 : For implementing Observable pattern.

 - Mokito : For implementing Unit Test cases for API service, Database, Repository and ViewModel.

 - Dagger2 : For implementing dependency injection.

 - Retrofit2  : For implementing API integration & fetch the data easily from the source.

 - Okhttp3 : For implementing interceptor, logging and mocking web server.

 - Picasso : For implementing image loading from the source API.

 - Gson :  For implementing for JSON data serialisation.

