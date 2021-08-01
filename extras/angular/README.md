# Build the Frontend of the `Hero App` With Angular
Follow this read-through tutorial to build the UI to hit the [backend Spring Boot API titled `Hero-Backend`](https://github.com/210517-Enterprise/demos/tree/main/extras/angular/Hero-Backend).
> *`Hero-Backend` is a simple Spring Boot App complete with an in-memory H2 database to which `Hero` objects are persisted and retrieved via a `RestController`.  This Angular app will provide the User Interface to perform CRUD operations against the server.*

## Angular Prerequisites & Project Initialization

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

## 