TrendingRepo :
- Created a Java based android application lists the most trending repositories that fetches data from GitHub repo API.

<img src="https://github.com/RxMobile-Dummy/java-githubtrends/blob/main/media/repo_list_screen.gif" width="200" style="max-width:100%;">   <img src="https://github.com/RxMobile-Dummy/java-githubtrends/blob/main/media/repo_detail_screen.gif" width="200" style="max-width:100%;"></br></br>

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

App Architecture :

Based on mvvm architecture and repository pattern.

<img src="https://github.com/RxMobile-Dummy/java-githubtrends/blob/main/media/mvvm.png" width="500" style="max-width:500%;">

We have follow MVVM architecture in this project because of it treats Activity's classes and XML files as views, and ViewModel classes are where we write our business logic. & it completely separates an app's UI from its logic.

 - Model: This holds the data of the application. It cannot directly talk to the View. Generally, it’s recommended to expose the data to the ViewModel through Observables.

 - View: It represents the UI of the application devoid of any Application Logic. It observes the ViewModel.

 - ViewModel: It acts as a link between the Model and the View. It’s responsible for transforming the data from the Model. It provides data streams to the View. It also uses hooks or callbacks to update the View. It’ll ask for the data from the Model.

 - ViewModelProviders.of : we have used to create ViewModel instance 

 - Live Data : It's act as observable data holder like communicator between view and model , and also we can update easily the UI from the ViewModel , therefore we have used provides public methods setValue() and getValue().

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


Package Structure

com.radixweb.trendingrepo
```sh
 ├── src
    │   ├── androidTest
    │   │   ├── db
    │   │   ├── repository
    │   │   ├── util
    │   ├── main
    │   │   ├── assets
    │   │   │   ├── fonts
    │   │   ├── java
    │   │   │   ├── data
    │   │   │   │   ├── local
    │   │   │   │   │   ├── converter
    │   │   │   │   │   ├── dao
    │   │   │   │   │   ├── entity
    │   │   │   │   ├── remote
    │   │   │   │   │   ├── api
    │   │   │   │   │   ├── interceptor
    │   │   │   │   │   ├── model
    │   │   │   │   ├── repository
    │   │   │   ├── di
    │   │   │   │   ├── component
    │   │   │   │   ├── module
    │   │   │   ├── factory
    │   │   │   ├── ui
    │   │   │   │   ├── activity
    │   │   │   │   ├── adapter
    │   │   │   │   ├── custom
    │   │   │   │   ├── viewmodel
    │   │   │   ├── utils
    │   │   ├── res
    │   │   │   ├── anim
    │   │   │   ├── drawable
    │   │   │   ├── layout
    │   │   │   ├── menu
    │   │   │   ├── values
    │   │   │   │   ├── menu
    │   │   ├── resources
    │   │   │   ├── api-response
    │   │   ├── AndroidManifest.xml
    │   ├── test
    │   │   ├── api
