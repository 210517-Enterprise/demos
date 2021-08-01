# Build the Frontend of the `Hero App` With Angular
Follow this read-through tutorial to build the UI to hit the [backend Spring Boot API titled `Hero-Backend`](https://github.com/210517-Enterprise/demos/tree/main/extras/angular/Hero-Backend).
> *`Hero-Backend` is a simple Spring Boot App complete with an in-memory H2 database to which `Hero` objects are persisted and retrieved via a `RestController`.  This Angular app will provide the User Interface to perform CRUD operations against the server.*

<br>

## Step 1: Angular Prerequisites & Project Initialization

1. You must have Node.JS installed on your machine.  If you don't, [go here to download the LTS](https://nodejs.org/en/download/).

2. **Install the Angular CLI**: run the following command in a terminal window (*you only need to do this once on your machine to install Angular*):
```
npm install -g @angular/cli
```

3. Verify installation with `ng --version`.

4. **Create a workspace and initial application**: Within a folded in VScode, run the CLI command `ng new` and provide the name `hero-frontend`, as shown here:
```
ng new hero-frontend
```

> You will be prompted to answer some questions about your app initialization:
> - `Stricter type checking?` : `N`
> - `Add Angular routing?` : `y`
> -  `Stylesheet format?` : `CSS`


5. **Run the application**: `cd` into `hero-frontend` and run the following command which will serve the intial application on `http://localhost:4200` in your browser.
```
cd hero-frontend
ng serve -o
```
> *This is similar to `yarn start` or `npm start` in a React app.  The `-o` option opens the application in a separate tab in your browser.*

<br>

## Step 2: Gut the initial boilerplate code

- In your VsCode file explorer, navigate to `src/app`.  We will create our models, components, and services within this directory.
    - *`app.component` is your root component, similar to `App.js` in React.*
    - `app.component.html` contains the precoded HTML that you see displayed on the initial page. **Delete all the code within `app.component.html`**.
        > *We will come back to this file later.*

<br>

## Step 3: Add the API URl to your Angular App

- Navigate to `src/environments` and paste `export const HERO_URL = `http://localhost:8081/api/heroes/`;` into `environment.ts`.  The file should look like so:

```ts
export const environment = {
  production: false
};

export const HERO_URL = `http://localhost:8081/api/heroes/`;
```

<br>

## Step 4: Generate custom components using the Angular CLI
This single-page application will consist of 5 main components:

- **`main` Component** : Represents the home page with a welcome header and image
- **`nav` Component** : Simple nav bar that, when clicked, routes the user to a separate "page" which is a separate component that gets loaded.
- **`all` Component** : An interface with a button that, when clicked, returns and renders all `Hero`s within the DB.
- **`register` Component** : A form that allows the user to create a new `Hero` and persist it to the DB.
- **`find` Component** : A form that accepts a `Hero`'s name as input and returns that Hero from the DB if it exists.

<br>

1. Create the `components` directory.  Run:
```
cd hero-frontend/src/app
mkdir components
```

2. Within `components`, generate your first component (`main`) using the Angular CLI.  Run:
```
ng g c main
```
> `ng` is a command to invoke the Angular CLI, `g` is shorthand for "generate", `c` is shorthand for "component", and `main` is the name of the component. *You could also run `ng generate component main`, but that's slower*.

:exclamation: Notice **the 4 files** created when we generated a component inside the `components` directory.  This is what a component in Angular consists of:

    - `main.component.html` : HTML Template of the component
    - `main.component.css` :  the component's private extrenal styling sheet
    - `main.component.ts` :  where we define the module, its properties, lifehooks, etc.
    - `main.component.spec.ts` : unit testing file

You will also notice that a file called `src/app/app.module.ts` has been updated. This is called your **`AppModule`**, or your [*root module*](https://angular.io/guide/architecture-modules) - it serves as a "registry" of all of your app's components.  This is the file that tells Angular how to construct your application.

<br>

## Step 5: Add styles & image assets
Before we begin developing our components out, let's import bootstrap and add global styles.

1. In `src/styles.css` add the following CSS rules:

<br>

```css
/* You can add global styles to this file, and also import other style files */
.panel-body  {
    word-break: break-all;
}

.panel-heading {
    background-color: #d9d9d9;
}

.panel-footer {
    background-color: #d9d9d9;
}

.image-center {
    display: block;
    margin-left: auto;
    margin-right: auto;
    max-width: 55%;
    border-radius: 25px; 
}

.image-inline {
    margin-top: 10px;
    margin-bottom: 10px;
}

.label-center {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: auto;
    font-size: 25px;
}

.list-group {
    margin-top: 10px;
}

.list-group-item-demo {
    background-color: #d9d9d9;
}

.strong-green {
    color: green;
}

.btn-center {
    display: block;
    margin-left: auto;
    margin-right: auto;
}

.btn-primary {
    background-color: #777777;
    border-color: #777777;
}

.btn-primary:hover {
    background-color: #555555;
    border-color: #777777;
}

a {
    color: #454444;
}

a:hover {
    color: gray;
}
```

<br>

2. Add an image to the `src/assets` directory. You may download it from [here](https://github.com/210517-Enterprise/demos/blob/main/extras/angular/hero-frontend/src/assets/superhero.jpg), or use any image you want.  I named it `superhero.png`.

3. **Add Bootstrap** by navigating to `src/index.html`.  Within the `<head>` tag, paste the Bootstrap CDN link (I'm using version 3.7.7).  Your `index.html` file should look like so:

<br>

```html
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>HeroFrontend</title>
  <base href="/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/x-icon" href="favicon.ico">

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- add Bootstrap minified JavaScript-->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
  <app-root></app-root>
</body>
</html>
```

<br>

*Remember that Angular is a framework which allows us to create “Single Page Applications”, and it (`index. html`) is **the single page which was provided by the server**.*

> The `<app-root></app-root>` within the `<body>` tag of `index.html` represents the **selector** of the app component.  This selector id is found in the meta-data of the component, specifically in the `app.component.ts` file under the `@Component` tag.

<br>

## Step 6: Build the `main` component typesript file & html template:

1. Go to `main.component.ts`.  You can delete the `constructor` and `ngOnInit` life-cycle hook. Instead we will give the `MainComponent` two properties: a title and image.  `main.component.ts` should look like the following:

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  title = 'Super Hero Portal';
  image = 'assets/superhero.jpg';
}
```

<br>

2. Go to `main.component.html` and paste the following code.

>  Notice the `{{ }}` around `image` and title. This is **One-way Data Binding** in which we are capturing the properties we declared in the component (`main.component.ts`) and rendering them to the view.

<br>

```html
<div>
    <div class="panel-heading">
        <span class="label label-default label-center">{{ title }}</span>
    </div>

    <!-- the Main component will have an img property and a title property that we're displaying in the html template-->
    <div class="panel-body">
        <img class="image-center" src={{image}}/>
        <div class="list-group">

            <div class="list-group-item">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <strong>Find</strong> a Superhero
            </div>

            <div class="list-group-item">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <strong>Register</strong> a Superhero
            </div>

            
            <div class="list-group-item">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <strong>Fetch</strong> all Superheroes
            </div>

        </div>
    </div>
</div>
```

<br>

:tada: You have completed the `main` Component ts file and html template! Now you need to render it.

<br>

## Step 7: View the `main` Component through *Routing*:
You may have notice that you have built out the main component with it's associated properties in the `main.componenet.ts` and it's html template, but nothing appears on the screen when you render the app. We must add a `router-outlet` to the root `app` component.

1. Open `src/app/app-routing-module.ts`. In the empty array called `Routes`, add the following:

<br>

```ts
// Make sure you import MainComponent at the top
import { MainComponent } from './components/main/main.component';

const routes: Routes = [

  // This means that an empty url will render the main component
  { path: '', redirectTo: 'main', pathMatch: 'full' }, 

  // This means that localhost:4200/main will render the main component
  { path: 'main', component: MainComponent },
];
```

<br>

2. Now navigate to `app.component.html` (which should currently be empty) and add the following code:

<br>

```html
<div class="container-fluid">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="panel panel-default">
  
        <!-- we inject our route here-->      
          <router-outlet></router-outlet>

      </div>
    </div>
  </div>
</div>
```

<br>

> `router-outlet` in Angular works as a placeholder which is used to load the different components dynamically based on the activated component or current route state.  This will load whatever component we have specified to be rendered based on the url.

<br>

:tada: `Main` Component is finished; You should see this in your browser!

<br>

<img src="imgs\main_1.png">

<br>

## Step 8: Build an HTTP Service
Our other component's will have functions that make an HTTP request to our server.  In Angular, we modularize this responsibility in the form of a [**Service**](https://angular.io/guide/architecture-services).

> A **Service** is a broad category encompassing any value, function, or feature that an application needs. A service is typically a class with a narrow, well-defined purpose. It should do something specific and do it well. Angular distinguishes components from services to increase modularity and reusability.

<br>

We will use **Dependency Injection** to inject an HTTP Service into our components.

1. Within `src/app` create a directory called `services`:
```
cd src/app
mkdir services
```

2. **Generate a Service with the Angular CLI**: Similar to how we generated a component with the CLI, we will generate a service called `hero-service`. Run:
```
cd src/app/services
ng g s hero
```

3. Open `hero.service.ts` (there is a unit testing file, and a typscript file). At the top, import `import { HttpClient, HttpHeaders } from '@angular/common/http';`.

4. Import the url to the api we'll be hitting with the HttpClient as well with `import { HERO_URL } from './../../environments/environment';`

5. We will use constructor injection to inject the HttpClient dependency into this custom service.  Your `hero.service.ts` file should look as follows:

<br>

```ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HERO_URL } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  constructor(private http: HttpClient) { }
}
```

<br>

## Step 9: Declare `HttpClientModule` as an import within `app.module.ts`

- Open `src/app/app.module.ts` and add `HttpClientModule`.  You should also import it at the top with `import { HttpClientModule } from '@angular/common/http';`.

Your `app.module.ts` file should look like this:

```ts
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

<br>

## Step 10: Create `Hero` & `ClientMessage` Models
Since we will be creating/retrieving `Hero` and `ClientMessage` models, we need to create a class within our Angular App to represent the object that we'll be using our Hero Service to perform CRUD operations on.

1. In `src/app` create a directory called `models`:
```
cd src/app
mkdir models
```

> *models* are technically `classes` in Angular.

2. `cd` into `models` directory and generate a `Hero` class like so:
```
cd models
ng generate class hero --type=model
```

3. Create one for `ClientMessage`, too.
```
cd models
ng generate class client-message --type=model
```

4. In `hero.model.ts` write the following code:

<br>

```ts
export class Hero {

    id: number;
    name: string;
    superPower: string;
    hasCape: boolean;

    constructor(id: number, name: string, superPower: string, hasCape: boolean) {
        this.id = id;
        this.name = name;
        this.superPower = superPower;
        this.hasCape = hasCape;
    }
}
```
<br>

5. In `client-message.model.ts` write the following code:

<br>

```ts
export class ClientMessage {

    message: string;

    constructor(message: string) {
        this.message = message;
    }
}
```

<br>

## Step 11: Complete the `HeroService`

1. Return to `hero.service.ts`. Add a properties to our `HeroService` class called `httpOptions` like so:

<br>

```ts
export class HeroService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  }
}
```

<br>

2. At the top of `hero.service.ts` import the following (this includes the models we'll be dealing with between client and server):

<br>

```ts
import { HERO_URL } from './../../environments/environment';
import { ClientMessage } from './../models/client-message.model';
import { Hero } from './../models/hero.model';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
// Remember to add HTTPClient to your imports[] in your app.module.ts!
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
```

<br>

3. We will now create several methods:
  - `registerHero()` : Sends a **post** request to our server, inserting a `Hero`.
  - `findHero()` : Sends a **post** request to our server, retrieving a `Hero` by name.
  - `findAllHeroes()` : Sends a **get** request to our server, retrieving an array of `Hero`s.
  -  `handleError<T>()` : A custom error handling method chained to the above methods incase something goes wrong.

<br>

`hero.service.ts` should look like this:

<br>

```ts
import { HERO_URL } from './../../environments/environment';
import { ClientMessage } from './../models/client-message.model';
import { Hero } from './../models/hero.model';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
// Remember to add HTTPClient to your imports[] in your app.module.ts if you havn't already!
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  }

  /*
  * POST
   */
  public registerHero(hero: Hero): Observable<ClientMessage>  {
    // this will return a client message if we are successfully able to POST a hero to our server 
    return this.http.post<ClientMessage>(`${HERO_URL}register`, hero, this.httpOptions)
                                                                      // adding httpOptions constructs asks our 
                                                                      // server to return the client-message as JSON.
    
    //.pipe(catchError...) is only necessary for handling errors tht could occur
    .pipe(
      catchError(this.handleError<any>('cannot register hero!'))
    )
  }

  /*
  * POST
  */
  public findHero(hero: Hero): Observable<Hero> {

    return this.http.post<Hero>(`${HERO_URL}findHero`, hero, this.httpOptions)
    .pipe(
      catchError(this.handleError<Hero>('getHero', undefined))
    )
  }

  /*
  * GET
  */
  public findAllHeroes(): Observable<Hero[]> {

    return this.http.get<Hero[]>(`${HERO_URL}findAll`) 
    .pipe(
      catchError(this.handleError<Hero[]>('getHeroes', []))
    )
  }

  /*
  * CUSTOM ERROR HANDLER
  */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log it to the console if something goes wrong

      // Let the app keep running by returning an empty result.
      return of(result as T);
    }
  }
}
```

<br>

## Step 12: Build the `All` Component and inject `HeroService` to retrieve Heroes

1. Generate the `AllComponent` like so:
```
cd src/app/components
ng g c all
```

<br>

2. At the top of `all.component.ts` import the appropriate models and services being used:

<br>

```ts
import { ClientMessage } from './../../models/client-message.model';
import { Hero } from './../../models/hero.model';
import { HeroService } from './../../services/hero.service';
```

<br>

3. `AllComponent` will have 3 properties:

<br>

```ts
  title = 'All Heroes';
  public heroes: Hero[] = [];
  public clientMessage: ClientMessage = new ClientMessage('Sorry, no heroes to display');
