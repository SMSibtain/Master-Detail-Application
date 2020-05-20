# Master Detail Application
## Introduction
This is a sample project which demonstrates the collection of movie list to be displayed from assets, integration of Flickr APIs.

We'll be using the most viewed section of this API. Note: you need to signup for an API key
at: https://www.flickr.com/services/api/misc.api_keys.html, then replace the ‘YOUR_API_KEY’ below with
the API key assigned to your account.
https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key={YOUR_API_KEY}&format=json&nojsoncallback=1&text={MOVIE_TITLE}&page=1&per_page=10

To test this API, you can change MOVIE_TITLE from the collection (movies.json) file which is in assets which provide you the photos collection details if available.

Again there comes another flickr photo search api which result in image,
http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
In this you have to replace the ‘farm’,‘server’,‘id’ and ‘secret’ which is being returned in the photos collection using the respective index object properties.

## Details
#### Basic Architecture
This project is build using the best practises mentioned on android developer guidelines, following are the main components which used,

 - MVVM (as a primary arch)
 - Data Binding v2
 - Design Patterns (Repository, Factory, Singleton etc)

#### Common Libraries

 - Retrofit (for network related operations)
 - Picasso (for image loading)
 - Mockk (for mocking in kotlin)
 - androidx.arch.core (for using live data in unit test)


## Author
Sibtain Raza
