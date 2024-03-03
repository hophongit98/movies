# movies
Egs challenge

## Project overview


The app follows clean architecture. Here’s what you will find in each package:
1. Data: contains
+ api calls, using Retrofit.
+ dto model
+ mapper to convert dto model to domain model.
+ Implementation of repository because data layer depends on domain layer, so it needs to implement what domain layer needs.
2. Domain: contains
+ Domain model
+ Repository
+ Usecases
3. Presentation: contains
+ ViewModel
+ Activity
4. Network
5. DI
6. Service
7. Utils

## App flow overview
This flow will be applied for both activities: MovieList and MovieDetail
![architecture](https://github.com/hophongit98/movies/assets/48801541/cc680d9a-87e5-4d68-b0b0-c00aeec64aca)

### Details
1.	Architecture: MVVM
2.	Testing: Junit, Mockito
3.	LiveData and ViewModel for managing UI data.
4.	Service and Kotlin flow for requesting network.
5.	Retrofit for triggering API and caching response.
6.	Dagger2 for dependency injection.

*After fetching from the server successfully, we will display the data on the UI, but the UI only need some information that I have to map the data into an UI model that contains all the necessary information. ViewModel classes will be responsible for that.

*I am familiar with Dagger 2 and because of the limited time, I chose Dagger2 for dependency injection.