```

<br>

4. User constructor injection to inject the `HeroService`:

<br>

```ts
constructor(private heroService: HeroService) { }
```

<br>

5. Under `ngOnInit()`, initialize the `heroes` with all hero objects fetched from the server:

<br>

```ts
  ngOnInit(): void {
      // we will set the heroes array = to all of the heroes fetched from the server
    this.findAllHeroesFromService();
  }
```

<br>

6. `AllComponent` will have one method which is `findAllHeroesFromService()`.  This will call the GET method from the `HeroService` and be featured in the html template.

<br>

```ts
  public findAllHeroesFromService(): void {
    // in this method we call on our service to fetch the heroes array and 
    // set it equal to our heroes[] property
    this.heroService.findAllHeroes().subscribe(data => this.heroes = data)
  }
```

<br>

`all.component.ts` should look like this:

<br>

```ts
import { Component, OnInit } from '@angular/core';
import { ClientMessage } from './../../models/client-message.model';
import { Hero } from './../../models/hero.model';
import { HeroService } from './../../services/hero.service';

@Component({
  selector: 'app-all',
  templateUrl: './all.component.html',
  styleUrls: ['./all.component.css']
})
export class AllComponent implements OnInit {

  title = 'All Heroes';
  public heroes: Hero[] = [];
  public clientMessage: ClientMessage = new ClientMessage('Sorry, no heroes to display');

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
      // we will set the heroes array = to all of the heroes fetched from the server
    this.findAllHeroesFromService();
  }

  public findAllHeroesFromService(): void {
    // in this method we call on our service to fetch the heroes array and set it equal to 
    // our heroes property
    this.heroService.findAllHeroes().subscribe(data => this.heroes = data)
  }

}


```

<br>

## Step 13: Complete `all.component.html` template
Write the following code into `all.component.html` :

<br>

```html
<div>
    <div class="panel-heading">
        <span class="label label-default label-center">{{ title }}</span>
    </div>
    <div class="panel-body">
        <div class="list-group">
            <div class="list-group-item">
                <div *ngFor="let h of heroes" class="list-group-item">
                    <!-- the "heroes" part of our *ngFor directive represents the heores array in our .ts file.
                         the "h" represents each individual object within the array -->

                    <strong>ID</strong>: {{ h.id }}
                    <strong>Name</strong>: {{ h.name }}
                    <strong>Power</strong>: {{ h.superPower }}
                    <strong>Has Cape?</strong>: {{ (h.hasCape) ? 'Yes' : 'No' }} <!-- Here I'm using a ternary operator-->

                </div>

                <div>
                    <button class="btn btn-primary btn-center" (click)="findAllHeroesFromService()">GET THE HEROES!</button>
                </div>

                <div *ngIf="heroes.length == 0">
                    {{ clientMessage.message }}
                </div>
            </div>
        </div>
    </div>
</div>
```

<br>

> *Notice the `*ngFor Directive`.  **`NgFor`** is a built-in template directive that makes it easy to iterate over something like an array or an object and create a template for each item. To learn more about directives in Angular, go [here](https://www.digitalocean.com/community/tutorials/angular-ngfor-directive#:~:text=NgFor%20is%20a%20built%2Din,a%20template%20for%20each%20item.&text=of%20users%20means%20that%20we,ngFor%20creates%20a%20parent%20template.).*

<br>

## Step 13: Add `AllComponent` to `app-routing.module.ts`
Your `app-routing.module.ts` file should look like this:

<br>

```ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
 // import the AllComponent!
import { AllComponent } from './components/all/all.component';

const routes: Routes = [
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: 'main', component: MainComponent },
  // add the AllComponent!
  { path: 'all', component: AllComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

<br>

## Step 14: Build the `RegisterComponent`

1. Create the `register` component:
```
cd src/app/components
ng g c register
```

Your `register.component.ts` file should look like this:

<br>

```ts
import { ClientMessage } from './../../models/client-message.model';
import { Hero } from './../../models/hero.model';
import { HeroService } from './../../services/hero.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  title = 'Register Hero'

  // Constructor Injection
  constructor(private heroService: HeroService) { }

  // For databinding
  public hero: Hero = new Hero(0, '', '', false);

  // Client message to the user
  public clientMessage: ClientMessage = new ClientMessage('');

  public registerHeroFromService(): void {

    this.heroService.registerHero(this.hero).subscribe(data => this.clientMessage = data, 
      error => this.clientMessage.message = 'SOMETHING WENT WRONG!');
      
  }
}
```

<br>

2. **Build the `RegisterComponent` html template**: Your `register.component.html` file should look like this:

<br>

```html
<div>
    <div class="panel-heading">
        <span class="label label-default label-center">{{ title }}</span>
    </div>
    
    <div class="panel-body">
        <div class="list-group">
            <div class="list-group-item">

                <div class="form-group">
                    <!-- Here I want to take input from the user, and change the property of the hero object in my ts file 
                         This is called 2-way property binding which is achieved with [()] -->
                    <input [(ngModel)]="hero.name" placeholder="Name" class="form-control">
                    <input [(ngModel)]="hero.superPower" placeholder="Superpower" class="form-control">
                    <input [(ngModel)]="hero.hasCape" placeholder="Do they have a cape?" class="form-control">
                </div>

                <div>
                    <button class ="btn btn-primary btn-center" (click)="registerHeroFromService()">REGISTER HERO!</button>
                </div>
                <div>
                        {{ clientMessage.message }}
                </div>

            </div>
        </div>
    </div>
</div>
```

<br>

## Step 14: Add `RegisterComponent` to `app-routing.module.ts`
Within `app-routing.module.ts`, add both `  { path: 'register', component: RegisterComponent }` to the `Routes` array, and `import { RegisterComponent } from './components/register/register.component';` at the top with the other imports.

<br>

## Step 15: Build and Route the `FindComponent`

1. Create the `find` component:
```
cd src/app/components
ng g c find
```

2. Your `find.component.ts` file should look like this:

<br>

```ts
import { ClientMessage } from './../../models/client-message.model';
import { Hero } from './../../models/hero.model';
import { HeroService } from './../../services/hero.service';
import { Component } from '@angular/core';


@Component({
  selector: 'app-find',
  templateUrl: './find.component.html',
  styleUrls: ['./find.component.css']
})
export class FindComponent {

  title = 'Find Hero';

  // Capture user input -- we will have an empty hero object like in register.component.ts
  public hero: Hero = new Hero(0, '', '', false)

  // Present the recieved hero -- set it to empty values at first
  public recievedHero: Hero = new Hero(0, '', '', false)

  // Message to the User.
  public clientMessage: ClientMessage = new ClientMessage('');

  constructor(private heroService: HeroService) { }

  public findHeroFromService(): void {

    this.heroService.findHero(this.hero).subscribe(data => this.recievedHero = data, // make sure you are setting your data equal here
      error => this.clientMessage.message = 'SOMETHING WENT WRONG');
  }
}
```

<br>

3. Your `find.component.html` file should look like this:

<br>

```html
<div>
    <div class="panel-heading">
        <span class="label label-default label-center">{{ title }}</span>
    </div>

    <div class="panel-body">
        <div class="list-group">
            <div class="list-group-item">
                <div class="form-group">
                    
                    <!-- here we take in some usre input and bind it to our component.ts hero property
                    This repreents the hero object that we include in our post request to our server.  -->
                    <input [(ngModel)]="hero.name" placeholder="Hero's name" class="form-control" />

                </div>

                <div>
                    <button class="btn btn-primary btn-center" (click)="findHeroFromService()">FIND HERO!</button>
                </div>

                <div *ngIf="recievedHero.name">
                    {{ recievedHero.superPower }} 
                </div>
                <div>
                    {{ clientMessage.message }}
                </div>
            </div>
        </div>
    </div>
</div>
```

3. **Add `FindComponent` to `app-routing.module.ts`**: Within `app-routing.module.ts`, add both `  { path: 'find', component: FindComponent }` to the `Routes` array, and `import { FindComponent } from './components/find/find.component';` at the top with the other imports.